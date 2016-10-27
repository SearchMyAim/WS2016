CREATE TABLE Art (
	Brauart varchar(255) NOT NULL, 
	PRIMARY KEY (Brauart)
);

CREATE TABLE Bier (
	Name varchar(255) NOT NULL, 
	ArtBrauart varchar(255) NOT NULL, 
	PersonName varchar(255) NOT NULL, 
	Kaufdatum int4, 
	Ablaufdatum int4, 
	"Column" int4, 
	"Lager OrtId" int4 NOT NULL, 
	PRIMARY KEY (Name)
);

CREATE TABLE Person (
	Name varchar(255) NOT NULL, 
	Geschlecht varchar(1), 
	Nationalitaet varchar(3), 
	"Alter" varchar(3), 
	PRIMARY KEY (Name)
);

CREATE TABLE Ort (
	PK_Id SERIAL NOT NULL, 
	LandName varchar(255) NOT NULL, 
	HerstellerName int4 NOT NULL, 
	PRIMARY KEY (PK_Id)
);

CREATE TABLE Land (
	Name varchar(255) NOT NULL, 
	PRIMARY KEY (Name)
);

CREATE TABLE Hersteller (
	Name SERIAL NOT NULL, 
	PRIMARY KEY (Name)
);

CREATE TABLE "Lager Ort" (
	Id SERIAL NOT NULL, 
	PRIMARY KEY (Id)
);

CREATE TABLE "Lager Ort_Ort" (
	"Lager OrtId" int4 NOT NULL, 
	OrtPK_Id int4 NOT NULL, 
	PRIMARY KEY ("Lager OrtId", OrtPK_Id)
);

CREATE TABLE Art_Hersteller (
	ArtBrauart varchar(255) NOT NULL, 
	HerstellerName int4 NOT NULL, 
	PRIMARY KEY (ArtBrauart, HerstellerName)
);

ALTER TABLE Bier ADD CONSTRAINT FKBier973618 FOREIGN KEY (ArtBrauart) REFERENCES Art (Brauart);
ALTER TABLE Bier ADD CONSTRAINT FKBier590868 FOREIGN KEY (PersonName) REFERENCES Person (Name);
ALTER TABLE Ort ADD CONSTRAINT FKOrt18511 FOREIGN KEY (LandName) REFERENCES Land (Name);
ALTER TABLE Bier ADD CONSTRAINT FKBier696161 FOREIGN KEY ("Lager OrtId") REFERENCES "Lager Ort" (Id);
ALTER TABLE "Lager Ort_Ort" ADD CONSTRAINT "FKLager Ort_774508" FOREIGN KEY ("Lager OrtId") REFERENCES "Lager Ort" (Id);
ALTER TABLE "Lager Ort_Ort" ADD CONSTRAINT "FKLager Ort_229833" FOREIGN KEY (OrtPK_Id) REFERENCES Ort (PK_Id);
ALTER TABLE Ort ADD CONSTRAINT Vererbung FOREIGN KEY (HerstellerName) REFERENCES Hersteller (Name);
ALTER TABLE Art_Hersteller ADD CONSTRAINT FKArt_Herste47248 FOREIGN KEY (ArtBrauart) REFERENCES Art (Brauart);
ALTER TABLE Art_Hersteller ADD CONSTRAINT FKArt_Herste789672 FOREIGN KEY (HerstellerName) REFERENCES Hersteller (Name);
