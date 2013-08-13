DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
	userId varchar(12) NOT NULL,
	password varchar(12) NOT NULL,
	name varchar(20) NOT NULL,
	email varchar(50),
	PRIMARY KEY (userId)
);

INSERT INTO USERS VALUES('admin','password', '자바지기', 'admin@javajigi.net');

DROP TABLE IF EXISTS BBS;

CREATE TABLE BBS (
	bbsIdx varchar(20) NOT NULL,
	userId varchar(20) NOT NULL,
	bbsPassword varchar(10) NOT NULL,
	subject varchar(255) NOT NULL,
	content varchar(512) NOT NULL,
	writeDate varchar(20),
	PRIMARY KEY (bbsIdx)
);

INSERT INTO BBS VALUES('1', 'jinwon','1111', 'Bbs subject', 'content', '2013-08-13 00:00:00');