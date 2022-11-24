package com.example.demo.repositories;

import com.example.demo.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskCategoryRepository extends JpaRepository<TaskCategories, Long> {
}
