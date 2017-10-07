/**
 * 
 */
app.controller('BlogPostController',function($scope,$location,BlogPostService){
	$scope.saveblogpost=function(){
		console.log($scope.blogpost)
		alert("ADDED Blog DETAILLS SUCCESSFULLY")
		BlogPostService.saveblogpost($scope.blogpost).then(function(response){
			$location.path("/home")
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path="/login"
			else
				$location.path="/saveblogpost"
		})
		
	}
	
	function listofblogsapproved(){
		BlogPostService.listofblogsapproved().then(function(response){
			$scope.blogpostsapproved=response.data
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path="/login"
				})
		
	}
	function listOfBlogsWaitingForApproval(){
		BlogPostService.listOfBlogsWaitingForApproval().then(function(response){
			console.log(response.data)
			$scope.blogPostsWaitingForApproval=response.data
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	listofblogsapproved()
	listOfBlogsWaitingForApproval()
	
})
