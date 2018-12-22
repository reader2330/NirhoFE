-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: nirhodb
-- ------------------------------------------------------
-- Server version	5.7.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividades_puesto_vacante`
--

DROP TABLE IF EXISTS `actividades_puesto_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actividades_puesto_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion_act_puesto_vacante` varchar(150) NOT NULL,
  `nivel` int(11) NOT NULL,
  `nombre_act_puesto_vacante` varchar(150) NOT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ruac94kepn03lqtt532oi6qa` (`id_vacante`),
  CONSTRAINT `FK5ruac94kepn03lqtt532oi6qa` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades_puesto_vacante`
--

LOCK TABLES `actividades_puesto_vacante` WRITE;
/*!40000 ALTER TABLE `actividades_puesto_vacante` DISABLE KEYS */;
INSERT INTO `actividades_puesto_vacante` VALUES (1,'ldkjñdkj oeireou ldk,smv',39,'Diseñar aterial de difución',1),(2,'VENTAS EN BOUTIQUE',39,'ATENCIÓN, SERVICIO Y \nRECEPCIÓN A CLIENTES',2),(3,'MANEJO DE CAJA',39,'MANEJO DE CAJA',2),(4,'ACOMODO Y ETIQUETADO DE MERCANCÍAS',37,'ACOMODO Y ETIQUETADO DE MERCANCÍAS',2),(5,'INVENTARIOS',39,'INVENTARIOS',2),(6,'CUIDAR IMAGEN Y LIMPIEZA DE BOUTIQUE',39,'CUIDAR IMAGEN Y LIMPIEZA DE BOUTIQUE',2);
/*!40000 ALTER TABLE `actividades_puesto_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apo_role_modulo_submodulo`
--

DROP TABLE IF EXISTS `apo_role_modulo_submodulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `apo_role_modulo_submodulo` (
  `id` bigint(20) NOT NULL,
  `id_modulo` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_submodulo` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apo_role_modulo_submodulo`
--

LOCK TABLES `apo_role_modulo_submodulo` WRITE;
/*!40000 ALTER TABLE `apo_role_modulo_submodulo` DISABLE KEYS */;
INSERT INTO `apo_role_modulo_submodulo` VALUES (1,4,1,1,'Bandeja de proyectos'),(2,4,1,2,'Alta de proyectos'),(3,4,2,1,'Bandeja de proyectos'),(5,4,2,3,'Carga de Head Count'),(6,4,2,4,'Carga de Head Count Ampliado'),(7,4,2,5,'Ver participantes'),(8,4,2,6,'Periodo de garantía'),(9,4,2,7,'Asignar consultor'),(10,4,2,8,'Ver estado de proyecto'),(11,4,3,1,'Bandeja de proyectos'),(12,4,3,2,'Alta de proyectos'),(13,4,3,3,'Carga de Head Count'),(14,4,3,4,'Carga de Head Count Ampliado'),(15,4,3,5,'Ver participantes');
/*!40000 ALTER TABLE `apo_role_modulo_submodulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignacion_consultor_vacante`
--

DROP TABLE IF EXISTS `asignacion_consultor_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `asignacion_consultor_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` int(11) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqcg2tr3qe9o4glvuufwe7pftk` (`id_usuario`),
  KEY `FKhje643c40ttxd2cinf9h1jtf8` (`id_vacante`),
  CONSTRAINT `FKhje643c40ttxd2cinf9h1jtf8` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`),
  CONSTRAINT `FKqcg2tr3qe9o4glvuufwe7pftk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion_consultor_vacante`
--

LOCK TABLES `asignacion_consultor_vacante` WRITE;
/*!40000 ALTER TABLE `asignacion_consultor_vacante` DISABLE KEYS */;
INSERT INTO `asignacion_consultor_vacante` VALUES (1,0,7,1);
/*!40000 ALTER TABLE `asignacion_consultor_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidato`
--

DROP TABLE IF EXISTS `candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `candidato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `disponibilidad` varchar(255) DEFAULT NULL,
  `estado` bigint(20) NOT NULL,
  `nacimiento` varchar(255) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  `pretencion` varchar(255) DEFAULT NULL,
  `rfc` varchar(150) NOT NULL,
  `situacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dqy0j0i4xt3asldaeefnqokxk` (`rfc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidato`
--

LOCK TABLES `candidato` WRITE;
/*!40000 ALTER TABLE `candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caracteristicas_candidato_cv`
--

DROP TABLE IF EXISTS `caracteristicas_candidato_cv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `caracteristicas_candidato_cv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cambio_residencia` varchar(150) NOT NULL,
  `caract_adicionales` varchar(150) NOT NULL,
  `carrera` varchar(150) NOT NULL,
  `certificaciones` varchar(150) NOT NULL,
  `cursos` varchar(150) NOT NULL,
  `discapacitados` varchar(150) NOT NULL,
  `dispo_viajar` varchar(150) NOT NULL,
  `edad_rango` int(11) NOT NULL,
  `especialidad` varchar(150) NOT NULL,
  `estado_civil` varchar(150) NOT NULL,
  `genero` varchar(150) NOT NULL,
  `nivel_estudios` int(11) NOT NULL,
  `o_capacidades` varchar(150) NOT NULL,
  `oficios` varchar(150) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `id_candidato` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKri6wo944buvk9noyj8nps7a62` (`id_candidato`),
  CONSTRAINT `FKri6wo944buvk9noyj8nps7a62` FOREIGN KEY (`id_candidato`) REFERENCES `candidato` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caracteristicas_candidato_cv`
--

LOCK TABLES `caracteristicas_candidato_cv` WRITE;
/*!40000 ALTER TABLE `caracteristicas_candidato_cv` DISABLE KEYS */;
/*!40000 ALTER TABLE `caracteristicas_candidato_cv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caracteristicas_candidato_vacante`
--

DROP TABLE IF EXISTS `caracteristicas_candidato_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `caracteristicas_candidato_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cambio_residencia` varchar(150) NOT NULL,
  `caract_adicionales` varchar(150) NOT NULL,
  `carrera` varchar(150) NOT NULL,
  `certificaciones` varchar(150) NOT NULL,
  `cursos` varchar(150) NOT NULL,
  `discapacitados` varchar(150) NOT NULL,
  `dispo_viajar` varchar(150) NOT NULL,
  `edad_rango` tinyblob NOT NULL,
  `especialidad` varchar(150) NOT NULL,
  `estado_civil` varchar(150) NOT NULL,
  `genero` varchar(150) NOT NULL,
  `nivel_estudios` int(11) NOT NULL,
  `o_capacidades` varchar(150) NOT NULL,
  `oficios` varchar(150) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKay3j3elx947gmnflbeyywv943` (`id_vacante`),
  CONSTRAINT `FKay3j3elx947gmnflbeyywv943` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caracteristicas_candidato_vacante`
--

LOCK TABLES `caracteristicas_candidato_vacante` WRITE;
/*!40000 ALTER TABLE `caracteristicas_candidato_vacante` DISABLE KEYS */;
INSERT INTO `caracteristicas_candidato_vacante` VALUES (1,'Indistinto','No aplica','Lic. en Diseño gráfico','No aplica','Construcción de paginas web\nsilver light','Indistinto','No',_binary '͜ur\0[IM۠&v겥\0\0xp\0\0\0\0\0\0\0\0\0(','Con tenido web','Indistinto','Indistinto',9,'No aplica','No aplica','Si',1);
/*!40000 ALTER TABLE `caracteristicas_candidato_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo`
--

DROP TABLE IF EXISTS `catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `catalogo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion_catalogo` varchar(255) NOT NULL,
  `estado` int(11) NOT NULL,
  `narrativa_catalogo` varchar(2000) DEFAULT NULL,
  `id_tipo_catalogo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKti310na9pms7qtev49u4fofc0` (`id_tipo_catalogo`),
  CONSTRAINT `FKti310na9pms7qtev49u4fofc0` FOREIGN KEY (`id_tipo_catalogo`) REFERENCES `tipo_catalogo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=273 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo`
--

LOCK TABLES `catalogo` WRITE;
/*!40000 ALTER TABLE `catalogo` DISABLE KEYS */;
INSERT INTO `catalogo` VALUES (1,'MEXICO',1,'NULL',1),(2,'USA',1,'NULL',1),(3,'ALEMANIA',1,'NULL',1),(4,'CANADA',1,'NULL',1),(5,'BRASIL',1,'NULL',1),(6,'PANAMA',1,'NULL',1),(7,'ARGENTINA',1,'NULL',1),(8,'ESPAÑA',1,'NULL',1),(9,'FRANCIA',1,'NULL',1),(10,'CUBA',1,'NULL',1),(11,'CHINA',1,'NULL',1),(12,'JAPON',1,'NULL',1),(13,'INDIA',1,'NULL',1),(14,'HONDURAS',1,'NULL',1),(15,'VENEZUELA',1,'NULL',1),(16,'Empresas de Servicios',1,'NULL',2),(17,'Empresas Industriales',1,'NULL',2),(18,'Empresa Comerciales',1,'NULL',2),(19,'Celular',1,'NULL',3),(20,'Email',1,'NULL',3),(21,'Teléfono',1,'NULL',3),(22,'Red social',1,'NULL',3),(23,'FaceBook',1,'NULL',3),(24,'Linkedin',1,'NULL',3),(25,'Alemán',1,'NULL',4),(26,'Español',1,'NULL',4),(27,'Francés',1,'NULL',4),(28,'Inglés',1,'NULL',4),(29,'Japonés',1,'NULL',4),(30,'Portugués',1,'NULL',4),(31,'Otro',1,'NULL',4),(32,'Básico',1,'NULL',5),(33,'Intermedio',1,'NULL',5),(34,'Avanzado',1,'NULL',5),(35,'Bilingüe',1,'NULL',5),(36,'Técnico',1,'NULL',5),(37,'Nativo',1,'Primera lengua hablada, total dominio de ella',5),(38,'Nulo',1,'NULL',6),(39,'Suficiente',1,'NULL',6),(40,'Medio',1,'NULL',6),(41,'Alto',1,'NULL',6),(42,'Superior',1,'NULL',6),(43,'Contacto de cobranza',1,'NULL',7),(44,'Líder de proyecto',1,'NULL',7),(45,'Entrevistador',1,'NULL',7),(46,'Nulo',1,'NULL',8),(47,'Suficiente',1,'NULL',8),(48,'Medio',1,'NULL',8),(49,'Alto',1,'NULL',8),(50,'Superior',1,'NULL',8),(51,'Actitud de servicio',1,'Disposición de actuar, sentir y/o pensar en torno a las necesidades del cliente para lo cual dirige toda sus acciones como estrategia para garantizar la satisfacción de los mismos.',9),(52,'Adaptación al medio',1,'Ajustarse a los posibles cambios laborales, sociales y personales, percibiéndolos como oportunidades de aprendizaje.',9),(53,'Análisis de problemas',1,'Capacidad para trascender de lo lógico a lo abstracto y encontrar las verdaderas causas y soluciones de una situación o problema especifico; cuyos resultados son sustentados con un alto nivel de eficacia y confiabilidad.',9),(54,'Comunicación efectiva',1,'Es la competencia que posee el líder para escuchar, entender y valorar con empatía información, ideas y opiniones que su equipo le comunique, siendo capaz de retroalimentar asertivamente el proceso comunicativo.',9),(55,'Comunicación interpersonal',1,'Capacidad para generar y mantener un flujo de comunicación adecuado entre los miembros del grupo o de la organización, utilizando los distintos canales que en cada caso se requieran; además, favorecer el establecimiento de relaciones mutuamente benefi...',9),(56,'Administración del tiempo',1,'Capacidad para establecer con criterio prioridades a la hora de ejecutar esquemas, basándose en la visión proyectada para planificar estrategias que minimicen el tiempo de la actividad y optimicen el desarrollo de las tareas.',9),(57,'Análisis de problemas',1,'Capacidad para trascender de lo lógico a lo abstracto y encontrar las verdaderas causas y soluciones de una situación o problema especifico; cuyos resultados son sustentados con un alto nivel de eficacia y confiabilidad.',9),(58,'Análisis numérico',1,'Capacidad para trascender de lo lógico a lo abstracto y encontrar las verdaderas causas y soluciones de una situación o problema especifico; cuyos resultados son sustentados con un alto nivel de eficacia y confiabilidad.',9),(59,'Aptitud verbal',1,'Habilidad para expresarse eficazmente dando muestras de manejar un lenguaje profesional y técnico con un tono muy espontáneo, acorde a su nivel de formación y experiencia, así como del nivel del cargo que ocupe, lo que incide directamente en el nivel...',9),(60,'Atención al cliente',1,'Exceder las expectativas del cliente demostrando un compromiso total en la identificación de cualquier problema y proporcionando las soluciones efectivas para la solución del mismo.',9),(61,'Autoconfianza',1,'Capacidad para demostrar un alto nivel de seguridad y confianza en las capacidades de si mismo, aplicándolas al logro de los objetivos fijados, movilizando todo su potencial cognitivo y emocional, convencido de que el éxito depende de sí mismo y de c...',9),(62,'Autocontrol',1,'Capacidad de mantener las propias emociones bajo control y evitar reacciones negativas ante provocaciones, oposición u hostilidad por parte de otros o bajo condiciones de estrés.',9),(63,'Autodesarrollo',1,'Es la competencia relacionada con el crecimiento personal y el fortalecimiento de las capacidades personales. Capacidad para aprovechar las oportunidades de aprender de la propia experiencia o de la de otros o de lo que se realiza en el entorno. Buscar h...',9),(64,'Automotivación',1,'Disposición para demostrar una alta capacidad de emprendimiento para realizar las actividades por encima de las exigencias establecidas, mejorar e incrementar los resultados, evitar problemas o encontrar nuevas oportunidades.',9),(65,'Asunción de riesgos',1,'Capacidad para emprender y asumir acciones que envuelvan un riesgo deliberado con el objeto de lograr un beneficio o una ventaja importante.',9),(66,'Búsqueda de información',1,'Inquietud y curiosidad por buscar información más allá de las preguntas rutinarias o de lo que se requiere en el puesto.',9),(67,'Búsqueda de la excelencia',0,'Es el compromiso con la eficiencia y eficacia. Las personas que poseen esta actitud se caracterizan porque: Hacen su trabajo cada día mejor, aún si tienen que asumir más trabajo. No están satisfechas con las cosas como están y buscan mejorarlas. No ...',9),(68,'Capacidad de análisis',1,'Capacidad para identificar, comprender y evaluar las diferentes variables que inciden en la consecución de un objetivo, determinando las posibles alternativas de viabilidad y teniendo en cuenta su repercusión en los niveles de calidad y eficiencia esp...',9),(69,'Capacidad para aprender',1,'Habilidad para adquirir y asimilar nuevos conocimientos y destrezas con el fin de utilizarlos en la práctica laboral. Capacidad para captar y asimilar con facilidad conceptos e informaciones simples y complejas.',9),(70,'Capacidad crítica',1,'Habilidad para la evaluación de datos y líneas de actuación, así como para tomar decisiones lógicas de una manera imparcial y desde un punto de vista racional.',9),(71,'Capacidad de decisión',1,'Disposición y habilidad para tomar decisiones acertadas basadas en análisis propios de la situación, logrando asumir con responsabilidad y madurez los riesgos del caso.',9),(72,'Capacidad de gestión',1,'Es la habilidad que tiene la persona para gestionar las tareas y procesos a su cargo en forma rápida y confiable; haciendo uso de la recursividad y dinamismo que requiere el hacer que las cosas resulten.',9),(73,'Capacidad de negociación',1,'Capacidad para llegar a acuerdos ventajosos a través del intercambio de información, debate de ideas y utilización de estrategias efectivas con personas o grupos que puedan representar de alto interés para la organización.',9),(74,'Comprensión interpersonal',1,'Es la habilidad para escuchar y entender correctamente los pensamientos, sentimientos o preocupaciones de los demás aunque no se expresen verbalmente o se expresen parcialmente, pero que requieren ser captados por los demás.',9),(75,'Compromiso',1,'Esfuerzo permanente hacia la consecución de un objetivo, lo cual implica un alto grado de integración de la disposición física, emocional e intelectual de un sujeto sobre lo que desea conseguir, sea a beneficio propio o común.',9),(76,'Comunicación efectiva',1,'Es la competencia que posee el líder para escuchar, entender y valorar con empatía información, ideas y opiniones que su equipo le comunique, siendo capaz de retroalimentar asertivamente el proceso comunicativo.',9),(77,'Comunicación escrita',1,'Capacidad para redactar las ideas claramente y de forma gramaticalmente correcta de manera que sean entendidas sin lugar a dudas.',9),(78,'Comunicación interpersonal',1,'Capacidad para generar y mantener un flujo de comunicación adecuado entre los miembros del grupo o de la organización, utilizando los distintos canales que en cada caso se requieran; además, favorecer el establecimiento de relaciones mutuamente benefi...',9),(79,'Comunicación oral persuasiva',1,'Capacidad para expresar ideas o hechos claramente y de una manera persuasiva. Convencer a los otros del punto de vista propio.',9),(80,'Conocimiento del entorno:',1,'Es la competencia que posee el líder para incluir en la toma de decisiones aquellas variables que afectan directa e indirectamente el normal desempeño de la organización y que permitan una ventaja competitiva de la misma en el sector, siendo congruent...',9),(81,'Conocimiento organizacional',1,'Capacidad para comprender e interpretar las situaciones que afectan directamente a la realidad organizacional, así como para demostrar el alto nivel de conocimiento de todos los procesos operativos, funcionales y de servicio de la compañía como estrat...',9),(82,'Creatividad',1,'Capacidad para proponer soluciones y/o alternativas novedosas e imaginativas para el mejoramiento de procesos funcionales, estrategias promociónales, entre otras. Las personas que poseen esta habilidad se caracterizan por que proponen y encuentran forma...',9),(83,'Defensa de los demás',1,'Disposición para actuar a favor o en beneficio de los demás sin que ello se les haya pedido.',9),(84,'Delegación',1,'Capacidad para distribuir eficazmente la toma de decisiones y de otras responsabilidades hacia el subordinado más adecuado.',9),(85,'Desarrollo de interrelaciones',1,'Capacidad para establecer y mantener relaciones cordiales, recíprocas y cálidas o redes de contacto con distintas personas.',9),(86,'Desarrollo de personas',1,'Capacidad para dirigir el proceso de aprendizaje o el desarrollo de los demás a partir de un apropiado análisis de sus necesidades y de la organización. Se centra en el interés por desarrollar a las personas, no en el de proporcionar formación.',9),(87,'Disponibilidad',1,'Capacidad para trabajar eficazmente en distintas y variadas situaciones.',9),(88,'Dirección de personas',1,'Capacidad para hacer que otras personas actúen según nuestros deseos utilizando apropiada y adecuadamente el poder personal o la autoridad que el puesto nos confiere. Incluye el \"decir a los demás lo que tienen que hacer\".',9),(89,'Efectividad',1,'Capacidad para lograr los máximos resultados de calidad con el mínimo agotamiento del recurso humano y técnico, utilizando la comunicación efectiva, la motivación y la participación conjunta de sus colaboradores.',9),(90,'Energía',1,'Capacidad para crear y mantener un ritmo de actividad indicado. Muestra el control, la resistencia y la capacidad de trabajar arduamente.',9),(91,'Entusiasmo',1,'Es la energía y la disposición que se tiene para realizar una labor, colmada por un positivismo y optimismo único, bajo el convencimiento y el dinamismo necesario orientado hacia la consecución de un objetivo particular.',9),(92,'Escucha',1,'Disposición y capacidad para recibir y entender cualquier tipo de información importante de una comunicación oral entre dos o varias personas.',9),(93,'Espíritu comercial',0,'Capacidad para entender aquellos puntos claves del negocio que afectan a la rentabilidad y al crecimiento de una empresa y para actuar de manera persistente para alcanzar el éxito comercial a su cargo.',9),(94,'Espíritu emprendedor',1,'Es la competencia que le permite al líder la búsqueda constante de oportunidades en el entorno para garantizar la viabilidad de los objetivos empresariales generando en sus colaboradores el mismo espíritu gestor de nuevas acciones que propendan por un...',9),(95,'Espíritu emprendedor',1,'Es la competencia que le permite al líder la búsqueda constante de oportunidades en el entorno para garantizar la viabilidad de los objetivos empresariales generando en sus colaboradores el mismo espíritu gestor de nuevas acciones que propendan por un...',9),(96,'Ética',1,'Hace referencia a la interiorización de normas y principios morales que hacen responsable al individuo de su propio bienestar y del de los demás mediante un comportamiento basado en conductas socialmente aceptadas. Las personas que poseen esta competen...',9),(97,'Empoderamiento',1,'Es la capacidad para asumir una responsabilidad con un profundo sentido de compromiso y autonomía personal. Competencia del líder que propicia la participación de su grupo de trabajo, haciendo que estos hagan contribuciones importantes, sean creativos...',9),(98,'Flexibilidad',1,'Es la habilidad de adaptarse y trabajar eficazmente en distintas y variadas situaciones y con personas o grupos diversos. Incluye una disposición a cambiar de enfoque o de la manera de concebir la realidad, buscando una mejor forma de hacer las cosas; a...',9),(99,'Gestión del cambio',1,'Es la capacidad para diagnosticar, diseñar y dirigir procesos de cambio específicos que contribuyan significativamente a la productividad grupal y empresarial.',9),(100,'Gestión de recursos',1,'Capacidad para optimizar y rentabilizar los recursos humanos, técnicos y económicos de que se dispone, con el objetivo de mejorar los procesos, procedimientos y métodos de trabajo y contribuir a la eficacia y agilidad de los sistemas de gestión.',9),(101,'Gestión de conflictos',1,'Capacidad para resolver eficazmente situaciones, hechos o conflictos en los que se ponen en juego intereses que pueden afectar a las relaciones entre personas o poner en riesgo los objetivos, los intereses o la imagen de la organización.',9),(102,'Habilidad de control',1,'Capacidad para diseñar estrategias que conlleven a controlar los métodos, personas y asuntos que requieran de medidas de seguimiento; implica la toma decisiones que aseguren este control.',9),(103,'Identificación con la compañía',1,'Capacidad y voluntad de orientar los propios intereses y comportamientos hacia las necesidades, prioridades y objetivos de la compañía.',9),(104,'Impacto',1,'Se traduce en causar buena impresión en los otros y mantener esa impresión a lo largo del tiempo.',9),(105,'Innovación',1,'Es la competencia que posee el líder para concebir y realizar tareas nuevas e inexistentes con el propósito de diseñar y generar nuevos procesos con mayores niveles de rentabilidad y eficiencia.',9),(106,'Influencia',1,'Implica la atención de persuadir, convencer, influir o impresionar a los demás para que contribuyan a alcanzar los objetivos propios. Está basada en el deseo de causar un efecto específico en los demás, una impresión determinada o una actuación c...',9),(107,'Iniciativa',1,'Capacidad para actuar de manera preactiva ante determinada situación. Incluye saber identificar un problema - obstáculo u oportunidad y llevar a cabo acciones que contribuyan a su solución.',9),(108,'Integridad',1,'Capacidad para mantenerse dentro de las normas éticas y morales socialmente aceptadas; así como de actuar en consonancia con lo que cada uno considera importante. Incluye el comunicar las intenciones, ideas y sentimientos abierta y directamente, y el e...',9),(109,'Interés por mantenerse actualizado',1,'Es la competencia que posee el líder referida al aprendizaje continuo de procesos que permiten implementar nuevos conceptos y metodologías. Compromiso con la promoción del aprendizaje organizacional.',9),(110,'Interactuar/interrelacionarse',1,'Capacidad para establecer, mantener y potenciar relaciones de valor en el trabajo con personas y grupos, tanto internos como externos, haciendo de estas relaciones un cauce para el logro y alineamiento estratégico de los objetivos de la organización.',9),(111,'Lealtad y sentido de pertenencia',1,'Se refiere a defender y promulgar los intereses de las organizaciones donde laboran como si fueran propios. Se aprecia gran sentido de identificación con los objetivos de la organización a tal forma que suelen anteponer los intereses organizacionales a...',9),(112,'Liderazgo',1,'Capacidad para dirigir a las personas y lograr que éstas contribuyan de forma efectiva y adecuada a la consecución de los objetivos. Comprometerse en el desarrollo de sus colaboradores, su evaluación y la utilización del potencial y las capacidades i...',9),(113,'Manejo de la incertidumbre y la complejidad',1,'Capacidad para discernir, evaluar con objetividad y tomar decisiones efectivas en escenarios caracterizados por el riesgo, la incertidumbre y complejidad.',9),(114,'Meticulosidad',1,'Resolución total de una tarea o asunto hasta el final y en todas las áreas que envuelva, independientemente de su insignificancia.',9),(115,'Minuciosidad en la preparación (para una presentación)',1,'Resolución total de una tarea o asunto hasta el final y en todas las áreas que envuelva, independientemente de su insignificancia.',9),(116,'Negociación y manejo de conflictos',1,'Es la competencia que posee un líder para plantear soluciones y resolver diferencias de ideas u opiniones de las partes, apoyándose en la suficiente autoridad y justicia, centrándose en los intereses comunes, tratando de conciliar y mediar de manera e...',9),(117,'Niveles de trabajo',1,'Establecimiento de grandes metas o modelos de conducta para uno mismo, para los otros y para la empresa.',9),(118,'Orientación al cliente',1,'Implica un deseo de ayudar o servir a los clientes, de satisfacer sus necesidades, de dirigir todas sus acciones en la búsqueda de satisfacción de los mismos.',9),(119,'Orientación al logro',1,'Es la preocupación por realizar bien el trabajo, por cumplir con un objetivo propuesto. Por realizar algo único y excepcional.',9),(120,'Orientación al servicio',1,'Es la disposición para realizar el trabajo con base en el conocimiento de las necesidades y expectativas de los clientes externos e internos, reflejando un trato amable y cordial, interés por la persona y por la solución a sus problemas.',9),(121,'Orientación estratégica',1,'Es la habilidad de vincular visiones a largo plazo y diseñar acciones tenientes al diseño de planes estratégicos que consoliden la misión y la visión de la organización a corto, mediano y largo plazo.',9),(122,'Orientación estratégica',1,'Es la habilidad de vincular visiones a largo plazo y diseñar acciones tenientes al diseño de planes estratégicos que consoliden la misión y la visión de la organización a corto, mediano y largo plazo.',9),(123,'Pensamiento analítico',1,'Es la capacidad de entender una situación, desagregándola en pequeñas partes o identificando sus implicaciones paso a paso. Incluye el organizar las partes de un problema o situación de forma sistemática al realizar comparaciones entre diferentes el...',9),(124,'Pensamiento conceptual',1,'Capacidad para identificar en las situaciones pautas o relaciones que no son obvias o identificar puntos clave en situaciones complejas. Incluye la utilización de un razonamiento creativo, inductivo o conceptual.',9),(125,'Pensamiento creativo',1,'Capacidad para generar, descubrir y transformar nuevas ideas en soluciones útiles y eficaces aplicables en la organización.',9),(126,'Pensamiento estratégico',1,'Es la capacidad para determinar la posición competitiva de la empresa, mediante un análisis profundo de todos los factores internos y externos para establecer estrategias integrales que vinculen toda la organización.',9),(127,'Pensamiento ético',1,'Es la competencia que el líder posee para valorar los eventos internos y externos de la empresa basándose en los valores personales y morales, una mentalidad limpia y transparente a la hora de administrar; honestidad, equidad, cumplimiento de normas y ...',9),(128,'Pensamiento sistémico',1,'Es la competencia que posee el líder referenciada a un enfoque integral, que concibe el funcionamiento general de la empresa interrelacionando y afectando cada una de sus áreas y componentes, y que por lo tanto toda decisión incide en el sistema, en e...',9),(129,'Persistencia',1,'Es la tenacidad, la insistencia permanente para lograr un propósito y no desfallecer hasta conseguirlo.',9),(130,'Planificación y control',1,'Capacidad para determinar de forma eficaz, fases, etapas, metas y prioridades para la consecución de objetivos, a través del desarrollo de planes de acción, incluyendo los recursos necesarios y los sistemas de control.',9),(131,'Planificación y organización',1,'Capacidad para establecer eficazmente un orden apropiado de actuación personal o para terceros con el objetivo de alcanzar una meta.',9),(132,'Positivismo',1,'Capacidad de pensamiento que tiene un sujeto y que está directamente relacionado con la confianza en el éxito de un trabajo, una idea o tarea.',9),(133,'Preocupación por el desarrollo',1,'Implica la intención de fomentar el aprendizaje o desarrollo de las personas con un adecuado análisis de necesidades. El énfasis está más en la intención de desarrollar que en un rol formal en el área de formación.',9),(134,'Preocupación por el orden y la calidad',1,'Disposición por mantener patrones de organización y eficacia en todo lo que lo rodea en niveles personal y laboral.',9),(135,'Preocupación por la imagen y el impacto de la organización',1,'Ser conscientes de cómo los demás perciben nuestra imagen y nuestro rol en la organización y de la forma como esta influye en el nivel de aceptación y en la impresión misma de la empresa interna y externamente.',9),(136,'Profesionalismo',1,'Se refiere a divulgar los estándares de comportamiento a través de las propias acciones. Aquellos que tienen esta competencia mantienen una imagen de alto profesionalismo en todo lo que hacen.',9),(137,'Resistencia',1,'Capacidad para mantenerse eficaz en situaciones de decepción y/o rechazo.',9),(138,'Responsabilidad',1,'Hace referencia al compromiso, a un alto sentido del deber, al cumplimiento de las obligaciones en las diferentes situaciones de la vida.',9),(139,'Rigor profesional',1,'Capacidad para utilizar la información, las normas, los procedimientos y las políticas de la empresa con precisión y eficacia, con objeto de lograr los estándares de calidad, en tiempo y forma, con eficacia y eficiencia, en consonancia con los valore...',9),(140,'Sensibilidad hacia el cliente',1,'Capacidad para orientar y enfocar la actividad hacia las necesidades de los clientes internos o externos, en cualquier ámbito funcional de la empresa.',9),(141,'Sensibilidad interpersonal',1,'Conocimiento de los demás, del entorno y de sus necesidades.',9),(142,'Sensibilidad medioambiental',1,'Capacidad para enfocar la prestación de los servicios, mejorando y respetando las condiciones medioambientales, a través de la búsqueda de la compatibilidad entre las demandas sociales y las líneas estratégicas de la organización.',9),(143,'Sensibilidad organizacional',1,'Capacidad para implicarse en el Proyecto de Empresa, poniendo a disposición de ella los conocimientos y habilidades para la consecución de los objetivos establecidos.',9),(144,'Sentido de efectividad',1,'Capacidad para orientar la actividad en la consecución de los objetivos previstos e implicando de forma activa a los colaboradores en retos y metas de excelencia profesional y de calidad.',9),(145,'Utilización de otros como recurso',1,'Implica la intención de colaborar y cooperar con otros, formar parte del grupo, trabajar juntos, como opuesto a hacerlo individual o competitivamente.',9),(146,'Utilización de otros como recurso',1,'Se refiere a una compleja interacción con los empleados autónomos que trabajan a tiempo parcial para la empresa y demás personas que no están sujetos a influencias de poder por la posición que ocupan.',9),(147,'Utilización de relaciones',1,'Define cómo el personal comercial puede utilizar las relaciones familiares, profesionales o de amistad para conseguir negocios.',9),(148,'Visión de futuro',1,'Es la capacidad de visualizar las tendencias del medio con una actitud positiva y optimista, así orientar su conducta a la consecución de metas.',9),(149,'Visión empresarial',1,'Capacidad para anticiparse a las necesidades futuras con criterios estratégicos, simulando nuevos escenarios de actuación en mercados y productos, formulando los pasos a seguir a mediano y largo plazo, con objeto de encontrar oportunidades de negocio q...',9),(150,'Visión prospectiva',1,'Capacidad para visualizar el futuro hacia donde llegar, identificando estrategias, previendo consecuencias y anticipándose a los hechos que pueden generar riesgos en las acciones empresariales que se implementen',9),(151,'Femenino',1,'sexo',10),(152,'Masculino',1,'sexo',10),(153,'Indistinto',1,'sexo',10),(154,'Soltero',1,'Estado civil',11),(155,'Casado',1,'Estado civil',11),(156,'Unió Libre',1,'Estado civil',11),(157,'Divorciado',1,'Estado civil',11),(158,'Cuantitativa',1,'El encuestado seleecciona su respuesta en  una escala de 1 a 5',12),(159,'Cualitativa',1,'El encuestado seleccióna su respuesta de una serie de textos que tienen una abstracción lógica del nivel o grado que desea dar a su respuesta',12),(160,'Lógica',1,'Su respuesta es \"si\" o \"no\"',12),(161,'Abierta',1,'La respuesta es un texto libre donde el encestado puede dar su opinión',12),(162,'AMERICANA',1,'NULL',13),(163,'ARGENTINA',1,'NULL',13),(164,'BOLIVIANA',1,'NULL',13),(165,'CANADIENSE',1,'NULL',13),(166,'CHILENA',1,'NULL',13),(167,'CHINA',1,'NULL',13),(168,'COLOMBIANA',1,'NULL',13),(169,'CUBANA',1,'NULL',13),(170,'ESPAÑOLA',1,'NULL',13),(171,'FRANCESA',1,'NULL',13),(172,'GUATEMALTECA',1,'NULL',13),(173,'INDU',1,'NULL',13),(174,'IRLANDES',1,'NULL',13),(175,'JANONESA',1,'NULL',13),(176,'MEXICANA',1,'NULL',13),(177,'RUSA',1,'NULL',13),(178,'Becario',1,'NULL',14),(179,'Analista',1,'NULL',14),(180,'Consultor Junior',1,'NULL',14),(181,'Consultor Semisenior ',1,'NULL',14),(182,'Consultor Senior',1,'NULL',14),(183,'Gerente de Operaciones ',1,'NULL',14),(184,'Analista Ventas',1,'NULL',14),(185,'Consultor Ventas  Junior',1,'NULL',14),(186,'Consultor Ventas  Semisenior',1,'NULL',14),(187,'Consultor Ventas  Senior',1,'NULL',14),(188,'Gerente Ventas',1,'NULL',14),(189,'PADRE',1,'NULL',15),(190,'MADRE',1,'NULL',15),(191,'HERMANO/HERMANA',1,'NULL',15),(192,'ESPOSO/ESPOSA',1,'NULL',15),(193,'HIJO/HIJA',1,'NULL',15),(194,'ABUELO/ABUELA',1,'NULL',15),(195,'NIETO/NIETA',1,'NULL',15),(196,'TÍO/TIA',1,'NULL',15),(197,'AMIGO/AMIGA',1,'NULL',15),(198,'CONCUBINO/CONCUBINA',1,'NULL',15),(199,'OTRO',1,'NULL',15),(200,'ABC CAPITAL',1,'NULL',16),(201,'ACTINVER',1,'NULL',16),(202,'AFIRME',1,'NULL',16),(203,'AUTOFIN',1,'NULL',16),(204,'AZTECA',1,'NULL',16),(205,'BAJIO',1,'NULL',16),(206,'BANCO FAMSA',1,'NULL',16),(207,'BANCOMER',1,'NULL',16),(208,'BANCOPPEL',1,'NULL',16),(209,'BANJERCITO',1,'NULL',16),(210,'BANORTE',1,'NULL',16),(211,'BANORTE/IXE',1,'NULL',16),(212,'BANREGIO',1,'NULL',16),(213,'BANSEFI',1,'NULL',16),(214,'BANSI',1,'NULL',16),(215,'BRADESCARD DE MEXICO',1,'NULL',16),(216,'BROXEL',1,'NULL',16),(217,'CIBANCO',1,'NULL',16),(218,'DONDE',1,'NULL',16),(219,'HSBC',1,'NULL',16),(220,'INBURSA',1,'NULL',16),(221,'IXE',1,'NULL',16),(222,'MULTIVA BANCO',1,'NULL',16),(223,'SANTANDER',1,'NULL',16),(224,'SCOTIABANK',1,'NULL',16),(225,'PRIMARIA TRUNCA',1,'NULL',17),(226,'PRIMARIA COMPLETA',1,'NULL',17),(227,'SECUNDARIA TRUNCA',1,'NULL',17),(228,'SECUNDARIA COMPLETA',1,'NULL',17),(229,'CARRERA TECNICA INCOMPLETA ',1,'NULL',17),(230,'CARRERA TECNICA COMPLETA ',1,'NULL',17),(231,'BACHILLERATO INCOMPLETO',1,'NULL',17),(232,'BACHILLERATO COMPLETO',1,'NULL',17),(233,'UNIVERSIDAD INCOMPLETA',1,'NULL',17),(234,'UNIVERSIDAD COMPLETA',1,'NULL',17),(235,'MAESTRIA INCOMPLETA',1,'NULL',17),(236,'MAESTRIA COMPLETA',1,'NULL',17),(237,'DOCTORADO INCOMPLETO',1,'NULL',17),(238,'DOCTORADO COMPLETO',1,'NULL',17),(239,'UNIVERSITARIO TECNICO INCOMPLETO',1,'NULL',17),(240,'UNIVERSITARIO TECNICO COMPLETO',1,'NULL',17),(241,'OTRO',1,'NULL',17),(242,'Sociabilidad',1,'Capacidad para mezclarse fácilmente con otras personas y establecer con ellas relaciones altamente satisfactorias a nivel personal, social y laboral.',18),(243,'Tolerancia al estrés',1,'Mantenimiento firme del carácter bajo presión y/o oposición. Se traduce en respuestas controladas en situaciones de estrés.',18),(244,'Toma de decisiones',1,'Capacidad para elegir entre varias alternativas aquellas más viables para la consecución de objetivos, basándose en un análisis exhaustivo de los posibles efectos y riesgos, además de la visualización de las posibilidades de implantación.',18),(245,'Trabajo en equipo y cooperación',1,'Implica la intención de colaborar y cooperar con otros, formar parte del grupo, trabajar juntos, como opuesto a hacerlo individual o competitivamente.',18),(246,'INFONAVIT',1,'NULL',20),(247,'FOVISSSTE',1,'NULL',20),(248,'FONACOT',1,'NULL',20),(249,'OTRO',1,'NULL',20),(250,'De ley',1,'Prestaciones',21),(251,'Superiores de ley',1,'Prestaciones',21),(252,'Honorarios',1,'Prestaciones',21),(253,'Salarios asimilados',1,'Prestaciones',21),(254,'Comisionista',1,'Prestaciones',21),(255,'Becario',1,'Prestaciones',21),(256,'Servicio social',1,'Prestaciones',21),(257,'otro',1,'Prestaciones',21),(258,'Por proyecto',1,'Tipo de  contrato',22),(259,'Por incapacidad',1,'Tipo de  contrato',22),(260,'Tiempo determinsdo',1,'Tipo de  contrato',22),(261,'Tiempo Indeterminado',1,'Tipo de  contrato',22),(262,'Por evaluación',1,'Tipo de  contrato',22),(263,'Plaza de nueva creación',1,'Motivo de vacante',23),(264,'Incapacacidad',1,'Motivo de vacante',23),(265,'Proyecto',1,'Motivo de vacante',23),(266,'Substitución',1,'Motivo de vacante',23),(267,'Expansión',1,'Motivo de vacante',23),(268,'Otro',1,'Motivo de vacante',23),(269,'Mensual',1,'NULL',24),(270,'Bimestral',1,'NULL',24),(271,'Semanal',1,'NULL',24),(272,'Anual',1,'NULL',24);
/*!40000 ALTER TABLE `catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clb_submodulo`
--

DROP TABLE IF EXISTS `clb_submodulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clb_submodulo` (
  `id_submodulo` int(11) NOT NULL,
  `descripcion` varchar(90) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_submodulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clb_submodulo`
--

LOCK TABLES `clb_submodulo` WRITE;
/*!40000 ALTER TABLE `clb_submodulo` DISABLE KEYS */;
INSERT INTO `clb_submodulo` VALUES (1,'Bandeja de Proyectos'),(2,'Alta de Proyecto'),(3,'Asignar Periodo Y Garantía'),(4,'Asignar Consultor'),(5,'Configurar Cuestionario'),(6,'Cargar Head Count'),(7,'Organigrama'),(8,'Seleccion de participantes'),(9,'Enviar Cuestionarios'),(10,'Gráficas de Resultados');
/*!40000 ALTER TABLE `clb_submodulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competencias_vacante`
--

DROP TABLE IF EXISTS `competencias_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `competencias_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desc_competencia` varchar(400) NOT NULL,
  `id_competencia` int(11) NOT NULL,
  `nivel` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr7h12ssyrplrkkv505bgtx5g8` (`id_vacante`),
  CONSTRAINT `FKr7h12ssyrplrkkv505bgtx5g8` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competencias_vacante`
--

LOCK TABLES `competencias_vacante` WRITE;
/*!40000 ALTER TABLE `competencias_vacante` DISABLE KEYS */;
INSERT INTO `competencias_vacante` VALUES (1,'Disposición de actuar, sentir y/o pensar en torno a las necesidades del cliente para lo cual dirige toda sus acciones como estrategia para garantizar la satisfacción de los mismos.',41,1,1,1),(2,'Capacidad para trascender de lo lógico a lo abstracto y encontrar las verdaderas causas y soluciones de una situación o problema especifico; cuyos resultados son sustentados con un alto nivel de eficacia y confiabilidad.',43,1,1,1),(3,'Disposición de actuar, sentir y/o pensar en torno a las necesidades del cliente para lo cual dirige toda sus acciones como estrategia para garantizar la satisfacción de los mismos.',41,5,1,2),(4,'Ajustarse a los posibles cambios laborales, sociales y personales, percibiéndolos como oportunidades de aprendizaje.',42,1,1,2),(5,'Capacidad para trascender de lo lógico a lo abstracto y encontrar las verdaderas causas y soluciones de una situación o problema especifico; cuyos resultados son sustentados con un alto nivel de eficacia y confiabilidad.',43,1,1,2),(6,' Es la competencia que posee el líder para escuchar, entender y valorar con empatía información, ideas y opiniones que su equipo le comunique, siendo capaz de retroalimentar asertivamente el proceso comunicativo.',44,4,1,2),(7,' Capacidad para generar y mantener un flujo de comunicación adecuado entre los miembros del grupo o de la organización, utilizando los distintos canales que en cada caso se requieran; además, favorecer el establecimiento de relaciones mutuamente beneficiosas.',45,4,1,2);
/*!40000 ALTER TABLE `competencias_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conocimiento_candidato`
--

DROP TABLE IF EXISTS `conocimiento_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conocimiento_candidato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion_conocimiento` varchar(150) NOT NULL,
  `nivel` int(11) NOT NULL,
  `nombre_conocimiento` varchar(150) NOT NULL,
  `id_candidato` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcm2r82mdg4ckrj8kx5gsyykp8` (`id_candidato`),
  CONSTRAINT `FKcm2r82mdg4ckrj8kx5gsyykp8` FOREIGN KEY (`id_candidato`) REFERENCES `candidato` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conocimiento_candidato`
--

LOCK TABLES `conocimiento_candidato` WRITE;
/*!40000 ALTER TABLE `conocimiento_candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `conocimiento_candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conocimiento_vacante`
--

DROP TABLE IF EXISTS `conocimiento_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conocimiento_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion_conocimiento_vacante` varchar(150) NOT NULL,
  `nivel` int(11) NOT NULL,
  `nombre_conocimiento_vacante` varchar(150) NOT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKig2hycti0l2ewb22upijuau2h` (`id_vacante`),
  CONSTRAINT `FKig2hycti0l2ewb22upijuau2h` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conocimiento_vacante`
--

LOCK TABLES `conocimiento_vacante` WRITE;
/*!40000 ALTER TABLE `conocimiento_vacante` DISABLE KEYS */;
INSERT INTO `conocimiento_vacante` VALUES (1,'alksdalk ',4,'lkdjlkg c,mn ',1),(2,'CONOCIMIENTOS DE MODA, \nTELAS, DISEÑADORES, ETC',4,'CONOCIMIENTOS DE MODA, \nTELAS, DISEÑADORES, ETC',2);
/*!40000 ALTER TABLE `conocimiento_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultor_proyecto`
--

DROP TABLE IF EXISTS `consultor_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consultor_proyecto` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_proyecto` int(11) NOT NULL,
  `avance` int(3) DEFAULT NULL,
  `asignado` int(1) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_proyecto`),
  KEY `fo_consultor_id_proy_idx` (`id_proyecto`),
  CONSTRAINT `fo_consultor_id_proy` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultor_proyecto`
--

LOCK TABLES `consultor_proyecto` WRITE;
/*!40000 ALTER TABLE `consultor_proyecto` DISABLE KEYS */;
INSERT INTO `consultor_proyecto` VALUES (4,8,NULL,1),(4,10,NULL,1);
/*!40000 ALTER TABLE `consultor_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contacto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `celular` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `puesto` varchar(150) DEFAULT NULL,
  `telefono` varchar(255) NOT NULL,
  `tipo_contacto` int(11) NOT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgdf9c83j3xr4w6k3dc8jcej41` (`id_empresa`),
  CONSTRAINT `FKgdf9c83j3xr4w6k3dc8jcej41` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` VALUES (1,'5554359783','rrensoli@comunndad.unam.mx','Rene Rensoli Samayoa','Director General','55446756',34,1),(2,'3322445566','anibal@xlkslks.com.mx','anibal arguelles','dgerente general','flw{flks{dflkfg ',34,2),(3,'45045045045045','lmerchant@admmax.mx','LIC. LETICIA MERCHANT','GERENTE DE RECURSOS HUMANOS','(55) 5255-1093 ext. 111',34,3),(4,'5522334455','alberto@hotmail.com','Alberto Mendoza','Director','13443',34,4),(5,'5533442211','michel@magallanes','Michel Magallanes','Director','423443',34,31),(6,'89789787','Prueba1@Prueba1.com','Prueba1','Prueba1','0909797',19,NULL),(7,'89789789','Prueba2@Prueba2.com','Prueba2','Prueba2','879878',19,NULL),(8,'asd','asdasd@zdzcx.c','ashjdasjhd','Contacto de cobranza','sadas',19,NULL),(9,'2w2','dasda@zxc.zxcz','asdasd','Contacto de cobranza','22',19,NULL),(10,'21312','asdasd@asda.cl','sadasd','Contacto de cobranza','23',19,NULL),(11,'65765465','gjhghj@ghjgjh.com','gjhgjhg','gfhfhf','100',19,NULL);
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto_candidato`
--

DROP TABLE IF EXISTS `contacto_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contacto_candidato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_contacto` varchar(150) NOT NULL,
  `tipo_contacto` int(11) NOT NULL,
  `id_candidato` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqe22ka8f0quqyo7qyv8ysp2a4` (`id_candidato`),
  CONSTRAINT `FKqe22ka8f0quqyo7qyv8ysp2a4` FOREIGN KEY (`id_candidato`) REFERENCES `candidato` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto_candidato`
--

LOCK TABLES `contacto_candidato` WRITE;
/*!40000 ALTER TABLE `contacto_candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacto_candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratacion_vacante`
--

DROP TABLE IF EXISTS `contratacion_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contratacion_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jornada` varchar(150) NOT NULL,
  `prestaciones` int(11) NOT NULL,
  `sueldo` varchar(255) NOT NULL,
  `tipo_contrato` int(11) NOT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk22s4qx60can3tx9h9nxr3lbq` (`id_vacante`),
  CONSTRAINT `FKk22s4qx60can3tx9h9nxr3lbq` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratacion_vacante`
--

LOCK TABLES `contratacion_vacante` WRITE;
/*!40000 ALTER TABLE `contratacion_vacante` DISABLE KEYS */;
INSERT INTO `contratacion_vacante` VALUES (1,'lunes a viernes',0,'30000',0,1),(2,'LUNES A DOMINGO DE 10:45 A 21:00 HRS CON 8 DÍAS DE DESCANSO AL MES',0,'$6,500 MENSUALES + COMISIONES+ BONO $2,000 + VALES DESPENSA $ 1,200 MENSUALES',0,2);
/*!40000 ALTER TABLE `contratacion_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_empresa`
--

DROP TABLE IF EXISTS `cuestionario_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_empresa` (
  `id_cuestionario_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `id_tema` int(11) NOT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `finalizado` int(11) NOT NULL,
  PRIMARY KEY (`id_cuestionario_empresa`),
  UNIQUE KEY `UK_CUESTIONARIO_EMPRESA` (`id_tema`,`id_empresa`),
  UNIQUE KEY `UK_c94x4liwa7m6ukid155xntd88` (`id_tema`),
  KEY `FK_CUEST_EMPR` (`id_empresa`),
  KEY `FK_CUEST_TEMA` (`id_tema`),
  CONSTRAINT `FK_CUEST_EMPR` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`),
  CONSTRAINT `FK_CUEST_TEMA` FOREIGN KEY (`id_tema`) REFERENCES `tema_cuestionario` (`id_tema`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_empresa`
--

LOCK TABLES `cuestionario_empresa` WRITE;
/*!40000 ALTER TABLE `cuestionario_empresa` DISABLE KEYS */;
INSERT INTO `cuestionario_empresa` VALUES (2,5,29,0),(3,6,29,0),(4,7,29,0),(5,8,29,0),(6,9,29,0),(7,10,29,0),(8,11,29,0);
/*!40000 ALTER TABLE `cuestionario_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_empresa_irh`
--

DROP TABLE IF EXISTS `cuestionario_empresa_irh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_empresa_irh` (
  `id_cuestionario_empresa` bigint(20) NOT NULL AUTO_INCREMENT,
  `finalizado` bit(1) DEFAULT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id_cuestionario_empresa`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_empresa_irh`
--

LOCK TABLES `cuestionario_empresa_irh` WRITE;
/*!40000 ALTER TABLE `cuestionario_empresa_irh` DISABLE KEYS */;
INSERT INTO `cuestionario_empresa_irh` VALUES (0,_binary '\0',0,0);
/*!40000 ALTER TABLE `cuestionario_empresa_irh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_empresa_irh_pregunta`
--

DROP TABLE IF EXISTS `cuestionario_empresa_irh_pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_empresa_irh_pregunta` (
  `id_pregunta` bigint(20) NOT NULL AUTO_INCREMENT,
  `enunciado` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `respuesta1` int(11) DEFAULT NULL,
  `respuesta2` int(11) DEFAULT NULL,
  `respuesta3` int(11) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `cuestionario_empresairhtema` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`),
  KEY `FKg1g8e6gn903tepgfpcuqa4xtg` (`cuestionario_empresairhtema`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_empresa_irh_pregunta`
--

LOCK TABLES `cuestionario_empresa_irh_pregunta` WRITE;
/*!40000 ALTER TABLE `cuestionario_empresa_irh_pregunta` DISABLE KEYS */;
INSERT INTO `cuestionario_empresa_irh_pregunta` VALUES (1,'Existe un Organigrama de la Organización',0,0,0,0,1),(2,'Existe un departamento o persona encargada específicamente del RyS de personal',0,0,0,0,1),(3,'Se cuenta con un sistema de evaluación de resultados del personal a todos los niveles y áreas',0,0,0,0,1),(4,'Se realiza un Diagnóstico de Necesidades de Capacitación periódicamente',0,0,0,0,1),(5,'Existe algún procedimiento para evaluar el Clima Laboral dentro de la empresa',0,0,0,0,1),(6,'Se cuenta con un tabulador que rija la estrategia de compensación de la empresa',0,0,0,0,1),(7,'Cuentan con un proceso de bajas del personal',0,0,0,0,1),(8,'Se cumple con la normatividad que aplique al tipo de industria a la que se pertenece',0,0,0,0,1),(9,'Se cuenta con un proceso documentado y estandarizado de RyS',0,0,0,0,2),(10,'Se cuenta con un sistema de evaluación de candidatos',0,0,0,0,2),(11,'Se realizan entrevistas de selección por parte del jefe directo de la posición a buscar',0,0,0,0,2),(12,'Se cuenta con un sistema de inducción al nuevo personal',0,0,0,0,2),(13,'Se evalúa la efectividad de la inducción proporcionada al nuevo personal',0,0,0,0,2),(14,'Existe un departamento o persona encargada de elaborar contratos laborales de servicios para los nuevos integrantes',0,0,0,0,2),(15,'Se cuenta con un sistema de evaluación de resultados del personal a todos los niveles y áreas',0,0,0,0,3),(16,'Se cuenta con un sistema de evaluación de competencias del personal  a todos los niveles y áreas',0,0,0,0,3),(17,'Se cuenta con un sistema de consecuencias post-evaluación',0,0,0,0,3),(18,'La periodicidad de las evaluaciones aplicadas al personal es cuatrimestral',0,0,0,0,3),(19,'Se programan sesiones de retroalimentación de las evaluaciones al personal',0,0,0,0,3),(20,'Se analizan periódicamente los indicadores de medición de resultados por puestos (en caso de contar con ellos)',0,0,0,0,3),(21,'Se realiza un Diagnóstico de Necesidades de Capacitación periódicamente',0,0,0,0,4),(22,'Se diseñan Planes de Capacitación relacionados con un diagnóstico previo',0,0,0,0,4),(23,'Se cuenta con un Presupuesto autorizado destinado a cubrir necesidades de Capacitación',0,0,0,0,4),(24,'Existen indicadores relativos al plan de capacitación como nivel de aprovechamiento, asistencia, efectividad, roi, etc.',0,0,0,0,4),(25,'Se tiene un sistema de gestión/administración de los resultados de la capacitación',0,0,0,0,4),(26,'Se diseñan Planes de Carrera orientado a ofrecer un crecimiento profesional dentro de la organización',0,0,0,0,4),(27,'Se realizan evaluaciones de potencial al personal con miras a planes de sucesión y carrera',0,0,0,0,4),(28,'Existe algún procedimiento para evaluar el Clima Laboral dentro de la empresa',0,0,0,0,5),(29,'Se diseñan planes de acción correctivos y preventivos como resultado de la Medición de Clima Laboral',0,0,0,0,5),(30,'Existe un procedimiento sistematizado de comunicación interna horizontal, vertical y diagonal',0,0,0,0,5),(31,'Se han identificado los Valores Organizacionales',0,0,0,0,5),(32,'Se difunden (comunican) los Valores Organizacionales de forma permanente',0,0,0,0,5),(33,'Existe un procedimiento que tenga por objetivo el hacer a todos los colaboradores partícipes de los Valores Organizacionales',0,0,0,0,5),(34,'Existe un programa de Calidad de Vida para los colaboradores de la empresa',0,0,0,0,5),(35,'Se diseñan y aplican programas de motivación para todo el personal',0,0,0,0,5),(36,'Se llevan a cabo eventos de integración y celebración en la empresa',0,0,0,0,5),(37,'Se mide la satisfacción de los eventos de integración y celebración llevados a cabo en la empresa',0,0,0,0,5),(38,'Se cuenta con un tabulador que rija la estrategia de compensación de la empresa',0,0,0,0,6),(39,'Se revisa y actualiza el tabulador en la empresa cuando menos una vez al año',0,0,0,0,6),(40,'Existe un procedimiento y políticas de aumento de sueldos',0,0,0,0,6),(41,'Existe un esquema de remuneración variable con base en resultados para todos los puestos',0,0,0,0,6),(42,'La empresa otorga prestaciones de ley (IMSS, Aguinaldo, Vacaciones, Infonavit, etc,.)',0,0,0,0,6),(43,'La empresa otorga prestaciones superiores a las de ley (SGMM, SV, Auto, etc.) al menos a algunos empleados',0,0,0,0,6),(44,'La empresa ha diseñado y ofrece esquemas de beneficios adicionales para sus empleados (horario flexible, préstamos, alianzas y descuentos empresariales, etc.)',0,0,0,0,6),(45,'La empresa mide las incidencias del personal y éstas tienen una repercusión en la remuneración de los empleados (permisos, retardos, incapacidades, etc.)',0,0,0,0,6),(46,'Cuentan con un proceso de bajas del personal',0,0,0,0,7),(47,'Existe un área o persona encargada de calcular los finiquitos o liquidaciones del personal de baja',0,0,0,0,7),(48,'Se aplican entrevistas de salida al personal que renuncia',0,0,0,0,7),(49,'Existe un procedimiento de acciones correctivas/preventivas correlacionadas al resultado de entrevistas de salida',0,0,0,0,7),(50,'Cuentan con un Contrato de Confidencialidad que ampare la propiedad intelectual de la empresa firmado por cada empleado',0,0,0,0,7),(51,'Si cuenta con personal operativo, éste se encuentra sindicalizado',0,0,0,0,7),(52,'Todos los empleados están dados de alta en algun sistema de Seguridad Social e Infonavit',0,0,0,0,7),(53,'Se pagan en tiempo y forma las aportaciones patronales a Infonavit, Fondo de Cesantía y Vejez y demás instancias que dicta el Gobierno estatal y federal',0,0,0,0,7),(54,'Se cumple con la normatividad que aplique al tipo de industria a la que se pertenece',0,0,0,0,8),(55,'Se llevan a cabo recorridos periódicos de Seguridad e Higiene',0,0,0,0,8),(56,'Se cuenta con una Brigada contra Incendios y Sismos perteneciente a la misma empresa',0,0,0,0,8),(57,'Se cuenta con planes y programas de salud y seguridad ocupacional',0,0,0,0,8),(58,'Se cuenta con un registro de los accidentes de trabajo ocurridos en la organización asi como de sus causas',0,0,0,0,8),(59,'Se diseñan planes de prevención de accidentes',0,0,0,0,8),(60,'Se realizan acciones correctivas para cada accidente de trabajo',0,0,0,0,8),(61,'Se cuenta con material y equipo de seguridad en las instalaciones',0,0,0,0,8),(62,'Se cuenta con un botiquín de primeros auxilios disponible para todos los colaboradores de la empresa con un responsable en el uso y manejo del contenido',0,0,0,0,8);
/*!40000 ALTER TABLE `cuestionario_empresa_irh_pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_empresa_irh_tema`
--

DROP TABLE IF EXISTS `cuestionario_empresa_irh_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_empresa_irh_tema` (
  `id_tema` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `score` double DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `cuestionario_empresairh` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_tema`),
  KEY `FKll8k4lh49xusr1oxoyfcv4k4e` (`cuestionario_empresairh`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_empresa_irh_tema`
--

LOCK TABLES `cuestionario_empresa_irh_tema` WRITE;
/*!40000 ALTER TABLE `cuestionario_empresa_irh_tema` DISABLE KEYS */;
INSERT INTO `cuestionario_empresa_irh_tema` VALUES (1,'','ESTRUCTURA ORGANIZACIONAL',0,_binary '',0),(2,'','INTEGRACION DE PERSONAL',0,_binary '',0),(3,'','EVALUACIÓN DEL PERSONAL',0,_binary '',0),(4,'','CAPACITACION Y DESARROLLO DE PERSONAL',0,_binary '',0),(5,'','DESARROLLO ORGANIZACIONAL',0,_binary '',0),(6,'','REMUNERACIONES',0,_binary '',0),(7,'','RELACIONES LABORALES',0,_binary '',0),(8,'','SEGURIDAD E HIGIENE',0,_binary '',0);
/*!40000 ALTER TABLE `cuestionario_empresa_irh_tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_opcion`
--

DROP TABLE IF EXISTS `cuestionario_opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_opcion` (
  `id_proyecto` int(11) NOT NULL,
  `id_tema` int(11) NOT NULL,
  `id_opcion` int(11) NOT NULL,
  PRIMARY KEY (`id_proyecto`,`id_tema`,`id_opcion`),
  KEY `fk_co_id_tema_idx` (`id_tema`),
  KEY `fk_co_id_opcion_idx` (`id_opcion`),
  CONSTRAINT `fk_co_id_opcion` FOREIGN KEY (`id_opcion`) REFERENCES `opcion` (`id_opcion`),
  CONSTRAINT `fk_co_id_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  CONSTRAINT `fk_co_id_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_opcion`
--

LOCK TABLES `cuestionario_opcion` WRITE;
/*!40000 ALTER TABLE `cuestionario_opcion` DISABLE KEYS */;
INSERT INTO `cuestionario_opcion` VALUES (8,301,301001),(8,301,301002),(8,301,301003),(8,301,301004),(8,301,301005),(10,301,301001),(10,301,301002),(10,301,301003),(10,301,301004),(10,301,301005),(8,302,302001),(8,302,302002),(8,302,302003),(8,302,302004),(8,302,302005),(10,302,302001),(10,302,302002),(10,302,302006),(8,303,303001),(8,303,303002),(8,303,303003),(8,303,303004),(8,303,303005),(8,304,304001),(8,304,304002),(8,304,304003),(8,304,304004),(8,304,304005);
/*!40000 ALTER TABLE `cuestionario_opcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_proyecto`
--

DROP TABLE IF EXISTS `cuestionario_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_proyecto` (
  `id_proyecto` int(11) NOT NULL,
  `id_tema` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  PRIMARY KEY (`id_proyecto`,`id_tema`,`id_pregunta`),
  KEY `fk_cuest_proy_id_tema_idx` (`id_tema`),
  KEY `fk_cuest_proy_id_preg_idx` (`id_pregunta`),
  CONSTRAINT `fk_cuest_proy_id_preg` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta` (`id_pregunta`),
  CONSTRAINT `fk_cuest_proy_id_proy` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  CONSTRAINT `fk_cuest_proy_id_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_proyecto`
--

LOCK TABLES `cuestionario_proyecto` WRITE;
/*!40000 ALTER TABLE `cuestionario_proyecto` DISABLE KEYS */;
INSERT INTO `cuestionario_proyecto` VALUES (5,101,501),(5,101,502),(5,101,510),(5,102,601),(5,103,701),(8,301,30101),(10,301,30101),(8,302,30201),(10,302,30201),(8,303,30301),(8,304,30401);
/*!40000 ALTER TABLE `cuestionario_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_proyecto_tp`
--

DROP TABLE IF EXISTS `cuestionario_proyecto_tp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_proyecto_tp` (
  `id_cuestionario_proyecto` bigint(20) NOT NULL AUTO_INCREMENT,
  `finalizado` bit(1) DEFAULT NULL,
  `id_proyecto` bigint(20) NOT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id_cuestionario_proyecto`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_proyecto_tp`
--

LOCK TABLES `cuestionario_proyecto_tp` WRITE;
/*!40000 ALTER TABLE `cuestionario_proyecto_tp` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuestionario_proyecto_tp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_proyecto_tp_pregunta`
--

DROP TABLE IF EXISTS `cuestionario_proyecto_tp_pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_proyecto_tp_pregunta` (
  `id_pregunta` bigint(20) NOT NULL AUTO_INCREMENT,
  `enunciado` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `respuesta` int(11) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `cuestionario_proyectotptema` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`),
  KEY `FKi7go5xnmb78xo9wosxsnjnysj` (`cuestionario_proyectotptema`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_proyecto_tp_pregunta`
--

LOCK TABLES `cuestionario_proyecto_tp_pregunta` WRITE;
/*!40000 ALTER TABLE `cuestionario_proyecto_tp_pregunta` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuestionario_proyecto_tp_pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionario_proyecto_tp_tema`
--

DROP TABLE IF EXISTS `cuestionario_proyecto_tp_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuestionario_proyecto_tp_tema` (
  `id_tema` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `cuestionario_proyectotp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_tema`),
  KEY `FK3m5odk661ccdnl45bjwwovui8` (`cuestionario_proyectotp`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario_proyecto_tp_tema`
--

LOCK TABLES `cuestionario_proyecto_tp_tema` WRITE;
/*!40000 ALTER TABLE `cuestionario_proyecto_tp_tema` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuestionario_proyecto_tp_tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuetionario_participante`
--

DROP TABLE IF EXISTS `cuetionario_participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cuetionario_participante` (
  `id_participante` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `id_tema` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  `respuesta` int(2) DEFAULT NULL,
  `respuesta_rh` int(2) DEFAULT NULL,
  `respuesta_jefe` int(2) DEFAULT NULL,
  `pregunta_id_pregunta` int(11) NOT NULL,
  `tema_id_tema` int(11) NOT NULL,
  PRIMARY KEY (`id_participante`,`id_proyecto`),
  KEY `fk_cuest_part_id_tema_idx` (`id_tema`),
  KEY `fk_cuest_part_id_preg_idx` (`id_pregunta`),
  KEY `FKt9twi2j2iwai7xcmdwtba6hhu` (`pregunta_id_pregunta`),
  KEY `FKny0nilg5ji8fqxq33m5nas3lr` (`tema_id_tema`),
  CONSTRAINT `FKny0nilg5ji8fqxq33m5nas3lr` FOREIGN KEY (`tema_id_tema`) REFERENCES `tema` (`id_tema`),
  CONSTRAINT `FKt9twi2j2iwai7xcmdwtba6hhu` FOREIGN KEY (`pregunta_id_pregunta`) REFERENCES `pregunta` (`id_pregunta`),
  CONSTRAINT `fk_cuest_part_id_part` FOREIGN KEY (`id_participante`, `id_proyecto`) REFERENCES `participante` (`id_participante`, `id_proyecto`),
  CONSTRAINT `fk_cuest_part_id_pregunta` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta` (`id_pregunta`),
  CONSTRAINT `fk_cuest_part_id_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuetionario_participante`
--

LOCK TABLES `cuetionario_participante` WRITE;
/*!40000 ALTER TABLE `cuetionario_participante` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuetionario_participante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado` (
  `id_empleado` bigint(20) NOT NULL,
  `banco` int(11) NOT NULL,
  `banco_cinterbancaria` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `banco_cuenta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `credito_hipotecario` bit(1) DEFAULT NULL,
  `curp` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `direccion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `documento_comprobante` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `documento_curp` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `documento_cv` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `documento_ine` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `escolaridad` int(11) NOT NULL,
  `escolaridad_capacidades` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `escolaridad_carrera` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `escolaridad_especialidad` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `estado_civil` int(11) DEFAULT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL,
  `institucion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nacionalidad` int(11) NOT NULL,
  `nombre_completo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nss` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pension_alimenticia` bit(1) DEFAULT NULL,
  `rfc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo_credito_hipotecario` int(11) DEFAULT NULL,
  `titulo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_contacto`
--

DROP TABLE IF EXISTS `empleado_contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado_contacto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `beneficiario` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `celular` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefono` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo_contacto` int(11) NOT NULL,
  `empleado` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsoynw38tfj3l369gfbf7q8s2s` (`empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_contacto`
--

LOCK TABLES `empleado_contacto` WRITE;
/*!40000 ALTER TABLE `empleado_contacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_escolaridad_certificaciones`
--

DROP TABLE IF EXISTS `empleado_escolaridad_certificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado_escolaridad_certificaciones` (
  `empleado_id_empleado` bigint(20) NOT NULL,
  `escolaridad_certificaciones` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FKdqq3ag8tmwls621tskwtlflb` (`empleado_id_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_escolaridad_certificaciones`
--

LOCK TABLES `empleado_escolaridad_certificaciones` WRITE;
/*!40000 ALTER TABLE `empleado_escolaridad_certificaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_escolaridad_certificaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_escolaridad_cursos`
--

DROP TABLE IF EXISTS `empleado_escolaridad_cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado_escolaridad_cursos` (
  `empleado_id_empleado` bigint(20) NOT NULL,
  `escolaridad_cursos` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FKna7oi3rko76yog6733quvgql1` (`empleado_id_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_escolaridad_cursos`
--

LOCK TABLES `empleado_escolaridad_cursos` WRITE;
/*!40000 ALTER TABLE `empleado_escolaridad_cursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_escolaridad_cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_escolaridad_oficios`
--

DROP TABLE IF EXISTS `empleado_escolaridad_oficios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado_escolaridad_oficios` (
  `empleado_id_empleado` bigint(20) NOT NULL,
  `escolaridad_oficios` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FKathhoexkd7i3249cwcttrom2n` (`empleado_id_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_escolaridad_oficios`
--

LOCK TABLES `empleado_escolaridad_oficios` WRITE;
/*!40000 ALTER TABLE `empleado_escolaridad_oficios` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_escolaridad_oficios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_idioma`
--

DROP TABLE IF EXISTS `empleado_idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado_idioma` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `habilidades` int(11) NOT NULL,
  `idioma` int(11) NOT NULL,
  `nivel` int(11) NOT NULL,
  `empleado` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKidyu1tnmp2a7wl7gk3jd0uyxc` (`empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_idioma`
--

LOCK TABLES `empleado_idioma` WRITE;
/*!40000 ALTER TABLE `empleado_idioma` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_laboral`
--

DROP TABLE IF EXISTS `empleado_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado_laboral` (
  `id` bigint(20) NOT NULL,
  `antiguedad` int(11) DEFAULT NULL,
  `area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_termino` datetime DEFAULT NULL,
  `localidad` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nivel_laboral` int(11) NOT NULL,
  `puesto` int(11) DEFAULT NULL,
  `sueldo` double DEFAULT NULL,
  `empleado` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk9bwwpj0liftfn8ds013qwr6q` (`empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_laboral`
--

LOCK TABLES `empleado_laboral` WRITE;
/*!40000 ALTER TABLE `empleado_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `empresa` varchar(150) NOT NULL,
  `giro` int(11) NOT NULL,
  `pais` int(11) NOT NULL,
  `rfc` varchar(150) NOT NULL,
  `anio_inicio_operaciones` bigint(20) DEFAULT NULL,
  `producto_servicio_estrella` varchar(150) DEFAULT NULL,
  `principales_productos_servicios` varchar(150) DEFAULT NULL,
  `no_empleados_administrativo` bigint(20) DEFAULT NULL,
  `no_empleados_operarativo` bigint(20) DEFAULT NULL,
  `tipo_contratacion_empleados` varchar(150) DEFAULT NULL,
  `facturacion_anual` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l7w1xs5k9lurn439riy4hushv` (`empresa`),
  UNIQUE KEY `UK_3r9hog1biuqoka7gqh15rebky` (`rfc`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'Lamartine 289\nCol. Polanco','KKANA S.A. de C.V.',4,9,'RESR901003958',0,'','',0,0,'',0),(2,'Periferico sur 2020\ncol. Perisur\n44444','Ptito s,a. de c.v.',4,9,'RESR901003968',0,'','',0,0,'',0),(3,'TAINE 229 INT 203, COL. CHAPULTEPEC MORALES, DELEGACIÓN MIGUEL HIDALGO, CDMX','ADMMAX, S.A. DE C.V.',6,9,'ADM991220C81',0,'','',0,0,'',0),(4,'z{xcñlkzlck {lcñxkzc\nzxcklzlck \nzxlclcjzkcj \nxckkcjl ','Desarrollos inteligents s.a-',4,9,'RECR581026SM7',0,'','',0,0,'',0),(21,'update emp 289\nCol. Polanco','EmpresaIRH2 S.A. de C.V.',4,9,'RESR901003963',2015,'productoServicioEstrella','principalesProductosServicios',10,10,'tipoContratacionEmpleados',7509682.5),(22,'insert emp 289\nCol. Polanco','EmpresaIRH3 S.A. de C.V.',4,9,'RESR901003965',2015,'productoServicioEstrella','principalesProductosServicios',10,10,'tipoContratacionEmpleados',7509682.5),(29,'INSERTAR emp 289\nCol. Polanco','EmpresaIRH4 S.A. de C.V.',4,9,'RESR901003971',2015,'productoServicioEstrella','principalesProductosServicios',10,10,'tipoContratacionEmpleados',7509682.5),(30,'INSERTAR emp 289\nCol. Polanco','EmpresaIRH21 S.A. de C.V.',4,9,'RESR901003972',2015,'productoServicioEstrella','principalesProductosServicios',10,10,'tipoContratacionEmpleados',7509682.5),(31,'Chihuahua 37, Col. San Agustin','Hermanos Magallanes',4,9,'CODE740301001',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'Prueba1','Prueba1',16,1,'Prueba1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'Prueba2','Prueba2',17,1,'Prueba2',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,'dsasd','ddsfsd',16,1,'sdadas',0,NULL,NULL,0,0,NULL,0),(35,'sdas','xzcxzxc',16,1,'sdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'sdfsd','cvxcx',16,2,'dsfsdf',0,NULL,NULL,0,0,NULL,0),(37,'ygyjhg','prueba jop',16,1,'gfghh',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrevista_asignacion_vacante`
--

DROP TABLE IF EXISTS `entrevista_asignacion_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `entrevista_asignacion_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `encargado_entrevista` varchar(255) DEFAULT NULL,
  `fecha_entrevista` varchar(255) DEFAULT NULL,
  `hora_final` varchar(255) DEFAULT NULL,
  `hora_inicial` varchar(255) DEFAULT NULL,
  `observaciones_cliente` varchar(255) DEFAULT NULL,
  `tipo_entrevista` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `id_asignacion_consultor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpb09g35pnam0d65j7wlumfmmo` (`id_asignacion_consultor`),
  CONSTRAINT `FKpb09g35pnam0d65j7wlumfmmo` FOREIGN KEY (`id_asignacion_consultor`) REFERENCES `asignacion_consultor_vacante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrevista_asignacion_vacante`
--

LOCK TABLES `entrevista_asignacion_vacante` WRITE;
/*!40000 ALTER TABLE `entrevista_asignacion_vacante` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrevista_asignacion_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrevistado`
--

DROP TABLE IF EXISTS `entrevistado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `entrevistado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_responsable_llenado` varchar(150) NOT NULL,
  `puesto_responsable_llenado` varchar(150) NOT NULL,
  `nombre_entrevistador` varchar(150) NOT NULL,
  `nombre_entrevistado` varchar(150) NOT NULL,
  `puesto_entrevistado` varchar(150) NOT NULL,
  `correo_electronico` varchar(150) NOT NULL,
  `telefono_celular` bigint(20) NOT NULL,
  `telefono_oficina_extension` bigint(20) DEFAULT NULL,
  `rfc_empresa` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKRFCEMPRESAENTREVISTADO` (`rfc_empresa`),
  CONSTRAINT `FKRFCEMPRESAENTREVISTADO` FOREIGN KEY (`rfc_empresa`) REFERENCES `empresa` (`rfc`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrevistado`
--

LOCK TABLES `entrevistado` WRITE;
/*!40000 ALTER TABLE `entrevistado` DISABLE KEYS */;
INSERT INTO `entrevistado` VALUES (11,'actualizado','puestoResponsableLlenado','nombreEntrevistador','nombreEntrevistado','puestoEntrevistado','correoElectronico',3123,31241,'RESR901003963'),(12,'actualizado','puestoResponsableLlenado','nombreEntrevistador','nombreEntrevistado','puestoEntrevistado','correoElectronico',3123,31241,'RESR901003965'),(13,'actualizado','puestoResponsableLlenado','nombreEntrevistador','nombreEntrevistado','puestoEntrevistado','correoElectronico',5554353215,31241,'RESR901003971'),(14,'actualizado','puestoResponsableLlenado','nombreEntrevistador','nombreEntrevistado','puestoEntrevistado','correoElectronico',5554353215,31241,'RESR901003972');
/*!40000 ALTER TABLE `entrevistado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `espacio_fisico`
--

DROP TABLE IF EXISTS `espacio_fisico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `espacio_fisico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delimitacion_areas_trabajo` bit(1) NOT NULL,
  `elementos_comunicacion_interna` bit(1) NOT NULL,
  `instalaciones_adecuadas` bit(1) NOT NULL,
  `luz_suficiente_adecuada` bit(1) NOT NULL,
  `posiciones_trabajo_comodas` bit(1) NOT NULL,
  `seguridad_ocupacional` bit(1) NOT NULL,
  `temperatura_adecuada` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espacio_fisico`
--

LOCK TABLES `espacio_fisico` WRITE;
/*!40000 ALTER TABLE `espacio_fisico` DISABLE KEYS */;
/*!40000 ALTER TABLE `espacio_fisico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estatus_proyecto`
--

DROP TABLE IF EXISTS `estatus_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estatus_proyecto` (
  `id_estatus` int(11) NOT NULL,
  `descripcion` varchar(90) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_estatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estatus_proyecto`
--

LOCK TABLES `estatus_proyecto` WRITE;
/*!40000 ALTER TABLE `estatus_proyecto` DISABLE KEYS */;
INSERT INTO `estatus_proyecto` VALUES (1,'En captura'),(2,'En asignación'),(3,'En configuración de cuestionario'),(4,'En carga de Head Count'),(5,'En enviado a participantes'),(6,'Vigente'),(7,'Cierre'),(8,'Resultados');
/*!40000 ALTER TABLE `estatus_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluador_evaluado`
--

DROP TABLE IF EXISTS `evaluador_evaluado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `evaluador_evaluado` (
  `id_proyecto` int(11) NOT NULL,
  `id_evaluador` int(11) NOT NULL,
  `id_evaluado` int(11) NOT NULL,
  PRIMARY KEY (`id_proyecto`,`id_evaluador`,`id_evaluado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluador_evaluado`
--

LOCK TABLES `evaluador_evaluado` WRITE;
/*!40000 ALTER TABLE `evaluador_evaluado` DISABLE KEYS */;
INSERT INTO `evaluador_evaluado` VALUES (8,2,1),(8,2,4),(8,2,6),(8,4,4),(8,4,6),(8,4,7),(10,2,2),(10,2,3),(10,2,5),(10,3,6);
/*!40000 ALTER TABLE `evaluador_evaluado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencia_candidato`
--

DROP TABLE IF EXISTS `experiencia_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `experiencia_candidato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desc_proyecto` varchar(255) DEFAULT NULL,
  `fecha_fin` varchar(255) DEFAULT NULL,
  `fecha_ini` varchar(150) NOT NULL,
  `herramientas` varchar(255) DEFAULT NULL,
  `proyecto` varchar(255) DEFAULT NULL,
  `puesto` varchar(255) DEFAULT NULL,
  `recomendacion` varchar(255) DEFAULT NULL,
  `id_candidato` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf94yron4o8rq5pgpmlnred4bd` (`id_candidato`),
  CONSTRAINT `FKf94yron4o8rq5pgpmlnred4bd` FOREIGN KEY (`id_candidato`) REFERENCES `candidato` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencia_candidato`
--

LOCK TABLES `experiencia_candidato` WRITE;
/*!40000 ALTER TABLE `experiencia_candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiencia_candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grafica_proyecto`
--

DROP TABLE IF EXISTS `grafica_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grafica_proyecto` (
  `id_proyecto` int(11) NOT NULL,
  `area_org` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `id_tema` int(11) NOT NULL,
  `num_resp_1` int(11) DEFAULT NULL,
  `num_resp_2` int(11) DEFAULT NULL,
  `num_resp_3` int(11) DEFAULT NULL,
  `num_resp_4` int(11) DEFAULT NULL,
  `num_resp_5` int(11) DEFAULT NULL,
  `comentarios` varchar(360) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_proyecto`,`area_org`,`id_tema`),
  KEY `fk_graf_proy_id_tema_idx` (`id_tema`),
  CONSTRAINT `fk_graf_proy_id_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  CONSTRAINT `fk_graf_proy_id_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grafica_proyecto`
--

LOCK TABLES `grafica_proyecto` WRITE;
/*!40000 ALTER TABLE `grafica_proyecto` DISABLE KEYS */;
INSERT INTO `grafica_proyecto` VALUES (5,'Administración',101,0,0,0,0,0,NULL),(5,'Administración',102,0,0,0,0,0,NULL),(5,'Administración',103,0,0,0,0,0,NULL),(5,'Control de Calidad',101,0,0,0,0,0,NULL),(5,'Control de Calidad',102,0,0,0,0,0,NULL),(5,'Control de Calidad',103,0,0,0,0,0,NULL),(5,'Corporativo',101,0,0,0,0,0,NULL),(5,'Corporativo',102,0,0,0,0,0,NULL),(5,'Corporativo',103,0,0,0,0,0,NULL),(5,'Producción',101,0,0,0,0,0,NULL),(5,'Producción',102,0,0,0,0,0,NULL),(5,'Producción',103,0,0,0,0,0,NULL),(5,'Ventas',101,0,0,0,0,0,NULL),(5,'Ventas',102,0,0,0,0,0,NULL),(5,'Ventas',103,0,0,0,0,0,NULL);
/*!40000 ALTER TABLE `grafica_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (24),(24);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma_candidato`
--

DROP TABLE IF EXISTS `idioma_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idioma_candidato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desc_idioma` varchar(400) NOT NULL,
  `id_idioma` int(11) NOT NULL,
  `nivel` int(11) NOT NULL,
  `id_candidato` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoxc27v2r29nxssb3ppgi5pmku` (`id_candidato`),
  CONSTRAINT `FKoxc27v2r29nxssb3ppgi5pmku` FOREIGN KEY (`id_candidato`) REFERENCES `candidato` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma_candidato`
--

LOCK TABLES `idioma_candidato` WRITE;
/*!40000 ALTER TABLE `idioma_candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `idioma_candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma_vacante`
--

DROP TABLE IF EXISTS `idioma_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idioma_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desc_idioma` varchar(400) NOT NULL,
  `id_idioma` int(11) NOT NULL,
  `nivel` int(11) NOT NULL,
  `id_vacante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4a3d0c4fj1ax7hwkob9wmjsj4` (`id_vacante`),
  CONSTRAINT `FK4a3d0c4fj1ax7hwkob9wmjsj4` FOREIGN KEY (`id_vacante`) REFERENCES `vacante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma_vacante`
--

LOCK TABLES `idioma_vacante` WRITE;
/*!40000 ALTER TABLE `idioma_vacante` DISABLE KEYS */;
INSERT INTO `idioma_vacante` VALUES (1,'Traducción',19,25,1),(2,'Hablado, CON POSIBILIDAD DE CONVERSACIÓN CON UN CTE EXTRANJERO',19,23,2);
/*!40000 ALTER TABLE `idioma_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicadores_nirho`
--

DROP TABLE IF EXISTS `indicadores_nirho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `indicadores_nirho` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `asignada_candidato` int(11) NOT NULL,
  `asignada_consultor_nirho` int(11) NOT NULL,
  `asignada_entrevista` int(11) NOT NULL,
  `cancelada` int(11) NOT NULL,
  `en_garantia` int(11) NOT NULL,
  `en_registro` int(11) NOT NULL,
  `guardada` int(11) NOT NULL,
  `publicada_red_social` int(11) NOT NULL,
  `recibida` int(11) NOT NULL,
  `terminada` int(11) NOT NULL,
  `total_vacantes` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicadores_nirho`
--

LOCK TABLES `indicadores_nirho` WRITE;
/*!40000 ALTER TABLE `indicadores_nirho` DISABLE KEYS */;
/*!40000 ALTER TABLE `indicadores_nirho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modulo`
--

DROP TABLE IF EXISTS `modulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `modulo` (
  `id_modulo` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modulo`
--

LOCK TABLES `modulo` WRITE;
/*!40000 ALTER TABLE `modulo` DISABLE KEYS */;
INSERT INTO `modulo` VALUES (1,'Clima Laboral','Módulo CLB'),(2,'Evaluación de Desempeño','Módulo EVD'),(3,'Evaluación Organizacional 360','Módulo EVO-360'),(4,'Administración por Objetivos','Módulo APO'),(5,'Plan y Vida Carrera','Módulo PVC');
/*!40000 ALTER TABLE `modulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `opcion` (
  `id_opcion` int(11) NOT NULL,
  `id_tema` int(11) NOT NULL,
  `enunciado` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `tipo` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_opcion`),
  KEY `fk_opcion_tema_idx` (`id_tema`),
  CONSTRAINT `fk_opcion_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcion`
--

LOCK TABLES `opcion` WRITE;
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` VALUES (201001,201,'No conoce a fondo sus funciones y responsabilidades, mostrando problemas para entender la función tanto en lo general como lo específico','BR'),(201002,201,'En ocasiones se observa falta de conocimiento y profundidad en el desempeño de sus funciones y responsabilidades.','MR'),(201003,201,'A través del desempeño de su trabajo, ha mostrado conocer y asumir las funciones inherentes y responsabilidades del puesto.','R'),(201004,201,'Conoce y realiza con claridad y precisión las funciones y responsabilidades del puesto, tanto en lo específico como en lo general.','RS'),(201005,201,'Tiene un dominio claro de su puesto y ha demostrado capacidad de conocimiento profundo de la función y hasta de otras áreas o puestos.','E'),(202001,202,'En el desempeño de su trabajo no existe el concepto de satisfacción al cliente, el trabajo tiene que ser repetido varias veces por ensayo-error.','BR'),(202002,202,'En ocasiones tiene dificultad para detectar o satisfacer las necesidades de sus “clientes”, algunas veces tiene que repetir el trabajo para que salga bien.','MR'),(202003,202,'Su trabajo está orientado a la satisfacción de las necesidades de sus “clientes” lo cual logra en la mayoría de las ocasiones.','R'),(202004,202,'Logra reconocer y satisfacer las necesidades de sus “clientes”, tanto internas como externas en forma óptima, logra hacer las cosas bien desde la primera vez, y siempre revisa su trabajo.','RS'),(202005,202,'Satisface la necesidad de sus \"clientes\" y constantemente busca entregar más de lo que se le solicitó y hace mejoras en calidad y proceso.','E'),(203001,203,'Frecuentemente tiene problemas para llevar a cabo sus funciones, el trabajo se acumula constantemente, hay demoras largas y constantes.','BR'),(203002,203,'En ocasiones no alcanza a llevar a cabo sus tareas encomendadas, el trabajo se acumula progresivamente y se observan algunas demoras en el mismo.','MR'),(203003,203,'Cumple oportuna y adecuadamente con sus tareas, revisa con tiempo sus resultados y planea sus actividades.','R'),(203004,203,'Realiza su trabajo en tiempo quedando la oportunidad de realizar otras actividades que enriquecen su función.','RS'),(203005,203,'Constantemente se preocupa por buscar la eficiencia en su trabajo haciendo mejoras que han logrado una mayor producción de resultados.','E'),(204001,204,'Frecuentemente existe desorden en las actividades y trabajos realizados, no hay secuencia ni asignación de prioridades en lo que se hace.','BR'),(204002,204,'En ocasiones se observa falta de secuencia y orden en la realización del trabajo, así como dificultad para asignar prioridades claramente.','MR'),(204003,204,'Planea y efectúa su trabajo en forma secuencial y ordenada con base a prioridades.  Su ejecución es eficiente.','R'),(204004,204,'Planea y efectúa su trabajo en forma oportuna y eficiente, pudiendo establecer con claridad y precisión las siguientes actividades a desarrollar','RS'),(204005,204,'El trabajo que desarrolla está muy organizado y existen controles que permiten planear y desarrollar nuevas actividades a largo plazo.','E'),(205001,205,'No cuenta con registros y controles actualizados, con frecuencia omite actividades importantes, la corrección de desviaciones es casi continua.','BR'),(205002,205,'En ocasiones hay dificultades para mantener el registro actualizado de actividades, así como algunos retrasos y omisiones en el seguimiento, y dificultad para corregir desviaciones.','MR'),(205003,205,'Registra, actualiza y da seguimiento oportuno a su trabajo en forma adecuada, pudiendo detectar y corregir desviaciones.','R'),(205004,205,'El registro y seguimiento de actividades es preciso, claro y completo, los pendientes son mínimos y se atienden con oportunidad.','RS'),(205005,205,'Existen controles del 100% de las actividades del puesto, son tan precisos que rara vez necesitan correcciones','E'),(206001,206,'Ha tenido serias dificultades para poder adquirir los conocimientos necesarios para desempeñar el puesto. Requiere que se le explique un mismo asunto varias veces.','BR'),(206002,206,'En ocasiones demuestra dificultad y lentitud para aprender y poner en práctica los conocimientos necesarios para el desempeño del puesto.','MR'),(206003,206,'Ha logrado adquirir y poner en práctica los conocimientos necesarios para desempeñar el puesto adecuadamente.','R'),(206004,206,'Demuestra gran capacidad para adquirir y poner en práctica nuevos conocimientos en forma sólida y rápida, requiere de un mínimo de explicaciones.','RS'),(206005,206,'Constantemente se le ve ocupado en adquirir nuevos conocimientos para reforzar su desarrollo profesional.','E'),(207001,207,'No muestra disposición o interés por involucrarse en el trabajo de otros, existe una marcada tendencia al individualismo.','BR'),(207002,207,'En ocasiones muestra falta de disposición o interés para colaborar con otras personas del área, muestra preferencia por el trabajo individual.','MR'),(207003,207,'Existe una buena disposición para ayudar y colaborar con otros, se interesa en cooperar.','R'),(207004,207,'Siempre se encuentra dispuesto a cooperar tanto con personas de su área como de otras para buscar en conjunto resultados','RS'),(207005,207,'Frecuente tiene la iniciativa de estar en busca de apoyar a sus compañeros y está dispuesto al trabajo en equipo para lograr resultados.','E'),(208001,208,'Difícilmente toma decisiones por iniciativa propia, tiende a ser dependiente y requiere constante supervisión de su trabajo.','BR'),(208002,208,'Ocasionalmente toma acciones por iniciativa propia, ya que necesita supervisión cercana en sus labores','MR'),(208003,208,'Toma decisiones por si mismo cuando es preciso, requiere un grado normal de supervisión.','R'),(208004,208,'Toma acciones por si mismo en forma correcta y anticipadamente. Existe confianza del jefe por lo que la supervisón es menor.','RS'),(208005,208,'En varias ocasiones, ha tomado iniciativas que han producido mejoras importantes en su trabajo o en el de otros.','E'),(209001,209,'Ha llegado tarde muchas veces en el año, frecuentemente existe nula justificación, a pesar de habérsele llamado la atención el empleado ha mantenido su conducta en forma repetitiva.','BR'),(209002,209,'Ha llegado tarde varias veces durante el año y son extensas las disculpas por su impuntualidad, en ocasiones los retardos no son justificados plenamente.','MR'),(209003,209,'Normalmente llega a tiempo a sus labores, son escasos los retardos y existe una justificación clara para ellos.','R'),(209004,209,'Su puntualidad es muy reconocida por su jefe y los demás y en rara ocasión llega tarde a sus labores.','RS'),(209005,209,'Su puntualidad es ejemplar tanto para la asistencia al trabajo como para atender juntas o compromisos.','E'),(210001,210,'Ha tenido demasiadas faltas durante el año y varias de éstas no se han justificado, el empleado lo ha reconocido a pesar de que se le ha llamado la atención.','BR'),(210002,210,'Tiene faltas durante el año sin justificación plena y es necesario que se le recuerde por parte de su jefe su actuación en este sentido.','MR'),(210003,210,'Demuestra una asistencia correcta en el trabajo; ocasionalmente falta pero con plena justificación.','R'),(210004,210,'La asistencia que demuestra es muy buena. Su responsabilidad en este sentido es clara.','RS'),(210005,210,'Ejemplarmente asiste al trabajo. Cualquier inasistencia es por causas ajenas a su control.','E'),(221001,221,'Por debajo de los resultados mínimos esperados','BR'),(221002,221,'Resultados mínimos','MR'),(221003,221,'Resultados suficientes','R'),(221004,221,'Resultados superiores ','RS'),(221005,221,'Excede en sus resultados','E'),(222001,222,'Por debajo de los resultados mínimos esperados','BR'),(222002,222,'Resultados mínimos','MR'),(222003,222,'Resultados suficientes','R'),(222004,222,'Resultados superiores ','RS'),(222005,222,'Excede en sus resultados','E'),(223001,223,'Por debajo de los resultados mínimos esperados','BR'),(223002,223,'Resultados mínimos','MR'),(223003,223,'Resultados suficientes','R'),(223004,223,'Resultados superiores ','RS'),(223005,223,'Excede en sus resultados','E'),(224001,224,'Por debajo de los resultados mínimos esperados','BR'),(224002,224,'Resultados mínimos','MR'),(224003,224,'Resultados suficientes','R'),(224004,224,'Resultados superiores ','RS'),(224005,224,'Excede en sus resultados','E'),(225001,225,'Por debajo de los resultados mínimos esperados','BR'),(225002,225,'Resultados mínimos','MR'),(225003,225,'Resultados suficientes','R'),(225004,225,'Resultados superiores ','RS'),(225005,225,'Excede en sus resultados','E'),(226001,226,'Por debajo de los resultados mínimos esperados','BR'),(226002,226,'Resultados mínimos','MR'),(226003,226,'Resultados suficientes','R'),(226004,226,'Resultados superiores ','RS'),(226005,226,'Excede en sus resultados','E'),(227001,227,'Por debajo de los resultados mínimos esperados','BR'),(227002,227,'Resultados mínimos','MR'),(227003,227,'Resultados suficientes','R'),(227004,227,'Resultados superiores ','RS'),(227005,227,'Excede en sus resultados','E'),(228001,228,'Por debajo de los resultados mínimos esperados','BR'),(228002,228,'Resultados mínimos','MR'),(228003,228,'Resultados suficientes','R'),(228004,228,'Resultados superiores ','RS'),(228005,228,'Excede en sus resultados','E'),(229001,229,'Por debajo de los resultados mínimos esperados','BR'),(229002,229,'Resultados mínimos','MR'),(229003,229,'Resultados suficientes','R'),(229004,229,'Resultados superiores ','RS'),(229005,229,'Excede en sus resultados','E'),(230001,230,'Por debajo de los resultados mínimos esperados','BR'),(230002,230,'Resultados mínimos','MR'),(230003,230,'Resultados suficientes','R'),(230004,230,'Resultados superiores ','RS'),(230005,230,'Excede en sus resultados','E'),(241001,241,'Por debajo de los resultados mínimos esperados','BR'),(241002,241,'Resultados mínimos','MR'),(241003,241,'Resultados suficientes','R'),(241004,241,'Resultados superiores ','RS'),(241005,241,'Excede en sus resultados','E'),(242001,242,'Por debajo de los resultados mínimos esperados','BR'),(242002,242,'Resultados mínimos','MR'),(242003,242,'Resultados suficientes','R'),(242004,242,'Resultados superiores ','RS'),(242005,242,'Excede en sus resultados','E'),(243001,243,'Por debajo de los resultados mínimos esperados','BR'),(243002,243,'Resultados mínimos','MR'),(243003,243,'Resultados suficientes','R'),(243004,243,'Resultados superiores ','RS'),(243005,243,'Excede en sus resultados','E'),(244001,244,'Por debajo de los resultados mínimos esperados','BR'),(244002,244,'Resultados mínimos','MR'),(244003,244,'Resultados suficientes','R'),(244004,244,'Resultados superiores ','RS'),(244005,244,'Excede en sus resultados','E'),(245001,245,'Por debajo de los resultados mínimos esperados','BR'),(245002,245,'Resultados mínimos','MR'),(245003,245,'Resultados suficientes','R'),(245004,245,'Resultados superiores ','RS'),(245005,245,'Excede en sus resultados','E'),(246001,246,'Por debajo de los resultados mínimos esperados','BR'),(246002,246,'Resultados mínimos','MR'),(246003,246,'Resultados suficientes','R'),(246004,246,'Resultados superiores ','RS'),(246005,245,'Excede en sus resultados','E'),(247001,247,'Por debajo de los resultados mínimos esperados','BR'),(247002,247,'Resultados mínimos','MR'),(247003,247,'Resultados suficientes','R'),(247004,247,'Resultados superiores ','RS'),(247005,247,'Excede en sus resultados','E'),(248001,248,'Por debajo de los resultados mínimos esperados','BR'),(248002,248,'Resultados mínimos','MR'),(248003,248,'Resultados suficientes','R'),(248004,248,'Resultados superiores ','RS'),(248005,248,'Excede en sus resultados','E'),(249001,249,'Por debajo de los resultados mínimos esperados','BR'),(249002,249,'Resultados mínimos','MR'),(249003,249,'Resultados suficientes','R'),(249004,249,'Resultados superiores ','RS'),(249005,249,'Excede en sus resultados','E'),(250001,250,'Por debajo de los resultados mínimos esperados','BR'),(250002,250,'Resultados mínimos','MR'),(250003,250,'Resultados suficientes','R'),(250004,250,'Resultados superiores ','RS'),(250005,250,'Excede en sus resultados','E'),(251001,251,'Por debajo de los resultados mínimos esperados','BR'),(251002,251,'Resultados mínimos','MR'),(251003,251,'Resultados suficientes','R'),(251004,251,'Resultados superiores ','RS'),(251005,251,'Excede en sus resultados','E'),(252001,252,'Por debajo de los resultados mínimos esperados','BR'),(252002,252,'Resultados mínimos','MR'),(252003,252,'Resultados suficientes','R'),(252004,252,'Resultados superiores ','RS'),(252005,252,'Excede en sus resultados','E'),(253001,253,'Por debajo de los resultados mínimos esperados','BR'),(253002,253,'Resultados mínimos','MR'),(253003,253,'Resultados suficientes','R'),(253004,253,'Resultados superiores ','RS'),(253005,253,'Excede en sus resultados','E'),(301001,301,'Por debajo de los resultados mínimos esperados.','BR'),(301002,301,'Sus conocimientos se encuentran por debajo de lo necesario para ejecutar su función.','MR'),(301003,301,'Sus conocimientos aun no son consistentes con lo que se necesita para ejecutar su función. Muestra interés por aprender más.','R'),(301004,301,'Cuenta con los conocimientos suficientes para su función.','RS'),(301005,301,'Cuenta con conocimientos sobresalientes y los desarrolla de manera constante permitiéndole participar en proyectos especiales. Es una referencia para sus compañeros de trabajo.','E'),(302001,302,'Nunca propone nuevos esquemasde trabajo.','BR'),(302002,302,'Ocasionalmente propone ideas de nuevos esquemas.','MR'),(302003,302,'A menudo investiga y propone nuevos esquemas de trabajo.','R'),(302004,302,'Ha documentado nuevos esquemas pero n han sido implementados.','RS'),(302005,302,'Cuenta con nuevos esquemas de trabajo implementados en su área.','E'),(302006,302,'ddsdd','R'),(303001,303,'En el desempeño de su trabajo no existe el concepto de satisfacción al cliente, el trabajo tiene que ser repetido varias veces por ensayo-error.','BR'),(303002,303,'En ocasiones tiene dificultad para detectar o satisfacer las necesidades de sus “clientes”, algunas veces tiene que repetir el trabajo para que salga bien.','MR'),(303003,303,'Su trabajo está orientado a la satisfacción de las necesidades de sus “clientes” lo cual logra en la mayoría de las ocasiones.','R'),(303004,303,'Logra reconocer y satisfacer las necesidades de sus “clientes”, tanto internas como externas en forma óptima, logra hacer las cosas bien desde la primera vez, y siempre revisa su trabajo.','RS'),(303005,303,'Satisface la necesidad de sus \"clientes\" y constantemente busca entregar más de lo que se le solicitó y hace mejoras en calidad y proceso.','E'),(304001,304,'Frecuentemente tiene problemas para llevar a cabo sus funciones, el trabajo se acumula constantemente, hay demoras largas y constantes.','BR'),(304002,304,'En ocasiones no alcanza a llevar a cabo sus tareas encomendadas, el trabajo se acumula progresivamente y se observan algunas demoras en el mismo.','MR'),(304003,304,'Cumple oportuna y adecuadamente con sus tareas, revisa con tiempo sus resultados y planea sus actividades.','R'),(304004,304,'Realiza su trabajo en tiempo quedando la oportunidad de realizar otras actividades que enriquecen su función.','RS'),(304005,304,'Constantemente se preocupa por buscar la eficiencia en su trabajo haciendo mejoras que han logrado una mayor producción de resultados.','E'),(305001,305,'Frecuentemente existe desorden en las actividades y trabajos realizados, no hay secuencia ni asignación de prioridades en lo que se hace.','BR'),(305002,305,'En ocasiones se observa falta de secuencia y orden en la realización del trabajo, así como dificultad para asignar prioridades claramente.','MR'),(305003,305,'Planea y efectúa su trabajo en forma secuencial y ordenada con base a prioridades.  Su ejecución es eficiente.','R'),(305004,305,'Planea y efectúa su trabajo en forma oportuna y eficiente, pudiendo establecer con claridad y precisión las siguientes actividades a desarrollar','RS'),(305005,305,'El trabajo que desarrolla está muy organizado y existen controles que permiten planear y desarrollar nuevas actividades a largo plazo.','E'),(306001,306,'No cuenta con registros y controles actualizados, con frecuencia omite actividades importantes, la corrección de desviaciones es casi continua.','BR'),(306002,306,'En ocasiones hay dificultades para mantener el registro actualizado de actividades, así como algunos retrasos y omisiones en el seguimiento, y dificultad para corregir desviaciones.','MR'),(306003,306,'Registra, actualiza y da seguimiento oportuno a su trabajo en forma adecuada, pudiendo detectar y corregir desviaciones.','R'),(306004,306,'El registro y seguimiento de actividades es preciso, claro y completo, los pendientes son mínimos y se atienden con oportunidad.','RS'),(306005,306,'Existen controles del 100% de las actividades del puesto, son tan precisos que rara vez necesitan correcciones','E');
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participante` (
  `id_participante` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `nivel` int(11) DEFAULT NULL,
  `nivel_texto` varchar(90) DEFAULT NULL,
  `nombres` varchar(90) DEFAULT NULL,
  `a_paterno` varchar(45) DEFAULT NULL,
  `a_materno` varchar(45) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `rfc` varchar(15) DEFAULT NULL,
  `puesto` varchar(45) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `antig_puesto` decimal(10,6) DEFAULT NULL,
  `nivel_escolaridad` varchar(45) DEFAULT NULL,
  `otros_estudios` varchar(45) DEFAULT NULL,
  `idioma` varchar(45) DEFAULT NULL,
  `nivel_idioma` varchar(45) DEFAULT NULL,
  `correo_electronico` varchar(45) DEFAULT NULL,
  `sede` varchar(45) DEFAULT NULL,
  `area_org` varchar(45) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `objetivo_puesto` varchar(180) DEFAULT NULL,
  `funciones` varchar(270) DEFAULT NULL,
  `actividades` varchar(270) DEFAULT NULL,
  `meta_kpi` varchar(90) DEFAULT NULL,
  `cantidad_meta` varchar(45) DEFAULT NULL,
  `unidad_medida` varchar(45) DEFAULT NULL,
  `tiempo` varchar(45) DEFAULT NULL,
  `frecuencia_eval` varchar(45) DEFAULT NULL,
  `id_evaluador` int(11) DEFAULT NULL,
  `id_part_jefe_inm` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_participante`,`id_proyecto`),
  KEY `FK_PATICIPANTE_PROY_idx` (`id_proyecto`),
  CONSTRAINT `FK_PATICIPANTE_PROY` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante`
--

LOCK TABLES `participante` WRITE;
/*!40000 ALTER TABLE `participante` DISABLE KEYS */;
INSERT INTO `participante` VALUES (1,8,1,'Gerente','Juan Carlos',NULL,NULL,'Masculino','JULC781125HES','Gerente General','0016-10-16',1.970000,'Licenciatura',NULL,'Ingés','Comprensión',NULL,'Oficina Central','Corporativo','1-seh521187cluj-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(1,10,1,'Gerente','Juan Carlos',NULL,NULL,'Masculino','JULC781125HES','Gerente General','0016-10-16',1.970000,'Licenciatura',NULL,'Ingés','Comprensión',NULL,'Oficina Central','Corporativo','1-seh521187cluj-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,8,2,'Supervisor','Miriam',NULL,NULL,'Femenino','CAHM741225CDM','Supervisor Producción','0018-03-20',0.550000,'Profesional Técnico',NULL,'Inglés','Lectura',NULL,'Planta','Producción','2-mdc522147mhac-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(2,10,2,'Supervisor','Miriam',NULL,NULL,'Femenino','CAHM741225CDM','Supervisor Producción','0018-03-20',0.550000,'Profesional Técnico',NULL,'Inglés','Lectura',NULL,'Planta','Producción','2-mdc522147mhac-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(3,8,2,'Supervisor','Karen',NULL,NULL,'Femenino','PERK881004CDM','SupervisorVentas','0017-10-06',1.000000,'Licenciatura',NULL,'Inglés','Lectura','tpl.jop@gmail.com','Planta','Ventas','3-mdc400188krep-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(3,10,2,'Supervisor','Karen',NULL,NULL,'Femenino','PERK881004CDM','SupervisorVentas','0017-10-06',1.000000,'Licenciatura',NULL,'Inglés','Lectura','tpl.jop@gmail.com','Planta','Ventas','3-mdc400188krep-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(4,8,2,'Supervisor','Luis Eduardo',NULL,NULL,'Masculino','COFL870711MG','Administración','0014-08-08',4.160000,'Licenciatura',NULL,'Inglés','Principiante',NULL,'Oficina Central','Administración','4-gm117078lfoc-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(4,10,2,'Supervisor','Luis Eduardo',NULL,NULL,'Masculino','COFL870711MG','Administración','0014-08-08',4.160000,'Licenciatura',NULL,'Inglés','Principiante',NULL,'Oficina Central','Administración','4-gm117078lfoc-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(5,8,2,'Supervisor','Alfredo',NULL,NULL,'Masculino','HEPA751204HES','Supervisor de Calidad','0012-08-08',6.160000,'Licenciatura',NULL,'Inglés','Comprensión',NULL,'Planta','Control de Calidad','5-seh402157apeh-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(5,10,2,'Supervisor','Alfredo',NULL,NULL,'Masculino','HEPA751204HES','Supervisor de Calidad','0012-08-08',6.160000,'Licenciatura',NULL,'Inglés','Comprensión',NULL,'Planta','Control de Calidad','5-seh402157apeh-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(6,8,3,'Intendente','Marco',NULL,NULL,'Masculino','ALTM600320CDM','Intendente de Producción','0013-07-24',5.210000,'Técnico','Mantenimiento preventivo y correctivo','Inglés','Principiante','tpl.jop@gmail.com','Planta','Producción','6-mdc023006mtla-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(6,10,3,'Intendente','Marco',NULL,NULL,'Masculino','ALTM600320CDM','Intendente de Producción','0013-07-24',5.210000,'Técnico','Mantenimiento preventivo y correctivo','Inglés','Principiante','tpl.jop@gmail.com','Planta','Producción','6-mdc023006mtla-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(7,8,3,'Intendente','Mario',NULL,NULL,'Masculino','LODM731228HDH','Intendente de Calidad','0012-06-19',6.300000,'Licenciatura',NULL,'Inglés','Principiante',NULL,'Planta','Control de Calidad','7-hdh822137mdol-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5),(7,10,3,'Intendente','Mario',NULL,NULL,'Masculino','LODM731228HDH','Intendente de Calidad','0012-06-19',6.300000,'Licenciatura',NULL,'Inglés','Principiante',NULL,'Planta','Control de Calidad','7-hdh822137mdol-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5),(8,8,3,'Intendente','Susana',NULL,NULL,'Femenino','RODS630221SDX','Jefe de Recursos Humanos','0013-11-01',4.930000,'Licenciatura',NULL,'N/A',NULL,NULL,'Planta','Administración','8-xds122036sdor-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4),(8,10,3,'Intendente','Susana',NULL,NULL,'Femenino','RODS630221SDX','Jefe de Recursos Humanos','0013-11-01',4.930000,'Licenciatura',NULL,'N/A',NULL,NULL,'Planta','Administración','8-xds122036sdor-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4),(9,8,4,'Operativo','Adriana',NULL,NULL,'Femenino','PEHA900310HEM','Producción turno 1','0016-10-16',1.970000,'Bachillerarto',NULL,'Inglés','Técnico',NULL,'Planta','Producción','9-meh013009ahep-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(9,10,4,'Operativo','Adriana',NULL,NULL,'Femenino','PEHA900310HEM','Producción turno 1','0016-10-16',1.970000,'Bachillerarto',NULL,'Inglés','Técnico',NULL,'Planta','Producción','9-meh013009ahep-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(10,8,4,'Operativo','Juan ',NULL,NULL,'Masculino','SARRJ751003195','Producción turno 2','0005-10-15',12.980000,'Técnico',NULL,'Inglés','Técnico',NULL,'Planta','Producción','10-591300157jrras-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(10,10,4,'Operativo','Juan ',NULL,NULL,'Masculino','SARRJ751003195','Producción turno 2','0005-10-15',12.980000,'Técnico',NULL,'Inglés','Técnico',NULL,'Planta','Producción','10-591300157jrras-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(11,8,4,'Operativo','Alberto',NULL,NULL,'Masculino','JURA880304HDF','Encargado de Empaques','0016-04-19',2.470000,'Técnico',NULL,'N/A',NULL,NULL,'Planta','Producción','11-fdh403088aruj-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(11,10,4,'Operativo','Alberto',NULL,NULL,'Masculino','JURA880304HDF','Encargado de Empaques','0016-04-19',2.470000,'Técnico',NULL,'N/A',NULL,NULL,'Planta','Producción','11-fdh403088aruj-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(12,8,4,'Operativo','Araceli',NULL,NULL,'Femenino','HEDA9003MNDF','Calidad Turno 1','0015-06-24',3.290000,'Licenciatura',NULL,'N/A',NULL,NULL,'Planta','Control de Calidad','12-fdnm3009adeh-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7),(12,10,4,'Operativo','Araceli',NULL,NULL,'Femenino','HEDA9003MNDF','Calidad Turno 1','0015-06-24',3.290000,'Licenciatura',NULL,'N/A',NULL,NULL,'Planta','Control de Calidad','12-fdnm3009adeh-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7),(13,8,4,'Operativo','Emmanuel',NULL,NULL,'Masculino','LOCE781009HDF','Calidad Turno 2','0009-03-07',9.590000,'Técnico',NULL,'N/A',NULL,NULL,'Planta','Control de Calidad','13-fdh900187ecol-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7),(13,10,4,'Operativo','Emmanuel',NULL,NULL,'Masculino','LOCE781009HDF','Calidad Turno 2','0009-03-07',9.590000,'Técnico',NULL,'N/A',NULL,NULL,'Planta','Control de Calidad','13-fdh900187ecol-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7),(14,8,4,'Operativo','Luis ',NULL,NULL,'Masculino','CAEL800623HDF','Auxiliar administrativo','0013-12-21',4.790000,'Licenciatura',NULL,'Inglés','Principiante',NULL,'Oficina Central','Administración','14-fdh326008leac-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8),(14,10,4,'Operativo','Luis ',NULL,NULL,'Masculino','CAEL800623HDF','Auxiliar administrativo','0013-12-21',4.790000,'Licenciatura',NULL,'Inglés','Principiante',NULL,'Oficina Central','Administración','14-fdh326008leac-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8),(15,8,4,'Operativo','Matha',NULL,NULL,'Femenino','LOGM740330MDF','Vendedor','0005-10-15',12.980000,'Técnico',NULL,'N/A',NULL,NULL,'Oficina Central','Ventas','15-fdm033047mgol-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),(15,10,4,'Operativo','Matha',NULL,NULL,'Femenino','LOGM740330MDF','Vendedor','0005-10-15',12.980000,'Técnico',NULL,'N/A',NULL,NULL,'Oficina Central','Ventas','15-fdm033047mgol-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),(16,8,4,'Operativo','Roberto',NULL,NULL,'Masculino','ALRR820214HDF','Vendedor','0016-04-19',2.470000,'Técnico',NULL,'N/A',NULL,NULL,'Oficina Central','Ventas','16-fdh412028rrla-8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),(16,10,4,'Operativo','Roberto',NULL,NULL,'Masculino','ALRR820214HDF','Vendedor','0016-04-19',2.470000,'Técnico',NULL,'N/A',NULL,NULL,'Oficina Central','Ventas','16-fdh412028rrla-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3);
/*!40000 ALTER TABLE `participante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participanteapo`
--

DROP TABLE IF EXISTS `participanteapo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participanteapo` (
  `id` int(11) NOT NULL,
  `a_materno` varchar(255) DEFAULT NULL,
  `a_paterno` varchar(255) DEFAULT NULL,
  `antig_puesto` double DEFAULT NULL,
  `area_org` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `id_part_jefe_inm` int(11) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `idioma` varchar(255) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
  `nivel_escolaridad` varchar(255) DEFAULT NULL,
  `nivel_idioma` varchar(255) DEFAULT NULL,
  `nivel_texto` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `otros_estudios` varchar(255) DEFAULT NULL,
  `puesto` varchar(255) DEFAULT NULL,
  `rfc` varchar(255) DEFAULT NULL,
  `sede` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participanteapo`
--

LOCK TABLES `participanteapo` WRITE;
/*!40000 ALTER TABLE `participanteapo` DISABLE KEYS */;
INSERT INTO `participanteapo` VALUES (1,'López','Junco',1.97,'Corporativo','read2330@gmail.com','0016-10-16','Masculino',0,7,'Ingés',1,'Licenciatura','Comprensión','Gerente','Juan Carlos',NULL,'Gerente General','JULC781125HES','Oficina Central'),(2,'Hernández','Carmona',0.55,'Producción','read2330@gmail.com','0018-03-20','Femenino',1,7,'Inglés',2,'Profesional Técnico','Lectura','Supervisor','Miriam',NULL,'Supervisor Producción','CAHM741225CDM','Planta'),(3,'Rángel','Pérez',1,'Ventas','read2330@gmail.com','0017-10-06','Femenino',1,7,'Inglés',2,'Licenciatura','Lectura','Supervisor','Karen',NULL,'SupervisorVentas','PERK881004CDM','Planta'),(4,'Franco','Contreras',4.16,'Administración','read2330@gmail.com','0014-08-08','Masculino',1,7,'Inglés',2,'Licenciatura','Principiante','Supervisor','Luis Eduardo',NULL,'Administración','COFL870711MG','Oficina Central'),(5,'Prado','Hernández',6.16,'Control de Calidad','read2330@gmail.com','0012-08-08','Masculino',1,7,'Inglés',2,'Licenciatura','Comprensión','Supervisor','Alfredo',NULL,'Supervisor de Calidad','HEPA751204HES','Planta'),(6,'Téllez','Alcocer',5.21,'Producción','read2330@gmail.com','0013-07-24','Masculino',2,7,'Inglés',3,'Técnico','Principiante','Intendente','Marco','Mantenimiento preventivo y correctivo','Intendente de Producción','ALTM600320CDM','Planta'),(7,'Domínguez','López',6.3,'Control de Calidad','read2330@gmail.com','0012-06-19','Masculino',5,7,'Inglés',3,'Licenciatura','Principiante','Intendente','Mario',NULL,'Intendente de Calidad','LODM731228HDH','Planta'),(8,'Domínguez','Rodríguez',4.93,'Administración','read2330@gmail.com','0013-11-01','Femenino',4,7,'N/A',3,'Licenciatura',NULL,'Intendente','Susana',NULL,'Jefe de Recursos Humanos','RODS630221SDX','Planta'),(9,'Hernández','Pérez',1.97,'Producción','read2330@gmail.com','0016-10-16','Femenino',6,7,'Inglés',4,'Bachillerarto','Técnico','Operativo','Adriana',NULL,'Producción turno 1','PEHA900310HEM','Planta'),(10,'Ramirez','Sandoval',12.98,'Producción','read2330@gmail.com','0005-10-15','Masculino',6,7,'Inglés',4,'Técnico','Técnico','Operativo','Juan ',NULL,'Producción turno 2','SARRJ751003195','Planta'),(11,'Rivera','Júarez',2.47,'Producción','read2330@gmail.com','0016-04-19','Masculino',6,7,'N/A',4,'Técnico',NULL,'Operativo','Alberto',NULL,'Encargado de Empaques','JURA880304HDF','Planta'),(12,'Domínguez','Herrera',3.29,'Control de Calidad','read2330@gmail.com','0015-06-24','Femenino',7,7,'N/A',4,'Licenciatura',NULL,'Operativo','Araceli',NULL,'Calidad Turno 1','HEDA9003MNDF','Planta'),(13,'Castañeda','López',9.59,'Control de Calidad','read2330@gmail.com','0009-03-07','Masculino',7,7,'N/A',4,'Técnico',NULL,'Operativo','Emmanuel',NULL,'Calidad Turno 2','LOCE781009HDF','Planta'),(14,'Espinoza','Cárdenas',4.79,'Administración','read2330@gmail.com','0013-12-21','Masculino',8,7,'Inglés',4,'Licenciatura','Principiante','Operativo','Luis ',NULL,'Auxiliar administrativo','CAEL800623HDF','Oficina Central'),(15,'González','López',12.98,'Ventas','read2330@gmail.com','0005-10-15','Femenino',3,7,'N/A',4,'Técnico',NULL,'Operativo','Matha',NULL,'Vendedor','LOGM740330MDF','Oficina Central'),(16,'Aleman','Ruiz',2.47,'Ventas','read2330@gmail.com','0016-04-19','Masculino',3,7,'N/A',4,'Técnico',NULL,'Operativo','Roberto',NULL,'Vendedor','ALRR820214HDF','Oficina Central');
/*!40000 ALTER TABLE `participanteapo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participanteapoamp`
--

DROP TABLE IF EXISTS `participanteapoamp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participanteapoamp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_participante` int(11) DEFAULT NULL,
  `objetivo_puesto` varchar(255) DEFAULT NULL,
  `participanteapo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKakrh5b9lony0x95n1gi8jxk3o` (`participanteapo`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participanteapoamp`
--

LOCK TABLES `participanteapoamp` WRITE;
/*!40000 ALTER TABLE `participanteapoamp` DISABLE KEYS */;
INSERT INTO `participanteapoamp` VALUES (1,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',1),(2,2,'Ejecutar los programas de producción',2),(3,6,'Supervisar el proceso de producción',6),(4,9,'Lograr una operación eficiente de unidades y máximizar la eficiencia de la capacidad instalada en elturno A',9),(5,10,'Lograr una operación eficiente de unidades y máximizar la eficiencia de la capacidad instalada en el turno B',10),(6,11,'Asegurar que los productos conserven sus características funcionales y de calidad al ser empaquetados para entrega',11),(7,5,'Garantizar la aplicación de la política y estándares de calidad ',5),(8,7,'Asegurar la calidad de los productos de la empresa',7),(9,12,'Ejecutar el programa diario de calidad ',12),(10,13,'Ejecutar el programa diario de calidad ',13),(11,3,'Establecer e Implementar la estrategia de ventas',3),(12,15,'Asegurar la cobertura de los sectores comericales asignados',15),(13,16,'Asegurar la cobertura de los sectores comericales asignados',16),(14,4,'Optimizar la gestión de recursos respecto a la operación de la mpresa',4),(15,8,'Abastecer de Recursos Humanos y Materiales a toda la empresa',8),(16,14,'Realizar las gestiones administrativas que requiere la empresa',14),(17,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL),(18,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL),(19,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL),(20,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL),(21,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL),(22,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL),(23,1,'Optimizar los recursos de la empresa, buscando siempre mejorar los márgenes de utilidad, cnservando la calidad de los productos',NULL);
/*!40000 ALTER TABLE `participanteapoamp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participanteapoamp_actividad`
--

DROP TABLE IF EXISTS `participanteapoamp_actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participanteapoamp_actividad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calificacion` int(11) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `fecha_termino` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `funcion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKebtey7ywv9w0822pmmpwn5a3g` (`funcion`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participanteapoamp_actividad`
--

LOCK TABLES `participanteapoamp_actividad` WRITE;
/*!40000 ALTER TABLE `participanteapoamp_actividad` DISABLE KEYS */;
INSERT INTO `participanteapoamp_actividad` VALUES (1,5,'2018-12-20','2018-12-21',NULL,'Muestra',_binary '\0',57),(2,5,'2018-12-20','2018-12-28',NULL,'Cambio',_binary '',57),(3,4,'2018-12-20','2018-12-21',NULL,'Muestra 2',_binary '\0',58),(4,4,'2018-12-20',NULL,'2018-11-12','JUAN CARLOS',_binary '',58),(5,NULL,'2018-12-21',NULL,'2018-11-12','JUAN CARLOS',_binary '',58),(6,NULL,'2018-12-21',NULL,'2018-12-27','Santa Fe México',_binary '',58),(7,NULL,'2018-12-21',NULL,'2018-12-25','JopF',_binary '',58),(8,NULL,'2018-12-21',NULL,'2018-12-26','Estado de México',_binary '',58);
/*!40000 ALTER TABLE `participanteapoamp_actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participanteapoamp_funcion`
--

DROP TABLE IF EXISTS `participanteapoamp_funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participanteapoamp_funcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calificacion` int(11) DEFAULT NULL,
  `cantidad_meta` varchar(255) DEFAULT NULL,
  `frecuencia_eval` varchar(255) DEFAULT NULL,
  `funcion` varchar(255) DEFAULT NULL,
  `id_evaluador` int(11) DEFAULT NULL,
  `meta_kpi` varchar(255) DEFAULT NULL,
  `tiempo` varchar(255) DEFAULT NULL,
  `participante_apo_amp` int(11) DEFAULT NULL,
  `check_jefe` bit(1) NOT NULL,
  `check_participante` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe743lka652xmp05ot98yafmp1` (`participante_apo_amp`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participanteapoamp_funcion`
--

LOCK TABLES `participanteapoamp_funcion` WRITE;
/*!40000 ALTER TABLE `participanteapoamp_funcion` DISABLE KEYS */;
INSERT INTO `participanteapoamp_funcion` VALUES (14,NULL,'235.8','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad producida','Mensual',9,_binary '\0',_binary '\0'),(15,NULL,'76.7','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad vendida','Mensual',9,_binary '\0',_binary '\0'),(16,NULL,'20%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Margen de utilidad de la empresa','Mensual',9,_binary '\0',_binary '\0'),(17,NULL,'76.7','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad vendida','Mensual',10,_binary '\0',_binary '\0'),(18,NULL,'20%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Margen de utilidad de la empresa','Mensual',11,_binary '\0',_binary '\0'),(19,NULL,'235.8','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad producida','Mensual',12,_binary '\0',_binary '\0'),(20,NULL,'76.7','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad vendida','Mensual',12,_binary '\0',_binary '\0'),(21,NULL,'20%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Margen de utilidad de la empresa','Mensual',12,_binary '\0',_binary '\0'),(22,NULL,'80%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'cociente entre unidades vendidas y producidas','Mensual',12,_binary '\0',_binary '\0'),(23,NULL,'85%','Bimestral','Establecer la estrategia de ventas de la empresa y dar seguimiento',0,'Porcentaje de ventas reales entre programadas','Mensual',12,_binary '\0',_binary '\0'),(24,NULL,'5%','Bimestral','Establecer la política de calidad de los productos y servicios de la empreesa',0,'Porcentaje de producto no conforme respeto a pdoructos revisados.','Mensual',12,_binary '\0',_binary '\0'),(25,NULL,'235.8','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad producida','Mensual',13,_binary '\0',_binary '\0'),(26,NULL,'76.7','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad vendida','Mensual',13,_binary '\0',_binary '\0'),(27,NULL,'20%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Margen de utilidad de la empresa','Mensual',13,_binary '\0',_binary '\0'),(28,NULL,'80%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'cociente entre unidades vendidas y producidas','Mensual',13,_binary '\0',_binary '\0'),(29,NULL,'85%','Bimestral','Establecer la estrategia de ventas de la empresa y dar seguimiento',0,'Porcentaje de ventas reales entre programadas','Mensual',13,_binary '\0',_binary '\0'),(30,NULL,'5%','Bimestral','Establecer la política de calidad de los productos y servicios de la empreesa',0,'Porcentaje de producto no conforme respeto a pdoructos revisados.','Mensual',13,_binary '\0',_binary '\0'),(31,NULL,'85%','Bimestral','Elaborar los programas diarios de producción',0,'Porcentaje de unidades producidas respecto a programadas por turno','Mensual',14,_binary '\0',_binary '\0'),(32,NULL,'2%','Bimestral','Programar el calendario de abastecimiento de insumos para la producción',0,'Porcentaje de Unidades no producidas por falta de insumos respecto a pedidos fincados','Mensual',14,_binary '\0',_binary '\0'),(33,NULL,'2%','Bimestral','Mantener operando la capacidad instalada de la planta',0,'Porcentaje de horas que la infraestructura no operó respecto a las horas hábiles del periodo','Mensual',15,_binary '\0',_binary '\0'),(34,NULL,'95%','Bimestral','Producir las unidades establecidas en el Progrma de Producción durante el turno A',0,'Porcentaje de unidades reales producidas entre unidades programadas en turno A','Semanal',16,_binary '\0',_binary '\0'),(35,NULL,'95%','Bimestral','Producir las unidades establecidas en el Progrma de Producción durante el turno B',0,'Porcentaje de unidades reales producidas entre unidades programadas en turno B','Semanal',17,_binary '\0',_binary '\0'),(36,NULL,'3%','Bimestral','Empaquetar las unidades aprobadas por el área de calidad',0,'Número de unidades de producción mutiladas o dañadas al empcar entre el númeto total de unidades empacadas','Semanal',18,_binary '\0',_binary '\0'),(37,NULL,'95%','Bimestral','Establecer los estándares de calidad de los producos',0,'Porcentaje de revisiones realizadas conforme a los métodos establecidos','Mensual',19,_binary '\0',_binary '\0'),(38,NULL,'95%','Bimestral','Establecer el programa diario de Control de calidad',0,'Porcentaje de lotes de calidad asignados  respecto al total de la muestra establecida','Mensual',20,_binary '\0',_binary '\0'),(39,NULL,'135','Bimestral','Registrar y canalizar los productos no conformes',0,'Costo de rehabilitacion de producto no conforme entre el total de productos no conformes','Mensual',20,_binary '\0',_binary '\0'),(40,NULL,'2%','Bimestral','Conocer los puntos de revisión de calidad,  los métodos establecidos y aplicarlos consistentemente.',0,'Porcentaje de producto no conforme respeto a productos revisados en turno A','Mensual',21,_binary '\0',_binary '\0'),(41,NULL,'2%','Bimestral','Conocer los puntos de revisión de calidad,  los métodos establecidos y aplicarlos consistentemente.',0,'Porcentaje de producto no conforme respeto a pdoructos revisados en turno B','Mensual',22,_binary '\0',_binary '\0'),(42,NULL,'75%','Bimestral','Elaborar el programa anual de ventas y elaborr laas proyecciones de ingresos',0,'Porcentaje de cobertura de los sectores identificados','Mensual',23,_binary '\0',_binary '\0'),(43,NULL,'70%','Bimestral','Ejecutar los programas de ventas en su sector',0,'Porcentaje de pedidos levntados respecto a nnumero de visitas a prospectos y clientes realiadas','Mensual',24,_binary '\0',_binary '\0'),(44,NULL,'99%','Bimestral','Revisar la facturación y ejecutar la cobranza dentro de su sector',0,'Porcentaje de pedidos cobrados respedto a los pedidos levntados','Mensual',24,_binary '\0',_binary '\0'),(45,NULL,'70%','Bimestral','Ejecutar los programas de ventas en su sector',0,'Porcentaje de pedidos levntados respecto a visitar a prospectos y clientes realiadas','Mensual',25,_binary '\0',_binary '\0'),(46,NULL,'99%','Bimestral','Revisar la facturación y ejecutar la cobranza dentro de su sector',0,'Porcentaje de pedidos cobrdo respedto a los pedidos levntados','Mensual',25,_binary '\0',_binary '\0'),(47,NULL,'95%','Bimestral','Establecer el presupuesto, las políticas y procedimientos para la administración de Recursos',0,'Porcentaje de presupuesto ejercido respecto al programado','Mensual',26,_binary '\0',_binary '\0'),(48,NULL,'5%','Bimestral','Operaar el programa de captación de recursos humanos de la empresa',0,'Cociente de la diferencia de entradas y salidas de personal entre y el promedio de empleados del mes, por 100','Mensual',27,_binary '\0',_binary '\0'),(49,NULL,'9.5','Bimestral','Operar el programa de captación de recursos mteriales de la empresa',0,' Costo de las mercancías vendidas durante un período de tiempo determinado, dividido entre el inventario promedio durante el período','Mensual',27,_binary '\0',_binary '\0'),(50,NULL,'99.5%','Bimestral','Gestionar diferentes encargos operqtivos del área administrativa',0,'Porcentaje de encargos realizados en tiempo y forma respecto a los encargos recibidos','Mensual',28,_binary '\0',_binary '\0'),(51,NULL,'235.8','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad producida','Mensual',1,_binary '\0',_binary '\0'),(52,NULL,'76.7','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Costo de Reursos Humanos y Materiales por unidad vendida','Mensual',1,_binary '\0',_binary '\0'),(53,NULL,'20%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'Margen de utilidad de la empresa','Mensual',1,_binary '\0',_binary '\0'),(54,NULL,'80%','Bimestral','Establecer las directrices de la administración de recursos humanos, materiales y financieros de la empresa',0,'cociente entre unidades vendidas y producidas','Mensual',1,_binary '\0',_binary '\0'),(55,NULL,'85%','Bimestral','Establecer la estrategia de ventas de la empresa y dar seguimiento',0,'Porcentaje de ventas reales entre programadas','Mensual',1,_binary '\0',_binary '\0'),(56,NULL,'5%','Bimestral','Establecer la política de calidad de los productos y servicios de la empreesa',0,'Porcentaje de producto no conforme respeto a pdoructos revisados.','Mensual',1,_binary '\0',_binary '\0'),(57,5,'85%','Bimestral','Elaborar los programas diarios de producción',0,'Porcentaje de unidades producidas respecto a programadas por turno','Mensual',2,_binary '',_binary '\0'),(58,1,'2%','Bimestral','Programar el calendario de abastecimiento de insumos para la producción',0,'Porcentaje de Unidades no producidas por falta de insumos respecto a pedidos fincados','Mensual',2,_binary '\0',_binary '\0'),(59,NULL,'2%','Bimestral','Mantener operando la capacidad instalada de la planta',0,'Porcentaje de horas que la infraestructura no operó respecto a las horas hábiles del periodo','Mensual',3,_binary '\0',_binary '\0'),(60,NULL,'95%','Bimestral','Producir las unidades establecidas en el Progrma de Producción durante el turno A',0,'Porcentaje de unidades reales producidas entre unidades programadas en turno A','Semanal',4,_binary '\0',_binary '\0'),(61,NULL,'95%','Bimestral','Producir las unidades establecidas en el Progrma de Producción durante el turno B',0,'Porcentaje de unidades reales producidas entre unidades programadas en turno B','Semanal',5,_binary '\0',_binary '\0'),(62,NULL,'3%','Bimestral','Empaquetar las unidades aprobadas por el área de calidad',0,'Número de unidades de producción mutiladas o dañadas al empcar entre el númeto total de unidades empacadas','Semanal',6,_binary '\0',_binary '\0'),(63,NULL,'95%','Bimestral','Establecer los estándares de calidad de los producos',0,'Porcentaje de revisiones realizadas conforme a los métodos establecidos','Mensual',7,_binary '\0',_binary '\0'),(64,NULL,'95%','Bimestral','Establecer el programa diario de Control de calidad',0,'Porcentaje de lotes de calidad asignados  respecto al total de la muestra establecida','Mensual',8,_binary '\0',_binary '\0'),(65,NULL,'135','Bimestral','Registrar y canalizar los productos no conformes',0,'Costo de rehabilitacion de producto no conforme entre el total de productos no conformes','Mensual',8,_binary '\0',_binary '\0'),(66,NULL,'2%','Bimestral','Conocer los puntos de revisión de calidad,  los métodos establecidos y aplicarlos consistentemente.',0,'Porcentaje de producto no conforme respeto a productos revisados en turno A','Mensual',9,_binary '\0',_binary '\0'),(67,NULL,'2%','Bimestral','Conocer los puntos de revisión de calidad,  los métodos establecidos y aplicarlos consistentemente.',0,'Porcentaje de producto no conforme respeto a pdoructos revisados en turno B','Mensual',10,_binary '\0',_binary '\0'),(68,NULL,'75%','Bimestral','Elaborar el programa anual de ventas y elaborr laas proyecciones de ingresos',0,'Porcentaje de cobertura de los sectores identificados','Mensual',11,_binary '\0',_binary '\0'),(69,NULL,'70%','Bimestral','Ejecutar los programas de ventas en su sector',0,'Porcentaje de pedidos levntados respecto a nnumero de visitas a prospectos y clientes realiadas','Mensual',12,_binary '\0',_binary '\0'),(70,NULL,'99%','Bimestral','Revisar la facturación y ejecutar la cobranza dentro de su sector',0,'Porcentaje de pedidos cobrados respedto a los pedidos levntados','Mensual',12,_binary '\0',_binary '\0'),(71,NULL,'70%','Bimestral','Ejecutar los programas de ventas en su sector',0,'Porcentaje de pedidos levntados respecto a visitar a prospectos y clientes realiadas','Mensual',13,_binary '\0',_binary '\0'),(72,NULL,'99%','Bimestral','Revisar la facturación y ejecutar la cobranza dentro de su sector',0,'Porcentaje de pedidos cobrdo respedto a los pedidos levntados','Mensual',13,_binary '\0',_binary '\0'),(73,NULL,'95%','Bimestral','Establecer el presupuesto, las políticas y procedimientos para la administración de Recursos',0,'Porcentaje de presupuesto ejercido respecto al programado','Mensual',14,_binary '\0',_binary '\0'),(74,NULL,'5%','Bimestral','Operaar el programa de captación de recursos humanos de la empresa',0,'Cociente de la diferencia de entradas y salidas de personal entre y el promedio de empleados del mes, por 100','Mensual',15,_binary '\0',_binary '\0'),(75,NULL,'9.5','Bimestral','Operar el programa de captación de recursos mteriales de la empresa',0,' Costo de las mercancías vendidas durante un período de tiempo determinado, dividido entre el inventario promedio durante el período','Mensual',15,_binary '\0',_binary '\0'),(76,NULL,'99.5%','Bimestral','Gestionar diferentes encargos operqtivos del área administrativa',0,'Porcentaje de encargos realizados en tiempo y forma respecto a los encargos recibidos','Mensual',16,_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `participanteapoamp_funcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participantepvc`
--

DROP TABLE IF EXISTS `participantepvc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participantepvc` (
  `id` int(11) NOT NULL,
  `a_materno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `a_paterno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `antig_puesto` double DEFAULT NULL,
  `area_org` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `correo_electronico` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `genero` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_part_jefe_inm` int(11) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `idioma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
  `nivel_escolaridad` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nivel_idioma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nivel_texto` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombres` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `otros_estudios` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `puesto` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rfc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sede` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `esfera` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `especialidad` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nivel_p` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participantepvc`
--

LOCK TABLES `participantepvc` WRITE;
/*!40000 ALTER TABLE `participantepvc` DISABLE KEYS */;
/*!40000 ALTER TABLE `participantepvc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plantilla_cuestionario`
--

DROP TABLE IF EXISTS `plantilla_cuestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plantilla_cuestionario` (
  `id_modulo` int(11) NOT NULL,
  `id_tema` int(11) NOT NULL,
  PRIMARY KEY (`id_modulo`,`id_tema`),
  KEY `fk_plantilla_tema_idx` (`id_tema`),
  KEY `fk_plantilla_proyecto_idx` (`id_modulo`),
  CONSTRAINT `fk_plantilla_proyecto` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`),
  CONSTRAINT `fk_plantilla_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantilla_cuestionario`
--

LOCK TABLES `plantilla_cuestionario` WRITE;
/*!40000 ALTER TABLE `plantilla_cuestionario` DISABLE KEYS */;
INSERT INTO `plantilla_cuestionario` VALUES (1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,110),(1,111),(1,112),(2,201),(2,202),(2,203),(2,204),(2,205),(2,206),(2,207),(2,208),(2,209),(2,210),(2,221),(2,222),(2,223),(2,224),(2,225),(2,226),(2,227),(2,228),(2,229),(2,230),(2,241),(2,242),(2,243),(2,244),(2,245),(2,246),(2,247),(2,248),(2,249),(2,250),(2,251),(2,252),(2,253),(3,301),(3,302),(3,303),(3,304),(3,305),(3,306);
/*!40000 ALTER TABLE `plantilla_cuestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pregunta` (
  `id_pregunta` int(11) NOT NULL,
  `id_tema` int(11) NOT NULL,
  `enunciado` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`id_pregunta`),
  KEY `fk_pregunta_tema_idx` (`id_tema`),
  CONSTRAINT `fk_pregunta_tema` FOREIGN KEY (`id_tema`) REFERENCES `tema` (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` VALUES (501,101,'Esta compania es la mejor empresa de sistemas del pais',1),(502,101,'Me siento orgulloso de trabajar en esta compania',1),(503,101,'Nuestros servicios tienen mucho prestigio entre los clientes',1),(504,101,'Esta es una empresa solida',1),(505,101,'Esta es una empresa con futuro',1),(506,101,'Mucha gente desearia tener un trabajo como el que tengo en esta compania',1),(507,101,'A nuestros clientes les interesa adquirir mas de nuestros servicios',1),(508,101,'Entiendo claramente los servicios que brinda la compania a nuestros clientes',1),(509,101,'En esta empresa tengo un trabajo seguro',1),(510,101,'Celebran festividades en tu trabajo',1),(601,102,'Tu espacio de trabajo se mantienen limpio la mayor parte del tiempo',1),(602,102,'Estoy satisfecho con las condiciones higienicas de los banos ',1),(603,102,'Mi oficina o lugar de trabajo tiene las condiciones adecuadas para trabajar',1),(604,102,'Hay buena vigilancia y seguridad en las instalaciones donde trabajo',1),(605,102,'En mi  trabajo cotidiano utilizo la tecnologia adecuada',1),(606,102,'Cuento con los materiales y el equipo necesario para realizar bien mi trabajo',1),(607,102,'Hay un buen programa de mantenimiento para los equipos de la empresa',1),(608,102,'En caso de una catastrofe se como debo de actuar',1),(701,103,'Puedo hablar con mi jefe directo cuando lo necesito',1),(702,103,'Mi  jefe inmediato es un buen lider de grupo',1),(703,103,'A mi  jefe inmediato le importo como persona',1),(704,103,'En el trabajo tengo a uno de mis mejores amigos',1),(705,103,'Confio plenamente en mi  jefe inmediato',1),(706,103,'Mi jefe inmediato es justo con nosotros',1),(707,103,'En esta empresa los jefes siempre escuchan a los empleados',1),(708,103,'Cuando mi jefe me promete algo lo cumple',1),(709,103,'Confío plenamente en mis companeros de trabajo',1),(801,104,'Entiendo claramente que se espera de mi en el trabajo',1),(802,104,'Mis obligaciones y responsabilidades en el trabajo estan bien definidas',1),(803,104,'Los pizarrones nos proporcionan información util',1),(804,104,'En esta compania los empleados informan a su jefe las cosas',1),(805,104,'importantes que ocurren en su trabajo',1),(806,104,'Los empleados sabemos quienes son los directivos de la empresa',1),(807,104,'Las juntas de trabajo son un excelente medio para informarnos de los aspectos relevantes para nuestro trabajo',1),(808,104,'Hay buena comunicacion entre la compania y sus empleados',1),(809,104,'En esta compania mis opiniones se toman en cuenta',1),(810,104,'Recibo oportunamente la informacion adecuada para hacer mi trabajo',1),(901,105,'Me gusta venir a trabajar cada dia',1),(902,105,'La mision de la empresa me ayuda a comprender que mi trabajo es importante',1),(903,105,'Entiendo la forma en que en mi trabajo contribuye a cumplir los objetivos de la compania',1),(904,105,'Crear un buen ambiente de trabajo es parte de la mision de la empresa',1),(905,105,'Las metas y objetivos de mi area de trabajo estan claramente definidas',1),(906,105,'Los empleados conocen los valores de la compania',1),(907,105,'Los empleados comprenden la misión de la compania',1),(1001,106,'La ultima vez que un companero me pido apoyo, se lo otorgue',1),(1002,106,'En la ultima semana le he dado apoyo a algun companero',1),(1003,106,'Mi jefe inmediato impulsa el trabajo en equipo dentro del area',1),(1004,106,'La ultima vez que le pedi apoyo a un companero de trabajo, recibi la ayuda que necesita',1),(1005,106,'Mis companeros trabajan bien en equipo',1),(1006,106,'Hay un ambiente de cooperacion y ayuda mutua entre los empleados de mi area',1),(1007,106,'Los jefes y gerentes de la empresa están siempre dispuestos a trabajar como un solo equipo',1),(1008,106,'En la ultima semana he recibido el apoyo de alguna persona para realizar mi trabajo',1),(1101,107,'En este ultimo ano dentro del trabajo, he tenido oportunidad de aprender y desarrollarme',1),(1102,107,'Mi  jefe inmediato impulsa mi desarrollo',1),(1103,107,'En la compania hay alguna persona que alienta mi desarrollo',1),(1104,107,'Estoy satisfecho con la capacitacion que recibo para realizar mi trabajo actual',1),(1105,107,'Las personas que ingresan a la compania reciben la capacitacion necesaria para su integracion a la empresa',1),(1106,107,'Esta empresa me da la posibilidad de hacer carrera',1),(1107,107,'La capacitacion que recibi en el ultimo ano me ha ayudado a ser mas productivo en el trabajo',1),(1108,107,'Los ascensos en esta compania se basan en el desempeno',1),(1109,107,'En los ultimos seis meses alguien de mi trabajo ha hablado conmigo sobre',1),(1201,108,'La empresa reconoce y da incentivos al buen desempeno de sus colaboradores',1),(1202,108,'Mi salario en esta compania es igual o mejor que el que podria ganar en otro trabajo similar',1),(1203,108,'En esta empresa recibo un pago justo a cambio de lo que doy en mi trabajo',1),(1204,108,'Estoy satisfecho con el salario que recibo por mi trabajo',1),(1205,108,'Estoy satisfecho con las prestaciones que recibo en esta empresa',1),(1206,108,'Mi salario es justo comparado con el de otras personas en la empresa',1),(1207,108,'Estoy satisfecho con el reconocimiento que recibo por hacer un buen trabajo',1),(1208,108,'En la ultima semana he felicitado a alguien por hacer bien su trabajo',1),(1209,108,'Mi jefe me da reconocimiento cuando hago un buen trabajo',1),(1210,108,'En la ultima semana he recibido algun reconocimiento o felicitacion por mi buen trabajo o desempeno',1),(1301,109,'Siempre que necesito ayuda para atender a un cliente interno y/o externo se a quien recurrir',1),(1302,109,'En la empresa entendemos las necesidades de nuestros clientes',1),(1303,109,'En esta compania hacemos lo que sea necesario para satisfacer los requerimientos de nuestros clientes',1),(1304,109,'Mis companeros de trabajo se caracterizan por su rapida respuesta a  los requerimientos que atienden',1),(1305,109,'El personal de las oficinas brinda apoyo y facilidades.',1),(1306,109,'La empresa siempre cumple lo que ofrece',1),(1307,109,'La empresa mantiene una relacion cercana y amable con los clientes',1),(1308,109,'La empresa visita a cada cliente con la frecuencia debida',1),(1309,109,'La empresa siempre cumple lo que ofrece a sus clientes',1),(1401,110,'Las politicas y procedimientos de la empresa son claros y consistentes',1),(1402,110,'Los planes y metas de crecimiento de la empresa son realistas',1),(1403,110,'Nuestra compania es una empresa bien organizada',1),(1404,110,'Las cargas de trabajo en mi area se distribuyen de manera justa',1),(1405,110,'En esta empresa todo se planea con anticipacion',1),(1406,110,'Nunca trabajo mas horas de lo necesario por culpa de otros',1),(1407,110,'En esta compania no se pierde tiempo esperando para hacer tramites',1),(1408,110,'Me gusta mucho el trabajo que hago en esta empresa',1),(1409,110,'Mi area de trabajo es considerada parte importante de esta empresa',1),(1501,111,'En mi trabajo tengo oportunidad de dedicarme a lo que se hacer mejor',1),(1502,111,'La compania reconoce la importancia del trabajo que yo desempeno',1),(1503,111,'Estoy satisfecho con la participacion que tengo en las decisiones que influyen en mi trabajo',1),(1504,111,'Tengo libertad para tomar decisiones en mi trabajo',1),(1505,111,'Estoy contento con mi horario de trabajo',1),(1506,111,'El personal de mi area de trabajo es altamente apreciado dentro de la empresa',1),(1507,111,'El trabajo que realizo casi nunca me aburre o me cansa',1),(1508,111,'El trabajo en la compania me permite dedicar tiempo suficiente a mi familia y  mis  intereses personales',1),(1601,112,'En esta empresa, todos estamos comprometidos en mejorar la calidad de nuestro trabajo',1),(1602,112,'Mis companeros de trabajo estan comprometidos en ser productivos',1),(1603,112,'Mis companeros de trabajo estan comprometidos a realizar su trabajo con calidad',1),(1604,112,'La empresa hace esfuerzos constantes para continuar mejorando la calidad de nuestros servicios',1),(1605,112,'Nuestras politicas y sistemas de trabajo nos ayudan a dar un servicio de calidad',1),(1606,112,'La compania invierte tiempo y esfuerzo en prevenir problemas',1),(20101,201,'Conocimiento del puesto',1),(20201,202,'Calidad del trabajo',1),(20301,203,'Efectividad en el trabajo',1),(20401,204,'Organización',1),(20501,205,'Control de Actividades',1),(20601,206,'Capacidad de Aprendizaje',1),(20701,207,'Cooperación',1),(20801,208,'Iniciativa',1),(20901,209,'Puntualidad',1),(21001,210,'Asistencia',1),(22101,221,'Adaptacion al medio',1),(22201,222,'Energía e iniciativa',1),(22301,223,'Tolerancia al stress',1),(22401,224,'Orientación al cliente',1),(22501,225,'Trabajo en equipo y cooperación',1),(22601,226,'Análisis y sentido común',1),(22701,227,'Planificación y organización',1),(22801,228,'Creatividad',1),(22901,229,'Comunicación efectiva',1),(23001,230,'Empatía',1),(24101,241,'Control',1),(24201,242,'Liderazgo',1),(24301,243,'Influencia e impacto',1),(24401,244,'Delegación',1),(24501,245,'Sensatez para los negocios',1),(24601,246,'Orientación al logro',1),(24701,247,'Toma de riesgos',1),(24801,248,'Negociación',1),(24901,249,'Persuasión',1),(25001,250,'Perspectiva estratégica',1),(25101,251,'Deseos de éxito',1),(25201,252,'Gestión de recursos',1),(25301,253,'Red de relaciones efectivas',1),(30101,301,'Efectividad con que la persona utiliza sus conocimientos para resolver problemas pertinentes al puesto que desempeña.',1),(30201,302,'Efectividad con que la persona utiliza sus conocimientos para proponer nuevos esquemas de trabajo',1),(30301,303,'Manejo del concepto de Calidad con que se desempeña la persona',1),(30401,304,'Efectividad para desarrollaar sus tareas o asignaciones',1),(30501,305,'Apego de su trabajo a los procedimientos establecidos ',1),(30601,306,'Controla el desarrollo de sus actividades',1);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta_cuestionario_empresa`
--

DROP TABLE IF EXISTS `pregunta_cuestionario_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pregunta_cuestionario_empresa` (
  `id_pregunta_cuestionario_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuestionario_empresa` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  PRIMARY KEY (`id_pregunta_cuestionario_empresa`),
  KEY `FK_PREGUNTA_CUESTIONARIO_EMPRESA` (`id_cuestionario_empresa`),
  KEY `FK_PREGUNTA_PREGUNTA_TEMA` (`id_pregunta`),
  CONSTRAINT `FK_PREGUNTA_CUESTIONARIO_EMPRESA` FOREIGN KEY (`id_cuestionario_empresa`) REFERENCES `cuestionario_empresa` (`id_cuestionario_empresa`),
  CONSTRAINT `FK_PREGUNTA_PREGUNTA_TEMA` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta_tema` (`id_pregunta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta_cuestionario_empresa`
--

LOCK TABLES `pregunta_cuestionario_empresa` WRITE;
/*!40000 ALTER TABLE `pregunta_cuestionario_empresa` DISABLE KEYS */;
INSERT INTO `pregunta_cuestionario_empresa` VALUES (1,8,1),(2,8,2);
/*!40000 ALTER TABLE `pregunta_cuestionario_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta_tema`
--

DROP TABLE IF EXISTS `pregunta_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pregunta_tema` (
  `id_pregunta` int(11) NOT NULL AUTO_INCREMENT,
  `id_tema` int(11) NOT NULL,
  `enunciado` varchar(180) NOT NULL,
  `tipo` int(1) NOT NULL,
  `de_plantilla` int(1) NOT NULL,
  `documento_referencia` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`),
  KEY `FK_TEMA_PREGUNTA` (`id_tema`),
  CONSTRAINT `FK_TEMA_PREGUNTA` FOREIGN KEY (`id_tema`) REFERENCES `tema_cuestionario` (`id_tema`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta_tema`
--

LOCK TABLES `pregunta_tema` WRITE;
/*!40000 ALTER TABLE `pregunta_tema` DISABLE KEYS */;
INSERT INTO `pregunta_tema` VALUES (1,1,'¿Existe un diagrama en la organizacion?',1,1,'documento'),(2,1,'¿El organigrama esta actualizado y responde a la operacion real de la empresa',1,1,NULL),(3,2,'¿Se cuenta con un proceso documentado y estandarizado de RyS?',1,1,NULL);
/*!40000 ALTER TABLE `pregunta_tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta_tema_historico`
--

DROP TABLE IF EXISTS `pregunta_tema_historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pregunta_tema_historico` (
  `id_pregunta` int(11) NOT NULL AUTO_INCREMENT,
  `id_tema` int(11) NOT NULL,
  `enunciado` varchar(180) NOT NULL,
  `tipo` int(1) NOT NULL,
  `de_plantilla` int(1) NOT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `ponderacion` varchar(10) NOT NULL,
  `accion` varchar(10) NOT NULL,
  `documento_referencia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`),
  KEY `FK_PREGUNTA_HIST` (`id_pregunta`),
  KEY `FK79p5rimayhlrj7sicu9v9p7ta` (`id_tema`),
  CONSTRAINT `FK79p5rimayhlrj7sicu9v9p7ta` FOREIGN KEY (`id_tema`) REFERENCES `tema_cuestionario` (`id_tema`),
  CONSTRAINT `FK_PREGUNTA_HIST` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta_tema` (`id_pregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta_tema_historico`
--

LOCK TABLES `pregunta_tema_historico` WRITE;
/*!40000 ALTER TABLE `pregunta_tema_historico` DISABLE KEYS */;
/*!40000 ALTER TABLE `pregunta_tema_historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto` (
  `id_proyecto` int(11) NOT NULL AUTO_INCREMENT,
  `id_modulo` int(11) NOT NULL,
  `nombre` varchar(90) DEFAULT NULL,
  `id_empresa` bigint(20) NOT NULL,
  `num_empleados` int(11) NOT NULL,
  `sedes` varchar(180) DEFAULT NULL,
  `num_participantes` int(11) NOT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `dias_garantia` int(11) DEFAULT NULL,
  `frecuencia_eval` decimal(10,0) DEFAULT NULL,
  `nombre_rh` varchar(180) DEFAULT NULL,
  `email_rh` varchar(90) DEFAULT NULL,
  `comentario_grap` varchar(360) DEFAULT NULL,
  `id_contacto` bigint(20) NOT NULL,
  `id_estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_proyecto`),
  KEY `FK_PROYECTO_EMPRESA_idx` (`id_empresa`),
  KEY `FK_PROY_CONTACTO_idx` (`id_contacto`),
  KEY `FK_PROYECTO_MODULO_idx` (`id_modulo`),
  KEY `FK_PROY_ID_ESTATIUS_idx` (`id_estatus`),
  CONSTRAINT `FK_CONTACTO_PROY` FOREIGN KEY (`id_contacto`) REFERENCES `contacto` (`id`),
  CONSTRAINT `FK_PROYECTO_EMPRESA` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`),
  CONSTRAINT `FK_PROYECTO_MODULO` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`),
  CONSTRAINT `FK_PROY_ID_ESTATIUS` FOREIGN KEY (`id_estatus`) REFERENCES `estatus_proyecto` (`id_estatus`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
INSERT INTO `proyecto` VALUES (1,1,'CLIMA LABOLAL KKANA',1,1,'AQUI',1,'2018-10-10 00:00:00','2018-11-22 00:00:00',3,NULL,NULL,NULL,NULL,1,1),(2,1,'CLIMA LABORAL PTITO',2,1000,'PTITO',50,NULL,NULL,NULL,3,NULL,NULL,NULL,2,1),(3,1,'CLIMA LABORAL DE CLIMA AUTOMOTRIZ S.A.',3,14,'OFICINA CENTRAL Y PLANTA',13,'2018-10-15 00:00:00','2019-08-09 00:00:00',30,3,NULL,NULL,NULL,3,1),(4,1,'CLIMA LABORAL TECNOLOGOP',4,14,'OFICINA CENTRAL Y PLANTA',13,'2018-10-15 17:27:41','2018-11-15 00:00:00',10,1,NULL,NULL,NULL,4,1),(5,1,'CLIMA LABORAL MAGALLANES HERMANOS S.A.',31,238,'LAS 5 SEDES',237,NULL,NULL,NULL,2,NULL,NULL,NULL,5,7),(6,4,'Prueba1',32,10,'Prueba1',10,NULL,NULL,NULL,1,NULL,NULL,NULL,6,4),(7,4,'Prueba2',33,10,'Prueba2',10,NULL,NULL,NULL,1,NULL,NULL,NULL,7,4),(8,3,'asdasd',34,10,'santa',1,'2018-12-01 00:00:00','2018-12-11 00:00:00',10,1,NULL,NULL,NULL,8,4),(9,2,'xzcz',35,22,'sdas',2,NULL,NULL,NULL,2,NULL,NULL,NULL,9,1),(10,3,'wesda',36,22,'sadaSA',23,'2018-12-03 00:00:00','2018-12-12 00:00:00',19,1,NULL,NULL,NULL,10,4),(11,4,'jop 1',37,100,'gfsf',1,NULL,NULL,NULL,1,NULL,NULL,NULL,11,4);
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto_pvc_area`
--

DROP TABLE IF EXISTS `proyecto_pvc_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto_pvc_area` (
  `id` int(11) NOT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto_pvc_area`
--

LOCK TABLES `proyecto_pvc_area` WRITE;
/*!40000 ALTER TABLE `proyecto_pvc_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto_pvc_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto_pvc_conocimiento`
--

DROP TABLE IF EXISTS `proyecto_pvc_conocimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto_pvc_conocimiento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `especialidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbxgmbp8b1n8mbf6t0r3v77hsh` (`especialidad`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto_pvc_conocimiento`
--

LOCK TABLES `proyecto_pvc_conocimiento` WRITE;
/*!40000 ALTER TABLE `proyecto_pvc_conocimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto_pvc_conocimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto_pvc_esfera`
--

DROP TABLE IF EXISTS `proyecto_pvc_esfera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto_pvc_esfera` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `area` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1cq3xhah35eftdg85a4ny9u4` (`area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto_pvc_esfera`
--

LOCK TABLES `proyecto_pvc_esfera` WRITE;
/*!40000 ALTER TABLE `proyecto_pvc_esfera` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto_pvc_esfera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto_pvc_especialidad`
--

DROP TABLE IF EXISTS `proyecto_pvc_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto_pvc_especialidad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `nivel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl4e9gp87v8ili7l9mlyruylm7` (`nivel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto_pvc_especialidad`
--

LOCK TABLES `proyecto_pvc_especialidad` WRITE;
/*!40000 ALTER TABLE `proyecto_pvc_especialidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto_pvc_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto_pvc_nivel`
--

DROP TABLE IF EXISTS `proyecto_pvc_nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto_pvc_nivel` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `esfera` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8nk1s4daehf4cfc5ahinjfyvs` (`esfera`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto_pvc_nivel`
--

LOCK TABLES `proyecto_pvc_nivel` WRITE;
/*!40000 ALTER TABLE `proyecto_pvc_nivel` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto_pvc_nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `psicometrico_asignacion_vacante`
--

DROP TABLE IF EXISTS `psicometrico_asignacion_vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `psicometrico_asignacion_vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observaciones_cliente` varchar(255) DEFAULT NULL,
  `id_asignacion_consultor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK35oxi2nrsacqvypgpixpo5lxd` (`id_asignacion_consultor`),
  CONSTRAINT `FK35oxi2nrsacqvypgpixpo5lxd` FOREIGN KEY (`id_asignacion_consultor`) REFERENCES `asignacion_consultor_vacante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psicometrico_asignacion_vacante`
--

LOCK TABLES `psicometrico_asignacion_vacante` WRITE;
/*!40000 ALTER TABLE `psicometrico_asignacion_vacante` DISABLE KEYS */;
/*!40000 ALTER TABLE `psicometrico_asignacion_vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta_pregunta`
--

DROP TABLE IF EXISTS `respuesta_pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `respuesta_pregunta` (
  `id_respuesta_pregunta` int(11) NOT NULL AUTO_INCREMENT,
  `id_participante` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  `respuesta_numero` int(1) DEFAULT NULL,
  `respuesta_texto` varchar(180) DEFAULT NULL,
  `esta_contestada` int(1) NOT NULL,
  `disc_table` varchar(1) DEFAULT NULL,
  `id_cuestionario_empresa` int(11) NOT NULL,
  PRIMARY KEY (`id_respuesta_pregunta`),
  UNIQUE KEY `UK_7a1y2wepr2i9gw6akvah7efsp` (`id_cuestionario_empresa`),
  KEY `FK_CUESTIONARIO_EMPRESA` (`id_cuestionario_empresa`),
  CONSTRAINT `FK_CUESTIONARIO_EMPRESA` FOREIGN KEY (`id_cuestionario_empresa`) REFERENCES `cuestionario_empresa` (`id_cuestionario_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta_pregunta`
--

LOCK TABLES `respuesta_pregunta` WRITE;
/*!40000 ALTER TABLE `respuesta_pregunta` DISABLE KEYS */;
INSERT INTO `respuesta_pregunta` VALUES (1,1,1,NULL,'si',1,'1',8);
/*!40000 ALTER TABLE `respuesta_pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta_pregunta_irh`
--

DROP TABLE IF EXISTS `respuesta_pregunta_irh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `respuesta_pregunta_irh` (
  `id_respuesta_pregunta` int(11) NOT NULL,
  `cumplimiento` varchar(180) DEFAULT NULL,
  `vigencia` varchar(500) DEFAULT NULL,
  `estado` varchar(500) DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `inversion_aproximada` double DEFAULT NULL,
  PRIMARY KEY (`id_respuesta_pregunta`),
  KEY `FK_RESPUESTA_PREGUNTA` (`id_respuesta_pregunta`),
  CONSTRAINT `FK_RESPUESTA_PREGUNTA` FOREIGN KEY (`id_respuesta_pregunta`) REFERENCES `respuesta_pregunta` (`id_respuesta_pregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta_pregunta_irh`
--

LOCK TABLES `respuesta_pregunta_irh` WRITE;
/*!40000 ALTER TABLE `respuesta_pregunta_irh` DISABLE KEYS */;
INSERT INTO `respuesta_pregunta_irh` VALUES (1,'SI','VIGENTE','EN DESARROLLO','NINGUNA',77500);
/*!40000 ALTER TABLE `respuesta_pregunta_irh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_clb_submodulo`
--

DROP TABLE IF EXISTS `rol_clb_submodulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rol_clb_submodulo` (
  `rol` int(11) NOT NULL,
  `id_submodulo` int(11) NOT NULL,
  PRIMARY KEY (`rol`,`id_submodulo`),
  KEY `fk_submodulo_idx` (`id_submodulo`),
  CONSTRAINT `fk_submodulo` FOREIGN KEY (`id_submodulo`) REFERENCES `clb_submodulo` (`id_submodulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_clb_submodulo`
--

LOCK TABLES `rol_clb_submodulo` WRITE;
/*!40000 ALTER TABLE `rol_clb_submodulo` DISABLE KEYS */;
INSERT INTO `rol_clb_submodulo` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(2,3),(2,4),(3,5),(3,6),(3,7),(3,8),(3,9);
/*!40000 ALTER TABLE `rol_clb_submodulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema`
--

DROP TABLE IF EXISTS `tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tema` (
  `id_tema` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(190) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_tema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` VALUES (101,'Imagen','Tema 01 de Clima Laboral',NULL),(102,'Espacio de trabajo','Tema 02 de Clima Laboral',NULL),(103,'Relaciones','Tema 03 de Clima Laboral',NULL),(104,'Comunicación','Tema 04 de Clima Laboral',NULL),(105,'Misión','Tema 05 de Clima Laboral',NULL),(106,'Trabajo en equipo','Tema 06 de Clima Laboral',NULL),(107,'Capacitación y desarrollo','Tema 07 de Clima Laboral',NULL),(108,'Reconocimiento','Tema 08 de Clima Laboral',NULL),(109,'Servicio al cliente','Tema 09 de Clima Laboral',NULL),(110,'Organización','Tema 10 de Clima Laboral',NULL),(111,'Condiciones de trabajo','Tema 11 de Clima Laboral',NULL),(112,'Calidad','Tema 12 de Clima Laboral',NULL),(201,'Conocimiento del puesto','Factor de Desempeño 01 de EVD','FA'),(202,'Calidad del trabajo','Factor de Desempeño 02 de EVD','FA'),(203,'Efectividad en el trabajo','Factor de Desempeño 03 de EVD','FA'),(204,'Organización','Factor de Desempeño 04 de EVD','FA'),(205,'Control de Actividades','Factor de Desempeño 05 de EVD','FA'),(206,'Capacidad de Aprendizaje','Factor de Desempeño 06 de EVD','FA'),(207,'Cooperación','Factor de Desempeño 07 de EVD','FA'),(208,'Iniciativa','Factor de Desempeño 08 de EVD','FA'),(209,'Puntualidad','Factor de Desempeño 09 de EVD','FA'),(210,'Asistencia','Factor de Desempeño 10 de EVD','FA'),(221,'Adaptacion al medio','Competencia 01 de EVD','CA'),(222,'Energía e iniciativa','Competencia 02 de EVD','CA'),(223,'Tolerancia al stress','Competencia 03 de EVD','CA'),(224,'Orientación al cliente','Competencia 04 de EVD','CA'),(225,'Trabajo en equipo y cooperación','Competencia 05 de EVD','CA'),(226,'Análisis y sentido común','Competencia 06 de EVD','CA'),(227,'Planificación y organización','Competencia 07 de EVD','CA'),(228,'Creatividad','Competencia 08 de EVD','CA'),(229,'Comunicación efectiva','Competencia 09 de EVD','CA'),(230,'Empatía','Competencia 10 de EVD','CA'),(241,'Control','Competencia 11 de EVD','CB'),(242,'Liderazgo','Competencia 12 de EVD','CB'),(243,'Influencia e impacto','Competencia 13 de EVD','CB'),(244,'Delegación','Competencia 14 de EVD','CB'),(245,'Sensatez para los negocios','Competencia 15 de EVD','CB'),(246,'Orientación al logro','Competencia 16 de EVD','CB'),(247,'Toma de riesgos','Competencia 17 de EVD','CB'),(248,'Negociación','Competencia 18 de EVD','CB'),(249,'Persuasión','Competencia 19 de EVD','CB'),(250,'Perspectiva estratégica','Competencia 20 de EVD','CB'),(251,'Deseos de éxito','Competencia 21 de EVD','CB'),(252,'Gestión de recursos','Competencia 22 de EVD','CB'),(253,'Red de relaciones efectivas','Competencia 23 de EVD','CB'),(301,'Conocimientos Técnicos inciso A','Categoría 01-A de EVD360',NULL),(302,'Conocimientos Técnicos inciso B','Categoría 01-B de EVD360',NULL),(303,'Calidad en el trabajo inciso A','Categoría 02-A de EVD360',NULL),(304,'Calidad en el trabajo inciso B','Categoría 02-B de EVD360',NULL),(305,'Organización inciso A','Categoría 03-A de EVD360',NULL),(306,'Organización inciso B','Categoría 03-B de EVD360',NULL);
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema_cuestionario`
--

DROP TABLE IF EXISTS `tema_cuestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tema_cuestionario` (
  `id_tema` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`id_tema`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema_cuestionario`
--

LOCK TABLES `tema_cuestionario` WRITE;
/*!40000 ALTER TABLE `tema_cuestionario` DISABLE KEYS */;
INSERT INTO `tema_cuestionario` VALUES (1,'Reclutamiento y Seleccion','Tema de Reclutamiento y Seleccion'),(2,'Administracion de recursos Humanos','Tema de Administracion de recursos Humanos'),(3,'Desarrollo organizacional','Tema de Desarrollo organizacional'),(5,'capacitacion',NULL),(6,'capacitacion1',NULL),(7,'capacitacion1',NULL),(8,'capacitacion1',NULL),(9,'capacitacion1',NULL),(10,'capacitacion1',NULL),(11,'capacitacion1',NULL);
/*!40000 ALTER TABLE `tema_cuestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_catalogo`
--

DROP TABLE IF EXISTS `tipo_catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_catalogo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` int(11) NOT NULL,
  `nombre_tipo_catalogo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_catalogo`
--

LOCK TABLES `tipo_catalogo` WRITE;
/*!40000 ALTER TABLE `tipo_catalogo` DISABLE KEYS */;
INSERT INTO `tipo_catalogo` VALUES (1,1,'Pais'),(2,1,'Giro'),(3,1,'Tipo Contacto'),(4,1,'Idioma'),(5,1,'Nivel Idioma'),(6,1,'Nivel Conocimiento'),(7,1,'Tipo Contacto Empresa'),(8,1,'Nivel actividad'),(9,1,'Competencia'),(10,1,'Sexo'),(11,1,'Estado civil'),(12,1,'Tipo de pregunta'),(13,1,'Nacionalidad Empleado'),(14,1,'Puesto Empleado'),(15,1,'Tipo Contacto Empleado'),(16,1,'Banco Empleado'),(17,1,'Nivel de escolaridad Empleado'),(18,1,'Habilidades Empleado'),(19,1,'Nivel Laboral Empleado'),(20,1,'Crédito hipotecario'),(21,1,'Prestaciones'),(22,1,'Tipo de contrato'),(23,1,'Motivo de vacante'),(24,1,'Frecuencia de evaluacion');
/*!40000 ALTER TABLE `tipo_catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(150) NOT NULL,
  `rol` int(11) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`),
  UNIQUE KEY `UK_863n1y3x0jalatoir4325ehal` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPxjRIYT8pG0zgzKTilbko-MOv8pSnmO63M9FkOvfHoR9FvInm','consultorventas@nirho.com','CONSULTOR VENTAS','17f82ee224bb3238db809ed4a7a006f0',1,'','consultorventas'),(2,'https://imgur.com/I80W1Q0.png','gerentenirho@nirho.com','GERENTE NIRHO','a16068c1b716a5dfd54b9e59b7bbc95f',2,'','gerentenirho'),(3,'https://raw.githubusercontent.com/fangpenlin/avataaars/HEAD/avataaars-example.png?raw=true','candidato@nirho.com','CANDIDATO','5f4dcc3b5aa765d61d8327deb882cf99',0,'','candidato'),(4,'http://74.208.131.50:80/assets/Avatars/p1.png','consultornirho@nirho.com','CONSULTOR NIRHO','6ba3c78f6ef92b87020886f1476a017d',3,'','consultornirho'),(5,'http://www.hotellaginestra.it/wp-content/uploads/2016/06/person-flat.png','empresanirho@nirho.com','EMPRESA NIRHO','5f4dcc3b5aa765d61d8327deb882cf99',3,'','empresanirho'),(6,'http://www.hotellaginestra.it/wp-content/uploads/2016/06/person-flat.png','gael2808@hotmail.com','DANILO GARCIA REYES','5f4dcc3b5aa765d61d8327deb882cf99',1,NULL,'GARD8908281E8'),(7,'http://www.hotellaginestra.it/wp-content/uploads/2016/06/person-flat.png','rrensoli@hotmail.com','rene ','5f4dcc3b5aa765d61d8327deb882cf99',3,NULL,'recr581026sm7'),(8,'http://www.hotellaginestra.it/wp-content/uploads/2016/06/person-flat.png','jonatan.gallegos@nirho.com','JONATAN GALLEGOS GUTIERREZ','5f4dcc3b5aa765d61d8327deb882cf99',3,NULL,'GAGA9402198H8'),(9,'http://www.hotellaginestra.it/wp-content/uploads/2016/06/person-flat.png','alejandro.barradas@nirho.com','ALEJANDRO BARRADAS PEREZ','5f4dcc3b5aa765d61d8327deb882cf99',3,NULL,'BAPA890207P96'),(10,'http://www.hotellaginestra.it/wp-content/uploads/2016/06/person-flat.png','ventasgdl@nirho.com','MARIA DE LOURDES VALDEZ RODRIGUEZ','5f4dcc3b5aa765d61d8327deb882cf99',1,NULL,'VARL600331VA4');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacante`
--

DROP TABLE IF EXISTS `vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vacante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anios_experiencia` tinyblob NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `estado_vacante` int(11) NOT NULL,
  `giro` int(11) NOT NULL,
  `motivo` int(11) NOT NULL,
  `nombre_vacante` varchar(150) NOT NULL,
  `num_vacantes` int(11) NOT NULL,
  `puesto` varchar(255) NOT NULL,
  `puesto_cargo` varchar(255) NOT NULL,
  `puesto_reporta` varchar(255) NOT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpvabgahouvvyq6s60b00bypki` (`id_empresa`),
  KEY `FKg3eq5kb5ko0sy905l7uhqkr9h` (`id_usuario`),
  CONSTRAINT `FKg3eq5kb5ko0sy905l7uhqkr9h` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKpvabgahouvvyq6s60b00bypki` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacante`
--

LOCK TABLES `vacante` WRITE;
/*!40000 ALTER TABLE `vacante` DISABLE KEYS */;
INSERT INTO `vacante` VALUES (1,_binary '͜ur\0[IM۠&v겥\0\0xp\0\0\0\0\0\0	\0\0\0','La Martine 280',4,4,46,'Gerente de Diseño',1,'Gerente \"A\"','No aplica','Director General',1,1),(2,_binary '͜ur\0[IM۠&v겥\0\0xp\0\0\0\0\0\0\0\0\0\n','No Aplica',1,6,49,'ASESORA DE MODA',4,'ASESORA DE MODA','NINGUNO','SUPERVISORA DE TIENDA O GERENTE',3,10);
/*!40000 ALTER TABLE `vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vw_cuestionario_preguntas_temas`
--

DROP TABLE IF EXISTS `vw_cuestionario_preguntas_temas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vw_cuestionario_preguntas_temas` (
  `id_pregunta` int(11) NOT NULL,
  `de_plantilla` int(11) DEFAULT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `documento_referencia` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enunciado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_tema` int(11) DEFAULT NULL,
  `nombre_tema` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vw_cuestionario_preguntas_temas`
--

LOCK TABLES `vw_cuestionario_preguntas_temas` WRITE;
/*!40000 ALTER TABLE `vw_cuestionario_preguntas_temas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vw_cuestionario_preguntas_temas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vw_cuestionario_respuestas`
--

DROP TABLE IF EXISTS `vw_cuestionario_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vw_cuestionario_respuestas` (
  `id_respuesta_pregunta` int(11) NOT NULL,
  `cumplimiento` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `de_plantilla` int(11) DEFAULT NULL,
  `documento_referencia` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enunciado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `estado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_participante` int(11) DEFAULT NULL,
  `id_pregunta` int(11) DEFAULT NULL,
  `inversion_aproximada` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `observaciones` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `vigencia` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_respuesta_pregunta`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vw_cuestionario_respuestas`
--

LOCK TABLES `vw_cuestionario_respuestas` WRITE;
/*!40000 ALTER TABLE `vw_cuestionario_respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vw_cuestionario_respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vw_empresas_solicitud_cuestionario`
--

DROP TABLE IF EXISTS `vw_empresas_solicitud_cuestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vw_empresas_solicitud_cuestionario` (
  `id_cuestionario_empresa` int(11) NOT NULL,
  `finalizado` int(11) DEFAULT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_tema` int(11) DEFAULT NULL,
  `nombre_empresa` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre_tema` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rfc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_cuestionario_empresa`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vw_empresas_solicitud_cuestionario`
--

LOCK TABLES `vw_empresas_solicitud_cuestionario` WRITE;
/*!40000 ALTER TABLE `vw_empresas_solicitud_cuestionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `vw_empresas_solicitud_cuestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vw_tema_cuestionario`
--

DROP TABLE IF EXISTS `vw_tema_cuestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vw_tema_cuestionario` (
  `id_tema` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_tema`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vw_tema_cuestionario`
--

LOCK TABLES `vw_tema_cuestionario` WRITE;
/*!40000 ALTER TABLE `vw_tema_cuestionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `vw_tema_cuestionario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-22  0:54:06
