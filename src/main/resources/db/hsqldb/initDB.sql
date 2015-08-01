DROP TABLE users_roles
IF EXISTS;
DROP TABLE parents_students
IF EXISTS;
DROP TABLE teachers_subjects
IF EXISTS;
DROP TABLE teachers_clazzes
IF EXISTS;

DROP TABLE persistent_logins
IF EXISTS;
DROP TABLE users
IF EXISTS;
DROP TABLE parents
IF EXISTS;
DROP TABLE students
IF EXISTS;
DROP TABLE teachers
IF EXISTS;
DROP TABLE roles
IF EXISTS;
DROP TABLE clazzes
IF EXISTS;
DROP TABLE shedule
IF EXISTS;
DROP TABLE subjects
IF EXISTS;
DROP TABLE schools
IF EXISTS;
DROP TABLE periods
IF EXISTS;
DROP TABLE news
IF EXISTS;
DROP TABLE grades
IF EXISTS;


CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);

CREATE TABLE clazzes (
  id        INTEGER IDENTITY PRIMARY KEY,
  number    VARCHAR(80),
  letter    VARCHAR(80),
  school_id INTEGER
);
CREATE INDEX clazzes_id ON clazzes (id);

CREATE TABLE periods (
  id         INTEGER IDENTITY PRIMARY KEY,
  start_time TIMESTAMP NOT NULL,
  end_time   TIMESTAMP NOT NULL
);
CREATE INDEX period_id ON periods (id);

CREATE TABLE shedule (
  id         INTEGER IDENTITY PRIMARY KEY,
  date       DATE   NOT NULL,
  period_id  INTEGER NOT NULL,
  subject_id INTEGER NOT NULL,
  clazz_id   INTEGER NOT NULL,
  teacher_id INTEGER NOT NULL,
  job        VARCHAR(200)
);
CREATE INDEX shedule_clazz_id ON shedule (clazz_id);

CREATE TABLE subjects (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30)
);
CREATE INDEX subjects_name ON subjects (name);

CREATE TABLE roles (
  id          INTEGER IDENTITY PRIMARY KEY,
  name        VARCHAR(30),
  description VARCHAR(30)
);
CREATE INDEX roles_name ON roles (name);

CREATE TABLE users (
  id       INTEGER IDENTITY PRIMARY KEY,
  email    VARCHAR(50),
  password VARCHAR(150),
  enabled  BOOLEAN
);
CREATE INDEX users_id ON users (id);

CREATE TABLE parents (
  id            INTEGER IDENTITY PRIMARY KEY,
  first_name    VARCHAR(30),
  last_name     VARCHAR(30),
  middle_name   VARCHAR(30),
  date_of_birth DATE,
  gender        VARCHAR(30) NOT NULL,
  user_id       INTEGER
);
CREATE INDEX parents_id ON parents (id);
-- ALTER TABLE parents ADD CONSTRAINT fk_parents_users FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE students (
  id            INTEGER IDENTITY PRIMARY KEY,
  first_name    VARCHAR(30),
  last_name     VARCHAR(30),
  middle_name   VARCHAR(30),
  date_of_birth DATE,
  gender        VARCHAR(30) NOT NULL,
  clazz_id      INTEGER      NOT NULL,
  user_id       INTEGER
);
CREATE INDEX students_id ON students (id);
ALTER TABLE students ADD CONSTRAINT fk_students_clazzes FOREIGN KEY (clazz_id) REFERENCES clazzes (id);
-- ALTER TABLE students ADD CONSTRAINT fk_students_users FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE teachers (
  id            INTEGER IDENTITY PRIMARY KEY,
  first_name    VARCHAR(30),
  last_name     VARCHAR(30),
  middle_name   VARCHAR(30),
  date_of_birth DATE,
  gender        VARCHAR(30) NOT NULL,
  user_id       INTEGER
);
CREATE INDEX teachers_id ON teachers (id);
-- ALTER TABLE teachers ADD CONSTRAINT fk_teachers_users FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE schools (
  id          INTEGER IDENTITY PRIMARY KEY,
  name        VARCHAR(100),
  director    VARCHAR(100),
  description VARCHAR(500)
);
CREATE INDEX schools_name ON schools (name);

CREATE TABLE news (
  id    INTEGER IDENTITY PRIMARY KEY,
  date  DATE,
  topic VARCHAR(100),
  text  VARCHAR(1000)
);
CREATE INDEX news_topic ON news (topic);

CREATE TABLE grades (
  id         INTEGER IDENTITY PRIMARY KEY,
  shedule_id INTEGER NOT NULL,
  student_id INTEGER NOT NULL,
  task VARCHAR(100),
  mark       INTEGER
);
CREATE INDEX grades_student_id ON grades (student_id);

CREATE TABLE parents_students (
  parent_id  INTEGER NOT NULL,
  student_id INTEGER NOT NULL
);
ALTER TABLE parents_students ADD CONSTRAINT fk_parents_students_students FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE parents_students ADD CONSTRAINT fk_parents_students_parents FOREIGN KEY (parent_id) REFERENCES parents (id);

CREATE TABLE users_roles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL
);
ALTER TABLE users_roles ADD CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE users_roles ADD CONSTRAINT fk_users_roles_roles FOREIGN KEY (role_id) REFERENCES roles (id);

CREATE TABLE teachers_clazzes (
  teacher_id INTEGER NOT NULL,
  clazz_id   INTEGER NOT NULL
);
ALTER TABLE teachers_clazzes ADD CONSTRAINT fk_teachers_clazzes_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id);
ALTER TABLE teachers_clazzes ADD CONSTRAINT fk_teachers_clazzes_clazzes FOREIGN KEY (clazz_id) REFERENCES clazzes (id);

CREATE TABLE teachers_subjects (
  teacher_id INTEGER NOT NULL,
  subject_id INTEGER NOT NULL
);
ALTER TABLE teachers_subjects ADD CONSTRAINT fk_teachers_subjects_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id);
ALTER TABLE teachers_subjects ADD CONSTRAINT fk_teachers_subjects_subjects FOREIGN KEY (subject_id) REFERENCES subjects (id);




