-- ADMIN USER
INSERT INTO nyiltnapp.public.users(id, email, first_name, last_name, mobile_number, password, role, token, username)
VALUES (nextval('users_seq'), 'moldovan.adrienn@student.ms.sapientia.ro', 'Moldovan', 'Adrienn', '0744207361', 'adrienn',
        'ADMIN', '', 'adrienn');
-- OTHER USER
INSERT INTO nyiltnapp.public.users(id, email, first_name, last_name, mobile_number, password, role, token, username)
VALUES (nextval('users_seq'), 'kisspista@gmail.com', 'Kiss', 'Pista', '0744552211', 'kisspista',
        'USER', '', 'kisspista');

-- LOCATION
INSERT INTO nyiltnapp.public.locations(id, city_name, classroom, lat, lng, street_name, street_number) VALUES
(nextval('locations_seq'), 'Koronka', '', 46.52, 24.59, '', '1C');

-- EVENT META + EVENT
INSERT INTO nyiltnapp.public.event_meta(id, description, name) VALUES
( nextval('event_meta_seq'),
 'Come visit the university, get to know every place and fall in love with it', 'Presenting the University');

INSERT INTO nyiltnapp.public.events(id, end_time, link, max_attendee_nr, start_time, event_meta_id, host_user_id, location_id)
VALUES ( nextval('events_seq'), NOW() + interval '1 month', '', 20, now() + interval '1 week', currval('event_meta_seq')
, 1, currval('locations_seq'));

-- SCHOOLS
INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'COLEGIUL NATIONAL "UNIREA" TIRGU MURES', 'UNIREA');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL CU PROGRAM SPORTIV "SZASZ ADALBERT" TIRGU MURES', 'LPS');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'COLEGIUL NATIONAL "MIRCEA ELIADE" SIGHISOARA', 'ME');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'COLEGIUL NATIONAL PEDAGOGIC "MIHAI EMINESCU" TIRGU MURES', 'PEDA');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "AVRAM IANCU" TIRGU MURES', 'AI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "DOMOKOS KAZMER" SOVATA', 'DK');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "ELECTROMURES" TIRGU MURES', 'ELEKTRO');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "GHEORGHE SINCAI" TIRGU MURES', 'SINCAI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "ION VLASIU" TIRGU MURES', 'VLASIU');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "LUCIAN BLAGA" REGHIN', 'LB');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "PETRU MAIOR" REGHIN', 'PM');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "SFANTUL GHEORGHE" SANGEORGIU DE PADURE', 'SG');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEHNOLOGIC "TRAIAN VUIA" TIRGU MURES', 'VUIA');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEOLOGIC ROMANO-CATOLIC " II. RAKOCZI FERENC" TIRGU MURES', 'RAKOCZI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEORETIC "BOCSKAI ISTVAN" MIERCUREA NIRAJULUI', 'BOCSKAI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL TEORETIC "BOLYAI FARKAS" TIRGU MURES', 'BFEL');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL VOCATIONAL DE ARTA TIRGU MURES', 'MUVESZETI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'MS', 'LICEUL VOCATIONAL REFORMAT TIRGU MURES', 'REFI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'COLEGIUL NATIONAL "MARTON ARON" MIERCUREA CIUC', 'MARCI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'COLEGIUL REFORMAT "BACZKAMADARASI KIS GERGELY" ODORHEIU SECUIESC', 'BMKG');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'COLEGIUL TEHNIC "BATTHYANY IGNAC" GHEORGHENI', 'BATTYANYI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL DE ARTE "DR.PALLO IMRE" ODORHEIU SECUIESC', 'PALLO');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL DE ARTE "NAGY ISTVAN" MIERCUREA CIUC', 'NI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL PEDAGOGIC "BENEDEK ELEK" ODORHEIU SECUIESC', 'BE');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "BANYAI JANOS" ODORHEIU SECUIESC', 'BJ');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "EOTVOS JOZSEF" ODORHEIU SECUIESC', 'EJ');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "FOGARASY MIHALY" GHEORGHENI', 'FOGARASI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "GABOR ARON" VLAHITA', 'GA');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "JOANNES KAJONI" MIERCUREA CIUC', 'KAJONI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "KOS KAROLY" MIERCUREA CIUC', 'KOS KAROLY');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "PETOFI SANDOR" DANESTI', 'PETOFI SANDOR');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "PUSKAS TIVADAR" DITRAU', 'PUSKAS TIVADAR');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "SOVER ELEK" JOSENI', 'SOVER ELEK');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "SZEKELY KAROLY" MIERCUREA CIUC', 'SZEKELY KAROLY');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "TIVAI NAGY IMRE" SANMARTIN', 'TNI');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC "VENCZEL JOZSEF" MIERCUREA CIUC', 'VENCZEL');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEHNOLOGIC CORUND', '');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEOLOGIC ROMANO-CATOLIC "SEGITO MARIA" MIERCUREA CIUC', 'SEGITO');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEOLOGIC ROMANO-CATOLIC "SZENT ERZSEBET" LUNCA DE SUS', 'SZENT ERZSEBET');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEOLOGIC UNITARIAN "BERDE MOZES" CRISTURU SECUIESC', 'MOZES');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEORETIC "ORBAN BALAZS" CRISTURU SECUIESC', 'ORBAN BALAZS');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEORETIC "SALAMON ERNO" GHEORGHENI', 'SALAMON');

INSERT INTO nyiltnapp.public.schools (id, county_code, name, short_name)
VALUES ( nextval('schools_seq'), 'HR', 'LICEUL TEORETIC "TAMASI ARON" ODORHEIU SECUIESC', 'TAMASI ARON');