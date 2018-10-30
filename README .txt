All libraries are in lib folder.
Data bse SQL =>

DROP DATABASE LC;
CREATE DATABASE LC;
USE LC;
CREATE TABLE student(
	sid VARCHAR(10) PRIMARY KEY,
	name VARCHAR(100),
	gender VARCHAR(10),
	address VARCHAR(200),
	tele VARCHAR(15),
	dob date,
	nic VARCHAR(15)
);

CREATE TABLE gurdian(
	gid VARCHAR(10) PRIMARY KEY,
	sid VARCHAR(10),
	name VARCHAR(100),
	address VARCHAR(200),
	tele VARCHAR(200),
	CONSTRAINT FOREIGN KEY (sid) REFERENCES student(sid)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE course(
	cid VARCHAR(10) PRIMARY KEY,
	name VARCHAR(100),
	fee DECIMAL(10,2),
	duration VARCHAR(15)
);


CREATE TABLE batch(
	bid VARCHAR(10) PRIMARY KEY,
	name Varchar(100),
	cid VARCHAR(10),
	CONSTRAINT FOREIGN KEY (cid) REFERENCES course(cid)
	on delete cascade on update cascade
);

CREATE TABLE regestration(
	rid VARCHAR(10) PRIMARY KEY,
	sid VARCHAR(10),
	bid VARCHAR(10),
	reg_fee DECIMAL(10,2),
	date DATE,
	CONSTRAINT FOREIGN KEY(bid) REFERENCES batch(bid)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(sid) REFERENCES student(sid)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE payment(
	pid VARCHAR(10) PRIMARY KEY,
	rid VARCHAR(10),
	cid VARCHAR(10),
	amount DECIMAL(10,2),
	date DATE,
	CONSTRAINT FOREIGN KEY (rid) REFERENCES regestration(rid)
	on delete cascade on update cascade,
	CONSTRAINT FOREIGN KEY (cid) REFERENCES course(cid)
	on delete cascade on update cascade
);



CREATE TABLE exam(
	eid VARCHAR(10) PRIMARY KEY,
	rid VARCHAR(10),
	cid VARCHAR(10),
	date date,
	marks DECIMAL(6,2),
	pass_fail tinyint(1),
	CONSTRAINT FOREIGN KEY (rid) REFERENCES regestration(rid)
	on delete cascade on update cascade,
	CONSTRAINT FOREIGN KEY (cid) REFERENCES course(cid)
	on delete cascade on update cascade
);

CREATE TABLE attendence(
	aid VARCHAR(10) PRIMARY KEY,
	rid VARCHAR(10),
	cid VARCHAR(10),
	attendence tinyint(1),
	CONSTRAINT FOREIGN KEY (rid) REFERENCES regestration(rid)
	on delete cascade on update cascade,
	CONSTRAINT FOREIGN KEY (cid) REFERENCES course(cid)
	on delete cascade on update cascade
);