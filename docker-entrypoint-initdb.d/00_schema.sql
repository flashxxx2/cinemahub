CREATE TABLE cinema (
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    attachment TEXT,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name TEXT

)