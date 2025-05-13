package com.mphasis.parent.service;

import java.time.LocalDate;
import java.util.List;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.mphasis.parent.dao.FileLoadRepository;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.FileLoadRequestDto;
import com.mphasis.parent.model.dto.SearchCriteriaDto;



@Service
public class FileLoadServiceImpl implements FileLoadService{
	
	private FileLoadRepository fileLoadRepo;
	
	public FileLoadServiceImpl(FileLoadRepository fileLoadRepo) {
		this.fileLoadRepo=fileLoadRepo;
	}
	
	/*
	 * Adding a new File to the DataBase
	 * 
	 * @param Name of file in String
	 * @param Status of file in String
	 * @param Integer to pass RecordCount of file
	 * @return all the saved file details with generated Id.
	 * 
	 */

	@Override
	public FileLoad createFileLoad(FileLoadRequestDto load) {
		FileLoad fileload = new FileLoad();
		fileload.setFileName(load.getFilename());
		fileload.setStatus(load.getStatus());
		fileload.setRecordCount(load.getRecordCount());
		fileload.setLocaldate(LocalDate.now());
		return fileLoadRepo.save(fileload);	
	}
	
	/*
	 *Method to retrieve a file with its file id
	 *
	 *@param Long to pass File id.
	 *@return File details of given Id.
	 *
	 */
	
	@Override
	public FileLoad getFileLoadById(Long id) {
		return fileLoadRepo.findById(id).orElseThrow(() -> new RuntimeException("FileLoad not found with ID: " + id));
	}
	
	/*
	 * Method to search for files with common status
	 * 
	 * @param a request body with filename and status and created date
	 * @return A list of files with the same status or created date.
	 */
	
	@Override
	public List<FileLoad> searchFileLoads(SearchCriteriaDto searchCriteria) {
        return fileLoadRepo.findByCriteria(searchCriteria);
    }
	
	/*
	 * Method to Update a file status
	 * 
	 * @param a long integer to pass id of file
	 * @param a String to pass new status of file
	 * @return the updated file information.
	 * 
	 */
	
	@Override
	public FileLoad updateFileLoadStatus(Long id, String status) {
		FileLoad load =  fileLoadRepo.findById(id).orElseThrow(null);
		load.setStatus(status);
		return  fileLoadRepo.save(load);
	}
	
	/*
	 * To Archive a file in the DataBase
	 * 
	 * @param Long integer to pass id of the file
	 * @return the file information that it is Archived.
	 */
	
	@Override
	public void archiveFileLoad(Long id) {
		FileLoad load =  fileLoadRepo.findById(id).orElseThrow(null);
		load.setStatus("Archived");
		 fileLoadRepo.save(load);
		
	}
	
	/*
	 * To delete the file form the DataBase.
	 * 
	 * @param Long integer to pass Id of the file.
	 * @return 204 No content and deletes the file from the DataBase.
	 */
	
	@Override
	public void deleteFileLoad(Long id) throws FileNotFoundException {
		 if (! fileLoadRepo.existsById(id)) {
		        throw new FileNotFoundException("FileLoad with ID " + id + " not found.");
		 }
		 fileLoadRepo.deleteById(id);
	}

	public void deleteAllFileLoads() {
		 fileLoadRepo.deleteAll(); // Deletes all entries from the database
	}

}


