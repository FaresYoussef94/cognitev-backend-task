DROP TABLE IF EXISTS registration_model;
DROP TABLE IF EXISTS users;
 
CREATE TABLE registration_model (
  id INT IDENTITY NOT NULL PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  country_code VARCHAR(5) NOT NULL,
  phone_number VARCHAR(20) UNIQUE NOT NULL,
  gender VARCHAR(6) NOT NULL,
  birthdate VARCHAR(10) NOT NULL,
  email VARCHAR(250) UNIQUE,
  avatar BLOB NOT NULL,
);

CREATE TABLE users(
	id INT IDENTITY NOT NULL PRIMARY KEY,
	phone_number VARCHAR(20) UNIQUE NOT NULL,
	token VARCHAR(250) UNIQUE ,
	password VARCHAR(250) NOT NULL
);

INSERT INTO users(id, phone_number,password) values (1, '01121710117', '1234');
 