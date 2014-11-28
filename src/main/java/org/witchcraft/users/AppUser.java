package org.witchcraft.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "appuser")
@Filter(name = "archiveFilterDef")
@Name("appUser")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class AppUser extends BaseEntity
		implements
			java.io.Serializable{
	private static final long serialVersionUID = -1510385050L;

	//@Unique(entityName = "com.hrb.tservices.domain.users.AppUser", fieldName = "userName")

	@NotNull
	@Length(min = 1, max = 250)
	@Column(name = "userName", unique = true)
	
	@Analyzer(definition = "entityAnalyzer")
	protected String userName

	;

	@NotNull
	@Column(name = "password", unique = false)
	
	@Analyzer(definition = "entityAnalyzer")
	protected String password

	;

	protected Boolean enabled = true

	;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "appUsers_appRoles", joinColumns = @JoinColumn(name = "appUsers_ID"), inverseJoinColumns = @JoinColumn(name = "appRoles_ID"))
	private Set<AppRole> appRoles = new HashSet<AppRole>();

	@NotNull
	@Length(min = 1, max = 250)
	
	@Analyzer(definition = "entityAnalyzer")
	protected String email

	;

	@Column(name = "lastLogin", unique = false)
	protected Date lastLogin

	;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {

		return userName;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {

		return password;

	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {

		return enabled;

	}

	public void setAppRoles(Set<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

	public Set<AppRole> getAppRoles() {
		return appRoles;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {

		return email;

	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastLogin() {

		return lastLogin;

	}

	@Transient
	public String getDisplayName() {
		try {
			return userName;
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

		listSearchableFields.add("userName");

		listSearchableFields.add("password");

		listSearchableFields.add("email");

		return listSearchableFields;
	}

	
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getUserName() + " ");

		builder.append(getPassword() + " ");

		builder.append(getEmail() + " ");

		return builder.toString();
	}

}
