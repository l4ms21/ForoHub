create table topicos (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    message text not null,
    date datetime not null,
    status enum('ACTIVO', 'INACTIVO', 'RESUELTO') not null,
    author_id bigint,
    course varchar(255) not null,
    active boolean not null,
    foreign key (author_id) references usuarios(id)
);

