/*
 Popolamento DB
 */

-- Popolamento tabella center - 12 centri di recupero per tartarughe marine
INSERT INTO center(name, contact_email, contact_number, province, address, CAP, city) VALUES
('SeaTurtleRescue Center', 'info@seaturtlerescue.com', '1234567890', 'GE', 'Via Mare 1', '16033', 'Genova'),
('OceanCare Center', 'contact@oceancarecenter.org', '2345678901', 'NA', 'Via Oceano 2', '80100', 'Napoli'),
('TurtleHaven Center', 'info@turtlehavencenter.net', '3456789012', 'CA', 'Viale Costiero 3', '09123', 'Cagliari'),
('AquaRehab Center', 'aquarehab@email.com', '4567890123', 'PA', 'Lungomare 4', '90100', 'Palermo'),
('MarineRescue Center', 'rescue@marinerescue.org', '5678901234', 'RC', 'Corso Marittimo 5', '88100', 'Reggio Calabria'),
('BlueWave Center', 'bluewave.contact@gmail.com', '6789012345', 'VE', 'Viale Costiero 6', '30100', 'Venezia'),
('TurtleSafe Center', 'info@turtlesafe.com', '7890123456', 'FI', 'Via Conchiglia 7', '50100', 'Firenze'),
('CoastalCare Center', 'coastalcare@center.net', '8901234567', 'SP', 'Lungomare 8', '16100', 'La Spezia'),
('SeaLife Haven', 'sealifehaven@gmail.com', '9012345678', 'SA', 'Viale Oceanico 9', '34100', 'Salerno'),
('TurtleRescue Center', 'rescue@turtlerescue.org', '0123456789', 'CT', 'Via Marina 10', '95100', 'Catania'),
('PacificRehab Center', 'pacificrehab@center.com', '2345678901', 'GE', 'Corso Pacifico 11', '16100', 'Portofino'),
('SunsetShores Center', 'sunsetshores@email.com', '3456789012', 'CA', 'Viale Tramonto 12', '07100', 'Cagliari');

-- Popolamento tabella employee - 30 impiegati distribuiti tra operatori,
-- tecnici di laboratorio, veterinari e ricercatori.
INSERT INTO employee(name, surname, date_of_birth, ProfileType) VALUES
('Mario', 'Rossi', '1985-03-15', 'V'),
('Anna', 'Bianchi', '1990-06-22', 'R'),
('Luca', 'Verdi', '1988-11-10', 'O'),
('Giulia', 'Neri', '1995-04-30', 'L'),
('Marco', 'Gialli', '1987-08-05', 'V'),
('Elena', 'Marroni', '1992-01-18', 'R'),
('Alessio', 'Blu', '1989-07-12', 'O'),
('Francesca', 'Rosa', '1997-09-25', 'L'),
('Paolo', 'Arancioni', '1986-12-03', 'V'),
('Sara', 'Turchesi', '1993-05-28', 'R'),
('Davide', 'Grigi', '1984-02-08', 'O'),
('Martina', 'Ciano', '1998-08-14', 'L'),
('Simone', 'Indaco', '1985-10-20', 'V'),
('Laura', 'Celesti', '1991-04-16', 'R'),
('Giovanni', 'Ruggine', '1988-07-07', 'O'),
('Valentina', 'Rosa', '1996-11-23', 'L'),
('Roberto', 'Turchese', '1987-06-01', 'V'),
('Elisa', 'Gialloro', '1994-03-12', 'R'),
('Andrea', 'Arancione', '1989-09-08', 'O'),
('Camilla', 'Azzurro', '1990-12-28', 'L'),
('Nicola', 'Verde', '1986-02-17', 'V'),
('Cristina', 'Grigio', '1992-07-19', 'R'),
('Stefano', 'Magenta', '1984-05-09', 'O'),
('Alessandra', 'Bluette', '1993-10-31', 'L'),
('Federico', 'Giallino', '1988-04-26', 'V'),
('Giorgia', 'Turchina', '1995-01-14', 'R'),
('Enrico', 'Aranciato', '1987-08-03', 'O'),
('Beatrice', 'Rosa', '1991-03-22', 'L'),
('Matteo', 'Azzurro', '1986-11-07', 'V'),
('Chiara', 'Viola', '1994-06-18', 'R');

