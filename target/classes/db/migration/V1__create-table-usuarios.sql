create table usuarios (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique,
    username varchar(255) not null unique,
    password varchar(255) not null,
    active boolean not null
);

