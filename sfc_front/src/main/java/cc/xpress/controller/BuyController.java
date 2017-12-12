package cc.xpress.controller;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.config.CommonConfig;
import cc.xpress.config.FrontUserConfig;
import cc.xpress.service.IOrderService;
import cc.xpress.utils.ToJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.jdbc.Expectation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-04 0:35
 * @modified By:
 */
@Controller
@RequestMapping("buy")
public class BuyController {
    Logger logger=Logger.getLogger(BuyController.class);
    @Resource(name = "orderService")
    IOrderService orderService;
    @RequestMapping(path = "submit_order")
    public String submitOrder(String userTel, String seatId, String planId, Model model){
        try{
            Session session = SecurityUtils.getSubject().getSession();
            UserTbDTO userTbDTO = (UserTbDTO)session.getAttribute(FrontUserConfig.USER_BEAN);
            logger.info("[submit order]-"+"userTel:"+userTel+"---"+"seatIds:"+seatId+"---"+"planId:"+planId);
            OrderTbDTO order = orderService.createOrder(userTel, seatId, planId, userTbDTO);
            model.addAttribute(CommonConfig.ORDER,order);
            return  "pay";
        }catch (NullPointerException|IllegalArgumentException e){
            logger.error("-[method:"+e.getStackTrace()[0].getMethodName()+"]:"+e.getMessage());
            return "redirect:/index/plan/"+planId+".html";
        }catch (Exception e){
            logger.error("-[method:"+e.getStackTrace()[0].getMethodName()+"]:"+e.getMessage());
            return "redirect:/index/plan/"+planId+".html";
        }
    }

    /**
     *  查询登陆用户的未支付订单数量
     */
    @RequestMapping(path = "check_order",produces = MediaType.ALL_VALUE)
    @ResponseBody
    public String checkOrder(){
        try{
            Session session = SecurityUtils.getSubject().getSession();
            UserTbDTO userTbDTO = (UserTbDTO)session.getAttribute(FrontUserConfig.USER_BEAN);
            int nonPaymentOrderCount = orderService.getNonPaymentOrderCount(userTbDTO);
            return Long.toString(nonPaymentOrderCount);
        }catch (Exception e){
            logger.error("-[method:"+e.getStackTrace()[0].getMethodName()+"]:"+e.getMessage());
            return "-2";
        }
    }
    @RequestMapping(path = "order/{orderId}.html")
    public String toPayOrder(@PathVariable String orderId,Model model){
        try {
            Session session = SecurityUtils.getSubject().getSession();
            UserTbDTO userTbDTO = (UserTbDTO)session.getAttribute(FrontUserConfig.USER_BEAN);
            OrderTbDTO nonPaymentOrder = orderService.getNonPaymentOrder(orderId, userTbDTO);
            model.addAttribute(CommonConfig.ORDER,nonPaymentOrder);
            return "pay";
        } catch (IllegalAccessException|NullPointerException|IllegalArgumentException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            model.addAttribute(CommonConfig.ERROR,e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "redirect:/member/order";
    }
    @ResponseBody
    @RequestMapping(path = "cancel_order",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String cancelOrder(String orderId){
        String json="{}";
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserTbDTO userTbDTO = (UserTbDTO)session.getAttribute(FrontUserConfig.USER_BEAN);
        try {
            int  status= orderService.cancelOrder(userTbDTO, orderId);
            json = ToJson.toJson(json, CommonConfig.STATUS, Integer.toString(status));
            return json ;
        } catch (IllegalAccessException|IllegalArgumentException|NullPointerException e) {
            return ToJson.toJson(json, CommonConfig.ERROR,e.getMessage());
        } catch (Exception e){
            return ToJson.toJson(json, CommonConfig.ERROR,CommonConfig.UNEXPECTED_ERROR);
        }
    }
    @RequestMapping(path="pay_order",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String payOrder(String orderId,String payMethod){
        String json="{}";
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            UserTbDTO userTbDTO = (UserTbDTO)session.getAttribute(FrontUserConfig.USER_BEAN);
            int status = orderService.payOrder(userTbDTO, orderId, payMethod, null, true);
            return ToJson.toJson(json, CommonConfig.STATUS, Integer.toString(status));
        } catch (IllegalAccessException|IllegalArgumentException|NullPointerException e) {
            return ToJson.toJson(json, CommonConfig.ERROR,e.getMessage());
        }catch (Exception e){
            return ToJson.toJson(json, CommonConfig.ERROR,CommonConfig.UNEXPECTED_ERROR);
        }
    }
}
