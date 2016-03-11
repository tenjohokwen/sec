DROP SCHEMA IF EXISTS sec;
CREATE SCHEMA sec;

use sec;

CREATE TABLE jhi_authority (
  name varchar(50) NOT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE jhi_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  login varchar(50) NOT NULL,
  password_hash varchar(60) DEFAULT NULL,
  first_name varchar(50) DEFAULT NULL,
  last_name varchar(50) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  activated bit(1) NOT NULL,
  locked bit(1) NOT NULL,
  lang_key varchar(5) DEFAULT NULL,
  activation_key varchar(20) DEFAULT NULL,
  reset_key varchar(20) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date timestamp NOT NULL,
  reset_date timestamp NULL DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  last_modified_date timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY login (login),
  UNIQUE KEY idx_user_login (login),
  UNIQUE KEY email (email),
  UNIQUE KEY idx_user_email (email)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE jhi_user_authority (
  user_id bigint(20) NOT NULL,
  authority_name varchar(50) NOT NULL,
  PRIMARY KEY (user_id,authority_name),
  KEY fk_authority_name (authority_name),
  CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES jhi_authority (name),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES jhi_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO JHI_AUTHORITY VALUES ('ROLE_ADMIN'),('ROLE_USER');

INSERT INTO JHI_USER
(id,
login,
password_hash,
first_name,
last_name,
email,
activated,
lang_key,
activation_key,
reset_key,
created_by,
created_date,
reset_date,
last_modified_by,
last_modified_date,
locked) VALUES 
(1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost',1,'en',NULL,NULL,'system','2015-09-05 10:12:15',NULL,NULL,NULL, false),
 (2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost', 1,'en',NULL,NULL,'system','2015-09-05 10:12:15',NULL,NULL,NULL, false),
(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost', 1,'en',NULL,NULL,'system','2015-09-05 10:12:15',NULL,NULL,NULL, false),
(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost', 1,'en',NULL,NULL,'system','2015-09-05 10:12:15',NULL,NULL,NULL, false);
;

INSERT INTO JHI_USER_AUTHORITY VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');
