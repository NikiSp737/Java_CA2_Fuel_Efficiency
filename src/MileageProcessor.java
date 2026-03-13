// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 06/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: MileageProcessor class containing the readRecords(), sortByDate() and computeEfficiencies() methods

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MileageProcessor {
	// List to store read records
    public static List<MileageRecord> readRecords(String filename) {
        ArrayList<MileageRecord> records = new ArrayList<>();	//create storage for records
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	//define the date format

        try (Scanner sc = new Scanner(new File(filename))) {	//open the file and create a scanner

			while (sc.hasNextLine()) { 
				String line = sc.nextLine().trim();				//scan next line and remove trailing and leading spaces using trim()

				if (line.isEmpty() || line.startsWith("date")) {//skip any blank line and the header
					continue;   								
				}

				String[] parts = line.split("\\s+");  			//split by tabs or spaces (double \ because java treats \ as an escape character example: \n so we use \\ to represent an actual \)

				try {
					LocalDate date = LocalDate.parse(parts[0], formatter);	//convert the first string to a LocalDate type object
					int kilometers = Integer.parseInt(parts[1]);			//convert hte second string to an int type onjec
					double liters = Double.parseDouble(parts[2]);			//convert the third string of the line to a double type object

					records.add(new MileageRecord(date, kilometers, liters));	//create the object responsible for the line

				} catch (Exception e) {				
					System.out.println("Skipping invalid record: " + line);
				}
			}

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

        return records;											//returns list of MileageRecord objects
    }
	// Method to sort the list by date 
    public static void sortByDate(List<MileageRecord> records) {
        records.sort(Comparator.comparing(MileageRecord::getDate));
    }

    public static List<Double> computeEfficiencies(List<MileageRecord> records) {
        List<Double> efficiencies = new ArrayList<>();

        for (int i = 1; i < records.size(); i++) {
            MileageRecord previous = records.get(i - 1);
            MileageRecord current = records.get(i);

            int distance = current.getKilometers() - previous.getKilometers();

            if (distance > 0) {
                double efficiency = (current.getLiters() / distance) * 100.0;
                current.setEfficiency(efficiency);
                efficiencies.add(efficiency);
            }
        }

        return efficiencies;
    }
	
	// standalone test for MileageProcessor
	public static void main(String[] args) {  

		List<MileageRecord> records = readRecords("mileage_tiny.txt");

		System.out.println("Scanned records:");
		for (MileageRecord record : records) {
			System.out.println(record.getDate() + " " + record.getKilometers() + " " + record.getLiters());
		}

		sortByDate(records);

		System.out.println("\nAfter sorting:");
		for (MileageRecord record : records) {
			System.out.println(record.getDate());
		}
	
		List<Double> efficiencies = computeEfficiencies(records);
	
		System.out.println("\nEfficiencies:");
		for (double e : efficiencies) {
			System.out.println(e);
		}
	}
}
