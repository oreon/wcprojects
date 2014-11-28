package org.witchcraft.base.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name = "comment")
@Name("entitycomment")
@NamedQuery(name="commentsForRecord", query= "Select c from EntityComment c where c.entityId = ?1 and c.entityName = ?2 ")
public class EntityComment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3936580980250027302L;

	@NotNull
	private String entityName;
	
	@NotNull
	private Long entityId;

	@Lob
	protected String text;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Transient
	public String getDisplayName() {
		return text + "";
	}

}
