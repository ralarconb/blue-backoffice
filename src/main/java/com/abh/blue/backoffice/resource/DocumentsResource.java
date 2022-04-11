package com.abh.blue.backoffice.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abh.blue.backoffice.model.Document;
import com.abh.blue.backoffice.service.DocumentsService;

@RestController
@RequestMapping("/documents")
public class DocumentsResource {
	private final DocumentsService documentsService;

	public DocumentsResource(DocumentsService pDocumentsService) {
		documentsService = pDocumentsService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Document>> getDocuments() {
		List<Document> list = documentsService.findAllDocuments();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Document> getDocument(@PathVariable("id") Long id) {
		Document document = documentsService.findDocumentById(id);
		return new ResponseEntity<>(document, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Document> addDocument(@RequestBody Document pDocument) {
		Document document = documentsService.addDocument(pDocument);
		return new ResponseEntity<>(document, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Document> updateDocument(@RequestBody Document pDocument) {
		Document document = documentsService.updateDocument(pDocument);
		return new ResponseEntity<>(document, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDocument(@PathVariable("id") Long id) {
		documentsService.deleteDocument(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
