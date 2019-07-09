/*
SQLyog Ultimate v8.55 
MySQL - 5.0.27-community-nt : Database - srd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`srd` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `srd`;

/*Table structure for table `languages` */

DROP TABLE IF EXISTS `languages`;

CREATE TABLE `languages` (
  `languages_id` int(11) NOT NULL auto_increment,
  `name` varchar(45) default NULL,
  `number` double default NULL,
  `repository_id` int(11) NOT NULL,
  PRIMARY KEY  (`languages_id`),
  KEY `fk_languages_repository1_idx` (`repository_id`),
  CONSTRAINT `fk_languages_repository1` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`repository_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `languages` */

insert  into `languages`(`languages_id`,`name`,`number`,`repository_id`) values (1,'Java',36599,1),(2,'CSS',2862067,2),(3,'JavaScript',2509841,2),(4,'Java',2394531,2),(5,'C#',237176,2),(6,'HTML',206289,2),(7,'PHP',147622,2),(8,'Pascal',133111,2),(9,'PowerShell',101099,2),(10,'D',35778,2),(11,'M',16415,2),(12,'Puppet',972,2),(13,'ASP',333,2),(14,'Java',35490,3),(15,'Java',11858,4),(16,'Java',40162,5),(17,'JavaScript',559,5),(18,'CSS',71,5),(19,'JavaScript',20392,6),(20,'Java',5696,6),(21,'HTML',3343,6),(22,'CSS',268,6),(23,'Java',4502,7),(24,'Java',2757,8),(25,'Java',10853,9),(26,'Java',7648,10),(27,'Java',4044,11);

/*Table structure for table `repository` */

DROP TABLE IF EXISTS `repository`;

CREATE TABLE `repository` (
  `repository_id` int(11) NOT NULL auto_increment,
  `name` varchar(45) default NULL,
  `url` varchar(100) default NULL,
  `description` varchar(250) default NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`repository_id`),
  KEY `fk_repository_user_idx` (`user_id`),
  CONSTRAINT `fk_repository_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `repository` */

insert  into `repository`(`repository_id`,`name`,`url`,`description`,`user_id`) values (1,'demo','https://api.github.com/repos/arslanyasinwattoo/demo','Java Spring mvc ,maven ,git, Rest api, github api ,hibernate,junit ,mysql ',1),(2,'Projects','https://api.github.com/repos/arslanyasinwattoo/Projects','Beta versions/student projects ',1),(3,'travisTesting','https://api.github.com/repos/arslanyasinwattoo/travisTesting',NULL,1),(4,'spring-embedded-database','https://api.github.com/repos/mkyong/spring-embedded-database','Spring Embedded Database Examples',2),(5,'spring-mvc-form-handling','https://api.github.com/repos/mkyong/spring-mvc-form-handling','Spring MVC Form Handling Example',2),(6,'spring-mvc-mustache-js-template','https://api.github.com/repos/mkyong/spring-mvc-mustache-js-template','Spring MVC + Mustache example',2),(7,'spring3-mvc-maven-annotation-hello-world','https://api.github.com/repos/mkyong/spring3-mvc-maven-annotation-hello-world','Maven + Spring 3 MVC hello world example (Annotation)',2),(8,'spring3-mvc-maven-xml-hello-world','https://api.github.com/repos/mkyong/spring3-mvc-maven-xml-hello-world','Maven + Spring 3 MVC hello world example (XML)',2),(9,'spring4-mvc-ajax-example','https://api.github.com/repos/mkyong/spring4-mvc-ajax-example','Spring 4 MVC Ajax Example',2),(10,'spring4-mvc-gradle-annotation-hello-world','https://api.github.com/repos/mkyong/spring4-mvc-gradle-annotation-hello-world','Gradle - Spring 4 MVC hello world example (Annotation)',2),(11,'spring4-mvc-gradle-xml-hello-world','https://api.github.com/repos/mkyong/spring4-mvc-gradle-xml-hello-world','Gradle + Spring 4 MVC hello world example (XML)',2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `name` varchar(45) default NULL,
  `location` varchar(45) default NULL,
  `company` varchar(45) default NULL,
  `email` varchar(45) default NULL,
  `bio` varchar(250) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`name`,`location`,`company`,`email`,`bio`) values (1,'arslanyasinwattoo','Arslan yasin Wattoo','germany',NULL,NULL,'i am a masters student currently studying Distributed Software systems from Tu Darmstadt Germany '),(2,'mkyong','Yong Mook Kim',NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
