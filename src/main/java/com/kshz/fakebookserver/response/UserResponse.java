package com.kshz.fakebookserver.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.kshz.fakebookserver.model.Post;
import com.kshz.fakebookserver.model.User;

public class UserResponse {
	private User user;
	private List<PostResponse> posts;
	private boolean self;
	private String message;
	private List<Connection> followers;
	private List<Connection> followings;

	public UserResponse() {
	}

	public UserResponse(User user, List<Post> posts, boolean self, String message, Set<User> followers, Set<User> followings) {
		this.user = user;
		
		if (posts != null) {
			this.posts = posts.stream()
					.map(post -> new PostResponse(post, self))
					.collect(Collectors.toList());
		}
		
		this.self = self;
		this.message = message;
		
		// map followers
		if (followers == null)
			this.followers = new ArrayList<>();
		else
			this.followers = followers.stream()
				.map(connection -> new Connection(connection.getUsername(), 
						connection.getName(), 
						connection.getProfileImage()))
				.collect(Collectors.toList());
		
		// map followings
		if (followings == null)
			this.followings = new ArrayList<>();
		else
			this.followings = followings.stream()
				.map(connection -> new Connection(connection.getUsername(), 
						connection.getName(), 
						connection.getProfileImage()))
				.collect(Collectors.toList());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PostResponse> getPosts() {
		return posts;
	}

	public void setPosts(List<PostResponse> posts) {
		this.posts = posts;
	}

	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Connection> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Connection> followers) {
		this.followers = followers;
	}

	public List<Connection> getFollowings() {
		return followings;
	}

	public void setFollowings(List<Connection> followings) {
		this.followings = followings;
	}

	@Override
	public String toString() {
		return "UserResponse [user=" + user + ", posts=" + posts + ", self=" + self + ", message=" + message + "]";
	}

}

class Connection {
	private String username;
	private String name;
	private String profileImage;

	public Connection() {
	}

	public Connection(String username, String name, String profileImage) {
		this.username = username;
		this.name = name;
		this.profileImage = profileImage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

}
