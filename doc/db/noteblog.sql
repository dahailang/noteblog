创建数据库：
create database `noteblog` default character set utf8 collate utf8_general_ci; 
grant all privileges on noteblog.* to noteblog identified by "123456";

--用户表
DROP TABLE IF EXISTS `note_user`;
CREATE TABLE `note_user` (
  `id` varchar(40) NOT NULL COMMENT '主键id',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '用户类型 0 管理员 1 普通用户',
  `avatar` varchar(255) NOT NULL COMMENT '用户头像',
  `status` int(4) NOT NULL COMMENT '配置状态 0 正常 1 回收站 ',
  `last_editor_noteid` varchar(40) COMMENT '上次编辑笔记本id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

--笔记本表
DROP TABLE IF EXISTS `note_book`;
CREATE TABLE `note_book` ( 
  `id` varchar(40) NOT NULL COMMENT '主键id',
  `pid` varchar(40) NOT NULL COMMENT '父节点id',
  `uid` varchar(40) NOT NULL COMMENT '用户id',
   is_parent int(2)  NOT NULL COMMENT '是否是目录节点',
  `name` varchar(255) DEFAULT NULL COMMENT '笔记本标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `status` int(4) NOT NULL COMMENT '配置状态 0 正常 1 回收站 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记本';

--笔记本摘要
DROP TABLE IF EXISTS `note_summary`;
CREATE TABLE `note_summary` ( 
  `id` varchar(40) NOT NULL COMMENT '主键id',
  `nid` varchar(40) NOT NULL COMMENT '文档内容id',
  `bid` varchar(40) NOT NULL COMMENT '笔记本id',
  `title` varchar(255) DEFAULT NULL COMMENT '笔记本标题',
  `summary` varchar(255) DEFAULT NULL COMMENT '笔记本摘要',
  `status` int(4) NOT NULL COMMENT '配置状态 0 正常 1 回收站 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记本摘要表';

--笔记内容表
DROP TABLE IF EXISTS `note_content`;
CREATE TABLE `note_content` (
  `id` varchar(40) NOT NULL COMMENT '主键id',
  `uid` varchar(40) NOT NULL COMMENT '归属用户id',
  `content` text DEFAULT NULL COMMENT '文档内容',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_updated_uid` varchar(40) DEFAULT NULL COMMENT '最后更新用户',
  `status` int(4) NOT NULL COMMENT '配置状态 0 正常 1 回收站 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档内容';
--配置表
DROP TABLE IF EXISTS `note_config`;
CREATE TABLE `note_config` (
  `id` varchar(40) NOT NULL COMMENT '主键id',
  `config_key` varchar(255) NOT NULL COMMENT '配置key',
  `config_value` varchar(255) NOT NULL COMMENT '配置value',
  `info` varchar(255) NOT NULL COMMENT '配置说明',
  `status` int(4) NOT NULL COMMENT '配置状态 0 正常 1 回收站 ',
  `order_id` int(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置';


--
-- Dumping data for table `tb_content`
--
LOCK TABLES `tb_content` note_user;
/*!40000 ALTER TABLE `tb_content` DISABLE KEYS */;
INSERT INTO `tb_content` VALUES (28,89,'标题2','标题3','标题1','http://www.jd.com','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFY-FTqAGIxLAAov-qfdCPQ072.png','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFY-Fb6Aeu-MAAE8I3uYZpQ989.jpg','标题1',NULL,'2016-07-08 21:54:52'),(29,89,'ad2','ad2','ad2','http://www.baidu.com','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFY-FfWAPWr9AAI1uIcWLf0213.jpg','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFY-Ff2ANeMFAATGV4DTU9g335.jpg','ad2',NULL,'2016-07-08 21:55:44'),(30,89,'ad32','ad32','ad311','http://www.sina.com.cn','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFY-FhaANoJ2AAG8JA4HWDM570.jpg','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFY-FhyAKXKbAAE8I3uYZpQ991.jpg','这是一个非常大的文章',NULL,'2016-07-08 22:11:48'),(31,89,'22123方式','2131','1231从','1232','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFeAkpSAH2ogAATGV4DTU9g198.jpg','','1232','2016-07-09 20:47:43','2016-07-09 20:47:43'),(32,89,'12313','12','1发','1212','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFeAkt6AQRxJAAG8JA4HWDM932.jpg','','123','2016-07-09 20:48:57','2016-07-09 20:48:57'),(33,90,'123123','121','1水电费','222','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFeAkxaAYUqKAATGV4DTU9g558.jpg','','如法v','2016-07-09 20:49:45','2016-07-09 20:49:45'),(34,90,'123123','121','1水电费','222','http://192.168.128.128:8080/group1/M00/00/00/wKiAgFeAkxaAYUqKAATGV4DTU9g558.jpg','','如法v','2016-07-09 20:58:40','2016-07-09 20:58:40');
/*!40000 ALTER TABLE `tb_content` ENABLE KEYS */;
UNLOCK TABLES;

//20171113 添加字段
ALTER TABLE `note_user`
ADD COLUMN `last_editor_noteid`  varchar(40) NULL AFTER `status`;
