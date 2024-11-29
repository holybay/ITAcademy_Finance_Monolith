\c finance_monolith

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

CREATE TABLE app.accounts
(
id uuid,
title character varying NOT NULL,
description character varying NOT NULL,
balance NUMERIC(13,2) NOT NULL,
type character varying NOT NULL,
currency_id uuid NOT NULL,
user_id uuid NOT NULL,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT accounts_pk PRIMARY KEY (id),
CONSTRAINT accounts_currency_fk  FOREIGN KEY (currency_id) REFERENCES app.currencies(id),
CONSTRAINT accounts_users_fk  FOREIGN KEY (user_id) REFERENCES app.users(id),
CONSTRAINT accounts_title_unq UNIQUE(title)
);

ALTER TABLE IF EXISTS app.accounts
    OWNER to postgres;

CREATE TABLE app.account_operations
(
id uuid,
operation_date TIMESTAMP(3) NOT NULL,
description character varying,
category_id uuid NOT NULL,
amount NUMERIC(13,2) NOT NULL,
currency_id uuid NOT NULL,
account_id uuid NOT NULL,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT acc_operations_pk PRIMARY KEY (id),
CONSTRAINT acc_operations_category_fk  FOREIGN KEY (category_id) REFERENCES app.operation_categories(id),
CONSTRAINT acc_operations_currency_fk  FOREIGN KEY (currency_id) REFERENCES app.currencies(id),
CONSTRAINT acc_operations_accounts_fk  FOREIGN KEY (account_id) REFERENCES app.accounts(id)
);

ALTER TABLE IF EXISTS app.account_operations
    OWNER to postgres;

CREATE TABLE app.audit_units
(
id uuid,
user_id uuid NOT NULL,
text character varying,
type character varying,
essence_type_id uuid NOT NULL,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT audit_pk PRIMARY KEY (id),
CONSTRAINT audit_users_fk  FOREIGN KEY (user_id) REFERENCES app.users(id)
);

ALTER TABLE IF EXISTS app.audit_units
    OWNER to postgres;

CREATE TABLE app.verification_units
(
id uuid,
user_id uuid NOT NULL,
code integer,
created_at TIMESTAMP(3) NOT NULL,
updated_at TIMESTAMP(3) NOT NULL,
CONSTRAINT verification_units_pk PRIMARY KEY (id),
CONSTRAINT verification_units_users_fk  FOREIGN KEY (user_id) REFERENCES app.users(id)
);

ALTER TABLE IF EXISTS app.verification_units
    OWNER to postgres;
