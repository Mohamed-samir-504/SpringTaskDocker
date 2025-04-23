INSERT INTO Author(author_id, name, email, birth_Date)
VALUES (1, 'Alice Smith', 'alice@example.com', '1985-04-12'),
       (2, 'Bob Johnson', 'bob@example.com', '1978-09-23'),
       (3, 'Carla Gomez', 'carla@example.com', '1990-01-17');

INSERT INTO Assessment
VALUES
    (1, 'Final exam with 20 MCQs and 2 short answers'),
    (2, 'Project + presentation'),
    (3, 'Midterm + final written assessment');


INSERT INTO Course (id, name, description, assessment_id)
VALUES
    (1, 'Java Basics', 'Introduction to Java programming', 1),
    (2, 'Spring Boot Essentials', 'Build REST APIs using Spring Boot',  2),
    (3, 'Data Structures', 'Study of arrays, linked lists, trees', 3);


INSERT INTO Rating (rating_id, number, course_id)
VALUES
    (1, 9, 1),
    (2, 4, 1),
    (3, 5, 2),
    (4, 8, 3),
    (5, 7, 3);
