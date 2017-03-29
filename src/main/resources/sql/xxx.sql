
drop table authorities;
drop table users;
create table users (
	username varchar2(50 char) primary key,
	password varchar2(50) not null,
	enabled	 number(1),
	xxx		 varchar2(100 char)
);

create table authorities (
	username varchar2(50 char) not null,
	authority varchar2(50 char) not null,
	xxx			varchar2(100 char),
	constraint fk_authorities_users foreign key (username) references users(username)
);

create unique index ix_auth_username on authorities (username, authority);

insert into users values ('admin', '1234', 1, 'xxx');
insert into users values ('admin2', '1234', 1, 'xxx');
insert into authorities values ('admin', 'ROLE_ADMIN', 'xxx');
insert into authorities values ('admin2', 'ROLE_ADMIN', 'xxx');
commit;

select * from users;
select * from authorities;

update users set enabled=1 where username = 'admin2';
  
