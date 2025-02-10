CREATE TABLE IF NOT EXISTS address(
                                      id SERIAL PRIMARY KEY,
                                      street TEXT NOT NULL,
                                      city TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    firstname TEXT NOT NULL,
    lastname TEXT NOT NULL,
    age INT NOT NULL,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);