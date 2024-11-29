\c finance_monolith

INSERT INTO app.currencies (id, title, description, created_at, updated_at)
VALUES (gen_random_uuid(), 'USD', 'United Stated Dollar', now(), now()),
(gen_random_uuid(), 'EUR', 'European Union Mark', now(), now()),
(gen_random_uuid(), 'RUB', 'Russian ruble', now(), now()),
(gen_random_uuid(), 'BYN', 'Belarusian ruble', now(), now());

INSERT INTO app.operation_categories (id, title, created_at, updated_at)
VALUES (gen_random_uuid(), 'Default', now(), now());

INSERT INTO app.users (id, mail, full_name, role, status, password, created_at, updated_at)
VALUES (gen_random_uuid(), 'admin@finance.com', 'adminus admin', 'ADMIN', 'ACTIVATED',
'$2a$12$TOmvpka3W8a2lpHundeHge9u8m2Tf1RtuKz4lyoULbPRyqrfUN9SK', now(), now()),

(gen_random_uuid(), 'user@finance.com', 'ordinary user', 'USER', 'ACTIVATED',
'$2a$10$Vulfz0xB4gGbRGNoZSjKXOwV265cebZQfn.2VtU9x9lfXZgb36GY.', now(), now());
