DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10, 2)
);

