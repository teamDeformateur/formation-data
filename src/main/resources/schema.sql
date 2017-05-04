CREATE TABLE formateur
(
  id bigint NOT NULL,
  dtnais date,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  titre VARCHAR(255),
  dtdebut date,
  dtfin date,
  CONSTRAINT formateur_pk PRIMARY KEY (id)
);

CREATE TABLE eleve
(
  id bigint NOT NULL,
  dtnais date,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  titre VARCHAR(255),
  note real NOT NULL,
  formateur_id bigint,
  CONSTRAINT eleve_pk PRIMARY KEY (id),
  CONSTRAINT fk_formateur FOREIGN KEY (formateur_id)
      REFERENCES formateur (id) 
);