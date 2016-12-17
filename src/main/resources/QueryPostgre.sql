 CREATE TABLE sys_form
(
  form_id character varying(6) NOT NULL,
  zul_file character varying(100),
  name character varying(125),
  CONSTRAINT sys_form_pk PRIMARY KEY (form_id)
);

CREATE TABLE sys_role
(
  role_id character varying(2) NOT NULL,
  name character varying(120),
  CONSTRAINT sys_role_pk PRIMARY KEY (role_id)
);

CREATE TABLE sys_menu
(
  menu_id character varying(6) NOT NULL,
  name character varying(50) NOT NULL,
  form_id character varying(6),
  parent_id character varying(6),
  sequence integer,
  param character varying(128),
  CONSTRAINT sys_menu_pk PRIMARY KEY (menu_id)
);

CREATE TABLE r_role_menu
(
  role_id character varying(2) NOT NULL,
  menu_id character varying(6) NOT NULL,
  CONSTRAINT cfg_role_menu_pk PRIMARY KEY (role_id, menu_id)
);

CREATE TABLE sys_user(
user_id int NOT NULL,
user_name character varying(50) NOT NULL,
password character varying(255) NOT NULL,
email character varying(255) NULL,
enabled int NOT NULL DEFAULT 0,
CONSTRAINT sys_user_pk primary key(user_id)
)

CREATE TABLE r_user_role(
user_id int  NOT NULL,
role_id character varying(2) NOT NULL,
CONSTRAINT r_user_role_pk primary key(user_id,role_id)
);

truncate table sys_user;
truncate table sys_menu;
truncate table r_user_role;
truncate table r_role_menu;
truncate table sys_role;
truncate table sys_form;

INSERT INTO sys_user(user_id,user_name,email,password,enabled)VALUES (0,'admin','dev@dev.com','$2a$04$j3JpPUp6CTAe.kMWmdRNC.Wie58xDNPfcYz0DBJxWkucJ6ekJuiJm',1);

INSERT INTO r_user_role(user_id,role_id)VALUES(0,'XX');

INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000000','ENTRY',null,null,0,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000100','PARAMETER SISTEM',null,'000000',1,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000101','Pendaftaran Form','0001','000100',1,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000102','Pemeliharaan User','0006','000100',2,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000200','PARAMETER MENU',null,'000000',2,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000201','Pemeliharaan Wewenang','0002','000200',1,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000202','Pemeliharaan Menu','0003','000200',2,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('000203','Pemeliharaan Role Menu','0004','000200',3,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('010100','USER',null,'010000',1,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('010101','Ubah Password','0008','010100',1,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('010102','Reset Password','0007','010100',2,null);
INSERT INTO sys_menu (menu_id,name,form_id,parent_id,sequence,param) VALUES ('010000','UTILITY',null,null,1,null);



INSERT INTO sys_role (role_id,name) VALUES ('01','Administrator');
INSERT INTO sys_role (role_id,name) VALUES ('02','User Aplikasi');
INSERT INTO sys_role (role_id,name) VALUES ('XX','SUPER USER');


INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000000');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000100');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000101');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000102');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000200');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000201');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000202');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','000203');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','010000');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','010100');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','010101');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','010102');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','010200');
INSERT INTO r_role_menu (role_id,menu_id) VALUES ('XX','010201');

INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0001','/page/master/WndPendaftaranForm.zul','Pemeliharaan Form');
INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0002','/page/master/WndPemeliharaanWewenang.zul','Pemeliharaan Wewenang');
INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0003','/page/master/WndPemeliharaanMenu.zul','Pemeliharaan Menu');
INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0004','/page/master/WndPemeliharaanRoleMenu.zul','Pemeliharaan Role Menu');
INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0006','/page/master/WndPemeliharaanUser.zul','Pemeliharaan User');
INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0007','/page/master/WndResetPassword.zul','Reset Password');
INSERT INTO sys_form (form_id,zul_file,name) VALUES ('0008','/page/master/WndChangePassword.zul','Change Password');


