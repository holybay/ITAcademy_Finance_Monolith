INSERT INTO app.currencies (id, title, description, created_at, updated_at)
VALUES (gen_random_uuid(), 'USD', 'United Stated Dollar', now(), now()),
(gen_random_uuid(), 'EUR', 'European Union Mark', now(), now()),
(gen_random_uuid(), 'RUB', 'Russian ruble', now(), now()),
(gen_random_uuid(), 'BYN', 'Belarusian ruble', now(), now());

INSERT INTO app.operation_categories (id, title, created_at, updated_at)
VALUES (gen_random_uuid(), 'Default', now(), now());
