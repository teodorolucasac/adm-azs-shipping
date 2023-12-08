create table tb_freight (
	id bigint not null auto_increment,
	type varchar(30) not null,
	weight decimal(10,2) null,
    cubage decimal(10,2) null,
    origin_address varchar(80) not null,
    destination_address varchar(80) not null,
    cost decimal(10,2) not null,
    order_date datetime null,
    shipment_date datetime null,
    arrival_date datetime null,
    order_status varchar(30) not null,
    carrier_id bigint not null,
    carrier_contact varchar(36) not null,
    payment_method varchar(30) not null,
    notes varchar(255) null,

    primary key(id)
) engine=InnoDB default charset=utf8;