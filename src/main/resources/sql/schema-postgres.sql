CREATE TABLE public.account (
	account_id bigserial NOT NULL,
	balance int8 NOT NULL,
	bank_name varchar(255) NULL,
	description varchar(255) NULL,
	"number" varchar(255) NULL,
	purpose varchar(255) NULL,
	"type" varchar(255) NULL,
	CONSTRAINT account_pkey PRIMARY KEY (account_id)
);

CREATE TABLE public.distribution (
	id bigserial NOT NULL,
	amount int8 NOT NULL,
	purpose varchar(255) NULL,
	transfer_day int4 NOT NULL,
	CONSTRAINT distribution_pkey PRIMARY KEY (id)
);

CREATE TABLE public.fixed_expenditure (
	id bigserial NOT NULL,
	amount int8 NOT NULL,
	institution_name varchar(255) NULL,
	"name" varchar(255) NULL,
	transfer_day int8 NOT NULL,
	account_id int8 NULL,
	CONSTRAINT fixed_expenditure_pkey PRIMARY KEY (id)
);


ALTER TABLE public.fixed_expenditure ADD CONSTRAINT fk7smgfwfh615ybuyl7sxvojpcs FOREIGN KEY (account_id) REFERENCES account(account_id);


CREATE TABLE public.income (
	id bigserial NOT NULL,
	amount int8 NOT NULL,
	income_day int4 NOT NULL,
	"name" varchar(255) NULL,
	account_id int8 NULL,
	CONSTRAINT income_pkey PRIMARY KEY (id)
);


ALTER TABLE public.income ADD CONSTRAINT fkin0g6pdepytwjv6dvcqi45qq3 FOREIGN KEY (account_id) REFERENCES account(account_id);

CREATE TABLE public.saving_account (
	saving_id bigserial NOT NULL,
	amount int8 NOT NULL,
	interest_rate int4 NOT NULL,
	maturity_date date NULL,
	opening_date date NULL,
	transfer_day int4 NOT NULL,
	account_id int8 NULL,
	CONSTRAINT saving_account_pkey PRIMARY KEY (saving_id)
);


ALTER TABLE public.saving_account ADD CONSTRAINT fklwwlw3lowc7ucdci4hddfm1f2 FOREIGN KEY (account_id) REFERENCES account(account_id);

CREATE TABLE public.temp_deposit (
	id bigserial NOT NULL,
	amount int8 NOT NULL,
	interest_rate int4 NOT NULL,
	maturity_date date NULL,
	opening_date date NULL,
	CONSTRAINT temp_deposit_pkey PRIMARY KEY (id)
);