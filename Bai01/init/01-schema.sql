-- Chạy một lần khi volume Postgres trống (docker-entrypoint-initdb.d)
CREATE TABLE IF NOT EXISTS demo_users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

INSERT INTO demo_users (name) VALUES
  ('Alice'),
  ('Bob');
