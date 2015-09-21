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

<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%><html>
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
	    	
	    	String agrno=request.getParameter("agrno");
	    	FreservationSRV srv = (FreservationSRV) wac.getBean("freservationSRV");
			List<Freservation> list=new ArrayList();
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
	    	if(agrno!=null)
	    	{
	    		if(agrno.length()>0)
	    		{
	    			list=srv.listByHedAgrno(agrno);
	    		}
	    		else
	    		{
	    			response.sendRedirect("http://www.w3schools.com/tags/tag_a.asp");
	    		}
	    	}
	    	else
	    	{
	    		response.sendRedirect("http://www.w3schools.com/tags/default.asp");
	    	}


%>	

		<form id="payment"  action="../HelloWorld" method="post">
			<fieldset>
				<ol>
					<li>
					<div>
						<button type="submit">Add</button>
						<button type="submit">Edit</button>
						<button type="submit">Delete</button>
						<button type="submit">Undo</button>
						<button type="submit">Find</button>
						<button type="submit">Save</button>
						<button type="submit">Close</button>
				</div>
				</li></ol>
			</fieldset>
			<fieldset>
				<legend>Genaral</legend>
				<ol>
					<li>
						<table>
						<tr>
						<td><label for="">Reservation No</label></td>
						<td><label for="">Hire Type</label></td>
						<td><label for="">Rate Type</label></td>
						<td><label for="">Status</label></td>
						<td><label for="">Date Out</label></td>
						<td><label for="">Date In</label></td>
						</tr>
						<%for(int i=0;i<list.size();i++){%>
						<tr>
						<td width="250"><label for="resno"><%out.println(((Freservation)(list.get(i))).getAgrno());%></label></td>
						<td width="250"><label for="hiretype"><%out.println(((Freservation)(list.get(i))).getResno());%></label></td>
						<td width="250"><label for="ratetype"><%out.println(((Freservation)(list.get(i))).getDebcode());%></label></td>
						<td width="250"><label for="status"><%out.println(((Freservation)(list.get(i))).getCohirestsid());%></label></td>
						<td width="250"><label for="dateout"><%out.println(simpleDateFormat.format(((Freservation)(list.get(i))).getDout().getTime()));%></label></td>
						<td width="250"><label for="datein"><%out.println(simpleDateFormat.format(((Freservation)(list.get(i))).getDin().getTime()));%></label></td>
						<td width="650"><a href="http://localhost:8400/malkey_server/page/AgreementView.jsp?resno=<%((Freservation)(list.get(i))).getResno();%>" >EDIT</a></td>
						<td width="650"><a href="http://localhost:8400/malkey_server/page/AgreementView.jsp?resno=<%((Freservation)(list.get(i))).getResno();%>" >DELETE</a></td>
						<td width="650"><a href="http://localhost:8400/malkey_server/page/AgreementView.jsp?resno=<%((Freservation)(list.get(i))).getResno();%>" >VIEW</a></td>
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
							<table>
					<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					</tr>
					</table>
</body>
</html>
