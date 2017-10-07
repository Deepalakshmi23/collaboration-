/**
 * 
 */
 app.controller('jobcontroller',function($scope,$location,JobService){
   $scope.showJobDetails=false;
   $scope.saveJob=function(){
	   
	   JobService.saveJob($scope.job).then(function(response){
		   console.log(response.data)
	   alert("ADDED JOB DETAILLS SUCCESSFULLY")
	   },function(response){
		   $scope.error=response.data;
		   console.log(response.status)
		   if(response.status=401)
			   $location.path('/login')
			   $location.path('/savejob')
			   
	   })
	   
	   
   }
 $scope.getJobById=function(id){
	 console.log("job id is" +id);
	 $scope.showJobDetails=true
	 JobService.getJobById(id).then(function(response){
		 $scope.job=response.data
	 },function(response){
		 console.log(response.data)
		 	if(response.status=401)
			   $location.path('/login')
	 })
 }
 
 
 
 function listofJobs(){
	 JobService.listofJobs().then(function(response){
		 console.log(response.data)
		 console.log(response.status)
		 $scope.jobs=response.data
	 },function(response){
		 
		 console.log(response.status)
		 if(response.status=401)
			   $location.path('/login')
		 
		 
	 })
 }
 
 listofJobs()
 
 })
 
 
 
