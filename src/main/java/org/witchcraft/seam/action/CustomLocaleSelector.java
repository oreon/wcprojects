package org.witchcraft.seam.action;

import java.util.Locale;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.LocaleSelector;

@Name("customLocaleSelector")
@Scope(ScopeType.SESSION)
public class CustomLocaleSelector {
	//@In(value = "userprofile", create = true)
	//private Profile profile;

	@In
	private LocaleSelector localeSelector;

	//@In
	//private ProfileDao profileDao;

	public String select() {
		Locale locale = localeSelector.getLocale();
		/*
		 * if (profile != null) { profile.setLocale(locale);
		 * profile.setLanguageCode(locale.getLanguage());
		 * profileDao.merge(profile); }
		 */
		localeSelector.select();
		return "";
	}
}
