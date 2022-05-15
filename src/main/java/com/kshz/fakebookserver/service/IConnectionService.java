package com.kshz.fakebookserver.service;

import java.util.Optional;

import com.kshz.fakebookserver.model.Connection;

public interface IConnectionService {
	public Optional<Connection> findById(String id);
	
	public void removeFollower(String followerUsername, String userId);

	public void addFollowing(String followingUsername, String userId);

	public void removeFollowing(String followingUsername, String userId);
}
