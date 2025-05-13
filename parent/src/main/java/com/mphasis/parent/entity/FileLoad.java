package com.mphasis.parent.entity;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="file_load")
public class FileLoad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String fileName;
	private String status;
	@Column(name = "record_count", nullable = false)
	private Integer recordCount;
	private List<String> errors;
	private LocalDate localdate;
	private String criteria;

	
	public FileLoad(long id, String fileName, String status, int recordCount, List<String> errors,
			LocalDate localdate) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.status = status;
		this.recordCount = recordCount;
		this.errors = errors;
		this.localdate = localdate;
	}
	
	public FileLoad(String fileName, String status, int recordCount) {
		super();
		this.fileName = fileName;
		this.status = status;
		this.recordCount = recordCount;
	}

	public FileLoad() {
		
	}
	public String getCriteria() {
		return criteria;
	}
	
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public LocalDate getLocaldate() {
		return localdate;
	}
	public void setLocaldate(LocalDate localdate) {
		this.localdate = localdate;
	}
	
}
