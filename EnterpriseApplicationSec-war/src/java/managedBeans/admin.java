/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.methodsRemote;

/**
 *
 * @author umangjain
 */
@Named(value = "admin")
@RequestScoped
public class admin implements Serializable{
    
    @EJB
    methodsRemote methods;  
    private String adminemail;
    private int jid;
    private int fid;
    private String fname;
    private String lname;
    private String pass;
    private String urole;

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
    
    public String addUser(){
        String pageType=methods.addUsertoDB(fname, lname, adminemail, pass, urole);
        return pageType;
    }
    public List viewAllOpenJobs(){
        return methods.getAllOpenJobs();
        
    }
    
    public List viewAllClosedJobs(){
        return methods.getAllClosedJobs();
        
    }
    
    public List viewAllProviders(){
        return methods.getAllProviders();
        
    }
    
    public List viewAllFreelancers(){
        return methods.getAllFreelancers();
        
    }
    
    public String admindeleteJobs(){
        String pageType=methods.admindeleteJob(jid);
        return pageType;
    }
    
    public String dAdminUsers(){
        String pageType=methods.adminDeleteUser(fid);
        return pageType;
    }
    
    public admin() {
    }
    
}

