/*
  CREAZIONE TABELLA CENTER
*/

CREATE TABLE center (
    -- Identificatore univoco del centro (generato dal trigger new_CenterID)
    ID_Center CHAR(10) PRIMARY KEY,

    -- Nome del centro (massimo 50 caratteri, obbligatorio)
    name VARCHAR(50) NOT NULL,

    -- Indirizzo email di contatto (massimo 100 caratteri, obbligatorio) - Legato al vincolo legit_Emails
    contact_email VARCHAR(100) NOT NULL,

    -- Numero di contatto del centro (massimo 20 caratteri)
    contact_number VARCHAR(20),

    -- Provincia del centro (massimo 2 caratteri, obbligatorio)
    province VARCHAR(2) NOT NULL,

    -- Indirizzo fisico del centro (massimo 30 caratteri, obbligatorio)
    address VARCHAR(30) NOT NULL,

    -- Codice di avviamento postale (massimo 5 caratteri, obbligatorio) - Legato al vincolo legit_CAP
    CAP CHAR(5) NOT NULL,

    -- Città del centro (massimo 15 caratteri, obbligatoria)
    city VARCHAR(15) NOT NULL,

    dismissed BOOLEAN DEFAULT FALSE,

/* Legit CAP - Un CAP valido deve contenere 5 caratteri, tutti numerici (Si presuppone un CAP italiano) */
    CONSTRAINT legit_CAP
        CHECK (CAP ~* '^\d{5}$'),
/* Legit Emails – Una mail valida può contenere 100 caratteri in totale, e deve rispettare il formato “**@**.**”, il tutto senza contenere caratteri speciali. */

	CONSTRAINT legit_Email
	CHECK (contact_email ~* '^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$')

);


/* Trigger e functions per la tabella CENTER */

CREATE FUNCTION new_CenterId()
RETURNS TRIGGER AS $$
DECLARE
    counter INT;
    ctr_id VARCHAR;
BEGIN
    -- Caso base: se non esiste alcun ID per il centro, assegna CTR0000001
    IF((SELECT ID_Center FROM center ORDER BY ID_Center LIMIT 1) IS NULL) THEN
        ctr_id := 'CTR0000001';
    ELSE
        -- Caso generico: recupera il centro con id più grande numericamente, cast a INT e assegna il valore trovato ad un counter
    	counter := (substring((SELECT ID_Center FROM center ORDER BY ID_Center DESC LIMIT 1) FROM 4 FOR 7))::INT;

    	/* Il caso che questo FOR copre potrebbe essere considerato superfluo. Anche se database è stato creato ignorando il caso "cancellazione
    	   di un centro", questo FOR commentato risulta obbligatorio per la riassegnazione dei valori già assegnati ad un centro poi cancellato.
    	   C'è da specificare che anche nel caso di dismissione di un centro, il gestore del database avrebbe tutto l'interesse a conservare
    	   la history delle cartelle mediche assegnate al centro dismesso. Se fosse permessa la cancellazione di un centro senza la cancellazione
    	   delle cartelle mediche, si avrebbe una grave violazione del vincolo d'integrità referenziale, assegnando alcune cartelle mediche a un
    	   centro non esistente nel migliore dei casi e ad un centro completamente diverso nel caso peggiore in cui si andasse ad aggiungere un centro
    	   e venisse ad esso assegnato l'id di un vecchio centro dismesso. Nel rispetto di quanto appena scritto, si procede all'aggiunta di
    	   un boolean che controllerà l'operatività di un centro, lasciandolo però in database, e questo FOR viene commentato nell'eventualità in
    	   cui un centro debba essere invece cancellato completamente. Se si decidesse di decommentare questo FOR, si consiglia l'aggiunta di "ON DELETE
    	   CASCADE" alla foreign key che referenzia la tabella CENTER all'interno della tabella MEDICAL_RECORD. */

    	/* FOR i IN 1..counter LOOP
		    -- Se manca un valore (centro cancellato per qualsivoglia motivo) assegna quel valore ed esce dalla funzione
			IF((SELECT ID_Center FROM center WHERE ID_Center = ('CTR' || lpad(i::VARCHAR, 7, '0'))) IS NULL) THEN
				ctr_id := 'CTR' || lpad(i::VARCHAR, 7, '0');
				new.ID_Center := ctr_id;
				RETURN NEW;
			END IF;
	    END LOOP; */

        -- Se l'esecuzione della funzione arriva a questo punto, è ragionevole pensare che esista un ID assegnato ad ogni centro
    	-- fino a CTR{counter}. Di conseguenza, si incrementa di uno il counter e si restituisce l'ID unico così trovato.
        ctr_id := 'CTR' || lpad((counter+1)::VARCHAR, 7, '0');

    END IF;
	    NEW.ID_center := ctr_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER new_Center
