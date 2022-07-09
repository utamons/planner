package com.corn.planner.service;

import com.corn.planner.dto.TodoItemDTO;
import com.corn.planner.entity.TodoItem;
import com.corn.planner.repository.TodoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TodoService {

	private final TodoRepository repo;

	public TodoService(TodoRepository repo) {
		this.repo = repo;
	}

	public List<TodoItemDTO> getAll() {
		return TodoItemDTO.toDtoList(getSorted());
	}

	private List<TodoItem> getSorted() {
		return repo.findAll().stream().sorted(Comparator.comparing(TodoItem::getOrder)).collect(Collectors.toList());
	}

	public TodoItem addItem(TodoItemDTO dto) {
		List<TodoItem> all  = getSorted();
		TodoItem       item = TodoItemDTO.toEntity(dto);
		item.setOrder((long) (all.size() + 1));
		return repo.save(item);
	}

	public void dropItem(long srcId, long dstId) {
		if (srcId == dstId)
			return;
		TodoItem       src      = repo.findById(srcId).orElseThrow(() -> new RuntimeException("Not found"));
		TodoItem       dst      = repo.findById(dstId).orElseThrow(() -> new RuntimeException("Not found"));
		List<TodoItem> all      = getSorted();
		long           srcOrder = src.getOrder();
		long           dstOrder = dst.getOrder();
		if (srcOrder > dstOrder) {
			for (int i = 0; i < srcOrder - dstOrder; ++i) {
				moveUp(srcId);
			}
		} else {
			for (int i = 0; i < dstOrder - srcOrder; ++i) {
				moveDown(srcId);
			}
		}
	}


	public void moveUp(long id) {
		TodoItem item = repo.getReferenceById(id);
		if (item.getOrder() == 1)
			return;
		List<TodoItem>     all     = getSorted();
		Optional<TodoItem> prevOpt = all.stream().filter((i) -> i.getOrder() == item.getOrder() - 1).findFirst();
		TodoItem           prev    = prevOpt.orElseThrow(() -> new RuntimeException("Order is broken"));
		prev.setOrder(item.getOrder());
		item.setOrder(item.getOrder() - 1);
		repo.save(prev);
		repo.save(item);
	}

	public void moveDown(long id) {
		TodoItem item = repo.getReferenceById(id);
		List<TodoItem> all  = getSorted();
		if (item.getOrder() == all.size())
			return;
		Optional<TodoItem> prevOpt = all.stream().filter((i) -> i.getOrder() == item.getOrder() + 1).findFirst();
		TodoItem           prev    = prevOpt.orElseThrow(() -> new RuntimeException("Order is broken"));
		prev.setOrder(item.getOrder());
		item.setOrder(item.getOrder() + 1);
		repo.save(prev);
		repo.save(item);
	}

	public TodoItemDTO updateItem(TodoItemDTO dto) {
		TodoItem item = repo.getReferenceById(dto.getId());
		item.setText(dto.getText());
		return TodoItemDTO.toDto(repo.save(item));
	}

	public void deleteItem(Long id) {
		repo.deleteById(id);
		reorder();
	}

	private void reorder() {
		List<TodoItem> all = getSorted();
		for (int i = 0; i < all.size(); i++) {
			TodoItem item = all.get(i);
			item.setOrder((long) (i + 1));
		}
		repo.saveAll(all);
	}

	public TodoItemDTO find(long id) {
		TodoItem old = repo.getReferenceById(id);

		return TodoItemDTO.toDto(old);
	}
}
