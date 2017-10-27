/**
 * 
 */
var app=angular.module("ngApp",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	
	.when('/home',{
		templateUrl:'views/home.html',
		controller:'HomeController'
	})
	.when('/registration',{
		templateUrl:'views/register.html',
		controller:'UserController'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'UserController'
	})
	 .when("/editprofile", {
        templateUrl : 'views/editprofile.html',
        controller:'UserController'
    })
     .when("/savejob", {
        templateUrl : 'views/jobform.html',
        controller:'jobcontroller'
    }) 
    
    .when("/getalljobs", {
    	templateUrl:'views/listofjobs.html',
    	controller:'jobcontroller'
    })
    
     .when("/saveblogpost", {
        templateUrl : 'views/blogpostform.html',
        controller:'BlogPostController'
    })
     .when("/getallblogs", {
        templateUrl : 'views/blogpostlist.html',
        controller:'BlogPostController'
    })
     .when("/blogpostforapproval/:id", {
        templateUrl : 'views/blogpostforapproval.html',
        controller:'BlogPostDetailController'
    })
    .when("/blogpostdetail/:id", {
        templateUrl : 'views/blogpostdetails.html',
        controller:'BlogPostDetailController'
    })
    	.when('/approvalstatus/:id',{
		templateUrl:'views/blogpoststatus.html',
		controller:'BlogPostDetailController'
	})
	 .when('/uploadprofilepic',{
		templateUrl: 'views/uploadprofilepic.html'
	})
	.when('/suggestedusers',{
		templateUrl:'views/suggestedusers.html',
		controller:'FriendController'
		
	})
	.when('/pendingrequests',{
		templateUrl:'views/pendingRequests.html',
		controller:'FriendController'//From C to V
	})
	.when('/getfriends',{
		templateUrl:'views/listoffriends.html',
		controller:'FriendController'//From C to V
	})
	 .when('/chat',{
		templateUrl:'views/Chat.html',
		controller:'ChatCtrl'
	})
	
    
	.otherwise({
		templateUrl:'views/home.html'
	})
})



app.run(function($rootScope,$cookieStore,UserService,$location){
	
	$rootScope.logout=function(){
		UserService.logout().then(function(response){
		 delete $rootScope.currentUser;
		 $cookieStore.remove('currentUser')
		$rootScope.loggedout="logged out successfully"
		 $location.path('/login')
		},function(response){
			console.log(response.status)
		})
	}
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
	
})