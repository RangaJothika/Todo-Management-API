package com.example.todocrud.services;

import com.example.todocrud.entity.Todo;
import com.example.todocrud.entity.Users;
import com.example.todocrud.repository.ToDoRepository;
import com.example.todocrud.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoServices {

	@Autowired
	UserServices userServices;

	@Autowired
	ToDoRepository toDoRepository;

	@Autowired
	UserRepository userRepository;

	public Todo getTodoById(Long todoId) {
		// write code
		return toDoRepository.findById(todoId).get();
	}

	public void addTodo(Long userId, Todo todo) {
		// write code
		Users user = userRepository.findById(userId).get();
		user.getTodoList().add(todo);
		todo.setUser(user);
		userRepository.save(user);
	}

	public void toggleTodoCompleted(Long todoId) {
		Todo todo = this.getTodoById(todoId);
		todo.setCompleted(!todo.getCompleted());
		toDoRepository.save(todo);
	}

	public void updateTodo(Todo todo) {
		toDoRepository.save(todo);
	}

	@Transactional
	public void deleteTodo(Long userId, Long todoId) {
		Users user = userRepository.findById(userId).get();
		Todo todo = toDoRepository.findById(todoId).get();
		user.getTodoList().remove(todo);
		todo.setUser(null);
		toDoRepository.deleteById(todoId);
	}

}
