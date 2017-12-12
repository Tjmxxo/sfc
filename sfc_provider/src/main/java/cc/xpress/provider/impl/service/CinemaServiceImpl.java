package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.CityTbDTO;
import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.vo.CinemaVo;
import cc.xpress.bean.vo.Node;
import cc.xpress.config.PlanStatus;
import cc.xpress.dao.ICinemaDAO;
import cc.xpress.dao.ICityDAO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.dao.IUserDAO;
import cc.xpress.service.ICinemaService;
import cc.xpress.utils.CommonUtils;
import cc.xpress.utils.ImageUtils;
import cc.xpress.utils.ToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static cc.xpress.utils.CommonUtils.isConvertedToNumber;

@Service("cinemaService")
public class CinemaServiceImpl implements ICinemaService {

    @Autowired
    private ICinemaDAO cinemaDAO;

    @Autowired
    private ICityDAO cityDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IPlanDAO planDAO;

    @Transactional
    @Override
    public CinemaTbDTO getCinema(String cinemaId) throws NumberFormatException {
        Integer id;
        if (isConvertedToNumber(cinemaId)) {
            id = Integer.parseInt(cinemaId);
        } else {
            throw new NumberFormatException();
        }
        return cinemaDAO.getEntityById(CinemaTbDTO.class, id);
    }

    @Transactional
    @Override
    public List<CinemaTbDTO> getAllCinemaList(String cityId) throws NumberFormatException {
        Integer id;
        List<CinemaTbDTO> cinemaTbDTOS;
        if (isConvertedToNumber(cityId)) {
            id = Integer.parseInt(cityId);
        } else {
            id = 0;
        }
        if (id == 0) {
            cinemaTbDTOS = cinemaDAO.getAllCinemas();
        } else {
            cinemaTbDTOS = cinemaDAO.query("from", CinemaTbDTO.class, new Node("cityTbDTO", cityDAO.getEntityById(CityTbDTO.class, id))).list();

        }
        return cinemaTbDTOS;
    }

    @Transactional
    @Override
    public Integer saveCinema(CinemaVo cinemaVo) {
        CinemaTbDTO cinemaTbDTO = cinemaVo.getCinemaTbDTO();
        cinemaTbDTO.setCityTbDTO(cityDAO.getEntityById(CityTbDTO.class, Integer.parseInt(cinemaVo.getCityId())));
        if (cinemaTbDTO.getCinemaId() == null) {
            return (Integer) cinemaDAO.saveEntity(cinemaTbDTO);
        }
        cinemaDAO.updateEntity(cinemaTbDTO);
        return cinemaTbDTO.getCinemaId();
    }

    @Transactional
    @Override
    public List<Integer> deleteCinema(String[] cinemaIds) throws NumberFormatException, IllegalArgumentException {
        List<Integer> integers = new ArrayList<>();
        CinemaTbDTO cinemaTbDTO;
        for (String cinemaId : cinemaIds) {
            Integer id = Integer.parseInt(cinemaId);
            if (id == null) {
                throw new NumberFormatException();
            }
            cinemaTbDTO = cinemaDAO.getEntityById(CinemaTbDTO.class, Integer.parseInt(cinemaId));
            if (cinemaTbDTO == null) {
                throw new IllegalArgumentException();
            } else {
                integers.add(cinemaTbDTO.getCityTbDTO().getCityId());
            }
            if (cinemaTbDTO != null) {
                cinemaDAO.deleteEntity(cinemaTbDTO);
                ImageUtils.deleteOne(cinemaTbDTO.getCinemaImg());
                integers.add(id);
            }
        }
        return integers;
    }

    @Override
    public Integer deleteOne(String cinemaId) throws NumberFormatException, IllegalArgumentException {
        if (isConvertedToNumber(cinemaId)) {
            throw new NumberFormatException("编号非数字");
        }
        CinemaTbDTO cinemaTbDTO;
        cinemaTbDTO = cinemaDAO.getEntityById(CinemaTbDTO.class, Integer.parseInt(cinemaId));
        if (cinemaTbDTO == null) {
            throw new IllegalArgumentException("查无此影院");
        } else if (cinemaTbDTO != null) {
            cinemaDAO.deleteEntity(cinemaTbDTO);
            ImageUtils.deleteOne(cinemaTbDTO.getCinemaImg());
        }
        return Integer.parseInt(cinemaId);
    }

    /**
     * 根据城市和电影查询点影院
     *
     * @param movieId
     * @param cityId
     * @return
     */
    @Transactional
    @Override
    public CinemaTbDTO[] getCinemaByCityAndMovie(String movieId, String cityId) {
        if (!CommonUtils.isConvertedToNumber(movieId) || !CommonUtils.isConvertedToNumber(cityId)) {
            return null;
        }
        int cId = Integer.parseInt(cityId);
        int mId = Integer.parseInt(movieId);
        List<PlanTbDTO> plans = planDAO.getPlanByMovieAndCityGroupByCinema(mId, cId, PlanStatus.ENABLE_STATUE);
        int size = plans.size();
        if (size == 0) {
            return null;
        }
        CinemaTbDTO[] cinemaArray = new CinemaTbDTO[size];
        //TODO 逻辑是否存在问题
        for (int i = 0; i < size; i++) {
            if (plans.get(i).getHallTbDTO() == null || plans.get(i).getHallTbDTO().getCinemaTbDTO() == null) {
                return null;
            }
            cinemaArray[i] = plans.get(i).getHallTbDTO().getCinemaTbDTO();
        }
        return cinemaArray;
    }
}
