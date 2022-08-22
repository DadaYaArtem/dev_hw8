INSERT INTO manufacturers (name) VALUES
('CleverMind'),
('ILovePDF'),
('Biomedical something');


INSERT INTO products (name, price) VALUES
('All4U', 777),
('IT-Pedia', 797),
('WorkBook', 99),
('Musical Album', 121),
('K.M.D.', 1943),
('Candle In The Wind', 2020);

INSERT INTO roles (name) VALUES
('Admin'),
('User');

INSERT INTO users (email, firstname, lastname, password) VALUES
('popusk@gmail.com', 'Bohdan', 'Holovchenko', 123);

INSERT INTO users (email, firstname, lastname, password) VALUES
('somebody@ukr.net', 'Mykola', 'Molodets', 123);

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1), (2, 2);