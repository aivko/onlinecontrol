INSERT INTO periods VALUES (1, '1970-01-01 08:45:00', '1970-01-01 09:30:00');
INSERT INTO periods VALUES (2, '1970-01-01 09:40:00', '1970-01-01 10:25:00');
INSERT INTO periods VALUES (3, '1970-01-01 10:35:00', '1970-01-01 11:15:00');
INSERT INTO periods VALUES (4, '1970-01-01 11:25:00', '1970-01-01 12:10:00');

INSERT INTO subjects VALUES (1, 'История');
INSERT INTO subjects VALUES (2, 'Математика');
INSERT INTO subjects VALUES (3, 'Английский');
INSERT INTO subjects VALUES (4, 'Литература');

INSERT INTO clazzes VALUES (1, '1', 'А', 1);
INSERT INTO clazzes VALUES (2, '1', 'Б', 1);
INSERT INTO clazzes VALUES (3, '2', 'В', 1);
INSERT INTO clazzes VALUES (4, '2', 'Г', 1);

INSERT INTO shedule VALUES (1, '2015-07-01', 1, 1, 1, 1, 'shedule1');
INSERT INTO shedule VALUES (2, '2015-07-01', 2, 1, 1, 1, 'shedule2');
INSERT INTO shedule VALUES (3, '2015-07-02', 1, 1, 1, 1, 'shedule3');
INSERT INTO shedule VALUES (4, '2015-07-02', 2, 1, 1, 1, 'shedule4');
INSERT INTO shedule VALUES (5, '2015-07-03', 1, 1, 1, 1, 'shedule5');

INSERT INTO roles VALUES (1, 'ROLE_USER',     'Пользователь');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN',    'Администратор');
INSERT INTO roles VALUES (3, 'ROLE_OPERATOR', 'Оператор');

INSERT INTO users VALUES (1, 'petr@gmail.com', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', TRUE);
INSERT INTO users VALUES (2, 'petr2@gmail.com', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', TRUE);
INSERT INTO users VALUES (3, 'petr3@gmail.com', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', TRUE);
INSERT INTO users VALUES (4, 'petr4@gmail.com', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', TRUE);
INSERT INTO users VALUES (5, 'petr5@gmail.com', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', TRUE);

INSERT INTO parents VALUES (1, 'Петр', 'ПЕРВЫЙ',      'Петрович',     '2008-01-07', 'MALE',   2);
INSERT INTO parents VALUES (2, 'Петр', 'ВТОРОЙ',      'Петрович',     '2008-01-07', 'MALE',   3);
INSERT INTO parents VALUES (3, 'Петр', 'ТРЕТИЙ',      'Петрович',     '2008-01-07', 'FEMALE', null);
INSERT INTO parents VALUES (4, 'Петр', 'ЧЕТВЕРТЫЙ',   'Петрович',     '2008-01-07', 'MALE',   null);
INSERT INTO parents VALUES (5, 'Петр', 'ПЯТЫЙ',       'Петрович',     '2008-01-07', 'MALE',   null);

INSERT INTO students VALUES (1, 'Иван', 'ПЕРВЫЙ',      'Иванович',    '2008-01-07', 'MALE',   1, 4);
INSERT INTO students VALUES (2, 'Иван', 'ВТОРОЙ',      'Иванович',    '2008-02-07', 'FEMALE', 1, 5);
INSERT INTO students VALUES (3, 'Иван', 'ТРЕТИЙ',      'Иванович',    '2009-09-04', 'FEMALE', 3, null);
INSERT INTO students VALUES (4, 'Иван', 'ЧЕТВЕРТЫЙ',   'Иванович',    '2009-07-16', 'MALE',   1, NULL);

INSERT INTO teachers VALUES (1, 'Мария', 'Коваленко',  'Васильевна',    '2008-01-07', 'FEMALE',   1);
INSERT INTO teachers VALUES (2, 'Ольга', 'Дуб',        'Валентиновна',  '2008-01-07', 'FEMALE',   NULL);
INSERT INTO teachers VALUES (3, 'Павел', 'Коротченко', 'Денисович',     '2008-01-07', 'MALE',     NULL);

INSERT INTO schools VALUES (1, 'Общеобразовательная школа №5', 'Иванов Иван Иванович', 'Школа №5');

INSERT INTO news VALUES (1, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками',
                         'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (2, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками',
                         'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (3, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками',
                         'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (4, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками',
                         'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');
INSERT INTO news VALUES (5, '2015-06-16', 'Стартовал онлайн проект по контролю за учениками',
                         'Теперь вам совсем не составит труда узнать как ваш ребенок учиться, вовремя ли он приходит в учебное заведение, какие оценки он получил сегодня или за последнюю неделю. Также вы можете оставлять онлайн рекомендации учителям и быть всегда спокойны за свое чадо))');

INSERT INTO grades VALUES (1, 1, 1, 'За стих',              11);
INSERT INTO grades VALUES (2, 1, 2, 'Домашнее задание1',    NULL);
INSERT INTO grades VALUES (3, 1, 2, 'Домашнее задание2',    10);
INSERT INTO grades VALUES (4, 2, 2, 'Домашнее задание3',    null);
INSERT INTO grades VALUES (5, 2, 2, NULL,                   10);
INSERT INTO grades VALUES (6, 3, 1, 'Домашнее задание5',    10);
INSERT INTO grades VALUES (7, 2, 2, 'Уборка в классе',      7);

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