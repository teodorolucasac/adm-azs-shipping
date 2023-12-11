alter table tb_freight add constraint fk_freight_carrier
foreign key (carrier_id) references tb_carrier (id);