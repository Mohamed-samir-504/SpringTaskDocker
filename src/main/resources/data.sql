INSERT IGNORE INTO author(author_id, name, email, birth_Date)
VALUES (1, 'Alice Smith', 'alice@example.com', '1985-04-12'),
       (2, 'Bob Johnson', 'bob@example.com', '1978-09-23'),
       (3, 'Carla Gomez', 'carla@example.com', '1990-01-17');

INSERT IGNORE INTO assessment
VALUES
    (1, 'Final exam with 20 MCQs and 2 short answers'),
    (2, 'Project + presentation'),
    (3, 'Midterm + final written assessment'),
    (4, 'Final exam with 40 MCQs and 3 short answers');


INSERT IGNORE INTO course (id, name, description, assessment_id)
VALUES
    (1, 'Java Basics', 'Introduction to Java programming', 1),
    (2, 'Spring Boot Essentials', 'Build REST APIs using Spring Boot',  2),
    (3, 'Data Structures', 'Study of arrays, linked lists, trees', 3),
    (4, 'Python', 'Python', 4);

ALTER TABLE course AUTO_INCREMENT = 5;

INSERT IGNORE INTO rating (rating_id, number, course_id)
VALUES
    (1, 9, 1),
    (2, 4, 1),
    (3, 5, 2),
    (4, 8, 3),
    (5, 7, 3);


-- ALTER TABLE rating
-- DROP CONSTRAINT FKRBUQJO7WYI9W281UAEUPNK26M;
--
-- ALTER TABLE rating
--     ADD CONSTRAINT fk_course_rating
--         FOREIGN KEY (course_id)
--             REFERENCES course(id)
--             ON DELETE CASCADE;

