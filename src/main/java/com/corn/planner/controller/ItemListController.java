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

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.service.ItemListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/list")
public class ItemListController {

	private final ItemListService service;

	public ItemListController(ItemListService service) {
		this.service = service;
	}

	@GetMapping("/all")
	public List<ItemListDTO> readAll() {
		return service.readAll();
	}

	@GetMapping("/{id}")
	public ItemListDTO read(@PathVariable("id") long id) {
		return service.read(id);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody ItemListDTO dto) {
		return service.create(dto);
	}

	@PutMapping()
	public void update(@RequestBody ItemListDTO dto) {
		service.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {
		service.delete(id);
	}
}
