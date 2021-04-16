CREATE TABLE weather (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    temperature NUMERIC(5, 2),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
