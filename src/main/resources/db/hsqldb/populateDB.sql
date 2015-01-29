INSERT INTO subjects VALUES (1, 'History');
INSERT INTO subjects VALUES (2, 'Mathematics');
INSERT INTO subjects VALUES (3, 'English');
INSERT INTO subjects VALUES (4, 'Literature');

INSERT INTO clazzes VALUES (1, '1-А');
INSERT INTO clazzes VALUES (2, '1-Б');
INSERT INTO clazzes VALUES (3, '2-А');
INSERT INTO clazzes VALUES (4, '2-Б');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles VALUES (3, 'ROLE_MANAGER');

INSERT INTO users VALUES ('petr123', 'petr', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Petr', 'Petrov', 'Petrovich');
INSERT INTO users VALUES ('ivan123', 'ivan', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Ivan', 'Ivanov', 'Ivanovich');
INSERT INTO users VALUES ('olga123', 'olga', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Olga', 'Ivanova', 'Ivanovna');
INSERT INTO users VALUES ('igor123', 'igor', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Igor', 'Dub', 'Nikolaevich');

INSERT INTO students VALUES (1, 'Vasiliy', 'Petrov', 'Petrovich', '2008-01-07', 'MALE', 1);
INSERT INTO students VALUES (2, 'Tatyana', 'Petrova', 'Petrova', '2008-02-07', 'FEMALE', 2);
INSERT INTO students VALUES (3, 'Anna', 'Ivanova', 'Ivanovna', '2009-09-04', 'FEMALE', 3);
INSERT INTO students VALUES (4, 'Nikolay', 'Ivanov', 'Ivanovich', '2009-07-16', 'MALE', 4);

INSERT INTO users_students VALUES ('olga', 1);
INSERT INTO users_students VALUES ('olga', 2);
INSERT INTO users_students VALUES ('petr', 1);
INSERT INTO users_students VALUES ('petr', 2);
INSERT INTO users_students VALUES ('ivan', 3);
INSERT INTO users_students VALUES ('ivan', 4);

INSERT INTO users_roles VALUES ('olga', 1);
INSERT INTO users_roles VALUES ('petr', 1);
INSERT INTO users_roles VALUES ('petr', 2);
INSERT INTO users_roles VALUES ('ivan', 1);
INSERT INTO users_roles VALUES ('igor', 3);

INSERT INTO teachers VALUES (1, 'Mariya', 'Kovalenko', 'Vasilyevna', 2);
INSERT INTO teachers VALUES (2, 'Olga', 'Dub', 'Valentinovna', 2);
INSERT INTO teachers VALUES (3, 'Pavel', 'Korotchenko', 'Denisovich', 1);

INSERT INTO teachers_subjects VALUES (1, 1);
INSERT INTO teachers_subjects VALUES (1, 2);
INSERT INTO teachers_subjects VALUES (2, 3);
INSERT INTO teachers_subjects VALUES (3, 4);

INSERT INTO teachers_clazzes VALUES (1, 1);
INSERT INTO teachers_clazzes VALUES (1, 2);
INSERT INTO teachers_clazzes VALUES (2, 3);
INSERT INTO teachers_clazzes VALUES (3, 4);