DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,SKU_CODE VARCHAR(50)
	,TITLE VARCHAR(1000) NOT NULL
	,CATEGORY_ID BIGINT NOT NULL
	,VENDOR_ID BIGINT NOT NULL
	,BRAND_ID BIGINT
	,DESCRIPTION VARCHAR(1000)
	,CART_DESCRIPTION VARCHAR(1000)
	,SHORT_DESCRIPTION VARCHAR(1000)
	,LONG_DESCRIPTION VARCHAR(1000)
	,URL_THUMB VARCHAR(250)
	,URL_IMG VARCHAR(250)
	,WEIGHT FLOAT
	,QUANTITY INT NOT NULL
	,BASE_PRICE FLOAT
	,DISCOUNT_PRICE FLOAT
  ,IS_OFFER BOOLEAN DEFAULT FALSE NOT NULL
  ,IS_RECOMMENDED BOOLEAN DEFAULT FALSE NOT NULL
  ,IS_ACTIVE BOOLEAN DEFAULT TRUE NOT NULL
	,CR_USER_ID BIGINT NOT NULL
	,CR_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
	,LM_USER_ID BIGINT
	,LM_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	,PRIMARY KEY (ID)
);

ALTER TABLE PRODUCTS
  ADD CONSTRAINT FK_PRODUCTS_BRANDS_BRAND_ID FOREIGN KEY (BRAND_ID) REFERENCES BRANDS;

ALTER TABLE PRODUCTS
  ADD CONSTRAINT FK_PRODUCTS_CATEGORIES_CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES;

ALTER TABLE PRODUCTS
  ADD CONSTRAINT FK_PRODUCTS_USERS_CR_USER_ID FOREIGN KEY (CR_USER_ID) REFERENCES USERS;

ALTER TABLE PRODUCTS
  ADD CONSTRAINT FK_PRODUCTS_USERS_LM_USER_ID FOREIGN KEY (LM_USER_ID) REFERENCES USERS;

ALTER TABLE PRODUCTS
  ADD CONSTRAINT FK_PRODUCTS_VENDORS_VENDOR_ID FOREIGN KEY (VENDOR_ID) REFERENCES VENDORS;