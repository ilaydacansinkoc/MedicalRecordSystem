SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP TABLE IF EXISTS `allowed_doctors`;
CREATE TABLE IF NOT EXISTS `allowed_doctors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`),
  FOREIGN KEY (`doctor_id`) REFERENCES `users` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `allowed_nurses`;
CREATE TABLE IF NOT EXISTS `allowed_nurses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nurse_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`nurse_id` ) REFERENCES `users`(`user_id`),
  FOREIGN KEY (`doctor_id`) REFERENCES `users`(`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `allowed_relatives`;
CREATE TABLE IF NOT EXISTS `allowed_relatives` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `relative_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`patient_id` ) REFERENCES `users`(`user_id`),
  FOREIGN KEY (`relative_id`) REFERENCES `users`(`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `patient_details`;
CREATE TABLE IF NOT EXISTS `patient_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diagnostic` varchar(256) NOT NULL,
  `prescription` varchar(256) NOT NULL,
  `user_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `relatives`;
CREATE TABLE IF NOT EXISTS `relatives` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relative_first_name` varchar(256) NOT NULL,
  `relative_last_name` varchar(256) NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `users`(`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(256) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'admin'),
(2, 'doctor'),
(3, 'nurse'),
(4, 'patient'),
(5, 'relative');


DROP TABLE IF EXISTS `role_users`;
CREATE TABLE IF NOT EXISTS `role_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`role_id`)  REFERENCES `roles`(`role_id`),
  FOREIGN KEY (`user_id`)  REFERENCES `users`(`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_first_name` varchar(256) NOT NULL,
  `patient_last_name` varchar(256) NOT NULL,
  `birth_date` varchar(256) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`doctor_id`) REFERENCES `users`(`user_id`),
  FOREIGN KEY (`patient_id`) REFERENCES `users`(`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  `birth_date` varchar(256) NOT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `is_authorized` varchar(256) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`role_id`)  REFERENCES `roles`(`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

COMMIT;
