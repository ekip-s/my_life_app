CREATE TABLE IF NOT EXISTS accounts (
    id uuid DEFAULT gen_random_uuid(),
    account_type SMALLINT,
    currency SMALLINT,
    capitalization SMALLINT,
    opening_date date,
    first_payment_date date,
    last_payment_date date,
    rate numeric(1000, 2),
    payment numeric(1000, 2),
    account_sum_amount numeric(1000, 2),
    deposit_amount numeric(1000, 2),
    last_payment numeric(1000, 2),
    create_dt TIMESTAMP WITHOUT TIME ZONE,
    last_update_dt TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS schedule (
    id uuid DEFAULT gen_random_uuid(),
    account_id uuid,
    schedule_num int,
    pay_amount numeric(1000, 2),
    pay_date date,
    CONSTRAINT pk_schedule PRIMARY KEY (id),
    CONSTRAINT fk_schedule_accounts foreign key (account_id) references accounts
);