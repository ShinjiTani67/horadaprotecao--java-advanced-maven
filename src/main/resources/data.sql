DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- ADMIN (senha: admin)
INSERT INTO users (id, username, password, role) VALUES (
    '8e5379d4-daf6-4ed7-a934-72a7f8e31ef1',
    '8e5379d4-daf6-4ed7-a934-72a7f8e31ef1',
    '$2a$10$EVKHKJKoPC6vChV2FLHp4OB14eDWHxwW7F3zp8XxZQfZIRjPajOYm', -- "admin"
    'ROLE_ADMIN'
);

-- USER (senha: fernando)
INSERT INTO users (id, username, password, role) VALUES (
    '6e4e19b1-a5f0-4d49-9b75-26b66c9427e2',
    'fernando@email.com',
    '$2a$10$W8QoCSa4ZJGEOmfVQFeU.eWmMz1zPu4OibNUHGl5Xh.pNq0cN5IVK', -- "fernando"
    'ROLE_USER'
);
