package com.skilldistillery.todoapp.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.services.TodoService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class TodoController {

	private String username = "shaun";
	
	@Autowired
	private TodoService svc;

	@GetMapping("todos")
	public Set<Todo> index(HttpServletRequest req, HttpServletResponse res) {
		return svc.index(username);
	}

	@GetMapping("todos/{tid}")
	public Todo show(HttpServletRequest req, HttpServletResponse res,@PathVariable int tid) {
		return svc.show(username, tid);
	}

	@PostMapping("todos")
	public Todo create(HttpServletRequest req, HttpServletResponse res,@RequestBody Todo todo) {
		return svc.create(username, todo);
	}

	@PutMapping("todos/{tid}")
	public Todo update(HttpServletRequest req, HttpServletResponse res,@PathVariable int tid,@RequestBody Todo todo) {
		return svc.update(username, tid, todo);
	}

	@DeleteMapping("todos/{tid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res,@PathVariable int tid) {
		svc.destroy(username, tid);
	}

}
