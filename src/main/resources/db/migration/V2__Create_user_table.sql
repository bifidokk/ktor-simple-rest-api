create table "user" (
    id serial PRIMARY KEY,
    email varchar(255) not null,
    created_at timestamp not null
);