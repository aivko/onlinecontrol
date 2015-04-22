INSERT INTO subjects VALUES (1, 'History');
INSERT INTO subjects VALUES (2, 'Mathematics');
INSERT INTO subjects VALUES (3, 'English');
INSERT INTO subjects VALUES (4, 'Literature');

INSERT INTO clazzes VALUES (1, '1-А', 1);
INSERT INTO clazzes VALUES (2, '1-Б', 1);
INSERT INTO clazzes VALUES (3, '2-А', 1);
INSERT INTO clazzes VALUES (4, '2-Б', 1);

INSERT INTO teachers VALUES (1, 'Mariya', 'Kovalenko', 'Vasilyevna', 2);
INSERT INTO teachers VALUES (2, 'Olga', 'Dub', 'Valentinovna', 2);
INSERT INTO teachers VALUES (3, 'Pavel', 'Korotchenko', 'Denisovich', 1);

INSERT INTO shedule VALUES (1, '2015-01-01 08:45:00', 1, 1, 1);
INSERT INTO shedule VALUES (2, '2015-01-01 08:45:00', 1, 2, 2);
INSERT INTO shedule VALUES (3, '2015-01-01 09:30:00', 2, 1, 3);
INSERT INTO shedule VALUES (4, '2015-01-01 09:30:00', 3, 3, 1);

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles VALUES (3, 'ROLE_MANAGER');

INSERT INTO users VALUES (1, 'petr', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Petr', 'Petrov', 'Petrovich');
INSERT INTO users VALUES (2, 'ivan', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Ivan', 'Ivanov', 'Ivanovich');
INSERT INTO users VALUES (3, 'olga', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Olga', 'Ivanova', 'Ivanovna');
INSERT INTO users VALUES (4, 'igor', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'Igor', 'Dub', 'Nikolaevich');
INSERT INTO users VALUES (5, 'alex', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', 'Alex', 'Dub', 'Nikolaevich');

INSERT INTO students VALUES (1, 'Vasiliy', 'Petrov', 'Petrovich', '2008-01-07', 'MALE', 1);
INSERT INTO students VALUES (2, 'Tatyana', 'Petrova', 'Petrova', '2008-02-07', 'FEMALE', 2);
INSERT INTO students VALUES (3, 'Anna', 'Ivanova', 'Ivanovna', '2009-09-04', 'FEMALE', 3);
INSERT INTO students VALUES (4, 'Nikolay', 'Ivanov', 'Ivanovich', '2009-07-16', 'MALE', 4);

INSERT INTO schools VALUES (1, 'School #5', 'Potapenko Potap Potapovich', 'super school');

INSERT INTO news VALUES (1, '2000-01-01', 'open school', 'School #5 was opened', 1);
INSERT INTO news VALUES (2, '2015-09-01', 'new learning year', 'open new learning year',1);

INSERT INTO grades VALUES (1, '2015-04-01', 1, 1, 11);
INSERT INTO grades VALUES (2, '2015-04-03', 1, 1, 10);
INSERT INTO grades VALUES (3, '2015-04-09', 3, 3, 7);

INSERT INTO users_students VALUES (1, 1);
INSERT INTO users_students VALUES (1, 2);
INSERT INTO users_students VALUES (2, 3);
INSERT INTO users_students VALUES (2, 4);
INSERT INTO users_students VALUES (3, 1);
INSERT INTO users_students VALUES (3, 2);

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (2, 1);
INSERT INTO users_roles VALUES (3, 1);
INSERT INTO users_roles VALUES (4, 3);
INSERT INTO users_roles VALUES (5, 2);
INSERT INTO users_roles VALUES (5, 1);


INSERT INTO teachers_subjects VALUES (1, 1);
INSERT INTO teachers_subjects VALUES (1, 2);
INSERT INTO teachers_subjects VALUES (2, 3);
INSERT INTO teachers_subjects VALUES (3, 4);

INSERT INTO teachers_clazzes VALUES (1, 1);
INSERT INTO teachers_clazzes VALUES (1, 2);
INSERT INTO teachers_clazzes VALUES (2, 3);
INSERT INTO teachers_clazzes VALUES (3, 4);