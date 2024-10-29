CREATE TABLE IF NOT EXISTS users
(
    id           UUID                  DEFAULT uuid_generate_v4() PRIMARY KEY,
    cpf          VARCHAR(11)  NOT NULL UNIQUE,
    name         VARCHAR(255) NOT NULL,
    password     VARCHAR(255)  NOT NULL,
    birth_date   DATE         NOT NULL,
    street       VARCHAR(255) NULL,
    number       VARCHAR(10)  NULL,
    complement   VARCHAR(255) NULL,
    neighborhood VARCHAR(255) NULL,
    city         VARCHAR(255) NULL,
    state        CHAR(2)      NULL,
    zip_code     VARCHAR(8)   NULL,
    status       VARCHAR(20)  NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by   UUID         NULL REFERENCES users (id) ON DELETE SET NULL,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by   UUID         NULL REFERENCES users (id) ON DELETE SET NULL,
    deleted_at   TIMESTAMP    NULL,
    deleted_by   UUID         NULL REFERENCES users (id) ON DELETE SET NULL
);