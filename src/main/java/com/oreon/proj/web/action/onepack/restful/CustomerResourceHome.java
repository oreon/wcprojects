package com.oreon.proj.web.action.onepack.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.Home;
import org.jboss.seam.resteasy.ResourceHome;
import org.jboss.seam.resteasy.ResourceQuery;

import java.util.Date;
import java.util.List;

import com.oreon.proj.onepack.Customer;

@Name("customerResourceHome")
@Path("customer")

public class CustomerResourceHome extends ResourceHome<Customer, Long> {
	@In(create = true)
	private EntityHome<Customer> customerAction;

	@Override
	public Home<?, Customer> getEntityHome() {
		return customerAction;
	}
	
	
	public CustomerResourceHome()  
    {  
       setMediaTypes(new String[] {  "application/json" });  
    }  
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getEntity(Long id) {
		Customer cust =  super.getEntity(id);
		customerAction.getEntityManager().detach(cust);
		return cust;
	}

}
