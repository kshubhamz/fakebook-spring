package com.kshz.fakebookserver.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshz.fakebookserver.exceptions.BadRequestException;
import com.kshz.fakebookserver.exceptions.EntityNotFoundException;
import com.kshz.fakebookserver.model.Connection;
import com.kshz.fakebookserver.model.User;
import com.kshz.fakebookserver.repository.ConnectionRepository;
import com.kshz.fakebookserver.repository.UserRepository;

@Service
public class ConnectionService implements IConnectionService {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void removeFollower(String followerUsername, String userId) {
		// get user connection
		Optional<Connection> userConnectionOpt = connectionRepository.findById(userId);
		if (userConnectionOpt.isEmpty())
			throw new BadRequestException("Invalid action on empty followers list.",
					"Cannot find connection for the request. So, Delete action is invalid.");
		Connection userConnection = userConnectionOpt.get();
		
		// get user from username
		Optional<User> followerToBeRemovedOpt = userRepository.findUserByUsername(followerUsername);
		if (followerToBeRemovedOpt.isEmpty())
			throw new BadRequestException("Invalid action.", null);
		User followerToBeRemoved = followerToBeRemovedOpt.get();
		
		// filter followerList
		Set<User> followers = userConnection.getFollowers();
		followers = followers.stream()
				.filter(user -> !user.getId().equals(followerToBeRemoved.getId()))
				.collect(Collectors.toSet());
		userConnection.setFollowers(followers);
		
		// save
		connectionRepository.save(userConnection);
	}

	@Override
	public void addFollowing(String followingUsername, String userId) {
		// get current user
		Optional<User> currentUserOpt = userRepository.findById(userId);
		if (currentUserOpt.isEmpty())
			throw new EntityNotFoundException("Current User cannot be located", "User not found");
		User currentUser = currentUserOpt.get();
		
		// check if user is trying to follow self
		if (followingUsername.equals(currentUser.getUsername()))
			throw new BadRequestException("Invalid action", "Cannot follow self");
		
		// get following user
		Optional<User> userToBeFollowedOpt = userRepository.findUserByUsername(followingUsername);
		if (userToBeFollowedOpt.isEmpty())
			throw new EntityNotFoundException("Following User cannot be located", "User not found with username: " + followingUsername);
		
		// get connection
		Optional<Connection> userConnectionOpt = connectionRepository.findById(userId);
		Connection currentUserConnection = retrieveConnection(userId, userConnectionOpt);
		
		// add following if doesn't exist already
		User userToBeFollowed = userToBeFollowedOpt.get();
		boolean isFollowingAlreadyExist = currentUserConnection.getFollowings().stream()
				.anyMatch(connection -> connection.getId().equals(userToBeFollowed.getId()));
		if (!isFollowingAlreadyExist)
			currentUserConnection.addFollowing(userToBeFollowed);
		
		// add to follower list of following user
		// get connection of followed user (ie user whom current user followed)
		Optional<Connection> userToBeFollowedConnectionOpt = connectionRepository.findById(userToBeFollowed.getId());
		Connection userToBeFollowedConnection = retrieveConnection(userToBeFollowed.getId(), userToBeFollowedConnectionOpt);
		
		// add current user to follower list if doesn't exist
		boolean isFollowerAlreadyExist = userToBeFollowedConnection.getFollowers().stream().anyMatch(connection -> connection.getId().equals(currentUser.getId()));
		if (!isFollowerAlreadyExist)
			userToBeFollowedConnection.addFollower(currentUser);
		
		// save modified connections
		connectionRepository.save(currentUserConnection);
		connectionRepository.save(userToBeFollowedConnection);
		
	}

	@Override
	public void removeFollowing(String followingUsername, String userId) {
		// get user connection
		Optional<Connection> userConnectionOpt = connectionRepository.findById(userId);
		if (userConnectionOpt.isEmpty())
			throw new BadRequestException("Invalid action on empty following list.",
					"Cannot find connection for the request. So, Delete action is invalid.");
		Connection userConnection = userConnectionOpt.get();
		
		// get user from username
		Optional<User> followingToBeRemovedOpt = userRepository.findUserByUsername(followingUsername);
		if (followingToBeRemovedOpt.isEmpty())
			throw new BadRequestException("Invalid action.", null);
		User followingToBeRemoved = followingToBeRemovedOpt.get();
		
		// filter followerList
		Set<User> followings = userConnection.getFollowings();
		followings = followings.stream()
				.filter(user -> !user.getId().equals(followingToBeRemoved.getId()))
				.collect(Collectors.toSet());
		userConnection.setFollowings(followings);
		
		// save
		connectionRepository.save(userConnection);
	}
	
	private Connection retrieveConnection(String userId, Optional<Connection> connectionOpt) {
		// create connection if connection doesn't exist
		if (connectionOpt.isEmpty())
			return new Connection(userId);
		else
			return connectionOpt.get();
	}

	@Override
	public Optional<Connection> findById(String id) {
		return connectionRepository.findById(id);
	};

}
