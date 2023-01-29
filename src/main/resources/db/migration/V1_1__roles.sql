INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO employees (first_name, last_name, department, birth_date, phone_number, email, password, enabled) VALUES ('admin', 'admin', 'admin', '11-11-1999', '1111-2222-3333', 'admin@gmail.com','$2a$04$M1QKsvEunOcdRmY5SOkfK.OEgO6mAknOQEf.M3dUmBcQFVQZXJSt6', '1');
INSERT INTO employees (first_name, last_name, department, birth_date, phone_number, email, password, enabled) VALUES ('user', 'user', 'user', '11-11-1999', '1111-2222-3333', 'user@gmail.com','$2a$04$HczQoHy2L9OZYbFLzesVuevnlZT5MP.6OW4sKJXGp8RcOMvXpSfWq', '1');

INSERT INTO employees_roles (employee_id, role_id) VALUES (1, 2);  
INSERT INTO employees_roles (employee_id, role_id) VALUES (2, 1); 