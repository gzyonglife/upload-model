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


# 项目计划
insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (1, '2020-1-1 00:00:00', '2017-6-1 00:00:00', '2021-12-1 00:00:00', 981542.74, 0, '二、证照审批
办理新通变电站划拨决定书和用地批准书
三、征收腾地
跟进新通站、管线腾地范围内完成企业、居民签约
四、市政配套
完成影响主线高架施工的弱电搬迁工作。
五、工程形象进度：
总体进度：完成总建安量40%，主线高架完成50%，地面道路桥梁完成20%。其中1、一标段主线高架下部结构完成80%；上部结构完成55%；地面桥梁完成5%、道路完成5%。
2、二标段主线高架下部结构完成70%；上部结构完成45%；地面桥梁完成10%、道路完成5%
3、三标段主线高架下部结构完成45%；上部结构完成20%；地面桥梁完成5%、道路完成5%。', '二、证照审批
办理新通变电站划拨决定书和用地批准书
三、征收腾地
新通站、管线腾地范围内完成企业1家、居民1户
四、市政配套
完成影响主线高架施工的弱电搬迁工作。
五、工程形象进度：
总体进度：完成总建安量40%，主线高架完成50%，地面道路桥梁完成20%。其中1、一标段主线高架下部结构完成80%；上部结构完成55%；地面桥梁完成5%、道路完成5%。
2、二标段主线高架下部结构完成70%；上部结构完成45%；地面桥梁完成10%、道路完成5%
3、三标段主线高架下部结构完成45%；上部结构完成20%；地面桥梁完成5%、道路完成5%。', '一、证照审批
3月份前完成新通变电站划拨决定书和用地批准书
三、征收腾地
新通站、管线腾地范围内完成企业5家、居民5户
四、市政配套
完成影响主线高架施工的弱电搬迁工作。
五、工程形象进度：
总体进度：完成总建安量42%，主线高架完成55%，地面道路桥梁完成22%。其中1、一标段主线高架下部结构完成85%；上部结构完成60%；地面桥梁完成10%、道路完成5%。
2、二标段主线高架下部结构完成75%；上部结构完成50%；地面桥梁完成15%、道路完成10%
3、三标段主线高架下部结构完成50%；上部结构完成25%；地面桥梁完成5%、道路完成5%。', '二、征收腾地
新通站、管线腾地范围内累计完成企业8家、居民8户
二、市政配套
完成影响地面桥梁施工的供水临搬。
五、形象进度
总体进度：完成总建安量45%，主线高架完成60%，地面道路桥梁完成25%。其中
1、一标段主线高架下部结构完成90%；上部结构完成70%；地面桥梁完成23%地面道路完成5%
2、二标段主线高架下部结构完成80%；上部结构完成60%；地面桥梁完成25%地面道路完成15%
3、三标段主线高架下部结构完成55%；上部结构完成30%；地面桥梁完成10%地面道路完成10%。', '二、征收腾地
新通站、管线腾地范围内累计完成企业10家、居民10户
三、市政配套
完成影响地面桥梁施工的供水临搬。五、形象进度
总体进度：完成总建安量48%，主线高架完成65%，地面道路桥梁完成28%。其中
1、一标段主线高架下部结构完成92%；上部结构完成72%；地面桥梁完成24%地面道路完成8%
2、二标段主线高架下部结构完成82%；上部结构完成62%；地面桥梁完成27%地面道路完成18%
3、三标段主线高架下部结构完成58%；上部结构完成32%；地面桥梁完成12%地面道路完成12%。', '一、、征收腾地
新通站、管线腾地范围内累计完成企业16家、居民18户
二、市政配套
完成影响地面桥梁施工的供水临搬。
五、形象进度
总体进度：完成总建安量50%，主线高架完成70%，地面道路桥梁完成30%。其中
1、一标段主线高架下部结构完成95%；上部结构完成75%；地面桥梁完成25%地面道路完成10%
2、二标段主线高架下部结构完成85%；上部结构完成65%；地面桥梁完成30%地面道路完成20%
3、三标段主线高架下部结构完成60%；上部结构完成35%；地面桥梁完成15%地面道路完成15%。', '三、形象进度：
总体进度：完成总建安量60%，主线高架完成75%，地面道路桥梁完成32%。其中
1、一标段主线高架下部结构完成97%；上部结构完成80%；地面桥梁完成30%地面道路完成15%
2、二标段主线高架下部结构完成90%；上部结构完成70%；地面桥梁完成40%地面道路完成25%
3、三标段主线高架下部结构完成65%；上部结构完成40%；地面桥梁完成20%地面道路完成20%。', '三、形象进度：
总体进度：完成总建安量62%，主线高架完成80%，地面道路桥梁完成35%。其中
1、一标段主线高架下部结构完成98%；上部结构完成85%；地面桥梁完成40%地面道路完成17%
2、二标段主线高架下部结构完成95%；上部结构完成80%；地面桥梁完成45%地面道路完成27%
3、三标段主线高架下部结构完成70%；上部结构完成50%；地面桥梁完成22%地面道路完成22%。', '二、市政配套
9月份完成燃气管线搬迁工作。
三、形象进度：
总体进度：完成总建安量65%，主线高架完成85%，地面道路桥梁完成37%。其中
1、一标段主线高架下部结构完成100%；上部结构完成90%；地面桥梁完成45%地面道路完成20%
2、二标段主线高架下部结构完成100%；上部结构完成90%；地面桥梁完成50%地面道路完成30%
3、三标段主线高架下部结构完成75%；上部结构完成60%；地面桥梁完成25%地面道路完成25%。', '三、形象进度
总体进度：完成总建安量70%，主线高架完成90%，地面道路桥梁完成40%。其中
1、一标段主线高架下部结构完成100%；上部结构完成100%；地面桥梁完成50%地面道路完成30%
2二标段主线高架下部结构完成100%；上部结构完成95%；地面桥梁完成60%地面道路完成40%、
3、三标段主线高架下部结构完成80%；上部结构完成70%；地面桥梁完成30%地面道路完成30%。', '三、形象进度
总体进度：完成总建安量75%，主线高架完成92%，地面道路桥梁完成42%。其中
1、一标段主线高架下部结构完成100%；上部结构完成100%；地面桥梁完成55%地面道路完成35%
2二标段主线高架下部结构完成100%；上部结构完成97%；地面桥梁完成65%地面道路完成45%、
3、三标段主线高架下部结构完成82%；上部结构完成80%；地面桥梁完成32%地面道路完成32%。', '二、市政配套
11月完成电力管线搬迁工作。
三、形象进度
总体进度：完成总建安量80%，主线高架完成95%，地面道路桥梁完成45%。其中
1、一标段主线高架下部结构完成100%；上部结构完成100%；地面桥梁完成65%地面道路完成40%
2二标段主线高架下部结构完成100%；上部结构完成100%；地面桥梁完成70%地面道路完成50%、
3、三标段主线高架下部结构完成85%；上部结构完成85%；地面桥梁完成35%地面道路完成35%。', 1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (2, '2020-01-01 00:00:00', '2018-12-01 00:00:00', '2021-12-01 00:00:00', 188037.8, 0,
        '1标施工准备；2、3标施工准备，吴淞江特大桥水务手续，北中心河大桥水务手续。', '1标施工准备；2、3标施工准备，吴淞江特大桥水务手续，北中心河大桥水务手续。',
        '1标施工准备；2、3标施工准备，吴淞江特大桥水务手续，北中心河大桥水务手续。', '1标围挡封闭；2、3标施工准备，吴淞江特大桥水务手续，北中心河大桥水务手续，便道便桥。',
        '1标围挡封；2、3标施工准备，吴淞江特大桥水务手续，北中心河大桥水务手续，便道便桥。', '1标桥梁下部结构，排水施工；2、3标施工准备，吴淞江特大桥水务手续，施工许可证。',
        '完成总建安量的1%；其中，1标桥梁下部结构，排水；2、3标桥梁下部结构施工。', '完成总建安量的3%。其中，1标桥梁下部结构，排水；2、3标桥梁下部结构施工。',
        '完成总建安量的5%.其中，1标桥梁下部结构，排水；2、3标桥梁下部结构施工。', '完成总建安量的10%.其中，1标桥梁下部结构，排水；2、3标桥梁下部结构施工。',
        '完成总建安量的15%.其中，1标桥梁，排水，道路；2、3标桥梁下部结构施工。', '完成总建安量的20%.其中，1标桥梁，排水，道路；2、3标桥梁下部结构施工。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (3, '2020-01-01 00:00:00', '2018-12-01 00:00:00', '2020-12-01 00:00:00', 47426.12, 0,
        '完成建安量的50%。桥梁钻孔灌注桩；桥梁上部结构；路基、水稳施工。', '完成建安量的52%。桥梁钻孔灌注桩；桥梁上部结构；路基、水稳施工。', '完成建安量的55%.桥梁下部结构；桥梁上部结构；路基、水稳施工。',
        '完成建安量的60%。桥梁下部结构；上部结构；路基、水稳施工。', '完成建安量的65%。桥梁上部结构施工；桥面铺装；水稳施工。', '完成建安量的70%。桥梁上部结构施工；桥面铺装；水稳施工，排水施工。',
        '完成建安量的75%，桥面铺装、水稳施工、排水工程施工。', '，完成建安量的80%.桥面铺装、水稳施工、排水工程施工。', '，完成建安量的85%.面层施工、排水工程、附属工程施工。',
        '完成建安量的92%。面层施工、排水工程、附属工程施工。', '，完成建安量的98%面层施工、附属工程、排水工程施工。', '基本完成。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (4, '2020-01-01 00:00:00', '2018-12-01 00:00:00', '2020-09-01 00:00:00', 22674.04, 0, '完成总建安量的30%。路基施工，管线施工。',
        '完成总建安量的32%。路基施工，管线施工。', '完成总建安量的35%。1标：路基施工，便道便桥，管线施工，桩基施工；2标：大临搭设完成，桩基施工。',
        '完成总建安量的45%。1标：路基施工，老桥拆除，桥梁下部结构施工。2标：灌注桩桩施工。', '完成总建安量的55%。1标：路基施工，桥梁下部结构施工，强电、弱电施工完成。2标：灌注桩施工。',
        '完成总建安量的65%。1标：路基施工，桥梁下部施工。2标：灌注桩施工，盖梁施工。', '完成总建安量的75%。1标：路基施工，路面施工，桥梁架梁、桥面铺装。2标：盖梁施工、桥梁架梁。',
        '完成总建安量的85%。1标：路基施工，路面施工，桥梁上部结构施工，附属设施施工。2标：桥面铺装、接坡段路基施工、摊铺水稳，附属设施施工。',
        '完成总建安量的100%。1标：桥面铺装，路面施工。2标桥面铺装、路面施工，附属设施施工、绿化施工。', '竣工结算。', '竣工结算。', '竣工结算。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (5, '2020-01-01 00:00:00', '2018-09-01 00:00:00', '2020-12-01 00:00:00', 36378.63, 0,
        '完成建安量的45%。南段1公里10#围护井三轴桩施工完成、顶管施工、北段1公里3座桥钻孔桩施工。',
        '完成建安量的46%.南段1公里10#围护井三轴桩施工、顶管施工完成、超深井完成、500米雨水管排放完成、北段1公里钻孔灌注桩施工。',
        '完成建安量的50%南段1公里路基灰土完成、水稳摊铺完成、北段1公里钻孔灌注桩施工完成，盖梁支模，钢筋绑扎，浇砼。', '排水管道28%、路基施工20%，桥梁上下部结构施工27%，完成建安工作量52%。',
        '排水管道33%，路基水稳施工25%，桥梁下部、上部结构施工35%，检查站施工10%，完成建安工作量58%。',
        '完成建安量的65%南段东侧1公里东侧施工路基、北段1公里雨污水管排放、防撞墙及栏杆基座施工、朝阳河填埋段施工。检查站结构完成，二结构及内外粉刷。',
        '排水管道60%，路基水稳施工25%，桥梁下部、上部结构施工55%，检查站施工50%，完成建安工作量70%。',
        '排水管道80%，路基水稳施工50%，桥梁下部、上部结构施工65%，检查站施工65%，完成建安工作量75%。',
        '完成建安量的80%南段东侧1公里东侧施工路基、水稳摊铺、侧平石铺砌、沥青底层及中层摊铺、北段1公里雨污水管排放、路基灰土施工。检查站钢结构施工，内外装修施工。',
        '路基水稳施工100%，桥梁下部、上部结构施工90%，检查站施工95%，完成建安工作量88%。', '路面沥青施工95%，桥梁上部结构及附属施工95%，检查站施工100%，总形象进度95%。', '基本完成。', 1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (6, '2020-01-01 00:00:00', '2016-12-01 00:00:00', '2020-09-01 00:00:00', 181109.62, 0,
        '完成建安量的75%。1标：南、北段桥梁施工、道路施工。3标：道路施工、桥梁施工。', '完成建安量的75%。1标：南、北段桥梁施工、道路施工。3标：道路施工、桥梁施工。', '
二、证照审批
3标完成交通设施招标
三、市政配套
1.1标自来水、弱电、电力公司机械排管施工
2.3标弱电施工
四、形象进度
完成建安量的75%。1标：南、北段桥梁施工、道路施工。3标：道路施工、桥梁施工。
', '1标：完成建安量的80%，道路施工，桥梁施工。3标：完成建安量的45%，章堰泾桥东半幅下部结构施工、雨水管施工。',
        '1标：完成建安量的85%，道路施工，桥梁施工。3标：完成建安量的55，章堰泾桥下部结构、上部结构施工、桥面附属设施施工、雨水管施工。道路施工。', '
二、证照审批
3标完成路灯、交通设施招标
三、市政配套
1.1标完成自来水、弱电、电力公司机械排管施工
2.3标完成自来水、弱电搬迁施工
四、形象进度

1、完成 1 标桥梁，道路 95%。
2、完成 3 标桥梁90%及道路。
', '1标：完成建安量的97%,道路施工，3标：完成建安量的75%，章堰泾桥西半幅下部结构施工。路面施工。',
        '1标：完成建安量的99%，道路施工。3标：完成建安量的85%，章堰泾桥西半幅上部结构、下部结构施工、桥面附属设施施工、道路施工。', '
二、形象进度
1.1标附属设施施工完成100%
2.3标桥梁施工完成100%、附属设施施工100%。
', '1标：交工验收，结算整理。3标：交工验收、结算资料整理。', '1、3标：结算资料整理。', '
二、形象进度
1.3标具备通车条件。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (7, '2020-01-01 00:00:00', '2019-03-01 00:00:00', '2021-12-01 00:00:00', 219248, 0,
        '西段土方施工、护岸施工，项目进度30%。东段施工招标。', '西段土方施工、护岸施工，项目进度30%。东段施工招标。', '西段土方施工、护岸施工。项目进度30%。东段施工招标。',
        '张贴3次公告，完成征收居民5户，企业5户。西段土方施工、护岸施工，项目进度31%。东段施工准备。', '张贴3次公告完成征收居民5户，企业6户。西段土方施工、护岸施工，项目进度32%。东段施工准备。', '1.完成征地批次下达,完成征收居民5户，企业7户。
2.西段土方施工、护岸施工，项目进度34%。东段完成施工招标。', '完成征收居民5户，企业7户。西段土方施工、护岸施工，项目进度35%。东段土方施工、护岸施工，项目进度2%。',
        '完成征收居民5户，企业7户。西段土方施工、护岸施工，项目进度38%。东段土方施工、护岸施工，项目进度3%。', '1.完成征收居民5户，企业8户。
2.西段土方施工、护岸施工，项目进度42%。东段开工建设并完成进度5%）。
', '西段土方施工、护岸施工，项目进度45%。东段土方施工、护岸施工，项目进度8%。', '西段土方施工、护岸施工，项目进度48%。东段土方施工、护岸施工，项目进度12%。',
        '西段土方施工、护岸施工，完成形象进度 51%。东段土方施工、护岸施工，完成形象进度 15%。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (8, '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2021-12-01 00:00:00', 89240.78, 0, '前期动迁。', '前期动迁。', '施工招标。',
        '施工准备。', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度2%。', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度4%。', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度6%。',
        '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度8%。', '西洋淀段桥梁施工，水闸施工河道开挖，护岸施工 ， 总 形 象 进 度10%。', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度13%。',
        '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度17%。', '西洋淀段水闸施工河道开挖，护岸施工，完成形象进度20%。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (9, '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2021-12-01 00:00:00', 70123.69, 0, '前期动迁。', '前期动迁。', '施工招标。',
        '施工准备。', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度2%。', '施工招标，前期动迁及施工准备', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度6%。',
        '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度8%。', '
西段桥梁施工，水闸施工河道开挖，护岸施工，完成形象进度 10%。）', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度13%。', '桥梁施工，水闸施工河道开挖，护岸施工，总形象进度17%。',
        '西段桥梁施工，水闸施工河道开挖，护岸施工，形象进度20%', 1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (10, '2020-01-01 00:00:00', null, null, 21083.86, 0, '初设编制。', '初设编制。', '初设编制。', '初设待批、代建招标。', '初设待批。',
        '初设批复。', '办理用地手续。', '办理用地手续、施工图。', '办理用地手续。', '启动腾地工作、招投标。', '招投标。', '完成招投标。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (11, '2020-01-01 00:00:00', '2019-01-01 00:00:00', '2021-05-01 00:00:00', 8381.39, 0, '上部结构施工，设备安装，总形象进度65%。',
        '上部结构施工，设备安装，总形象进度65%。', '上部结构施工，设备安装，总形象进度67%', '上部结构施工，设备安装，总形象进度68%。', '上部结构施工，设备安装，总形象进度69%。',
        '上部结构施工，设备安装，总形象进度70%。', '上部结构施工，设备安装，总形象进度74%。', '上部结构施工，设备安装，总形象进度78%。', '场地施工，设备安装调试，总形象进度82%。',
        '上部结构施工，设备安装，总形象进度84%。', '上部结构施工，设备安装，总形象进度87%。', '场地清理，设备调试，试运行，总形象进度90%。', 1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (12, '2020-01-01 00:00:00', '2019-01-01 00:00:00', '2021-05-01 00:00:00', 17019.24, 0, '总形象进度67%。', '总形象进度67%。',
        '上部结构施工，设备安装，总形象进度67%。', '上部结构施工，设备安装，总形象进度68%。', '上部结构施工，设备安装，总形象进度69%。', '上部结构施工，设备安装，总形象进度70%。',
        '上部结构施工，设备安装，总形象进度74%。', '上部结构施工，设备安装，总形象进度78%。', '场地施工，设备安装调试，总形象进度82%。', '上部结构施工，设备安装，总形象进度84%。',
        '上部结构施工，设备安装，总形象进度87%。', '场地清理，设备调试，试运行，总形象进度90%。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (13, '2020-01-01 00:00:00', '2019-12-01 00:00:00', '2020-12-01 00:00:00', 54469.15, 0, '疫情未施工。', '疫情未施工。',
        '3月10日复工，围墙开始施工，3月20日主厂房基础工程开始打桩。', '围墙工程、桩基工程基本完成，主厂房施工10%，综合污水池完成15%，总体进度完成7%。',
        '主厂房施工20%，除臭系统完成20%，综合污水池完成30%，总体进度完成15%。', '主厂房施工30%，除臭系统、综合污水池完成35%，总体进度完成25%。',
        '主厂房施工45%，除臭系统、综合污水池完成50%，污水设备完成10%，总体进度完成40%。', '主厂房施工65%，除臭系统、综合污水池完成60%，综合楼完成15%，主设备进场，总体进度完成50%。',
        '主厂房施工85%，除臭系统、综合污水池完成80%，综合楼完成30%，设备安装完成30%，总体进度完成65%。',
        '主厂房施工95%，除臭系统、综合水池基本完成，综合楼完成45%，设备安装完成55%，总体进度完成75%。', '主厂房基本完成，综合楼完成65%，门卫完成40%，设备安装完成80%，总体进度完成85%。',
        '除室外零星工程外，土建基本完成，主工艺段设备安装完成，基本完成单机调试。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (14, '2020-01-01 00:00:00', '2019-12-01 00:00:00', '2020-12-01 00:00:00', 38000, 0, '腾地；桩基工程。', '腾地；桩基工程。',
        '桩基完成；基础工程开始。', '车间基础65%、筒仓基础完成、附楼基础40%。', '基础完成，车间钢结构15%、筒仓上部结构40%，附楼上部结构15%。', '筒仓上部结构完成，车间钢结构50%。',
        '筒仓建筑完成、车间钢结构80%；准备安装、附楼80%', '筒仓安装50%、车间安装、钢结构完成、附楼二结构。', '筒仓安装完成、车间安装完成、附楼机电安装，外总体开始施工。',
        '外总体施工、车间安装基本完成、附楼装饰施工。', '附楼装饰完成、车间完成、外总体接入。', '竣工。', 1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (15, '2020-01-01 00:00:00', '2019-03-01 00:00:00', '2020-12-01 00:00:00', 44000, 0, '办理前期手续。', '办理前期手续。',
        '办理前期手续。', '土地平整，实施造林300亩。', '累计实施造林600亩。', '累计实施造林1000亩。', '土地平整。',
        '土地平整。', '土地平整。', '累计实施造林2000亩。', '累计实施造林3000亩。',
        '累计实施造林4500亩，完成天马处理厂周边重点生态廊道、沪渝高速、沈海高速、绕城高速、拦路港沿线重点生态廊道。吴淞江生态廊道根据苏申内港项目进度实施。', 1);



insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (17, '2020-01-01 00:00:00', '2019-06-01 00:00:00', null, 750000, 0, '未提供计划。', '未提供计划。', '未提供计划。', '未提供计划。',
        '未提供计划。', '未提供计划。', '未提供计划。', '未提供计划。', '未提供计划。', '未提供计划。', '未提供计划。', '未提供计划。', 1);



insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (22, '2020-01-01 00:00:00', '2020-06-01 00:00:00', '2021-12-01 00:00:00', 13220, 0, '确定方案。', '项建书批文。',
        '选址和土地预审。', '工可报告编制。', '工可评审。', '工可批文、方案批文。', '施工图设计。', '施工图审图。', '施工招标、建设工程规划许可证。', '施工招标。', '开工手续。', '开工。',
        1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (24, '2020-01-01 00:00:00', '2019-10-01 00:00:00', '2021-10-01 00:00:00', 86000, 0, '地下结构围护工程完成。',
        '地下结构工程完成10%。', '地下结构工程完成20%。', '地下结构工程完成35%。', '地下结构工程完成50%。', '地下结构工程完成65%。', '地下结构工程完成80%。',
        '地下结构工程完成90%，主体结构工程开始施工。', '地下结构工程完成，主体结构工程完成15%。', '主体结构工程完成30%。', '主体结构工程完成45%。', '主体结构工程完成60%。', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (25, '2020-01-01 00:00:00', '2019-10-01 00:00:00', '2021-04-01 00:00:00', 12028.51, 0, '进场准备。', '进场准备。', '进场准备。',
        '柴油发电机完成10%。', '柴油发电机完成35%、连廊3完成20%。', '柴油发电机完成50%，连廊3完成40%。', '柴油发电机完成80%、连廊3完成60%。', '柴油发电机完成100%、连廊3完成80%。',
        'E区完成（包括连廊1、2）10%、G区完成20%、连廊3完成100%。', 'E区完成（包括连廊1、2）35%、G区完成
40%。', 'E区完成（包括连廊1、2）50%、G区完成60%。', 'E区完成（包括连廊1、2）70%、G区完成80%。', 1);


insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (27, '2020-01-01 00:00:00', '2019-07-01 00:00:00', '2021-06-01 00:00:00', 212219, 0, '围护工程、地下结构工程。', '地下结构工程。',
        '地下结构工程。', '地下结构工程。', '地下结构工程。', '地下结构工程、上部结构工程。', '上部结构工程。', '上部结构工程。', '上部结构工程、填充墙砌筑。', '上部结构工程、填充墙砌筑、屋面工程。',
        '填充墙砌筑、屋面工程、外装饰工程。', '填充墙砌筑、屋面工程、外装饰工程', 1);

insert into project_plan_table (project_id, project_plan_year, project_plan_expect_start_time,
                                project_plan_expect_end_time, project_plan_invest_total, project_plan_invest_finish,
                                project_plan_january, project_plan_february, project_plan_march, project_plan_april,
                                project_plan_may, project_plan_june, project_plan_july, project_plan_august,
                                project_plan_september, project_plan_october, project_plan_november,
                                project_plan_december, plan_type)
values (28, '2020-01-01 00:00:00', '2018-01-01 00:00:00', '2020-11-01 00:00:00', 88157, 0, 'A区落脚手架，BC区内外装饰。',
        'A区外墙涂料，室内地坪，BC区内外装饰。', 'A区外墙涂料、室内地坪；BC区拆脚手架。', 'A区室内地坪；BC区拆脚手架，室内地坪。', 'BC区外墙涂料，室内地坪；回填土、室外总体。',
        'BC区外墙涂料完成，回填土完成，室外总体，配套管线。', '室外总体完成，景观绿化完成，竣工验收。', '竣工验收。', '编制结算。', '编制结算。', '编制结算。', '编制结算。', 1);




