package com.mycompany.readtable.cdi;

import com.mycompany.readtable.control.EventController;
import com.mycompany.readtable.dao.Event;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felix
 */

@Named
@SessionScoped
public class EventBean implements Serializable {

    private static final Logger logger = LogManager.getLogger(EventBean.class);

    
    private List<Event> evList;
    
    @EJB
    private EventController evCo;

    public EventBean() {
      
    }

    public List<Event> getListOfEvents() throws IOException {
        evList = evCo.getEventsController();

        logger.info("jetzt sind wir da");
        return evList;
    }
    
    public int getEventCount(){
       evList = evCo.getEventsController();
       int count = evList.size();
       return count;
    }
}
