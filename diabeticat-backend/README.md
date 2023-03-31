# Readme von ftse-projekt-ws22 - Gruppe G03

## 1. Ausführen des Projektes
#### Das Projekt ist auf 2 verschiedene Arten problemlos ausführ und startbar.

## 1.1 Bootable-Jar (lokales Umfeld)
### 1.1.1 Deployment und Start
1. Führen Sie im Stammverzeichnis `ftse-projekt-ws22`die Datei `run-jar.sh` aus.
Die Datei wird via maven die Projekte bauen und die bootable-jars erstellen. 
Nach dem Bau werden alle Wildfly Services automatisch ausgeführt und gestartet.
Wichtig: Dies kann in Summe ein paar Minuten dauern.

2. Im Anschluss sind alle Datenbanken und Wildfly Server in seperaten Containern deployt und gestartet
und auf ihrem Host System Lokal zugreifbar unter:
- UI-View: http://localhost:8080/
- Film-REST: http://localhost:8081/resources
- Store-REST: http://localhost:8082/resources
- Customer-REST: http://localhost:8083/resources

### 1.1.2 Clean und Stop
Führen Sie im Stammverzeichnis `ftse-projekt-ws22`die Datei `clean-up-jar.sh` aus.
Das Script terminiert automatisch alle geöffneten instanzen des ausgeführten Projektes.

## 1.2 Docker (remote)
### 1.2.1 Deployment und Start
1. Stellen vor dem Ausführen Sie sicher, dass alle alten Container und Images dieses Projektes in Docker gelöscht wurden. (Ansonsten siehe [Anleitung Punkt 1.2.2](https://gitlab-fi.ostfalia.de/lv-ftse-ws22/g03/ftse-projekt-ws22/-/blob/main/README.md#122-clean-und-stop))

2. Führen Sie im Stammverzeichnis `ftse-projekt-ws22`die Datei `run-wildfly.sh` aus.
Das Script wird automatisch via maven aus allen Projekten die war-Dateien bauen. 
Im Anschluss führt das Script automatisiert das komplette Deployment von Volumes, Images und Containern in Docker aus.

3. Nach dem Bau werden alle Wildfly Services automatisch ausgeführt und gestartet.
Wichtig: Dies kann in Summe ein paar Minuten dauern, da auch die Datenbanken erstellt werden müssen.

4. Im Anschluss sind alle Datenbanken und Wildfly Server in seperaten Containern deployt und gestartet
und auf ihrem Host System Lokal zugreifbar unter:
- UI-View: http://localhost:8080/dvd-rental-ui
- Film-REST: http://localhost:8081/dvd-rental-film/resources
- Store-REST: http://localhost:8082/dvd-rental-store/resources
- Customer-REST: http://localhost:8083/dvd-rental-customer/resources

## 1.3 Sonstige Befehle
.war Datei für Docker erstellen: 
```
    cd dvd-rental-[app]      (for example: cd dvd-rental-customer)
    mvn clean package -P docker
```
Ausführen der Docker-Compose Datei: 
```
    docker-compose up --build
```
Bootable-jar Datei erstellen: 
```
    cd dvd-rental-[app]      (for example: cd dvd-rental-customer)
    mvn clean package -f pom-bootable.xml
```

#### 1.2.2 Clean und Stop
Führen Sie im Stammverzeichnis `ftse-projekt-ws22`die Datei `clean-up-wildfly.sh` aus.
Das Script terminiert automatisch alle geöffneten instanzen des ausgeführten Projektes in Docker.
Desweiteren löscht es alle in Docker zum Projekt erstellen Container, Images und Volumes.

# Teilnehmer, Aufteilung und Hauptverantwortlichkeiten
Wir haben uns einstimmig auf eine Auteilung von 25% je Mitglied geeinigt.
Die Auteilung spiegelt den Arbeitsaufwand des jeweiligen Mitglieds wieder.
Um diesen gerecht und möglichst gleich zu halten, haben wir von Anfang an Hautpverantwortlichkeiten vergeben, 
die wie unten aufgelistet verteilt wurden.
Ungeachtet von Hauptverantwortlichkeiten haben wir uns natürlich trotzdem gegenseitig alle sehr gut unterstützt und einander ausgeholfen.
Regelmäßige Teammeetings haben stattgefunden. 

Bei Rückfragen zögern Sie nicht, den oder die jeweiligen Hautpverantwortlichen zu kontaktieren. Wir helfen jeder Zeit gerne weiter


Aufgaben:\
Aufgabe 1 Realisierung des REST-APIs mit WildFly (Server und Jar)
- Rest APIs
- Bootable jars
- Anwendungslogik
- Pom.xml Entwurf (für flexibles packaging)
- Integrationstest

Aufgabe 2 JSF-Front-End
- Design
- Funktionalität

Aufgabe 3 Laufzeitumgebung mit lokalen Containern
- Docker-Files

Aufgabe 4 Zusätzliche Umsetzung mit Docker Compose
- Docker Compose File
- .sh Scripte

## 1. Marcel Diedrich
Verantwortlich für folgenden Teilbereiche aus Aufgabe 1
- Rest APIs
- Bootable jars
- Anwendungslogik
- Pom.xml Entwurf (für flexibles packaging)

Verantwortlich für folgenden Teilbereiche aus Aufgabe 2

- Design
- Funktionalität

## 2. Florian Mussehl
Verantwortlich für folgenden Teilbereiche aus Aufgabe 1
- Rest APIs
- Bootable jars
- Anwendungslogik
- Pom.xml Entwurf (für flexibles packaging)

Verantwortlich für folgenden Teilbereiche aus Aufgabe 2

- Design
- Funktionalität

## 3. Pascal Wilczek
Verantwortlich für folgenden Teilbereiche:
- Docker-Files zu Microservices
- Integrationstests\
-> Integrationstests zum erstellen, editieren und entfernen von\
   Schauspieler, Kunden, Adressen und Filmen\
-> prüfen der Responses und der dazugehörigen Daten in der Datenbank.
   

## 4. Oliver Wanzek
Verantwortlich für die Aufgaben 3 & 4:

- Docker Files für alle 7 Container (4 Microservices + 3 Datenbanken)
- Docker-Compose File
- .sh Scripte zum Bauen, Ausführen und Clearen der Bootable-Jars
- .sh Scripte zum Bauen, Ausführen und Clearen der Docker Container

