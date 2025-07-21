DELIMITER $$
CREATE PROCEDURE sp_AgregarCliente(
    IN p_nombre VARCHAR(50),
    IN p_apellido VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_telefono VARCHAR(15),
    IN p_direccion VARCHAR(255),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    DECLARE v_contrasenaHash CHAR(64);
    SET v_contrasenaHash = SHA2(p_contrasena, 256);

    INSERT INTO cliente (nombre, apellido, email, telefono, direccion, contrasenaHash)
    VALUES (p_nombre, p_apellido, p_email, p_telefono, p_direccion, v_contrasenaHash);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_Login(
    IN p_email VARCHAR(100),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    DECLARE v_contrasenaHash CHAR(64);
    DECLARE v_id INT;
    DECLARE v_tipoUsuario VARCHAR(10);

    SET v_contrasenaHash = SHA2(p_contrasena, 256);

    SELECT idCliente INTO v_id FROM cliente 
    WHERE email = p_email AND contrasenaHash = v_contrasenaHash;

    IF v_id IS NOT NULL THEN
        SET v_tipoUsuario = 'cliente';
        SELECT v_id AS idUsuario, v_tipoUsuario AS tipoUsuario;
    ELSE
        SELECT idEmpleado INTO v_id FROM empleados
        WHERE email = p_email AND contrasenaHash = v_contrasenaHash;

        IF v_id IS NOT NULL THEN
            SET v_tipoUsuario = 'empleado';
            SELECT v_id AS idUsuario, v_tipoUsuario AS tipoUsuario;
        ELSE
            SELECT '0' AS idUsuario, 'invalido' AS tipoUsuario;
        END IF;
    END IF;
END$$
DELIMITER ;

