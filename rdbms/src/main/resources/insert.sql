--liquibase formatted sql

--changeset aleks:2

insert into account1(id, amount, version)
values (1, 1000, 1),
       (2, 2000, 1)