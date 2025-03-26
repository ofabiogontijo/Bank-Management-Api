CREATE TABLE transaction (
    id                           VARCHAR(50) PRIMARY KEY,
    account_id                   INT NOT NULL,
    payment_method               VARCHAR(50) NOT NULL,
    amount                       DECIMAL(10, 2) NOT NULL,
    created_at                   TIMESTAMP WITH TIME ZONE,
    updated_at                   TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (account_id)     REFERENCES account (account_number)
);