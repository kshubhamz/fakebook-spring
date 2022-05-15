package com.kshz.fakebookserver.model;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document("connections")
public class Connection {

	@Id
	@JsonIgnore
	private String id;

	@Field
	@DocumentReference(collection = "users", lazy = true)
	private Set<User> followers;

	@Field
	@DocumentReference(collection = "users", lazy = true)
	private Set<User> followings;

	public Connection() {
	}

	public Connection(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<User> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}

	public void addFollower(User user) {
		if (this.followers == null)
			this.followers = new TreeSet<>((user1, user2) ->  user1.getName().compareTo(user2.getName()));
		this.followers.add(user);
	}

	public void addFollowing(User user) {
		if (this.followings == null)
			this.followings = new TreeSet<>((user1, user2) ->  user1.getName().compareTo(user2.getName()));
		this.followings.add(user);
	}

	@Override
	public String toString() {
		return "Connection [id=" + id + ", followers=" + followers + ", followings=" + followings + "]";
	}

}
