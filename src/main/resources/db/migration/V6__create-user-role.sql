CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (id)
);
