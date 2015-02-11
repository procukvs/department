-- create database if not exists Department;
use Department;

drop table if exists Authors;
create table Authors
(idPer integer not null references Person(idPer),
 idPrn integer not null references Printing(idPrn),
 OrderA smallint,
 constraint primary key (idPer, idPrn)
) engine=InnoDB; 

drop table if exists Flow;
create table Flow
(idFl integer not null primary key, --  
 Speciality varchar(15) not null,
 Years smallint not null,
 Term char(3) not null 
) engine=InnoDB;

drop table if exists Discipline;
create table Discipline
(idDisc integer not null primary key,
 Title varchar(15) not null,
 idFl integer not null references Flow(idFlow),
 Lecture integer,
 Seminar integer,
 Computer integer,
 Control integer,
 Individ integer
) engine=InnoDB;

drop table if exists Teacher;
create table Teacher
(idPer integer not null primary key references Person(idPer) ,
 idCh integer references Chair(idCh),  
 Post varchar(15) not null,
 ScienceD varchar(15) -- ,
 -- constraint foreign key (idPer) references Person(idPer) 
) engine=InnoDB; 

drop table if exists Graduate;
create table Graduate
(idPer integer not null primary key references Person(idPer) ,
 idCh integer references Chair(idCh), 
 YearS smallint not null,
 idLead integer references Teacher(idPer)-- ,
 --  constraint  foreign key (idPer) references Person(idPer) 
) engine=InnoDB; 

drop table if exists Student;
create table Student
(idPer integer not null primary key references Person(idPer),
 idFl integer not null references Flow(idFl), 
 GroupS smallint,
 idLead integer references Teacher(idPer) -- ,
 -- constraint foreign key (idPer) references Person(idPer) 
) engine=InnoDB; 

drop table if exists Chair;
create table Chair
(idCh integer not null primary key,
 Title varchar(15) not null,
 idLead integer references Teacher(idPer)
) engine=InnoDB; 

drop table if exists Rooms;
create table Rooms
(idRoom integer not null primary key,
 isComp boolean,
 cntSt integer not null
) engine=InnoDB;

drop table if exists Assignment;
create table Assignment
(idAss integer not null primary key,
 idDisc integer not null references Discipline(idDisc),
 idPer integer not null references Teacher(idPer),
 tLean char(3) not null,
 cntLearner smallint
) engine=InnoDB;

drop table if exists Studies;
create table Studies
(idRoom integer not null references Rooms(idRoom),
 tDay char(3) not null,
 Pair smallint not null,
 idAss integer not null references  Assignment(idAss),
 idFl integer not null references Flow(idFl),
 GroupS smallint,
 constraint primary key (idRoom, tDay, Pair)
) engine=InnoDB; 

drop table if exists Printing;
create table Printing
(idPrn integer not null primary key,
 Title varchar(15) not null,
 cntAut smallint,
 YearP integer,
 tPrint varchar(10)
) engine=InnoDB;

drop table if exists Person;
create table Person
(idPer integer not null primary key,
 Surname varchar(15) not null,
 Name varchar(15) not null,
 Telefon varchar(12),
 Sex char(1) not null
) engine=InnoDB; 

 


 