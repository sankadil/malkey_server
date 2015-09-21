<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INVOICE ENTRY</title>
<link rel=stylesheet href=../css/style.css type=text/css>
</head>
<body>
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
						<td><label for="refno">Reference No</label></td>
						<td><input id="refno" name="refno" type="text" placeholder="Reference No" required="" autofocus=""></td>
						<td width="250"></td>
						<td><label for="manurefno">Manual No</label></td>
						<td><input id="manurefno" name="manurefno" type="text" placeholder="Manual No" required="" autofocus=""></td>
						</tr>
						</table>
					</li>
					<li>
					<table>
					<tr>
					<td><label for="orderno">Order No</label></td>
					<td><input id="orderno" name="orderno" type="text" placeholder="Order Code" required="" autofocus=""></td>
					<td width="250"></td>
					<td><label for="date">Date</label></td>
					<td><input id="date" name="date" type="date" placeholder="2012/08/12" required="" autofocus=""></td>
					</tr>
					</table>
					</li>
					<li>
					<table>
					<tr>
					<td><label for="curcode">Currency</label></td>
					<td><input id="curcode" name="curcode" type="text" placeholder="Currency Code" required="" autofocus=""></td>
					<td><label for="currate">Exchange Rate</label></td>
					<td><input id="currate" name="currate" type="text" placeholder="Exchange Rate" required="" autofocus=""></td>
					<td><label for="tax">Tax Status</label></td>
					<td>
					<input id="tax" name="tax" type="radio" placeholder="Tax Status" required="" autofocus="" value="1">Yes
					<input id="tax" name="tax" type="radio" placeholder="Tax Status" required="" autofocus="" value="0">No
					</td>
					</tr>
					</table>
					</li>
					<li>
						<label for="debcode">Customer</label>
						<input id="debcode" name="debcode" type="text" placeholder="Customer Code" required="" autofocus="">
						<input id="debdesc" name="debdesc" type="text" placeholder="Customer Description"  autofocus="" class="descrition">
					</li>
					<li>
						<label for="loccode">Location</label>
						<input id="loccode" name="loccode" type="text" placeholder="Location Code" required="" autofocus="">
						<input id="locdesc" name="locdesc" type="text" placeholder="Location Description"  autofocus="" class="descrition">
					</li>
					<li>
						<label for="repcode">Sales Rep</label>
						<input id="repcode" name="repcode" type="text" placeholder="Sales Rep Code" required="" autofocus="">
						<input id="repdesc" name="repdesc" type="text" placeholder="Sales Rep Description"  autofocus="" class="descrition">
					</li>
					<li>
						<label for="costcode">Cost Center</label>
						<input id="costcode" name="costcode" type="text" placeholder="Cost Center Code" required="" autofocus="" >
						<input id="costdesc" name="costdesc" type="text" placeholder="Cost Center Description"  autofocus="" class="descrition">
					</li>
					<li>
						<label for="remarks">Remarks</label>
						<input id="remarks" name="remarks" type="text" placeholder="Remarks" required="" autofocus="" class="large">
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
