
/**
 * This file can be modified
 *
 */

package com.oreon.proj.onepack;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "customer")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
@JsonSerialize
@JsonIgnoreProperties(value = { "handler", "javassistLazyInitializer" })
public class Customer extends CustomerBase implements java.io.Serializable {
	private static final long serialVersionUID = -1154459983L;
}