BEFORE INSERT ON Center
FOR EACH ROW
EXECUTE FUNCTION new_CenterId();



/*
  CREAZIONE TABELLA EMPLOYEE
*/

-- Definizione del dominio 'Profile', utilizzato dalla tabella 'Employee' per identificare il ruolo di ciascun impiegato
CREATE DOMAIN Profile AS CHAR(1)
    CHECK(VALUE='V' OR VALUE='R' OR VALUE='O' OR VALUE='L');

CREATE TABLE employee (
    -- Identificatore unico del dipendente (Generato dal trigger employeeInsert)
    employee_ID CHAR(10) PRIMARY KEY,

    -- Nome del dipendente (massimo 50 caratteri, obbligatorio)
    name VARCHAR(50) NOT NULL,

    -- Cognome del dipendente (massimo 50 caratteri, obbligatorio)
    surname VARCHAR(50) NOT NULL,

    -- Data di nascita del dipendente (obbligatoria)
    date_of_birth DATE NOT NULL,

    -- Profilo del lavoratore nel centro (Vincolato al dominio 'Profile', obbligatorio)
    ProfileType Profile NOT NULL,

    -- Vincolo per garantire che l'età del dipendente sia almeno 15 anni
    CONSTRAINT legit_Age
        CHECK (date_of_birth <= CURRENT_DATE - INTERVAL '15 years')
);


/* Triggers e Funzioni della tabella Employee */
CREATE FUNCTION loopEmployee(IN profileT CHAR)
RETURNS VARCHAR AS $$
DECLARE
    counter INT;
    final_ID VARCHAR;
    curs2 CURSOR FOR SELECT employee_ID FROM employee WHERE employee_ID LIKE profileT || '%' ORDER BY employee_ID;
BEGIN
    -- Inizializzazione del contatore
    counter := 0;

    -- Scorrimento degli impiegati con identificatori dello stesso ruolo
    FOR recordvar IN curs2 LOOP
        counter := counter + 1;

        -- Se l'identificatore non corrisponde al contatore, vuol dire che l'impiegato possessore di quell'id è stato cancellato.
        -- Si procede dunque alla riassegnazione dell'identificatore all'impiegato che si sta inserendo in database
        IF(substring(recordvar.employee_ID FROM 4 FOR 7)::INT <> counter) THEN
            final_ID := substring(recordvar.employee_ID FROM 1 FOR 3) || lpad(counter::VARCHAR, 7, '0');
            RETURN final_ID;
        END IF;
    END LOOP;

    -- Se nessun identificatore è stato generato all'interno del loop, crea il primo identificatore disponibile
    final_ID := substring((SELECT employee_ID FROM employee WHERE employee_ID LIKE profileT || '%' LIMIT 1) FROM 1 FOR 3) || lpad((counter+1)::VARCHAR, 7, '0');
    RETURN final_ID;
END;
$$ LANGUAGE plpgsql;


CREATE FUNCTION newEmployeeId()
RETURNS TRIGGER AS $$
DECLARE
    emp_id VARCHAR;
