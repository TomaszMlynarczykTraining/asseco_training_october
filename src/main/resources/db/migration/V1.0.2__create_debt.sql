CREATE TABLE IF NOT EXISTS `debt` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `amount` varchar(50),
    `currency` varchar(50),
    `is_overdue` varchar(1)

);

ALTER  TABLE  client ADD  CONSTRAINT  client_x_debt_const  FOREIGN  KEY  (debt_id)
REFERENCES  debt  (id);


INSERT INTO debt (id,amount, currency,is_overdue)
VALUES ('1', '2456', 'PLN', '1');


INSERT INTO debt (id,amount, currency,  is_overdue)
VALUES ('2', '5256', 'CHF', '0');


UPDATE client
SET debt_id=1
WHERE id=1;

UPDATE client
SET debt_id=2
WHERE id=3;
