package org.witchcraft.jasperreports;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import net.sf.jasperreports.engine.util.FormatFactory;

/** Return date and number formats
 * @author J Singh
 *
 */
public class WCFormatFactory implements FormatFactory{

	public DateFormat createDateFormat(String pattern, Locale locale,
			TimeZone timezone) {
		return new SimpleDateFormat("dd-MMM-yyyy");
	}

	public NumberFormat createNumberFormat(String pattern, Locale locale) {
		return new DecimalFormat("#0.00");
	}

}
