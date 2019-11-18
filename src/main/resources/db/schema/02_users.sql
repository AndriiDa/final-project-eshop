DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,BIRTH_DATE DATE
	,EMAIL VARCHAR(100) NOT NULL
	,EMAIL_VERIFIED BOOLEAN NOT NULL
	,FIRST_NAME VARCHAR(100) NOT NULL
	,GENDER VARCHAR(255)
	,IS_ACTIVE BOOLEAN NOT NULL
	,LAST_NAME VARCHAR(100) NOT NULL
	,LOGIN_NAME VARCHAR(50) NOT NULL
	,LOGIN_PASSWORD VARCHAR(50)
	,MIDDLE_NAME VARCHAR(100)
	,PHONE_NUMBER VARCHAR(50)
	,ROLE VARCHAR(255) NOT NULL
	,VERIFICATION_CODE VARCHAR(20)
	,ADDRESS_ID BIGINT
	,CR_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
	,PRIMARY KEY (ID)
);

ALTER TABLE USERS
	ADD CONSTRAINT FK_USERS_ADDRESSES_ADDRESS_ID FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES;