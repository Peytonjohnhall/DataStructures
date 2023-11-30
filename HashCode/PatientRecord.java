import java.util.Objects;

/** Overrides the method hashcode, from the Driver class.
	@author Peyton J. Hall
	Precondition:  All parameters meet the specified conditions.
	Postcondition: Either the object is initialized, with valid data,
				   or an exception is thrown, if data is invalid.
*/
public class PatientRecord
{
  private int patientID;
  private int month;
	private int day;
	private int year;
	private String reason;
	private String treatment;

	/** A constructor to check if everything satisfies the conditions.
		@param patientID 	    The patient ID.
		@param month 		      The month of the visit date.
		@param day 			      The day of the visit date.
		@param year 		      The year of the visit date.
		@param reasonForVisit The reason for the visit.
		@param treatment 	    The prescribed treatment.
		Precondition:  		    All parameters meet the specified conditions.
	  Postcondition: 		    Private variables are passed as parameters.
	    					          The object is initialized, with either valid 
	    					          data, or a thrown exception from invalid data.
	  @throws BadVisitDateException If parameter value(s) is/are invalid.
	*/
	public PatientRecord(int patientID, int month, int day, int year,
						           String reasonForVisit, String treatment)
            			     throws BadVisitDateException
    {
        setPatientID(patientID);

        if (month < 1 || month > 12) 
        {
	        throw new BadVisitDateException("Month not in range 1-12");
	      }
        setMonth(month);

        if (day < 1 || day > 31) 
        {
	        throw new BadVisitDateException("Day not in range 1-31");
	      }
        setDay(day);

        if (year <= 1900) 
        {
	        throw new BadVisitDateException("Year not greater than 1900");
	      }
        setYear(year);

        setVisitReason(reasonForVisit);
        setTreatmentType(treatment);
    } // end PatientRecord method

	/**
		Precondition:  None.
		Postcondition: Returns the value of the patient ID.
	*/
	public int getPatientID()
	{
		return patientID;
	} // end getPatientID

	/**
		@param id      The patient id to set.
		Precondition:  None.
    Postcondition: Stores the value of the parameter, id,
        			     to the private field, patientID.
	*/
	public void setPatientID(int id)
	{
		patientID = id;
	} // end setPatientID

	/**
		Precondition:  None.
    Postcondition: Returns the value of the month.
	*/
	public int getMonth()
	{
		return month;
	} // end getMonth

	/**
		@param theMonth The month to set.
		Precondition:   None.
    Postcondition:  Stores the value of the parameter, theMonth,
        				    to the private field, month.
	*/
	public void setMonth(int theMonth)
	{
		month = theMonth;
	} // end setMonth

	/**
		Precondition:  None.
    Postcondition: Returns the value of the day.
	*/
	public int getDay()
	{
		return day;
	} // end getDay

	/**
		@param theDay  The day to set.
		Precondition:  None.
    Postcondition: Stores the value of the parameter, theDay,
        			     to the private field, day.
	*/
	public void setDay(int theDay)
	{
		day = theDay;
	} // end setDay

	/**
		Precondition:  None.
    Postcondition: Returns the value of the year.
	*/
	public int getYear()
	{
		return year;
	} // end getYear

	/**
		@param theYear The year to set.
		Precondition:  None.
    Postcondition: Stores the value of the parameter, theYear,
        			     to the private field, year.
	*/
	public void setYear(int theYear)
	{
		year = theYear;
	} // end setYear

	/**
		Precondition:  None.
    Postcondition: Returns the reason as a string.
	*/
	public String getVisitReason()
	{
		return reason;
	} // end getVisitReason

	/**
		@param theReason The reason for the visit.
		Precondition:    None.
    Postcondition:   Stores the value of the parameter, theReason,
        				     to the private field, reason.
	*/
	public void setVisitReason(String theReason)
	{
		reason = theReason;
	} // end setVisitReason

	/**
		Precondition:  None.
    Postcondition: Returns the treatment type as a string.
	*/
	public String getTreatmentType()
	{
		return treatment;
	} // end getTreatmentType

	/**
		@param theType The type of treatment.
		Precondition:  None.
    Postcondition: Stores the value of the parameter, theType,
        			     to the private field, treatment.
	*/
	public void setTreatmentType(String theType)
	{
		treatment = theType;
	} // end setTreatmentType

  /** Overrides the hashCode method.
		Precondition:  None.
    Postcondition: Assigns a random unique integer to each record.
	*/
	@Override
  public int hashCode()
  {
    int thisHashCode = Objects.hash(patientID, month, day, year, reason, treatment);
    if (thisHashCode < 0)
    {
      return Integer.MAX_VALUE + thisHashCode;
    }
	  return thisHashCode;
  } // end hashCode

  /** Overrides the toString method.
	Precondition:  None.
  Postcondition: Returns a formatted string, which contains 
        			   patient information and the corresponding hash code.
        			   Information is inserted on placeholders: %d, %02d, %s.
	*/
  @Override
  public String toString() 
  {
    // %d corresponds to an integer (patientID, month, day, year).
    // %02d corresponds to an integer with zero-padding (month & day),
    // that is, a 2-width minimum decimal integer.
    // %s corresponds to a string (reason & treatment).
    return String.format("Patient:%d [%02d/%02d/%d] Complaint: %s" + 
        					       " Prescribed: %s", patientID, month, day, 
        					       year, reason, treatment);
    } // end toString
} // end PatientRecord class
