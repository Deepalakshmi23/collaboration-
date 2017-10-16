package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDao {
List <User> getALLSuggestedUsers(String username);
void friendrequest(Friend friend);
List<Friend>pendingRequests(String username);
void updatePendingRequest(Friend friend);
List<Friend> listOfFriends(String username);
/*public List<String> getMutualFriends(String username, String otherUsername);*/
}
