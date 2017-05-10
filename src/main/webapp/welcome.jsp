<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Convert your currency</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>

			<div class="container">

				<form:form method="POST" modelAttribute="currencyForm"
					class="form-currency">
					<h2 class="form-signin-heading">Find Currency Rate</h2>
					<spring:bind path="currency">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:select id="selectCurrency" path="currency" class="form-control">
								<option>INR</option>
								<option selected="selected">EUR</option>
								<option>GBP</option>
								<option>NZD</option>
								<option>AUD</option>
								<option>JPY</option>
								<option>HUF</option>
							</form:select>
							<form:errors path="currency"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="date">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input id="datefield" type="date" path="date" class="form-control"
								placeholder="date"></form:input>
							<form:errors path="date"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:hidden path="username" class="form-control"
								value="${pageContext.request.userPrincipal.name}"></form:hidden>
						</div>
					</spring:bind>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
					<h6 style="color:#f45342">* Source is fixed with US as
						free API not allowed to change the source</h6>
				</form:form>
			</div>
		</c:if>
		<div class="forms col-md-12 bounceInDown mainContent"
			data-wow-delay="0.2s">
			<c:if test="${not empty searchedCurrency}">
				<h2 class="form-signin-heading">Result</h2>
				<table class="table table-striped">
					<tr>
						<td>Date</td>
						<td>${searchedCurrency.date}</td>
					</tr>
					<tr>
						<td>Currency</td>
						<td>${searchedCurrency.currency}</td>
					</tr>
					<tr>
						<td>Value</td>
						<td>${searchedCurrency.value}</td>
					</tr>
				</table>
			</c:if>
		</div>
		<div class="forms col-md-12 bounceInDown mainContent"
			data-wow-delay="0.2s">
			<h2 class="form-signin-heading">Latest 10 Results</h2>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Date</th>
						<th>Currency</th>
						<th>Value</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty tasks}">
						<tr>
							<td colspan="8">No history to Display</td>
						</tr>
					</c:if>
					<c:if test="${not empty tasks}">
						<c:forEach items="${tasks}" var="task">
							<tr class="">
								<td>${task.date}</td>
								<td>${task.currency}</td>
								<td>${task.value}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script>
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();
	 	if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 
		today = yyyy+'-'+mm+'-'+dd;
		document.getElementById("datefield").setAttribute("max", today);
		document.getElementById("datefield").setAttribute("value", today);
	</script>
</body>
</html>
