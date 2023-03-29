package de.pdbm.business.customer.components.schemas;

import de.pdbm.business.customer.entity.Customer;
import de.pdbm.config.Configuration;

/**
 * CustomerHref class as defined in customer.openapi. Holds a string representation of the URI pointing to the corresponding customer object.
 */
public class CustomerHref {

   private String href;

   public CustomerHref(Customer customer){
      this.href = Configuration.DVD_RENTAL_CUSTOMER+"resources/customers/"+customer.getCustomer_id();
   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }
}


