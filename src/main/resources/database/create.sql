drop database if exists `upload_model`;
create database `upload_model`;
use `upload_model`;
#  用户表
drop table if exists `user_table`;
create table `user_table`
(
    `user_id`        int          not null auto_increment comment '用户id，主键',
    `user_name`      varchar(25)  not null comment '用户名',
    `login_name`     varchar(25)  not null comment '用户登录名',
    `login_password` varchar(255) not null comment '用户登录密码',
    `creator_id`     int          not null default '1' comment '创建者id',
    `create_time`    timestamp    not null default current_timestamp comment '创建时间戳',
    `role_id`        int          not null comment '用户权限id',
    primary key (user_id),
    index (create_time)
) engine = innodb
  default char set utf8mb4 comment '用户表';
# 角色权限表
drop table if exists `role_table`;
create table `role_table`
(
    `role_id`     int         not null auto_increment comment '角色id',
    `role_name`   varchar(25) not null comment '角色名称',
    `role_access` varchar(25) not null comment '角色权限',
    primary key (role_id)
) engine = innodb
  default char set utf8mb4 comment '角色权限表';
# 青浦区行政区域表
drop table if exists `administrative_table`;
create table `administrative_table`
(
    `administrative_table_id` int         not null auto_increment comment '青浦区行政区域表id',
    `administrative_name`     varchar(50) not null comment '行政区域名称',
    primary key (administrative_table_id)
) engine = innodb
  char set utf8mb4 comment '青浦区行政区域表';
# 青浦区区域计划表
drop table if exists `administrative_plan`;
create table `administrative_plan`
(
    `administrative_plan_id`   int         not null auto_increment comment '青浦区行政区域计划表id',
    `administrative_plan_name` varchar(50) not null comment '计划名称',
    primary key (administrative_plan_id)
) engine = innodb
  char set utf8mb4 comment '青浦区行政区域计划表';
# 青浦区区域计划与行政区域绑定表
drop table if exists `administrative_table_binding_administrative_table`;
create table `administrative_table_binding_administrative_table`
(
    `administrative_table_binding_administrative_table_id` int not null auto_increment comment '青浦区区域计划与行政区域绑定表id',
    `administrative_table_id`                              int not null comment '青浦区行政区域表id',
    `administrative_plan_id`                               int not null comment '青浦区行政区域计划表id',
    primary key (administrative_table_binding_administrative_table_id)
) engine = innodb
  char set utf8mb4 comment '青浦区区域计划与行政区域绑定表';
# 项目文件空间地址表
drop table if exists `project_zone`;
create table `project_zone`
(
    `project_zone_id`   int          not null auto_increment comment '项目文件空间地址id',
    `project_zone_path` varchar(255) not null comment '项目文件空间地址',
    `project_zone_port` int          not null default 8071 comment '项目访问端口',
    primary key (project_zone_id)
) engine = innodb
  char set utf8mb4 comment '项目文件空间地址表';
# 项目表
drop table if exists `project_table`;
create table `project_table`
(
    `project_id`          int         not null auto_increment comment '项目id',
    `project_name`        varchar(25) not null comment '项目名称',
    `project_zone_id`     int         not null comment '项目文件空间地址id',
    `project_details_id`  int comment '项目详情id',
    `project_parent`      int comment '父级项目id',
    `project_category_id` int         not null comment '项目类型id',
    `create_time`         timestamp   not null default current_timestamp comment '创建时间戳',
    `project_note`        varchar(255) comment '项目备注',
    `item_number`         varchar(25) not null comment '项目年度编号',
    `user_id`             int         not null comment '项目所属用户id',
    `img_url`             varchar(255)  comment '项目缩略图文件存放路径',
    `Longitude_and_latitude`   varchar(255)  comment '项目经纬度地址',
    `project_view`        varchar(255) comment '项目视角',
    `is_focus`            boolean     not null default false comment '是否为重点关注项目',
    primary key (project_id),
    index (user_id),
    index (project_category_id),
    index (project_zone_id),
    unique index (create_time)
) engine = innodb
  default char set utf8mb4 comment '项目表';
