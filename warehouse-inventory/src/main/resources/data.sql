/* Inserting 10 Warehouses */
insert into warehouses (name, capacity) values ('North Carolina', 34);
insert into warehouses (name, capacity) values ('Florida', 61);
insert into warehouses (name, capacity) values ('Arizona', 96);
insert into warehouses (name, capacity) values ('Connecticut', 84);
insert into warehouses (name, capacity) values ('California', 46);
insert into warehouses (name, capacity) values ('South Carolina', 48);
insert into warehouses (name, capacity) values ('Texas', 33);
insert into warehouses (name, capacity) values ('Georgia', 20);
insert into warehouses (name, capacity) values ('New Jersey', 59);
insert into warehouses (name, capacity) values ('Washington', 15);

/* Inserting 10 Items */
insert into items (name) values ('Granite');
insert into items (name) values ('Plastic');
insert into items (name) values ('Plexiglass');
insert into items (name) values ('Vinyl');
insert into items (name) values ('Glass');
insert into items (name) values ('Wood');
insert into items (name) values ('Steel');
insert into items (name) values ('Rubber');
insert into items (name) values ('Aluminum');
insert into items (name) values ('Stone');

/* Inserting 10 rows for warehouse inventory. Will comment out for now because reallistically this data will be created by an admin
insert into warehouse_inventory (warehouse_id, item_id, quantity) values (3, 4, 19);
insert into warehouse_inventory (warehouse_id, item_id, quantity) values (7, 5, 14);
insert into warehouse_inventory (warehouse_id, item_id, quantity) values (9, 7, 11);
insert into warehouse_inventory (warehouse_id, item_id, quantity) values (6, 2, 18);
insert into warehouse_inventory (warehouse_id, item_id, quantity) values (8, 6, 14);
insert into warehouse_inventory (warehouse_id, item_id, quantity) values (4, 8, 17); */
