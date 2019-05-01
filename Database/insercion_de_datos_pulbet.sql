use pulbet;

/*IDIOMA*/
INSERT INTO IDIOMA(COD_IDIOMA) VALUES 
('ESP'),
('GAL'),
('ENG');

/*USUARIO*/
/*INSERT INTO USUARIO(EMAIL,NOMBRE,APELLIDO1,APELLIDO2,PASSWORD,BANCO,TELEFONO,FECHA_NACIMIENTO,NOMBRE_USUARIO,DNI)VALUES
("pepe@yahoo.es", "Pepe", "Perez", "Fernandez", "lsrrf7dysc", 15.6, "666666666", "1990-12-18", "pepeperez", "34567890J"),
("vv.alex.jandro@gmail.es", "Alejandro", "Varela", "Vazquez", "ojmcwdsgfc", 34.7, "647123456", "1989-04-04", "vvalejandro", "34567123L"),
("manolo@gmail.es", "Manuel", "Gonzalez", "Perez", "wledfgce", 200.79, "777777777", "1995-07-04", "manologp", "76543210G"),
("franguimil@gmail.es","Fran","Güimil","Gúimilez","ewafdsvcresfd",0.0,"111111111","1998-03-05","franpastran", "12345678G"),
("alfredo21@gmail.es", "Alfredo", "Lopez", "Mendez", "weafc438ewfh3c", 0.0, "666777666", "1987-01-15", "alfredo21", "34243456K"),
("pablo.rg@gmail.es", "Pablo", "Rivas", "Gonzalez", "32qerfgrfe7dysc", 30.0, "613343267", "1992-08-09", "pablo123", "76543987Y"),
("xandre@gmail.com", "Alexandre", "Perez", "Corral", "t54wt545t34", 1000.0, "661223456", "2000-01-01", "corralxela", "34275123X"),
("thepepe@yahoo.es", "Jose", "Vazquez", "Alvarez", "lf32awefdfqec", 10.0, "671895423", "1970-09-28", "thepepe", "76985324V"),
("alfonso45@gmail.es", "Alfonso", "Fernandez", "Vazquez", "awfefewfqarfd", 20.0, "654123321", "1975-12-01", "alfonsito", "76985410F"),
("seijasiago@gmail.es","Iago","Seijas","Seijas","aervvcaefrgv",100.0,"111111112","1991-08-08","seijasiago", "34567891K");
*/

/*PAIS*/
INSERT INTO PAIS(ID_PAIS) VALUES 
(34),
(33),
(351),
(39);

/*PAIS_IDIOMA*/
INSERT INTO PAIS_IDIOMA(ID_PAIS,COD_IDIOMA, NOMBRE) VALUES
(34,'ESP','ESPAÑA'),
(34,'GAL','ESPAÑA'),
(34,'ENG','SPAIN'),
(33,'ESP','FRANCIA'),
(33,'GAL','FRANCIA'),
(33,'ENG','FRANCE'),
(351,'ESP','PORTUGAL'),
(351,'GAL','PORTUGAL'),
(351,'ENG','PORTUGAL'),
(39,'ESP','ITALIA'),
(39,'GAL','ITALIA'),
(39,'ENG','ITALY');

/*PROVINCIA*/
INSERT INTO PROVINCIA(ID_PROVINCIA, NOMBRE, ID_PAIS) VALUES 
(01,"Álava",34),						
(02,"Albacete",34),
(03,"Alicante",34),
(04,"Almería",34),
(05,"Ávila",34),
(06,"Badajoz",34),
(07,"Baleares",34),
(08,"Barcelona",34),
(09,"Burgos",34),
(10,"Cáceres",34),
(11,"Cádiz",34),
(12,"Castellón",34),
(13,"Ciudad Real",34),
(14,"Córdoba",34),
(15,"A Coruña",34),
(16,"Cuenca",34),
(17,"Girona",34),
(18,"Granada",34),
(19,"Guadalajara",34),
(20,"Gipuzkoa",34),
(21,"Huelva",34),
(22,"Huesca",34),
(23,"Jaén",34),
(24,"León",34),
(25,"Lleida",34),
(26,"La Rioja",34),
(27,"Lugo",34),
(28,"Madrid",34),
(29,"Málaga",34),
(30,"Murcia",34),
(31,"Navarra",34),
(32,"Ourense",34),
(33,"Asturias",34),
(34,"Palencia",34),
(35,"Las Palmas",34),
(36,"Pontevedra",34),
(37,"Salamanca",34),
(38,"Santa Cruz de Tenerife",34),
(39,"Cantabria",34),
(40,"Segovia",34),
(41,"Sevilla",34),
(42,"Soria",34),
(43,"Tarragona",34),
(44,"Teruel",34),
(45,"Toledo",34),
(46,"Valencia",34),
(47,"Valladolid",34),
(48,"Bizkaia",34),
(49,"Zamora",34),
(50,"Zaragoza",34),
(51,"Ceuta",34),
(52,"Melilla",34);


