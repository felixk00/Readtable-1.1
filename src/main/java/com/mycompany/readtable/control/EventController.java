/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.readtable.control;

import com.mycompany.readtable.dao.Event;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felix
 */
@Stateless
public class EventController {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(EventController.class);

    public List<Event> getEventsController() {
        logger.info("Methode getEventsController wurde aufgerufen");

        try {
            TypedQuery<Event> query = em.createNamedQuery("Event.readAll", Event.class);

            List<Event> result = query.getResultList();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public void removeEvent(Event event) {
        int eID = event.getEventID();

        Event ev = em.find(Event.class, eID);
        em.remove(ev);

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            fc.getExternalContext().redirect(request.getRequestURI());
        } catch (IOException ioe) {
            logger.info("Hier gibt es eine IOException");
        }
    }

    public void updateEvent(Event event) {
        int eID = event.getEventID();

        Event ev = em.find(Event.class, eID);
        logger.info("hier ist updateEvent" + event.getEventTitle() );
        logger.info("hier ist updateEvent ev vorher " + ev.getEventTitle() );
        ev.setEventTitle(event.getEventTitle());
        logger.info("hier ist updateEvent ev" + ev.getEventTitle() );
        ev.setEventDescription(event.getEventDescription());
        
        
        ev.setEventDate(event.getEventDate());
        em.merge(ev);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            fc.getExternalContext().redirect(request.getRequestURI());
        } catch (IOException ioe) {
            logger.info("Hier gibt es eine IOException");
        }
    }

    public void newEvent (Event event) {
        
        
                             
        logger.info("hier ist newEvent" + event.getEventTitle() );
       
        
        em.persist(event);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            fc.getExternalContext().redirect(request.getRequestURI());
        } catch (IOException ioe) {
            logger.info("Hier gibt es eine IOException");
        }
    }
    
}
