
/**
 * This file can be modified
 *
 */

package com.oreon.proj.univeris.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "investorprofile")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class InvestorProfile extends InvestorProfileBase
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1062433502L;
}
