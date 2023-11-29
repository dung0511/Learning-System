CREATE TABLE system_setting
(
	setting_id int PRIMARY KEY AUTO_INCREMENT,
	setting_name varchar(20),
    setting_type varchar(30),
    display_order int,
    setting_status bit
);

CREATE TABLE account (
	account_id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(30) UNIQUE,
    email varchar(50) UNIQUE,
	password varchar(50),
	role int,
    status int,
    full_name varchar(20) character set utf8,
    mobile varchar(20),
	enrolment_date date,
    description text,
	FOREIGN KEY(role) REFERENCES system_setting(setting_id)
);

CREATE TABLE subject (
	subject_id int PRIMARY KEY AUTO_INCREMENT,
	subject_code char(6) UNIQUE NOT NULL,
	subject_name VARCHAR(50),
    subject_des text character set utf8,
    manager int,
    FOREIGN KEY (manager) REFERENCES account(account_id),
    status bit
);

CREATE TABLE class
(
	class_id int PRIMARY KEY AUTO_INCREMENT,
    class_name varchar(50),
    subject_id int,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    semester int,
    FOREIGN KEY (semester) REFERENCES system_setting(setting_id),
    status bit,
    start_date date,
    end_date date,
    start_time time,
    end_time time,
    trainer int,
    FOREIGN KEY (trainer) REFERENCES account(account_id),
    UNIQUE (class_name, subject_id)
);

CREATE TABLE student_class
(
	student_id int,
    FOREIGN KEY (student_id) REFERENCES account(account_id),
    class_id int,

    semester int,
    FOREIGN KEY (semester) REFERENCES system_setting(setting_id),
    subject_id int,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    start_date date,
    end_date date,
    start_time time,
    end_time time,
    PRIMARY KEY (student_id, class_id, subject_id)
);

CREATE TABLE dimension_type
(
    type_id int PRIMARY KEY AUTO_INCREMENT,
    type_name varchar(50),
    subject_id int,
    FOREIGN KEY(subject_id) REFERENCES subject(subject_id),
    status bit
);


CREATE TABLE subject_setting
(
	setting_id int PRIMARY KEY AUTO_INCREMENT,
    subject_id int,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    setting_name varchar(50),
    display_order int,
    description text,
    setting_type ENUM ('Chapter', 'Dimension'),
    created_by int,
    created_at datetime,
    updated_by int,
    updated_at datetime,
    status bit,
    FOREIGN KEY (created_by) REFERENCES account(account_id),
    FOREIGN KEY (updated_by) REFERENCES account(account_id)
);	


CREATE TABLE lesson
(
	lesson_id int PRIMARY KEY AUTO_INCREMENT,
    chapter_id int,
    FOREIGN KEY (chapter_id) REFERENCES subject_setting(setting_id),
    lesson_name varchar(50),
    lesson_type int,
    display_order int,
    video_link varchar(100),
	description text,
    class_id int,
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    created_by int,
    created_at datetime,
    updated_by int,
    updated_at datetime,
    status bit,
    FOREIGN KEY (lesson_type) REFERENCES system_setting(setting_id),
    FOREIGN KEY (created_by) REFERENCES account(account_id),
    FOREIGN KEY (updated_by) REFERENCES account(account_id)
);
CREATE TABLE quiz
(
	quiz_id int PRIMARY KEY AUTO_INCREMENT,
    quiz_name varchar(50),
    chapter_id int,
    FOREIGN KEY (chapter_id) REFERENCES subject_setting(setting_id),
    subject_id int,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    quiz_type int,
    FOREIGN KEY(quiz_type) REFERENCES system_setting(setting_id),
    noQ int,
    class_id int,
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    time_limit real,
    display_order int,
    created_by int,
    created_at datetime,
    updated_by int,
    updated_at datetime,
    description text,
    status bit,
    FOREIGN KEY (created_by) REFERENCES account(account_id),
    FOREIGN KEY (updated_by) REFERENCES account(account_id)
);

CREATE TABLE question
(
	question_id int PRIMARY KEY AUTO_INCREMENT,
    subject_id int,
    chapter_id int,
    topic text,
    display_order int,
    created_by int,
    created_at datetime,
    updated_by int,
    updated_at datetime,
    status bit,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (chapter_id) REFERENCES subject_setting(setting_id),
    FOREIGN KEY (created_by) REFERENCES account(account_id),
    FOREIGN KEY (updated_by) REFERENCES account(account_id)
);

