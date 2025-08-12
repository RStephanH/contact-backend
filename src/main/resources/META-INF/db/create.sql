CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    mail VARCHAR(255),
    username VARCHAR(100) UNIQUE,
    password VARCHAR(255)
);

