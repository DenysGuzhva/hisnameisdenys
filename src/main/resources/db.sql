CREATE TABLE car
(
    id                  bigserial
        constraint car_pkey
            primary key,
    model               varchar,
    registration_number varchar(10) NOT NULL UNIQUE
);

CREATE TABLE customer
(
    id                bigserial
        constraint customer_pkey
            primary key,
    full_name         varchar,
    driver_license_id varchar(50) NOT NULL UNIQUE
)
CREATE TABLE car_current_status
(
    id        bigserial
        constraint car_current_status_pkey
            primary key,
    car_id    bigserial,
    status_id bigserial

);
CREATE TABLE excluded_car
(
    id                     bigserial
        constraint excluded_car_pkey
            primary key,
    car_id                 bigserial,
    is_exclusion_permanent boolean DEFAULT false
)
CREATE TABLE car_rent_history
(
    id             bigserial
        constraint car_rent_history_pkey
            primary key,
    car_id         bigserial,
    customer_id    bigserial,
    rent_status_id bigserial
);
CREATE TABLE rent_status
(
    id   bigserial
        constraint rent_status_pkey
            primary key,
    name varchar
);
INSERT INTO rent_status (name)
VALUES ('ACTIVE');
INSERT INTO rent_status (name)
VALUES ('CLOSED');

INSERT INTO car (model, registration_number)
VALUES ('Lexus', 'AX2487AX');
INSERT INTO car (model, registration_number)
VALUES ('Lexus', 'AX2477AX');
INSERT INTO car (model, registration_number)
VALUES ('Lexus', 'AX2407AX');
INSERT INTO car (model, registration_number)
VALUES ('Lexus', 'AX2347AX');

CREATE TABLE car_status
(
    id   bigserial
        constraint status_pkey
            primary key,
    name varchar
);
INSERT INTO car_status (name)
VALUES ('FREE');
INSERT INTO car_status (name)
VALUES ('TAKEN');
INSERT INTO car_status (name)
VALUES ('EXCLUDED');
INSERT INTO car_status (name)
VALUES ('EXCLUDED PERMANENTLY')
    INSERT
INTO car_current_status (car_id, status_id)
VALUES (1, 3);
INSERT INTO car_current_status (car_id, status_id)
VALUES (2, 2);
INSERT INTO car_current_status (car_id, status_id)
VALUES (3, 1);
CREATE TABLE car_rent_status
(
    id   bigserial
        constraint car_rent_status_pkey
            primary key,
    name varchar
);
INSERT INTO car_rent_status (name)
VALUES ('CLOSED');
INSERT INTO car_rent_status (name)
VALUES ('ACTIVE');
INSERT INTO customer (full_name, driver_license_id)
VALUES ('Test Testovich', '34376439DP');
INSERT INTO car_rent_history (car_id, customer_id, rent_status_id)
VALUES (2, 1, 1);
INSERT INTO car_rent_history (car_id, customer_id, rent_status_id)
VALUES (2, 1, 2);