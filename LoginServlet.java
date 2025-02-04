

import java.io.*;
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.util.*;
import com.mishra.*;

public class LoginServlet extends HttpServlet {
   private String Username, Password;
   private PrintWriter output;


   public void init( ServletConfig config ) throws ServletException
   {
      super.init( config );
      Username = new String("");
      Password = new String("");
   }
   
   public void doGet ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {	doPost(req, res);
   }

   
   
   public void doPost ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {
     
      output = res.getWriter();

          res.setContentType( "text/html" );

    
      Username = req.getParameter( "UserName" );
      Password = req.getParameter( "PassWord" );
      Account Acct = new Account(Username, Password);
      String CustomerName = Acct.signIn();
      if (!CustomerName.equals("")){
		  System.out.println("login username=" + Username);
        
          req.setAttribute("Username", Username);
		  req.setAttribute("CustomerName", CustomerName);
		  req.getRequestDispatcher("/CSCI6810/afterlogin.jsp").forward(req, res);
      }else
           output.println("login failed");
   }


   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}
