## Greet-in-java

Greet-in-java project allows you to greet users in a database in 4 different languages.

## Build status

[![Build Status](https://travis-ci.com/thaabit-jacobs/Greet-in-java-Project.svg?branch=master)](https://travis-ci.com/thaabit-jacobs/Greet-in-java-Project)

## Run Greet-in-java

In the main project diectory run command:

java -cp target/* net.greet.Greet

- greet &nbsp; &nbsp; &nbsp; &nbsp;[name] [language] (To greet a user)

- greeted &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;   &nbsp; &nbsp;   &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; (Returns all the unique users have been greeted)

- greeted &nbsp; &nbsp; [name] &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;   &nbsp; &nbsp;   &nbsp;  	(Returns the number times user has been greeted)

- clear &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;   &nbsp; &nbsp;   &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;  &nbsp; &nbsp;   &nbsp; (Sets all users greet count to 0 that have been greeted)

- clear &nbsp; &nbsp; &nbsp; &nbsp; [name]   &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;(Sets user's greet count to 0)

- help &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp; (Returns commands)

- exit    &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;(Exit application)

## Running test

To run test enter: 

mvn test
