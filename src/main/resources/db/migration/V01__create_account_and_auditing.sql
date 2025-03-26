CREATE TABLE account (
    account_number  INT PRIMARY KEY NOT NULL,
    balance         DECIMAL(10, 2) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE,
    updated_at      TIMESTAMP WITH TIME ZONE
);