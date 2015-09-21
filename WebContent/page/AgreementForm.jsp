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
			
				<legend>Rental Details</legend>
		
				<ol>
					
					<li>
						<table>
						<tr>
						<td><label for="refno">Reference No</label></td>
						<td><input id="refno" name="refno" type="text" placeholder="Reference No" required="" autofocus=""></td>
						<td width="250"></td>
						<td><label for="agrrefno">Agreement No</label></td>
						<td><input id="agrrefno" name="agrrefno" type="text" placeholder="Agreement No" required="" autofocus=""></td>
						</tr>
						</table>
					</li>
					<li>
					<table>
					<tr>
					<td><label for="dateout">Date Out</label></td>
					<td><input id="dateout" name="dateout" type="date" placeholder="2012/10/01" required="" autofocus=""></td>
					<td width="250"></td>
					<td><label for="timeout">Time</label></td>
					<td>
							<select placeholder="select time out" required="" autofocus="">
							  <option value="0">00:00:00</option>
							  <option value="1">00:30:00</option>
							  <option value="2">01:00:00</option>
							  <option value="3">01:30:00</option>
							  <option value="4">02:00:00</option>
							  <option value="5">02:30:00</option>
							  <option value="6">03:00:00</option>
							  <option value="7">03:30:00</option>
							  <option value="8">04:00:00</option>
							  <option value="9">04:30:00</option>
							  <option value="10">05:00:00</option>
							  <option value="11">05:30:00</option>
							  <option value="12">06:00:00</option>
							  <option value="13">06:30:00</option>
							  <option value="14">07:00:00</option>
							  <option value="15">07:30:00</option>
							  <option value="16">08:00:00</option>
							  <option value="17">08:30:00</option>
							  <option value="18">09:00:00</option>
							  <option value="19">09:30:00</option>
							  <option value="20">10:00:00</option>
							  <option value="21">10:30:00</option>
							  <option value="22">11:00:00</option>
							  <option value="23">11:30:00</option>
							</select>
					</td>
					</tr>
					<tr>
					<td><label for="curcode">Num.Days</label></td>
					<td>
					<input type="number" name="numdays" min="1" max="366" placeholder="0" required="" autofocus="">
					</td>
					<td><label for="currate">Chargeable Days</label></td>
					<td><input type="number" name="chargabledays" min="1" max="366" placeholder="0" required="" autofocus=""></td>
					</tr>
					<tr>
					<td><label for="datein">Date In</label></td>
					<td><input id="datein" name="datein" type="date" placeholder="2012/11/01" required="" autofocus=""></td>
					<td width="250"></td>
					<td><label for="date">Date</label></td>
					<td>
							<select placeholder="select time in" required="" autofocus="">
							  <option value="0">00:00:00</option>
							  <option value="1">00:30:00</option>
							  <option value="2">01:00:00</option>
							  <option value="3">01:30:00</option>
							  <option value="4">02:00:00</option>
							  <option value="5">02:30:00</option>
							  <option value="6">03:00:00</option>
							  <option value="7">03:30:00</option>
							  <option value="8">04:00:00</option>
							  <option value="9">04:30:00</option>
							  <option value="10">05:00:00</option>
							  <option value="11">05:30:00</option>
							  <option value="12">06:00:00</option>
							  <option value="13">06:30:00</option>
							  <option value="14">07:00:00</option>
							  <option value="15">07:30:00</option>
							  <option value="16">08:00:00</option>
							  <option value="17">08:30:00</option>
							  <option value="18">09:00:00</option>
							  <option value="19">09:30:00</option>
							  <option value="20">10:00:00</option>
							  <option value="21">10:30:00</option>
							  <option value="22">11:00:00</option>
							  <option value="23">11:30:00</option>
							</select>
					
					</td>
					</tr>
					</table>
					</li>
					<li>
						<label for="debcode">Rate Type</label>
						<input id="ratetype" name="ratetype" type="text" placeholder="Select Rate Type" required="" autofocus="">
						<input id="ratetypedesc" name="ratetypedesc" type="text" placeholder="Rate Type Description"  autofocus="" class="descrition">
<!--					</li>-->
<!--					<li>-->
						<label for="loccode">Hire Type</label>
						<input id="hiretype" name="hiretype" type="text" placeholder="Hire Type Code" required="" autofocus="">
						<input id="hiretypedesc" name="hiretypedesc" type="text" placeholder="Hire Type Description"  autofocus="" class="descrition">
<!--					</li>-->
<!--					<li>-->
						<label for="repcode">Hire Status</label>
						<input id="hirestatus" name="hirestatus" type="text" placeholder="Hire Status" required="" autofocus="">
						<input id="hirestatusdesc" name="hirestatusdesc" type="text" placeholder="Hire Status Description"  autofocus="" class="descrition">
					</li>
					<li>
						<label for="remarks">Remarks</label>
						<input id="remarks" name="remarks" type="text" placeholder="Remarks" required="" autofocus="" class="large">
<!--					</li>-->
<!--					<li>-->
						<label for="remarks">Report Address</label>
						<input id="remarks" name="remarks" type="text" placeholder="Report Address" required="" autofocus="" class="large">
<!--					</li>-->
<!--					<li>-->
						<label for="remarks">Return Address</label>
						<input id="remarks" name="remarks" type="text" placeholder="Return Address" required="" autofocus="" class="large">
					</li>
					<li>
					<table>
					<tr>
					<td><label for="datein">Date In</label></td>
					<td><input id="datein" name="datein" type="date" placeholder="2012/11/01" required="" autofocus=""></td>
					<td width="250"></td>
					<td><label for="date">Date</label></td>
					<td>
							<select placeholder="select time in" required="" autofocus="">
							  <option value="0">00:00:00</option>
							  <option value="1">00:30:00</option>
							  <option value="2">01:00:00</option>
							  <option value="3">01:30:00</option>
							  <option value="4">02:00:00</option>
							  <option value="5">02:30:00</option>
							  <option value="6">03:00:00</option>
							  <option value="7">03:30:00</option>
							  <option value="8">04:00:00</option>
							  <option value="9">04:30:00</option>
							  <option value="10">05:00:00</option>
							  <option value="11">05:30:00</option>
							  <option value="12">06:00:00</option>
							  <option value="13">06:30:00</option>
							  <option value="14">07:00:00</option>
							  <option value="15">07:30:00</option>
							  <option value="16">08:00:00</option>
							  <option value="17">08:30:00</option>
							  <option value="18">09:00:00</option>
							  <option value="19">09:30:00</option>
							  <option value="20">10:00:00</option>
							  <option value="21">10:30:00</option>
							  <option value="22">11:00:00</option>
							  <option value="23">11:30:00</option>
							</select>
					
					</td>
					</tr>
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
