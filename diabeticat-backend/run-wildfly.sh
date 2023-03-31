#!/bin/sh

#create maven package: Customer
cd dvd-rental-customer
mvn clean package -P docker
cd ..

#create maven package: Film
cd dvd-rental-film
mvn clean package -P docker
cd ..

#create maven package: Store
cd dvd-rental-store
mvn clean package -P docker
cd ..

#create maven package: UI
cd dvd-rental-ui
mvn clean package -P docker
cd ..

docker compose up --build 