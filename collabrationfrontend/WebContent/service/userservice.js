/**
 * 
 */
app.factory('UserService',function($http){
	var userService={}
	var BASE_URL="http://localhost:8085/collabrationbackend"
		/*
		 * user = $scope.user from controller => $scope.user is ng-model=user.x
		 */
	userService.registerUser=function(user){
		alert('entering registerUser in user service')
		return $http.post(BASE_URL + "/registration",user)
	}
	
	userService.login=function(user){
		/*alert('successful login')*/
		return $http.post(BASE_URL + "/login",user)
	}
	userService.logout=function(){
		return  $http.put(BASE_URL + "/logout")
	}
	userService.getUser=function(){
		return  $http.put(BASE_URL + "/getuser")
	}
	userService.updateUser=function(user){
		return	$http.put(BASE_URL + "/updateprofile",user)
		}
	
	
	
	return userService;
})
