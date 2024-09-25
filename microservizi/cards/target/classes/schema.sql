CREATE TABLE IF NOT EXISTS `card` (
  `card_id` int AUTO_INCREMENT  PRIMARY KEY,
  `card_number` varchar(100) NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `total_limit`int NOT NULL,
  `amount_used`int NOT NULL,
  `available_amount`int NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);
