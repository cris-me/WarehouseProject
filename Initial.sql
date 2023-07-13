CREATE TABLE warehouses(
	id SERIAL PRIMARY KEY,
	warehouse_name VARCHAR(50) UNIQUE NOT NULL,
	capacity INT NOT NULL,
	current_inventory INT DEFAULT 0
);

CREATE TABLE items(
	id SERIAL PRIMARY KEY,
	item_name TEXT UNIQUE NOT NULL
);

CREATE TABLE warehouse_inventory(
	id SERIAL PRIMARY KEY,
	warehouse_id INT NOT NULL,
	item_id INT NOT NULL,
	quantity INT DEFAULT 1,
	FOREIGN KEY(warehouse_id) REFERENCES warehouses(id) ON DELETE CASCADE,
	FOREIGN KEY(item_id) REFERENCES items(id) ON DELETE CASCADE
);


SELECT * FROM warehouses
SELECT * FROM items
SELECT * FROM warehouse_inventory

INSERT INTO warehouses(id, name,capacity)
VALUES(3, 'Arizona', 96);

INSERT INTO items(id, name)
VALUES(20, 'Soccer Goal');

INSERT INTO warehouse_inventory(warehouse_id,item_id,quantity)
VALUES(1,4,22)

DELETE FROM warehouses
WHERE "id" = 3;

DELETE FROM items
WHERE "id" = 20;

DELETE FROM warehouse_inventory
WHERE "id" = 20;

DROP TABLE warehouses;
DROP TABLE items;
DROP TABLE warehouse_inventory;

