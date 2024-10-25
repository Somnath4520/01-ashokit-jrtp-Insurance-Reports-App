<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2 class="pt-3 pb-3">Report Generator App</h2>
		<form:form action="search" modelAttribute="searchObj" method="POST">
			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names }" />
						</form:select></td>
					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status }" />
						</form:select></td>
					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="FeMale">FeMale</form:option>

						</form:select></td>
				</tr>
				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date"
							date-date-format="dd-mm-yyyy" /></td>
					<td>End Date:</td>
					<td><form:input path="endDate" type="date"
							date-date-format="dd-mm-yyyy" /></td>
				</tr>
				<tr>
					<td><a href="/" class="btn btn-secondary">Reset</a></td>
					<td><input class="btn btn-primary" type="submit"
						value="Search"></td>
				</tr>
			</table>
		</form:form>

		<hr>
			<table class="table table-striped-columns">
				<thead>
					<tr>
						<th>S no:</th>
						<th>Holder Name:</th>
						<th>Gender:</th>
						<th>Plan Name:</th>
						<th>Plan Status:</th>
						<th>Start date:</th>
						<th>End Date:</th>
						<th>Benefit amnt:</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenName}</td>
						<td>${plan.gender}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.start_date}</td>
						<td>${plan.end_date}</td>
						<td>${plan.benefit_amnt}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty plans }">
					<tr>
						<td colspan="8" style="text-align: center;">No record found!</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		<hr>
		Export: <a href="excel" class="btn btn-primary">Excel</a> or <a href="pdf"
			class="btn btn-primary">Pdf</a>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

</body>
</html>