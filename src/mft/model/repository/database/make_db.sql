salam
create table person_tbl
(
    id     number primary key,
    name   nvarchar2(30),
    family nvarchar2(30)
);
create sequence person_seq start with 1 increment by 1;

create table book_tbl
(
    id     number primary key,
    title  nvarchar2(30),
    author nvarchar2(30)
);
create sequence book_seq start with 1 increment by 1;

create table user_tbl
(
    id       number primary key,
    username nvarchar2(30) UNIQUE ,
    password nvarchar2(30),
    active   number(1)
);
create sequence user_seq start with 1 increment by 1;

create table receipt_tbl
(
    id          number primary key,
    amount      int,
    description nvarchar2(100)
);
create sequence receipt_seq start with 1 increment by 1;

create table stuff_tbl
(
    id          number primary key,
    name        nvarchar2(30),
    brand       nvarchar2(30),
    group_title nvarchar2(30)
);
create sequence stuff_seq start with 1 increment by 1;

create table borrow_tbl
(
    id               number primary key,
    person_id references person_tbl,
    book_id references book_tbl,
    borrow_TimeStamp timestamp,
    return_TimeStamp timestamp
);
create sequence borrow_seq start with 1 increment by 1;

create view borrow_report as
select P.ID     as person_id,
       P.NAME   as person_name,
       p.FAMILY as person_family,
       B.ID     as book_id,
       B.TITLE  as book_title,
       B.AUTHOR as book_author,
       BR.ID    as borrow_id,
       BR.BORROW_TIMESTAMP,
       BR.RETURN_TIMESTAMP
from BORROW_TBL BR,
     PERSON_TBL P,
     BOOK_TBL B
where BR.PERSON_ID = P.ID
  and BR.BOOK_ID = B.ID;