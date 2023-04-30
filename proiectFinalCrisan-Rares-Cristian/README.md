### INSTRUCTIUNI
(Acest proiect folosește baza de date MariaDB și driverul specific pentru aceasta
și anume "org.mariadb.jdbc.Driver")

- Copiați codul din scriptul sql și rulați-l în interfața sql dorită(mysql-workbench, phpmyadmin, etc)
- Schimabți numele driverul dacă este necesar din mariadb în mysql în fișierul Login.java 
liniile 113 și 114 din

113       String driver = "org.mariadb.jdbc.Driver";
114       String url = "jdbc:mariadb://localhost:3306/cabinetMedical?useSSL=false";

în

113       String driver = "org.mysql.jdbc.Driver";
114       String url = "jdbc:mysql://localhost:3306/cabinetMedical?useSSL=false";

- Salvați fișerul Login.java

- Deschideți terminalul Windows/Mac/Linux 
- verificati daca aveti instalat java cu comanda:
  java -version

- Mergeți în folderul curent prin comanda: 
  cd calea/catre/care/doriti

- Rulați după ce sunteți în folderul proiectului
pe rând comenzile:

javac Login.java
java Login

- Aplicația java Swing rulează
date Login
nume doctor: ana
parola: 1234

## Proiect creat de: Crișan Rareș-Cristian
## Informatica anul 2 grupa 1
## 1 Decembrie 1918
## prof. Manuella Kadar
