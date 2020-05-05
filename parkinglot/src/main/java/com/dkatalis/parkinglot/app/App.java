package com.dkatalis.parkinglot.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.dkatalis.parkinglot.commons.Constants;
import com.dkatalis.parkinglot.entity.ParkingEntity;
import com.dkatalis.parkinglot.entity.ParkingServiceDTO;
import com.dkatalis.parkinglot.service.ParkingService;

public class App {

	private static ParkingService svc;

	public static void main(String[] args) {

		svc = new ParkingService();

		if (args.length > 0) {
			// get command
			String cmd1 = args[0];

			// run the app with file
			if (cmd1.split(".txt").length > 0) {
				try (BufferedReader br = new BufferedReader(new FileReader(cmd1))) {
					String line;
					while ((line = br.readLine()) != null) {
						RunningApp(line);
					}
				} catch (FileNotFoundException e) {
					System.out.println("File not found!");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			// run interactively
			while (true) {
				String input = System.console().readLine();
				RunningApp(input);
			}
		}

	}

	private static void RunningApp(String input) {
		if (input.trim() != "") {

			String[] inputs = input.split(" ");

			if (inputs.length == 2) {
				if (inputs[1].toLowerCase() == "--help") {
					GenerateHelp(inputs[0].toLowerCase());
					return;
				}
			}

			ParkingServiceDTO dto;

			switch (inputs[0]) {
			case Constants.Commands.CREATE_PARKING_LOT:

				if (inputs.length != 2) {
					GenerateParametersError(Constants.Commands.CREATE_PARKING_LOT);
					break;
				}

				int capacity;
				try {
					capacity = Integer.parseInt(inputs[1]);
				} catch (Exception e) {
					GenerateParametersError(Constants.Commands.CREATE_PARKING_LOT);
					break;
				}

				svc.createParkingLot(capacity);
				System.out.println(String.format("Created parking lot with %d slots", capacity));
				break;

			case Constants.Commands.PARK:

				if (inputs.length != 2) {
					GenerateParametersError(Constants.Commands.PARK);
					break;
				}

				dto = svc.park(inputs[1]);
				if (dto.getMessages().containsKey("errorMsg")) {
					System.out.println(dto.getMessages().get("errorMsg"));
				} else {
					System.out.println(String.format("Allocated slot number: %d", dto.getMessages().get("slotNo")));
				}
				break;

			case Constants.Commands.LEAVE:

				if (inputs.length != 3) {
					GenerateParametersError(Constants.Commands.LEAVE);
					break;
				}

				int hours;
				try {
					hours = Integer.parseInt(inputs[2]);
				} catch (Exception e) {
					GenerateParametersError(Constants.Commands.LEAVE);
					break;
				}

				dto = svc.remove(inputs[1], hours);
				if (dto.getMessages().containsKey("errorMsg")) {
					System.out.println(dto.getMessages().get("errorMsg"));
				} else {
					System.out
							.println(String.format("Registration number %s with Slot Number %d is free with Charge %d",
									dto.getMessages().get("registrationNo"), dto.getMessages().get("slotNo"),
									dto.getMessages().get("charge")));
				}

				break;

			case Constants.Commands.STATUS:
				if (inputs.length != 1) {
					GenerateParametersError(Constants.Commands.STATUS);
					break;
				}

				List<ParkingEntity> list = svc.getStatus();
				
				break;
			case Constants.Commands.HELP:
				GenerateHelp(Constants.Commands.ALL);
				break;
			default:
				GenerateCommandError();
				break;
			}

		} else {
			GenerateCommandError();
		}
	}

	private static void GenerateParametersError(String command) {
		System.out.println("Parameters error!");
		System.out.println(String.format("See %s --help\n", command));
	}

	private static void GenerateCommandError() {
		System.out.println("Command not found!");
		System.out.println(String.format("See help\n"));
	}

	private static void GenerateHelp(String command) {
		String result = "";
		switch (command) {
		case Constants.Commands.CREATE_PARKING_LOT:
			result = "create_parking_lot [capacity]";
			break;
		case Constants.Commands.PARK:
			result = "park [registration no]";
			break;
		case Constants.Commands.LEAVE:
			result = "leave [slot no] [hours]";
			break;
		case Constants.Commands.STATUS:
			result = "status";
			break;
		case Constants.Commands.ALL:
			result = "commands available: create_parking_lot, park, leave, status, registration_numbers_for_cars_with_colour, slot_numbers_for_cars_with_colour, slot_number_for_registration_number";
			break;
		}
		System.out.println(String.format("%s\n", result));
	}

}
