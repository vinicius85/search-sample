'use strict';

App.factory('SearchService', [ '$http', '$q', function($http, $q) {
	
	$http.defaults.headers.put = {
	        'Access-Control-Allow-Origin': '*',
	        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
	        'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With'
	        };
	        $http.defaults.useXDomain = true;

	return {

		findArticles : function(query) {
			return $http.get('http://localhost:8080/news/searchForm', {
				params : {
					q : query
				}
			}).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		}

	};

} ]);