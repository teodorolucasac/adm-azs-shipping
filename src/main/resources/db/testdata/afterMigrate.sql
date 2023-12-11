set foreign_key_checks = 0;

delete from tb_carrier;
delete from tb_freight;

set foreign_key_checks = 1;

alter table tb_carrier auto_increment = 1;
alter table tb_freight auto_increment = 1;

insert into tb_carrier (id, name) values (1, 'ABC Transporte');
insert into tb_carrier (id, name) values (2, 'Transportadora XYZ');
insert into tb_carrier (id, name) values (3, 'TESTE Transporte S/A');
insert into tb_carrier (id, name) values (4, 'Transport Test S/A');

insert into tb_freight (id, type, weight, cubage, origin_address, destination_address, cost, order_date, shipment_date, arrival_date,
order_status, carrier_id, carrier_contact, payment_method, notes) values (1, 'GROUND', 50000.0, 0.0, 'Rua A, 100, Centro -
Uberlandia-MG',
'Rua B, 200, Centro - São Paulo-SP', 6599.98, '2023-12-07 00:00:00', "2023-12-08 00:00:00", '2023-12-10 00:00:00',
'ORDER', 1, '843b3128-4289-430f-a107-42075a43fd1f', 'Débito', 'Conteúdo: 1000 notebooks');
insert into tb_freight (id, type, weight, cubage, origin_address, destination_address, cost, order_date, shipment_date, arrival_date,
order_status, carrier_id, carrier_contact, payment_method, notes) values (2, 'AIR', 100000.0, 10000.0, 'Rua Um, 100, Centro - Uberlandia-MG',
'Rua Dois, 200, Centro - São Paulo-SP', 12099.98, '2023-12-08 00:00:00', "2023-12-10 00:00:00", '2023-12-12 00:00:00',
'ORDER', 2, 'c8bc927-fa79-49a0-b53a-53e2af8d323e', 'Parcelado', 'Conteúdo: 2000 notebooks');
insert into tb_freight (id, type, weight, cubage, origin_address, destination_address, cost, order_date, shipment_date, arrival_date,
order_status, carrier_id, carrier_contact, payment_method, notes) values (3, 'MARITIME', 0.0, 10000.0, 'Rua C, 10, Centro - Uberlandia-MG',
'Rua D, 20, Centro - São Paulo-SP', 120.00, '2023-12-10 00:00:00', "2023-12-12 00:00:00", '2023-12-14 00:00:00',
'ORDER', 3, 'c0a66269-e737-4d1e-b131-2ad0374e0925', 'Crédito', 'Conteúdo: 10000 balas de morango');
insert into tb_freight (id, type, weight, cubage, origin_address, destination_address, cost, order_date, shipment_date, arrival_date,
order_status, carrier_id, carrier_contact, payment_method, notes) values (4, 'GROUND', 1000.0, 1000.0, 'Rua teste, 10, Centro - Uberlandia-MG',
'Rua teste, 20, Centro - São Paulo-SP', 100.00, '2023-12-15 00:00:00', "2023-12-16 00:00:00", '2023-12-17 00:00:00',
'ORDER', 4, 'bf93e148-fcdf-407c-baac-5b5c5a706a40', 'Crédito', 'Conteúdo: teste 01');
insert into tb_freight (id, type, weight, cubage, origin_address, destination_address, cost, order_date, shipment_date, arrival_date,
order_status, carrier_id, carrier_contact, payment_method, notes) values (5, 'GROUND', 1000.0, 1000.0, 'Rua teste, 10, Centro - Uberlandia-MG',
'Rua teste, 20, Centro - São Paulo-SP', 100.00, '2023-12-15 00:00:00', "2023-12-16 00:00:00", '2023-12-17 00:00:00',
'ORDER', 4, 'bf93e148-fcdf-407c-baac-5b5c5a706a41', 'Crédito', 'Conteúdo: teste 02');
insert into tb_freight (id, type, weight, cubage, origin_address, destination_address, cost, order_date, shipment_date, arrival_date,
order_status, carrier_id, carrier_contact, payment_method, notes) values (6, 'GROUND', 1000.0, 1000.0, 'Rua teste, 10, Centro - Uberlandia-MG',
'Rua teste, 20, Centro - São Paulo-SP', 100.00, '2023-12-15 00:00:00', "2023-12-16 00:00:00", '2023-12-17 00:00:00',
'ORDER', 4, 'bf93e148-fcdf-407c-baac-5b5c5a706a42', 'Crédito', 'Conteúdo: teste 03');

