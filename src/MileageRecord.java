// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 06/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: Skeleton for MileageRecord class contains definitions of the MileageRecord objects and has getters for each record member

import java.time.LocalDate;

public class MileageRecord {

    private LocalDate date;
    private int kilometers;
    private double liters;
    private Double efficiency;	//Double allows setting to = null we need this here for the first reading which wont have an efficiency

    public MileageRecord(LocalDate date, int kilometers, double liters) {
        this.date = date;
        this.kilometers = kilometers;
        this.liters = liters;
        this.efficiency = null;	//initialize to null
    }

    public LocalDate getDate() {
        return date;
    }

    public int getKilometers() {
        return kilometers;
    }

    public double getLiters() {
        return liters;
    }
	
	//Double type object to allow setting to Null vlaue for the initial object which wont have an efficiency
    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "MileageRecord : " + "date = " + date + ", kilometers = " + kilometers + ", liters = " + liters + ", efficiency = " + efficiency;
    }
	
	//standalone test for MileageRecord
	//expected output:
	//MileageRecord: date= 2025-01-02, kilometers= 24886, liters= 27.59, efficiency= Null
	public static void main(String[] args) {   
		MileageRecord record = new MileageRecord(
            java.time.LocalDate.of(2025,1,2),
            24886,
            27.59
		);

		System.out.println(record);
	}
	
}