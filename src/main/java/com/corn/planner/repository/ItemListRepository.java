package com.corn.planner.repository;

import com.corn.planner.entity.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemListRepository extends JpaRepository<ItemList, Long>, JpaSpecificationExecutor<ItemList> {

}
