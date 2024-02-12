#!/bin/bash
cd ChaseLogic
mvn clean install
cd ../Game
mvn clean install
java -jar target/Game-1-jar-with-dependencies.jar --enemiesCount=2 --wallsCount=20 --size=15 --profile=production