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
		// Second year algorithm 
		double min = efficiencies.indexOf(Collections.min(efficiencies)); 
		//initialise max as a very small value for comparison
		double max = ; 
		
		//loop through each efficiency value in the efficiencies list
		for (int i = 1; i < efficiencies.size(); i++){
			//if the efficiency is smaller than the current min
			if(efficiencies[i] < min) {
			//set min equal to the new smaller value
			smaller = min;
			}
		} 
		
		//if efficiency is bigger than the current max
		//set max to be the new biggest value
		//add efficiency to the running total
		//increment count of values processed 

		//ensure count is > 0 before dividing to get avg 
		//avg = add all up and divide by the count
		//create the result object with the stats variables 
		//if the count was not > 0 return zeros for each stat as the list would be empty
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
