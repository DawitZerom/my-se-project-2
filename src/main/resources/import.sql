INSERT INTO roles VALUES (null, 'ROLE_ADMIN');
INSERT INTO roles VALUES (null, 'ROLE_REGISTRAR');
INSERT INTO roles VALUES (null, 'ROLE_STUDENT');
INSERT INTO users VALUES (null, b'1',  b'1',  b'1', 'ana.admin@library.com',b'1', 'Ana', 'Test', 'Admin', '$2a$10$ogq095yZjfs9P2hWAufFdO0lQ3sxvQhGLlScDqnP0LPqzCO3MnR0y', 'ana.admin@library.com');
INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (1, 3);
INSERT INTO users VALUES (null,  b'1',  b'1',  b'1', 'bob.registrar@library.com',b'1', 'Bob', 'Test', 'Registrar', '$2a$10$ogq095yZjfs9P2hWAufFdO0lQ3sxvQhGLlScDqnP0LPqzCO3MnR0y', 'bob.registrar@library.com');
INSERT INTO users_roles VALUES (2, 2);
INSERT INTO users VALUES (null,  b'1',  b'1',  b'1', 'carlos.student@library.com',b'1', 'Carlos', 'Test', 'Student', '$2a$10$ogq095yZjfs9P2hWAufFdO0lQ3sxvQhGLlScDqnP0LPqzCO3MnR0y', 'carlos.student@library.com');
INSERT INTO users_roles VALUES (3, 3);

INSERT INTO library_member_type VALUES (null, 21, 'basic', 0.25);
INSERT INTO library_member_type VALUES (null, 21, 'staff', 0.10);
INSERT INTO library_member_type VALUES (null, 42, 'senior', 0.05);