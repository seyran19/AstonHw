-- Таблица пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL
);
-- Таблица ролей
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       role_name VARCHAR(100) NOT NULL
);
-- Таблица связи many-to-many между пользователями и ролями
CREATE TABLE user_roles (
                            user_id INT REFERENCES users(id),
                            role_id INT REFERENCES roles(id),
                            PRIMARY KEY (user_id, role_id)
);
-- Таблица заказов
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        description VARCHAR(255),
                        user_id INT REFERENCES users(id)
);
-- Таблица деталей заказов (one-to-many связь с заказами)
CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             order_id INT REFERENCES orders(id),
                             product_name VARCHAR(100),
                             quantity INT
);