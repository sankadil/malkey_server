<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.dspl.malkey.domain.Freservation"%>
<%@page import="com.dspl.malkey.service.FreservationSRV"%>
<%@page import="java.util.List"%>
<%@page import="com.dspl.malkey.domain.Freshed"%>
<%@page import="com.dspl.malkey.service.FreshedSRV"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INVOICE ENTRY</title>
<link rel=stylesheet href=../css/style.css type=text/css>
</head>
<body>

<%
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	    try
	    {
	    	FreshedSRV srv = (FreshedSRV) wac.getBean("freshedSRV");
		List<Freshed> list= srv.listAllDescription();

%>	
	
		<form id="payment"  action="../HelloWorld" method="post">
			<fieldset>
				<ol>
					<li>
					<!-- <div>
						<button type="submit">Add</button>
						<button type="submit">Edit</button>
						<button type="submit">Delete</button>
						<button type="submit">Find</button>
						<button type="submit">Save</button>
						<button type="submit">Close</button>
					</div>
					 -->
				</li></ol>
			</fieldset>
			
			<fieldset>
			
				<legend>Select Agreement</legend>
		
				<ol>
					<li>
						<table>
						<tr>
						<td><label for="agrno">Agreement No</label></td>
						<td><label for="refno">Reservation NO</label></td>
						<td><label for="debcode">Debtor Code</label></td>
						<td><label for="debname">Debtor Name</label></td>
						<td><label for="companyid">Company ID</label></td>
						<td><label for="companyname">Company Name</label></td>
						</tr>
						<%for(int i=0;i<list.size();i++){%>
						
						<tr>
						<td width="250"><label for="agrno"><%out.println(((Freshed)(list.get(i))).getAgrno());%></label></td>
						<td width="250"><label for="resno"><%out.println(((Freshed)(list.get(i))).getUuid());%></label></td>
						<td width="250"><label for="debcode"><%out.println(((Freshed)(list.get(i))).getDebcode());%></label></td>
						<td width="250"><label for="debname"><%out.println(((Freshed)(list.get(i))).getDebname());%></label></td>
						<td width="250"><label for="companyid"><%out.println(((Freshed)(list.get(i))).getCompanyid());%></label></td>
						<td width="650"><label for="companyname"><%out.println(((Freshed)(list.get(i))).getCompany());%></label></td>
						<td width="650"><a href="http://localhost:8400/malkey_server/page/AgreementView.jsp?agrno=<%out.println(((Freshed)(list.get(i))).getAgrno());%>" >EDIT</a></td>
						</tr>
						
						<%
						}
						}
	    catch(Exception e)
					    {
					    	System.out.println("ERROR in JSP PAge");
					    }
					    %>
						</table>
					</li>
				</ol>
			
			</fieldset>
		</form>
						
</body>
</html>
