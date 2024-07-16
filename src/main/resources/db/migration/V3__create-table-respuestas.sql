create table respuestas (
    id bigint auto_increment primary key,
    creationdate datetime not null,
    solution text not null,
    author bigint,
    topico bigint,
    active boolean not null,
    foreign key (author) references usuarios(id),
    foreign key (topico) references topicos(id)
);