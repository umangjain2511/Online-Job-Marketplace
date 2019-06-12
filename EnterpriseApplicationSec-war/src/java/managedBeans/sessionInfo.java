/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import service.methodsRemote;

/**
 *
 * @author umangjain
 */
@Named(value = "sessionInfo")
@SessionScoped
public class sessionInfo implements Serializable {

    private final String URL = "jdbc:derby://localhost:1527/Details";
    private final String USER = "adksu";
    private final String PASSWD = "adksu";
    private String email;  
    private String password;    
    private String title;    
    private String desc;    
    private String pay;    
    private String keywords;
    private int jid;
    private String fname;
    private String lname;
    private String skills;
    private String msg;
    private String secEmail;
    private int secJid;
    private String ujid;
    private int thirdjid;
    

    public int getThirdjid() {
        return thirdjid;
    }

    public void setThirdjid(int thirdjid) {
        this.thirdjid = thirdjid;
    }
    
    public String getUjid() {
        return ujid;
    }

    public void setUjid(String ujid) {
        this.ujid = ujid;
    }
    
    public int getSecJid() {
        return secJid;
    }

    public void setSecJid(int secJid) {
        this.secJid = secJid;
    }

    public String getSecEmail() {
        return secEmail;
    }

    public void setSecEmail(String secEmail) {
        this.secEmail = secEmail;
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }  

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }
    
    
    @EJB
    methodsRemote methods;

    /**
     * Get the value of keywords
     *
     * @return the value of keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Set the value of keywords
     *
     * @param keywords new value of keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }


    /**
     * Get the value of pay
     *
     * @return the value of pay
     */
    public String getPay() {
        return pay;
    }

    /**
     * Set the value of pay
     *
     * @param pay new value of pay
     */
    public void setPay(String pay) {
        this.pay = pay;
    }


    /**
     * Get the value of desc
     *
     * @return the value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set the value of desc
     *
     * @param desc new value of desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String check(){
        String pageType= authenticate(email,password);
        return pageType ;
    }
    
   
    public String authenticate(String uEmail, String uPass) {
        String pageType = null;
    try
    {
        PreparedStatement ps;
        Connection conn;
        conn=DriverManager.getConnection(URL,USER,PASSWD);
        ps = conn.prepareStatement("Select email, password, urole, uid from USER_DETAILS where email = ? ");
        ps.setString(1, uEmail);
        System.out.print(ps);
        ResultSet rs = ps.executeQuery();
            rs.next();
            String typeOfUser = rs.getString("urole");
            String checkEmail = rs.getString("email");
            String checkPassword = rs.getString("password");
         if(checkEmail.equals(uEmail) && checkPassword.equals(uPass))
        {
            System.out.println("Login Successful "+checkEmail);
            HttpSession session = SessionUtils.getSession();
			session.setAttribute("usrEmail", uEmail);
            if(typeOfUser.equals("Provider")){
                pageType = "providerPage";
            }
            else if(typeOfUser.equals("Admin"))
                     pageType = "adminHome";
            else
                pageType = "freelancerHome";
        }
        else {
	        FacesContext.getCurrentInstance().addMessage(
	        null,
	        new FacesMessage(FacesMessage.SEVERITY_WARN,
		"Incorrect Username and Passowrd",
		"Please enter correct username and Password"));
                pageType = "index";
        }
    }
    catch (SQLException ex) 
    {
            System.out.println("Login error -->" + ex.getMessage());       
    }     
    return pageType;
    } 
       
    //logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}
    
    public String postJob(){
        String pageType=methods.addJobPosting(title, desc, pay, keywords, email);
        return pageType;
    }
    
    
    public List viewOpenJobs(){
        return methods.getPreviousOpenJobs(email);
        
    }
    
    public List viewClosedJobs(){
        return methods.getPreviousClosedJobs(email);
        
    }
    
    public String dJobs(){
        String pageType=methods.deleteJob(jid);
        return pageType;
    }

    public List browseOpenJobs(){
        return methods.viewAllJobs();
    }
    
    public List searchById()
    {
        return methods.searchByJid(ujid);
    }
    
    public List searchByKey(){
        return methods.searchByKeywords(keywords);
    }
    
    public void jobApply(){
        methods.applyJob(email, jid, thirdjid);
    }
    
    public void jobRevoke(){
        methods.revokeJobReq(email, jid);
    }
    
    public List viewFprofile(){
        return methods.showFreelancerProfile(email);
    }
    
    public List fProfile(){
        return methods.showFreelancerProfile(secEmail);
    }
    
    public String editFProfile(){
        String pageType=methods.editProfile(fname, lname, skills, msg, email);
        return pageType;
    }
    
    public List vApplicants(){
        return methods.viewApplicants(jid);
    }
    
    public void assignJob(){
        methods.assignJob(secEmail, jid);
    }
    
    public void completeAndPay(){
        methods.markCompleteAndPay(secJid);
    }
    
    public sessionInfo() {
    }
    
}
