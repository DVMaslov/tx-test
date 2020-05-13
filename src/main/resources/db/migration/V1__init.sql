create table product(
    id bigserial primary key,
    name varchar(100) not null
);

insert into product(name)
values ('apple'), ('orange'), ('banana');