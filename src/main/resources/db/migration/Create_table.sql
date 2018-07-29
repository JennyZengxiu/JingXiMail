CREATE TABLE `Product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '',
  `description` varchar(100) DEFAULT NULL,
  `price` int (10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

INSERT INTO `Product` VALUES (1,'薯片','乐事',10),(2,'方便面','汤达人',7),(3,'方便面','康师傅',5),(4,'矿泉水','怡宝',2);

CREATE TABLE `Inventory` (
  `id` int(10) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '0',
  `lockedCount` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

INSERT INTO `Inventory` VALUES (1,100,100),(2,100,100),(3,100,100),(4,100,100);