BEGIN
    -- Verifica se non ci sono impiegati con l'identificatore specificato
    IF((SELECT employee_ID FROM employee WHERE employee_ID LIKE new.profiletype || '%' ORDER BY employee_ID DESC LIMIT 1) IS NULL) THEN
        -- Se non ci sono impiegati con l'identificatore specificato, crea il primo identificatore disponibile
        CASE new.profiletype
            WHEN 'V' THEN emp_id := 'VET' || lpad('1', 7, '0');
            WHEN 'R' THEN emp_id := 'RES' || lpad('1', 7, '0');
            WHEN 'O' THEN emp_id := 'OPR' || lpad('1', 7, '0');
            WHEN 'L' THEN emp_id := 'LBT' || lpad('1', 7, '0');
        END CASE;

        -- Assegna il nuovo identificatore al nuovo impiegato
        NEW.employee_id := emp_id;
        RETURN NEW;
    END IF;

    -- Se ci sono impiegati con l'identificatore specificato, utilizza la funzione loopEmployee per generare il nuovo identificatore
    emp_ID := loopEmployee(NEW.profiletype);

    -- Concatenazione della stringa a seconda del ProfileType al numero identificativo, con aggiunta di padding laddove necessario
    NEW.employee_ID := emp_id;
    RETURN NEW;
END
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione automaticamente prima dell'inserimento in 'employee'
CREATE TRIGGER new_Employee
BEFORE INSERT ON employee
FOR EACH ROW
EXECUTE FUNCTION newEmployeeId();



/*
CREAZIONE TABELLA PONTE EMPLOYMENT
*/

CREATE TABLE employment (
    -- Identificativo del centro (massimo 10 caratteri, obbligatorio)
    center_ID CHAR(10) NOT NULL,

    -- Identificativo del dipendente (massimo 10 caratteri, obbligatorio)
    employee_ID CHAR(10) NOT NULL,

    -- Vincoli di chiave esterna per garantire l'integrità referenziale con le tabella 'employee' e
    -- 'center', con specifica dell'opzione ON DELETE CASCADE per eliminare automaticamente le
    -- relazioni tra le tabelle nel caso di eliminazione di un centro o di un impiegato dal database
    FOREIGN KEY (center_ID) REFERENCES center(ID_Center) ON DELETE CASCADE,
    FOREIGN KEY (employee_ID) REFERENCES employee(employee_ID) ON DELETE CASCADE
);



/*
CREAZIONE TABELLA LOGIN
*/
CREATE EXTENSION pgcrypto;

CREATE TABLE login (
    -- Identificativo del dipendente (massimo 10 caratteri, obbligatorio)
    employee_ID CHAR(10) NOT NULL,

    -- Hash della password associata al dipendente (lunghezza fissa, 60 caratteri)
    hash_password VARCHAR(60) NOT NULL,

    -- Vincolo per garantire l'unicità dell'identificativo del dipendente
    UNIQUE(employee_ID),

    -- Vincolo di chiave esterna per garantire l'integrità referenziale con la tabella 'employee' e
    -- specifica dell'opzione ON DELETE CASCADE per eliminare automaticamente i login associati
    -- a un impiegato quando l'impiegato è rimosso dal database
    FOREIGN KEY (employee_ID) REFERENCES employee (employee_ID) ON DELETE CASCADE

);

/* Template INSERT e SELECT per la tabella login
INSERT INTO login(employee_ID, hash_password)
VALUES ('VET0000001', crypt('placeholder', gen_salt('bf'))) ;

Validazione di una password inserita:

SELECT * FROM login
WHERE employee_id='VET0000001'
AND password=crypt('password inserita', hash_password);
*/



/*
  CREAZIONE TABELLA TANK
*/
CREATE TABLE tank (
    -- Identificativo unico della vasca (massimo 2 byte, obbligatorio)
    tank_ID INT2 NOT NULL,

    -- Identificativo del centro associato alla vasca (massimo 10 caratteri, obbligatorio)
    center_ID CHAR(10) NOT NULL,

    -- Capacità massima di tartarughe nella vasca (massimo 2 byte, obbligatoria)
    capacity INT2 NOT NULL,

    -- Identificativo unico della vasca (utilizzato come chiave primaria)
    surrogate_tank INT4 PRIMARY KEY,

    -- Vincolo per garantire l'unicità della combinazione di tank_ID e center_ID
    UNIQUE (tank_ID, center_ID),

    -- Vincolo per garantire che la capacità della vasca sia maggiore o uguale a 0
    CONSTRAINT legit_Capacity
        CHECK (capacity >= 0),

    -- Vincolo di chiave esterna per garantire l'integrità referenziale con la tabella 'center' e
    -- specifica l'opzione ON DELETE CASCADE per eliminare automaticamente le vasche associate
    -- a un centro quando il centro viene eliminato
    FOREIGN KEY (center_ID) REFERENCES center(ID_Center) ON DELETE CASCADE
);


