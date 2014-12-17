@org.hibernate.annotations.FilterDefs({
		@org.hibernate.annotations.FilterDef(name = "archiveFilterDef", defaultCondition = "archived = :aArchived", parameters = @org.hibernate.annotations.ParamDef(name = "aArchived", type = "string")),

		@org.hibernate.annotations.FilterDef(name = "tenantFilterDef", defaultCondition = "(tenant is null or tenant = 0 or tenant = :tenantId  )", parameters = @org.hibernate.annotations.ParamDef(name = "tenantId", type = "long"))

})
package com.oreon.proj.questionnaire;
