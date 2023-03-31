package de.pdbm.business.api.components.schemas;

import de.pdbm.config.Configuration;

/**
 * RentalHref class as defined in customer.openapi. Holds a string representation of the URI pointing to the corresponding address object.
 */
public class RentalHref {

   private String href;

   public RentalHref(int rentalId){
      href = Configuration.DVD_RENTAL_STORE+"resources/rentals/" + rentalId;
   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }
}


