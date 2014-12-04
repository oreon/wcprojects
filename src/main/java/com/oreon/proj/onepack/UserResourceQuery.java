package com.oreon.proj.onepack;

import javax.ws.rs.Path;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.resteasy.ResourceQuery;

@Name("userResourceQuery")
@Path("user")
public class UserResourceQuery extends ResourceQuery<Customer>
{

}