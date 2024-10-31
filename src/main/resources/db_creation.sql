CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE _user (
id VARCHAR(50) PRIMARY KEY,
name VARCHAR(50),
creation_table VARCHAR(50)
);

CREATE TABLE ticket (
id VARCHAR(50) PRIMARY KEY,
user_id VARCHAR(50),
ticket_type ticket_type NOT NULL,
creation_table VARCHAR(50),
CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES _user(id) ON DELETE CASCADE
);