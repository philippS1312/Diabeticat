package de.pdbm.business.api.components.schemas;

import de.pdbm.config.Configuration;


/**
 * StoreHref class as defined in customer.openapi. Holds a string representation of the URI pointing to the corresponding store object.
 */
public class StoreHref {

   private String href;

   public StoreHref(int storeId){
      href = Configuration.DVD_RENTAL_STORE+"resources/stores/" + storeId;
   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }

}


