use upload_model;
insert into role_table(role_name, role_access) value
('后台管理员', 'SUPERADMIN'),
('项目管理员', 'ADMIN'),
('普通用户', 'COMMON'),
('游客', 'GUEST');
insert into user_table
(user_name, login_name, login_password, creator_id, user_zone, create_time, role_id)
VALUES ('admin', 'admin', '$2a$10$Q96zmpvV4Udk81xT9ZfdbeF0zRezkHWiHt/jBIbG5391PObg2j35i', 0, 'admin',
        current_timestamp(), 1),
       ('guest', 'guest', '$2a$10$PdeHEh2nNvOI/TUiWoNBo.tdM.JUG3hL.jco2DEczFzJ1HLFqB5n2', 0, 'guest',
        current_timestamp(), 4);