package org.witchcraft.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

public class CapitalizeConverter  implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		return StringUtils.capitalize(value);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object modelValue) {
		String value = (String) modelValue;
		return StringUtils.capitalize(value);
	}

}
