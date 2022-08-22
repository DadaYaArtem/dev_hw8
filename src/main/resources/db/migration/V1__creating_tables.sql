create table if not exists products(
id serial primary key not null,
name varchar(100) not null,
price decimal not null);

create table if not exists manufacturers(
id serial primary key not null,
name varchar(100) not null);

create table if not exists roles(
id serial primary key not null,
name varchar(100) not null);

create table if not exists users(
id serial primary key not null,
email varchar(50) not null,
firstName varchar(50) not null,
lastName varchar(50) not null,
password varchar(50) not null);

create table if not exists users_roles(
user_id integer not null,
role_id integer not null,
foreign key (user_id) references users(id) on delete cascade,
foreign key (role_id) references roles(id) on delete cascade
);