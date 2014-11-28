package org.witchcraft.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Length;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BaseEntity;



@Entity
@Table(name = "approle")
@Filter(name = "archiveFilterDef")
@Name("appRole")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class AppRole extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -1618836781L;

	//@Unique(entityName = "com.hrb.tservices.domain.users.AppRole", fieldName = "name")

	@NotNull
	@Length(min = 1, max = 250)
	@Column(name = "name", unique = true)
	
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@ManyToMany(mappedBy = "appRoles")
	private Set<AppUser> appUsers = new HashSet<AppUser>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setAppUsers(Set<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	public Set<AppUser> getAppUsers() {
		return appUsers;
	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		return listSearchableFields;
	}

	
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		return builder.toString();
	}

}
