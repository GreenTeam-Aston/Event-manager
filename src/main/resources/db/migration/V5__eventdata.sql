INSERT INTO events(title, description, id_user_created, link_event_site, start_datetime)
VALUES ('вечеринка', 'вечеринка в честь дня рождения друга', 2, 'Санкт-Петербург_улица_Возрождения_20A', '2023-07-12T10:15:30'),
       ('пикник', 'закупаемся и едем за город', 3, 'Санкт-Петербуг_аллея_Поликарпова_10к1', '2023-07-13T16:40:30'),
       ('игра в футбол', 'товарищеский матч между отделами на работе', 1, 'Санкт-Петербург_улица_Садовая_10', '2023-07-14T14:45:30');

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

INSERT INTO roles(title)
VALUES ('participant'),
       ('owner');

INSERT INTO user_events(id_event, id_user, id_role)
VALUES (1, 1, 1),
       (1, 2, 2),
       (1, 3, 1);