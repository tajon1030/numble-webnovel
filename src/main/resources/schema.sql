drop table if exists member;

create table member
(
    id int AUTO_INCREMENT,
    username  varchar(30) not null,
    password varchar(200) not null,
    created_dt datetime not null,
    modified_dt datetime,

    primary key (id)
);
