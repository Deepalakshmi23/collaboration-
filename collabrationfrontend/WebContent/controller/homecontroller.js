/**
 * 
 */
app.controller('HomeController',function($scope,$rootScope,BlogPostService,FriendService,$location,$interval){
	function getApprovalStatus(){
		BlogPostService.getApprovalStatus().then(function(response){
			/*
			 * list of blog posts which are approved /rejected by admin and user is not yet notified
			 */
			$rootScope.approvalStatus=response.data
			console.log($rootScope.approvalStatus.length)
		},function(response){
			console.log(response.status)
		})
	}

	function pendingRequests(){
		FriendService.pendingRequests().then(function(response){
			$scope.pendingRequests=response.data//List of Friend objects [use only fromId])
			$rootScope.pendingRequests=response.data
			$rootScope.pendingRequestsLength=$scope.pendingRequests.length
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	function getFriendsLength(){
		FriendService.getFriends().then(function(response){
			$scope.friends=response.data;
			$rootScope.noOfFriends=$scope.friends.length;
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$rootScope.reset=function(){
		$rootScope.pendingRequestsLength=0
	}
	$rootScope.updatePendingRequest=function(request,value){
		console.log('pending request ' + request)
		request.status=value //value is 'A' for accept and 'D' for delete
		console.log('after assigning value to status  ' + request)
		FriendService.updatePendingRequest(request).then(function(response){
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	$rootScope.updateViewedStatus=function(){
		$rootScope.approvalStatusLength=0;
		BlogPostService.updateViewedStatus($rootScope.approvalStatus).then(function(response){
			console.log(response.data)
		},function(response){
			console.log(response.status)
		})
	}
	getApprovalStatus();
    pendingRequests()
	getApprovalStatus();
	
	
//   $interval(function(){
//		getFriendsLength();
//		pendingRequests()
//		
//		getApprovalStatus();
//	},50000)
	
	
		
})