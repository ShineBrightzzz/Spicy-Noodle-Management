CREATE TABLE employees (
    id VARCHAR(10) PRIMARY KEY,
    name NVARCHAR(100),
    address NVARCHAR(255),
    gender NVARCHAR(10),
    date_of_birth DATE,
    phone NVARCHAR(20)
);

CREATE TABLE customers (
    id VARCHAR(10) PRIMARY KEY,
    name NVARCHAR(100),
    phone NVARCHAR(20)
);

CREATE TABLE suppliers (
    id VARCHAR(10) PRIMARY KEY,
    name NVARCHAR(100),
    address NVARCHAR(255),
    phone NVARCHAR(20)
);

CREATE TABLE product_types (
    id VARCHAR(10) PRIMARY KEY,
    name NVARCHAR(100)
);

CREATE TABLE products (
    id VARCHAR(10) PRIMARY KEY,
    name NVARCHAR(100),
    type_id VARCHAR(10),
    import_price DECIMAL(18, 2),
    sale_price DECIMAL(18, 2),
    quantity INT,
    image NVARCHAR(255),
    FOREIGN KEY (type_id) REFERENCES product_types(id)
);

CREATE TABLE import_invoices (
    id VARCHAR(10) PRIMARY KEY,
    import_date DATE,
    employee_id VARCHAR(10),
    supplier_id VARCHAR(10),
    total_amount DECIMAL(18, 2),
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE import_invoice_details (
    invoice_id VARCHAR(10),
    product_id VARCHAR(10),
    quantity INT,
    unit_price DECIMAL(18, 2),
    discount DECIMAL(5, 2),
    amount DECIMAL(18, 2),
    PRIMARY KEY (invoice_id, product_id),
    FOREIGN KEY (invoice_id) REFERENCES import_invoices(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE sale_invoices (
    id VARCHAR(10) PRIMARY KEY,
    sale_date DATE,
    employee_id VARCHAR(10),
    customer_id VARCHAR(10),
    total_amount DECIMAL(18, 2),
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE sale_invoice_details (
    invoice_id VARCHAR(10),
    product_id VARCHAR(10),
    quantity INT,
    unit_price DECIMAL(18, 2),
    discount DECIMAL(5, 2),
    amount DECIMAL(18, 2),
    PRIMARY KEY (invoice_id, product_id),
    FOREIGN KEY (invoice_id) REFERENCES sale_invoices(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
