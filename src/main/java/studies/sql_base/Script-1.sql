create schema netology;

create table netology.PERSONS
(
  name varchar not null,
  surname varchar not null,
  age int not null ,
  phone_number varchar not null,
  city_of_living varchar not null,
  PRIMARY KEY (name, surname, age)
);

insert into netology.PERSONS
values ('IVAN', 'PETROV', 25, +7231234567, 'KIROV');

insert into netology.PERSONS
values ('PETYA', 'VETROV', 67, +77654321546, 'MOSCOW');

insert into netology.PERSONS
values ('STEPAN', 'HITROV', 43, +79876544312, 'MOSCOW');

insert into netology.PERSONS
values ('VLAD', 'VIVALDI', 42, +72374658934, 'OREL');

insert into netology.PERSONS
values ('INNA', 'RIZHIK', 38, +79876543212, 'MOSCOW');