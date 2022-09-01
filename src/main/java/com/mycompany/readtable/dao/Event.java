 package com.mycompany.readtable.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Felix
 */
@Entity
@NamedQuery(name = "Event.readAll", query = "SELECT ev FROM Event ev")
public class Event implements Serializable {

    

    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventID;
    
    private String eventTitle;
    private Date eventDate;
    private String eventDescription;

    public Event() {
        
    }
    
    public Event (int EventID, String eventTitle, Date eventDate, String eventDescription){
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
    }
    
    
    @Override
    public Event clone() {
        return new Event(getEventID(),getEventTitle(), getEventDate(), getEventDescription());
    }
    
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    

}
