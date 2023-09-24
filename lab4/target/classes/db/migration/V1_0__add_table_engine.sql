CREATE TABLE IF NOT EXISTS 'Engine' (
    'id' int NOT NULL PRIMARY KEY,
    'Name' varchar(20),
    'Value' int,
    'Quantity' int,
    'Higher' int,
    'Cylinder' int,
    'CarModel' varchar(50)

    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;