CREATE FUNCTION generateTankId()
RETURNS TRIGGER AS $$
DECLARE
    counter INT;
    cursorTankID CURSOR FOR SELECT tank_ID FROM tank WHERE center_ID = NEW.center_ID ORDER BY tank_ID;
BEGIN
    -- Recupero dell'identificatore di una vasca associata al centro specificato dall'utente
    SELECT tank_ID INTO counter FROM tank WHERE center_ID = NEW.center_ID LIMIT 1;

    -- Se non ci sono vasche associate al centro (centro nuovo), imposta il nuovo identificatore a 1
    IF(counter IS NULL) THEN
        NEW.tank_ID := 1;
        RETURN NEW;
    END IF;

    -- Sovrascrittura del risultato non NULL dell'interrogazione precedente
    counter := 0;

    -- Scorrimento delle vasche esistenti associate al centro
    FOR row_record IN cursorTankID LOOP
        counter := counter + 1;
        -- Se l'identificatore corrente non coincide con il contatore, imposta il nuovo identificatore
        IF(row_record.tank_ID <> counter) THEN
            NEW.tank_ID := counter;
            RETURN NEW;
        END IF;
    END LOOP;

    -- Incrementa il contatore e imposta il nuovo identificatore
    counter := counter + 1;
    NEW.tank_ID := counter;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione automaticamente prima dell'inserimento in 'tank'
CREATE TRIGGER tankIdGeneration
BEFORE INSERT ON Tank
FOR EACH ROW
EXECUTE FUNCTION generateTankId();


CREATE FUNCTION tankUIDGeneration()
RETURNS TRIGGER AS $$
DECLARE
    counter INT;
    cursorUniqueTankID CURSOR FOR SELECT surrogate_tank FROM tank ORDER BY surrogate_tank;
BEGIN
    SELECT surrogate_tank INTO counter FROM tank LIMIT 1;

    IF(counter IS NULL) THEN
        NEW.surrogate_tank := 1;
        RETURN NEW;
    END IF;
    
    -- Azzeramento della variabile counter, se non NULL
    counter := 0;

    -- Scorrimento delle vasche esistenti
    FOR rows IN cursorUniqueTankID LOOP
        counter := counter + 1;
        -- Se l'id non coincide con il contatore, imposta come id il numero del contatore
        IF(rows.surrogate_tank <> counter) THEN
            NEW.surrogate_tank := counter;
            RETURN NEW;
        END IF;
    END LOOP;

    NEW.surrogate_tank := (counter + 1);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione automaticamente prima dell'inserimento in 'tank'
CREATE TRIGGER tankUniqueIdGeneration
BEFORE INSERT ON Tank
FOR EACH ROW
EXECUTE FUNCTION tankUIDGeneration();



/*
  CREAZIONE TABELLA TURTLE
*/

-- Creazione del dominio 'sex' per rappresentare il sesso delle tartarughe (Maschio o Femmina)
CREATE DOMAIN sex AS CHAR(1)
    CHECK(VALUE='M' OR VALUE='F');

