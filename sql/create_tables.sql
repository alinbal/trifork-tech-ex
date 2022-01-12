create table employees
(
    id         bigserial
        primary key,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255)
);

INSERT INTO public.employees (id, email, first_name, last_name) VALUES (1, 'admin@gmail.com', 'admin1', 'admin2');
INSERT INTO public.employees (id, email, first_name, last_name) VALUES (2, 'admin@gmail.com', 'user1FirstName', 'user1LastName');
