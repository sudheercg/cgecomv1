SELECT * FROM ecomschema.product;
ALTER TABLE ecomschema.product DROP COLUMN stock;

CREATE TABLE ecomschema.users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

INSERT INTO ecomschema.users (username, password, role)
VALUES
('admin', 'admin123', 'ADMIN'),
('user', 'user123', 'USER');



CREATE TABLE ecomschema.orders (
    id int AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    total_price DECIMAL(10, 2),
    order_status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE ecomschema.order_items (
    id int AUTO_INCREMENT PRIMARY KEY,
    order_id int,
    product_id int,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);