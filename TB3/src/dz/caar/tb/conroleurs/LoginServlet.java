package dz.caar.tb.conroleurs;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;


import javafx.beans.property.SetProperty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

import dz.caar.tb.database.DatabaseConnection;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Codec ORACLE_CODEC = new OracleCodec();
	String result;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	    request.setAttribute("todo", "10");
	    request.getRequestDispatcher("/index.jsp").forward(request, response);
	//	response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id_utilisateur = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,request.getParameter("id_utilisateur"));
		String mdp = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,request.getParameter("mdp"));
		//String id_utilisateur = request.getParameter("id_utilisateur");
		//String mdp = request.getParameter("mdp");
		if(!id_utilisateur.isEmpty() && !mdp.isEmpty()){
		    Connection db = new DatabaseConnection().connect();
			PreparedStatement pst; 
			try {
				 pst = db.prepareStatement
						                ("Select ID_UTILISATEUR,MDP,PROFIL from UTILISATEUR where " +
						                		"ID_UTILISATEUR=? and MDP=?");
				 pst.setString(1, id_utilisateur);
		         pst.setString(2, mdp);
		         ResultSet rs = pst.executeQuery();
		         if (rs.next()) {
			         System.out.println("Correct login credentials");
		        	 if (rs.getString("profil").equalsIgnoreCase("ADMIN"))
					request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		        	 else {
		        		 if (rs.getString("profil").equalsIgnoreCase("ADMIN"))
		            request.getRequestDispatcher("indexTechnique.jsp").forward(request, response); 
		        		 else{
		 		    request.getRequestDispatcher("indexDirecteurGeneral.jsp").forward(request, response); 
		        		 }
		        	 }
		         } 
		         else {
		        	System.out.println("Incorrect login credentials");
		        	result = "login failed";
		        
		        	response.sendRedirect("login.jsp");

		         }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		/*	User currentUser = new User();
			currentUser.setLogin(login);
			currentUser.setPassword(password);
			
			//... et on le stocke dans la session courante.
			request.getSession().setAttribute("user", currentUser);*/
        else {
			// Si l'utilisateur n'a pas rempli les deux champs du formulaire, il est renvoyé sur home.jsp
			response.sendRedirect("index.jsp");
		}
	}

}
