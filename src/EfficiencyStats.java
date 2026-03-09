// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 06/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: EfficiencStats class containg definitions of the EfficiencyStats objects and getters for the stat variables

public class EfficiencyStats {

    private double min;
    private double max;
    private double avg;
    private int count;

    public EfficiencyStats(double min, double max, double avg, int count) {
        this.min = min;							//store the min, max, avg and count in this object
        this.max = max;
        this.avg = avg;
        this.count = count;
    }

    public double getMin() {					//getter method that returns the min efficiency
        return min;
    }

    public double getMax() {					//getter method that returns the max efficiency
        return max;
    }

    public double getAvg() {					//getter method that returns the avg efficiency
        return avg;
    }

    public int getCount() {						//getter method that returns the number of efficiency values
        return count;
    }

    @Override
    public String toString() {					//converts the statistics object into a formatted string for printing
        return "EfficiencyStats:" + "min=" + min + ", max=" + max + ", avg=" + avg + ", count=" + count;
    }
	
	
	public static void main(String[] args) {   // standalone test for this class

		EfficiencyStats stats = new EfficiencyStats(6.76, 6.88, 6.82, 3);

		System.out.println("Min: " + stats.getMin());
		System.out.println("Max: " + stats.getMax());
		System.out.println("Avg: " + stats.getAvg());
		System.out.println("Count: " + stats.getCount());

		System.out.println(stats);  // test the toString() method
	}
}