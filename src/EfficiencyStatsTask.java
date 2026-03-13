// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 06/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: EfficiencyStatsTask Runnable class containing the definitions of the overriden run() method that executes on a seperate thread

import java.util.List;

//implements Runnable so the run() method can execute in its own thread
public class EfficiencyStatsTask implements Runnable {
    private List<MileageRecord> records;
	
	public EfficiencyStatsTask(List<MileageRecord> records) {
		this.records = records;
	}
	
	private double min;
    private double max;
    private double avg;

	
	public void run() {
		//initialise min as a very large value for comparison 
		min = Double.POSITIVE_INFINITY;
		//initialise max as a very small value for comparison
		max = Double.NEGATIVE_INFINITY;
		avg = 0;
		// Sum to store total efficiency value for average calculation
		double sum = 0;
		int count = 0;
		
		for(int i = 1; i < records.size(); i++) {
			MileageRecord current = records.get(i);
			double efficiency = current.getEfficiency();
			if (!Double.isNaN(efficiency)) {
				if(efficiency < min) { 
					min = efficiency; 
				}
				
				if(efficiency > max) {
					max = efficiency;
				}
				
				//increment count with each loop iteration
				count++;
				// Sum all efficiency values
				sum += efficiency;
			}
		}
		
		if(count > 0) {
			avg = sum / count;
		} else {
			avg = Double.NaN;
		}
	}

    public double getMin() { 
        return min;
    }

    public double getMax() { 
        return max;
    }

    public double getAvg() { 
        return avg;
    }
  


}