CREATE TABLE question_ans
(
	question_id int,
    FOREIGN KEY (question_id) REFERENCES question(question_id),
    answer_option varchar(10),
    answer_content varchar(100),
    is_key bit,
    PRIMARY KEY (question_id, answer_option)
);


CREATE TABLE question_dimension
(
	question_id int,
    dimension_id int,
    FOREIGN KEY (question_id) REFERENCES question(question_id),
    FOREIGN KEY (dimension_id) REFERENCES dimension_type(type_id),
    PRIMARY KEY (question_id, dimension_id)
);

CREATE TABLE quiz_question
(
	quiz_id int,
    FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id),
    question_id int,
    FOREIGN KEY (question_id) REFERENCES question(question_id),
    display_order int,
    created_by int,
    created_at datetime,
    updated_by int,
    updated_at datetime,
    FOREIGN KEY (created_by) REFERENCES account(account_id),
    FOREIGN KEY (updated_by) REFERENCES account(account_id),
    PRIMARY KEY (quiz_id, question_id)
);

CREATE TABLE student_ans
(
	student_id int,
    FOREIGN KEY (student_id) REFERENCES account(account_id),
    quiz_id int,
    count int,
    question_id int,
    FOREIGN KEY (quiz_id, question_id) REFERENCES quiz_question(quiz_id, question_id),
    answer_option varchar(10),
    FOREIGN KEY (question_id, answer_option) REFERENCES question_ans(question_id, answer_option),
    PRIMARY KEY (student_id, quiz_id, count, question_id)
);

CREATE TABLE assignment
(
    asm_id int PRIMARY KEY AUTO_INCREMENT,
    asm_name varchar(50) character set utf8,
    asm_des text,
    subject_id int,
    chapter_id int,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (chapter_id) REFERENCES subject_setting(setting_id),
    class_id int,
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    lesson_id int,
    FOREIGN KEY (lesson_id) REFERENCES lesson(lesson_id),
    deadline datetime, 
    created_by int,
    created_at datetime,
    updated_by int,
    updated_at datetime,
    status bit,
    FOREIGN KEY (created_by) REFERENCES account(account_id),
    FOREIGN KEY (updated_by) REFERENCES account(account_id)
);

CREATE TABLE assignment_submit
(
	submission_id INT PRIMARY KEY AUTO_INCREMENT,
    asm_id INT,
    account_id INT,
	date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    file_url VARCHAR(255),
    create_by INT,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_by INT,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (asm_id) REFERENCES assignment(asm_id),
    FOREIGN KEY (account_id) REFERENCES account(account_id)

);

CREATE TABLE grade_item
(
	item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name varchar(50),
    student_id int,
    FOREIGN KEY (student_id) REFERENCES account(account_id),
    class_id int, 
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    item_type ENUM('Quiz', 'Assignment'),
    count int,
    grade real,
    notes text,
    date_taken timestamp,
    time_taken real,
    quiz_id int,
    FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id),
    asm_id int,
    FOREIGN KEY (asm_id) REFERENCES assignment(asm_id),
    UNIQUE (student_id, count, grade, quiz_id, asm_id)
);

CREATE TABLE `video_tracking` (
  `account_id` int NOT NULL,
  `lesson_id` int NOT NULL,
  `state` double DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`account_id`,`lesson_id`),
  KEY `lesson_id` (`lesson_id`),
  CONSTRAINT `video_tracking_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `video_tracking_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`)
);

CREATE TABLE class_discussion
(
	discussion_id INT PRIMARY KEY AUTO_INCREMENT,
	account_id int,
    FOREIGN KEY (account_id) REFERENCES account(account_id),
    class_id int,
    FOREIGN KEY (class_id) REFERENCES class(class_id),
    discussion_topic varchar(150),
    discussion_content text,
    discussion_date datetime,
    status bit,
    noVote int,
    UNIQUE (account_id, class_id, discussion_date)
);

CREATE TABLE discussion_comment
(
	discussion_id int,
    FOREIGN KEY (discussion_id) REFERENCES class_discussion(discussion_id),
    account_id int,
    FOREIGN KEY (account_id) REFERENCES account(account_id),
    comment text,
    reply int,
    FOREIGN KEY (reply) REFERENCES account(account_id),
    comment_date datetime,
    noVote int,
    PRIMARY KEY (discussion_id, account_id, comment_date)
);