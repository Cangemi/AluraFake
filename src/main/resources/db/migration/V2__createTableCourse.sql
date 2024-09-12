CREATE TABLE Course (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name varchar(50) NOT NULL,
    code varchar(10) NOT NULL,
    user_id bigint(20) NOT NULL,  -- Referência ao id da tabela User (instrutor)
    description text NOT NULL,
    status enum('ACTIVE', 'INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
    inactivationDate datetime DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT CHK_Code_Length CHECK (CHAR_LENGTH(code) BETWEEN 4 AND 10),
    CONSTRAINT FK_User FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;