-- Popolamento tabella employment - Costruzione delle relazioni di impiego
INSERT INTO employment(center_id, employee_id) VALUES
('CTR0000001', 'VET0000001'),
('CTR0000002', 'RES0000001'),
('CTR0000003', 'OPR0000001'),
('CTR0000004', 'LBT0000001'),
('CTR0000005', 'VET0000002'),
('CTR0000006', 'RES0000002'),
('CTR0000007', 'OPR0000002'),
('CTR0000008', 'LBT0000002'),
('CTR0000009', 'VET0000003'),
('CTR0000010', 'RES0000003'),
('CTR0000011', 'OPR0000003'),
('CTR0000012', 'LBT0000003'),
('CTR0000001', 'VET0000004'),
('CTR0000002', 'RES0000004'),
('CTR0000003', 'OPR0000004'),
('CTR0000004', 'LBT0000004'),
('CTR0000005', 'VET0000005'),
('CTR0000006', 'RES0000005'),
('CTR0000007', 'OPR0000005'),
('CTR0000008', 'LBT0000005'),
('CTR0000009', 'VET0000006'),
('CTR0000010', 'RES0000006'),
('CTR0000011', 'OPR0000006'),
('CTR0000012', 'LBT0000006'),
('CTR0000001', 'VET0000007'),
('CTR0000002', 'RES0000007'),
('CTR0000003', 'OPR0000007'),
('CTR0000004', 'LBT0000007'),
('CTR0000005', 'VET0000008'),
('CTR0000006', 'RES0000008'),
('CTR0000012', 'VET0000001'),
('CTR0000005', 'RES0000002'),
('CTR0000009', 'OPR0000003'),
('CTR0000004', 'LBT0000004'),
('CTR0000008', 'VET0000005'),
('CTR0000011', 'RES0000006'),
('CTR0000006', 'OPR0000007'),
('CTR0000002', 'LBT0000001'),
('CTR0000007', 'VET0000008'),
('CTR0000001', 'RES0000004'),
('CTR0000010', 'OPR0000002'),
('CTR0000003', 'LBT0000006'),
('CTR0000004', 'VET0000003'),
('CTR0000008', 'RES0000007'),
('CTR0000005', 'OPR0000001'),
('CTR0000009', 'LBT0000002'),
('CTR0000006', 'VET0000002'),
('CTR0000012', 'RES0000005');

-- Popolazione tabella login con password di esempio
INSERT INTO login(employee_ID, hash_password) VALUES
('VET0000001', crypt('vet_password1', gen_salt('bf'))),
('RES0000001', crypt('res_password1', gen_salt('bf'))),
('OPR0000001', crypt('opr_password1', gen_salt('bf'))),
('LBT0000001', crypt('lbt_password1', gen_salt('bf'))),
('VET0000002', crypt('vet_password2', gen_salt('bf'))),
('RES0000002', crypt('res_password2', gen_salt('bf'))),
('OPR0000002', crypt('opr_password2', gen_salt('bf'))),
('LBT0000002', crypt('lbt_password2', gen_salt('bf'))),
('VET0000003', crypt('vet_password3', gen_salt('bf'))),
('RES0000003', crypt('res_password3', gen_salt('bf'))),
('OPR0000003', crypt('opr_password3', gen_salt('bf'))),
('LBT0000003', crypt('lbt_password3', gen_salt('bf'))),
('VET0000004', crypt('vet_password4', gen_salt('bf'))),
('RES0000004', crypt('res_password4', gen_salt('bf'))),
('OPR0000004', crypt('opr_password4', gen_salt('bf'))),
('LBT0000004', crypt('lbt_password4', gen_salt('bf'))),
('VET0000005', crypt('vet_password5', gen_salt('bf'))),
('RES0000005', crypt('res_password5', gen_salt('bf'))),
('OPR0000005', crypt('opr_password5', gen_salt('bf'))),
('LBT0000005', crypt('lbt_password5', gen_salt('bf'))),
('VET0000006', crypt('vet_password6', gen_salt('bf'))),
('RES0000006', crypt('res_password6', gen_salt('bf'))),
('OPR0000006', crypt('opr_password6', gen_salt('bf'))),
('LBT0000006', crypt('lbt_password6', gen_salt('bf'))),
('VET0000007', crypt('vet_password7', gen_salt('bf'))),
('RES0000007', crypt('res_password7', gen_salt('bf'))),
('OPR0000007', crypt('opr_password7', gen_salt('bf'))),
('LBT0000007', crypt('lbt_password7', gen_salt('bf'))),
('VET0000008', crypt('vet_password8', gen_salt('bf'))),
('RES0000008', crypt('res_password8', gen_salt('bf')));

