INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (1, NULL, TRUE, 1001000001, 'Photography', 'Photography tools & Accessories', TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (2, NULL, TRUE, 1002000001, 'Computers', 'Computers and spares',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (3, NULL, TRUE, 1003000001, 'Pro Video', 'Professional Video Equipment',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (4, NULL, TRUE, 1004000001, 'Lighting', 'Lighting Equipment',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (5, NULL, TRUE, 1005000001, 'Pro Audio', 'Professional Audio Equipment',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (6, 1, FALSE, 1001000101, 'Digital Cameras', 'Digital Cameras and Accessories',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (7, 1, FALSE, 1001000201, 'Lenses', 'Lenses and Camera Control',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, IS_GROUP, CODE, NAME, DESCRIPTION, IS_ACTIVE, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (8, 1, FALSE, 1001000301, 'Tripods', 'Supporting Systems',TRUE, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (14, NULL, '1006000001', 'Ноутбуки и компьютеры', NULL, NULL, 1, 1, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (15, 14, '1006001001', 'Ноутбуки', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/69/69532.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (16, 15, '1006001101', 'Для несложных задач', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/114/114266.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (17, 15, '1006001201', 'Универсальные', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/114/114278.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (18, 15, '1006001301', 'Для бизнеса', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/114/114290.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (19, 15, '00000019', 'Геймерские ноутбуки', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/113/113920.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (20, 15, '00000020', 'Ноутбуки с SSD', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/114/114314.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (21, 15, '00000021', 'Ноутбуки с Windows', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/115/115013.120x150.jp', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (22, 14, '1006000201', 'Планшеты', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/69/69546.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (23, 22, '00000023', 'Бюджетные', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/95/95085.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (24, 22, '00000024', 'Трансформеры', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/95/95099.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (25, NULL, '00000025', 'Товары для дома', NULL, NULL, 1, 1, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (26, 25, '00000026', 'Домашний текстиль', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/112/112058.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (27, 26, '00000027', 'Матрасы', NULL, 'https://i2.rozetka.ua/goods/portal/constructors/units/groups/55/55450.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (28, 26, '00000028', 'Одеяла', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/55/55750.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (29, 25, '00000029', 'Мебель', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/45/45843.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (30, 29, '00000030', 'Обеденные столы', NULL, 'https://i1.rozetka.ua/goods/portal/constructors/units/groups/124/124590.120x150.jpg', 1, 0, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (31, NULL, '00000031', 'Спорт и увлечения', NULL, NULL, 1, 1, CURRENT_TIMESTAMP, 1, NULL, NULL);

INSERT INTO CATEGORIES (ID, PARENT_ID, CODE, NAME, DESCRIPTION, IMG_URL, IS_ACTIVE, IS_GROUP, CR_TIME, CR_USER_ID, LM_TIME, LM_USER_ID)
VALUES (32, NULL, '00000032', 'Одежда, обувь и украшения', NULL, NULL, 1, 1, CURRENT_TIMESTAMP, 1, NULL, NULL);