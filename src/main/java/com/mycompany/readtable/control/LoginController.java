package com.mycompany.readtable.control;


import com.mycompany.readtable.dao.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felix
 */
@Stateless // Abarbeitung in einem Aufruf ohne Zwischezustand. 
public class LoginController {

    @PersistenceContext (unitName = "felixDatabase")//Dient zur Erzeugung des Entitiymanagers  in der folgenden Zeile
    private EntityManager em; // Verwaltet die Entity Instanzen

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    public User loadUser( String userId, String password ) {
        logger.info("try to query and check user credentials for: " + userId);
        logger.info("password: " + password);
        try {
            TypedQuery<User> query = em.createNamedQuery("User.loginUser", User.class); //TypedQuery =erwarteter Ergebnistyp der Abfrage, also "User"
            query.setParameter("userId", userId);
            query.setParameter("password", password);
            User userSem = query.getSingleResult(); //Execute a SELECT query that returns a single result.
            return userSem; //Das in der DB gefundene User Objekt(das den gegebenen Namen und PW enthällt) wird zurückgegeben.
        } catch ( Exception e ) {
            logger.error(e.getMessage());
            return null;
        }

    }

    private int getNextIdForStudent() {
        String sql = "Select id from idgenerator where class='student'";
        Query query = em.createNativeQuery(sql);
        int studentId = (Integer) query.getSingleResult();
        studentId++;
        sql = "update idgenerator set id=" + studentId + " where class='student'";
        query = em.createNativeQuery(sql);
        query.executeUpdate();
        return studentId;
    }

    

}
