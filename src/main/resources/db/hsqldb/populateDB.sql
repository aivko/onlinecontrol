INSERT INTO subjects VALUES (1, 'History');
INSERT INTO subjects VALUES (2, 'Mathematics');
INSERT INTO subjects VALUES (3, 'English');
INSERT INTO subjects VALUES (4, 'Literature');

INSERT INTO clazzes VALUES (1, '1', 'А', 1);
INSERT INTO clazzes VALUES (2, '1', 'Б', 1);
INSERT INTO clazzes VALUES (3, '2', 'В', 1);
INSERT INTO clazzes VALUES (4, '2', 'Г', 1);

INSERT INTO teachers VALUES (1, 'Mariya', 'Kovalenko', 'Vasilyevna', '2008-01-07', 'FEMALE', 1);
INSERT INTO teachers VALUES (2, 'Olga', 'Dub', 'Valentinovna', '2008-01-07', 'FEMALE', 2);
INSERT INTO teachers VALUES (3, 'Pavel', 'Korotchenko', 'Denisovich', '2008-01-07', 'MALE', 3);

INSERT INTO shedule VALUES (1, '2015-01-01 08:45:00', '2015-01-01 09:30:00', 1, 1, 1);
INSERT INTO shedule VALUES (2, '2015-01-01 08:45:00', '2015-01-01 09:30:00', 1, 2, 2);
INSERT INTO shedule VALUES (3, '2015-01-01 09:30:00', '2015-01-01 10:15:00', 2, 1, 3);
INSERT INTO shedule VALUES (4, '2015-01-01 09:30:00', '2015-01-01 10:15:00', 3, 3, 1);

INSERT INTO roles VALUES (1, 'ROLE_USER', 'User');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN', 'Admin');
INSERT INTO roles VALUES (3, 'ROLE_MANAGER', 'Manager');

INSERT INTO users VALUES (1, 'petr@gmail.com', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', TRUE);
INSERT INTO users VALUES (2, 'ivan@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', TRUE);
INSERT INTO users VALUES (3, 'olga@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', TRUE);
INSERT INTO users VALUES (4, 'igor@gmail.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', TRUE);
INSERT INTO users VALUES (5, 'alex1@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (6, 'alex2@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (7, 'alex3@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (8, 'alex4@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (9, 'alex5@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (10, 'alex6@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (11, 'alex7@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);
INSERT INTO users VALUES (12, 'alex8@gmail.com', 'ac1ab23d6288711be64a25bf13432baf1e60b2bd', TRUE);

INSERT INTO parents VALUES (1, 'Petr', 'Petrov', 'Petrovich', '2008-01-07', 'MALE', 4);
INSERT INTO parents VALUES (2, 'Ivan', 'Ivanov', 'Ivanovich', '2008-01-07', 'MALE', 5);
INSERT INTO parents VALUES (3, 'Olga', 'Ivanova', 'Ivanovna', '2008-01-07', 'FEMALE', 6);
INSERT INTO parents VALUES (4, 'Igor', 'Dub', 'Nikolaevich', '2008-01-07', 'MALE', 7);
INSERT INTO parents VALUES (5, 'Alex', 'Dub', 'Nikolaevich', '2008-01-07', 'MALE', 8);

INSERT INTO students VALUES (1, 'Vasiliy', 'Petrov', 'Petrovich', '2008-01-07', 'MALE', 1, 9);
INSERT INTO students VALUES (2, 'Tatyana', 'Petrova', 'Petrova', '2008-02-07', 'FEMALE', 2, 10);
INSERT INTO students VALUES (3, 'Anna', 'Ivanova', 'Ivanovna', '2009-09-04', 'FEMALE', 3, 11);
INSERT INTO students VALUES (4, 'Nikolay', 'Ivanov', 'Ivanovich', '2009-07-16', 'MALE', 4, null);

INSERT INTO schools VALUES (1, 'School #5', 'Potapenko Potap Potapovich', 'super school');

INSERT INTO news VALUES (1, '2000-01-01', 'open school', 'School #5 was opened', 1);
INSERT INTO news VALUES (2, '2015-09-01', 'new learning year', 'open new learning year',1);

INSERT INTO grades VALUES (1, '2015-04-01', 1, 1, 11);
INSERT INTO grades VALUES (2, '2015-04-03', 1, 1, 10);
INSERT INTO grades VALUES (3, '2015-04-09', 3, 3, 7);

INSERT INTO parents_students VALUES (1, 1);
INSERT INTO parents_students VALUES (1, 2);
INSERT INTO parents_students VALUES (2, 3);
INSERT INTO parents_students VALUES (2, 4);
INSERT INTO parents_students VALUES (3, 1);
INSERT INTO parents_students VALUES (3, 2);

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