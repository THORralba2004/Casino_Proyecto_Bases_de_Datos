USE BosqPlayDB;

CREATE VIEW vw_EmpleadosActivos AS
SELECT idEmpleado, nombre, cargo FROM Empleado WHERE activo='S';

CREATE VIEW vw_ApuestasClientes AS
SELECT c.nombre AS Cliente, a.monto, a.resultado
FROM Apuesta a
JOIN Cliente c USING(idCliente);

DELIMITER $$
CREATE PROCEDURE RegistrarMovimiento(
    pInventario INT,
    pEmpleado INT,
    pCantidad INT,
    pTipo VARCHAR(20),
    pObs VARCHAR(255)
)
BEGIN
    INSERT INTO MovimientoFicha(idInventario,idEmpleado,cantidad,tipoMov,observacion)
    VALUES(pInventario,pEmpleado,pCantidad,pTipo,pObs);

    UPDATE InventarioFicha
    SET saldo = saldo + pCantidad
    WHERE idInventario = pInventario;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_valida_saldo
BEFORE UPDATE ON InventarioFicha
FOR EACH ROW
BEGIN
    IF NEW.saldo < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Saldo insuficiente para operación';
    END IF;
END $$
DELIMITER ;

CALL RegistrarMovimiento(2, 1, 3000, 'Entrada', 'Ajuste de caja');
