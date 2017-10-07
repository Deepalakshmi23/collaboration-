/**
 * 
 */
app.factory('BlogPostService',function($http){
	var blogPostService={}
	
	var BASE_URL="http://localhost:8085/collabrationbackend"
		
		blogPostService.saveblogpost=function(blogpost){
		return	$http.post(BASE_URL + "/saveBlogpost",blogpost)
		}
		
		blogPostService.listofblogsapproved=function(){
		return	$http.get(BASE_URL + "/getAllblogpost/"+1)
		}
		blogPostService.listOfBlogsWaitingForApproval=function(){
			return	$http.get(BASE_URL + "/getAllblogpost/"+0)
			}
		
		blogPostService.getblogpostbyid=function(id){
			return	$http.get(BASE_URL + "/getBlogpostByid/"+id)
			}
		
		blogPostService.updateApproval=function(blogpost){
		/*	console.log(blogpost)*/
			return	$http.put(BASE_URL + "/updateBlogpost",blogpost)
			}
		blogPostService.getApprovalStatus=function(){
			return $http.get(BASE_URL + "/blogpostapprovalstatus")
		}
	
		blogPostService.getallcomments=function(blogpostid){
			console.log(blogpostid)
			return	$http.get(BASE_URL + "/getAllBlogcomment/"+blogpostid)
			}
		return blogPostService;

})		


