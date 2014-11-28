package org.witchcraft.base.entity;

import java.util.Date;
import java.util.GregorianCalendar;

/** Date helper methods such as find duration/age
 * @author jsingh
 *
 */
public class DateUtils {
	
	/** Calculates age in years from today's date
	 * @param fromDate 
	 * @return age in years 
	 */
	public static int calcAge(Date fromDate){
		if(fromDate == null)
			return 0;
		
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar then = new GregorianCalendar();
		
		then.setTime(fromDate);
		
		return now.get(GregorianCalendar.YEAR) - then.get(GregorianCalendar.YEAR);
		
	}

}
