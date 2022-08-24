INSERT INTO manufacturers (name) VALUES
('GoodLogic'),
('DontCare'),
('Papich');


INSERT INTO products (name, price) VALUES
('Jolybells', 777),
('Moisha', 797),
('SomeProduct', 99),
('Starry night', 121),
('The scream', 1943),
('Karmagedon', 2020);

INSERT INTO roles (name) VALUES
('Admin'),
('User');

INSERT INTO users (email, firstname, lastname, password) VALUES
('admin@gmail.com', 'AdnimFname', 'AdminLname', '$2a$12$L6shB9R49VnoKCLUcKFCbO0oJGzikQYXlZQUk3Qm/ePEWyvFrdkmC');

INSERT INTO users (email, firstname, lastname, password) VALUES
('user@ukr.net', 'UserFname', 'UserLname', '$2a$12$L6shB9R49VnoKCLUcKFCbO0oJGzikQYXlZQUk3Qm/ePEWyvFrdkmC');

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1), (2, 2);