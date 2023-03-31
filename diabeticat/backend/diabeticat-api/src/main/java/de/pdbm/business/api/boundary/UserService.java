package de.pdbm.business.api.boundary;

import de.pdbm.business.api.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class acts as a communicator to the database.
 */
@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public void removeUser(User user){
        em.remove(user);
    }

    public User getUserById(int userId){
        return em.find(User.class, userId);
    }

    /**
     * Save Data into Database
     * @param user
     */
    public void persistUser(User user) {
        em.persist(user);
    }
}
