/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.readtable.cdi;

import com.mycompany.readtable.control.EventController;
import com.mycompany.readtable.dao.Event;
import com.mycompany.readtable.dao.facade.EventFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import java.io.Serializable;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felix
 */

@Named
@SessionScoped
public class BeanLeiche implements Serializable {
    
    private static final Logger logger = LogManager.getLogger(BeanLeiche.class);
    
    
    @EJB
    private EventController evCo;
    
    private List <Event> evList;
    

    public BeanLeiche() {
       
    }
    
   public List<Event> getListOfEvents() throws IOException {
        evList = evCo.getEventsController();
        
        
        logger.info("jetzt sind wir da");
        return evList;
   }
    
}
