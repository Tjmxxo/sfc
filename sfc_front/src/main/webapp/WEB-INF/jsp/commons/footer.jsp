<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-11-30
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--------- 底部 ---------------->
<div id="foot">
    <div class="wrap">
        <div class="footer">
            <div class="service_mode clearfix">
                <div class="service_left">
                    <div class="local_city fl">
                        <div class="local_title clearfix"><strong>本地影城</strong><span class="local_width1"></span><a
                                href="cinema/index">更多&gt;&gt;</a></div>
                        <div class="yard_list clearfix" id="cinema_div_0" style="display: block">
                            <div class="yard_left_free left"></div>
                            <div class="yard_middle left">
                                <a href="cinema/detail/31128901" class="yard_picture"><img src="" width="76px"
                                                                                           height="58px" alt=""></a>
                                <p>
                                    <a >SFC上影影城（丁香路店）</a>
                                    <span>中国（上海）自由贸易试验区丁香路858号三楼</span>
                                    <font>021-68585768</font>
                                </p>
                            </div>
                            <div class="yard_right left" onclick="chang_cinema(1,'max')"></div>
                        </div>
                        <div class="yard_list clearfix" id="cinema_div_1" style="display: none">
                            <div class="yard_left left" onclick="chang_cinema(0,'min')"></div>
                            <div class="yard_middle left">
                                <a  class="yard_picture"><img src="" width="76px"
                                                                                           height="58px" alt=""></a>
                                <p>
                                    <a >SFC上影影城（金桥太茂店）</a>
                                    <span>上海市浦东新区金高路1882号金桥太茂广场四楼L401</span>
                                    <font>68766608</font>
                                </p>
                            </div>
                            <div class="yard_right left" onclick="chang_cinema(2,'max')"></div>
                        </div>
                        <div class="yard_list clearfix" id="cinema_div_2" style="display: none">
                            <div class="yard_left left" onclick="chang_cinema(1,'min')"></div>
                            <div class="yard_middle left">
                                <a href= class="yard_picture"><img
                                        src="http://file.komovie.cn/piaocang/gallery/14320030803537.jpg" width="76px"
                                        height="58px" alt=""></a>
                                <p>
                                    <a >SFC上海影城</a>
                                    <span>长宁区新华路160号(番禺路口)</span>
                                    <font>021-62817017</font>
                                </p>
                            </div>
                            <div class="yard_right left" onclick="chang_cinema(3,'max')"></div>
                        </div>
                        <div class="yard_list clearfix" id="cinema_div_3" style="display: none">
                            <div class="yard_left left" onclick="chang_cinema(2,'min')"></div>
                            <div class="yard_middle left">
                                <a  class="yard_picture"><img
                                        src="http://file.komovie.cn/piaocang/gallery/14317687592970.jpg" width="76px"
                                        height="58px" alt=""></a>
                                <p>
                                    <a >SFC上影影城（世博店）</a>
                                    <span>浦东新区世博大道1200号世博文化中心6楼</span>
                                    <font>021-20251186</font>
                                </p>
                            </div>
                            <div class="yard_right left" onclick="chang_cinema(4,'max')"></div>
                        </div>
                        <div class="yard_list clearfix" id="cinema_div_4" style="display: none">
                            <div class="yard_left left" onclick="chang_cinema(3,'min')"></div>
                            <div class="yard_middle left">
                                <a  class="yard_picture"><img
                                        src="http://file.komovie.cn/piaocang/gallery/14320039065760.jpg" width="76px"
                                        height="58px" alt=""></a>
                                <p>
                                    <a >SFC上影影城（喜玛拉雅店）</a>
                                    <span>浦东新区芳甸路1188弄喜玛拉雅中心7-8楼</span>
                                    <font>021-60457099</font>
                                </p>
                            </div>
                            <div class="yard_right left" onclick="chang_cinema(5,'max')"></div>
                        </div>
                        <div class="yard_list clearfix" id="cinema_div_5" style="display: none">
                            <div class="yard_left left" onclick="chang_cinema(4,'min')"></div>
                            <div class="yard_middle left">
                                <a  class="yard_picture"><img
                                        src="http://file.komovie.cn/piaocang/gallery/14320045247297.jpg" width="76px"
                                        height="58px" alt=""></a>
                                <p>
                                    <a >SFC上影影城（七宝店）</a>
                                    <span>闵行区漕宝路3489号汇宝广场8楼</span>
                                    <font>021-64119776</font>
                                </p>
                            </div>
                            <div class="yard_right_free left"></div>
                        </div>
                    </div>
                    <div class="service_link fr">
                        <p class="service_title">友情链接</p>
                        <div class="link_list clearfix">
                            <a href="http://www.sh-sfc.com" target="_blank"><img src="${staticPath}/images/link_1.jpg" alt=""></a>
                            <a href="http://www.sfs-cn.com" target="_blank"><img src="${staticPath}/images/link_2.jpg" alt=""></a>
                            <a href="" target="_blank"><img src="${staticPath}/images/link_3.jpg" alt=""></a>
                            <a href="http://weibo.com/u/5592001638?topnav=1&wvr=6&topsug=1" target="_blank"><img
                                    src="${staticPath}/images/link_4.jpg" alt=""></a>
                        </div>
                    </div>
                </div>
            </div><!--banner-->
            <div class="site_map clearfix">
                <a href="#" class="movie_logo"><img src="${staticPath}/images/logo.png" alt=""></a>
                <a href="http://weibo.com/u/5592001638?topnav=1&wvr=6&topsug=1" target="_blank"><img
                        src="${staticPath}/images/movie_concern.png" alt="" class="movie_concern"></a>
                <a class="movie_link" href="site/help/48?tag=1" target="_blank">常见问题(FAQ) </a>
                <span>|</span>
                <a class="movie_link" href="site/help/47?tag=1" target="_blank">订单状态说明</a>
                <span>|</span>
                <a class="movie_link" href="site/help/45?tag=1" target="_blank">影城取票</a>
                <span>|</span>
                <a class="movie_link" href="site/help/44?tag=1" target="_blank">购票流程</a>
                <span>|</span>
                <a class="movie_link" href="site/help/11?tag=1" target="_blank">快速购票 </a>
                <span>|</span>
                <a class="movie_link" href="site/help/10?tag=1" target="_blank">注册/登录 </a>

                &nbsp;&nbsp;客服电话 400-607-5588
            </div>
        </div>
    </div>
    <div class="bg_black" style=" height: 46px;">
        <div class="wrap">
            <div class="copyright white">
                <div class="left service_our">
                    <a href="site/help/21" target="_blank">隐私保护</a>
                    <span>|</span> <a href="site/help/19" target="_blank">联系我们</a>
                    <span>|</span> <a href="site/help/18" target="_blank">关于我们</a>
                    <span>|</span> <a href="http://wpa.qq.com/msgrd?v=3&uin=2087521174&site=qq&menu=yes"
                                      target="_blank">团体购票</a>
                </div>

                <div class="right"><a href="http://www.miitbeian.gov.cn" target="_blank" style="color: #b4b4b4;">沪ICP备15055478号</a>
                    Copyright © 2015 sfccinema.com 版权所有
                </div>
                <div style="width:300px;margin:0 auto; padding:13px 0;">
                    <a target="_blank"
                       href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31010402000757"
                       style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">
                        <img src="${staticPath}/images/beian.png" style="float:left;">
                        <p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">沪公网安备
                            31010402000757号</p>
                    </a>&nbsp;&nbsp;
                    <a href="http://wljg.ynaic.gov.cn:80/ynwjww/indexquery/indexqueryAction!dizview.dhtml?chr_id=2d5d15ffe74fafdaa6120705823f40b5&amp;bus_ent_id=530100D000539002&amp;bus_ent_chr_id=db76aeb2d70642ae8153607fc6cfab0c"
                       target="_blank"
                       style="background-color: #FFF;display:inline-block;text-decoration:none;height:30px;line-height:30px;">
                        <img src="http://wljg.ynaic.gov.cn:80/ynwjww/images/govIcon.gif" width="25" height="30"
                             title="云南网监电子标识" alt="云南网监电子标识" border="0"/>
                    </a>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<img src="${staticPath}/images/loading.gif" class="ko_loading">




