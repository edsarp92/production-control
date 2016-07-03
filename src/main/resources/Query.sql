INSERT INTO users(user_name,email,password,enabled)
VALUES ('admin','dev@dev.com','$2a$04$j3JpPUp6CTAe.kMWmdRNC.Wie58xDNPfcYz0DBJxWkucJ6ekJuiJm', true);

INSERT INTO roles (user_id, role,role_name)
VALUES (1, 'ROLE_USER','Administrator');
INSERT INTO roles (user_id, role,role_name)
VALUES (1, 'ROLE_ADMIN','Administrator');

INSERT INTO sys_form (form_id,zul_file,nama) VALUES (1,'/page/master/WndPendaftaranForm.zul','Pemeliharaan Form');
INSERT INTO sys_form (form_id,zul_file,nama) VALUES (2,'/page/master/WndPemeliharaanWewenang.zul','Pemeliharaan Wewenang');
INSERT INTO sys_form (form_id,zul_file,nama) VALUES (3,'/page/master/WndPemeliharaanMenu.zul','Pemeliharaan Menu');
INSERT INTO sys_form (form_id,zul_file,nama) VALUES (4,'/page/master/WndPemeliharaanRoleMenu.zul','Pemeliharaan Role Menu');
INSERT INTO sys_form (form_id,zul_file,nama) VALUES (5,'/page/master/WndPemeliharaanUser.zul','Pemeliharaan User');
INSERT INTO sys_form (form_id,zul_file,nama) VALUES (6,'/page/master/WndResetPassword.zul','Reset Password');
INSERT INTO sys_form (form_id,zul_file,nama) VALUES (7,'/page/master/WndChangePassword.zul','Change Password');


INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (1,'ENTRY',null,null,0,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (2,'PARAMETER SISTEM',null,1,1,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (3,'Pendaftaran Form',1,2,1,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (4,'Pemeliharaan User',5,2,2,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (5,'PARAMETER MENU',null,1,2,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (6,'Pemeliharaan Wewenang',2,5,1,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (7,'Pemeliharaan Menu',3,5,2,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (8,'Pemeliharaan Role Menu',4,5,3,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (9,'UTILITY',null,null,1,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (10,'USER',null,9,1,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (12,'Reset Password',6,10,1,null);
INSERT INTO cfg_menu (menu_id,name,form_id,menu_parent,seq,param) VALUES (11,'Ubah Password',7,10,2,null);

INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (1,1,1);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (2,1,2);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (3,1,3);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (4,1,4);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (5,1,5);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (6,1,6);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (7,1,7);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (8,1,8);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (9,1,9);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (10,1,10);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (11,1,11);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (12,1,12);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (13,1,13);
INSERT INTO role_menu (role_menu_id,role_id,menu_id) VALUES (14,1,14);