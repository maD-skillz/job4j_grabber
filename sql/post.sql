create table if not exists post (
	id serial primary key,
	name text,
	link text,
	created timestamp
);