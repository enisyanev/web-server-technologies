CREATE TABLE CUSTOMER
(
    ID         UUID PRIMARY KEY,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME  VARCHAR(255) NOT NULL,
    EMAIL      VARCHAR(255) NOT NULL
);

CREATE TABLE ACCOUNT
(
    ID          UUID PRIMARY KEY,
    CURRENCY    CHAR(3) NOT NULL,
    CUSTOMER_ID UUID NOT NULL,
    CONSTRAINT FK_ACCOUNT_CUSTOMER FOREIGN KEY (customer_id) REFERENCES CUSTOMER (id) ON DELETE CASCADE
);

CREATE TABLE TRANSACTION
(
    id          UUID PRIMARY KEY,
    CUSTOMER_ID UUID           NOT NULL,
    ACCOUNT_ID  UUID           NOT NULL,
    AMOUNT      DECIMAL(19, 4) NOT NULL,
    INSERT_TIME TIMESTAMP WITH TIME ZONE NOT NULL,
    CURRENCY    CHAR(3)        NOT NULL,
    CONSTRAINT FK_TRANSACTION_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES Customer (ID) ON DELETE CASCADE,
    CONSTRAINT FK_TRANSACTION_ACCOUNT FOREIGN KEY (ACCOUNT_ID) REFERENCES Account (ID) ON DELETE CASCADE
);


CREATE TABLE TRANSACTION_DETAILS
(
    ID      UUID PRIMARY KEY,
    TYPE    VARCHAR(255) NOT NULL,
    MESSAGE VARCHAR(255) NOT NULL,
    SOURCE  VARCHAR(255) NOT NULL,
    CONSTRAINT FK_TRANSACTION_DETAILS FOREIGN KEY (ID) REFERENCES Transaction (ID) ON DELETE CASCADE
);