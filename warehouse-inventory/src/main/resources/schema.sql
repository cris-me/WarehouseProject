/* If the users table already exists, drop it so it can be remade */
drop table if exists warehouses;
drop table if exists items;
drop table if exists warehouse_inventory;

CREATE TABLE warehouses(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	capacity INT NOT NULL,
	current_inventory INT DEFAULT 0
);

CREATE TABLE items(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE warehouse_inventory(
	id INT AUTO_INCREMENT PRIMARY KEY,
	Warehouse_Id INT NOT NULL,
	Item_Id INT NOT NULL,
	Quantity INT DEFAULT 0,
	FOREIGN KEY(Warehouse_Id) REFERENCES warehouses(id) ON DELETE CASCADE,
	FOREIGN KEY(Item_Id) REFERENCES items(id) ON DELETE CASCADE
);