-- Popolamento tabella Tank
INSERT INTO tank(center_ID, capacity) VALUES
('CTR0000001', 5),
('CTR0000001', 8),
('CTR0000001', 12),
('CTR0000002', 3),
('CTR0000002', 10),
('CTR0000003', 7),
('CTR0000004', 15),
('CTR0000004', 4),
('CTR0000004', 9),
('CTR0000005', 6),
('CTR0000005', 11),
('CTR0000005', 2),
('CTR0000006', 8),
('CTR0000007', 14),
('CTR0000007', 1),
('CTR0000008', 10),
('CTR0000009', 3),
('CTR0000009', 5),
('CTR0000010', 12),
('CTR0000011', 9),
('CTR0000012', 7),
('CTR0000012', 13),
('CTR0000012', 6);

-- Tartaruga 1
INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES
(1, 'M', 'Terry', 'Caretta comune');

-- Tartaruga 2
INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES
(2, 'F', 'Shelly', 'Testudo graeca');

-- Tartaruga 3
INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES
(3, 'M', 'Leonardo', 'Chrysemys picta');

-- Tartaruga 4
INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES
(4, 'F', 'Aqua', 'Trachemys scripta');

-- Tartaruga 5
INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES
(1, 'M', 'Michelangelo', 'Emys orbicularis');

-- Tartaruga 6
INSERT INTO turtle(tank_UID, turtle_sex, name, species) VALUES
(2, 'F', 'Tina', 'Kinosternon scorpioides');


-- Cartelle mediche associate alla Tartaruga 1
INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES
('2022-01-15', POINT(41.9028, 12.4964), 'CTR0000001', 'TID000000000001'),
('2022-03-22', POINT(41.9028, 12.4964), 'CTR0000002', 'TID000000000001');

-- Cartelle mediche associate alla Tartaruga 2
INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES
('2022-02-10', POINT(41.9028, 12.4964), 'CTR0000003', 'TID000000000002'),
('2022-04-18', POINT(41.9028, 12.4964), 'CTR0000004', 'TID000000000002');

-- Cartelle mediche associate alla Tartaruga 3
INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES
('2022-01-25', POINT(41.9028, 12.4964), 'CTR0000005', 'TID000000000003'),
('2022-03-30', POINT(41.9028, 12.4964), 'CTR0000006', 'TID000000000003');

-- Cartelle mediche associate alla Tartaruga 4
INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES
('2022-02-18', POINT(41.9028, 12.4964), 'CTR0000007', 'TID000000000004'),
('2022-04-10', POINT(41.9028, 12.4964), 'CTR0000008', 'TID000000000004');

-- Cartelle mediche associate alla Tartaruga 5
INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES
('2022-01-20', POINT(41.9028, 12.4964), 'CTR0000009', 'TID000000000005'),
('2022-03-15', POINT(41.9028, 12.4964), 'CTR0000010', 'TID000000000005');

-- Cartelle mediche associate alla Tartaruga 6
INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES
('2022-02-05', POINT(41.9028, 12.4964), 'CTR0000011', 'TID000000000006'),
('2022-04-05', POINT(41.9028, 12.4964), 'CTR0000012', 'TID000000000006');



-- Esame 1 associato a tartaruga 1 nel centro 1
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000001-000000001', 'C', 'D', 'L', 'N', 'P', 'C', 'N', 'C', '2022-01-15', 'Esame regolare', TRUE);

-- Esame 1 associato a tartaruga 1 nel centro 2
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000002-000000001', 'N', 'C', 'D', 'L', 'P', 'D', 'N', 'D', '2022-03-22', 'Note sulle pinne danneggiate', TRUE);

-- Esame 1 associato a tartaruga 2 nel centro 3
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000003-000000001', 'L', 'L', 'P', 'L', 'P', 'N', 'L', 'L', '2022-03-20', 'Leggeri problemi alla testa', TRUE);

-- Esame 1 associato a tartaruga 2 nel centro 4
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000004-000000001', 'D', 'C', 'N', 'C', 'L', 'C', 'D', 'C', '2022-04-19', 'Problemi con gli occhi', TRUE);

-- Esame 1 associato a tartaruga 3 nel centro 5
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000005-000000001', 'P', 'P', 'N', 'N', 'N', 'N', 'P', 'N', '2022-01-31', 'Nessun problema evidente', TRUE);

-- Esame 1 associato a tartaruga 3 nel centro 6
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000006-000000001', 'N', 'L', 'D', 'P', 'D', 'D', 'L', 'D', '2022-03-30', 'Problemi al collo', TRUE);

