drop database if exists accounts;
create database accounts;
use accounts;


-- =====================================================================================================================
-- Account tables
-- =====================================================================================================================

create table account (
    id bigint unsigned not null auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null,
   	clientId varchar(50) not null,
   	creationTime long not null,
    unique index account_idx1 (username)
) engine = InnoDb;

create table role (
    id bigint unsigned not null auto_increment primary key,
    name varchar(50) not null
) engine = InnoDb;

create table permission (
    id bigint unsigned not null auto_increment primary key,
    name varchar(50) not null
) engine = InnoDb;

create table account_role (
    id bigint unsigned not null auto_increment primary key,
    account_id bigint unsigned not null,
    role_id bigint unsigned not null,
    foreign key (account_id) references account (id),
    foreign key (role_id) references role (id),
    unique index account_role_idx1 (account_id, role_id)
) engine = InnoDb;

create table role_permission (
    id bigint unsigned not null auto_increment primary key,
    role_id bigint unsigned not null,
    permission_id bigint unsigned not null,
    foreign key (role_id) references role (id),
    foreign key (permission_id) references permission (id),
    unique index role_permission_idx1 (role_id, permission_id)
) engine = InnoDb;


-- =====================================================================================================================
-- Procedures
-- =====================================================================================================================

delimiter //

create procedure createRole($name varchar(50), out $id smallint)
begin
    insert into role (name) values ($name);
end //

create procedure createAccount($username varchar(50), $pass varchar(50), $clientId varchar(50), $creationTime long, $role varchar(50))
BEGIN 
DECLARE last_id int default 0;
DECLARE role_id int default 0;
DECLARE exit handler for sqlexception
BEGIN
ROLLBACK;
END;
DECLARE exit handler for sqlwarning
BEGIN
ROLLBACK;
END;
START TRANSACTION;
  insert into account (username, password, clientId, creationTime) values ($username, MD5($pass), $clientId, $creationTime);
    set last_id = last_insert_id();   
    select id from role where name=$role into role_id;
    insert into account_role (account_id, role_id) values (last_id, role_id);  
COMMIT;
END //  