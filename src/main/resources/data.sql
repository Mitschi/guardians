	DROP TABLE IF EXISTS guardians_values;

	CREATE TABLE guardians_values (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	value INT NOT NULL,
	country VARCHAR(250) NOT NULL
	);

	INSERT INTO guardians_values(value,country)
	VALUES
	(123,'Austria'),
	(212,'Germany'),
	(33,'France'),
	(144,'USA'),
	(223,'China'),
	(12,'spain'),
	(142,'Canada');



