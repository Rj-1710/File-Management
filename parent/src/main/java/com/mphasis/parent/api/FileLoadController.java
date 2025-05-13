package com.mphasis.parent.api;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.exception.FileLoadException;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;
import com.mphasis.parent.service.FileLoadServiceImpl;
	
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/file-loads")
public class FileLoadController {
		
	private final FileLoadServiceImpl fileloadservice;
	
	
	
	public FileLoadController(FileLoadServiceImpl fileloadservice) {
		super();
		this.fileloadservice = fileloadservice;
	}

	/*
     * Creates a new file in the system.
     *
     * @param FilLoadRequestDto the file details to create
     * @return ResponseEntity containing the created File's details
     */

	@PostMapping("/create")
	public ResponseEntity<FileLoad> createFileLoad(@RequestBody FileLoadRequestDto request){
		FileLoad load=fileloadservice.createFileLoad(request);
		return ResponseEntity.ok(load);
	}
	
	/*
     * Retrieves the details of a File by It's unique ID.
     *
     * @param userId the unique ID of the File to retrieve
     * @return ResponseEntity containing File details in JSON format
     * @throws FileNotFoundException if the File with the given ID does not exist
     */
	
	@GetMapping("/file/{id}")
	public ResponseEntity<FileLoad> getFileLoad(@PathVariable Long id) throws FileNotFoundException {
	    try {
	        FileLoad load = fileloadservice.getFileLoadById(id);
	        return ResponseEntity.ok(load);
	    } catch (RuntimeException e) {
	        throw new FileNotFoundException("File not found with ID: " + id);
	    }
	}
	
	/*
	 * Searches for list of files with common status or created date
	 * 
	 * @param request body containing status or date of creation of file
	 * @return list of files with same status
	 */
	
	@PostMapping("/files")  
	public ResponseEntity<List<FileLoad>> searchFileLoad(@RequestBody SearchCriteriaDto searchCriteria) {
	    if (searchCriteria == null || searchCriteria.getFilename() == null || searchCriteria.getStatus() == null) {
	        return ResponseEntity.badRequest().build();
	    }

	    List<FileLoad> load = fileloadservice.searchFileLoads(searchCriteria);
	    return ResponseEntity.ok(load);
	}

	
	/*
     * Updates the details of an existing File.
     *
     * @param FileId the unique ID of the File to update
     * @param status of  the updated File details
     * @return ResponseEntity containing the updated File's details
     * @throws FileNotFoundException if the File with the given ID does not exist
     */
	
	  @PutMapping("/update/{id}")
	    public ResponseEntity<FileLoad> updateFileLoadStatus(@PathVariable Long id, @RequestParam String status) {
	        FileLoad fileLoad = fileloadservice.updateFileLoadStatus(id, status);
	        return ResponseEntity.ok(fileLoad);
	    }
	  
	  /*
	     * Archives a File by their unique ID.
	     *
	     * @param FileId the unique ID of the File to Archive
	     * @return ResponseEntity confirming the file is Archived
	     * @throws FileNotFoundException if the file with the given ID does not exist
	     */
	  
	  @PostMapping("/{id}/archive")
	    public ResponseEntity<Void> archiveFileLoad(@PathVariable Long id) {
	        fileloadservice.archiveFileLoad(id);
	        return ResponseEntity.noContent().build();
	  }
	  
	  /*
	     * Deletes a File by their unique ID.
	     *
	     * @param FileId the unique ID of the File to delete
	     * @return ResponseEntity confirming the deletion
	     * @throws FileNotFoundException if the file with the given ID does not exist
	     */
	  
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<Void> deleteFileLoad(@PathVariable Long id) {
	      try {
	          fileloadservice.deleteFileLoad(id);
	          return ResponseEntity.noContent().build();
	      } catch (FileNotFoundException e) {
	          throw new FileLoadException(e.getMessage());
	      }
	  }
	 
	  @DeleteMapping("/deleteall")
	  public ResponseEntity<Void> deleteAllFileLoads() {
	      try {
	          fileloadservice.deleteAllFileLoads(); 
	          return ResponseEntity.noContent().build();
	      } catch (Exception e) {
	          throw new FileLoadException("Error deleting all records: " + e.getMessage());
	      }
	  }

	  
	  
	  /*
	   * A sample method to check whether 
	   *  API is getting triggered or not.
	   */
	  @GetMapping("/hello")
	  public String greet() {
		  return "Helloooo";
	  }
}

