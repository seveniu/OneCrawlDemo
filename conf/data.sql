SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `field`
-- ----------------------------
CREATE TABLE `field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT 'HTML_TEXT(1), TARGET_LINK(2),NEXT_LINK(3), TEXT_LINK(4), PURE_TEXT(5);',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

INSERT INTO `field` (`id`, `name`, `type`)
VALUES
  (1, '作者', 5),
  (2, '标题', 5),
  (3, '章节目录', 2),
  (4, '书内容', 1),
  (5, '链接', 2),
  (6, '下一页', 3),
  (7, '内容', 1),
  (8, '时间', 5),
  (9, '书籍简介', 1),
  (10, 'URL', 2),
  (11, '内容链接', 4),
  (12, 'ISSN/ISBN', 5),
  (13, 'E-ISSN', 5),
  (14, '作者工作单位', 5),
  (15, '国家', 5),
  (16, '语言', 5),
  (17, '出版品名称', 5),
  (18, '期次', 5),
  (19, '关键字', 5),
  (20, '文章摘要', 1),
  (21, '下载链接', 6),
  (22, 'test', 1);



-- ----------------------------
--  Table structure for `field_group`
-- ----------------------------
CREATE TABLE `field_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `fields` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

INSERT INTO `field_group` (`id`, `name`, `fields`)
VALUES
  (1, '通用列表', '[\"5\",\"6\"]'),
  (2, '通用内容', '[\"1\",\"2\",\"7\",\"8\",\"6\"]'),
  (4, '书籍内容', '[\"4\",\"5\",\"3\"]'),
  (13, '外部工具', '[\"2\",\"10\"]');


-- ----------------------------
--  Table structure for `template`
-- ----------------------------
CREATE TABLE `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` int(11) NOT NULL COMMENT '1 多级内容页，只有最后一级是内容，2. 单页（跳转后是新的单页）',
  `pages` longtext NOT NULL,
  `website_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1:正常状态，2: 禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `language` varchar(10) NOT NULL DEFAULT '',
  `country` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `website_id` (`website_id`),
  KEY `status` (`status`),
  KEY `name` (`name`),
  KEY `create_time` (`create_time`),
  KEY `language` (`language`),
  KEY `country` (`country`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `website`
-- ----------------------------
CREATE TABLE `website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `domain` varchar(100) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq` (`name`,`domain`),
  UNIQUE KEY `domain` (`domain`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `data`
-- ----------------------------
CREATE TABLE `data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL DEFAULT '',
  `title` varchar(500) NOT NULL DEFAULT '',
  `content` text NOT NULL,
  `author` varchar(500) NOT NULL DEFAULT '',
  `date` varchar(500) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;
