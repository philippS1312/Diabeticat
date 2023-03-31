package de.pdbm.business.api.components.schemas;

import de.pdbm.business.api.entity.old.Address;
import de.pdbm.config.Configuration;

/**
 * AddressHref class as defined in customer.openapi. Holds a string representation of the URI pointing to the corresponding address object.
 */
public class AddressHref {

   private String href;

   public AddressHref(Address address){
      this.href = Configuration.DVD_RENTAL_CUSTOMER+"resources/addresses/" + address.getAddress_id();
   }

   public AddressHref(){

   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }
}


