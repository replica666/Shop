CREATE TABLE products (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         code VARCHAR(255) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(1000) NOT NULL,
                         price DOUBLE NOT NULL,
                         quantity INT NOT NULL,
                         inventory_status VARCHAR(255) NOT NULL,
                         category VARCHAR(255) NOT NULL,
                         image VARCHAR(255),
                         rating INT,
                         internal_reference VARCHAR(255) NOT NULL UNIQUE,
                         shell_id BIGINT,
                         created_at BIGINT,
                         updated_at BIGINT,
                         CONSTRAINT UK_product_code UNIQUE (code)
);

