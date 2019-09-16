DROP TABLE IF EXISTS registration_model;
 
CREATE TABLE registration_model (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  country_code VARCHAR(5) NOT NULL,
  phone_number VARCHAR(20) UNIQUE NOT NULL,
  gender VARCHAR(6) NOT NULL,
  birthdate VARCHAR(10) NOT NULL,
  email VARCHAR(250) UNIQUE,
  avatar BLOB NOT NULL,
);
 