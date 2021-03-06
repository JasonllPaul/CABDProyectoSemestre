GRANT Create Session to CABD


CREATE USER empleado IDENTIFIED BY empleado1;
CREATE USER cliente IDENTIFIED BY cliente1;

GRANT Create Session to empleado
GRANT Create Session to cliente

GRANT SELECT ON HOTEL TO empleado
GRANT SELECT ON EMPLEADO TO empleado
GRANT SELECT ON HABITACION TO empleado
GRANT INSERT ON HOTEL TO empleado
GRANT INSERT ON EMPLEADO TO empleado
GRANT INSERT ON HABITACION TO empleado
GRANT UPDATE ON HOTEL TO empleado
GRANT UPDATE ON EMPLEADO TO empleado
GRANT UPDATE ON HABITACION TO empleado
GRANT DELETE ON HOTEL TO empleado
GRANT DELETE ON EMPLEADO TO empleado
GRANT DELETE ON HABITACION TO empleado

GRANT SELECT ON RESERVA TO cliente
GRANT UPDATE ON RESERVA TO cliente
GRANT DELETE ON RESERVA TO cliente

