package com.Denver.Airport;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import com.Denver.Airport.Conveyor.BaggageNode;
import com.Denver.Airport.Conveyor.ConveyorPath;
import com.Denver.Airport.Model.Model;
import com.Denver.Airport.Passenger.Baggage;

public class AirportOperationsMain {
	private Model airPortModel;

	public AirportOperationsMain() {
		this.airPortModel = Model.getInstance();
	}

	/*
	 * Main Method
	 */
	public static void main(String[] args) {

		AirportOperationsMain mainOpr = new AirportOperationsMain();
		mainOpr.checkInLuggages();// Read input from a file
		mainOpr.calculateAndDisplayPaths();
	}

	/*
	 * Calculate minimum distance and Display Path
	 */
	private void calculateAndDisplayPaths() {
		for (Baggage bag : airPortModel.getBaggagelist()) {
			// controller.computePaths(bag.getLoadingPoint(),
			// bag.getBoardingPoint());
			bag.computePaths();
			System.out.print(bag.getLoadingPoint() + " ");
			bag.printPath(bag.getLoadingPoint(), bag.getBoardingPoint());
			System.out.println(":" + bag.getBoardingPoint().getMinDistance());

			// Reset mimimun distance of all nodes
			airPortModel.resetMinimumDistance();
		}

	}

	/*
	 * Input data from text file Read the file "input.txt"
	 */
	private void checkInLuggages() {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("input.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		try {
			String currentLine = "";
			String currentInput = "";

			while (Boolean.TRUE) {
				currentLine = br.readLine();
				if (currentLine == null) {
					break;
				}
				if (currentLine.contains(Constants.START_STRING)) {
					currentInput = currentLine.substring(
							currentLine.indexOf(':') + 1, currentLine.length());

				} else if (currentInput.trim().equalsIgnoreCase(
						Constants.CONV_SYS)) {
					updateConveyor(currentLine);

				} else if (currentInput.trim().equalsIgnoreCase(
						Constants.DEPARTURES)) {
					updateDepartures(currentLine);
				} else if (currentInput.trim().equalsIgnoreCase(Constants.BAGS)) {
					updateBags(currentLine);
				}

			}
			airPortModel.updateBoardingPoints();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Update Luggage Details
	 */
	private void updateBags(String input) {
		String[] inputs = input.split(" ");
		airPortModel.checkinBaggage(new Baggage(inputs[0], airPortModel
				.getNodeMap().get(inputs[1]), inputs[2]));
	}

	/*
	 * Read Departure Details UA10 A1 MIA 08:00
	 */

	private void updateDepartures(String input) {
		String[] inputs = input.split(" ");
		airPortModel.addDeparture(new Departures(inputs[0], airPortModel
				.getNodeMap().get(inputs[1]), inputs[2], inputs[3]));
	}

	/*
	 * Read Conveyor Details Node1->Node2 Distance
	 */

	private void updateConveyor(String input) {
		String[] inputs = input.split(" ");

		String node1Name = inputs[0];
		String node2name = inputs[1];
		int dist = Integer.parseInt(inputs[2].trim());

		BaggageNode sourceNode;
		BaggageNode newDestinationNode;
		if (airPortModel.getNodeMap().containsKey(node1Name)) {
			sourceNode = airPortModel.getNodeMap().get(node1Name);
		} else {
			sourceNode = new BaggageNode(node1Name);
		}

		if (airPortModel.getNodeMap().containsKey(node2name)) {
			newDestinationNode = airPortModel.getNodeMap().get(node2name);
		} else {
			newDestinationNode = new BaggageNode(node2name);
		}

		sourceNode.addAdjencies(Arrays.asList(new ConveyorPath(dist,
				newDestinationNode)));
		newDestinationNode.addAdjencies(Arrays.asList(new ConveyorPath(dist,
				sourceNode)));

		airPortModel.getNodeMap().put(sourceNode.getName(), sourceNode);
		airPortModel.getNodeMap().put(newDestinationNode.getName(),
				newDestinationNode);

	}

}
