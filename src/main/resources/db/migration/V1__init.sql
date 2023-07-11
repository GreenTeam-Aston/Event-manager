CREATE TABLE contact_type
(
    id    bigserial PRIMARY KEY,
    title varchar(30) not null unique
);

CREATE TABLE contacts
(
    id              bigserial PRIMARY KEY,
    value           varchar(256) not null unique,
    contact_type_id integer     REFERENCES contact_type (id) ON DELETE SET NULL
);

CREATE TABLE user_roles
(
    id    bigserial PRIMARY KEY,
    user_role varchar(30) not null unique
);

CREATE TABLE users
(
    id         bigserial PRIMARY KEY,
    login      varchar(30) not null unique,
    nickname   varchar(80),
    password   varchar(80) not null,
    age        integer,
    gender     varchar(80),
    user_role  integer REFERENCES user_roles (id) ON DELETE SET NULL,
    contact_id integer     REFERENCES contacts (id) ON DELETE SET NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp
);

CREATE TABLE user_friends
(
    id_user   integer REFERENCES users (id) ON DELETE CASCADE,
    id_friend integer REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE roles
(
    id    bigserial PRIMARY KEY,
    title varchar(30) not null unique
);

CREATE TABLE values
(
    id   bigserial PRIMARY KEY,
    name varchar(30) not null
);

CREATE TABLE parameters
(
    id   bigserial PRIMARY KEY,
    name varchar(30) not null
);

CREATE TABLE parameters_value
(
    parameter_id integer REFERENCES parameters (id) ON DELETE CASCADE,
    value_id     integer REFERENCES values (id) ON DELETE CASCADE
);

CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    title       varchar(30) not null unique,
    description varchar(80),
    image       varchar(80) not null,
    price       integer,
    quantity    integer,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp
);

CREATE TABLE product_parameters
(
    product_id   integer REFERENCES products (id) ON DELETE CASCADE,
    parameter_id integer REFERENCES parameters (id) ON DELETE CASCADE
);

CREATE TABLE buckets
(
    id    bigserial PRIMARY KEY,
    name  varchar(30),
    price integer
);

CREATE TABLE product_buckets
(
    id_bucket  integer REFERENCES buckets (id) ON DELETE CASCADE,
    id_product integer REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE user_buckets
(
    id_user   integer REFERENCES users (id) ON DELETE CASCADE,
    id_bucket integer REFERENCES buckets (id) ON DELETE CASCADE
);

CREATE TABLE categories_for_events
(
    id    bigserial PRIMARY KEY,
    title varchar(30) not null unique
);

CREATE TABLE events
(
    id              bigserial PRIMARY KEY,
    title           varchar(80) not null unique,
    description     varchar(1000),
    start_datetime  timestamp,
    end_datetime    timestamp,
    link_event_site varchar(240),
    link_image      varchar(10000),
    price           integer,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp,
    is_active       boolean   default true,
    id_user_created integer REFERENCES users (id),
    tags            varchar(80)
);

CREATE TABLE events_categories
(
    id_category integer REFERENCES categories_for_events (id) ON DELETE CASCADE,
    id_event    integer REFERENCES events (id) ON DELETE CASCADE
);

CREATE TABLE events_photo
(
    id       bigserial PRIMARY KEY,
    url      varchar(240),
    id_event integer REFERENCES events (id) ON DELETE CASCADE
);

CREATE TABLE user_events
(
    id_event integer REFERENCES events (id) ON DELETE CASCADE,
    id_user  integer REFERENCES users (id) ON DELETE CASCADE,
    id_role  integer REFERENCES roles (id) ON DELETE SET NULL
);

CREATE TABLE event_buckets
(
    id_event  integer REFERENCES events (id) ON DELETE CASCADE,
    id_bucket integer REFERENCES buckets (id) ON DELETE CASCADE
);

CREATE TABLE notice_categories
(
    id    bigserial PRIMARY KEY,
    title varchar(240) not null unique
);

CREATE TABLE notices
(
    id                 bigserial PRIMARY KEY,
    msg                varchar(80),
    created_at         timestamp default current_timestamp,
    updated_at         timestamp,
    category_notice_id integer REFERENCES notice_categories (id) ON DELETE SET NULL,
    user_from_id       integer REFERENCES users (id) ON DELETE SET NULL,
    user_to_id         integer REFERENCES users (id) ON DELETE SET NULL,
    event_id           integer REFERENCES events (id) ON DELETE SET NULL
);

