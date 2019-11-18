DROP TABLE IF EXISTS PROPERTIES;

CREATE TABLE PROPERTIES (
	ID BIGINT GENERATED BY DEFAULT AS IDENTITY
	,NAME VARCHAR(100) NOT NULL
    ,DESCRIPTION VARCHAR(1000)
	,PRIMARY KEY (ID)
	);

ALTER TABLE PROPERTIES
  DROP CONSTRAINT	IF EXISTS IX_PROPERTIES_NAME;

ALTER TABLE PROPERTIES
  ADD CONSTRAINT IX_PROPERTIES_NAME	UNIQUE (NAME);