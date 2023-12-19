CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    postalCode VARCHAR(20) NOT NULL,
    clientId BIGINT,
    FOREIGN KEY (clientId) REFERENCES client(id)
);

CREATE TABLE warehouse (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description TEXT,
    warehouseId BIGINT,
    FOREIGN KEY (warehouseId) REFERENCES warehouse(id)
);


CREATE TABLE order_table (
    id SERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    clientId BIGINT,
    shippingAddressId BIGINT,
    FOREIGN KEY (clientId) REFERENCES client(id),
    FOREIGN KEY (shippingAddressId) REFERENCES address(id)
);

CREATE TABLE order_product (
    id SERIAL PRIMARY KEY,
    orderId BIGINT,
    productId BIGINT,
    quantity INT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES order_table(id),
    FOREIGN KEY (productId) REFERENCES product(id)
);
