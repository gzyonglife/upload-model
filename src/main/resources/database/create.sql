drop database if exists `upload_model`;
create database `upload_model`;
use `upload_model`;
#  用户表
drop table if exists `user_table`;
create table `user_table`
(
    `user_id`        int unsigned not null auto_increment comment '用户id，主键',
    `user_name`      varchar(25)  not null comment '用户名',
    `login_name`     varchar(25)  not null comment '用户登录名',
    `login_password` varchar(255) not null comment '用户登录密码',
    `creator_id`     int unsigned not null default '1' comment '创建者id',
    `create_time`    timestamp    not null default current_timestamp comment '创建时间戳',
    `user_zone`      varchar(25)  not null comment '用户存储空间地址',
    `role_id`        int unsigned not null comment '用户权限id',
    primary key (user_id),
    index (create_time)
    ) engine = innodb
  default char set utf8mb4 comment '用户表';
# 角色权限表
drop table if exists `role_table`;
create table `role_table`
(
    `role_id`     int unsigned not null auto_increment comment '角色id',
    `role_name`   varchar(25)  not null comment '角色名称',
    `role_access` varchar(25)  not null comment '角色权限',
    primary key (role_id)
) engine = innodb
  default char set utf8mb4 comment '角色权限表';
# 文件表
# 文件表未来将弃用
drop table if exists `file_table`;
create table `file_table`
(
    `file_id`      bigint unsigned not null comment '文件id',
    `user_id`      int unsigned    not null comment '文件所属用户id',
    `project_id`   bigint unsigned not null comment '文件所在属项目id',
    `file_name`    varchar(25)     not null comment '文件名',
    `file_address` varchar(25)     not null comment '文件在项目空间的地址',
    `file_type`    boolean         not null default false comment '文件类型,true：文件夹，false：文件',
    `father_id`    bigint unsigned not null comment '上级目录id，根目录为0',
    `upload_time`  timestamp       not null default current_timestamp comment '文件上传时间',
    unique index (upload_time),
    index (user_id),
    index (project_id),
    primary key (file_id)
) engine = innodb
  default char set utf8mb4 comment '文件表';
# 项目表
drop table if exists `project_table`;
create table `project_table`
(
    `project_id`         int unsigned not null auto_increment comment '项目id',
    `project_name`       varchar(25)  not null comment '项目名称',
    `user_id`            int          not null comment '项目所属用户id',
    `project_details_id` int unsigned comment '项目详情id',
    `project_parent`     int comment '父级项目id',
    `create_time`        timestamp    not null comment '创建时间戳',
    `project_note`       varchar(255) comment '项目备注',
    `item_number`        varchar(25)  not null comment '项目年度编号',
    primary key (project_id),
    index (user_id),
    unique index (create_time)
) engine = innodb
  default char set utf8mb4 comment '项目表';
# 单位表
drop table if exists `firm_table`;
create table `firm_table`
(
    `firm_id`   int unsigned not null auto_increment comment '单位id',
    `firm_name` varchar(25)  not null comment '单位名称',
    primary key (firm_id)
) engine = innodb
  char set utf8mb4 comment '单位表';
# 项目分类表
drop table if exists `project_category_table`;
create table `project_category_table`
(
    `project_category_id`        int unsigned not null auto_increment comment '项目分类id',
    `project_category_name`      varchar(25)  not null comment '项目名称',
    `project_parent_category_id` int unsigned comment '父级分类id',
    primary key (project_category_id)
) engine = innodb
  char set utf8mb4 comment '项目分类';
