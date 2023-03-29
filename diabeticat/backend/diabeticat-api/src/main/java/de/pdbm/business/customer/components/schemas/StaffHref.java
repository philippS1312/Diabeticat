package de.pdbm.business.customer.components.schemas;

import de.pdbm.config.Configuration;

/**
 * StaffHref class as defined in customer.openapi. Holds a string representation of the URI pointing to the corresponding staff object.
 */
public class StaffHref {

   private String href;

   public StaffHref(int staffId){
      href = Configuration.DVD_RENTAL_STORE+"resources/staff/" + staffId;
   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }

}