/*DIRECCION*/

/*
INSERT INTO DIRECCION(CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA,ID_USUARIO) VALUES 
('Chantada',27,'Carlos Otero Diaz',13,27500,3,'J',1),
('Chantada',32,'Carlos Otero Diaz',13,27500,3,'J',2),
('Chantada',27,'Carlos Otero Diaz',1,27500,1,'A',3),
('Chantada',27,'Juan XXVIII',45,27500,3,'B',4),
('Chantada',27,'Rua da Barxela',13,27500,2,'C',5),
('Escairon',27,'Rua Chantada',8,27540,1,'A',6);

INSERT INTO DIRECCION(CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,ID_USUARIO) VALUES
('Taboada',27,'Casa do Iago',44,27550,7),
('Chantada',27,'Basan Pequeno',33,27500,8),
('O Saviñao',27,'Outeiro, Diomondi',13,27540,9),
('Valga',36,'Casa do Güimil',20,36640,10);

*/

/*DEPORTE*/
INSERT INTO DEPORTE(NOMBRE) VALUES 
('FUTBOL'),
('TENIS'),
('BALONCESTO'),
('BALONMANO'),
('FUTBOL_SALA'),
('CICLISMO'),
('MOTOR'),
('WATERPOLO'),
('HOCKEY_HEIRBA'),
('HOCKEY'),
('BADMINTON');

/*DEPORTE_IDIOMA*/
INSERT INTO DEPORTE_IDIOMA(COD_IDIOMA, ID_DEPORTE, NOMBRE) VALUES 
('ESP',1,'FUTBOL'),
('ESP',2,'TENIS'),
('ESP',3,'BALONCESTO'),
('ESP',4,'BALONMANO'),
('ESP',5,'FUTBOL SALA'),
('ESP',6,'CICLISMO'),
('ESP',7,'MOTOR'),
('ESP',8,'WATERPOLO'),
('ESP',9,'HOCKEY HIERBA'),
('ESP',10,'HOCKEY'),
('ESP',11,'BADMINTON'),
('GAL',1,'FUTBOL'),
('GAL',2,'TENIS'),
('GAL',3,'BALONCESTO'),
('GAL',4,'BALONMAN'),
('GAL',5,'FUTBOL SALA'),
('GAL',6,'CICLISMO'),
('GAL',7,'MOTOR'),
('GAL',8,'WATERPOLO'),
('GAL',9,'HOCKEY HERBA'),
('GAL',10,'HOCKEY'),
('GAL',11,'BADMINTON'),
('ENG',1,'FOOTBALL'),
('ENG',2,'TENNIS'),
('ENG',3,'BASKETBALL'),
('ENG',4,'HANDBALL'),
('ENG',5,'INDOOR FOOTBALL'),
('ENG',6,'CYCLING'),
('ENG',7,'RACES'),
('ENG',8,'WATERPOLO'),
('ENG',9,' GRASS HOCKEY'),
('ENG',10,'HOCKEY'),
('ENG',11,'BADMINTON');


/*PARTICIPANTE*/
INSERT INTO PARTICIPANTE(NOMBRE,ID_DEPORTE) VALUES 
('DEPORTIVO DE LA CORUÑA',1), 
('CELTA DE VIGO',1),
('REAL MADRID',1),
('F.C. BARCELONA',1),
('RAFA NADAL',2),
('ROGER FEDERER',2),
('NOVAK DJOKOVIC',2),
('DOMINIC THIEM',2),
('BARCELONA',3),
('MADRID',3),
('BASKONIA',3),
('OBRADOIRO',3),
('F.C. BARCELONA',5),
('INTER MOVISTAR',5);
  