CREATE TABLE turtle (
    -- Identificativo unico della vasca associata alla tartaruga
    tank_UID INT,

    -- Identificativo del centro associato alla tartaruga
    center_ID CHAR(10),

    -- Identificativo unico della tartaruga (15 caratteri, generato dal trigger new_TurtleID)
    turtle_ID CHAR(15) PRIMARY KEY,

    -- Sesso della tartaruga (Vincolato al dominio 'sex', obbligatorio)
    turtle_sex sex NOT NULL,

    -- Nome della tartaruga (massimo 10 caratteri, obbligatorio)
    name VARCHAR(20) NOT NULL,

    -- Specie della tartaruga (massimo 30 caratteri, obbligatoria)
    species VARCHAR(30) NOT NULL,

    -- Vincoli di chiave esterna per garantire l'integrità referenziale con le tabelle 'tank' e 'center'
    FOREIGN KEY (tank_UID) REFERENCES tank(surrogate_tank),
    FOREIGN KEY (center_ID) REFERENCES center(ID_Center)
);


CREATE FUNCTION tank_Check()
RETURNS TRIGGER AS $$
DECLARE
    -- Dichiarazione di una variabile per memorizzare il conteggio delle tartarughe nella vasca
    turtleCount INT;
BEGIN
    -- Count del numero di tartarughe nella vasca corrente
    turtleCount := (SELECT COUNT(*) FROM turtle WHERE tank_UID = NEW.tank_UID);

    -- Verifica se il numero di tartarughe supera la capacità massima della vasca
    IF turtleCount > (SELECT capacity FROM tank WHERE surrogate_tank = NEW.tank_UID) THEN
        -- Se la capacità è superata, genera un'eccezione
        RAISE EXCEPTION 'Vasca già piena, selezionane una diversa';
    END IF;
    RETURN NEW;
END
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire automaticamente la funzione prima dell'inserimento di una nuova tartaruga
CREATE TRIGGER tank_Consistency
BEFORE INSERT ON turtle
FOR EACH ROW
EXECUTE FUNCTION tank_Check();


CREATE FUNCTION new_TurtleId()
RETURNS TRIGGER AS $$
DECLARE
    trt_number INT;
    trt_id VARCHAR;
BEGIN
    trt_number := substring((SELECT turtle_ID FROM turtle ORDER BY turtle_ID DESC LIMIT 1) FROM 4 FOR 12)::INT;
    IF(trt_number IS NULL) THEN
        trt_id := 'TID' || lpad('1', 12, '0');
    ELSE
        trt_id := 'TID' || lpad((trt_number+1)::VARCHAR, 12, '0');
    END IF;
    NEW.turtle_id := trt_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire automaticamente la funzione prima dell'inserimento di una nuova tartaruga
CREATE TRIGGER new_Turtle
BEFORE INSERT ON turtle
FOR EACH ROW
EXECUTE FUNCTION new_TurtleId();


CREATE FUNCTION automaticCenterId()
RETURNS TRIGGER AS $$
BEGIN
    -- Assegnazione del centro_ID in base alla vasca associata alla tartaruga
    NEW.center_ID := (SELECT center_ID FROM tank WHERE NEW.tank_UID = surrogate_tank);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione automaticamente prima dell'inserimento in 'turtle'
CREATE TRIGGER autoCenterId
BEFORE INSERT ON Turtle
FOR EACH ROW
EXECUTE FUNCTION automaticCenterId();



/*
  CREAZIONE TABELLA MEDICAL_RECORD
*/

CREATE TABLE medical_record (
    -- Identificativo interno del record medico (massimo 20 caratteri, chiave primaria)
    Internal_ID CHAR(20) PRIMARY KEY,

    -- Data di accesso al record medico (obbligatoria)
    access_date DATE NOT NULL,

    -- Dati di localizzazione (tipo 'point', obbligatorio)
    location_data POINT NOT NULL,

    -- Data di rilascio del record medico (opzionale)
    release_date DATE,

    -- Identificativo del centro (massimo 10 caratteri, obbligatorio)
    Center_ID CHAR(10) NOT NULL,

    -- Identificativo della tartaruga associata al record medico (massimo 15 caratteri, obbligatorio)
    Turtle_ID CHAR(15) NOT NULL,

    -- Vincoli di chiave esterna per garantire l'integrità referenziale con le tabelle 'center' e 'turtle'
    FOREIGN KEY(Center_ID) REFERENCES center(ID_Center),
    FOREIGN KEY(Turtle_ID) REFERENCES turtle(Turtle_ID) ON DELETE CASCADE
);


