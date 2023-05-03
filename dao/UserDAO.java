/**
 * 
 */
package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.User;

/**
 * @author kloudone
 *
 */
public interface UserDAO {

	public void addUser(User user);

	public List<User> getUsers();

	public User findById(int id);

	public User update(User user, int id);

	public void delete(int id);
	
	public String savePerson(User person);
}
