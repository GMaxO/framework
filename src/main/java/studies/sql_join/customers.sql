create schema netology;

create table netology.customers(
                                 id int primary key auto_increment,
                                 name varchar(255) not null,
                                 surname varchar(255) not null,
                                 age int not null,
                                 phone_number varchar(255)
);

insert into netology.customers (name, surname, age, phone_number)
values ('Alexey', 'Petrov', 9, '111-999'),
       ('Alena', 'Vasilyeva', 33, '222-888'),
       ('Svetlana', 'Sharapova', 28, '333-777'),
       ('aleXEY', 'Suvorov', 30, '444-666'),
       ('Darya', 'Semenova', 26, '555-000');