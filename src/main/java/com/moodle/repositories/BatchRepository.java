package com.moodle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moodle.entities.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
	Optional<Batch> findByIdAndDeletedAtNull(Integer id);

	List<Batch> findAllByDeletedAtNull();

}
