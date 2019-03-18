create table employees(
employeeid serial primary key,
fullname varchar(80),
birthdate varchar(80),
email varchar(80),
companyid int references companies(companyid) on delete cascade,
namecompany varchar(80)
);

create table companies(
companyid serial primary key,
name varchar(80),
nip bigint,
address varchar(80),
phone bigint
);

