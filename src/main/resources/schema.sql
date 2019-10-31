DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ADDRESSES;
DROP TABLE IF EXISTS CATEGORY_PROPERTY_VALUES;
DROP TABLE IF EXISTS CATEGORIES;
DROP TABLE IF EXISTS PROPERTY_VALUES;
DROP TABLE IF EXISTS PROPERTIES;

CREATE TABLE PROPERTIES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,NAME VARCHAR(100) NOT NULL
  ,DESCRIPTION VARCHAR(1000)
	,PRIMARY KEY (ID)
	);

CREATE TABLE PROPERTY_VALUES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,NAME VARCHAR(100) NOT NULL
  ,DESCRIPTION VARCHAR(1000)
	,PROPERTY_ID BIGINT NOT NULL
	,PRIMARY KEY (ID)
	);

CREATE TABLE CATEGORIES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,CODE VARCHAR(50)
	,CR_TIME TIMESTAMP NOT NULL
	,DESCRIPTION VARCHAR(1000)
	,IMG_URL VARCHAR(100)
	,IS_ACTIVE BOOLEAN DEFAULT TRUE NOT NULL
	,IS_GROUP BOOLEAN NOT NULL
	,LM_TIME TIMESTAMP
	,NAME VARCHAR(200) NOT NULL
	,PARENT_ID BIGINT
	,CR_USER_ID BIGINT NOT NULL
	,LM_USER_ID BIGINT
	,PRIMARY KEY (ID)
);

CREATE TABLE CATEGORY_PROPERTY_VALUES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,SEQUENCE INTEGER NOT NULL
	,CATEGORY_ID BIGINT NOT NULL
	,PROPERTY_VALUE_ID BIGINT NOT NULL
	,PRIMARY KEY (ID)
);

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
	,PRIMARY KEY (ID)
);

ALTER TABLE CATEGORIES ADD CONSTRAINT FK_CATEGORIES_CATEGORIES_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES CATEGORIES;
ALTER TABLE CATEGORIES ADD CONSTRAINT FK_CATEGORIES_USERS_CR_USER_ID FOREIGN KEY (CR_USER_ID) REFERENCES USERS;
ALTER TABLE CATEGORIES ADD CONSTRAINT FK_CATEGORIES_USERS_LM_USER_ID FOREIGN KEY (LM_USER_ID) REFERENCES USERS;

ALTER TABLE CATEGORY_PROPERTY_VALUES ADD CONSTRAINT FK_CATEGORY_PROPERTY_VALUES_CATEGORIES_CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES;
ALTER TABLE CATEGORY_PROPERTY_VALUES ADD CONSTRAINT FK_CATEGORY_PROPERTY_VALUES_CATEGORIES_PROPERTY_VALUE_ID FOREIGN KEY (PROPERTY_VALUE_ID) REFERENCES PROPERTY_VALUES;