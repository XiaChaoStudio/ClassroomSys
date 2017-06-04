<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
  Object username = session.getAttribute("LoginUserId");  
  if(null == username){  
      response.sendRedirect("Users_login.jsp");  
  }  
%> 