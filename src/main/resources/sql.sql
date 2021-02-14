CREATE TABLE `salary` (
 `id` varchar(255) NOT NULL,
 `salary` bigint DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB;
 
 CREATE TABLE `employee` (
 `id` varchar(255) NOT NULL,
 `birth_date` datetime DEFAULT NULL,
 `created_at` datetime DEFAULT NULL,
 `first_name` varchar(255) DEFAULT NULL,
 `gender` varchar(255) DEFAULT NULL,
 `hire_date` datetime DEFAULT NULL,
 `last_name` varchar(255) DEFAULT NULL,
 `total_salary` bigint DEFAULT NULL,
 `total_working_days` int DEFAULT 0,
 `updated_at` datetime DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB;
 
  CREATE TABLE `attendance` (
 `id` varchar(255) NOT NULL,
 `date_of_work` datetime DEFAULT NULL,
 `check_in` datetime DEFAULT NULL,
 `check_out` datetime DEFAULT NULL,
 `employee_id` varchar(255) NOT NULL,
 `first_name` varchar(255) DEFAULT NULL,
 `hire_date` datetime DEFAULT NULL,
 `last_name` varchar(255) DEFAULT NULL,
 `salary_id` varchar(255) DEFAULT NULL,
 `total_salary` bigint DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB;