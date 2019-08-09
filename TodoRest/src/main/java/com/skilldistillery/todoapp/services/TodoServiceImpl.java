package com.skilldistillery.todoapp.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository repo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Todo> index(String username) {
		return repo.findByUser_Username(username);
	}

	@Override
	public Todo show(String username, int tid) {
		return repo.findByIdAndUser_Username(tid, username);
	}

	@Override
	public Todo create(String username, Todo todo) {
		System.out.println(todo.toString());
		User user = userRepo.findByUsername(username);
		if (user != null) {
			todo.setUser(user);
			return repo.saveAndFlush(todo);
		}
		return null;
	}

	@Override
	public Todo update(String username, int tid, Todo todo) {
		Optional<Todo> opt = repo.findById(tid);
		Todo managedTodo = null;
		if (opt.isPresent()) {
			managedTodo = opt.get();
			managedTodo.setTask(todo.getTask());
			managedTodo.setDescription(todo.getDescription());
			managedTodo.setDueDate(todo.getDueDate());
			managedTodo.setCompleteDate(todo.getCompleteDate());
			managedTodo.setCompleted(todo.getCompleted());
			repo.saveAndFlush(managedTodo);
		}
		return managedTodo;
	}

	@Override
	public boolean destroy(String username, int tid) {
		Boolean deleted = false;
		if (repo.findByIdAndUser_Username(tid, username) != null) {
		try {
			repo.deleteById(tid);
			deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return deleted;
	}

}