# 项目计划表
drop table if exists `project_plan_table`;
create table `project_plan_table`
(
    `project_plan_id`                int unsigned not null auto_increment comment '项目计划id',
    `project_plan_year`              date comment '计划年份',
    `project_plan_expect_start_time` date comment '预期开工日期',
    `project_plan_expect_end_time`   date comment '预期竣工日期',
    `project_plan_invest_total`      double comment '计划总投资额',
    `project_plan_invest_finish`     double comment '计划完成额',
    `project_plan_january`           varchar(255) comment '一月阶段性目标',
    `project_plan_february`          varchar(255) comment '二月阶段性目标',
    `project_plan_march`             varchar(255) comment '三月阶段性目标',
    `project_plan_april`             varchar(255) comment '四月阶段性目标',
    `project_plan_may`               varchar(255) comment '五月阶段性目标',
    `project_plan_june`              varchar(255) comment '六月阶段性目标',
    `project_plan_july`              varchar(255) comment '七月阶段性目标',
    `project_plan_august`            varchar(255) comment '八月阶段性目标',
    `project_plan_september`         varchar(255) comment '九月阶段性目标',
    `project_plan_october`           varchar(255) comment '十月阶段性目标',
    `project_plan_november`          varchar(255) comment '十一月阶段性目标',
    `project_plan_december`          varchar(255) comment '十二月阶段性目标',
    primary key (project_plan_id)
) engine = innodb
  char set utf8mb4 comment '项目计划表';
# 项目计划实施表
# 未来与项目计划表合二为一
drop table if exists `project_plan_practical_table`;
create table `project_plan_practical_table`
(
    `project_plan_practical_id`                int unsigned not null auto_increment comment '项目计划实施id',
    `project_plan_year`                        date comment '计划年份',
    `project_plan_practical_expect_start_time` date comment '实际开工日期',
    `project_plan_practical_expect_end_time`   date comment '实际竣工日期',
    `project_plan_practical_invest_finish`     double comment '实际完成额',
    `project_plan_practical_january`           varchar(255) comment '一月阶段实施进度',
    `project_plan_practical_february`          varchar(255) comment '二月阶段实施进度',
    `project_plan_practical_march`             varchar(255) comment '三月阶段实施进度',
    `project_plan_practical_april`             varchar(255) comment '四月阶段实施进度',
    `project_plan_practical_may`               varchar(255) comment '五月阶段实施进度',
    `project_plan_practical_june`              varchar(255) comment '六月阶段实施进度',
    `project_plan_practical_july`              varchar(255) comment '七月阶段实施进度',
    `project_plan_practical_august`            varchar(255) comment '八月阶段实施进度',
    `project_plan_practical_september`         varchar(255) comment '九月阶段实施进度',
    `project_plan_practical_october`           varchar(255) comment '十月阶段实施进度',
    `project_plan_practical_november`          varchar(255) comment '十一月阶段实施进度',
    `project_plan_practical_december`          varchar(255) comment '十二月阶段实施进度',
    `modify_time`                              timestamp comment '修改最后时间',
    primary key (project_plan_practical_id),
    index (modify_time)
    ) engine = innodb
  char set utf8mb4 comment '项目计划实施表';
# 项目详情表
drop table if exists `project_details_table`;
create table `project_details_table`
(
    `project_details_id`         int unsigned not null auto_increment comment '项目详情id',
    `project_name`               varchar(25)  not null comment '项目名称',
    `project_zone`               varchar(25)  not null comment '项目在用户存储空间的地址',
    `create_time`                timestamp    not null comment '创建时间戳',
    `project_note`               varchar(255) comment '项目备注',
    `project_category_id`        int unsigned not null comment '项目所在分类id',
    `item_number`                varchar(25)  not null comment '项目年度编号',
    `construction_firm_id`       int unsigned comment '建设单位id',
    `agent_construction_firm_id` int unsigned comment '代建单位id',
    `cooperate_firm_id`          int unsigned comment '配合单位id',
    `project_parent`             int comment '父级项目id',
    `project_plan_id`            int unsigned comment '项目计划id',
    `project_plan_practical_id`  int unsigned comment '项目计划实施状况id',
    `user_id`                    int          not null comment '项目所属用户id',
    `continue_project_id`        int unsigned comment '续建项目id',
    primary key (project_details_id),
    index (user_id),
    unique index (create_time)
) engine = innodb
  default char set utf8mb4 comment '项目详情表';
# 登录日志表
drop table if exists `login_logs_table`;
create table `login_logs_table`
(
    `logs_id`    bigint unsigned not null comment '登录日志id',
    `user_id`    int unsigned    not null comment '登录用户id',
    `login_time` timestamp       not null default current_timestamp comment '登录时间戳',
    `login_ip`   varchar(25)     not null comment '登录地址ip',
    primary key (logs_id)
) engine = innodb
  default char set utf8mb4 comment '登录日志表';