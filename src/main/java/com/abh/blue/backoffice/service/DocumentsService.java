package com.abh.blue.backoffice.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abh.blue.backoffice.exception.EntityNotFoundException;
import com.abh.blue.backoffice.model.Document;
import com.abh.blue.backoffice.repo.DocumentsRepo;

@Service
@Transactional
public class DocumentsService {
	private final DocumentsRepo documentsRepo;

	@Autowired
	public DocumentsService(DocumentsRepo pDocumentsRepo) {
		documentsRepo = pDocumentsRepo;
	}

	public Document addDocument(Document document) {
		if (documentsRepo.findDocumentByCode(document.getCode()).isPresent()) {
			throw new EntityExistsException("Document with code " + document.getCode() + " already exists");
		}
		return documentsRepo.save(document);
	}

	public List<Document> findAllDocuments() {
		return documentsRepo.findAll();
	}

	public Document updateDocument(Document document) {
		return documentsRepo.save(document);
	}

	public Document findDocumentById(Long id) {
		return documentsRepo.findDocumentById(id)
				.orElseThrow(() -> new EntityNotFoundException("Document by id " + id + " was not found"));
	}

	public Document findDocumentByCode(String code) {
		return documentsRepo.findDocumentByCode(code)
				.orElseThrow(() -> new EntityNotFoundException("Document by code " + code + " was not found"));
	}

	public void deleteDocument(Long id) {
		documentsRepo.deleteDocumentById(id);
	}
}
