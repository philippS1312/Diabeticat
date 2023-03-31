package de.pdbm.business.api.boundary;


import de.pdbm.business.api.entity.MeasurementData;
import de.pdbm.business.api.entity.Pet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MeasurementService {

    @PersistenceContext
    EntityManager em;

    public MeasurementData getMeasurementDataById(int measurementId){ return em.find(MeasurementData.class, measurementId);}

    public List<MeasurementData> getMeasurementDataByPet(Pet pet){
        return em.createQuery("SELECT m FROM MeasurementData m WHERE m.pet.id = :id", MeasurementData.class).setParameter("id", pet.getId()).getResultList();
    }

    public void deleteMeasurementData(MeasurementData measurementData){
        em.remove(measurementData);
    }

    /**
     * Save Data to the DataBase
     * @param measurementData
     */
    public void persistMeasurement(MeasurementData measurementData) {
        em.persist(measurementData);
    }
}
