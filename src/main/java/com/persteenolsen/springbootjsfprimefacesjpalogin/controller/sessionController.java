package com.persteenolsen.springbootjsfprimefacesjpalogin.controller;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
//import javax.faces.flow.FlowScoped;
//import javax.faces.view.ViewScoped;
import java.io.Serializable;

// Used for testing HttpSession information
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
//import java.util.List;

// Used for testing ServletRequest
import javax.faces.context.*;

// For getting the Servlet Request and HttpSession the good old way
//import javax.servlet.http.*;

//import javax.inject.Inject;

//import javax.annotation.PostConstruct;


//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;

@Named(value = "sessionController")
@SessionScoped
//@ViewScoped
//@FlowScoped(value = "personController")
public class sessionController implements Serializable {

    
  private static final long serialVersionUID = 1L;

    
    // Just for Demo - Class members
    //private String name;
    //private String email;
    //private Integer age;

    // Just a constructor
    public sessionController() {
    }


    // Get the memory info for the Java Virtuel Machine
    public String getJVMHeap(){
        
        int mb = 1024*1024;
        
	    Runtime runtime = Runtime.getRuntime();
	
	    long usedmemory = (runtime.totalMemory() - runtime.freeMemory()) / mb;

	    long freememory = runtime.freeMemory() / mb;

	    long totalmemory = runtime.totalMemory() / mb;
	
	    long maxmemory = runtime.maxMemory() / mb;
	
	    String memoryheapS = "JVM Memory info in MB - Used: " + usedmemory + " Free: " + freememory + " Total: " + totalmemory + " Max: " + maxmemory;

        return memoryheapS;
    }

    // Get all the HttpSessions but I disabled the Values  
    public String getSessionNamesHTML(){
        
        String currentSessions = "";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        Enumeration<String> e = session.getAttributeNames();
                        
        while (e.hasMoreElements())
        {
          String attr = (String) e.nextElement();
          currentSessions = currentSessions + "Session Name:" + "<br />" + attr + "<br /><br />";

        // The value of the Session
         // Object value = session.getAttribute(attr);
         // currentSessions = currentSessions + "Session Value:" + "<br />"+  value  + "<br /><br />";
         
        }
       return currentSessions;
      }
        
    
    // JUST FOR DEMO: Getting the Servlet Request method to get the HttpSession the old way
    /*public String getSessionNamesServletRequest(){

    String currentSessions = "";
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    Object requestObj = context.getRequest();

    if(requestObj instanceof HttpServletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) requestObj;
        HttpSession session = httpRequest.getSession(false);
        
        // Note: From here it is like the method above and I could just have make a method call
        Enumeration<String> e = session.getAttributeNames();
        
        while (e.hasMoreElements())
           {
            String attr = (String) e.nextElement();
            currentSessions = currentSessions + "Session Name:" + "<br />" + attr + "<br /><br />";
  
            // The value of the Session
            Object value = session.getAttribute(attr);
            currentSessions = currentSessions + "Session Value:" + "<br />"+  value  + "<br /><br />";
            }
         }
      return  currentSessions;
    }*/
    

    // NOTE: Get and Set methods goes here and below are just for demo! 
    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/


}
