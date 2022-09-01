package com.mycompany.readtable.dao.facade;

import com.mycompany.readtable.dao.Event;

import com.mycompany.readtable.dao.facade.AbstractFacade;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Felix
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "felixDatabase")
    private EntityManager em;

    public EventFacade() {
        super(Event.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Event> getEvents () {
        TypedQuery<Event> query = em.createNamedQuery("Event.readAll", Event.class);
        
         List<Event> result = query.getResultList();
        return result;
    }

      

}
