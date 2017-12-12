DROP TABLE  if EXISTS  order_seat_tb;
DROP TABLE  if EXISTS  user_role_tb;
DROP TABLE  if EXISTS  role_permission_tb;
DROP TABLE  if EXISTS  role_tb;
DROP TABLE  if EXISTS  permission_tb;
DROP TABLE  if EXISTS  order_tb;
DROP TABLE  if EXISTS  select_tb;
DROP TABLE  if EXISTS  plan_tb;
DROP TABLE  if EXISTS  movie_tb;
DROP TABLE  if EXISTS  seat_tb;
DROP TABLE  if EXISTS  hall_tb;
DROP TABLE  if EXISTS  vip_tb;
DROP TABLE  if EXISTS  coupon_tb;
DROP TABLE  if EXISTS  cinema_tb;
DROP TABLE  if EXISTS  city_tb;
DROP TABLE  if EXISTS  user_tb;

#创建用户表
CREATE TABLE user_tb (
  user_id INT PRIMARY KEY auto_increment,
  user_account VARCHAR (100),
  user_password VARCHAR (32) NOT NULL,
  user_salt VARCHAR(32),
  user_email VARCHAR(20),
  user_tel VARCHAR(11),
  user_img VARCHAR (200),
  user_status INT,
  user_register_time TIMESTAMP,
  cinema_id INT,
  FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id)
);
#ALTER TABLE user_tb ADD UNIQUE INDEX (user_tel);
#ALTER TABLE user_tb ADD cinema_id INT;
#ALTER TABLE user_tb ADD FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id);

#ALTER TABLE  user_tb MODIFY user_register_time TIMESTAMP;
#创建角色表
CREATE TABLE role_tb (
  role_id INT PRIMARY KEY auto_increment,
  role_name VARCHAR (100) NOT NULL,
  role_describe VARCHAR (200),
  role_status INT
);

#创建权限表
CREATE TABLE permission_tb (
  permission_id INT PRIMARY KEY auto_increment,
  permission_name VARCHAR (100) NOT NULL,
  permission_describe VARCHAR (200),
  permission_status INT
);

#用户角色表
CREATE TABLE user_role_tb(
  ur_id INT PRIMARY KEY auto_increment,
  ur_user_id INT,
  ur_role_id INT,
  FOREIGN KEY(ur_role_id) REFERENCES  role_tb(role_id),
  FOREIGN KEY(ur_user_id) REFERENCES  user_tb(user_id)
);

#创建角色权限表
CREATE TABLE role_permission_tb (
  rp_id  INT PRIMARY KEY auto_increment,
  rp_role_id INT,
  rp_permission_id INT,
  FOREIGN KEY (rp_role_id) REFERENCES  role_tb (role_id),
  FOREIGN KEY (rp_permission_id) REFERENCES  permission_tb (permission_id)
);
#ALTER TABLE role_permission_tb CHANGE rp_pms_id rp_permission_id INT;
#优惠券
CREATE TABLE coupon_tb(
  #优惠券id
  coupon_id INT PRIMARY KEY AUTO_INCREMENT,
  #优惠券号
  coupon_no VARCHAR(16),
  #优惠今个
  coupon_money INT,
  #满足条件后可以使用
  coupon_min_money INT,
  #过期时间
  coupon_time DATETIME,
  #绑定用户id 不绑定任何任何用户都可以使用
  user_id INT,
  #绑定电影院 不绑定任何电影院，全网通用
  cinema_id INT,
  #优惠券状态 -1为过期 0为可使用 1为已经使用
  coupon_status INT,
  UNIQUE  INDEX (coupon_no)
);

#城市
CREATE  TABLE city_tb(
  #城市id
  city_id INT PRIMARY KEY AUTO_INCREMENT,
  #城市名称
  city_name VARCHAR(100),
  #城市邮编
  city_zip_code INT
);

#电影
CREATE TABLE movie_tb(
  #id
  movie_id INT PRIMARY KEY AUTO_INCREMENT,
  #电影名称
  movie_name VARCHAR(100),
  #时长
  movie_play_time INT,
  #上映时间
  movie_publish_time DATE,
  #导演
  movie_director VARCHAR(100),
  #地区
  movie_country VARCHAR(100),
  #语言
  movie_language VARCHAR(100),
  #演员
  movie_actor TEXT,
  #类型
  movie_type VARCHAR(100),
  #海报图片
  movie_poster VARCHAR(200),
  #最低价格
  min_price INT,
  #会员最低价格
  min_vip_price INT
);
#ALTER table movie_tb MODIFY movie_publish_time DATE;


#影院信息
CREATE TABLE cinema_tb(
  #电影院信息
  cinema_id INT PRIMARY KEY AUTO_INCREMENT,
  #电影院名称
  cinema_name VARCHAR(100),
  #电影院描述
  cinema_describe TEXT,
  #电影院地址
  cinema_address VARCHAR(200),
  #电影院电话
  cinema_tel VARCHAR(100),
  #电影院营业时间
  cinema_business_hours VARCHAR(100),
  #电影院级经度
  cinema_longitude VARCHAR(20),
  #电影院维度
  cinema_latitude VARCHAR(20),
  cinema_img VARCHAR(200),
  #城市id
  city_id INT,
  FOREIGN KEY(city_id) REFERENCES city_tb(city_id)
);

