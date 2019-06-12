/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author umangjain
 */
@Stateless
public class methods implements methodsRemote {

    String URL = "jdbc:derby://localhost:1527/Details";
    String USER = "adksu";
    String PASSWD = "adksu";
    List<jobList> list;
    List<jobList> list1;
    List<jobList> list2;
    List<jobList> list3;
    List<jobList> list4;
    List<freelancerInfo> list5;
    List<jobList> list6;
    List<jobList> list7;
    List<freelancerInfo> list8;
    List<freelancerInfo> list9;

    public List<freelancerInfo> getList8() {
        return list8;
    }

    public void setList8(List<freelancerInfo> list8) {
        this.list8 = list8;
    }
   

    public List<freelancerInfo> getList9() {
        return list9;
    }

    public void setList9(List<freelancerInfo> list9) {
        this.list9 = list9;
    }
    

    public List<jobList> getList7() {
        return list7;
    }

    public void setList7(List<jobList> list7) {
        this.list7 = list7;
    }

    public List<jobList> getList6() {
        return list6;
    }

    public void setList6(List<jobList> list6) {
        this.list6 = list6;
    }

    public List<freelancerInfo> getList5() {
        return list5;
    }

    public void setList5(List<freelancerInfo> list5) {
        this.list5 = list5;
    }
    

    public List<jobList> getList3() {
        return list3;
    }

    public void setList3(List<jobList> list3) {
        this.list3 = list3;
    }

    public List<jobList> getList4() {
        return list4;
    }

    public void setList4(List<jobList> list4) {
        this.list4 = list4;
    }
    

    public List<jobList> getList2() {
        return list2;
    }

    public void setList2(List<jobList> list2) {
        this.list2 = list2;
    }
    

    public List<jobList> getList1() {
        return list1;
    }

    public void setList1(List<jobList> list1) {
        this.list1 = list1;
    }
    

    public List<jobList> getList() {
        return list;
    }

    public void setList(List<jobList> list) {
        this.list = list;
    }
    
    public static String quote(String s) {
    return new StringBuilder()
        .append('\'')
        .append(s)
        .append('\'')
        .toString();
    }
            

    @Override
    public String addJobPosting(String title, String Description, String pay, String keywords, String uEmail) {
        try
        {
        //setEmail = lgn.getEmail();
        PreparedStatement ps;
        Connection conn;
        conn=DriverManager.getConnection(URL,USER,PASSWD);
        String record="INSERT INTO JOB_DETAILS (Title,Job_Desc,Payment,Keywords,added_by,status) VALUES (?,?,?,?,?,?)";
        ps=conn.prepareStatement(record);
        ps.setString(1, title);
        ps.setString(2, Description);
        ps.setString(3, pay);
        ps.setString(4, keywords);
        ps.setString(5, uEmail);
        ps.setString(6, "open");        
        ps.executeUpdate();
        }
        
        catch(SQLException ex1)
        {
            System.out.println("Error -->" + ex1.getMessage());       
        }
        
        String pageType="providerPage";
        return pageType;
    }
    

@Override
    public String addUsertoDB(String fname, String lname, String email, String pass, String urole) {
        try
        {
        PreparedStatement ps;
        Connection conn;
        conn=DriverManager.getConnection(URL,USER,PASSWD); 
        String record="INSERT INTO User_DETAILS (fname,lname,email,password,urole) VALUES (?,?,?,?,?)";
        ps=conn.prepareStatement(record);
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, email);
        ps.setString(4, pass);
        ps.setString(5, urole);       
        ps.executeUpdate();
        }
        
        catch(SQLException ex1)
        {
            System.out.println("Error -->" + ex1.getMessage());       
        }
        
