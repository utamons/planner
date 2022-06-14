package com.corn.planner.controller;

import com.corn.planner.dto.TodoItemDTO;
import com.corn.planner.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TodoController {

	private final TodoService service;
	private final Logger      log = LoggerFactory.getLogger(TodoController.class);


	public TodoController(TodoService service) {
		this.service = service;
	}

	@GetMapping("/todo/all")
	public ResponseEntity<List<TodoItemDTO>> allTodo(@RequestParam(name = "delay", required = false) Integer delay,
	                                                 @RequestParam(name = "errorCode", required = false) Integer errorCode) throws InterruptedException {
		log.debug("REST request to get todo items");
		if (delay != null) {
			Thread.sleep(delay);
		}
		if (errorCode != null)
			return ResponseEntity.status(errorCode).build();
		return ResponseEntity.ok().body(service.getAll());
	}

	@GetMapping("/todo/{id}")
	public ResponseEntity<TodoItemDTO> get(@PathVariable("id") long id,
	                                       @RequestParam(name = "delay", required = false) Integer delay) throws InterruptedException {
		log.debug("REST request to get todo items");
		if (delay != null) {
			Thread.sleep(delay * 1000);
		}
		TodoItemDTO result = service.find(id);
		if (result == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/todo")
	public ResponseEntity<Long> add(@RequestBody TodoItemDTO item) {
		log.debug("REST request to add todo item {}", item.getText());
		return ResponseEntity.ok().body(service.addItem(item).getId());
	}

	@PutMapping("/todo")
	public ResponseEntity<String> update(@RequestBody TodoItemDTO item) {
		log.debug("REST request to update todo item");
		if (item == null || item.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		TodoItemDTO result = service.updateItem(item);
		if (result == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body("ok");
	}

	@PutMapping("/todo/moveUp/{id}")
	public ResponseEntity<String> moveUp(@PathVariable("id") long id) {
		log.debug("REST request to move up todo item");
		service.moveUp(id);
		return ResponseEntity.ok().body("ok");
	}

	@PutMapping("/todo/moveDown/{id}")
	public ResponseEntity<String> moveDown(@PathVariable("id") long id) {
		log.debug("REST request to move down todo item");
		service.moveDown(id);
		return ResponseEntity.ok().body("ok");
	}

	@PutMapping("/todo/drop")
	public ResponseEntity<String> drop(@RequestParam("srcId") long srcId, @RequestParam("dstId") long dstId) {
		log.debug("REST request to drop item {} to {}", srcId, dstId);
		service.dropItem(srcId, dstId);
		return ResponseEntity.ok().body("ok");
	}

	@DeleteMapping("/todo/{id}")
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		log.debug("REST request to delete todo item {}", id);
		service.deleteItem(id);
		return ResponseEntity.ok().build();
	}

}
