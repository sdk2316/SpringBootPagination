package com.durgesh.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.helper.ExcelHelper;
import com.durgesh.model.LenovoSmartTab;
import com.durgesh.repository.LenovoSmartTabRepository;

@Service
public class LenovoSmartTabImpl implements ILenovoSmartTab{
	
	@Autowired
	LenovoSmartTabRepository lenovoSmartTabRepository;

	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		
		try {
		    List<LenovoSmartTab> AllLenovoSmartTab = ExcelHelper.excelToLenovoSmartTab(file.getInputStream());
		    lenovoSmartTabRepository.saveAll(AllLenovoSmartTab);
		  } catch (IOException e) {
		    throw new RuntimeException("fail to store excel data: " + e.getMessage());
		  }
		
	}

	@Override
	public List<LenovoSmartTab> getAllLenovoSmartTab() {
		// TODO Auto-generated method stub
		return lenovoSmartTabRepository.findAll();
	}
	
	public Page<LenovoSmartTab> listAll(int pageNum) {
	    int pageSize = 5;
	     
	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
	     
	    return lenovoSmartTabRepository.findAll(pageable);
	}

}


