INSERT INTO subjects VALUES (1, 'History');
INSERT INTO subjects VALUES (2, 'Mathematics');
INSERT INTO subjects VALUES (3, 'English');
INSERT INTO subjects VALUES (4, 'Literature');

INSERT INTO clazzes VALUES (1, '1-А');
INSERT INTO clazzes VALUES (2, '1-Б');
INSERT INTO clazzes VALUES (3, '2-А');
INSERT INTO clazzes VALUES (4, '2-Б');

INSERT INTO roles VALUES (1, 'user');
INSERT INTO roles VALUES (2, 'teacher');
INSERT INTO roles VALUES (3, 'admin');

INSERT INTO users VALUES (1, 'petr', '123', 'Petr', 'Petrov', 'Petrovich');
INSERT INTO users VALUES (2, 'ivan', '123', 'Ivan', 'Ivanov', 'Ivanovich');

INSERT INTO students VALUES (1, 'Vasiliy', 'Petrov', 'Petrovich', '2008-01-07', 1, 1);
INSERT INTO students VALUES (2, 'Tatyana', 'Petrova', 'Petrova', '2008-02-07', 2, 2);
INSERT INTO students VALUES (3, 'Anna', 'Ivanova', 'Ivanovna', '2009-09-04', 2, 3);
INSERT INTO students VALUES (4, 'Nikolay', 'Ivanov', 'Ivanovich', '2009-07-16', 1, 4);

INSERT INTO users_students VALUES (1, 1);
INSERT INTO users_students VALUES (1, 2);
INSERT INTO users_students VALUES (2, 3);
INSERT INTO users_students VALUES (2, 4);

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (2, 2);

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