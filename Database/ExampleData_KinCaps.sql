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
VALUES ('Proveedor X', 'Juan Pérez', '123-456-7890'),
('Proveedor F1', 'El Piloto de f1', '123-456-7890');

INSERT INTO gorras (tipo, marca, nombreGorra, color, precio, stock, descripcion, imagen, idProveedor) VALUES
('URBANA', 'New Era', 'Gorra Yankees Vintage', 'Negro', 350.00, 20, 'Gorra New Era Negra de los Yankees de Nueva York, Año 2000.', 'gorra2.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers Clásica', 'Negro', 320.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra3.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers Side Patch', 'Negro', 330.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra4.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers All-Star Game', 'Negro', 340.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra5.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers World Series', 'Negro', 350.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra6.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers Logo Grande', 'Negro', 325.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra7.webp', 1),
('URBANA', 'New Era', 'Gorra Dodgers Edición Especial', 'Negro', 360.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra8.webp', 1),
('URBANA', 'New Era', 'Gorra Dodgers Visera Plana', 'Negro', 335.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra9.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers Coleccionista', 'Negro', 370.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra10.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers City Connect', 'Negro', 380.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra11.png', 1),
('URBANA', 'New Era', 'Gorra Dodgers Throwback', 'Negro', 345.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra12.webp', 1),
('URBANA', 'New Era', 'Gorra Dodgers Script', 'Negro', 355.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra13.webp', 1),
('URBANA', 'New Era', 'Gorra Dodgers Parche Lateral', 'Negro', 365.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra14.jpg', 1),
('URBANA', 'New Era', 'Gorra Dodgers Edición Limitada', 'Negro', 390.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra15.webp', 1),
('URBANA', 'New Era', 'Gorra Dodgers Campeones', 'Negro', 400.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra16.webp', 1),
('URBANA', 'New Era', 'Gorra Dodgers Cooperstown', 'Negro', 375.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra17.avif', 1),
('URBANA', 'New Era', 'Gorra Dodgers Fan Favorite', 'Negro', 310.00, 20, 'Gorra New Era Negra de Los Angeles Dodgers.', 'gorra18.jpg', 1),
('URBANA', 'New Era', 'Gorra Padres 2020', 'Marrón', 385.00, 20, 'Gorra New Era Marrón de los San Diego Padres, Año 2020.', 'gorra19.jpg', 1),
('URBANA', 'New Era', 'Gorra Mets 1954', 'Negro', 395.00, 20, 'Gorra New Era Negra de los New York Mets, Año 1954.', 'gorra20.webp', 1);

INSERT INTO gorras (tipo, marca, nombreGorra, color, precio, stock, descripcion, imagen, idProveedor) VALUES
('FORMULA_1', 'Williams Racing', 'Gorra Alexander Albon 2025', 'Azul', 480.00, 25, 'Gorra oficial del piloto Alexander Albon, temporada 2025.', 'alb.avif', 2),
('FORMULA_1', 'Aston Martin', 'Gorra Fernando Alonso 2025', 'Verde Racing', 520.00, 25, 'Gorra oficial del piloto Fernando Alonso, temporada 2025.', 'alo.webp', 2),
('FORMULA_1', 'Red Bull Racing', 'Gorra Sergio Pérez 2025', 'Azul Marino', 550.00, 25, 'Gorra oficial del piloto Sergio Pérez, temporada 2025.', 'ant.avif', 2),
('FORMULA_1', 'Haas F1 Team', 'Gorra Oliver Bearman 2025', 'Rojo y Negro', 490.00, 25, 'Gorra oficial del piloto Oliver Bearman, temporada 2025.', 'bea.avif', 2),
('FORMULA_1', 'Kick Sauber', 'Gorra Gabriel Bortoleto 2025', 'Verde y Negro', 480.00, 25, 'Gorra oficial del piloto Gabriel Bortoleto, temporada 2025.', 'bor.avif', 2),
('FORMULA_1', 'Williams Racing', 'Gorra Franco Colapinto 2025', 'Azul', 485.00, 25, 'Gorra oficial del piloto Franco Colapinto, temporada 2025.', 'col.avif', 2),
('FORMULA_1', 'Alpine', 'Gorra Pierre Gasly 2025', 'Azul y Rosa', 495.00, 25, 'Gorra oficial del piloto Pierre Gasly, temporada 2025.', 'gas.png', 2),
('FORMULA_1', 'RB Formula One Team', 'Gorra Isaack Hadjar 2025', 'Azul y Blanco', 490.00, 25, 'Gorra oficial del piloto Isaack Hadjar, temporada 2025.', 'had.png', 2),
('FORMULA_1', 'Mercedes-AMG Petronas', 'Gorra Lewis Hamilton 2025', 'Negro', 550.00, 25, 'Gorra oficial del piloto Lewis Hamilton, temporada 2025.', 'ham.png', 2),
('FORMULA_1', 'Haas F1 Team', 'Gorra Nico Hülkenberg 2025', 'Negro y Rojo', 475.00, 25, 'Gorra oficial del piloto Nico Hülkenberg, temporada 2025.', 'hul.avif', 2),
('FORMULA_1', 'RB Formula One Team', 'Gorra Liam Lawson 2025', 'Azul y Blanco', 490.00, 25, 'Gorra oficial del piloto Liam Lawson, temporada 2025.', 'law.avif', 2),
('FORMULA_1', 'Scuderia Ferrari', 'Gorra Charles Leclerc 2025', 'Rojo', 550.00, 25, 'Gorra oficial del piloto Charles Leclerc, temporada 2025.', 'lec.png', 2),
('FORMULA_1', 'McLaren', 'Gorra Lando Norris 2025', 'Naranja Papaya', 530.00, 25, 'Gorra oficial del piloto Lando Norris, temporada 2025.', 'nor.avif', 2),
('FORMULA_1', 'Alpine', 'Gorra Esteban Ocon 2025', 'Azul y Rosa', 495.00, 25, 'Gorra oficial del piloto Esteban Ocon, temporada 2025.', 'oco.avif', 2),
('FORMULA_1', 'McLaren', 'Gorra Oscar Piastri 2025', 'Naranja Papaya', 510.00, 25, 'Gorra oficial del piloto Oscar Piastri, temporada 2025.', 'pia.avif', 2),
('FORMULA_1', 'Mercedes-AMG Petronas', 'Gorra George Russell 2025', 'Negro', 520.00, 25, 'Gorra oficial del piloto George Russell, temporada 2025.', 'rus.avif', 2),
('FORMULA_1', 'Scuderia Ferrari', 'Gorra Carlos Sainz 2025', 'Rojo', 540.00, 25, 'Gorra oficial del piloto Carlos Sainz, temporada 2025.', 'sai.avif', 2),
('FORMULA_1', 'Aston Martin', 'Gorra Lance Stroll 2025', 'Verde Racing', 490.00, 25, 'Gorra oficial del piloto Lance Stroll, temporada 2025.', 'str.webp', 2),
('FORMULA_1', 'RB Formula One Team', 'Gorra Yuki Tsunoda 2025', 'Azul y Blanco', 485.00, 25, 'Gorra oficial del piloto Yuki Tsunoda, temporada 2025.', 'tsu.avif', 2),
('FORMULA_1', 'Red Bull Racing', 'Gorra Max Verstappen 2025', 'Azul Marino', 580.00, 25, 'Gorra oficial del piloto Max Verstappen, temporada 2025.', 'ver.avif', 2);



use db_kincaps;
select * from cliente;
select * from CookieAuth;
select * from gorras;
select * from carrito;
select * from detallecarrito;