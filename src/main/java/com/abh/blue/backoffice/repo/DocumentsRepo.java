package com.abh.blue.backoffice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abh.blue.backoffice.model.Document;

public interface DocumentsRepo extends JpaRepository<Document, Long> {
	void deleteDocumentById(Long id);

	Optional<Document> findDocumentById(Long id);

	Optional<Document> findDocumentByCode(String code);
}
