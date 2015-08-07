CREATE DATABASE IF NOT EXISTS onlinecontrol CHARACTER SET utf8 COLLATE utf8_unicode_ci;
GRANT ALL PRIVILEGES ON onlinecontrol.* TO root@localhost IDENTIFIED BY 'root';

USE onlinecontrol;

CREATE TABLE IF NOT EXISTS persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS clazzes (
  id        INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  number    VARCHAR(80),
  letter    VARCHAR(80),
  school_id INT(4) UNSIGNED NOT NULL,
  INDEX(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS periods (
  id         INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  start_time TIMESTAMP NOT NULL,
  end_time   TIMESTAMP NOT NULL,
  INDEX(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS shedule (
  id         INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date       DATE   NOT NULL,
  period_id  INT(4) UNSIGNED NOT NULL,
  subject_id INT(4) UNSIGNED NOT NULL,
  clazz_id   INT(4) UNSIGNED NOT NULL,
  teacher_id INT(4) UNSIGNED NOT NULL,
  job        VARCHAR(200),
  INDEX(clazz_id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS subjects (
  id   INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  INDEX(name)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS roles (
  id          INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(30),
  description VARCHAR(30),
  INDEX(name)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS users (
  id       INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email    VARCHAR(50),
  password VARCHAR(150),
  enabled  BOOLEAN,
  INDEX(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS parents (
  id            INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name    VARCHAR(30),
  last_name     VARCHAR(30),
  middle_name   VARCHAR(30),
  date_of_birth DATE,
  gender        VARCHAR(30) NOT NULL,
  user_id       INT(4),
  INDEX(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS students (
  id            INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name    VARCHAR(30),
  last_name     VARCHAR(30),
  middle_name   VARCHAR(30),
  date_of_birth DATE,
  gender        VARCHAR(30) NOT NULL,
  clazz_id      INT(4) UNSIGNED NOT NULL,
  user_id       INT(4),
  INDEX(id),
  FOREIGN KEY (clazz_id) REFERENCES clazzes(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS teachers (
  id            INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name    VARCHAR(30),
  last_name     VARCHAR(30),
  middle_name   VARCHAR(30),
  date_of_birth DATE,
  gender        VARCHAR(30) NOT NULL,
  user_id       INT(4),
  INDEX(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS schools (
  id          INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100),
  director    VARCHAR(100),
  description VARCHAR(500),
  INDEX(name)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS news (
  id    INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date  DATE,
  topic VARCHAR(100),
  text  VARCHAR(1000),
  INDEX(topic)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS grades (
  id         INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  shedule_id INT(4) UNSIGNED NOT NULL,
  student_id INT(4) UNSIGNED NOT NULL,
  task VARCHAR(100),
  mark       INT(4),
  INDEX(student_id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS parents_students (
  parent_id  INT(4) UNSIGNED NOT NULL,
  student_id INT(4) UNSIGNED NOT NULL,
  UNIQUE (parent_id,student_id),
  FOREIGN KEY (parent_id) REFERENCES parents(id),
  FOREIGN KEY (student_id) REFERENCES students(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS users_roles (
  user_id INT(4) UNSIGNED NOT NULL,
  role_id INT(4) UNSIGNED NOT NULL,
  UNIQUE (user_id,role_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS teachers_clazzes (
  teacher_id INT(4) UNSIGNED NOT NULL,
  clazz_id   INT(4) UNSIGNED NOT NULL,
  UNIQUE (teacher_id,clazz_id),
  FOREIGN KEY (teacher_id) REFERENCES teachers(id),
  FOREIGN KEY (clazz_id) REFERENCES clazzes(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS teachers_subjects (
  teacher_id INT(4) UNSIGNED NOT NULL,
  subject_id INT(4) UNSIGNED NOT NULL,
  UNIQUE (teacher_id,subject_id),
  FOREIGN KEY (teacher_id) REFERENCES teachers(id),
  FOREIGN KEY (subject_id) REFERENCES subjects(id)
)engine=InnoDB;




