CREATE TABLE users
(
    id       serial PRIMARY KEY,
    username varchar(50)  NOT NULL,
    email    varchar(100) NOT NULL
);

CREATE TABLE products
(
    id    serial PRIMARY KEY,
    name  varchar(100)   NOT NULL,
    price decimal(10, 2) NOT NULL
);

CREATE TABLE orders
(
    id         serial PRIMARY KEY,
    user_id    int REFERENCES users (id),
    product_id int REFERENCES products (id),
    quantity   int NOT NULL,
    order_date timestamp DEFAULT CURRENT_TIMESTAMP
);
