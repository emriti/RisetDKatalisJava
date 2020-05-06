# Parking Lot Problem

# Background

This is my interpretation solving parking lot problem, the application were built using Java 1.8, Junit 3.8, and Apache Maven 3.0.5.
The source code can be found in ```parkinglot/src```.

## Setup application

Run bash ```setup``` in ```bin``` directory
```sh
parkinglot$ bin/setup
```
That command has automatically run unit testing, unit testing commands contained in the file  ```bin/run_functional_tests```

This application fully controlled by command. Run bash ```parking_lot``` in bin directory with 2 options:

* The inputs commands are expected and taken from the file specified
```sh
parkinglot$ bin/parking_lot [input_filepath]
```
* Or start the program in interactive mode.
```sh
parkinglot$ bin/parking_lot
```
**Command list**

* ```>> create_parking_lot [capacity]```
Initialization of a parking lot with parameters of slot capacity. This is the first command that must be run to initialize the parking lot.

* ```>> park [car_registration_number]```
Parking a car in an available slot with registration number as the identification.
If successfully parked, the program will print ```Allocated slot number: [nearest_slot_number]```. If failed,
(parking lot is full) it will print ```Sorry, parking lot is full```. The application ensure that a car will have a unique identification number, if any car parked with the same id it will print ```Data has already exists!```.

* ```>> leave [car_registration_number] [hours]```
The slot is be available after a car leaves the parking lot (using car registration number) so that the slot can be occupied by another car. When leaving, the system will calculate the fee for parking the car, with formulation $10 for first 2 hours and $10 for every additional hour. The system also validate to ensure that a car that leaving are registered in the system. If the system does not found the car, it will print ```Registration number [car_registration_number] not found```.

* ```>> status```
For print parking area status in table format.
```Slot No.    Registration No     ```

## Unit testing

Unit testing can be done be executing this command
```sh
parkinglot$ mvn test
```
