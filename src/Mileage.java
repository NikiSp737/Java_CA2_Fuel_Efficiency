// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 08/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: Skeleton for MileageRecord method

import java.util.List;

public class Mileage {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsageMessage();
            return;
        }

        String filename = args[0];
        List<MileageRecord> records = MileageProcessor.readRecords(filename);

        if (records.size() < 2) {
            printNotEnoughData();
            return;
        }

        MileageProcessor.sortByDate(records);
        List<Double> efficiencies = MileageProcessor.computeEfficiencies(records);
        EfficiencyStats stats = startStatisticsThread(efficiencies);

        printTable(records);
        printStatistics(stats);
    }

    private static EfficiencyStats startStatisticsThread(List<Double> efficiencies) {
        EfficiencyStatsTask task = new EfficiencyStatsTask(efficiencies);
        Thread thread = new Thread(task);

        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
            return new EfficiencyStats(0, 0, 0, 0);
        }

        return task.getResult();
    }

    private static void printTable(List<MileageRecord> records) {
        for (MileageRecord record : records) {
            System.out.println(record);
        }
    }

    private static void printStatistics(EfficiencyStats stats) {
        System.out.println("Min efficiency: " + stats.getMin());
        System.out.println("Max efficiency: " + stats.getMax());
        System.out.println("Avg efficiency: " + stats.getAvg());
        System.out.println("Count: " + stats.getCount());
    }

    private static void printUsageMessage() {
        System.out.println("Usage: java Mileage <filename>");
    }

    private static void printNotEnoughData() {
        System.out.println("Not enough data to compute efficiencies.");
    }
}