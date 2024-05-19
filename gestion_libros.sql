-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2024 a las 12:59:10
-- Versión del servidor: 8.0.34
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestion_libros`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `biblioteca`
--

CREATE TABLE `biblioteca` (
  `idBiblioteca` int NOT NULL,
  `NombreBibliot` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Direccion` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Telefono` int NOT NULL,
  `Ciudad` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Pais` varchar(250) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `biblioteca`
--

INSERT INTO `biblioteca` (`idBiblioteca`, `NombreBibliot`, `Direccion`, `Telefono`, `Ciudad`, `Pais`) VALUES
(1, 'Biblioteca Hortaleza', 'Guaimaral 1', 915256787, 'Madrid', 'España'),
(2, 'Biblioteca Municipal Maria', 'Calle de la Pricensa', 915256787, 'Madrid', 'España'),
(3, 'Biblioteca Chamartin', 'Chamartin 5', 777777777, 'Madrid', 'España'),
(4, 'Biblioteca Las Tablas', 'Las Tablas 5', 777777777, 'Madrid', 'España'),
(5, 'Biblioteca Centro', 'Centro 5', 777777777, 'Madrid', 'España'),
(6, 'Biblioteca Retiro', 'Alcala 5', 777777777, 'Madrid', 'España'),
(7, 'Biblioteca Latina', 'Latina 5', 777777777, 'Madrid', 'España'),
(8, 'Biblioteca Carabanchel', 'Carabanchel 5', 777777777, 'Madrid', 'España'),
(9, 'Biblioteca Villaverde', 'Villaverde 5', 777777777, 'Madrid', 'España'),
(10, 'Biblioteca Barajas', 'Barajas 5', 777777777, 'Madrid', 'España');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `existencialibros`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `existencialibros` (
`Anno` int
,`Autor` varchar(250)
,`Direccion` varchar(250)
,`Edicion` int
,`Nombre` varchar(250)
,`NombreBibliot` varchar(250)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `idLibros` int NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Autor` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Edicion` int NOT NULL,
  `Anno` int NOT NULL,
  `UbicBiblioteca` int NOT NULL DEFAULT '1',
  `Stock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`idLibros`, `Nombre`, `Autor`, `Edicion`, `Anno`, `UbicBiblioteca`, `Stock`) VALUES
(1, 'Encuentra tu Persona Vitamina', 'Marian Rojas', 2017, 2017, 2, 3),
(2, 'Hábitos Atomicos', 'James Clear', 1, 2018, 2, 4),
(3, 'El Señor de los Anillos', 'J. R. R. Tolkien\r\n', 4, 1954, 2, 1),
(4, 'El hobbit', 'J. R. R. Tolkien\r\n', 10, 1937, 1, 3),
(5, 'Cocina de 10', 'Karlos Arguiños', 3, 1994, 1, 5),
(6, 'La ciudad y sus muros inciertos', 'Haruki Murakami', 1, 2024, 2, 3),
(7, 'La ratonera', 'Agatha Christie', 3, 1947, 1, 9),
(8, 'La ciudad y sus muros incierto', 'Agatha Christie', 5, 1937, 2, 4),
(9, 'Heidi', 'Johanna Spyri', 4, 1880, 1, 5),
(10, 'Asesinato en el Orient Expresso', 'Agatha Christie', 5, 1934, 2, 3),
(11, 'Orgullo y prejuicio', 'Jane Austen', 3, 1813, 2, 2),
(12, 'Orgullo y prejuicio', 'Jane Austen', 3, 1813, 1, 2),
(13, 'Emma', 'Jane Austen', 4, 1813, 1, 2),
(14, 'Emma', 'Jane Austen', 4, 1813, 2, 2),
(15, 'Sense and Sensibility', 'Jane Austen', 3, 1811, 1, 3),
(16, 'Presuación', 'Jane Austen', 10, 1811, 10, 3),
(17, 'Sense and Sensibility', 'Jane Austen', 10, 1811, 9, 3),
(19, 'Violeta', 'Isabel Allende', 5, 2022, 7, 3),
(22, 'Harry Potter y el prisionero de Azkaban', '7', 6, 1999, 7, 3),
(23, 'Harry Potter y la Orden del Fénix', '8', 6, 2000, 8, 3),
(24, 'Harry Potter y el cáliz de fuego', '8', 6, 2000, 8, 3),
(25, 'Harry Potter y el misterio del príncipe', 'J. K. Rowling', 9, 2005, 5, 3),
(26, 'Harry Potter y las reliquias de la Muerte', 'J. K. Rowling', 9, 2007, 5, 3),
(27, 'Harry Potter y el legado maldito', 'J. K. Rowling', 9, 2016, 5, 3),
(28, 'Presuación', 'Jane Austen', 10, 1811, 4, 3),
(29, 'Sense and Sensibility', 'Jane Austen', 10, 1811, 5, 3),
(30, 'Los Watsons ', 'Jane Austen', 10, 1805, 3, 5),
(32, 'Harry Potter y la piedra filosofal', 'J. K. Rowling', 5, 1997, 5, 2),
(33, 'Harry Potter y la cámara secreta', '6', 6, 1998, 6, 3),
(34, 'Harry Potter y el prisionero de Azkaban', '7', 6, 1999, 8, 2),
(35, 'Harry Potter y la Orden del Fénix', '8', 6, 2000, 10, 2),
(36, 'Harry Potter y el cáliz de fuego', '8', 6, 2000, 3, 2),
(37, 'Harry Potter y el misterio del príncipe', 'J. K. Rowling', 5, 2005, 6, 2),
(38, 'Harry Potter y las reliquias de la Muerte', 'J. K. Rowling', 9, 2007, 8, 2),
(39, 'Harry Potter y el legado maldito', 'J. K. Rowling', 9, 2016, 7, 2),
(42, 'NuevoLibro', 'Geydi', 2, 2017, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plazosprestamos`
--

CREATE TABLE `plazosprestamos` (
  `idPrestamo` int NOT NULL,
  `clasificacion` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `cantidadDias` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plazosprestamos`
--

INSERT INTO `plazosprestamos` (`idPrestamo`, `clasificacion`, `cantidadDias`) VALUES
(1, 'Puntual', 7),
(2, 'Normal', 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `idPrestamoLibro` int NOT NULL,
  `idUsuario` int NOT NULL,
  `idLibros` int NOT NULL,
  `FechaPrestamo` date NOT NULL,
  `tipoPrestamo` int DEFAULT NULL,
  `FechaEntrega` date DEFAULT NULL,
  `Estado` varchar(250) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'En curso',
  `Detalle` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'En este campo pueden detallar informacion sobre el prestamo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`idPrestamoLibro`, `idUsuario`, `idLibros`, `FechaPrestamo`, `tipoPrestamo`, `FechaEntrega`, `Estado`, `Detalle`) VALUES
(1, 2, 2, '2024-02-01', 0, '2024-02-01', 'Devuelto', ''),
(2, 6, 3, '2024-04-01', 3, '2024-04-04', 'En curso', ''),
(3, 6, 2, '2024-04-01', 7, '2024-04-08', 'Devuelto', 'todo ok'),
(5, 1, 1, '2024-02-01', NULL, NULL, 'Devuelto', NULL),
(7, 10, 8, '2024-05-01', NULL, NULL, 'Devuelto', 'sadsad<zx<xsdasdasda'),
(11, 2, 1, '2024-05-03', NULL, '2024-05-20', 'En curso', 'ok');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservalibro`
--

CREATE TABLE `reservalibro` (
  `idReserva` int NOT NULL,
  `UsuarioReserva` int NOT NULL,
  `idLibroReserva` int NOT NULL,
  `FechaReserva` date NOT NULL,
  `Comentarios` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Estado` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'En curso'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservalibro`
--

INSERT INTO `reservalibro` (`idReserva`, `UsuarioReserva`, `idLibroReserva`, `FechaReserva`, `Comentarios`, `Estado`) VALUES
(1, 11, 7, '2024-05-02', NULL, 'Cancelada'),
(8, 10, 24, '2024-05-02', NULL, 'En curso'),
(9, 14, 2, '2024-05-19', NULL, 'Cancelada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `IdRol` int NOT NULL,
  `NombreRol` varchar(250) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`IdRol`, `NombreRol`) VALUES
(1, 'Administrador'),
(2, 'Cliente'),
(3, 'Gestor'),
(4, 'Otro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Apellido` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Rol` int NOT NULL,
  `CorreoElectronico` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `contrasenna` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Telefono` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Ciudad` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Pais` varchar(250) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `Nombre`, `Apellido`, `Rol`, `CorreoElectronico`, `contrasenna`, `Telefono`, `Ciudad`, `Pais`) VALUES
(1, 'Geydi', 'Ramos', 1, 'geydi2210@gmail.com', '', '622737801', 'Madrid', 'España'),
(2, 'Adrian', 'Fumero', 3, 'adri@gmail.com', 'R2V5ZGkyMjEw', '622737870', 'Madrid', 'España'),
(6, 'Marisol', 'Diaz', 2, 'mari@gmail.com', '', '623678899', 'Madrid', 'España'),
(10, 'geydiPrueba', 'dsfsdf', 1, 'asdas@prueba.com', 'R2V5ZGkyMjEw', 'fdsdf3435dddd', 'Madriddddd', 'Españaaaaa'),
(11, 'Geydi', 'Ramos Diaz de Villegas', 2, 'geydi2210_prueba@prueba.es', 'R2V5ZGkyMjEw', '33333', 'Barcelona', 'España'),
(12, 'Lau', 'Lopez', 2, 'khsghk', 'MTIzNA==', '6789443', 'Madrid', 'España'),
(14, 'Pedro', 'Gonzalez', 2, 'pedro@gmail.com', 'cGVkcm8xMjM0Kg==', '633454667', 'Madrid', 'España');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_listausuarios`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_listausuarios` (
`Apellido` varchar(250)
,`Ciudad` varchar(250)
,`CorreoElectronico` varchar(250)
,`idRol` int
,`idUsuario` int
,`Nombre` varchar(250)
,`NombreRol` varchar(250)
,`Pais` varchar(250)
,`Telefono` varchar(250)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_misprestamos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_misprestamos` (
`DireccionBiblioteca` varchar(250)
,`EstadoPrestamo` varchar(250)
,`FechaEntrega` date
,`FechaReserva` date
,`idLibroPrestamo` int
,`idPrestamo` int
,`NombreBiblioteca` varchar(250)
,`NombreLibro` varchar(250)
,`StockLibro` int
,`Usuario` int
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_misreservas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_misreservas` (
`AnnoLibro` int
,`AutorLibro` varchar(250)
,`DireccionBiblioteca` varchar(250)
,`EdicionLibro` int
,`EstadoReserva` varchar(100)
,`Existencia` int
,`FechaReserva` date
,`idReservaLibro` int
,`NombreBiblioteca` varchar(250)
,`NombreLibro` varchar(250)
,`UsuarioCorreoElectronico` varchar(250)
,`UsuarioReserva` int
);

-- --------------------------------------------------------

--
-- Estructura para la vista `existencialibros`
--
DROP TABLE IF EXISTS `existencialibros`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `existencialibros`  AS SELECT `libros`.`Nombre` AS `Nombre`, `libros`.`Autor` AS `Autor`, `libros`.`Edicion` AS `Edicion`, `libros`.`Anno` AS `Anno`, `biblioteca`.`NombreBibliot` AS `NombreBibliot`, `biblioteca`.`Direccion` AS `Direccion` FROM (`libros` left join `biblioteca` on((`biblioteca`.`idBiblioteca` = `libros`.`UbicBiblioteca`))) WHERE (`libros`.`Stock` > 0) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_listausuarios`
--
DROP TABLE IF EXISTS `v_listausuarios`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_listausuarios`  AS SELECT `usuarios`.`idUsuario` AS `idUsuario`, `usuarios`.`Nombre` AS `Nombre`, `usuarios`.`Apellido` AS `Apellido`, `roles`.`NombreRol` AS `NombreRol`, `usuarios`.`Rol` AS `idRol`, `usuarios`.`CorreoElectronico` AS `CorreoElectronico`, `usuarios`.`Telefono` AS `Telefono`, `usuarios`.`Ciudad` AS `Ciudad`, `usuarios`.`Pais` AS `Pais` FROM (`usuarios` join `roles` on((`usuarios`.`Rol` = `roles`.`IdRol`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_misprestamos`
--
DROP TABLE IF EXISTS `v_misprestamos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_misprestamos`  AS SELECT `libros`.`idLibros` AS `idLibroPrestamo`, `libros`.`Stock` AS `StockLibro`, `prestamos`.`idPrestamoLibro` AS `idPrestamo`, `prestamos`.`idUsuario` AS `Usuario`, `libros`.`Nombre` AS `NombreLibro`, `prestamos`.`Estado` AS `EstadoPrestamo`, `prestamos`.`FechaPrestamo` AS `FechaReserva`, `prestamos`.`FechaEntrega` AS `FechaEntrega`, `biblioteca`.`NombreBibliot` AS `NombreBiblioteca`, `biblioteca`.`Direccion` AS `DireccionBiblioteca` FROM ((`prestamos` join `libros` on((`prestamos`.`idLibros` = `libros`.`idLibros`))) join `biblioteca` on((`libros`.`UbicBiblioteca` = `biblioteca`.`idBiblioteca`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_misreservas`
--
DROP TABLE IF EXISTS `v_misreservas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_misreservas`  AS SELECT `reservalibro`.`idReserva` AS `idReservaLibro`, `reservalibro`.`UsuarioReserva` AS `UsuarioReserva`, `usuarios`.`CorreoElectronico` AS `UsuarioCorreoElectronico`, `reservalibro`.`FechaReserva` AS `FechaReserva`, `reservalibro`.`Estado` AS `EstadoReserva`, `libros`.`Nombre` AS `NombreLibro`, `libros`.`Autor` AS `AutorLibro`, `libros`.`Edicion` AS `EdicionLibro`, `libros`.`Anno` AS `AnnoLibro`, `libros`.`Stock` AS `Existencia`, `biblioteca`.`NombreBibliot` AS `NombreBiblioteca`, `biblioteca`.`Direccion` AS `DireccionBiblioteca` FROM (((`reservalibro` join `libros` on((`libros`.`idLibros` = `reservalibro`.`idLibroReserva`))) join `usuarios` on((`usuarios`.`idUsuario` = `reservalibro`.`UsuarioReserva`))) join `biblioteca` on((`libros`.`UbicBiblioteca` = `biblioteca`.`idBiblioteca`))) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `biblioteca`
--
ALTER TABLE `biblioteca`
  ADD PRIMARY KEY (`idBiblioteca`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`idLibros`),
  ADD KEY `Libros_Biblioteca` (`UbicBiblioteca`);

--
-- Indices de la tabla `plazosprestamos`
--
ALTER TABLE `plazosprestamos`
  ADD PRIMARY KEY (`idPrestamo`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`idPrestamoLibro`),
  ADD KEY `PrestamoUsuario` (`idUsuario`),
  ADD KEY `PrestamosLibros` (`idLibros`);

--
-- Indices de la tabla `reservalibro`
--
ALTER TABLE `reservalibro`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `ReservaUsuario` (`UsuarioReserva`),
  ADD KEY `ReservaLibro` (`idLibroReserva`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`IdRol`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `CorreoElectronico` (`CorreoElectronico`),
  ADD KEY `UsuarioRol` (`Rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `biblioteca`
--
ALTER TABLE `biblioteca`
  MODIFY `idBiblioteca` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `idLibros` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `plazosprestamos`
--
ALTER TABLE `plazosprestamos`
  MODIFY `idPrestamo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `idPrestamoLibro` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `reservalibro`
--
ALTER TABLE `reservalibro`
  MODIFY `idReserva` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `IdRol` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `Libros_Biblioteca` FOREIGN KEY (`UbicBiblioteca`) REFERENCES `biblioteca` (`idBiblioteca`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `PrestamosLibros` FOREIGN KEY (`idLibros`) REFERENCES `libros` (`idLibros`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `PrestamoUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `reservalibro`
--
ALTER TABLE `reservalibro`
  ADD CONSTRAINT `ReservaLibro` FOREIGN KEY (`idLibroReserva`) REFERENCES `libros` (`idLibros`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `ReservaUsuario` FOREIGN KEY (`UsuarioReserva`) REFERENCES `usuarios` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `UsuarioRol` FOREIGN KEY (`Rol`) REFERENCES `roles` (`IdRol`) ON DELETE RESTRICT ON UPDATE RESTRICT;

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `actualizarFecha` ON SCHEDULE EVERY 1 DAY STARTS '2024-05-01 09:20:21' ENDS '2024-05-31 22:26:32' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE prestamos SET prestamos.FechaEntrega = prestamos.FechaPrestamo + prestamos.tipoPrestamo$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