-- Esame 1 associato a tartaruga 4 nel centro 7
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000007-000000001', 'N', 'N', 'N', 'N', 'P', 'N', 'N', 'N', '2022-02-18', 'Esame regolare', TRUE);

-- Esame 1 associato a tartaruga 4 nel centro 8
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000008-000000001', 'N', 'C', 'L', 'D', 'P', 'N', 'C', 'C', '2022-04-11', 'Pinne danneggiate', TRUE);

-- Esame 1 e 2 -- Due visite nello stesso giorno -- associate a tartaruga 5 nel centro 9
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000009-000000001', 'N', 'L', 'N', 'N', 'P', 'P', 'P', 'L', '2022-01-20', 'Leggeri problemi alla testa', TRUE);
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000009-000000001', 'N', 'L', 'N', 'N', 'P', 'P', 'P', 'L', '2022-01-20', 'Ancora leggeri problemi alla testa', FALSE);

-- Esame 10 associato a tartaruga 5 nel centro 10
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000010-000000001', 'D', 'P', 'N', 'D', 'L', 'D', 'D', 'D', '2022-03-15', 'Problemi con le pinne', TRUE);

-- Esame 11 associato a tartaruga 6 nel centro 11
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000011-000000001', 'P', 'C', 'N', 'L', 'D', 'L', 'P', 'C', '2022-02-05', 'Problemi agli occhi', TRUE);

-- Esame 12 associato a tartaruga 6 nel centro 12
INSERT INTO Examination (Internal_ID, Head_Status, Eyes_Status, Tail_Status, Fins_Status, Neck_Status, Beak_Status, Nose_Status, AvgHealth, Ex_Date, Vet_Notes, first_visit) VALUES
('CTR0000012-000000001', 'N', 'L', 'L', 'P', 'C', 'D', 'L', 'C', '2022-04-06', 'Problemi al collo', TRUE);



INSERT INTO Measurement(turtle_ID, width, length, weight, m_Date) VALUES
('TID000000000001', 20.1, 25.5, 5.3, '2022-01-15'),
('TID000000000001', 21.2, 26.3, 5.8, '2022-04-25'),
('TID000000000001', 21.5, 27.0, 6.1, '2022-08-10'),
('TID000000000002', 18.8, 22.3, 4.7, '2022-02-20'),
('TID000000000002', 19.0, 22.7, 4.9, '2022-06-05'),
('TID000000000002', 19.5, 23.2, 5.2, '2022-09-15'),
('TID000000000003', 22.3, 28.5, 6.7, '2022-03-10'),
('TID000000000003', 22.8, 29.0, 7.0, '2022-07-20'),
('TID000000000003', 23.1, 29.5, 7.3, '2022-11-30'),
('TID000000000004', 16.5, 20.0, 4.0, '2022-01-25'),
('TID000000000004', 17.0, 20.5, 4.2, '2022-06-05'),
('TID000000000004', 17.5, 21.0, 4.5, '2022-09-15'),
('TID000000000005', 19.8, 23.5, 5.8, '2022-02-10'),
('TID000000000005', 20.0, 24.0, 6.0, '2022-07-20'),
('TID000000000005', 20.5, 24.5, 6.3, '2022-11-30'),
('TID000000000006', 14.2, 18.0, 3.5, '2022-02-25'),
('TID000000000006', 14.5, 18.5, 3.7, '2022-07-05'),
('TID000000000006', 15.0, 19.0, 4.0, '2022-10-15');


-- Creazione di ulteriori tartarughe senza tank_UID
INSERT INTO turtle(turtle_sex, name, species) VALUES
('M', 'Leonardo', 'Chelonia mydas'),
('F', 'Michelangelo', 'Eretmochelys imbricata'),
('M', 'Donatello', 'Dermochelys coriacea'),
('F', 'Raphael', 'Caretta caretta'),
('M', 'Splinter', 'Natator depressus'),
('F', 'April', 'Chelonia mydas'),
('M', 'Shredder', 'Eretmochelys imbricata'),
('F', 'Mona Lisa', 'Caretta caretta'),
('M', 'Bebop', 'Dermochelys coriacea'),
('F', 'Rocksteady', 'Natator depressus'),
('M', 'Casey', 'Chelonia mydas'),
('F', 'Karai', 'Eretmochelys imbricata'),
('M', 'Baxter', 'Dermochelys coriacea'),
('F', 'Irma', 'Natator depressus'),
('M', 'Krang', 'Caretta caretta');