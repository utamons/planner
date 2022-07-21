/* planner
 * Copyleft (C) 2022  Cornknight
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.corn.planner.controller;

import com.corn.planner.dto.ItemDTO;
import com.corn.planner.service.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/item")
public class ItemController {

	private final ItemService service;

	public ItemController(ItemService service) {
		this.service = service;
	}

	@PostMapping
	public Long create(@Valid @RequestBody ItemDTO dto) {
		return service.create(dto);
	}

	@GetMapping("/{id}")
	public ItemDTO read(@PathVariable("id") long id) {
		return service.read(id);
	}

	@GetMapping("/all/{listId}")
	public List<ItemDTO> readAllByList(@PathVariable("listId") long listId) {
		return service.readAllByList(listId);
	}

	@PutMapping
	public void update(@Valid @RequestBody ItemDTO dto) {
		if (dto.getId() == null) {
			throw new IllegalArgumentException("id is required");
		}
		service.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {
		service.delete(id);
	}

}
