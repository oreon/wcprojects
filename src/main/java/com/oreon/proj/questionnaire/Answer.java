package com.oreon.proj.questionnaire;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

/** 
 *  
 **/

/**
 * This file is an Entity Class generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 */

@Entity
@Table(name = "answer")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
public class Answer extends AnswerBase implements java.io.Serializable {
	private static final long serialVersionUID = 1969982965L;
}