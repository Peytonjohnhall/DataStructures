/** 
   A driver that demonstrates the class PatientRecord.
 */
public class Driver
{
	public static void main(String args[])
	{
		PatientRecord test1;
		PatientRecord test2;

		try
		{
			test1 = new PatientRecord(101, 12, 15, 2020, "cough", "bed rest");
			System.out.println("Patient Record created:  " + test1);
			System.out.println("It has hash code " + test1.hashCode());
		} // end try
		catch(BadVisitDateException e)
		{
			System.out.println("Creation failed " + e);
		} // end catch

		try
		{
			test2 = new PatientRecord(101, 12, 17, 2020, "high fever", "antibiotics");
			System.out.println("Patient Record created:  " + test2);
			System.out.println("It has hash code " + test2.hashCode());
		} // end try
		catch(BadVisitDateException e)
		{
			System.out.println("Creation failed " + e);
		} // end catch

		try
		{
			test2 = new PatientRecord(101, 17, 17, 2020, "high fever", "antibiotics");
			System.out.println("Patient Record created:  " + test2);
			System.out.println("It has hash code " + test2.hashCode());
		} // end try
		catch(BadVisitDateException e)
		{
			System.out.println("Creation failed " + e);
		} // end catch

		try
		{
			test2 = new PatientRecord(101, 12, 92, 2020, "high fever", "antibiotics");
			System.out.println("Patient Record created:  " + test2);
			System.out.println("It has hash code " + test2.hashCode());
		} // end try
		catch(BadVisitDateException e)
		{
			System.out.println("Creation failed " + e);
		} // end catch

		try
		{
			test2 = new PatientRecord(101, 12, 17, 20, "high fever", "antibiotics");
			System.out.println("Patient Record created:  " + test2);
			System.out.println("It has hash code " + test2.hashCode());
		} // end try
		catch(BadVisitDateException e)
		{
			System.out.println("Creation failed " + e);
		} // end catch
	} // end main
} // end Driver

/*
 Patient Record created:  Patient:101 [12/15/2020] Complaint: cough Prescribed: bed rest
 It has hash code 2115285468
 Patient Record created:  Patient:101 [12/17/2020] Complaint: high fever Prescribed: antibiotics
 It has hash code 1455923027
 Creation failed BadVisitDateException: Month not in range 1-12
 Creation failed BadVisitDateException: Day not in range 1-31
 Creation failed BadVisitDateException: Year not greater than 1900
 */
