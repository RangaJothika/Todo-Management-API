package com.example.todocrud.services;

import com.example.todocrud.entity.Todo;
import com.example.todocrud.entity.Users;
import com.example.todocrud.exception.NotFoundException;
import com.example.todocrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.*;

@Service
public class UserServices {
	@Autowired
	UserRepository userRepository;

	public Users getUserById(Long userId) {
		// write code
		return userRepository.findById(userId).get();
	}

	public Users addUser(Users user) {
		// write code
		userRepository.save(user);
		return user;
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	public void updateUser(Users user) {
		Optional<Users> userOptional = userRepository.findById(user.getId());
		Users oldUser = userOptional.isPresent() ? userOptional.get() : null;
		if (oldUser != null) {
			oldUser.setPassword(user.getPassword());
			oldUser.setUsername(user.getUsername());

			List<Todo> existingTodos = oldUser.getTodoList();
			existingTodos.clear();
			for (Todo todo : user.getTodoList()) {
				todo.setUser(oldUser); // maintain FK
				existingTodos.add(todo);
			} // will clear them in db too
			userRepository.save(oldUser);// use old user to save for security concerns
		} else {
			throw new NotFoundException("Id doesnot exists");// if user with given id doesnot exist,throw error
		}
	}

}
