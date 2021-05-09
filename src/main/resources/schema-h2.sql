--Create Table
DROP TABLE IF EXISTS ADDRESSES;
CREATE TABLE ADDRESSES
        (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        address1 VARCHAR2(100) NOT NULL,
        address2 VARCHAR2(100) NOT NULL,
        city VARCHAR2(100) NOT NULL,
        state VARCHAR2(100) NOT NULL,
        zipcode  VARCHAR2(10) NOT NULL,
        country  VARCHAR2(100) NOT NULL,
        );

        