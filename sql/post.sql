create table if not exists post (
	id serial primary key,
	title text,
	link text unique,
	descriprion text,
	created timestamp
);