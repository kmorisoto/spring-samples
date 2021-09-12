CREATE TABLE users
(
    id   int
        CONSTRAINT users_pk
            PRIMARY KEY,
    name varchar(100) NOT NULL
);
INSERT INTO users (id, name)
VALUES (1, 'foo')
     , (2, 'bar')
     , (3, 'baz');
