# NyiltnApp

## Alkalmazás alapötlete
A megvalósítandó alkalmazás a Sapientián megrendezett nyílt napok alkalmával történő eseménysorozat regisztrációs folyamatát segíti, valamint részvételi statisztikákat hivatott kimutatni. Egy ilyen esemény több napos időintervallumban, változatos helyszíneken kerül megszervezésre. Az eseményt megelőzően, a szervezőknek szükséges tudniuk, hogy kb. hány érdeklődő van, emiatt szükséges egy regisztációs ívet kitölteni. Ebben olyan adatokat kell a jelentkezőknek kitölteni, mint: név, email-cím, tanár vagy diák, osztály (diák esetén), iskola. Az iskolákat egy legördülő listából lesz lehetőség kiválasztani, annak érdekében hogy az adatok egységesek legyenek, és ezeket fel lehessen használni statisztikák készítésére.

## Technikai megközelítés
* ### Architektúra
### Backend:
 - Java Spring (JPA, postgresql)
### Frontend:
 - Webes kliens (Angular)
### Szerepkör:
 - admin (szervező)
 - jelentkező/résztvevő
## Funkcionalitások:
 - Regisztráció + Login
 - Statisztika vizualizáció, honnan hányan regisztráltak, iskolák, megyék, melyek a legfelkapottabb események
 - Esemény regisztráció esetén, email-t kapni pontos részletekről
 - Eseményekre CRUD műveletek
 - Regisztrálás és leiratkozás eseményről
 - Részletek megtekintése
 - Elfelejtett jelszó visszaállítása
 
 ## Továbbfejlesztési lehetőségek
 - térképes vizualizáció
 - Emlékeztető értesítés esemény kezdete előtt x idővel
 - Térképes útvonal megnyitása amennyiben adott esemény helyszíne felé tart
 - QR-kód leolvasásakor helyadatokkal együtt validálni hogy talál-e az eseményhez csatolt kóddal, hellyel.
 - Androidos, ios-os kliens megvalósítása
 - kitelepítés
