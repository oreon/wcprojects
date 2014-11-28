package org.witchcraft.seam.action;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * @author jagdeep singh
 * 
 * This class holds the state of side navigation menus 
 *
 */
@Name("panelMenuStateBean")
@Scope(ScopeType.SESSION)
public class PanelMenuStateBean {
	
	private String activeIndex;

	public void setActiveIndex(String activeIndex) {
		this.activeIndex = activeIndex;
	}

	public String getActiveIndex() {
		return activeIndex;
	}  
}