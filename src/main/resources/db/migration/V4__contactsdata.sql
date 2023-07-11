INSERT INTO contact_type(title)
VALUES ('email');

INSERT INTO contacts(value, contact_type_id)
VALUES ('pwosne@gmail.com', 1),
       ('steve128@yandex.ru', 1),
       ('jatpack128@gmail.com', 1);

UPDATE users
SET contact_id = 1
WHERE id = 1;

UPDATE users
SET contact_id = 2
WHERE id = 2;

UPDATE users
SET contact_id = 3
WHERE id = 3;