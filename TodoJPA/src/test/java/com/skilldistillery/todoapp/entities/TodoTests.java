package com.skilldistillery.todoapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoTests {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("todoPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		todo = em.find(Todo.class, 1);

	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		todo = null;
	}

	@Test
	@DisplayName(value = "Todo Mappings Test")
	void test_Todo_mappings() {
		assertNotNull(todo);
		assertEquals("Go round Mum's", todo.getTask());
		assertEquals("shaun", todo.getUser().getUsername());
	}

}