/*COMPETICION*/
INSERT INTO COMPETICION(NOMBRE,ID_DEPORTE,FECHA_INICIO, FECHA_FIN) VALUES 
('LIGA SANTANDER',1,2018,2019),
('LIGA 1,2,3',1,2018,2019),
('SERIE A',1,2018,2019),
('SERIE B',1,2018,2019),
('LEAGUE 1',1,2018,2019),
('LEAGUE 2',1,2018,2019),
('PREMIER LEAGUE',1,2018,2019);

INSERT INTO COMPETICION(NOMBRE,ID_DEPORTE,FECHA_INICIO) VALUES 
('ROLAND GARROS',2,2019),
('WINBLEDON',2,2019);

INSERT INTO COMPETICION(NOMBRE,ID_DEPORTE,FECHA_INICIO, FECHA_FIN) VALUES 
('ACB',3,2018,2019),
('NBA',3,2018,2019),
('PRIMERA DIVISION',5,2018,2019);

/*TIPO DE RESULTADO*/
INSERT INTO TIPO_RESULTADO(NOMBRE) VALUES 
('GANADOR'),	-- 1
('DOBLE OPORTUNIDAD'), -- 2
('GANADOR (DOS OPCIONES)'); -- 3

/*TIPO_RESULTADO_IDIOMA*/
INSERT INTO TIPO_RESULTADO_IDIOMA(COD_IDIOMA, ID_TIPO_RESULTADO, NOMBRE) VALUES 
('ESP',1,'GANADOR'),
('ESP',2,'DOBLE OPORTUNIDAD'),
('ESP',3,'GANADOR (DOS OPCIONES)'),
('GAL',1,'GAÑADOR'),
('GAL',2,'DOBRE OPORTUNIDADE'),
('GAL',3,'GAÑADOR (DUAS OPCIONS)'),
('ENG',1,'WINNER'),
('ENG',2,'DOUBLE CHANCE'),
('ENG',3,'WINNER (TWO OPTIONS)');

/*RESULTADO*/
INSERT INTO RESULTADO(ID_TIPO_RESULTADO) VALUES 
(1),
(1),
(1),
(2),
(2),
(2),
(3),
(3);

/*RESULTADO_IDIOMA*/
INSERT INTO RESULTADO_IDIOMA(ID_RESULTADO, COD_IDIOMA, NOMBRE) VALUES
(1,'ESP','LOCAL'),
(2,'ESP','VISITANTE'),
(3,'ESP','EMPATE'),
(4,'ESP','1 o X'),
(5,'ESP','X o 2'),
(6,'ESP','1 o 2'),
(7,'ESP','LOCAL'),
(8,'ESP','VISITANTE'),
(1,'GAL','LOCAL'),
(2,'GAL','VISITANTE'),
(3,'GAL','EMPATE'),
(4,'GAL','1 ou X'),
(5,'GAL','X ou 2'),
(6,'GAL','1 ou 2'),
(7,'GAL','LOCAL'),
(8,'GAL','VISITANTE'),
(1,'ENG','LOCAL'),
(2,'ENG','AWAY'),
(3,'ENG','DRAW'),
(4,'ENG','1 or X'),
(5,'ENG','X or 2'),
(6,'ENG','1 or 2'),
(7,'ENG','LOCAL'),
(8,'ENG','AWAY');


/*TIPO_RESULTADO_DEPORTE*/
INSERT INTO TIPO_RESULTADO_DEPORTE(ID_DEPORTE,ID_TIPO_RESULTADO) VALUES 
(1,1),
(1,2),
(2,3),
(3,3),
(4,1),
(4,2),
(5,1),
(5,2),
(6,1),
(7,1),
(8,1),
(8,2),
(9,1),
(9,2),
(10,1),
(10,2),
(11,3);

/*EVENTO*/
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2019-05-05 20:45:00", 1);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2019-05-12 20:45:00", 1);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2019-05-19 20:45:00", 1);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2019-05-05 20:45:00", 10);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2019-05-05 10:45:00", 10);
/*finalizados*/
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-03 13:00:00", 1);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-03 13:00:00", 1);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-03 15:00:00", 8);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-03 15:00:00", 8);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-03 17:00:00", 10);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-03 17:00:00", 10);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-05 19:00:00", 9);
INSERT INTO EVENTO(FECHA_HORA,ID_COMPETICION) VALUES ("2018-12-05 19:00:00", 9);