# 项目模型表
drop table if exists `project_model_table`;
create table `project_model_table`
(
    `project_model_id`      bigint       not null auto_increment comment '项目模型表id',
    `project_model_path`    varchar(255) not null comment '模型相对项目的路径',
    `project_model_name`    varchar(255) not null comment '模型名称',
    `project_id`            int          not null comment '项目id',
    `create_user_id`        int          not null comment '所有者id',
    `create_time`           timestamp    not null default current_timestamp comment '上传时间',
    `project_model_time`    timestamp    not null default current_timestamp comment '模型对应的项目时间',
    `project_model_type_id` int          not null comment '模型类型',
    `project_model_view`    varchar(255) comment '模型视角',
    primary key (project_model_id),
    index (project_model_type_id),
    index (project_id),
    index (create_user_id)
) engine = innodb
  char set utf8mb4 comment '项目模型表';
# 模型类型表
drop table if exists `project_model_type_table`;
create table `project_model_type_table`
(
    `project_model_type_id` int         not null auto_increment comment '模型类型表id',
    `project_model_type`    varchar(25) not null comment '模型类型',
    `is_folder`             boolean     not null default false comment '是否为文件夹',
    primary key (project_model_type_id)
) engine = innodb
  char set utf8mb4 comment '模型类型表';
# 单位表
drop table if exists `firm_table`;
create table `firm_table`
(
    `firm_id`   int         not null auto_increment comment '单位id',
    `firm_name` varchar(25) not null comment '单位名称',
    `firm_phone` varchar(25) comment '联系方式',
    primary key (firm_id)
) engine = innodb
  char set utf8mb4 comment '单位表';
# 项目类型表
drop table if exists `project_category_table`;
create table `project_category_table`
(
    `project_category_id`        int         not null auto_increment comment '项目分类id',
    `project_category_name`      varchar(25) not null comment '项目名称',
    `project_parent_category_id` int default 0 comment '父级分类id',
    primary key (project_category_id),
    index (project_parent_category_id)
) engine = innodb
  char set utf8mb4 comment '项目分类';
# 项目种类表
drop table if exists `project_class_table`;
create table `project_class_table`
(
    `project_class_table_id`   int         not null auto_increment comment '项目种类表id',
    `project_class_table_name` varchar(25) not null comment '项目种类名称',
    primary key (project_class_table_id)
) engine = innodb
  char set utf8mb4 comment '项目总类表';
# 项目计划表
drop table if exists `project_plan_table`;
create table `project_plan_table`
(
    `project_plan_id`                int not null auto_increment comment '项目计划id',
    `project_id`                     int not null comment '项目id',
    `project_plan_year`              date comment '计划年份',
    `project_plan_expect_start_time` date comment '开工日期',
    `project_plan_expect_end_time`   date comment '竣工日期',
    `project_plan_invest_total`      double comment '总投资额',
    `project_plan_invest_finish`     double comment '完成额',
    `project_plan_january`           text comment '一月目标',
    `project_plan_february`          text comment '二月目标',
    `project_plan_march`             text comment '三月目标',
    `project_plan_april`             text comment '四月目标',
    `project_plan_may`               text comment '五月目标',
    `project_plan_june`              text comment '六月目标',
    `project_plan_july`              text comment '七月目标',
    `project_plan_august`            text comment '八月目标',
    `project_plan_september`         text comment '九月目标',
    `project_plan_october`           text comment '十月目标',
    `project_plan_november`          text comment '十一月目标',
    `project_plan_december`          text comment '十二月阶目标',
    `plan_type`                      boolean comment '是否为计划，或者为实施',
    primary key (project_plan_id),
    index (project_id)
) engine = innodb
  char set utf8mb4 comment '项目计划实施表';
