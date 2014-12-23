package com.oreon.proj.web.action.onepack;

import org.jboss.seam.annotations.Name;

import com.oreon.proj.onepack.CreditCard;
import com.oreon.proj.onepack.PayPal;

//@Scope(ScopeType.CONVERSATION)
@Name("paymentMethodAction")
public class PaymentMethodAction extends PaymentMethodActionBase
		implements
			java.io.Serializable {
	
	
	
	public void onLoad(){
		getRedirect().captureCurrentView();
		
		if(getInstance() instanceof CreditCard ){
			getRedirect().setViewId("/admin/entities/onepack/creditCard/viewCreditCard.xhtml");
			getRedirect().execute();
		}else if (getInstance() instanceof PayPal ){
			getRedirect().setViewId("/admin/entities/onepack/paypal/viewPaypal.xhtml");
			getRedirect().execute();
		}else{
			
		}
		
		
	
	}
	

}
