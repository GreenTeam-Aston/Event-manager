insert into values (name) values ('Gold');
insert into values (name) values ('White');
insert into values (name) values ('Black');
insert into values (name) values ('Silver');
insert into values (name) values ('Bronze');
insert into values (name) values ('chicken');
insert into values (name) values ('pork');
insert into values (name) values ('beef');
insert into values (name) values ('for one');
insert into values (name) values ('kilogram');
insert into values (name) values ('liter');

insert into parameters (name) values ('color_bread');
insert into parameters (name) values ('alcohol');
insert into parameters (name) values ('meat');
insert into parameters (name) values ('vegetables');
insert into parameters (name) values ('union');

insert into parameters_value (parameter_id, value_id) values (1,2);
insert into parameters_value (parameter_id, value_id) values (1,3);
insert into parameters_value (parameter_id, value_id) values (2,1);
insert into parameters_value (parameter_id, value_id) values (2,5);
insert into parameters_value (parameter_id, value_id) values (3,6);
insert into parameters_value (parameter_id, value_id) values (3,7);
insert into parameters_value (parameter_id, value_id) values (3,8);
insert into parameters_value (parameter_id, value_id) values (4,10);
insert into parameters_value (parameter_id, value_id) values (5,9);
insert into parameters_value (parameter_id, value_id) values (5,10);
insert into parameters_value (parameter_id, value_id) values (5,11);

insert into products (title, description, image, price, quantity) values ('bread','russian bread', 'https://image/', 45, 200);
insert into products (title, description, image, price, quantity) values ('wine','Jack Daniels', 'https://image/', 1000, 5);
insert into products (title, description, image, price, quantity) values ('whiskey','Nice wine', 'https://image/', 45, 200);
insert into products (title, description, image, price, quantity) values ('vodka','Russian', 'https://image/', 350, 45);
insert into products (title, description, image, price, quantity) values ('shashlik','Russian', 'https://image/', 400, 120);
insert into products (title, description, image, price, quantity) values ('tomato','Krasdar', 'https://image/', 35, 220);
insert into products (title, description, image, price, quantity) values ('cucumber','Krasnodar', 'https://image/', 35, 120);
insert into products (title, description, image, price, quantity) values ('potato','Belarus', 'https://image/', 45, 120);
insert into products (title, description, image, price, quantity) values ('onion','Russian', 'https://image/', 30, 170);

insert into product_parameters (product_id, parameter_id) values (1,1);
insert into product_parameters (product_id, parameter_id) values (2,2);
insert into product_parameters (product_id, parameter_id) values (3,2);
insert into product_parameters (product_id, parameter_id) values (4,2);
insert into product_parameters (product_id, parameter_id) values (5,3);
insert into product_parameters (product_id, parameter_id) values (6,4);
insert into product_parameters (product_id, parameter_id) values (7,4);
insert into product_parameters (product_id, parameter_id) values (8,4);
insert into product_parameters (product_id, parameter_id) values (9,4);



