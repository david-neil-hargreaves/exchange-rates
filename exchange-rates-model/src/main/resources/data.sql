DROP TABLE IF EXISTS exchange_rate;

DROP TABLE IF EXISTS exchange_rate_history;

DROP TABLE IF EXISTS currency;

DROP TABLE IF EXISTS period;

CREATE TABLE period (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  period_type VARCHAR(20) NOT NULL,
  code VARCHAR(20) NOT NULL,
  description VARCHAR(100) NOT NULL,
  start_date_time DATE NOT NULL,
  end_date_time DATE NOT NULL,
  current_period BOOLEAN NOT NULL
);
 
CREATE TABLE currency (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  code VARCHAR(3) NOT NULL,
  description VARCHAR(100) NOT NULL,
  sort_order_number INT NOT NULL,
  default_subject_currency BOOLEAN NOT NULL,
  default_other_currency BOOLEAN NOT NULL
);

CREATE TABLE exchange_rate (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  buying_currency_id INT NOT NULL,
  selling_currency_id INT NOT NULL,
  start_date_time DATE NOT NULL,
  rate DECIMAL NOT NULL
);

CREATE TABLE exchange_rate_history (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  buying_currency_id INT NOT NULL,
  selling_currency_id INT NOT NULL,
  start_date_time DATE NOT NULL,
  end_date_time DATE NOT NULL,
  rate DECIMAL NOT NULL
);

INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2019011', 'November 2019', '2019-11-01', '2019-11-30', false);
  
INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2019012', 'December 2019', '2019-12-01', '2019-12-31', false);

INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2020001', 'January 2020', '2020-01-01', '2020-01-31', false);
  
INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2020002', 'February 2020', '2020-02-01', '2020-02-29', false); 
  
INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2020003', 'March 2020', '2020-03-01', '2020-03-31', false);

INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2020004', 'April 2020', '2020-04-01', '2020-04-30', false);
  
INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2020005', 'May 2020', '2020-05-01', '2020-05-31', false); 
  
INSERT INTO period (period_type, code, description, start_date_time, end_date_time, current_period) VALUES
  ('CALENDAR_MONTH', '2020006', 'June 2020', '2020-06-01', '2020-06-30', true);

INSERT INTO currency (code, description, sort_order_number, default_subject_currency, default_other_currency) VALUES
  ('EUR', 'Euros', 10, true, false),
  ('HKD', 'Hong Kong Dollars', 40, false, true),
  ('GBP', 'Pounds Sterling', 20, false, true),
  ('USD', 'US Dollars', 30, false, true),
  ('HUF', 'Forints', 50, false, false),
  ('RSD', 'Serbian Dinars', 60, false, false),
  ('FRF', 'French Francs', 70, false, false)
  ;
  
INSERT INTO exchange_rate(buying_currency_id, selling_currency_id, start_date_time, rate)
SELECT buy.id, sell.id, '2020-05-01', 0.89
FROM currency buy, currency sell
WHERE buy.code = 'EUR' 
  AND sell.code = 'GBP'
; 

INSERT INTO exchange_rate(buying_currency_id, selling_currency_id, start_date_time, rate)
SELECT buy.id, sell.id, '2020-01-01', 8.50
FROM currency buy, currency sell
WHERE buy.code = 'EUR' 
  AND sell.code = 'HKD'
; 

INSERT INTO exchange_rate(buying_currency_id, selling_currency_id, start_date_time, rate)
SELECT buy.id, sell.id, '2020-05-01', 1.12
FROM currency buy, currency sell
WHERE buy.code = 'GBP' 
  AND sell.code = 'EUR'
; 

INSERT INTO exchange_rate(buying_currency_id, selling_currency_id, start_date_time, rate)
SELECT buy.id, sell.id, '2020-01-01', 0.12
FROM currency buy, currency sell
WHERE buy.code = 'HKD' 
  AND sell.code = 'EUR'
; 

INSERT INTO exchange_rate_history(buying_currency_id, selling_currency_id, start_date_time, end_date_time, rate)
SELECT buy.id, sell.id, '2019-10-01', '2020-04-30', 0.86
FROM currency buy, currency sell
WHERE buy.code = 'EUR' 
  AND sell.code = 'GBP'
;

INSERT INTO exchange_rate_history(buying_currency_id, selling_currency_id, start_date_time, end_date_time, rate)
SELECT buy.id, sell.id, '2019-09-01', '2019-12-31', 8.23
FROM currency buy, currency sell
WHERE buy.code = 'EUR' 
  AND sell.code = 'HKD'
;  

INSERT INTO exchange_rate_history(buying_currency_id, selling_currency_id, start_date_time, end_date_time, rate)
SELECT buy.id, sell.id, '2020-01-01', '2020-02-29', 8.30
FROM currency buy, currency sell
WHERE buy.code = 'EUR' 
  AND sell.code = 'HKD'
;  

INSERT INTO exchange_rate_history(buying_currency_id, selling_currency_id, start_date_time, end_date_time, rate)
SELECT buy.id, sell.id, '2019-12-31', '2020-03-01', 1.41
FROM currency buy, currency sell
WHERE buy.code = 'EUR' 
  AND sell.code = 'USD'
;  

