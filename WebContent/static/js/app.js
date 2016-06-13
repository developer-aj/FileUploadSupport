angular.module('myApp', []).directive('ngFiles', [ '$parse', function($parse) {

	function fn_link(scope, element, attrs) {
		var onChange = $parse(attrs.ngFiles);
		element.on('change', function(event) {
			onChange(scope, {
				$files : event.target.files
			});
		});
	}
	;

	return {
		link : fn_link
	}
} ]).controller('fupController', function($scope, $http) {
	$scope.continueFileUpload = function() {
		var formData = new FormData();
		formData.append("file", file.files[0]);
		$http({
			method : 'POST',
			url : '/FileUploadSupport/fileUpload/',
			headers : {
				'Content-Type' : undefined
			},
			data : formData,
			transformRequest : function(data, headersGetterFunction) {
				return data;
			}
		}).success(function(data, status) {
			alert("success");
		})

	};
});