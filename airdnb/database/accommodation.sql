CREATE TABLE accommodation
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id               VARCHAR(255) NOT NULL,
    name                    VARCHAR(255) NOT NULL,
    max_capacity            BIGINT       NOT NULL,
    accommodation_type      BIGINT       NULL,
    accommodation_room_type BIGINT       NULL,
    bedroom_count           BIGINT       NOT NULL,
    bathroom_count          BIGINT       NOT NULL,
    bed_count               BIGINT       NOT NULL,
    per_price               BIGINT       NOT NULL,
    CONSTRAINT fk_accommodation_type FOREIGN KEY (accommodation_type) REFERENCES accommodation_type (id) ON DELETE SET NULL,
    CONSTRAINT fk_accommodation_room_type FOREIGN KEY (accommodation_room_type) REFERENCES accommodation_room_type (id) ON DELETE SET NULL
);