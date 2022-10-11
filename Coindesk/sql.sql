-- 新增新的schema coindesk
create schema coindesk collate utf8mb4_0900_ai_ci;

-- 新增新的table coindesk
create table coindesk
(
    id           bigint auto_increment
        primary key,
    chart_name   varchar(255) null,
    code         varchar(255) null,
    code_name_zh varchar(255) null
);