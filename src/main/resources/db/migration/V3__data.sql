INSERT INTO events(title, description, id_user_created)
VALUES ('вечеринка', 'вечеринка в честь дня рождения друга', 2),
       ('пикник', 'закупаемся и едем за город', 3),
       ('игра в футбол', 'товарищеский матч между отделами на работе', 1);

INSERT INTO categories_for_events(title)
VALUES ('праздник'),
       ('спортивное мероприятие'),
       ('игровое мероприятие');

INSERT INTO events_categories(id_category, id_event)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (3, 3),
       (3, 1);