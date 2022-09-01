/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.readtable.cdi;

import com.mycompany.readtable.control.EventController;
import com.mycompany.readtable.dao.Event;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Felix
 */
@Named
@SessionScoped
public class CrudView implements Serializable {

    private List<Event> events;

    private Event selectedEvent;

    private static final Logger logger = LogManager.getLogger(EventBean.class);

    @EJB
    private EventController evCo;

    public List<Event> getEvents() {
        return events;
    }

    public Event getSelectedEvent() {
        logger.info("jetzt sind wir in der CrudView. getSelectedEvent");
        return selectedEvent;

    }

    public void setSelectedEvent(Event selectedEvent) {
        logger.info("jetzt sind wir in setselectedEvent");
        this.selectedEvent = selectedEvent;
    }

    public void openNew() {
        this.selectedEvent = new Event();
    }

    public void saveEvent() {

        
        logger.info("hier ist saveEvent");
        evCo.newEvent(selectedEvent);

        PrimeFaces.current().executeScript("PF('manageEventDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Events");
    }

    public void deleteEvent() {

        evCo.removeEvent(selectedEvent);

        this.events.remove(this.selectedEvent);
        this.selectedEvent = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Events");
    }

    public void updateEvent() {
        logger.info("hier ist update Event");
        evCo.updateEvent(selectedEvent);

        
    }

    
    public void testMethode() {
        logger.info("hier ist die Debug-Methode");
    }
    
    
    

}
