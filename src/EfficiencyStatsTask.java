// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 06/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: EfficiencyStatsTask Runnable class containing the definitions of the overriden run() method that executes on a seperate thread

import java.util.List;

//implements Runnable so the run() method can execute in its own thread
public class EfficiencyStatsTask implements Runnable {

    private List<Double> efficiencies;	//list containing efficiency values to analyse
    private EfficiencyStats result;		//object that will store the computed stats

    public EfficiencyStatsTask(List<Double> efficiencies) {	//constructor recieves the efficiencies list
        this.efficiencies = efficiencies;					//store the list so the thread can access it
        this.result = null;									//initialize all results as null
    }

	//method executed when the thread is started
    @Override
    public void run() {
		//initialise min as a very large value for comparison 
		//double min = efficiencies.indexOf(Collections.min(efficiencies)); 
		//double min = 999999; // Just set to value we know will be larger.
		double min = efficiencies.get(0); // Better to get first element 
		//initialise max as a very small value for comparison
		double max = efficiencies.get(0);
		// Sum to store total efficiency value for average calculation
		double sum = 0;
		// Count value for amount of elements in list
		int count = efficiencies.size();
		// If count is 0 or less then return 0's for the results
		// Prevents division by 0 later on.
		if(count <= 0){
			result = new EfficiencyStats(0, 0, 0, 0);
			System.out.println("No efficiency values to analyse.");
			return; // Exit
		}
		// Linear scan algorithm O^n complexity because it goes through each efficiency value once
		//loop through each efficiency value in the efficiencies list
		for (int i = 0; i < efficiencies.size(); i++){
			// Current for storing efficiency value assessed
			double current = efficiencies.get(i);
			// Check if new efficiency is smaller 
			if(current < min){
			// update min
			min = current;
			}
			// Check if new efficiency is greater
			if(current > max){
			// Set max to be the new biggest value
				max = current;
			}
			// Sum all efficiencies for average calculation
			sum += current;
		} 
		// Average
		double average = sum / count;
		// new result object to return stats values
		result = new EfficiencyStats(min, max, average, count);
    }

    public EfficiencyStats getResult() {					//retrieve method for the computed stats
        return result;
    }
	

	public static void main(String[] args) {   //standalone test for EfficiencyStatsTask

		List<Double> testEfficiencies = List.of(6.82, 6.88, 6.77);		//hardcoded efficiencies for testing

		EfficiencyStatsTask task = new EfficiencyStatsTask(testEfficiencies);	//create a new object named task of type EfficiencyStatsTask and feed it the list of hardcoded testEfficiencies
		Thread thread = new Thread(task);								//creates a new thread type object called thread and assign it the task to run (executes tast.run() but in a seperate thread)

		try {
			thread.start();												//start the new thread
			thread.join();												//main program will wait for the thread to finish its task before continuing
		} catch (InterruptedException e) {								//required catch because the thread can throw this error if its interrupted by another thread
			System.out.println("thread interrupted error");			//print error message if interrupted
		}

		EfficiencyStats result = task.getResult();

		System.out.println("Min: " + result.getMin());
		System.out.println("Max: " + result.getMax());
		System.out.println("Avg: " + result.getAvg());
		System.out.println("Count: " + result.getCount());
	}

}
