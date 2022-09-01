package com.mycompany.readtable.cdi;

import com.mycompany.readtable.util.ViewContextUtil;
import com.mycompany.readtable.control.LoginController;
import com.mycompany.readtable.dao.User;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.NOT_DONE;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felix
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final Logger logger = LogManager.getLogger(LoginBean.class);
    private static final long serialVersionUID = 7730144564841961508L;
    private boolean loggedIn;

    @EJB // der Applik. Server injiziert  die EJB Bean LoginController Das EJB-Objekt loginController wird über die Annotation @EJB vom EJBContainer (Payara) injiziert.
    private LoginController loginController;

    
    private User user;  

    public LoginBean() { 
    }

    public String doLogin() throws IOException {  //die vom login.xhtml aufgerufenen Methode #{loginBean.doLogin}"
        String forward, caption, msg;
        logger.debug("initiate validation");
        forward = "login.xhtml";
        user = loginController.loadUser(user.getUserId(), user.getPassword());       
        if ( user == null ) {
                logger.debug("Could not find user with id. Possibly credentials are wrong.");
                ViewContextUtil.getFacesContext().validationFailed();            
                caption = "Falsche Benutzerdaten";
                msg = "Benutzerdaten konnten nicht gefunden werden";
                ViewContextUtil.getFacesContext().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, caption, msg));
}
        else{
                logger.debug("Login successful, for user: ".concat(user.getUserId()));
                ViewContextUtil.getFacesContext().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                ViewContextUtil.getFacesContext().getExternalContext().redirect(ViewContextUtil.getFacesContext().getExternalContext().getRequestContextPath() + "/liste.xhtml");
                loggedIn = true;
                forward = "liste.xhtml";
            }
        return forward;
    }

 
    private void resetData() {
        user = null;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    private void switchLoggedIn() {
        this.loggedIn = !loggedIn;
    }

}
