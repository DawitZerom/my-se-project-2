INSERT INTO roles VALUES (null, 'ROLE_ADMIN');
INSERT INTO roles VALUES (null, 'ROLE_LIBRARIAN');
INSERT INTO roles VALUES (null, 'ROLE_MEMBER');
INSERT INTO users VALUES (null, b'1',  b'1',  b'1', 'go.admin@library.com', b'1', 'Ana', 'Test', 'Admin', '$2a$10$ogq095yZjfs9P2hWAufFdO0lQ3sxvQhGLlScDqnP0LPqzCO3MnR0y', 'go.admin@library.com');
INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (1, 3);
INSERT INTO users VALUES (null,  b'1',  b'1',  b'1', 'go.librarian@library.com', b'1', 'Bob', 'Test', 'Librarian', '$2a$10$ogq095yZjfs9P2hWAufFdO0lQ3sxvQhGLlScDqnP0LPqzCO3MnR0y', 'go.librarian@library.com');
INSERT INTO users_roles VALUES (2, 2);
INSERT INTO users VALUES (null,  b'1',  b'1',  b'1', 'go.member@library.com', b'1', 'Carlos', 'Test', 'Member', '$2a$10$ogq095yZjfs9P2hWAufFdO0lQ3sxvQhGLlScDqnP0LPqzCO3MnR0y', 'go.member@library.com');
INSERT INTO users_roles VALUES (3, 3);

INSERT INTO library_member_type VALUES (null, 21, 'basic', 0.25);
INSERT INTO library_member_type VALUES (null, 21, 'staff', 0.10);
INSERT INTO library_member_type VALUES (null, 42, 'senior', 0.05);