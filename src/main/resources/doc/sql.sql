-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 demo 的数据库结构
CREATE DATABASE IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `demo`;

-- 导出  表 demo.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userno` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='test';

-- 正在导出表  demo.user 的数据：4 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `userno`, `name`, `age`, `sex`) VALUES
	(1, 100001, 'zhangsan', 18, 0),
	(2, 100002, 'lisi', 20, 1),
	(3, 100003, 'wangwu', 16, 0),
	(4, 100004, 'zhaoliu', 19, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 demo.userinfo 结构
CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` bigint(20) NOT NULL,
  `cours` varchar(50) DEFAULT NULL,
  `userno` bigint(20) NOT NULL DEFAULT '0',
  `score` double DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 正在导出表  demo.userinfo 的数据：12 rows
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`, `cours`, `userno`, `score`) VALUES
	(1, '数学', 100001, 65),
	(2, '语文', 100001, 80),
	(3, '英语', 100001, 75),
	(4, '数学', 100002, 75),
	(5, '语文', 100002, 82),
	(6, '英语', 100002, 73),
	(7, '数学', 100003, 70),
	(8, '语文', 100003, 85),
	(9, '英语', 100003, 66),
	(10, '数学', 100004, 62),
	(11, '语文', 100004, 100),
	(12, '英语', 100004, 69);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
