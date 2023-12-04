create table netology.orders(
                                  id int primary key auto_increment,
                                  date varchar(255) not null,
                                  customer_id int references netology.customers (id),
                                  product_name varchar(255) not null,
                                  amount int
);

insert into netology.orders (date, customer_id, product_name, amount)
values ('05.01.2023', 3, 'хлеб', 5),
       ('05.01.2023', 4, 'макароны', 3),
       ('05.01.2023', 4, 'рубашка', 2),
       ('05.01.2023', 1, 'тортик', 15),
       ('05.01.2023', 2, 'тортик', 7);