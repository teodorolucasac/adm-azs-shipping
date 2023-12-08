create table tb_carrier (
	id bigint not null auto_increment,
    name varchar(120) not null,

    primary key(id)
) engine=InnoDB default charset=utf8;