/*RESULTADO_PARTICIPANTE_EVENTO*/
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,2,1,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,3,2,2.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,2,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,3,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,2,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,2,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,2,6,1.15); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,3,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,3,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (1,3,6,1.15); 
-- 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,3,1,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,4,2,2.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,3,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,4,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,3,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,3,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,3,6,1.15); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,4,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,4,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (2,4,6,1.15); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,4,1,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,2,2,2.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,4,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,2,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,4,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,4,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,4,6,1.15); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,2,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,2,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (3,2,6,1.15); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (4,9,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (4,10,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (5,11,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (5,12,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,2,1,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,3,2,2.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,2,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,3,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,2,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,2,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,2,6,1.15); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,3,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,3,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (6,3,6,1.15); 
-- 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,3,1,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,4,2,2.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,3,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,4,3,3.00); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,3,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,3,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,3,6,1.15); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,4,4,1.25); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,4,5,1.30); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (7,4,6,1.15); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (8,5,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (8,6,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (9,8,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (9,7,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (10,10,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (10,11,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (11,12,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (11,9,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (12,5,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (12,6,8,2.00); 
--
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (13,8,7,1.75); 
INSERT INTO RESULTADO_PARTICIPANTE_EVENTO(ID_EVENTO,ID_PARTICIPANTE,ID_RESULTADO,CUOTA) VALUES (13,7,8,2.00); 

/*RESULTADO_REAL*/
/*Ata que acaban os partidos non se poderia saber.*/
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(6,2);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(6,5);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(6,6);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(7,1);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(7,4);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(7,6);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(8,7);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(9,8);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(10,7);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(11,8);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(12,7);
INSERT INTO RESULTADO_REAL(ID_EVENTO,ID_RESULTADO) VALUES(13,7);

/*APUESTA*/
/*
INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) VALUES 
(1.0, 2, "2018-11-26 12:14:15",10.0);-- NO TERMINADA
INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) VALUES
(2.0, 1, "2018-11-23 12:14:15", 10.0); -- TERMINADA
INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) VALUES
(1.0, 3, "2018-12-06 17:14:15", 10.0), -- NO TERMINADA
(1.0, 5, "2018-12-03 10:02:15", 10.0); -- NO TERMINADA
INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) VALUES
(5.0, 6, "2018-12-01 17:14:16", 10.0); -- TERMINADA
INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) VALUES
(1.0, 7, "2018-12-02 10:14:36", 10.0), -- NO TERMINADA
(3.0, 8, "2018-12-03 17:14:15", 10.0), -- NO TERMINADA
(1.0, 9, "2018-12-01 13:02:58", 10.0), -- NO TERMINADA
(4.0, 10, "2018-12-03 17:14:15", 10.0); -- NO TERMINADA
INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) VALUES
(1.0, 4, "2018-12-02 15:14:15", 10.0); -- TERMINADA
*/

/*LINEA_APUESTA*/
/*
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,1,1,1);
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (2,1,7,4);
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (3,1,8,8);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,2,4,7);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,3,7,13);
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (2,3,6,1);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,4,3,2);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,5,2,6);
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (2,5,7,9);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,6,1,1);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,7,5,1);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,8,4,1);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,9,8,5);
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (2,9,6,6);
INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (3,9,7,10);

INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) VALUES (1,10,7,8);
*/

/*TIPO_RESULTADO_EVENTO*/
/*Tipos de resultado dispoñibles para cada evento*/
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (1,1);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (1,2);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (2,1);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (2,2);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (3,1);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (3,2);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (4,3);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (5,3);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (6,1);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (6,2);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (7,1);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (7,2);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (8,3);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (9,3);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (10,3);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (11,3);
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (12,3);	
INSERT INTO TIPO_RESULTADO_EVENTO(ID_EVENTO,ID_TIPO_RESULTADO) VALUES (13,3);	

/*PARTICIPANTE-COMPETICION*/
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (1,2);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (2,1);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (3,1);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (4,1);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (9,10);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (10,10);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (11,10);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (12,10);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (5,8);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (6,8);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (7,8);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (8,8);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (5,9);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (6,9);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (7,9);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (8,9);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (13,12);
INSERT INTO PARTICIPANTE_COMPETICION(ID_PARTICIPANTE,ID_COMPETICION) VALUES (14,12);


						




