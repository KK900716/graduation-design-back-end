# 不同水体识别系统设计与实现后端

#### 介绍
前端Vue，后端SpringBoot，图像识别YOLOv5，数据库Redis、Mysql
#### SQL

```mysql
CREATE TABLE `userinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `permission` enum('1','2','3') NOT NULL,
  `balance` float NOT NULL DEFAULT '0',
  `available` int NOT NULL DEFAULT '10',
  `capacity` int NOT NULL DEFAULT '10',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
CREATE TABLE `userwarehouse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `count` int NOT NULL DEFAULT '10',
  `available` int NOT NULL DEFAULT '0',
  `remaining` int NOT NULL DEFAULT '10',
  `userInfo_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userInfo_id` (`userInfo_id`),
  CONSTRAINT `userwarehouse_ibfk_1` FOREIGN KEY (`userInfo_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
CREATE TABLE `warehouse` (
  `id` varchar(128) NOT NULL,
  `userWarehouse_id` int NOT NULL,
  `state` enum('处理中','处理成功','处理失败') NOT NULL DEFAULT '处理中',
  `score` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userWarehouse_id` (`userWarehouse_id`),
  CONSTRAINT `warehouse_ibfk_1` FOREIGN KEY (`userWarehouse_id`) REFERENCES `userwarehouse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
```
