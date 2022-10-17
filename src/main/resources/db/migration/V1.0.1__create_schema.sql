CREATE TABLE IF NOT EXISTS `client` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(20),
    `last_name` varchar(50),
    `street` varchar(50),
    `phone_number` varchar(50),
    `declared_income` varchar(50),
    `currency` varchar(50),
    `debt_id` int
);


INSERT INTO client (id,first_name, last_name, street, phone_number, declared_income, currency)
VALUES ('1', 'Tomasz', 'Mlynarczyk', 'Miejska','12541556', '25566','PLN');

INSERT INTO client (id,first_name, last_name, street, phone_number, declared_income, currency)
VALUES ('2', 'Leon', 'Kennedy', 'Downing Street','15351556', '184755','USD');

INSERT INTO client (id,first_name, last_name, street, phone_number, declared_income, currency)
VALUES ('3', 'Samus', 'Aran', 'Zeebes','2423526', '234566','KRF');

INSERT INTO client (id,first_name, last_name, street, phone_number, declared_income, currency)
VALUES ('4', 'Sub', 'Zero', 'Temple','1342556', '4536','CHF');