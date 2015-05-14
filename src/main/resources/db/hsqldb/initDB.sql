DROP TABLE users_students IF EXISTS;
DROP TABLE users_roles IF EXISTS;
DROP TABLE teachers_subjects IF EXISTS;
DROP TABLE teachers_clazzes IF EXISTS;

DROP TABLE persistent_logins IF EXISTS;
DROP TABLE students IF EXISTS;
DROP TABLE clazzes IF EXISTS;
DROP TABLE shedule IF EXISTS;
DROP TABLE subjects IF EXISTS;
DROP TABLE roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE teachers IF EXISTS;
DROP TABLE schools IF EXISTS;
DROP TABLE news IF EXISTS;
DROP TABLE grades IF EXISTS;


CREATE TABLE persistent_logins (
  username        VARCHAR(64) NOT NULL,
  series          VARCHAR(64) PRIMARY KEY,
  token           VARCHAR(64) NOT NULL,
  last_used       TIMESTAMP NOT NULL
);

CREATE TABLE clazzes (
  clazz_id        BIGINT IDENTITY PRIMARY KEY,
  name            VARCHAR(80),
  school_id       BIGINT
);
CREATE INDEX clazzes_name ON clazzes (name);

CREATE TABLE shedule (
  shedule_id      BIGINT IDENTITY PRIMARY KEY,
  start_date      DATETIME,
  subject_id      BIGINT NOT NULL,
  clazz_id        BIGINT NOT NULL,
  teacher_id      BIGINT NOT NULL
);
CREATE INDEX shedule_clazz_id ON shedule (clazz_id);

CREATE TABLE subjects (
  subject_id      BIGINT IDENTITY PRIMARY KEY,
  name            VARCHAR(30)
);
CREATE INDEX subjects_name ON subjects (name);

CREATE TABLE roles (
  role_id         BIGINT IDENTITY PRIMARY KEY,
  name            VARCHAR(30),
  description     VARCHAR(30)
);
CREATE INDEX roles_name ON roles (name);

CREATE TABLE users (
  user_id         BIGINT IDENTITY PRIMARY KEY,
  email           VARCHAR(50),
  password        VARCHAR(150),
  first_name      VARCHAR(30),
  last_name       VARCHAR(30),
  middle_name     VARCHAR(30),
  enabled         BOOLEAN
);
CREATE INDEX users_user_id ON users (user_id);

CREATE TABLE students (
  student_id      BIGINT IDENTITY PRIMARY KEY,
  first_name      VARCHAR(30),
  last_name       VARCHAR(30),
  middle_name     VARCHAR(30),
  date_of_birth   DATE,
  gender          VARCHAR(30) NOT NULL,
  clazz_id        BIGINT NOT NULL
);
CREATE INDEX students_student_id ON students (student_id);
ALTER TABLE students ADD CONSTRAINT fk_students_clazzes FOREIGN KEY (clazz_id) REFERENCES clazzes (clazz_id);

CREATE TABLE teachers (
  teacher_id      BIGINT IDENTITY PRIMARY KEY,
  first_name      VARCHAR(30),
  last_name       VARCHAR(30),
  middle_name     VARCHAR(30),
  gender          INTEGER NOT NULL
);
CREATE INDEX teachers_last_name ON teachers (last_name);

CREATE TABLE schools (
  school_id       BIGINT IDENTITY PRIMARY KEY,
  name            VARCHAR(30),
  director        VARCHAR(30),
  description     VARCHAR(30)
);
CREATE INDEX schools_name ON schools (name);

CREATE TABLE news (
  news_id         BIGINT IDENTITY PRIMARY KEY,
  date            DATE,
  topic           VARCHAR(30),
  text            VARCHAR(30),
  school_id       BIGINT NOT NULL
);
CREATE INDEX news_topic ON news (topic);

CREATE TABLE grades (
  grade_id        BIGINT IDENTITY PRIMARY KEY,
  date            DATE,
  subject_id      LONGVARCHAR NOT NULL,
  student_id      LONGVARCHAR NOT NULL,
  mark            INTEGER NOT NULL
);
CREATE INDEX grades_student_id ON grades (student_id);

CREATE TABLE users_students (
  user_id         BIGINT NOT NULL,
  student_id      BIGINT NOT NULL
);
ALTER TABLE users_students ADD CONSTRAINT fk_users_students_users FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE users_students ADD CONSTRAINT fk_users_students_students FOREIGN KEY (student_id) REFERENCES students (student_id);

CREATE TABLE users_roles (
  user_id         BIGINT NOT NULL,
  role_id         BIGINT NOT NULL
);
ALTER TABLE users_roles ADD CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE users_roles ADD CONSTRAINT fk_users_roles_roles FOREIGN KEY (role_id) REFERENCES roles (role_id);

CREATE TABLE teachers_clazzes (
  teacher_id      BIGINT NOT NULL,
  clazz_id        BIGINT NOT NULL
);
ALTER TABLE teachers_clazzes ADD CONSTRAINT fk_teachers_clazzes_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id);
ALTER TABLE teachers_clazzes ADD CONSTRAINT fk_teachers_clazzes_clazzes FOREIGN KEY (clazz_id) REFERENCES clazzes (clazz_id);

CREATE TABLE teachers_subjects (
teacher_id      BIGINT NOT NULL,
subject_id      BIGINT NOT NULL
);
ALTER TABLE teachers_subjects ADD CONSTRAINT fk_teachers_subjects_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id);
ALTER TABLE teachers_subjects ADD CONSTRAINT fk_teachers_subjects_subjects FOREIGN KEY (subject_id) REFERENCES subjects (subject_id);




