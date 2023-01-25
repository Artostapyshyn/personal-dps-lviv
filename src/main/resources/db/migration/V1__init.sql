CREATE TABLE IF NOT EXISTS employees (
  id bigint NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  department varchar(100) NOT NULL,
  birth_date varchar(100) NOT NULL,
  phone_number varchar(100) NOT NULL,
  email varchar(150) NOT NULL,
  password varchar(255) NOT NULL,
  enabled boolean NOT NULL,
  confirmation_token boolean,
  reset_token boolean,
  PRIMARY KEY (id),
  UNIQUE KEY UK_email (email)
)

CREATE TABLE IF NOT EXISTS fired_employees (
  id bigint NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  department varchar(100) NOT NULL,
  birth_date varchar(100) NOT NULL,
  phone_number varchar(100) NOT NULL,
  firing_reason varchar(255) NOT NULL,
  firing_date varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (name)
);

CREATE TABLE employees_roles (
  employee_id bigint NOT NULL,
  role_id bigint NOT NULL,
  PRIMARY KEY (employees_id, roles_id),
  FOREIGN KEY (employees_id) REFERENCES employees(id) ON UPDATE CASCADE,
  FOREIGN KEY (roles_id) REFERENCES roles(id) ON UPDATE CASCADE
);

