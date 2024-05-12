CREATE TABLE book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author_id BIGINT,
    publisher_id BIGINT,
    publicationTime TIME,
    category_id BIGINT,
    description TEXT,
    available BOOLEAN,

    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);