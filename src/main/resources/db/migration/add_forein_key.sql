alter table tb_freight add constraint fk_freight_carrier
foreign key (carrier_id) references tb_carrier (id);

09:31:07	alter table tb_freight add constraint fk_freight_carrier foreign key (carrier_id) references tb_carrier (id)	Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`apifrete`.`#sql-16bc_56c`, CONSTRAINT `fk_freight_carrier` FOREIGN KEY (`carrier_id`) REFERENCES `tb_carrier` (`id`))	0.063 sec