CREATE FUNCTION noRecordModification()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se la data di rilascio del record medico è già impostata
    IF OLD.release_date IS NOT NULL THEN
       -- Se è già chiuso, genera un'eccezione che impedisce la modifica
       RAISE EXCEPTION 'Non puoi modificare una cartella medica chiusa.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione prima dell'aggiornamento di un record medico
CREATE TRIGGER released_Turtle
BEFORE UPDATE ON medical_record
FOR EACH ROW
EXECUTE FUNCTION noRecordModification();


CREATE FUNCTION turtle_Release()
RETURNS TRIGGER AS $$
BEGIN
    -- Aggiorna le informazioni sulla tartaruga impostando 'center_ID' e 'tank_UID' a 'NULL'
    UPDATE turtle
    SET center_ID = 'NULL', tank_UID = 'NULL'
    WHERE turtle_ID = OLD.Turtle_ID;
END
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire automaticamente la funzione prima dell'aggiornamento della data di rilascio
CREATE TRIGGER autoRelease
AFTER UPDATE OF release_date ON medical_record
FOR EACH ROW
EXECUTE FUNCTION turtle_Release();


CREATE FUNCTION internalIdGen()
RETURNS TRIGGER AS $$
DECLARE
    -- Dichiarazione di variabili per memorizzare l'ultimo identificatore e il nuovo identificatore
    lastId VARCHAR(20);
    finalId VARCHAR(20);
    finalNumber INT;
BEGIN
    -- Ottiene l'ultimo identificatore interno associato al centro corrente
    SELECT Internal_ID INTO lastId FROM medical_record
    WHERE Internal_ID LIKE concat(NEW.Center_ID,'-','%')
    ORDER BY Internal_ID DESC LIMIT 1;

    -- Verifica se non ci sono identificatori precedenti per il centro corrente
    IF lastId IS NULL
        THEN finalId := NEW.Center_ID || '-' || lpad(1::VARCHAR, 9, '0');
    ELSE
        -- Estrae il numero finale dall'ultimo identificatore e incrementa di 1
        finalNumber := CAST(substring(lastId FROM 12 for 9) AS INT);
        finalNumber := finalNumber + 1;

        -- Crea il nuovo identificatore concatenando il numero incrementato
        finalId := NEW.Center_ID || '-' || lpad(finalNumber::VARCHAR, 9, '0');
    END IF;

    -- Assegna il nuovo identificatore interno al record medico in inserimento
    NEW.Internal_ID := finalId;

    -- Restituisce la nuova riga
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire automaticamente la funzione prima dell'inserimento di un nuovo record medico
CREATE TRIGGER newInternalID
BEFORE INSERT ON medical_record
FOR EACH ROW
EXECUTE FUNCTION internalIdGen();



/*
  CREAZIONE TABELLA EXAMINATION
*/

-- Creazione del dominio 'medcondition' per rappresentare le condizioni mediche
CREATE DOMAIN medcondition AS CHAR(1)
	CHECK(VALUE='C' OR VALUE='D' OR VALUE='L' OR VALUE='N' OR VALUE='P');

CREATE TABLE Examination(
    -- Identificativo interno della cartella medica (massimo 20 caratteri, obbligatorio)
	Internal_ID CHAR(20) NOT NULL,

    -- Condizioni delle parti della tartaruga (condizione medica, obbligatoria)
	Head_Status medcondition NOT NULL,
	Eyes_Status medcondition NOT NULL,
	Tail_Status medcondition NOT NULL,
	Fins_Status medcondition NOT NULL,
	Neck_Status medcondition NOT NULL,
	Beak_Status medcondition NOT NULL,
	Nose_Status medcondition NOT NULL,

    -- Salute media complessiva (condizione medica, obbligatoria)
	AvgHealth medcondition NOT NULL,

    -- Data dell'esame (obbligatoria, valore predefinito: data corrente)
	Ex_Date DATE NOT NULL DEFAULT CURRENT_DATE,

    -- Note del veterinario (testo libero)
	Vet_Notes TEXT,

    -- Flag per indicare se è la prima visita (obbligatorio, valore predefinito: FALSE)
	first_visit BOOLEAN NOT NULL DEFAULT FALSE,

    -- Vincolo di chiave esterna per garantire l'integrità referenziale con la tabella 'medical_record'
	FOREIGN KEY(Internal_ID) REFERENCES medical_record(Internal_ID) ON DELETE CASCADE
);


