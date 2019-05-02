


**Grupperapport, Gruppe 8320.  kandidatnummer 671, 656, 624**

**Det vi er spesielt fornøyd med:**
- Samarbeidet i gruppa. Vi har hatt god strukturering av ansvarsområdene. Alle har bidratt, og vi har hatt en sunn samarbeidskultur der vi har kunnet diskutere problemer på en givende måte.
- Struktur på programmet: Når det gjelder fordeling av klasser og bruk av MVC mener vi at dette har blitt gjort på en god måte i henhold til oppgavebeskrivelsen.
- Vi har fått demonstrert implementasjon av det aller meste i pensum gjennom oppgaven.
- Så godt det har latt seg gjøre har vi etterstrevd å bruke god OOP-praksis, bl.a. gjennom bruk av designmønstre som State, Listeners og Builders, samt de som ble eksplisitt beskrevet i oppgaven.
- Læringsutbyttet har vært veldig høyt.
- Vi var veldig raske med å komme i gang, og har holdt et jevnt tempo hele veien.

**Rom for forbedring:**
- Mange av klassene våre for objekter som Insurance, Customer, etc. har hatt konstruktører som har gjort det vanskelig å opprette "midlertidige" objekt. Om vi i større grad hadde fulgt f.eks. Bean standarden med tom konstruktør etc. ville det f.eks. vært mye lettere å jobbe med instanser av disse objektene i kontrollørene.
- Lesing av csv fil i Excel bydde på noen problemer. Vi har prøvd å leke med forskjellig encoding (UTF-8, ISO-8859-1), men fikk problemer med å lese inn spesialtegn ved bruk av disse filene. Slik det er nå har vi i noen versjoner av Excel vært nødt til å manuelt sette encoding til UTF-8 for å få inn mer spesielle tegn.
- Designet var litt kjedelig, men vi valgte å bortprioritere å bruke tid på styling (noe lett styling har dog blitt gjort).
- Kommunikasjon mellom controllere (passering av objekt o.l.) var utfordrende og kunne nok vært gjort bedre. Spesielt ved endring/sletting/tillegging av objekt som ligger i tabeller kunne vi nok i større grad ha brukt Listeners for å oppdatere tabellen. Slik det er nå blir mye av disse operasjonene gjort manuelt.
- Progressbar kunne vist nøyaktig hvor langt den har kommet, nå viser den først og fremst at programmet jobber.
- Noen av kontrollerne, spesielt src.main.java.controllers.DetailedCustomerController kunne vært delt opp mer, slik den er nå er noe uoversiktlig.

**Hva har vi lært?**
- Vi har merket hvor viktig det er å ha tydelige arbeidsoppgaver og jobbe strukturert. Noen ganger har flere stykk jobbet på samme område samtidig noe som har resultert i git-conflicts som har tatt litt tid.
- Spesifikke og tydelige commits er viktig! Dette går inn under det å jobbe strukturert.

**Hva ville vi gjort annerledes?**
- Jobbet mer strukturert, implementere en ting av gangen og kjøre commits ofte.
- Generelt bruke erfaringene vi har gjort til å velge bedre løsninger fra begynnelsen (f.eks. se etter tilfeller der bruk av designmønstre er naturlig.).
- Ved bruk av pakker som JavaFX: Sette seg enda bedre inn i hva som inngår i god implementasjon av disse.

**Arbeidsfordeling**
Vi vil legge vekt på at det har vært mye samarbeid om det aller meste av koden. Vi har blant annet jobbet mye via parprogrammering, og har gjort code-reviews på de aller fleste commits. Det er dermed slik at alle har involvert i alle deler av koden, og vi vil legge vekt på at alle gruppemedlemmene har jobbet godt og like mye, og er svært fornøyde med hverandres innsats.

Når det er sagt har vi lagd denne oversikten:

Felles:
- Oppsett av hovedklassene (Customer, alle typer Insurance, AccidentStatement, etc).
- Alle builders (src.main.java.models.builders)
- Vi har gjort code-reviews så langt det har latt seg gjøre. På denne måten har alle vært involvert i så og si alle deler av prosjektet. 

671
- src.main.java.models.fileReader lesing av fil med alle tilhørende klasser i pakken.
- src.main.java.models.threading implementasjon av trådprogrammering
- src.main.java.models.fileHandling Hjelpeklasse for håndtering av filer.
- src.main.java.controllers.ToolBarController controller for toolbar samt tilhørende fxml.
- src.main.java.controllers.NewCustomerController controller for ny kunde samt tilhørende fxml.
- src.main.java.models.Exceptions Diverse exceptions...

656
- src.main.java.controllers.DetailedCustomerController Ca. 50% av denne klassen, bl.a. generering av tables. Med tilhørende view.
- src.main.java.controllers.CustomersController Controller for Hovedvinduet for programmet med tilhørende view.
- src.main.java.models.gui Hjelpeklasse for å generere errorvinduer, samt åpne nye vinduer.
- src.main.java.controllers.accidentStatement
- Lagd et skript som har generert data for testing av programmet (50kKunder.csv, med tilhørende forsikringer etc.)

624
- src.main.java.models.filewriter Implementasjon av skriving til fil.
- src.main.java.controllers.insurance Controllere for alle forsikringsvinduer med tilhørende views. 
- src.main.java.controllers.DetailedCustomerController Ca 50% av denne kontrolleren. (åpning av forsikrings- og skademeldingvindu).



<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEzODAyODMwNjVdfQ==
-->