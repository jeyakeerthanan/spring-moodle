package com.moodle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.moodle.entities.Batch;
import com.moodle.repositories.BatchRepository;

@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepository;

	public Batch getBatchById(Integer id) {
		return batchRepository.findByIdAndDeletedAtNull(id).orElse(null);
	}

	public List<Batch> getAllBatch() {
		return batchRepository.findAllByDeletedAtNull();
	}

}
