CALL sp_AgregarCliente('Juan', 'Perez', 'juan.perez@email.com', '1234567890', 'Calle Falsa 123', 'clave123');
CALL sp_AgregarCliente('Ana', 'Gomez', 'ana.gomez@email.com', '2345678901', 'Avenida Siempre Viva 742', 'password');
CALL sp_AgregarCliente('Carlos', 'Rodriguez', 'carlos.r@email.com', '3456789012', 'Boulevard de los Sueños Rotos 45', 'carlos_pass');
CALL sp_AgregarCliente('Maria', 'Lopez', 'maria.lopez@email.com', '4567890123', 'Plaza Mayor 1', 'maria1234');
CALL sp_AgregarCliente('Luis', 'Martinez', 'luis.m@email.com', '5678901234', 'Calle Luna 23', 'luis_secreto');
CALL sp_AgregarCliente('Sofia', 'Hernandez', 'sofia.h@email.com', '6789012345', 'Paseo de la Reforma 222', 'sofia_h_2024');
CALL sp_AgregarCliente('Javier', 'Garcia', 'javier.g@email.com', '7890123456', 'Calle del Sol 89', 'garcia_javier');
CALL sp_AgregarCliente('Laura', 'Diaz', 'laura.diaz@email.com', '8901234567', 'Avenida Insurgentes Sur 1234', 'lauradiaz_pwd');
CALL sp_AgregarCliente('Miguel', 'Sanchez', 'miguel.s@email.com', '9012345678', 'Calle Roble 56', 'miguelito_123');
CALL sp_AgregarCliente('Valeria', 'Torres', 'valeria.t@email.com', '0123456789', 'Camino Real 101', 'valtorres_pass');
CALL sp_AgregarCliente('Diego', 'Ramirez', 'diego.r@email.com', '1122334455', 'Calle del Parque 33', 'diego_ram');
CALL sp_AgregarCliente('Camila', 'Flores', 'camila.f@email.com', '2233445566', 'Avenida Juarez 500', 'camila_flores');
CALL sp_AgregarCliente('Jorge', 'Vargas', 'jorge.v@email.com', '3344556677', 'Callejon del Beso 9', 'jorgev2025');
CALL sp_AgregarCliente('Isabella', 'Rojas', 'isabella.r@email.com', '4455667788', 'Avenida Americas 78', 'isa_rojas_!_');
CALL sp_AgregarCliente('Mateo', 'Mendoza', 'mateo.m@email.com', '5566778899', 'Calle de la Montaña 45', 'mateo_mendoza_pwd');
CALL sp_AgregarCliente('Lucia', 'Castro', 'lucia.c@email.com', '6677889900', 'Circuito Interior 12', 'lucia_castro_key');
CALL sp_AgregarCliente('Andres', 'Ortiz', 'andres.o@email.com', '7788990011', 'Calle Pino 111', 'andres_ortiz_11');
CALL sp_AgregarCliente('Fernanda', 'Gutierrez', 'fernanda.g@email.com', '8899001122', 'Calle Cedro 22', 'fer_guti');
CALL sp_AgregarCliente('Sebastian', 'Cruz', 'sebastian.c@email.com', '9900112233', 'Avenida Patriotismo 88', 'sebas_cruz');
CALL sp_AgregarCliente('Daniela', 'Reyes', 'daniela.r@email.com', '1010101010', 'Plaza de la Constitucion 2', 'dani_reyes_2024');

CALL sp_AgregarEmpleado('Ana','López','ana.lopez@example.com','555-9876','Plaza Mayor 45, Ciudad Principal','password','Diseñadora UX/UI','2024-06-15');

INSERT INTO proveedor (nombre, contacto, telefono)
VALUES ('Proveedor X', 'Juan Pérez', '123-456-7890');

use db_kincaps;
select * from cliente;
select * from CookieAuth;