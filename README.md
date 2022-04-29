# NyiltnApp

## Alkalmazás alapötlete
A megvalósítandó alkalmazás a Sapientián megrendezett nyílt napok alkalmával történő eseménysorozat regisztrációs folyamatát segíti, valamint részvételi statisztikákat hivatott kimutatni. Egy ilyen esemény több napos időintervallumban, változatos helyszíneken kerül megszervezésre. Az eseményt megelőzően, a szervezőknek szükséges tudniuk, hogy kb. hány érdeklődő van, emiatt szükséges egy regisztációs ívet kitölteni. Ebben olyan adatokat kell a jelentkezőknek kitölteni, mint: név, email-cím, tanár vagy diák, osztály (diák esetén), iskola. Az iskolákat egy legördülő listából lesz lehetőség kiválasztani, annak érdekében hogy az adatok egységesek legyenek, és ezeket fel lehessen használni statisztikák készítésére.
Az egyes eseményeken való tényleges részvételt, egy, a helyszínen található QR-kód beolvasásával tud igazolni a felhasználó, valamint felhasználásra kerülnek helyadatai is (GPS-koordináták) validálás céljából.

## Technikai megközelítés
* ### Architektúra
### Backend:
 - Java Spring (JPA, postgresql)
### Frontend:
 - Android kliens (Kotlin)
 - Webes kliens (Angular)
### Szerepkör:
 - admin (szervező)
 - jelentkező/résztvevő
## Funkcionalitások:
 - Regisztráció + Login
 - Statisztika térképes vizualizáció, honnan hányan regisztráltak s hányan vettek tényleg részt (%-os arányok)
 - Előadók listája
 - Esemény regisztráció esetén, email-t kapni pontos részletekről
 - Emlékeztető értesítés esemény kezdete előtt x idővel
 - Térképes útvonal megnyitása amennyiben adott esemény helyszíne felé tart
 - QR-kód leolvasásakor helyadatokkal együtt validálni hogy talál-e az eseményhez csatolt kóddal, hellyel.
