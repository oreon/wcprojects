
/**
 * This file can be modified
 *
 */

package com.oreon.proj.onepack;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "department")
@Filters({@Filter(name = "archiveFilterDef")})
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Department extends DepartmentBase implements java.io.Serializable {
	private static final long serialVersionUID = -1926504923L;
}
