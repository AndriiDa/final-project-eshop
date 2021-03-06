DROP TABLE IF EXISTS ADDRESSES;

CREATE TABLE ADDRESSES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,COUNTRY VARCHAR(100) NULL
	,STATE VARCHAR(100) NULL
	,CITY VARCHAR(100) NULL
	,STREET VARCHAR(100) NULL
	,ZIP_CODE VARCHAR(8) NULL
	,ADDRESS_LINE VARCHAR(250) NOT NULL
	,PRIMARY KEY (ID)
);
