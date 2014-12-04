package org.witchcraft.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.jboss.seam.Component;
import org.witchcraft.seam.action.UserUtilAction;
import org.witchcraft.users.AppUser;






@MappedSuperclass
@EntityListeners({EntityListener.class})
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = -2225862673125944610L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    @DocumentId
    private Long id;
    
	@Transient
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	private String searchData;
    
    private boolean archived;
    
    @Version
    @Column(name="version")
    transient private Long version;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date dateCreated;
    
    @Transient
    private String higlightedFragment;
    
    
    private Long tenant;
    
     
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="created_by_user_id", nullable=true)
    private AppUser createdByUser;
    
    
    @Transient
    private String highlightedFragment;
    
    public void setHighlightedFragment(String highlightedFragment) {
		this.highlightedFragment = highlightedFragment;
	}

	public String getHighlightedFragment() {
		return highlightedFragment;
	}

	public boolean isNew(){
		return id == null || id.equals(0);
	}
	
    
    
    /*
    public Boolean isArchived() {
		return archived;
	}*/
    
    public Boolean getArchived(){
    	return archived;
    }

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_updated")
    private Date dateUpdated;
    
    public BaseEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
    
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    
    public AppUser getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(AppUser createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
    @Transient
    public String getDisplayName(){
    	return toString();
    }
   
    
    
    
	public List<String> listSearchableFields() {
    	return new ArrayList<String>();
    }
	
	public String getPopupInfo(){
		return getDisplayName();
	}
	
	
	@Override
    public boolean equals(Object o) {
		if(o == null || !(o instanceof BaseEntity))
			return false;
		BaseEntity entity = ((BaseEntity)o);
		if(getId() == null || getId() == 0 || entity.getId() == null || entity.getId() == 0)
			return false;
    	return this.getId() == entity.getId();
    }
	
	public String getCollectionAsString(Collection<? extends BaseEntity> list){
		StringBuffer ret = new StringBuffer();
		for (BaseEntity businessEntity : list) {
			ret.append(businessEntity.getDisplayName() + "; ");
		}
		return ret.toString();
	}

	public void setHiglightedFragment(String higlightedFragment) {
		this.higlightedFragment = higlightedFragment;
	}

	public String getHiglightedFragment() {
		return higlightedFragment;
	}

	public void setSearchData(String searchData) {
		this.searchData = searchData;
	}

	public String getSearchData() {
		return searchData;
	}

	public void setTenant(Long tenant) {
		this.tenant = tenant;
	}

	public Long getTenant() {
		return tenant;
	}
	

	@PrePersist
	// @PreUpdate
	public void updateTenant() {
		// if (this instanceof AppUser)
		// return;

		UserUtilAction userUtilAction = (UserUtilAction) Component
				.getInstance("userUtilAction");

		if (tenant == null || tenant == 0)
			setTenant(userUtilAction.getCurrentTenantId());

	}
	
/*
	public Object onCycleDetected(Context context) {
		return null;
	}
*/
	
}
