INSERT INTO user_roles(user_role)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users(login, nickname, password, age, gender, user_role)
VALUES ('user1', 'peter', '$2a$12$Fz0i8S3MGaywuRiom28aW.D9y0uJSjKkVYnlKvamjp1aPMX0qqp92', 20, 'male', 1),
       ('user2', 'kirill', '$2a$12$Fz0i8S3MGaywuRiom28aW.D9y0uJSjKkVYnlKvamjp1aPMX0qqp92', 22, 'male', 1),
       ('user3', 'anna', '$2a$12$Fz0i8S3MGaywuRiom28aW.D9y0uJSjKkVYnlKvamjp1aPMX0qqp92', 25, 'female', 1),
       ('user4', 'katya', '$2a$12$Fz0i8S3MGaywuRiom28aW.D9y0uJSjKkVYnlKvamjp1aPMX0qqp92', 25, 'female', 1);