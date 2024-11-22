package com.example.jspwithboot.service.iface;

import java.util.List;

import com.example.jspwithboot.model.User;

public interface UserService {
    User authenticate(String username, String password);
    public User createUser(String username, String password, String role,String phoneNumber,String email);
    User getUserById(int id);
	void deleteUser(int id);
	List<User> getAllUsers();
	void updateUser(int id, String username, String password, String role, String phoneNumber, String email);
}
