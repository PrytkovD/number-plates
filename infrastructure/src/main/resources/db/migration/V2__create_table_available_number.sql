create table available_number
(
    number integer primary key
);

insert into available_number
select *
from generate_series(0, 1727999);