        String pageType="adminHome";
        return pageType;
    }    
    
    @Override
    public List<jobList> getPreviousOpenJobs(String uName) {        
           list = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            String quotedX = quote(uName);
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where added_by = ? and status = ?");
            ps1.setString(1, uName);
            ps1.setString(2, "open");
            System.out.print(quotedX);
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
                System.out.println(rs1.getInt("ujid"));
                list.add(jl);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    @Override
    public List<jobList> getAllOpenJobs() {        
           list6 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);          
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where status = ?");
            ps1.setString(1, "open");
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
               
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
                System.out.println(rs1.getInt("ujid"));
                list6.add(jl);           
            }
          
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list6;
    }
    
    @Override
    public List<jobList> getPreviousClosedJobs(String uName){
        list1 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            String quotedX = quote(uName);
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where added_by = ? and status = ?");
            ps1.setString(1, uName);
            ps1.setString(2, "closed");
            System.out.print(quotedX);
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
               
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
                System.out.println(rs1.getInt("ujid"));
                list1.add(jl);
              
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list1;
    }

    @Override
    public List<jobList> getAllClosedJobs(){
        list7 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
         
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where status = ? or status = ?");
            ps1.setString(1, "closed" );
            ps1.setString(2, "completed");
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
                
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
                System.out.println(rs1.getInt("ujid"));
                list7.add(jl);
              
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list7;
    }      
    
    @Override
    public List<freelancerInfo> getAllProviders(){
        list8 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1 = conn.prepareStatement("Select uid,fname,lname,email,skills,message,acc_money from USER_DETAILS where urole = ?");
            ps1.setString(1, "Provider");
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
            freelancerInfo fi=new freelancerInfo();
                fi.setFid(rs1.getInt("uid"));
                fi.setFname(rs1.getString("fname"));
                fi.setLname(rs1.getString("lname"));
                fi.setFemail(rs1.getString("email"));
                fi.setSkills(rs1.getString("skills"));
                fi.setMsg(rs1.getString("message"));
                fi.setAcc_money(rs1.getInt("acc_money"));
                list8.add(fi);
                
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list8;
    }
    
    @Override
    public List<freelancerInfo> getAllFreelancers(){
        list9 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
         
            ps1 = conn.prepareStatement("Select uid,fname,lname,email,skills,message,acc_money from USER_DETAILS where urole = ?");
            ps1.setString(1, "Freelancer");
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
                freelancerInfo fi=new freelancerInfo();
                fi.setFid(rs1.getInt("uid"));
                fi.setFname(rs1.getString("fname"));
                fi.setLname(rs1.getString("lname"));
                fi.setFemail(rs1.getString("email"));
                fi.setSkills(rs1.getString("skills"));
                fi.setMsg(rs1.getString("message"));
                fi.setAcc_money(rs1.getInt("acc_money"));
                list9.add(fi);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list9;
    }
    
    @Override
    public String deleteJob(int jid) {
         try{
            PreparedStatement ps2;
            Connection conn;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps2=conn.prepareStatement("delete from job_details where ujid = ? and status = ?");
            ps2.setInt(1,jid);
            ps2.setString(2, "open");
            ps2.executeUpdate();
           }
        catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        String pageType= "providerPage";
        return pageType;
    }

    @Override
    public String admindeleteJob(int jid) {
         try{
            PreparedStatement ps2;
            Connection conn;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps2=conn.prepareStatement("delete from job_details where ujid = ?");
            ps2.setInt(1,jid);
            ps2.executeUpdate();
           }
        catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        String pageType= "viewJobonDatabase";
        return pageType;
    }
    
    @Override
    public String adminDeleteUser(int fid) {
         try{
            PreparedStatement ps2,ps3,ps4;
            Connection conn;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps3=conn.prepareStatement("select email from user_details where uid = ?");
            ps3.setInt(1,fid);
            ResultSet rs=ps3.executeQuery();
            rs.next();
            String femail=rs.getString("email");
            ps2=conn.prepareStatement("delete from user_details where uid = ?");
            ps2.setInt(1,fid);
            ps2.executeUpdate();
            
            ps4=conn.prepareStatement("delete from jid_to_fid where freelancer = ?");
            ps4.setString(1,femail);
            ps4.executeUpdate();
           }
        catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        String pageType= "adminHome";
        return pageType;
    }
    
    @Override
    public List<jobList> viewAllJobs() {
        list2 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where status = ?");
            ps1.setString(1, "open");
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
                
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
                System.out.println(rs1.getInt("ujid"));
                list2.add(jl);
             
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list2;
    }

    @Override
    public List<jobList> searchByKeywords(String kword) {
        list3 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where keywords = ? and status = ?");
            ps1.setString(1, kword);
            ps1.setString(2, "open");
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
               
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
                System.out.println(rs1.getInt("ujid"));
                list3.add(jl);
               
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list3;
    }

    @Override
    public List<jobList> searchByJid(String ujid) {
        list4 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1 = conn.prepareStatement("Select ujid,title,job_desc,payment from JOB_DETAILS where ujid = ? and status = ?");
            ps1.setString(1, ujid);
            ps1.setString(2, "open");
            rs1=ps1.executeQuery();
            System.out.print("jid "+ujid);
            while (rs1.next())
            {
              
                jobList jl=new jobList();
                jl.setJid(rs1.getInt("ujid"));
                jl.setTitle(rs1.getString("title"));
                jl.setDesc(rs1.getString("job_desc"));
                jl.setPay(rs1.getInt("payment"));
            
                list4.add(jl);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list4;
        
    }

    @Override
    public void applyJob(String uName, int jid, int thirdjid) {
         try{
            PreparedStatement ps1;
            Connection conn;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1=conn.prepareStatement("insert into jid_to_fid (job_id,freelancer) values (?,?)");
            if(thirdjid==0){
            ps1.setInt(1, jid);
            ps1.setString(2,uName);
            }
            else{
            ps1.setInt(1, thirdjid);
            ps1.setString(2,uName);
            }           
            ps1.executeUpdate();
         } 
         catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void revokeJobReq(String uName, int jid) {
      try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1=conn.prepareStatement("delete from jid_to_fid where job_id = ? and freelancer = ?");
            ps1.setInt(1, jid);
            ps1.setString(2,uName);
            ps1.executeUpdate();
         } 
         catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<freelancerInfo> showFreelancerProfile(String uName) {
        list5 = new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;
            ResultSet rs1;
            conn=DriverManager.getConnection(URL,USER,PASSWD);           
            ps1 = conn.prepareStatement("Select uid,fname,lname,email,skills,message,acc_money from USER_DETAILS where email = ? and urole = ?");
            ps1.setString(1, uName);
            ps1.setString(2, "Freelancer");            
            rs1=ps1.executeQuery();
           
            while (rs1.next())
            {
                freelancerInfo fi=new freelancerInfo();
                fi.setFid(rs1.getInt("uid"));
                fi.setFname(rs1.getString("fname"));
                fi.setLname(rs1.getString("lname"));
                fi.setFemail(rs1.getString("email"));
                fi.setSkills(rs1.getString("skills"));
                fi.setMsg(rs1.getString("message"));
                fi.setAcc_money(rs1.getInt("acc_money"));
                list5.add(fi);                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list5;
    }

    @Override
    public String editProfile(String fname, String lname, String skills, String msg, String email) {
        try{
            PreparedStatement ps1;
            Connection conn;            
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1=conn.prepareStatement("update user_details set fname = ?, lname = ?, skills = ?, message = ? where email = ?");
            ps1.setString(1, fname);
            ps1.setString(2, lname);
            ps1.setString(3, skills);
            ps1.setString(4, msg);
            ps1.setString(5, email);
            ps1.executeUpdate();
         } 
         catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String pageType="freelancerProfile";
        return pageType;
    }

    @Override
    public List<String> viewApplicants(int jid) {
        List<String> li=new ArrayList<>();
        try{
            PreparedStatement ps1;
            Connection conn;            
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1=conn.prepareStatement("select freelancer from jid_to_fid where job_id = ?");
            ps1.setInt(1, jid);
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next())
            {
                li.add(rs1.getString("freelancer"));
            }
        
           }catch (SQLException ex) {   
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
            }   
        return li;
    }

    @Override
    public void assignJob(String email, int jid) {
        try{
            PreparedStatement ps1,ps2;
            Connection conn;            
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1=conn.prepareStatement("update job_details set assigned_to = ?, status = ? where ujid = ?");
            ps1.setString(1, email);
            ps1.setString(2, "closed");
            ps1.setInt(3, jid);
            ps1.executeUpdate();
            ps2 = conn.prepareStatement("delete from jid_to_fid where freelancer = ? and job_id = ?");
            ps2.setString(1, email);
            ps2.setInt(2, jid);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void markCompleteAndPay(int jid) {
        int pay,prePay,newPay;
        String freelancer;
        try{
            PreparedStatement ps1,ps2,ps3,ps4;
            Connection conn;            
            conn=DriverManager.getConnection(URL,USER,PASSWD);
            ps1=conn.prepareStatement("update job_details set status = ? where ujid = ?");
            ps1.setString(1, "completed");
            ps1.setInt(2, jid);
            ps1.executeUpdate();
            ps2=conn.prepareStatement("select payment,assigned_to from job_details where ujid = ?");
            ps2.setInt(1, jid);
            ResultSet rs2=ps2.executeQuery();
            rs2.next();
            pay=rs2.getInt("payment");
            freelancer=rs2.getString("assigned_to");
            ps3=conn.prepareStatement("select acc_money from user_details where email = ?");
            ps3.setString(1,freelancer);
            ResultSet rs3=ps3.executeQuery();
            rs3.next();
            prePay=rs3.getInt("acc_money");
            newPay=pay+prePay;
            ps4=conn.prepareStatement("update user_details set acc_money = ? where email = ?");
            ps4.setInt(1, newPay);
            ps4.setString(2, freelancer);
            ps4.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
