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

	private static ParkingService svc = new ParkingService();

	public static void main(String[] args) {
		run(args);
//		runningApp("park --help");
//		runningApp("create_parking_lot 6");
//		runningApp("park KA-01-HH-1234");
//		runningApp("park KA-01-HH-9999");
//		runningApp("park KA-01-BB-0001");
//		runningApp("park KA-01-HH-7777");
//		runningApp("park KA-01-HH-2701");
//		runningApp("park KA-01-HH-3141");
//		runningApp("leave KA-01-HH-3141 4");
//		runningApp("status");
//		runningApp("park KA-01-P-333");
//		runningApp("park DL-12-AA-9999");
//		runningApp("leave KA-01-HH-1234 4");
//		runningApp("leave KA-01-BB-0001 6");
//		runningApp("leave DL-12-AA-9999 2");
//		runningApp("park KA-09-HH-0987");
//		runningApp("park CA-09-IO-1111");
//		runningApp("park KA-09-HH-0123");
//		runningApp("status");
	}

	private static void run(String[] args) {

		if (args.length > 0) {
			// get command
			String cmd1 = args[0];

			// run the app with file
			if (cmd1.split(".txt").length > 0) {
				try (BufferedReader br = new BufferedReader(new FileReader(cmd1))) {
					String line;
					while ((line = br.readLine()) != null) {
						runningApp(line);
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
				runningApp(input);
			}
		}

	}

	private static void runningApp(String input) {
		if (input.trim() != "") {

			String[] inputs = input.split(" ");

			if (inputs.length == 2) {
				if (inputs[1].toLowerCase().equals("--help")) {
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
					System.out.println(dto.getMessages().get("errorMsg").toString());
				} else {
					System.out.println(
							String.format("Allocated slot number: %s", dto.getMessages().get("slotNo").toString()));
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
					if (dto.getMessages().get("errorMsg").toString().equals("Data not found!")) {
						System.out.println(String.format("Registration number %s not found",
								dto.getMessages().get("registrationNo").toString()));
					} else {
						System.out.println(dto.getMessages().get("errorMsg"));
					}

				} else {
					System.out.println(String.format(
							"Registration number %s with Slot Number %s is free with Charge %s",
							dto.getMessages().get("registrationNo").toString(),
							dto.getMessages().get("slotNo").toString(), dto.getMessages().get("charge").toString()));
				}

				break;

			case Constants.Commands.STATUS:
				if (inputs.length != 1) {
					GenerateParametersError(Constants.Commands.STATUS);
					break;
				}

				System.out.println("Slot No.\tRegistration No");
				List<ParkingEntity> list = svc.getStatus();
				for (ParkingEntity parkingEntity : list) {
					if (parkingEntity != null) {
						System.out.println(String.format("%s\t\t%s", parkingEntity.getSlotNo(),
								parkingEntity.getRegistrationNo()));
					}
				}
				break;
			case Constants.Commands.HELP:
				GenerateHelp(Constants.Commands.ALL);
				break;
			case Constants.Commands.EXIT:
				System.exit(0);
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
			result = "commands available: create_parking_lot, park, leave, status";
			break;
		}
		System.out.println(String.format("%s\n", result));
	}

}
