CREATE TABLE brand (
  brewing_method varchar(255) NOT NULL, 
  PRIMARY KEY (brewing_method)
);  
  
CREATE TABLE beer (
  name            varchar(255) NOT NULL, 
  brewing_method  varchar(255) NOT NULL, 
  person_name     varchar(255) NOT NULL, 
  buy_date        date, 
  expire_date     date, 
  production_date date, 
  storage_id      int4 NOT NULL, 
  PRIMARY KEY (name)
);

CREATE TABLE person (
  name        varchar(255) NOT NULL, 
  gender      varchar(1), 
  nationality varchar(3), 
  age         int4, 
  PRIMARY KEY (name)
);

CREATE TABLE location (
  pk_id        varchar(255) NOT NULL, 
  country_name  varchar(255) NOT NULL, 
  producer_name varchar(255) NOT NULL, 
  PRIMARY KEY (pk_id)
);

CREATE TABLE country (
  name varchar(255) NOT NULL, 
  PRIMARY KEY (name)
);

CREATE TABLE producer (
  name varchar(255) NOT NULL, 
  PRIMARY KEY (name)
);

CREATE TABLE storage (
  pk_id SERIAL NOT NULL, 
  PRIMARY KEY (pk_id)
);

CREATE TABLE storage_location (
  storage_id  int4 NOT NULL, 
  location_id varchar(255) NOT NULL, 
  PRIMARY KEY (storage_id, 
  location_id)
);

CREATE TABLE brand_producer (
  brewing_method varchar(255) NOT NULL, 
  producer_name  varchar(255) NOT NULL, 
  PRIMARY KEY (brewing_method, 
  producer_name)
);

ALTER TABLE beer ADD CONSTRAINT FK_beer_brewing FOREIGN KEY (brewing_method) REFERENCES brand (brewing_method);
ALTER TABLE beer ADD CONSTRAINT FK_beer_person FOREIGN KEY (person_name) REFERENCES person (name);
ALTER TABLE beer ADD CONSTRAINT FK_beer_storage FOREIGN KEY (storage_id) REFERENCES storage (pk_id);

ALTER TABLE location ADD CONSTRAINT FK_location_country FOREIGN KEY (country_name) REFERENCES country (name);
ALTER TABLE location ADD CONSTRAINT FK_location_producer FOREIGN KEY (producer_name) REFERENCES producer (name);

ALTER TABLE storage_location ADD CONSTRAINT FK_stoloc_storage FOREIGN KEY (storage_id) REFERENCES storage (pk_id);
ALTER TABLE storage_location ADD CONSTRAINT FK_stoloc_location FOREIGN KEY (location_id) REFERENCES location (pk_id);

ALTER TABLE brand_producer ADD CONSTRAINT FK_brndprod_method FOREIGN KEY (brewing_method) REFERENCES brand (brewing_method);
ALTER TABLE brand_producer ADD CONSTRAINT FK_brndprod_name FOREIGN KEY (producer_name) REFERENCES producer (name);
