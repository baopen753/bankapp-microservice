create table if not exists `cards` (
    `card_id` int not null primary key auto_increment,
    `mobile_number` varchar(15) not null,
    `card_number` varchar(100) not null,
    `card_type` varchar(100) not null,
    `total_limit` int not null,
    `amount_used` int not null,
    `available_amount` int not null,
    `created_at` date not null,
    `created_by` varchar(20) not null,
    `updated_at` date not null,
    `updated_by` varchar(20) default null
);