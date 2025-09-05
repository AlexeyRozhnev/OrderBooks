create schema if not exists library;

create table if not exists library.book
(
    id       serial primary key,
    title  varchar(255) not null check (length(trim(title)) >= 1),
    author varchar(255) not null check (length(trim(author)) >= 3),
    isbn   varchar(20)  not null unique
);

create table if not exists library.client
(
    id          serial primary key,
    full_name varchar(255) not null check (length(trim(full_name)) >= 3),
    birthdate date not null
);

create table if not exists library.order
(
    id           serial primary key,
    client_id    int not null references library.client(id) on delete cascade,
    book_id      int not null references library.book(id) on delete cascade,
    order_date  date not null default current_date
);
