package com.emoty.app.model.process;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.emoty.app.model.Images;

public class Uploading {
	private Date uploadDate;
	private boolean status;
	private List<MultipartFile> files;


	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	

}
