DROP TABLE IF EXISTS PROPERTY_VALUES;

CREATE TABLE PROPERTY_VALUES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,NAME VARCHAR(100) NOT NULL
  ,DESCRIPTION VARCHAR(1000)
	,PROPERTY_ID BIGINT NOT NULL
	,PRIMARY KEY (ID)
	);

ALTER TABLE PROPERTY_VALUES
  DROP CONSTRAINT IF EXISTS IX_PROPERTY_VALUES_PROPERTY_ID_NAME;

ALTER TABLE PROPERTY_VALUES
  ADD CONSTRAINT IX_PROPERTY_VALUES_PROPERTY_ID_NAME
    UNIQUE (PROPERTY_ID,	NAME);

ALTER TABLE PROPERTY_VALUES
  ADD CONSTRAINT FK_PROPERTY_VALUES_PROPERTIES_PROPERTY_ID
    FOREIGN KEY (PROPERTY_ID) REFERENCES PROPERTIES;