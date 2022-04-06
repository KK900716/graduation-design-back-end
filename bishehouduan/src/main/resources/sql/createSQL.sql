# 创建数据库
create database user2;
# 创建用户
create user 'user2'@'localhost' identified by '123456';
# 授予用户权限
grant all on user2.* to 'user2'@'localhost';
# 使用该数据库
use user2;
# 建立表
# 1. 用户表
# 用户编号
# 用户邮箱（登录名）
# 用户密码
# 用户姓名
# 用户权限
# 用户余额
# 可建仓库数量
# 仓库总量
create table userInfo(
                         id int auto_increment primary key,
                         account varchar(50) unique not null ,
                         password varchar(20) not null ,
                         name varchar(20) ,
                         permission enum('1','2','3') not null ,
                         balance float default 0 not null ,
                         available int default 10 not null,
                         capacity int default 10 not null
);
drop table userInfo;
select * from userInfo;
delete from userInfo where true;
insert into userInfo values (null,'443808626@qq.com','123456','梁嘉成','1',100,10,10);
update userinfo set balance=100 where account='443808626@qq.com';
# 2.用户仓库表
# 仓库编号
# 仓库名称
# 仓库容量
# 已用容量
# 剩余容量
# 用户编号
create table userWarehouse(
                              id int auto_increment primary key,
                              name varchar(50) not null ,
                              count int not null default 10,
                              available int not null default 0,
                              remaining int not null default 10,
                              userInfo_id int not null ,
                              foreign key (userInfo_id) references userInfo(id)
);
select * from userWarehouse;
delete from userWarehouse where true;
drop table userWarehouse;
# 3.仓库
# 图片编号
# 仓库编号
# 图片原始地址
# 识别图片地址
create table warehouse(
                          id varchar(128) primary key,
                          userWarehouse_id int not null ,
                          state enum('处理中','处理成功','处理失败') default '处理中' not null ,
                          score int default 0,
                          foreign key (userWarehouse_id) references userWarehouse(id)
);
select * from warehouse;
drop table warehouse;

create table testuser(
                         name varchar(10) primary key ,
                         value int,
                         id varchar(15)
);
select * from testuser;
insert into testuser values ('00000001',1,'a');
update testuser set value=2 where name=00000001;
update testuser set value=1 where name=00000001;
drop table testuser;