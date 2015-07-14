INSERT INTO periods VALUES (1, '0001-01-01 08:45:00', '0001-01-01 09:30:00');
INSERT INTO periods VALUES (2, '0001-01-01 09:40:00', '0001-01-01 10:25:00');
INSERT INTO periods VALUES (3, '0001-01-01 10:35:00', '0001-01-01 11:15:00');
INSERT INTO periods VALUES (4, '0001-01-01 11:25:00', '0001-01-01 12:10:00');

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

INSERT INTO shedule VALUES (7, '2015-07-10', 4, 3, 3, 1, NULL);
INSERT INTO shedule VALUES (8, '2015-07-01', 4, 3, 3, 1, NULL);
INSERT INTO shedule VALUES (10, '2015-07-13', 1, 3, 3, 1, NULL);
INSERT INTO shedule VALUES (9, '2015-07-01', 2, 3, 3, 1, NULL);
INSERT INTO shedule VALUES (13, '2015-07-21', 4, 3, 3, 1, NULL);
INSERT INTO shedule VALUES (11, '2015-07-13', 2, 3, 3, 1, NULL);
INSERT INTO shedule VALUES (12, '2015-07-21', 1, 3, 3, 1, 'Упражнение №21');
INSERT INTO shedule VALUES (14, '2015-07-01', 1, 3, 1, 1, 'Упражнение №23');
INSERT INTO shedule VALUES (15, '2015-07-01', 1, 3, 2, 1, 'Упражнение №22');
INSERT INTO shedule VALUES (4, '2015-07-07', 2, 1, 2, 2, NULL);
INSERT INTO shedule VALUES (1, '2015-07-06', 1, 1, 1, 1, 'Упражнение №10');
INSERT INTO shedule VALUES (2, '2015-07-06', 2, 2, 1, 2, 'Упражнение №11');
INSERT INTO shedule VALUES (3, '2015-07-06', 3, 3, 1, 3, NULL);
INSERT INTO shedule VALUES (5, '2015-07-08', 3, 2, 1, 3, NULL);
INSERT INTO shedule VALUES (6, '2015-07-09', 4, 3, 3, 1, 'Написать реферат');

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

INSERT INTO schools VALUES (1, 'Общеобразовательная школа №5', 'Иванов Иван Иванович', 'Школа №5');

INSERT INTO news VALUES (1, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками', 'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (2, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками', 'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (3, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками', 'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (4, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками', 'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (5, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками', 'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');

INSERT INTO grades VALUES (1, '2015-07-06', 1, 1, 1, 11);
INSERT INTO grades VALUES (2, '2015-07-07', 2, 1, 1, 10);
INSERT INTO grades VALUES (3, '2015-07-08', 2, 3, 3, 7);

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