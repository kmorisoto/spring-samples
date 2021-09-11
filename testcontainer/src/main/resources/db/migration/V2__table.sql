CREATE TABLE users
(
    id   serial
        CONSTRAINT users_pk
            PRIMARY KEY,
    name varchar(100) NOT NULL
);