CREATE FUNCTION check_Examination()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se la data dell'esame è precedente alla data di accesso al record medico
	IF (NEW.Ex_Date < (SELECT Access_Date FROM medical_record WHERE internal_id = NEW.Internal_ID))
		-- Oppure se la data di rilascio del record medico è già impostata
		OR ((SELECT Release_Date FROM medical_record WHERE internal_id = NEW.Internal_ID) IS NOT NULL)
	THEN
		-- Se una delle condizioni non è soddisfatta, genera un'eccezione
		RAISE EXCEPTION 'Errore: Data non consistente con il database';
	END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione prima dell'inserimento di un nuovo esame
CREATE TRIGGER exams_Consistency
BEFORE INSERT ON Examination
FOR EACH ROW
EXECUTE FUNCTION check_Examination();


CREATE FUNCTION updateNoModify()
RETURNS TRIGGER AS $$
BEGIN
	-- Verifica se la data corrente è successiva alla data di rilascio del record medico associato
	IF CURRENT_DATE > (SELECT Release_Date FROM medical_record WHERE internal_id = OLD.Internal_ID)
		-- Se la condizione è soddisfatta, genera un'eccezione
		THEN RAISE EXCEPTION 'Non puoi modificare una visita una volta inserita.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione prima dell'aggiornamento di un esame medico
CREATE TRIGGER exams_Update
BEFORE UPDATE ON Examination
FOR EACH ROW
EXECUTE FUNCTION updateNoModify();


CREATE FUNCTION oneFirstVisit()
RETURNS TRIGGER AS $$
BEGIN
	-- Verifica se esiste già un esame medico con 'first_visit' impostato a TRUE per la stessa cartella medica
	IF ((NEW.first_visit = TRUE) AND (SELECT Internal_ID FROM Examination WHERE first_visit=TRUE AND Internal_ID = NEW.Internal_ID LIMIT 1) IS NOT NULL)
		-- Se la condizione è soddisfatta, genera un'eccezione
		THEN RAISE EXCEPTION 'La prima visita è unica per ogni cartella medica.';
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione prima dell'inserimento di un nuovo esame medico
CREATE TRIGGER maxOneFirstVisit
BEFORE INSERT OR UPDATE OF first_visit ON Examination
FOR EACH ROW
EXECUTE FUNCTION oneFirstVisit();



CREATE TABLE Measurement(
    -- Identificativo della tartaruga associata alla misurazione (massimo 15 caratteri, obbligatorio)
    turtle_ID CHAR(15) NOT NULL,

    -- Larghezza della tartaruga (valore a virgola mobile, obbligatorio)
    width float4 NOT NULL,

    -- Lunghezza della tartaruga (valore a virgola mobile, obbligatorio)
    length float4 NOT NULL,

    -- Peso della tartaruga (valore a virgola mobile, obbligatorio)
    weight float4 NOT NULL,

    -- Data della misurazione (obbligatoria, valore predefinito: data corrente)
    m_Date DATE NOT NULL DEFAULT CURRENT_DATE,

    -- Vincolo di chiave esterna per garantire l'integrità referenziale con la tabella 'turtle'
    FOREIGN KEY(Turtle_ID) REFERENCES turtle(Turtle_ID) ON DELETE CASCADE
);


CREATE FUNCTION check_Measurements()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se la data della misurazione da aggiornare è diversa dalla data corrente
	IF OLD.m_Date <> CURRENT_DATE THEN
		-- Se la condizione è soddisfatta, genera un'eccezione
		RAISE EXCEPTION 'Non puoi modificare una misurazione in una data diversa. Crearne una nuova';
	END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creazione di un trigger per eseguire la funzione prima dell'aggiornamento di una misurazione
