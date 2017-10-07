/**
 * 
 */
app.factory('JobService',function($http){
	var jobservice={}
	var BASE_URL="http://localhost:8085/collabrationbackend"

jobservice.saveJob=function(job){
		return $http.post(BASE_URL + "/savejob",job)
	}
	jobservice.listofJobs=function(){
		return $http.get(BASE_URL + "/getalljobs")
	}
	jobservice.getJobById=function(id){
	return $http.get(BASE_URL + "/getjobbyid/"+id)
	}
return jobservice;

})