/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author umangjain
 */
@Remote
public interface methodsRemote {

    
    String addJobPosting(String title, String Description, String pay, String keywords, String uEmail);

    List<jobList> getPreviousOpenJobs(String uName);
    
    List<jobList> getPreviousClosedJobs(String uName);

    String deleteJob(int jid);

    List<jobList> viewAllJobs();

    List<jobList> searchByKeywords(String kword);

    List<jobList> searchByJid(String ujid);

    void applyJob(String uName, int jid, int thirdjid);

    void revokeJobReq(String uName, int jid);

    List<freelancerInfo> showFreelancerProfile(String uName);

    String editProfile(String fname, String lname, String skills, String msg, String email);

    List<String> viewApplicants(int jid);

    void assignJob(String email, int jid);

    void markCompleteAndPay(int jid);

   List<jobList> getAllOpenJobs();
   List<jobList> getAllClosedJobs();
   String admindeleteJob(int jid);
   List<freelancerInfo> getAllProviders();
   List<freelancerInfo> getAllFreelancers();
   String adminDeleteUser(int fid);
   String addUsertoDB(String fname, String lname, String email, String pass, String urole);
    
}


