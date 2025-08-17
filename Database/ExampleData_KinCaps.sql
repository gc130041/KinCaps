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

INSERT INTO gorras (tipo, marca, nombreGorra, color, descripcion, imagen, precio, stock, idProveedor) VALUES 
('DEPORTIVA', 'Jointophat', 'Gorra Deportiva Clásica', 'Negro', 'Gorra deportiva transpirable, ideal para actividades al aire libre.', 'gorra1.webp', 175.00, 100, 1),
('DEPORTIVA', 'Nox', 'Gorra Nox Negro', 'Negro y Blanco', 'Gorra oficial Nox Sport, diseño bicolor con logo frontal.', 'gorra2.webp', 220.00, 75, 1),
('DEPORTIVA', 'Stamina', 'Gorra Stamina 6 Paneles', 'Azul Marino', 'Gorra deportiva modelo Karin de 6 paneles, color azul marino.', 'gorra3.png', 150.00, 120, 1),
('DEPORTIVA', 'Adidas', 'Adidas Bball S Cap', 'Azul', 'Gorra de baseball Adidas, estilo unisex para lifestyle.', 'gorra4.png', 250.00, 60, 1),
('DEPORTIVA', 'Jointophat', 'Gorra Deportiva Premium', 'Blanco', 'Gorra deportiva de alta calidad con cierre ajustable.', 'gorra5.webp', 185.00, 90, 1);

INSERT INTO gorras (tipo, marca, nombreGorra, color, descripcion, imagen, precio, stock, idProveedor) VALUES 
('OTRO', 'Legion', 'Gorra Legion Anthracite', 'Antracita', 'Gorra de diseño exclusivo Legion, color antracita.', 'gorra1.webp', 275.00, 50, 1);

use db_kincaps;
select * from usuario;
select * from CookieAuth;
select * from gorras;
select * from carrito;
select * from detallecarrito;

SELECT email, contrasenaHash, LENGTH(contrasenaHash) AS longitud FROM usuario WHERE email = 'test@email.com';