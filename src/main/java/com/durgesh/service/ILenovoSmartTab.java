package com.durgesh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.model.LenovoSmartTab;

public interface ILenovoSmartTab {
	
	public void save(MultipartFile file);
	public List<LenovoSmartTab> getAllLenovoSmartTab();
	//Page<LenovoSmartTab> listAll(int pageNum);
	
	Page<LenovoSmartTab> listAll(int pageNum, String sortField, String sortDir);
	

}
