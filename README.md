# trip-management-cmd

Trip Management Command manages trips and invoices. It is the core service of the ridesharing app.

## Features
* Create Trips
* Start Trips
* Cancel Trips
* End Trips

# Demo
To run this project from docker:
* Make sure that the Docker daemon is installed and running.
* Assemble this project.
* Run ```docker-compose up --build``` from the root project.
* On another terminal run ```docker ps``` to see which ports were assigned.

# Development

## Requirements
* Docker 17.xx.x
* JDK 1.8
* IntelliJ 2018
* Mongo 3.4

 To run this project from docker:
 * Make sure that the Docker daemon is installed and running.
 * Assemble this project.
 * Run ```docker-compose up --build -f docker-compose-dev.yml``` from the root project.
 * Run the Gradle task 'application/bootrun' from IntelliJ.