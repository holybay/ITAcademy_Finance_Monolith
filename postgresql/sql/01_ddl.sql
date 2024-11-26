CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.users
(
id uuid,
mail character varying NOT NULL,
full_name character varying NOT NULL,
role character varying NOT NULL,
status character varying NOT NULL,
password character varying NOT NULL,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT users_pk PRIMARY KEY (id),
CONSTRAINT users_mail_unq UNIQUE(mail)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;

CREATE TABLE app.currencies
(
id uuid,
title character varying NOT NULL,
description character varying NOT NULL,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT currencies_pk PRIMARY KEY (id),
CONSTRAINT currencies_title_unq UNIQUE(title)
);

ALTER TABLE IF EXISTS app.currencies
    OWNER to postgres;

CREATE TABLE app.operation_categories
(
id uuid,
title character varying NOT NULL,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT operation_categories_pk PRIMARY KEY (id),
CONSTRAINT operation_categories_title_unq UNIQUE(title)
);

ALTER TABLE IF EXISTS app.operation_categories
    OWNER to postgres;