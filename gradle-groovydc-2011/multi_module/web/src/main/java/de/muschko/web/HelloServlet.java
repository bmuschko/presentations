package de.muschko.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import de.muschko.core.Helper;

public class HelloServlet extends HttpServlet {
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {           PrintWriter out = response.getWriter();
      String input = (String)request.getParameter("input");      
      out.println(Helper.capitalize(input));
   }
}
