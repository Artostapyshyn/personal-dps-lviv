CREATE TABLE IF NOT EXISTS employees (
  id serial NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  department varchar(100) NOT NULL,
  birth_date varchar(100) NOT NULL,
  phone_number varchar(100) NOT NULL,
  email varchar(150) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  enabled boolean,
  confirmation_token varchar(255),
  reset_token varchar(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS fired_employees (
  id serial NOT NULL,
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
  id serial NOT NULL,
  name varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employees_roles (
  employee_id serial NOT NULL,
  role_id serial NOT NULL,
  PRIMARY KEY (employee_id, role_id),
  FOREIGN KEY (employee_id) REFERENCES employees(id) ON UPDATE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles(id) ON UPDATE CASCADE
);

