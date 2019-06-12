/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author umangjain
 */
@WebFilter("/faces/*")
public class AdminAccessFilter implements Filter {
    
    private final String URL = "jdbc:derby://localhost:1527/Details";
    private final String USER = "adksu";
    private final String PASSWD = "adksu";
    
    public AdminAccessFilter() {
        
    }
    
  
    public String getUserRole () {
        String typeOfUser = null;
        String testemail = "rdojenfreelancer@gmail.com";
        try {
        PreparedStatement ps;
        Connection conn;
        conn=DriverManager.getConnection(URL,USER,PASSWD);
        ps = conn.prepareStatement("Select urole from USER_DETAILS where email =" + testemail);
        ResultSet rs = ps.executeQuery();
        rs.next();
        typeOfUser = rs.getString("urole");
        } 
        catch (SQLException ex) {
        
          System.out.println("Login error -->" + ex.getMessage());       
          }
        return typeOfUser;
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
	    HttpSession ses = reqt.getSession(false);
            String path = reqt.getRequestURI().substring(reqt.getContextPath().length());
            if (path.startsWith("/faces/admin") && !getUserRole().equals("admin") )
               resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);            
     
            
        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
        
    @Override
    public void destroy() {

    }
    
}
