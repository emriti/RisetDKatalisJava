package com.dkatalis.parkinglot.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.dkatalis.parkinglot.service.ParkingService;

/**
 * Hello world!
 *
 */
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
				}
				catch (FileNotFoundException e) {
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
		System.out.println(input);
	}

}
