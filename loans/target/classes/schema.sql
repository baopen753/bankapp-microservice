CREATE TABLE IF NOT EXISTS `loans`
(
    `loan_id`            int primary key not null auto_increment,
    `mobile_number`      varchar(15)     not null,
    `loan_number`        varchar(100)    not null,
    `loan_type`          varchar(100)    not null,
    `total_loan`         int             not null,
    `amount_paid`        int             not null,
    `outstanding_amount` int             not null,
    `created_at`         date            not null,
    `created_by`         varchar(20)     not null,
    `updated_at`         date        default null,
    `updated_by`         varchar(20) default null
);