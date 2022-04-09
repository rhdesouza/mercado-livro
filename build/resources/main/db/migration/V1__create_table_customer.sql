CREATE TABLE customer (
  id int AUTO_INCREMENT PRIMARY KEY,
  email varchar(255) not null,
  name varchar(255) not null unique
);
