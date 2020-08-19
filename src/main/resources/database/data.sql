use upload_model;
# 填充用户权限表
insert into role_table(role_name, role_access) value
    ('后台管理员', 'SUPERADMIN'),
    ('项目管理员', 'ADMIN'),
    ('普通用户', 'COMMON'),
    ('游客', 'GUEST');
# 填充用户表
insert into user_table
(user_name, login_name, login_password, creator_id, create_time, role_id)
VALUES ('admin', 'admin', '$2a$10$Q96zmpvV4Udk81xT9ZfdbeF0zRezkHWiHt/jBIbG5391PObg2j35i', 0,
        current_timestamp(), 1),
       ('guest', 'guest', '$2a$10$PdeHEh2nNvOI/TUiWoNBo.tdM.JUG3hL.jco2DEczFzJ1HLFqB5n2', 0,
        current_timestamp(), 4);
# 填充项目分类表
insert into project_category_table(project_category_name, project_parent_category_id)
VALUES ('市级项目', 0),
       ('社会民生', 0),
       ('环境提升', 0),
       ('工业及研发', 0),
       ('商办及住宅', 0),
       ('基础建设', 0);
# 填充项目存储路径
insert into project_zone(project_zone_path)
values ('F:/data');
# 存储项目模型类型
insert into project_model_type_table(project_model_type, is_folder)
values ('tif', false),
       ('osgb', true),
       ('kml', false),
       ('lrp', false),
       ('3dtiles', true);
# 填充青浦行政区域表
insert into administrative_table(administrative_name)
VALUES ('徐泾镇'),
       ('华新镇'),
       ('赵巷镇'),
       ('重固镇'),
       ('白鹤镇'),
       ('金泽镇'),
       ('莲塘镇'),
       ('朱家角镇');
# 填充区域计划表
insert into administrative_plan(administrative_plan_name)
values ('青东五镇计划'),
       ('长三角计划');
# 填充区域计划与行政区域绑定表
insert into administrative_table_binding_administrative_table(administrative_table_id, administrative_plan_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2);
# 填充项目总体情况
insert into overview_project(overview_project_all_num, overview_project_all_money, overview_project_starts_num,
                             overview_project_use_money)
VALUES (128, 17125859.01, 35, 10125859);
# 填充项目类型统计
insert into statistics_project_type(project_category_id, statistics_project_type_null)
VALUES (1, 335),
       (2, 310),
       (3, 234),
       (4, 234),
       (5, 234),
       (6, 135);
# 填充区域计划项目数量
insert into overview_administrative_plan_project (administrative_table_binding_administrative_table_id,
                                                  overview_administrative_plan_project_num)
VALUES (1, 30),
       (2, 22),
       (3, 18),
       (4, 15),
       (5, 11),
       (6, 30),
       (7, 18),
       (8, 8);
# 填充项目种类表
insert into project_class_table(project_class_table_name)values
('结转项目'),
('新列项目'),
('储备项目');
# 填充年度项目总览
insert into overview_year_plan(project_class_table_id,overview_year_plan_starts,overview_year_plan_complete,overview_year_plan) VALUES
(1,35,39,80),
(2,35,39,48),
(3,35,39,24);
