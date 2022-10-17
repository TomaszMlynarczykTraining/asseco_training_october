CREATE TABLE IF NOT EXISTS `loan` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `amount` varchar(50),
    `currency` varchar(50),
    `amount_of_installament` varchar(1),
    `client_id` int

);


--ALTER  TABLE  loan  CONSTRAINT  loan_x_client_con  FOREIGN  KEY  (client_id) REFERENCES  client (id);


INSERT INTO loan (id,amount, currency, amount_of_installament, client_id)
VALUES ('1', '2556', 'USD', '5', 3);


INSERT INTO loan (id,amount, currency, amount_of_installament, client_id)
VALUES ('2', '215125', 'EUR', '3', 4);