#放映厅
CREATE TABLE  hall_tb(
  #放映厅id
  hall_id INT PRIMARY KEY AUTO_INCREMENT,
  #放映厅名称
  hall_name VARCHAR(100),
  #屏幕类型
  hall_screen_type VARCHAR(50),
  #放映厅 描述
  hall_describe VARCHAR(100),
  #放映厅最大行
  hall_max_row INT,
  #放映同最大列
  hall_max_col INT,
  #电影院id
  cinema_id INT,
  FOREIGN KEY(cinema_id) REFERENCES cinema_tb(cinema_id)
);

#放映厅座位信息
CREATE TABLE seat_tb(
  #id
  seat_id INT PRIMARY KEY AUTO_INCREMENT,
  #行号
  seat_row INT,
  #列号
  seat_col INT,
  #-1不可购买 0可购买 1 已购买
  seat_status INT,
  #厅号
  hall_id INT,
  FOREIGN KEY (hall_id) REFERENCES hall_tb(hall_id),
  #唯一索引
  UNIQUE INDEX (seat_id,hall_id)
);
#ALTER TABLE seat_tb ADD seat_status INT;
#UPDATE seat_tb SET seat_status=0;
#播放计划
CREATE TABLE plan_tb(
  #计划id
  plan_id INT PRIMARY KEY AUTO_INCREMENT,
  #价格
  plan_price INT,
  #开售时间
  plan_start_time DATETIME,
  #开售日期
  plan_data DATE,
  #播放时间
  plan_time TIME,
  #播放语言
  plan_language VARCHAR(50),
  #播放影片类型 2D/3D/IMAX 3D
  plan_screen_type VARCHAR(50),
  #折扣信息百分比(1-100)
  plan_discount INT,
  #售票状态 -1关闭/已经放映 0未开启售票 1开启售票
  plan_status INT,
  #城市id
  city_id INT,
  #影院id
  cinema_id INT,
  #电影id
  movie_id INT,
  #影厅id
  hall_id INT,
  FOREIGN KEY (movie_id) REFERENCES movie_tb(movie_id),
  FOREIGN KEY (hall_id) REFERENCES hall_tb(hall_id),
  FOREIGN KEY (city_id) REFERENCES city_tb(city_id),
  FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id)
);
ALTER TABLE plan_tb ADD city_id INT;
ALTER TABLE plan_tb ADD   FOREIGN KEY (city_id) REFERENCES city_tb(city_id);
ALTER TABLE plan_tb ADD cinema_id INT;
ALTER TABLE plan_tb ADD   FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id);

#选座信息
CREATE TABLE select_tb(
  select_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  seat_id INT,
  plan_id INT,
  seat_status INT,
   #售票状态 -1关闭/不可选 0未选 1已选
  FOREIGN KEY (seat_id) REFERENCES seat_tb(seat_id),
  FOREIGN KEY (plan_id) REFERENCES plan_tb(plan_id),
  UNIQUE INDEX (seat_id,plan_id)
);

#订单信息
CREATE TABLE order_tb(
  #订单id
  order_id INT PRIMARY KEY AUTO_INCREMENT,
  #订单创建时间
  order_create_time TIMESTAMP,
  #订单支付时间
  order_pay_time DATETIME,
  #-1取消/过期 0-待支付 1-支付完成
  order_status INT,
  #订单总价
  order_sum_price INT,
  #实际支付金额
  order_pay_price INT,
  #支付方式
  order_pay_method VARCHAR(20),
  #取票电话
  user_tel VARCHAR(11),
  #用户id
  user_id INT,
  #计划id
  plan_id INT,
  #电影id
  movie_id INT,
  #电影院id
  cinema_id INT,
  #影厅id
  hall_id INT,
  FOREIGN KEY (user_id) REFERENCES user_tb(user_id),
  FOREIGN KEY(plan_id) REFERENCES plan_tb(plan_id),
  FOREIGN KEY (movie_id) REFERENCES movie_tb(movie_id),
  FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id),
  FOREIGN KEY (hall_id) REFERENCES hall_tb(hall_id)
);

#ALTER  TABLE order_tb ADD order_sum_price INT;
#ALTER  TABLE order_tb ADD order_pay_price INT;
#ALTER  TABLE order_tb ADD order_pay_method  VARCHAR(20);
#ALTER TABLE  order_tb ADD order_sum_price INT;
#ALTER TABLE order_tb ADD user_tel VARCHAR(11);
#ALTER TABLE order_tb ADD movie_id INT;
#ALTER TABLE order_tb ADD  FOREIGN KEY (movie_id) REFERENCES movie_tb(movie_id);
#ALTER TABLE order_tb ADD cinema_id INT;
#ALTER TABLE order_tb ADD  FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id);
#ALTER TABLE order_tb ADD hall_id INT;
#ALTER TABLE order_tb ADD  FOREIGN KEY (hall_id) REFERENCES hall_tb(hall_id);

CREATE TABLE order_seat_tb(
  os_id INT PRIMARY KEY AUTO_INCREMENT,
  os_price INT,
  seat_id INT,
  order_id INT,
  FOREIGN KEY(order_id) REFERENCES order_tb(order_id),
  FOREIGN KEY(seat_id) REFERENCES seat_tb(seat_id)
);

#会员卡
CREATE TABLE vip_tb(
  vip_id INT PRIMARY KEY AUTO_INCREMENT,
  vip_no VARCHAR(20),
  vip_money INT,
  user_id INT,
  cinema_id INT,
  FOREIGN KEY (user_id) REFERENCES user_tb(user_id),
  FOREIGN KEY (cinema_id) REFERENCES cinema_tb(cinema_id),
  UNIQUE INDEX(vip_no)
);