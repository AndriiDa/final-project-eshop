DROP TABLE IF EXISTS ORDERS;

CREATE TABLE ORDERS (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,USER_ID BIGINT NOT NULL
  ,ORDER_NUM VARCHAR(20)
	,ORDER_DATE DATE DEFAULT CURRENT_DATE
	,REQUIRED_DATE DATE DEFAULT CURRENT_DATE
	,SHIPPED_DATE DATE DEFAULT CURRENT_DATE
  ,RECEIPT_NAME VARCHAR(100)
  ,RECEIPT_PHONE VARCHAR(20)
  ,RECEIPT_ADDRESS_ID BIGINT NOT NULL
  ,TRACKING_NUMBER VARCHAR(50)
  ,NOTES VARCHAR(1000)
	,TOTAL FLOAT
	,STATUS VARCHAR(255)
	,CR_USER_ID BIGINT NOT NULL
	,CR_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
	,LM_USER_ID BIGINT NOT NULL
	,LM_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
	,PRIMARY KEY (ID)
);

ALTER TABLE ORDERS
  ADD CONSTRAINT FK_ORDERS_USERS_ADDRESS_ID FOREIGN KEY (USER_ID) REFERENCES USERS;

ALTER TABLE ORDERS
  ADD CONSTRAINT FK_ORDERS_USERS_CR_USER_ID FOREIGN KEY (CR_USER_ID) REFERENCES USERS;

ALTER TABLE ORDERS
  ADD CONSTRAINT FK_ORDERS_USERS_LM_USER_ID FOREIGN KEY (LM_USER_ID) REFERENCES USERS;

ALTER TABLE ORDERS
  ADD CONSTRAINT FK_ORDERS_ADDRESSES_RECEIPT_ADDRESS_ID FOREIGN KEY (RECEIPT_ADDRESS_ID) REFERENCES ADDRESSES;