# 项目详情表
drop table if exists `project_details_table`;
create table `project_details_table`
(
    `project_details_id`         int         not null auto_increment comment '项目详情id',
    `project_id`                 int         not null comment '项目id',
    `project_name`               varchar(25) not null comment '项目名称',
    `create_time`                timestamp   not null comment '创建时间戳',
    `project_note`               varchar(255) comment '项目备注',
    `project_category_id`        int         not null comment '项目所在分类id',
    `item_number`                varchar(25) not null comment '项目年度编号',
    `construction_firm_id`       int comment '建设单位id',
    `agent_construction_firm_id` int comment '代建单位id',
    `cooperate_firm_id`          int comment '配合单位id',
    `project_parent`             int comment '父级项目id',
    `project_plan_id`            int comment '项目计划id',
    `project_plan_practical_id`  int comment '项目计划实施状况id',
    `user_id`                    int         not null comment '项目所属用户id',
    `continue_project_id`        int comment '续建项目id',
    primary key (project_details_id),
    index (user_id),
    index (project_id),
    unique index (create_time)
) engine = innodb
  default char set utf8mb4 comment '项目详情表';
# 登录日志表
drop table if exists `login_logs_table`;
create table `login_logs_table`
(
    `logs_id`    bigint      not null auto_increment comment '登录日志id',
    `user_id`    int         not null comment '登录用户id',
    `login_time` timestamp   not null default current_timestamp comment '登录时间戳',
    `login_ip`   varchar(25) not null comment '登录地址ip',
    primary key (logs_id)
) engine = innodb
  default char set utf8mb4 comment '登录日志表';
# 图标表
drop table if exists `icon_table`;
create table `icon_table`
(
    `icon_id`   int auto_increment not null comment '图标id',
    `icon_url`  varchar(255)       not null comment '图标路径',
    `icon_name` varchar(255)       not null comment '图标名称',
    primary key (icon_id)
) engine = innodb
  char set utf8mb4 comment '图标表';
# 标绘表
drop table if exists `plot_point_table`;
create table `plot_point_table`
(
    `plot_point_id`        int auto_increment not null comment '标绘点id',
    `plot_point_name`      varchar(255) comment '标绘点名称',
    `plot_point_longitude` varchar(25)        not null comment '经度',
    `plot_point_latitude`  varchar(25)        not null comment '纬度',
    `icon_id`              int                not null comment '标绘点图标id',
    primary key (plot_point_id)
) engine = innodb
  char set utf8mb4 comment '标绘表';
# 项目总体情况表
drop table if exists `overview_project`;
create table `overview_project`
(
    `overview_project_id`         int not null auto_increment comment '项目总体情况表id',
    `overview_project_all_num`    int not null comment '项目总数量',
    `overview_project_all_money`  int not null comment '项目总资金',
    `overview_project_starts_num` int not null comment '项目已开工总数量',
    `overview_project_use_money`  int not null comment '项目已使用资金',
    primary key (overview_project_id)
) engine = innodb
  char set utf8mb4 comment '项目总览情况表';
# 项目类型统计表
drop table if exists `statistics_project_type`;
create table `statistics_project_type`
(
    `statistics_project_type_id`   int not null auto_increment comment '项目类型统计表id',
    `project_category_id`          int not null comment '项目分类id',
    `statistics_project_type_null` int not null comment '项目类型统计数量',
    primary key (statistics_project_type_id),
    index (project_category_id)
) engine = innodb
  char set utf8mb4 comment '项目类型统计表';
# 区域计划项目数量
drop table if exists `overview_administrative_plan_project`;
create table `overview_administrative_plan_project`
(
    `overview_administrative_plan_project_id`              int not null auto_increment comment '区域计划项目数量表id',
    `administrative_table_binding_administrative_table_id` int not null comment '青浦区区域计划与行政区域绑定表id',
    `overview_administrative_plan_project_num`             int not null comment '计划在该区域的项目数',
    primary key (overview_administrative_plan_project_id)
) engine = innodb
  char set utf8mb4 comment '区域计划项目数量';
# 年度项目总览表
drop table if exists `overview_year_plan`;
create table `overview_year_plan`
(
    `overview_year_plan_id`       int not null auto_increment comment '年度项目总览表id',
    `project_class_table_id`      int not null comment '年度项目种类id',
    `overview_year_plan_starts`   int not null comment '年内计划开工数量',
    `overview_year_plan_complete` int not null comment '年内计划竣工数量',
    `overview_year_plan`   int not null comment '年内计划项目',
    primary key (overview_year_plan_id)
) engine = innodb
  char set utf8mb4 comment '年度项目总览表';