CREATE TRIGGER appraisal_Update
BEFORE UPDATE ON Measurement
FOR EACH ROW
EXECUTE FUNCTION check_Measurements();


-- CREAZIONE FUNZIONI A FINI STATISTICI

CREATE FUNCTION MonthlyStats(
	IN month_to_get INT,
	IN year_to_get INT,
	OUT total_turtles INT,
	OUT compromised_t INT,
	OUT deepwounded_t INT,
	OUT lightwounded_t INT,
	OUT normal_t INT,
	OUT perfect_t INT
) AS $$
BEGIN
	total_turtles := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(MONTH FROM Ex_Date) = month_to_get
					  AND EXTRACT(YEAR FROM Ex_Date) = year_to_get);

	compromised_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(MONTH FROM Ex_Date) = month_to_get
					  AND EXTRACT(YEAR FROM Ex_Date) = year_to_get AND AvgHealth = 'C');

	deepwounded_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(MONTH FROM Ex_Date) = month_to_get
					  AND EXTRACT(YEAR FROM Ex_Date) = year_to_get AND AvgHealth = 'D');

	lightwounded_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(MONTH FROM Ex_Date) = month_to_get
					  AND EXTRACT(YEAR FROM Ex_Date) = year_to_get AND AvgHealth = 'L');

	normal_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(MONTH FROM Ex_Date) = month_to_get
					  AND EXTRACT(YEAR FROM Ex_Date) = year_to_get AND AvgHealth = 'N');

	perfect_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(MONTH FROM Ex_Date) = month_to_get
					  AND EXTRACT(YEAR FROM Ex_Date) = year_to_get AND AvgHealth = 'P');
END;
$$ language plpgsql;



CREATE FUNCTION YearlyStats(
	IN year_to_get INT,
	OUT total_turtles INT,
	OUT compromised_t INT,
	OUT deepwounded_t INT,
	OUT lightwounded_t INT,
	OUT normal_t INT,
	OUT perfect_t INT
) AS $$
BEGIN
	total_turtles := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(YEAR FROM Ex_Date) = year_to_get);

	compromised_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(YEAR FROM Ex_Date) = year_to_get
					  AND AvgHealth = 'C');

	deepwounded_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(YEAR FROM Ex_Date) = year_to_get
					  AND AvgHealth = 'D');

	lightwounded_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(YEAR FROM Ex_Date) = year_to_get
					  AND AvgHealth = 'L');

	normal_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(YEAR FROM Ex_Date) = year_to_get
					  AND AvgHealth = 'N');

	perfect_t := (SELECT COUNT(*) FROM examination WHERE
					  first_visit = TRUE AND EXTRACT(YEAR FROM Ex_Date) = year_to_get
					  AND AvgHealth = 'P');
END;
$$ language plpgsql;


-- Template dell’INSERT nella tabella Center:
-- INSERT INTO center(name, contact_email, contact_number, province, address, CAP, city) VALUES ();

-- Template dell'INSERT nella tabella Employee:
-- INSERT INTO employee(name, surname, date_of_birth, ProfileType) VALUES ();

-- Template dell'INSERT nella tabella Employment:
-- INSERT INTO employment(center_ID, employee_ID) VALUES();

/* Template INSERT e SELECT per la tabella login
INSERT INTO login(employee_ID, hash_password)
VALUES ('VET0000001', crypt('placeholder', gen_salt('bf'))) ;

Validazione di una password inserita:

SELECT * FROM login
WHERE employee_id='VET0000001'
AND password=crypt('password inserita', hash_password);
*/

-- Template dell'INSERT nella tabella Tank:
-- INSERT INTO TANK(center_ID, capacity) VALUES();

-- Template di un INSERT nella tabella Turtle:
--INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES()

-- Template di un INSERT nella tabella Medical_record
-- INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES(..., POINT(...), ..., ...)

-- Template di un INSERT nella tabella Examination
-- INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES ();

-- Template INSERT Measurement
-- INSERT INTO Measurement(turtle_ID, width, length, weight, m_Date) VALUES()
