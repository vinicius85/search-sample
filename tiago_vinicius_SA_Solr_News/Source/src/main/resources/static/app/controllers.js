'use strict';

App.controller('SearchController', [ '$scope', 'SearchService',
		function($scope, SearchService) {
			var self = this;
			self.search = {
				id : null,
				title : '',
				content : '',
				image: ''
			};
			self.searches = [];

			self.findArticles = function() {
				var button = document.getElementById("searchButton"),
				query =  button.form.queryInput.value;
				SearchService.findArticles(query).then(function(data) {
					self.searches = data;
					$scope.articles = self.searches;
				}, function(errResponse) {
					console.error('Error while fetching articles');
				});
			};

			self.findArticles("*:*");

		} ]);