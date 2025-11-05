USE BosqPlayDB;

INSERT INTO Rol(nombreRol) VALUES
('Administrador'), ('Cajero'), ('Supervisor'), ('Crupier'), ('Seguridad');

INSERT INTO Empleado(nombre, cargo) VALUES
('Carlos Ruiz', 'Cajero'),
('Ana Gómez', 'Crupier'),
('Luis Pérez', 'Supervisor'),
('David Mendoza', 'Administrador'),
('Sofía Reyes', 'Crupier'),
('Mateo Vargas', 'Seguridad');

INSERT INTO EmpleadoRol(idEmpleado,idRol) VALUES
(1,2),(2,4),(3,3),(4,1),(5,4),(6,5);

INSERT INTO MetodoPago(nombre) VALUES
('Efectivo'),('Tarjeta Crédito'),('Tarjeta Débito'),('Transferencia');

INSERT INTO Cliente(nombre, docId, telefono, email) VALUES
('Juan López', 'CC1001', '3001234567','juan@mail.com'),
('María Torres','CC1002','3011112233','maria@mail.com'),
('Pedro Suárez','CC1003','3022223344','pedro@mail.com');

INSERT INTO TipoJuego(nombre, descripcion) VALUES
('Mesa','Juegos como póker o blackjack'),
('Máquina','Slot machines'),
('Torneo', 'Competencias estructuradas');

INSERT INTO EventoJuego(idTipoJuego, estado) VALUES
(1,'Disponible'),(2,'Disponible'),(3,'Planificado');

INSERT INTO Mesa(idEvento,nroMesa,limiteJugadores) VALUES
(1,10,6);

INSERT INTO Maquina(idEvento,codigoSerie,denominacion) VALUES
(2,'SL-001',200);

INSERT INTO Torneo(idEvento,nombre,fechaInicio,fechaFin,buyIn,premio) VALUES
(3,'Torneo Poker HighRoller','2025-12-01','2025-12-05',5000000,20000000);

INSERT INTO InventarioFicha(nombreBoveda,saldo) VALUES
('Bóveda Principal',50000),('Caja Principal',10000);

INSERT INTO MovimientoFicha(idInventario,idEmpleado,cantidad,tipoMov,observacion) VALUES
(1,4,10000,'Entrada','Ingreso de fichas inicial'),
(2,1,-2000,'Salida','Entrega a caja');
