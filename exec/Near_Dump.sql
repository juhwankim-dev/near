# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.6.4-MariaDB)
# Database: neardb
# Generation Time: 2022-02-17 07:22:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table bookmark
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bookmark`;

CREATE TABLE `bookmark` (
  `bookmark_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `handcontent_key` bigint(20) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookmark_key`),
  KEY `FKcstlm7gb9sadcki4le6y382aw` (`handcontent_key`),
  KEY `FKm0i0nn7a1tn7pa2m4j1ma74ui` (`id`),
  CONSTRAINT `FKcstlm7gb9sadcki4le6y382aw` FOREIGN KEY (`handcontent_key`) REFERENCES `handcontent` (`handcontent_key`),
  CONSTRAINT `FKm0i0nn7a1tn7pa2m4j1ma74ui` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table fingercontent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fingercontent`;

CREATE TABLE `fingercontent` (
  `fingercotent_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` int(11) NOT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  `name` varchar(2) DEFAULT NULL,
  `register_time` datetime NOT NULL,
  PRIMARY KEY (`fingercotent_key`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `fingercontent` WRITE;
/*!40000 ALTER TABLE `fingercontent` DISABLE KEYS */;

INSERT INTO `fingercontent` (`fingercotent_key`, `category`, `explanation`, `image_path`, `name`, `register_time`)
VALUES
	(51,0,'\"오른 주먹의 1·5지를 펴서 1지 끝이 아래로 손등이 밖으로 향하게 세운다.\"','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_001.jpg','ㄱ','2022-02-17 14:09:29'),
	(52,0,'오른 주먹의 1·5지를 펴서 1지 끝이 아래로, 손등이 밖으로 향하게 세워 오른쪽으로 한 번 옮긴다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_002.jpg','ㄲ','2022-02-17 14:09:29'),
	(53,0,'오른 주먹의 1·5지를 펴서 1지 끝이 왼쪽으로 손등이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_003.jpg','ㄴ','2022-02-17 14:09:29'),
	(54,0,'오른 주먹의 1·2지를 펴서 벌려 끝이 왼쪽으로 손등이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_004.jpg','ㄷ','2022-02-17 14:09:29'),
	(55,0,'오른 주먹의 1·2지를 펴서 손끝이 왼쪽으로, 손등이 밖으로 향하게 하여 오른쪽으로 한 번 옮긴다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_005.jpg','ㄸ','2022-02-17 14:09:29'),
	(56,0,'오른 주먹의 1·2·3지를 펴서 벌려 손끝이 왼쪽으로 손등이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_006.jpg','ㄹ','2022-02-17 14:09:29'),
	(57,0,'손등이 안으로 향하게 세워 쥔 오른 주먹의 1·2지를 구부린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_007.jpg','ㅁ','2022-02-17 14:09:29'),
	(58,0,'오른 주먹의 1·2·3·4지를 펴서 붙여 손바닥이 밖으로 손끝이 위로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_008.jpg','ㅂ','2022-02-17 14:09:29'),
	(59,0,'오른 주먹의 1·2·3·4지를 펴서 붙여 손바닥이 밖으로, 손끝이 위로 향하게 세워 오른쪽으로 한 번 옮긴다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_009.jpg','ㅃ','2022-02-17 14:09:29'),
	(60,0,'오른 주먹의 1·2지를 펴서 벌려 등이 밖으로 끝이 아래로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_010.jpg','ㅅ','2022-02-17 14:09:29'),
	(61,0,'오른 주먹의 1·2지를 펴서 벌려 등이 밖으로, 끝이 아래로 향하게 하여 오른쪽으로 한 번 옮긴다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_011.jpg','ㅆ','2022-02-17 14:09:29'),
	(62,0,'손바닥이 밖으로 향하게 펴서 세운 오른손의 1·5지 끝을 맞대어 동그라미를 만들어 보인다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_012.jpg','ㅇ','2022-02-17 14:09:29'),
	(63,0,'오른 주먹의 1·2·5지를 펴서 등이 밖으로 끝이 아래로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_013.jpg','ㅈ','2022-02-17 14:09:29'),
	(64,0,'오른 주먹의 1·2·5지를 펴서 등이 밖으로, 끝이 아래로 향하게 세워 오른쪽으로 한 번 옮긴다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_014.jpg','ㅉ','2022-02-17 14:09:29'),
	(65,0,'오른 주먹의 1·2·3·5지를 펴서 등이 밖으로 끝이 아래로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_015.jpg','ㅊ','2022-02-17 14:09:29'),
	(66,0,'오른 주먹의 1지를 반쯤 굽히고 2·5지를 펴서 등이 밖으로, 끝이 아래로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_016.jpg','ㅋ','2022-02-17 14:09:29'),
	(67,0,'손등이 밖으로 향하게 쥔 오른 주먹의 1·2·3지를 펴서 끝이 왼쪽으로 향하게 하여 2·3지는 붙이고 1지는 뗀다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_017.jpg','ㅌ','2022-02-17 14:09:29'),
	(68,0,'손등이 안으로 향하게 세운 오른손의 1·2·3·4·5지를 반쯤 굽힌다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_081.jpg','ㅍ','2022-02-17 14:09:29'),
	(69,0,'오른 주먹의 5지를 펴서 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_091.jpg','ㅎ','2022-02-17 14:09:29'),
	(70,1,'오른 주먹의 1지를 펴서 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_020.jpg','ㅏ','2022-02-17 14:09:29'),
	(71,1,'오른 주먹의 1·4지를 펴서 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_021.jpg','ㅐ','2022-02-17 14:09:29'),
	(72,1,'오른 주먹의 1·2지를 펴서 떼어 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_022.jpg','ㅑ','2022-02-17 14:09:29'),
	(73,1,'오른 주먹의 1·2·4지를 펴서 1·2지를 붙이고 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_023.jpg','ㅒ','2022-02-17 14:09:29'),
	(74,1,'오른 주먹의 1지를 펴서 바닥이 왼쪽으로 끝이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_024.jpg','ㅓ','2022-02-17 14:09:29'),
	(75,1,'오른 주먹의 1·4지를 펴서 바닥이 왼쪽으로, 끝이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_025.jpg','ㅔ','2022-02-17 14:09:29'),
	(76,1,'오른 주먹의 1·2지를 펴서 약간 벌려 바닥이 왼쪽으로, 끝이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_026.jpg','ㅕ','2022-02-17 14:09:29'),
	(77,1,'오른 주먹의 1·2·4지를 펴서 1·2지를 약간 벌려 바닥이 왼쪽으로, 끝이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_027.jpg','ㅖ','2022-02-17 14:09:29'),
	(78,1,'오른 주먹의 1지를 펴서 끝이 위로, 등이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_028.jpg','ㅗ','2022-02-17 14:09:29'),
	(79,1,'오른 주먹의 1지를 펴서 끝이 위로, 등이 밖으로 향하게 세운 다음, 오른 주먹의 1지를 펴서 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_029.jpg','ㅘ','2022-02-17 14:09:29'),
	(80,1,'오른 주먹의 1지를 펴서 끝이 위로 등이 밖으로 향하게 세운 다음, 1·4지를 펴서 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_030.jpg','ㅙ','2022-02-17 14:09:29'),
	(81,1,'오른 주먹의 1·4지를 펴서 끝이 위로, 등이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_031.jpg','ㅚ','2022-02-17 14:09:29'),
	(82,1,'오른 주먹의 1·2지를 펴서 약간 벌려 끝이 위로, 등이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_032.jpg','ㅛ','2022-02-17 14:09:29'),
	(83,1,'오른 주먹의 1지를 펴서 끝이 아래로 등이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_033.jpg','ㅜ','2022-02-17 14:09:29'),
	(84,1,'오른 주먹의 1지를 펴서 끝이 아래로 등이 밖으로 향하게 세운 다음, 1지 바닥이 왼쪽으로 끝이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_034.jpg','ㅝ','2022-02-17 14:09:29'),
	(85,1,'오른 주먹의 1지를 펴서 끝이 아래로 등이 밖으로 향하게 세운 다음, 1·4지를 펴서 바닥이 왼쪽으로 끝이 밖으로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_035.jpg','ㅞ','2022-02-17 14:09:29'),
	(86,1,'오른 주먹의 1·4지를 펴서 끝이 아래로, 등이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_036.jpg','ㅟ','2022-02-17 14:09:29'),
	(87,1,'오른 주먹의 1·2지를 펴서 약간 벌려 끝이 아래로, 등이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_037.jpg','ㅠ','2022-02-17 14:09:29'),
	(88,1,'오른 주먹의 1지를 펴서 끝이 왼쪽으로 향하게 모로 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_038.jpg','ㅡ','2022-02-17 14:09:29'),
	(89,1,'오른 주먹의 1·4지를 펴서 끝이 왼쪽으로 향하게 모로 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_039.jpg','ㅢ','2022-02-17 14:09:29'),
	(90,1,'오른 주먹의 4지를 펴서 끝이 위로 바닥이 밖으로 향하게 세운다.','https://d2o6fwxt8bq04z.cloudfront.net/image/MA_040.jpg','ㅣ','2022-02-17 14:09:29');

/*!40000 ALTER TABLE `fingercontent` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table handcontent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `handcontent`;

CREATE TABLE `handcontent` (
  `handcontent_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `explanation` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `mean` varchar(255) DEFAULT NULL,
  `movement` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `register_time` datetime NOT NULL,
  `video_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`handcontent_key`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `handcontent` WRITE;
/*!40000 ALTER TABLE `handcontent` DISABLE KEYS */;

INSERT INTO `handcontent` (`handcontent_key`, `explanation`, `image_path`, `mean`, `movement`, `name`, `register_time`, `video_path`)
VALUES
	(1,'손등이 밖으로 손끝이 오른쪽으로 향하게 편 왼 손등을 오른 주먹의 1·2지 끝으로 두 번 두드린 다음, 손바닥이 위로 향하게 편 왼 손바닥에 오른 주먹의 1·2지를 펴서 등을 댄다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0060_SYN01.png','환자가 병을 고치기 위하여 일정한 기간 동안 병원에 들어가 머무는 것.','[진찰 + 눕다]','입원(入院)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0060_SYN01_F.mp4\r'),
	(2,'오른 주먹의 1지를 펴서 반쯤 구부려 오른쪽 귀 가까이서 좌우로 두 번 움직인 다음, 왼손을 펴서 손끝이 위로 손바닥이 밖으로 향하게 세우고, 그 바닥에 약간 굽힌 오른 손끝을 댄 다음 밖으로 밀어낸다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0552_SYN02.png','안의 소리가 밖으로 새어 나가거나 밖의 소리가 안으로 들어오지 못하도록 막음.','[소리+막다]','방음(防音)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0552_SYN02_F.mp4\r'),
	(3,'오른 주먹의 1지를 펴서 끝 바닥으로 코 끝을 왼쪽으로 스쳐 낸 다음, 약간 구부린 오른손의 손끝을 턱 왼쪽에 댔다가 오른쪽으로 비스듬히 올린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0690_SYN02.png','불길하고 무서운 꿈.','[나쁘다+꿈]','악몽(惡夢)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0690_SYN02_F.mp4\r'),
	(4,'오른 주먹의 1지를 펴서 끝으로 치아를 가리킨 다음, 손등이 위로 손끝이 밖으로 향하게 편 두 손의 손가락을 번갈아 움직이며 눈앞에서 아래로 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0005_SYN01.png','대기 중의 수증기가 찬 기운을 만나 얼어서 땅 위로 떨어지는 얼음의 결정체.','[희다+내리다]','눈','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0005_SYN01_F.mp4\r'),
	(5,'오른 주먹의 1지를 펴서 끝이 아래로 향하게 하여 끝으로 명치 부위를 스쳐 올려 바닥이 안으로 향하게 세운 다음, 손끝이 오른쪽으로 향하게 모로 세운 왼 손바닥에 오른 주먹의 1지를 펴서 끝을 댄 채 밖으로 내민다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0006_SYN01.png','배우자 없이 혼자 사는 사람.','[혼자+추진]','독신','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0006_SYN01_F.mp4\r'),
	(6,'오른 주먹의 1·5지를 펴서 끝을 맞대어 오른쪽 눈 밑에 댔다가 내린 다음, 손가락을 펴서 구부려 가슴에 대고 왼쪽으로 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0009_SYN01.png','괴롭거나 울고 싶은 느낌이 있다.','[울다+괴롭다]','슬프다','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0009_SYN01_F.mp4\r'),
	(7,'오른손의 1·5지 끝을 맞대어 동그라미를 만들어, 손바닥이 안으로 향하게 편 왼 손등에 댄 다음, 왼 주먹의 5지와 오른 주먹의 4지를 펴서 세워 붙였다가 양옆으로 벌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0567_SYN02.png','부부나 한집안 식구가 따로 떨어져 삶.','[임시+이혼]','별거','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0567_SYN02_F.mp4\r'),
	(8,'오른 주먹의 5지 바닥을 1·2지 손톱에 가볍게 대고 손등이 밖으로 향하게 하여 왼쪽 아래로 내리며 1·2지를 편 다음, 오른 주먹의 4지를 펴서 등이 밖으로 향하게 세우고, 왼 주먹의 5지를 펴서 바닥이 밖으로 향하게 세워 5지와 4지 옆면을 가슴 앞에서 맞댄다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0061_SYN01.png','다시 결혼함.','[다시+결혼]','재혼','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0061_SYN01_F.mp4\r'),
	(9,'오른 주먹의 1지를 펴서 목 중앙에 댄 다음, 손가락이 위로 향하게 편 두 손의 손등을 맞대고 엇갈리게 상하로 움직인다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0012_SYN01.png','서먹서먹하여 부자연스럽고 쑥스럽다.','','어색하다','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0012_SYN01_F.mp4\r'),
	(10,'자연스럽게 편 오른손의 5지 끝을 이마 중앙에 대고 좌우로 흔든 다음, 손바닥이 옆으로 손끝이 위로 향하게 펴서 약간 구부린 두 손을 얼굴 양옆에 세워 마주 보게 하여 가볍게 좌우로 두 번 흔든다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0573_SYN02.png','아직 다 자라지 아니한 어린 닭.','[닭+아기]','병아리','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0573_SYN02_F.mp4\r'),
	(11,'오른 주먹을 베고 오른쪽으로 약간 기운 자세를 취하고, 오른 주먹의 1·2지를 펴서 반쯤 구부려 벌려 끝이 두 눈으로 향하게 하여 왼쪽으로 돌린 다음, 오른 주먹의 1·2지를 펴서 2지 옆면으로 왼 주먹의 손목을 두 번 두드린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0598_SYN02.png','기숙사에서 기숙생들의 생활을 지도하고 감독하는 사람.','[잠+살피다+교사]','사감','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0598_SYN02_F.mp4\r'),
	(12,'오른 주먹의 1지 끝으로 왼 팔등을 스쳐 내린 다음, 손끝을 모은 두 손을 상하로 두 번 마주 댔다 떼며 아래로 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0606_SYN02.png','같은 핏줄의 계통.','[핏줄을 나타내는 동작+이어내림을 나타내는 동작]','혈통','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0606_SYN02_F.mp4\r'),
	(13,'오른 주먹의 1·2지를 펴서 끝 바닥으로, 손등이 밖으로 손끝이 오른쪽으로 향하게 편 왼 손등을 두 번 두드린 다음, 두 주먹의 1지를 펴서 끝으로 네모를 그린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0062_SYN01.png','의사가 병의 진단 결과를 적은 증명서.','[진찰+네모]','진단서','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0062_SYN01_F.mp4\r'),
	(14,'오른 주먹의 1지를 펴서 옆면으로 입술 밑을 오른쪽으로 스쳐 낸 다음, 오른 주먹의 1·5지로 오른쪽 귀를 잡는다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0612_SYN02.png','혈액의 분류형.','[피+귀에서 채혈하는 것을 나타내는 동작]','혈액형','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0612_SYN02_F.mp4\r'),
	(15,'왼 주먹의 5지를 펴서 세우고 그 등에서 손바닥이 왼쪽으로 손끝이 밖으로 향하게 편 오른손을 좌우로 흔든다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0613_SYN02.png','감정이나 상태의 변화 따위를 부추김.','','부채질','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0613_SYN02_F.mp4\r'),
	(16,'손등이 밖으로 손끝이 오른쪽으로 향하게 편 왼 손등을, 오른 주먹의 1·2지를 펴서 끝으로 두드린 다음, 손바닥이 위로 향하게 편 왼 손바닥에 오른 주먹의 1·2지를 펴서 눕혔다가 오른쪽으로 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0067_SYN01.png','일정 기간 병원에 머물던 환자가 병원에서 나옴.','[진찰+침대(병상)에서 내려오는 동작]','퇴원(退院)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0067_SYN01_F.mp4\r'),
	(17,'오른 주먹의 1·2지를 펴서 끝 바닥을 왼 주먹의 손목 안쪽에 댄 다음, 두 주먹을 마주 보게 하여 전후로 엇갈리게 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0068_SYN01.png','한방에서 쓰는 약.','[맥+짜다]','한약(韓藥)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0068_SYN01_F.mp4\r'),
	(18,'오른 주먹의 4·5지를 펴서 5지 끝을 코끝에 대고 왼 주먹의 4·5지를 펴서 5지 끝을 오른 주먹의 4지 끝에 대고 일렬로 세워 밖으로 짧게 힘주어 내민 다음, 왼 주먹의 1·2지를 펴서 등이 위로 향하게 하고, 그 등에 오른 주먹의 1·2지를 펴서 바닥을 ‘X’자로 댔다가 뒤집어 등을 댄다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0620_SYN02.png','상으로 주는 물품.','[상(賞)+선물]','상품(賞品)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0620_SYN02_F.mp4\r'),
	(19,'두 손을 펴서 손등이 밖으로 손끝이 위로 향하게 세워 손가락을 가볍게 흔들면서 상하로 엇갈리게 움직인 다음, 두 주먹의 1지를 펴서 끝으로 얼굴을 두 번 엇갈리게 스쳐 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0071_SYN01.png','높은 온도의 기체, 액체, 고체, 화염 따위에 데었을 때 일어나는 피부의 손상.','[불+상처]','화상(火傷)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0071_SYN01_F.mp4\r'),
	(20,'오른 주먹의 1지를 펴서 끝으로 왼 손바닥을 두 번 빠르게 두드린 다음, 손바닥이 위로 손끝이 옆으로 향하게 편 두 손을 배에서 가슴 앞으로 세 번 급히 올린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0026_SYN01.png','놀라서 다급히.','','황급하다','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0026_SYN01_F.mp4\r'),
	(21,'오른 주먹의 1·5지를 펴서 손등을 입 앞에 대고 1·5지 끝을 붙인 다음, 두 손을 옆으로 벌려 상하로 흔든다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0621_SYN02.png','몸에 깃털이 있고 다리가 둘이며, 하늘을 자유로이 날 수 있는 짐승을 통틀어 이르는 말.','[부리+날다]','새','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0621_SYN02_F.mp4\r'),
	(22,'손끝이 위로 손바닥이 왼쪽으로 향하게 편 오른손의 1지 옆면을 이마 중앙에 댄 다음, 왼 주먹의 5지를 펴서 바닥이 밖으로 향하게 세우고, 오른 손바닥으로 왼 주먹 등을 두 번 좌우로 스쳐 낸다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0028_SYN01.png','앓는 사람을 찾아가서 병세를 알아보고 위안하는 일.','','병문안','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0028_SYN01_F.mp4\r'),
	(23,'오른 주먹의 1·2지를 약간 구부려 끝이 두 눈으로 향하게 하여 왼쪽으로 두 바퀴 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0029_SYN01.png','사실이나 일의 상태 또는 물질의 구성 성분 따위를 조사하여 옳고 그름과 낫고 못함을 판단하는 일.','','검사(檢査)','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0029_SYN01_F.mp4\r'),
	(24,'모로 세운 왼 주먹에 오른 주먹의 팔꿈치를 대고 1지를 펴서 끝이 밖으로 향하게 하여 한 번 위로 빠르게 올린 다음, 손바닥이 아래로 향하게 반쯤 구부린 오른손을 가슴 앞에서 약간 내리다가 멈춘다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0031_SYN01.png','낚시질하는 곳','[낚시+곳]','낚시터','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0031_SYN01_F.mp4\r'),
	(25,'가볍게 쥐고 모로 세운 왼 주먹의 1·5지 속을 오른 주먹의 1지를 펴서 구부려 콕콕 찍은 다음, 두 손 각각 1·5지 끝을 맞대어 동그라미를 만들어 손바닥이 밖으로 향하게 맞댔다가 양옆으로 벌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0072_SYN01.png','가는 원통형으로 길게 뽑아 일정한 길이로 자른 흰떡.','[떡+대]','가래떡','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0072_SYN01_F.mp4\r'),
	(26,'두 손을 펴서 손끝이 밖으로 향하게 하여 맞댔다가 손목을 양옆으로 돌려 편 다음, 오른 주먹의 1·2지를 펴서 끝으로, 손바닥이 위로 손끝이 밖으로 향하게 펴서 비스듬히 세운 왼 손바닥을 두 번 오르내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0034_SYN01.png','책을 읽음.','[책+읽다]','독서','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0034_SYN01_F.mp4\r'),
	(27,'오른손의 손가락을 구부려 턱에 대고 좌우로 움직인 다음, 오른 주먹의 1·2지를 벌려 펴서 끝 바닥을 두 눈에 대고 아래로 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0625_SYN02.png','빛깔을 가려내지 못하는 상태.','[색깔+맹]','색맹','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0625_SYN02_F.mp4\r'),
	(28,'오른손의 손가락을 구부려 턱에 대고 좌우로 움직인 다음, 오른 주먹의 1·5지 끝을 입에 댔다가 그 끝을 전후로 약간 움직이며 오른쪽으로 이동시킨다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0626_SYN02.png','심에 물감을 섞어 여러 가지 색깔이 나게 만든 연필.','[색깔+연필]','색연필','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0626_SYN02_F.mp4\r'),
	(29,'오른 주먹의 1지를 펴서 옆면으로 입술 밑을 오른쪽으로 스쳐 낸 다음, 오른 주먹의 1지를 그 등 쪽에서 왼 주먹의 1·5지로 잡는다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0074_SYN01.png','초록색이나 익으면 빨갛게 되며 매운 맛이 나는 열매.','[빨강+고추 모양]','고추','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0074_SYN01_F.mp4\r'),
	(30,'오른손을 펴서 5지 끝을 이마에 대고 나머지 손가락을 좌우로 약간 흔든 다음, 오른 주먹으로 오른쪽 어깨 위에서 밖으로 쳐내는 동작을 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0038_SYN01.png','네트를 사이에 두고 라켓으로 셔틀콕을 서로 치고 받는 구기 경기.','[닭+받아치는 동작]','배드민턴','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0038_SYN01_F.mp4\r'),
	(31,'오른쪽 볼에 오른손의 2지 끝을 댔다 떼고 손바닥을 살짝 댄 다음, 오른손을 펴서 손끝이 위로 손바닥이 입으로 향하게 하여, 내민 혀 앞에서 왼쪽으로 두 바퀴 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0079_SYN01.png','꿀벌이 꽃에서 빨아들여 벌집 속에 모아 두는 달콤하고 끈끈한 액체.','[벌+달다]','꿀','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0079_SYN01_F.mp4\r'),
	(32,'오른손의 손가락을 구부려 턱에 대고 좌우로 움직인 다음, 두 주먹의 1지를 펴서 네모를 그린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0627_SYN02.png','여러 가지 색깔로 물들인 종이.','[색깔+네모]','색종이','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0627_SYN02_F.mp4\r'),
	(33,'1·2지를 펴서 반쯤 구부린 오른 주먹을 왼 손등 위쪽에서 모로 세워 밖으로 내민 다음, 손등이 위로 향하게 손끝을 모아 쥔 두 주먹을 밖으로 내밀며 손가락을 편다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0635_SYN02.png','기차나 자동차 따위의 앞쪽에 달린 등.','[자동차+켜다]','헤드라이트','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0635_SYN02_F.mp4\r'),
	(34,'자연스럽게 편 오른손의 5지 끝을 이마 중앙에 대고 좌우로 흔든 다음, 두 주먹의 1·2지를 펴서 입으로 돌려 올리는 동작을 동시에 두 번 반복한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0090_SYN01.png','국수를 증기로 익히고 기름에 튀겨서 말린 즉석식품.','[닭+국수]','라면','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0090_SYN01_F.mp4\r'),
	(35,'오른손의 1·2·3·4지 바닥을 왼쪽 볼에, 5지 바닥은 오른쪽 볼에 댔다가 아래로 내리며 손가락을 모아 붙인 다음, 오른손의 2지 끝을 5지 끝에 대고 턱 밑으로 올리며 튕겨 편다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0043_SYN01.png','행복하지 아니함.','[행복+거두는 동작]','불행','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0043_SYN01_F.mp4\r'),
	(36,'오른 주먹의 1지를 펴서 끝으로 치아를 가리킨 다음, 오른손으로, 주먹을 쥐고 앞으로 내놓은 왼 팔목을 중심으로 한 바퀴 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0044_SYN01.png','상처나 부스럼 따위에 감는 소독한 헝겊.','[희다+감는 동작]','붕대','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0044_SYN01_F.mp4\r'),
	(37,'오른손의 손가락을 구부려 끝을 배에 대고 상하로 흔든 다음, 1·2·5지 끝이 위로 향하게 편 오른 주먹을 아래로 내리면서 1·2·5지 끝을 맞댄다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0046_SYN01.png','변에 포함된 수분의 양이 많아져서 변이 액상','[불편한 속을 나타내는 동작+대변]','설사','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0046_SYN01_F.mp4\r'),
	(38,'오른 주먹의 1지를 펴서 모로 세운 왼 주먹의 주위를 위로부터 밖으로 한 바퀴 돌리고, 오른 주먹의 5지를 펴서 세워 왼 손등에 힘주어 내려놓은 다음, 손등이 아래로 향하게 가볍게 쥔 두 주먹을 위로 올리며 손가락을 활짝 편다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0653_SYN02.png','연말에 한 해를 보내며 그 해의 온갖 괴로움을 잊자는 뜻으로 베푸는 모임.','[1년+마지막+축하]','송년회','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0653_SYN02_F.mp4\r'),
	(39,'오른 주먹의 1지를 펴서 끝을 오른쪽 관자놀이에 댄 다음, 주먹을 쥐고 1·5지 옆면이 닿게 코에 댔다가 약간 밖으로 내민다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0656_SYN02.png','머리가 좋고 재주가 뛰어난 사람.','','수재','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0656_SYN02_F.mp4\r'),
	(40,'오른 손끝을 코 오른쪽에 댔다가 떼면서 5지로 나머지 손가락을 4지부터 차례로 비빈 다음, 두 손의 손끝이 아래로 향하게 하여 4지 옆면을 배 양옆에 댔다가 아래로 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0661_SYN02.png','산모가 아무 탈 없이 순조롭게 아이를 낳음.','[순조롭다+낳다]','순산','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0661_SYN02_F.mp4\r'),
	(41,'왼 손등에 오른 손끝 바닥을 댄 다음, 오른 주먹의 4지를 펴서 끝으로 배를 스쳐 내려 등이 위로 향하게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0050_SYN01.png','아들의 딸. 또는 딸의 딸','[손(‘손’의 음을 ‘孫’(손)의 음으로 빌려 씀)+딸]','손녀','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0050_SYN01_F.mp4\r'),
	(42,'오른 손끝 바닥을 왼 손등에 댔다가 아래로 내리며 주먹을 쥐고 5지를 펴서 끝 바닥으로 배를 스쳐 내리며 손목을 돌려 바닥이 보이게 한다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0051_SYN01.png','아들의 아들. 또는 딸의 아들','[손(‘손’의 음을 ‘孫’(손)의 음으로 빌려 씀)+아들]','손자','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0051_SYN01_F.mp4\r'),
	(43,'오른 주먹의 5지를 펴서 세워 위로 올렸다가 5지 끝이 아래로 향하게 하여 내린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0668_SYN02.png','승리와 패배를 아울러 이르는 말.','[이기다+지다]','승패','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0668_SYN02_F.mp4\r'),
	(44,'약간 구부린 두 손의 손끝으로 머리 양쪽을 두 번 두드린 다음, 약간 구부린 오른 손끝을 가슴에 대고 왼쪽으로 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0670_SYN02.png','괴로움이나 성가심을 당하다.','[고생+괴롭다]','시달리다','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0670_SYN02_F.mp4\r'),
	(45,'5지를 접고 나머지 손가락을 편 오른손의 1지 옆면을 턱 중앙에 댄 다음, 두 주먹의 1지를 펴서 구부려 끝이 아래로 향하게 하여 번갈아 밖으로 내민다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0682_SYN02.png','생각한 바를 실제로 행함.','[정말+발걸음을 나타내는 동작]','실천','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0682_SYN02_F.mp4\r'),
	(46,'왼 주먹의 5지를 펴서 바닥이 밖으로 향하게 세우고, 오른 주먹의 4지를 펴서 등이 밖으로 향하게 세워 5지와 4지의 옆면을 가슴 앞에서 맞댄 다음, 손바닥이 아래로 향하게 반쯤 구부린 오른손을 가슴 앞에서 약간 내리다가 멈춘다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0055_SYN01.png','예식을 치를 수 있도록 설비를 갖추어 놓은 장소.','[결혼+곳]','예식장','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0055_SYN01_F.mp4\r'),
	(47,'오른 주먹의 1지를 펴서 끝으로 치아를 가리킨 다음, 5지와 1·2지 사이를 약간 벌린 두 손의 손가락을 서로 마주 보게 하여 부드럽게 두 번 굽혔다 편다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0087_SYN01.png','콩으로 만든 식품의 하나.','[희다+부드럽다]','두부','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0087_SYN01_F.mp4\r'),
	(48,'오른 주먹의 1지를 펴서 옆면으로 입술 밑을 오른쪽으로 스쳐 낸 다음, 오른손을 오므려 손끝을 코에 두 번 댄다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0088_SYN01.png','겉에 갈색 작은 씨가 박혀 있는 빨갛고 작은 여름 과일.','[빨강+코 <딸기코>]','딸기','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0088_SYN01_F.mp4\r'),
	(49,'가볍게 쥔 왼 주먹의 1·5지 속을 오른 주먹의 1지를 펴서 구부려 끝으로 콕콕 찍은 다음, 왼손을 펴서 손바닥이 위로 향하게 오므리고, 그 위에서 손바닥이 위로 향하게 편 오른손을 두 바퀴 돌린다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0089_SYN01.png','가래떡을 얇게 썰어 맑은 장국에 넣고 끓인 음식.','[떡+국]','떡국','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0089_SYN01_F.mp4\r'),
	(50,'오른 손바닥을 왼쪽 뺨에 두 번 댄 다음, 오른 주먹의 1지를 구부려 왼 손바닥에 대고 두 손을 동시에 안으로 당긴다.','https://d2o6fwxt8bq04z.cloudfront.net/image/SL_WORD0687_SYN02.png','물건이나 돈, 시간 따위를 함부로 쓰지 아니하다.','[아깝다+절약]','아끼다','2022-02-17 14:11:23','https://d2o6fwxt8bq04z.cloudfront.net/video/NIA_SL_WORD0687_SYN02_F.mp4');

/*!40000 ALTER TABLE `handcontent` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `address_detail` varchar(255) DEFAULT NULL,
  `age` int(3) NOT NULL DEFAULT 0,
  `email` varchar(255) NOT NULL,
  `gender` varchar(1) NOT NULL DEFAULT '',
  `img` varchar(255) NOT NULL,
  `is_bind` varchar(1) NOT NULL DEFAULT 'Y',
  `join_type` varchar(5) NOT NULL,
  `name` varchar(32) NOT NULL,
  `nickname` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `provider` varchar(100) DEFAULT NULL,
  `push` varchar(1) NOT NULL DEFAULT 'Y',
  `token` varchar(255) DEFAULT NULL,
  `uid` varchar(120) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a7hlm8sj8kmijx6ucp7wfyt31` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
