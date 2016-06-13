<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AngularJS File Upload Example with $http and FormData</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet" type="text/css"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"
	type="text/css"></link>
<script src="<c:url value='/static/js/angular.min.js' />"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
</head>

<body ng-app="myApp">
	<div ng-controller="fupController">
		<form method="post" id="fromFileUpload" enctype="multipart/form-data"
			ng-submit="continueFileUpload()">
			<div class="form-group">
				<label class="control-label col-sm-4 col-xs-12" for="file">Please
					upload the file : <span class="required">*</span>
				</label>
				<div class="col-xs-4 input-max controls ">
					<input class="inline-block" type="file" name="file" ng-model="file"
						data-rule-required="true" id="file">
				</div>
				<span id="vaildFile" class="text-success icon-ok hide">Valid
					File</span> <span id="invaildFile" class="text-error icon-remove hide">
					Invalid File</span>
			</div>
			<div class="box-header">
				<div class="actions">
					<button type="submit" class="btn btn-primary">
						<i class="icon-arrow-right"></i> Continue
					</button>
				</div>
			</div>
		</form>
	</div>
</body>