package com.corn.planner.controller;

import com.corn.planner.dto.TodoItemDTO;
import com.corn.planner.exception.ValidationException;
import com.corn.planner.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
	public List<TodoItemDTO> allTodo(@RequestParam(name = "delay", required = false) Integer delay,
	                                 @RequestParam(name = "error", required = false) Boolean error) throws InterruptedException {
		log.debug("REST request to get todo items");
		if (delay != null) {
			Thread.sleep(delay);
		}
		if (BooleanUtils.isTrue(error))
			throw new RuntimeException("A test error");
		return service.getAll();
	}

	@GetMapping("/todo/{id}")
	public TodoItemDTO get(@PathVariable("id") long id,
	                                       @RequestParam(name = "delay", required = false) Integer delay) throws InterruptedException {
		log.debug("REST request to get todo items");
		if (delay != null) {
			Thread.sleep(delay * 1000);
		}
		return service.find(id);
	}

	@Operation(summary = "Post a new todo item")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "text/plain",
					examples = {
					@ExampleObject(
							name = "Id of the item created", value = "1")}))})
	@PostMapping("/todo")
	@ResponseStatus(HttpStatus.CREATED)
	public Long add(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
					@ExampleObject(
							name = "Todo item",
							summary = "Todo item example",
							value = "{\"text\": \"Item1\"}")}))
			@RequestBody TodoItemDTO item) {
		log.debug("REST request to add todo item {}", item.getText());
		return service.addItem(item).getId();
	}

	@PutMapping("/todo")
	public void update(@RequestBody TodoItemDTO item) throws ValidationException {
		log.debug("REST request to update todo item");
		if (item == null || item.getId() == null) {
			throw new ValidationException("Item should be present and have an id");
		}
		TodoItemDTO result = service.updateItem(item);
	}

	@PutMapping("/todo/moveUp/{id}")
	public void moveUp(@PathVariable("id") long id) {
		log.debug("REST request to move up todo item");
		service.moveUp(id);
	}

	@PutMapping("/todo/moveDown/{id}")
	public void moveDown(@PathVariable("id") long id) {
		log.debug("REST request to move down todo item");
		service.moveDown(id);
	}

	@PutMapping("/todo/drop")
	public void drop(@RequestParam("srcId") long srcId, @RequestParam("dstId") long dstId) {
		log.debug("REST request to drop item {} to {}", srcId, dstId);
		service.dropItem(srcId, dstId);
	}

	@DeleteMapping("/todo/{id}")
	public void delete(@PathVariable("id") Long id) {
		log.debug("REST request to delete todo item {}", id);
		service.deleteItem(id);
	}
}
