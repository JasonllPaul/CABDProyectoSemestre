LOAD DATA
INFILE "DatosReserva.csv"
INTO TABLE RESERVA
INSERT
FIELDS TERMINATED BY ';'
TRAILING NULLCOLS
(RESID,CLIDNI,RESPERSONAS,RESFECHAINICIO,RESFECHAFIN,RESTOTAL)
