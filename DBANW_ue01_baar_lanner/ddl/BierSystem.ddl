CREATE TABLE Art (
	Brauart varchar(255) NOT NULL, 
	
	PRIMARY KEY (Brauart)
);

CREATE TABLE Beer (
	Name varchar(255) NOT NULL, 
	ArtBrauart varchar(255) NOT NULL, 
	PersonName varchar(255) NOT NULL, 
	BuyDate date, 
	ExpireDate date, 
	ProductionDate date, 
	FK_StorageID int4 NOT NULL, 
	
	PRIMARY KEY (Name)
);

CREATE TABLE Person (
	Name varchar(255) NOT NULL, 
	Gender varchar(1), 
	Nationality varchar(3), 
	Age int4, 
	
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

CREATE TABLE StoragePlace (
	Id SERIAL NOT NULL, 
	FK_Ort varchar(255),
	Address varchar(255),
	
	PRIMARY KEY (Id)
);

CREATE TABLE "Lager Ort_Ort" (
	StorageID int4 NOT NULL, 
	OrtID int4 NOT NULL, 
	
	PRIMARY KEY (StorageID, OrtID)
);

CREATE TABLE Art_Hersteller (
	ArtBrauart varchar(255) NOT NULL, 
	HerstellerName int4 NOT NULL, 
	
	PRIMARY KEY (ArtBrauart, HerstellerName)
);

ALTER TABLE Beer ADD CONSTRAINT FKBeer973618 FOREIGN KEY (ArtBrauart) REFERENCES Art (Brauart);
ALTER TABLE Beer ADD CONSTRAINT FKBeer590868 FOREIGN KEY (PersonName) REFERENCES Person (Name);
ALTER TABLE Ort ADD CONSTRAINT FKOrt18511 FOREIGN KEY (LandName) REFERENCES Land (Name);
ALTER TABLE Beer ADD CONSTRAINT FKBeer696161 FOREIGN KEY ("Lager OrtId") REFERENCES "Lager Ort" (Id);
ALTER TABLE "Lager Ort_Ort" ADD CONSTRAINT "FKLager Ort_774508" FOREIGN KEY (StorageID) REFERENCES "Lager Ort" (Id);
ALTER TABLE "Lager Ort_Ort" ADD CONSTRAINT "FKLager Ort_229833" FOREIGN KEY (OrtID) REFERENCES Ort (PK_Id);
ALTER TABLE Ort ADD CONSTRAINT Vererbung FOREIGN KEY (HerstellerName) REFERENCES Hersteller (Name);
ALTER TABLE Art_Hersteller ADD CONSTRAINT FKArt_Herste47248 FOREIGN KEY (ArtBrauart) REFERENCES Art (Brauart);
ALTER TABLE Art_Hersteller ADD CONSTRAINT FKArt_Herste789672 FOREIGN KEY (HerstellerName) REFERENCES Hersteller (Name);
