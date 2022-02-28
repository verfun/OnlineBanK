INSERT INTO client (id, first_name, last_name) VALUES (1, 'alex', 'leblanc');
INSERT INTO client (id, first_name, last_name) VALUES (2, 'mary', 'stephane');


INSERT INTO account (id, balance, client_id) VALUES (1, 1830, 1);
INSERT INTO account (id, balance, client_id) VALUES (2, 1200, 2);


INSERT INTO transaction (post_balance, amount ,account_id, date, type, status) VALUES (1300, 100, 1,CURRENT_TIMESTAMP,'DEPOSIT', 'Accepted');
INSERT INTO transaction (post_balance, amount ,account_id, date, type, status) VALUES (1430, 130, 1, CURRENT_TIMESTAMP,'DEPOSIT', 'Accepted');
INSERT INTO transaction (post_balance, amount ,account_id, date, type, status) VALUES (1830, 400, 1, CURRENT_TIMESTAMP,'DEPOSIT', 'Accepted');
INSERT INTO transaction (post_balance, amount ,account_id, date, type, status) VALUES (1430, 400, 1, CURRENT_TIMESTAMP,'WITHDRAWAL', 'Accepted');
INSERT INTO transaction (post_balance, amount ,account_id, date, type, status) VALUES (1830, 400, 1, CURRENT_TIMESTAMP,'DEPOSIT', 'Accepted');
INSERT INTO transaction (post_balance, amount ,account_id, date, type, status) VALUES (1830, 400, 2, CURRENT_TIMESTAMP,'DEPOSIT', 'Accepted');



