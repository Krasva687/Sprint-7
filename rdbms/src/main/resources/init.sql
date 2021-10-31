--liquibase formatted sql

--changeset aleks:1

create table account1
(
    id bigserial constraint account_pk primary key,
    amount int,
    version int
);



