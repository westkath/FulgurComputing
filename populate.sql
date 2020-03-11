CREATE DATABASE IF NOT EXISTS shopdb;
use shopdb;

DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS Product(
	product_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    product_description VARCHAR(255) NOT NULL,
    product_price DECIMAL(13,2) NOT NULL,
    product_stock_level INT NOT NULL
);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Intel Core i9 9900K", "", 504.99, 29);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Intel Core i7 9700K", "8-core @ 3.6GHz", 369.99, 38);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Intel Core i5 9600K", "6-core @ 3.7GHz", 219.99, 35);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("AMD Ryzen 9 3900X", "12-core @ 3.8GHz", 418.95, 22);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("AMD Ryzen 7 3700X", "8-core @ 3.6GHz", 289.99, 30);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("AMD Ryzen 5 3600X", "6-core @ 3.8GHz", 199.99, 26);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Corsair H100i RGB PLATINUM", "2400RPM Fan @ 37dB", 159.49, 12);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Corsair H115i PRO", "1200RPM Fan @ 20.4dB", 158.47, 16);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("MSI B450 TOMAHAWK", "64GB Max Memory", 110.23, 19);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("MSI Z390-A PRO", "128GB Max Memory", 122.48, 23);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Corsair Vengeance RGB Pro", "16GB @ DDR4-3200", 94.99, 7);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Kingston HyperX Fury", "16GB @ DDR4-3200", 97.08, 11);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("NVIDIA Star Wars Jedi Order", "Expensive Star Wars GPU", 2476.99, 3);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("AMD RADEON PRO WX 4100", "Budget Friendly GPU", 234.99, 28);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Asus ROG Swift PG65UQ", "64.5in Expensive Monitor", 4499.00, 2);

INSERT INTO Product(product_name, product_description, product_price, product_stock_level)
VALUES ("Sceptre C248W-1920RN", "23.6in Cheaper Monitor", 104.97, 55);
