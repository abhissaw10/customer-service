create table if not exists customer(
    customer_id varchar(50) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    primary key (customer_id)
);