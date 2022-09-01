package com.mycompany.readtable.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//!!!import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Felix
 */
@Entity
@Table(name = "userdata")
@NamedQuery(name = "User.loginUser", query = "Select u from User u where u.userId = :userId and u.password= :password")
public class User implements Serializable {

    String firstName;
    String lastName;
    @Size(min = 8, message = " Das Passwort muss aus mindestens 8 Zeichen bestehen")
    
    private String password;
    
    @Id
    @Pattern(regexp = "[_\\w-]+(\\.[_\\w-]+)*@[\\w-]+(\\.[\\w]+)*(\\.[A-Za-z]{2,3})", message = " Falsche E-Mail-Adresse")
    private String userId;
    
    
    public User() {
        
    }

    public User(String userId, String password) {
        this.userId = userId;
       
        this.password = password;
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

        
}
