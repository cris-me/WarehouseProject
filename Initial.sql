CREATE TABLE warehouses(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	capacity INT NOT NULL,
	current_inventory INT DEFAULT 0
);

CREATE TABLE items(
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL
)

CREATE TABLE warehouse_inventory(
	id SERIAL PRIMARY KEY,
	Warehouse_Id INT NOT NULL,
	Item_Id INT NOT NULL,
	Quantity INT DEFAULT 0,
	FOREIGN KEY(Warehouse_Id) REFERENCES warehouses(id) ON DELETE CASCADE,
	FOREIGN KEY(Item_Id) REFERENCES items(id) ON DELETE CASCADE
)


SELECT * FROM warehouses
SELECT * FROM items
SELECT * FROM warehouse_inventory

INSERT INTO warehouses(name,capacity)
VALUES('West Coast Branch', 200);

INSERT INTO items(name)
VALUES('Baseball Bat');

INSERT INTO warehouse_inventory(warehouse_id,item_id,quantity)
VALUES(1,4,22)

DELETE FROM warehouses
WHERE "id" = 4;

DELETE FROM items
WHERE "id" = 3;



