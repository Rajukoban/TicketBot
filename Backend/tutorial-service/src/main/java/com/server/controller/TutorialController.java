package com.server.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.model.Tutorial;
import com.server.repository.TutorialRepo;



@RestController
@RequestMapping("/api/tutorials")
@CrossOrigin("*")
public class TutorialController {

	@Autowired
	private TutorialRepo tutorialRepository;

	private final String UPLOAD_DIR = "D:\\database\\";

	@PostMapping("/upload")
	public ResponseEntity<?> uploadTutorial(@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("pdfFile") MultipartFile pdfFile) {

		try {
			// Generate dynamic file path
			String fileName = pdfFile.getOriginalFilename();
			String filePath = Paths.get(UPLOAD_DIR, fileName).toString();

			// Save the file locally
			File file = new File(filePath);
			pdfFile.transferTo(file);

			// Save tutorial data to database
			Tutorial tutorial = new Tutorial();
			tutorial.setTitle(title);
			tutorial.setDescription(description);
			tutorial.setContentType("application/pdf");
			tutorial.setPdfFilePath(filePath); // Save file path in database
			tutorial.setPdfFileName(fileName);

			tutorialRepository.save(tutorial);

			return ResponseEntity.ok("Tutorial uploaded successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload tutorial: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTutorialById(@PathVariable Long id) {
	    Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);
	    if (tutorialOptional.isPresent()) {
	        return ResponseEntity.ok(tutorialOptional.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutorial not found with ID: " + id);
	    }
	}

	@GetMapping("/all")
    public ResponseEntity<?> getAllTutorials() {
        List<Tutorial> tutorials = tutorialRepository.findAll();
        if (tutorials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No tutorials found.");
        } else {
            return ResponseEntity.ok(tutorials);
        }
    }
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
		Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);
		if (tutorialOptional.isPresent()) {
			Tutorial tutorial = tutorialOptional.get();
			Path path = Paths.get(tutorial.getPdfFilePath());
			Resource resource = null;
			try {
				resource = new UrlResource(path.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			if (resource.exists()) {
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(tutorial.getContentType()))
						.header(HttpHeaders.CONTENT_DISPOSITION,
								"attachment; filename=\"" + tutorial.getPdfFileName() + "\"")
						.body(resource);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTutorial(
	        @PathVariable Long id,
	        @RequestParam("title") String title,
	        @RequestParam("description") String description,
	        @RequestParam("contentType") String contentType,
	        @RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile) {

	    Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);

	    if (tutorialOptional.isPresent()) {
	        try {
	            Tutorial tutorial = tutorialOptional.get();
	            tutorial.setTitle(title);
	            tutorial.setDescription(description);
	            tutorial.setContentType(contentType);

	            // If a new file is provided, update the file and file path
	            if (pdfFile != null && !pdfFile.isEmpty()) {
	                // Delete the old file
	                File oldFile = new File(tutorial.getPdfFilePath());
	                if (oldFile.exists()) {
	                    oldFile.delete();
	                }

	                // Save the new file
	                String fileName = pdfFile.getOriginalFilename();
	                String filePath = Paths.get(UPLOAD_DIR, fileName).toString();
	                File newFile = new File(filePath);
	                pdfFile.transferTo(newFile);

	                tutorial.setPdfFilePath(filePath);
	                tutorial.setPdfFileName(fileName);
	            }

	            tutorialRepository.save(tutorial);
	            return ResponseEntity.ok("Tutorial updated successfully!");

	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update tutorial: " + e.getMessage());
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutorial not found with id: " + id);
	    }
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTutorial(@PathVariable Long id) {
	    Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);

	    if (tutorialOptional.isPresent()) {
	        try {
	            Tutorial tutorial = tutorialOptional.get();

	            // Delete the file from the file system
	            File file = new File(tutorial.getPdfFilePath());
	            if (file.exists()) {
	                file.delete();
	            }

	            // Delete the tutorial from the database
	            tutorialRepository.delete(tutorial);

	            return ResponseEntity.ok("Tutorial deleted successfully!");

	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete tutorial: " + e.getMessage());
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutorial not found with id: " + id);
	    }
	}
	
	@GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewPdfFile(@PathVariable Long id) {
        Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);

        if (tutorialOptional.isPresent()) {
            Tutorial tutorial = tutorialOptional.get();
            try {
                Path filePath = Paths.get(tutorial.getPdfFilePath());
                Resource resource = new UrlResource(filePath.toUri());

                if (resource.exists() || resource.isReadable()) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_PDF)
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + tutorial.getPdfFileName() + "\"")
                            .body(resource);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } catch (MalformedURLException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



}
