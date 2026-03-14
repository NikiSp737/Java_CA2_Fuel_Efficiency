// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 07/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: Testing harness for the readRecords(), sortByDate(), computeEfficiencies() and efficiencyStatsTask() methods

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MileageTestHarness {

    public static void main(String[] args) {
        if (args.length == 0) {							//if user didnt provide an argument (filename) 
            System.out.println("No filename provided");	//print reason for exit
            return;
        }

        String filename = args[0];						//otherwise if user provided a filename store it

        testReadRecords(filename);
        testSortByDate(filename);
        testComputeEfficiencies(filename);
        testEfficiencyStatsTask(filename);
    }


	//readRecords() method definition stored in MileageRecord.java
	//expected output for testReadRecords() using mileage_tiny.txt:
    /*Number of records read: 4
	MileageRecord: date= 2025-01-02, kilometers= 24886, liters= 27.59, efficiency= Null
	MileageRecord: date= 2025-01-09, kilometers= 25319, liters= 29.55, efficiency= Null
	MileageRecord: date= 2025-01-16, kilometers= 25740, liters= 28.98, efficiency= Null
	MileageRecord: date= 2025-01-23, kilometers= 26141, liters= 27.13, efficiency= Null
	*/
    private static void testReadRecords(String filename) {
        System.out.println("test for the readRecords method:");

        List<MileageRecord> records = MileageProcessor.readRecords(filename);

        System.out.println("Number of records read: " + records.size());
        for (MileageRecord record : records) {
            System.out.println(record);
        } 
    }
	
	
	

	//sortByDate() method definition stored in MileageRecord.java
    /*expected output for testSortByDate() using mileage_tiny.txt:
	Records after sorting:
	2025-01-02 24886 27.59
	2025-01-09 25319 29.55
	2025-01-16 25740 28.98
	2025-01-23 26141 27.13
	*/
    private static void testSortByDate(String filename) {
        System.out.println("test for sortByDate method:");

        List<MileageRecord> records = MileageProcessor.readRecords(filename);
        MileageProcessor.sortByDate(records);

        System.out.println("Records after sorting:");
        for (MileageRecord record : records) {
            System.out.println(record.getDate() + " " + record.getKilometers() + " " + record.getLiters());
        } 
    }
  


	//computeEfficiencies() method definition stored in MileageRecord.java
	/*expected output for testComputeEfficiencies() using mileage_tiny.txt
	Efficiencies:
	6.82
	6.88
	6.77
	*/
    private static void testComputeEfficiencies(String filename) {
        System.out.println("test for computeEfficiencies:");

        List<MileageRecord> records = MileageProcessor.readRecords(filename);
        MileageProcessor.sortByDate(records);
        List<Double> efficiencies = MileageProcessor.computeEfficiencies(records);

        System.out.println("Efficiencies:");
        for (Double efficiency : efficiencies) {
            System.out.printf("%.2f%n", efficiency);
        } 
    }


	//efficiencyStatsTask() method definition stored in EfficiencyStatTask.java
	/*expected output for testEfficiencyStatsTask() using mileage_tiny.txt:
	test for EfficiencyStatsTask:
	Min = 6.77
	Max = 6.88
	Avg = 6.82
	Count = 3
	*/
    private static void testEfficiencyStatsTask(String filename) {
        System.out.println("test for EfficiencyStatsTask:");

        List<MileageRecord> records = MileageProcessor.readRecords(filename);
        MileageProcessor.sortByDate(records);
        List<Double> efficiencies = MileageProcessor.computeEfficiencies(records);

        EfficiencyStatsTask task = new EfficiencyStatsTask(efficiencies);
        Thread thread = new Thread(task);

        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Test interrupted.");
            return;
        }

        EfficiencyStats stats = task.getResult();

        System.out.println("Min = " + stats.getMin());
		System.out.println("Max = " + stats.getMax());
		System.out.println("Avg = " + stats.getAvg());
		System.out.println("Count = " + stats.getCount());
    }
}
