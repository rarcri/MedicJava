drop database cabinetMedical;
create database cabinetMedical; 
use cabinetMedical;

create table doctori(
  id_doctor int not null primary key auto_increment,
  nume varchar(100) not null,
  parola varchar(200),
  email varchar(200),
  telefon varchar(200),
  unique(nume)
);

create table pacienti(
  id_pacient int not null primary key auto_increment,
  nume varchar(100) not null,
  adresa varchar(200),
  telefon varchar(50),
  varsta int(20),
  sex varchar(50),
  grupa_sanguina varchar(50),
  boli_cronice varchar(300),
  id_doctor int not null,
  unique(nume),
  constraint fk_doctori foreign key(id_doctor) references doctori(id_doctor)
);

create table programari(
  id_programare int not null primary key auto_increment,
  data_curenta DATE,
  data_programarii DATE,
  id_pacient int not null,
  constraint fk_pacient1 foreign key(id_pacient) references pacienti(id_pacient)
);

create table diagnostice(
  id_diagnostic int not null primary key auto_increment,
  nume_diagnostic varchar(100),
  detalii_diagnostic varchar(500),
  data_diagnostic date,
  id_pacient int not null,
  constraint fk_pacient foreign key(id_pacient) references pacienti(id_pacient)
);

create table istoric (
  id_istoric int not null primary key auto_increment,
  titlu_istoric varchar(50),
  continut_istoric varchar(5000),
  data_istoric DATE,
  id_pacient int not null,
  constraint fk_pacient2 foreign key(id_pacient) references pacienti(id_pacient)
);

CREATE VIEW istoric_pacienti AS
SELECT pacienti.nume, diagnostice.nume_diagnostic    
FROM pacienti JOIN diagnostice ON diagnostice.id_pacient = pacienti.id_pacient
ORDER BY diagnostice.data_diagnostic;

INSERT INTO doctori(nume, parola, email, telefon) VALUES ('ana', '1234', 'ana@gmail.com', '075756464');
