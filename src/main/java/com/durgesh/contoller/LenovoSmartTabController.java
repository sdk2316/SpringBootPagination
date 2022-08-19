package com.durgesh.contoller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.helper.ExcelHelper;
import com.durgesh.model.LenovoSmartTab;
import com.durgesh.model.ResponseMessage;
import com.durgesh.service.ILenovoSmartTab;
import com.durgesh.utils.LenovoSmartTabExcelExporter;
import com.durgesh.utils.LenovoSmartTabPdfExporter;
import com.lowagie.text.DocumentException;

@Controller
public class LenovoSmartTabController {
	
	@Autowired
	ILenovoSmartTab iLenovoSmartTab;
	
	
	
	
	// find all record in excel by ram size
	//http://localhost:8081/iLenovoSmartTab/export/excel/4GB
	@GetMapping("/iLenovoSmartTab/export/excel/{ram}")
    public void exportToExcel(HttpServletResponse response,@PathVariable("ram") String ram) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=LenovoSmartTab_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<LenovoSmartTab> listOfRam = iLenovoSmartTab.getByRam(ram);
         
        LenovoSmartTabExcelExporter excelExporter = new LenovoSmartTabExcelExporter(listOfRam);
         
        excelExporter.export(response);  
        
       
    }  
	
	
	@GetMapping("/filterByNameInExcel")
	public String filterExcelForm() {
	     
	   
	     
	    return "filterByNameInExcel";
	}
	
	// find all record in excel by Name
	//http://localhost:8081/iLenovoSmartTab/export/excel/ByName/20MP
		@GetMapping("/iLenovoSmartTab/export/excel/ByName")
	    public String exportToExcelByName(HttpServletResponse response,@RequestParam(value="keyword") String keyword) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=LenovoSmartTab_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<LenovoSmartTab> listOfRam = iLenovoSmartTab.getByName(keyword);
	         
	        LenovoSmartTabExcelExporter excelExporter = new LenovoSmartTabExcelExporter(listOfRam);
	         
	        excelExporter.export(response);   
	        
	        return "filterByNameInExcel";
	    }  
	
	
	// jquery 

	@GetMapping("/jquerytable")
	public String viewPage(Model model
	      ) {
	     
	    List<LenovoSmartTab> listLenovoSmartTab = iLenovoSmartTab.jquerylistAll();
	     
	   
	    
	    model.addAttribute("listLenovoSmartTab", listLenovoSmartTab);
	     
	    return "index";
	}
	
	
	 @GetMapping("/addProduct")
	    public String showForm(Model model) {
		 
	         LenovoSmartTab t=new LenovoSmartTab();
	         model.addAttribute("lenovoSmartTab",t);
	        return "addProduct_form";
	    }
	 
	 @PostMapping("/addProduct")
	 public String submitForm(@Valid @ModelAttribute("lenovoSmartTab") LenovoSmartTab lenovoSmartTab,BindingResult bindingResult) {
	     System.out.println(lenovoSmartTab);
	     if (bindingResult.hasErrors()) {       
	         
	         return "addProduct_form";
	     } else {
	    	 iLenovoSmartTab.saveLenovoSmartTab(lenovoSmartTab);
	         return "add_success";
	     }
	   
	 }
	
	
	// select count(title) from tutorials;
			//@PostMapping(value = "/upload")
			
	 @PostMapping(value = "/upload",headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
			public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
				String message = "";
				System.out.println("bulk upload");
				if (ExcelHelper.hasExcelFormat(file)) {
					try {
						
						long startTime = System.currentTimeMillis();
						iLenovoSmartTab.save(file);
						message = " file record uploaded successfully: " + file.getOriginalFilename();
						long endTime = System.currentTimeMillis();

						long totalTime = endTime - startTime;
						System.out.println("Total time taken to upload file data is :" + totalTime);
						return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
					} catch (Exception e) {
						message = "Could not upload the file: " + file.getOriginalFilename() + "!";
						return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
					}
				}
				message = "Please upload an excel file!";
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
			
			@GetMapping("/getAllLenovoSmartTab")
			@ResponseBody
			public List<LenovoSmartTab> getAllLenovoSmartTab(){
				
				return iLenovoSmartTab.getAllLenovoSmartTab();
				
			}
			
			@GetMapping("/uploadForm")
			public String upload(){
				
				return "upload";
				
			}
			
			
//			@GetMapping("/page/{pageNum}")
//			public String viewPage(Model model,
//			        @PathVariable(name = "pageNum") int pageNum) {
//			     
//			    Page<LenovoSmartTab> page = iLenovoSmartTab.listAll(pageNum);
//			     
//			    List<LenovoSmartTab> listLenovoSmartTab = page.getContent();
//			     
//			    model.addAttribute("currentPage", pageNum);
//			    model.addAttribute("totalPages", page.getTotalPages());
//			    model.addAttribute("totalItems", page.getTotalElements());
//			    model.addAttribute("listLenovoSmartTab", listLenovoSmartTab);
//			     
//			    return "index";
//			}
			
			
			
//			@GetMapping("/")
//			public String viewHomePage(Model model) {
//			    return viewPage(model, 1);
//			}
			
			@GetMapping("/")
			public String viewHomePage(Model model) {
			    return viewPage(model, 1, "model", "asc");
			}
			
			@GetMapping("/pravin")
			public String viewHomePage() {
				return "index";
			}
			
			
			@GetMapping("/page/{pageNum}")
			public String viewPage(Model model,
			        @PathVariable(name = "pageNum") int pageNum,
			        @Param("sortField") String sortField,
			        @Param("sortDir") String sortDir) {
			     
			    Page<LenovoSmartTab> page = iLenovoSmartTab.listAll(pageNum, sortField, sortDir);
			     
			    List<LenovoSmartTab> listLenovoSmartTab = page.getContent();
			     
			    model.addAttribute("currentPage", pageNum);
			    model.addAttribute("totalPages", page.getTotalPages());
			    model.addAttribute("totalItems", page.getTotalElements());
			    
			    model.addAttribute("sortField", sortField);
			    model.addAttribute("sortDir", sortDir);
			    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			     
			    
			    model.addAttribute("listLenovoSmartTab", listLenovoSmartTab);
			     
			    return "index";
			}
			
			// pdf 
			
			@GetMapping("/lenovoSmartTab/export/pdf")
		    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		        response.setContentType("application/pdf");
		        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		        String currentDateTime = dateFormatter.format(new Date());
		         
		        String headerKey = "Content-Disposition";
		        String headerValue = "attachment; filename=LenovoSmartTab_" + currentDateTime + ".pdf";
		        response.setHeader(headerKey, headerValue);
		         
		        List<LenovoSmartTab> listLenovoSmartTab = iLenovoSmartTab.getAllLenovoSmartTab();
		         
		        LenovoSmartTabPdfExporter exporter = new LenovoSmartTabPdfExporter(listLenovoSmartTab);
		        exporter.export(response);
		         
		    }
			
			//get by ram
			
			@GetMapping("/lenovoSmartTab/getByRam/{ram}")
			public List<LenovoSmartTab> getByRam(@PathVariable String ram) {
				// TODO Auto-generated method stub
				return iLenovoSmartTab.getByRam(ram);
			}
			
			
			
}
