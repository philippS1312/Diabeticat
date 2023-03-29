#!/bin/sh

#create maven package: Customer
cd dvd-rental-customer
mvn clean package -f pom-bootable.xml
cd ..

#create maven package: Film
cd dvd-rental-film
mvn clean package -f pom-bootable.xml
cd ..

#create maven package: Store
cd dvd-rental-store
mvn clean package -f pom-bootable.xml
cd ..

#create maven package: UI
cd dvd-rental-ui
mvn clean package -f pom-bootable.xml
cd ..

#start all Wildfly instances:
java -jar dvd-rental-customer/target/dvd-rental-customer-bootable.jar &
java -jar dvd-rental-film/target/dvd-rental-film-bootable.jar &
java -jar dvd-rental-store/target/dvd-rental-store-bootable.jar &
java -jar dvd-rental-ui/target/dvd-rental-ui-bootable.jar

#Keep Window open
$SHELL
