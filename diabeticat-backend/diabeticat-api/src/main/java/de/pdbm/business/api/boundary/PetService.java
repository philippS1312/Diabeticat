package de.pdbm.business.api.boundary;


import de.pdbm.business.api.entity.Pet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PetService {

    @PersistenceContext
    EntityManager em;

    public Pet getPetById(int petId){return em.find(Pet.class, petId);
    }

    public void deletePetById(Pet pet){
         em.remove(pet);
    }


    /**
     * Save Pet into Database
     * @param pet
     */
    public void persistPet(Pet pet) {
        em.persist(pet);
    }

}
