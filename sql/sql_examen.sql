CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values('1', 'Cola');
insert into company (id, name) values('2', 'Pepsi');
insert into company (id, name) values('3', 'MacD');
insert into company (id, name) values('4', 'Ford');
insert into company (id, name) values('5', 'Tesla');

insert into person(id, name, company_id) values('1', 'Mike', '1');
insert into person(id, name, company_id) values('2', 'Stan', '2');
insert into person(id, name, company_id) values('3', 'Bob', '3');
insert into person(id, name, company_id) values('4', 'Rob', '4');
insert into person(id, name) values('5', 'Steve');
insert into person(id, name, company_id) values('6', 'Torb', '2');
insert into person(id, name, company_id) values('7', 'Rein', '2');
insert into person(id, name, company_id) values('8', 'Lucio', '4');
insert into person(id, name, company_id) values('9', 'Zen', '4');
insert into person(id, name, company_id) values('10', 'Hammond', '4');

select
c.name as company,
p.name as person,
p.id = 5 and p.company_id is null
from company c
right join person as p
on (c.id = p.company_id);

with takeMax as (
select
com.name,
count(per) as c
from company as com
join person as per
on (com.id = per.company_id)
group by com.name
)
select * from takeMax where takeMax.c = (select max(takeMax.c) from takeMax)

	