package com.ssc.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.exception.CustomException;
import com.ssc.service.FileBrowserService;
import com.ssc.service.FileTransferService;
import com.ssc.service.StatusService;
import com.ssc.service.StatusUpdateService;
import com.ssc.utils.BeanUtilsCustom;




@Controller
public class FileTransferController {

	private static Logger logger = Logger.getLogger(FileTransferController.class);
	
	@Autowired
	private StatusService statusService;
	@Autowired
	private FileBrowserService fileBrowserService;
	@Autowired
	private FileTransferService fileTransferService;
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView uploadFile(Integer projectID) throws Exception{
		
		 if(projectID == null){
			  throw new CustomException("No Item be Selected");
		    }
		
		StatusBeanCustom statusBeanCustom = statusService.fetchStatusByID(projectID);
		logger.info("[uploadFile] ::: Fetch Status By ID : "+projectID +" Succeed");
		
		ModelAndView modelAndView = new ModelAndView();
		setModel(projectID, statusBeanCustom, modelAndView);
		modelAndView.setViewName("upload/uploadFile");
		return modelAndView;
	}

	/**
	 * @param projectID
	 * @param statusBeanCustom
	 * @param modelAndView
	 * @throws Exception
	 */
	public void setModel(Integer projectID, StatusBeanCustom statusBeanCustom, ModelAndView modelAndView) throws Exception {
		List<ProjectAttachmentBeanCustom> projectAttachmentBeanList = fileBrowserService.fetchFilesByProjectID(projectID);
		modelAndView.addObject("statusBeanCustom", statusBeanCustom);
		modelAndView.addObject("projectAttachmentBeanList", projectAttachmentBeanList);
		modelAndView.addObject("Total", projectAttachmentBeanList.size());
	}
	
	@RequestMapping(value = "/uploadFileSubmit", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView uploadFileSubmit(Integer projectID, String deleteOld, MultipartFile[] file,StatusBeanCustom statusBeanCustom,HttpSession session) throws Exception{

		StatusBeanCustom statusBean = statusService.fetchStatusByID(projectID);
		logger.info("[uploadFileSubmit] ::: ====upload File Submitted=====");
//		BeanUtils.copyProperties(statusBean, statusBeanCustom);
		BeanUtilsCustom.copyPropertiesCustom(statusBean, statusBeanCustom);
//		mergeObject(statusBean,statusBeanCustom);
		String filePath = "W:/project_file_pool/";
		for(MultipartFile eachFile:file){
			String originFileName = eachFile.getOriginalFilename();
			statusBeanCustom.setFileName(originFileName);
			String targetFilePath = filePath + statusBeanCustom.getProjectName()+"/" + projectID  +"/" ;
			if(originFileName != null){
				ProjectAttachmentBeanCustom projectAttachmentBeanCustom = new ProjectAttachmentBeanCustom();
				logger.info("[uploadFileSubmit] ::: ====update File To Obselete=====Old File::"+ originFileName + "  Project ID: "+projectID);
				fileTransferService.updateFileToObselete(projectID, originFileName, projectAttachmentBeanCustom);
				projectAttachmentBeanCustom.setProjectId(projectID);
				projectAttachmentBeanCustom.setFileName(originFileName);
				projectAttachmentBeanCustom.setFileDesc(statusBeanCustom.getDescription());
				projectAttachmentBeanCustom.setFilePath(targetFilePath + System.currentTimeMillis() + "_"+ originFileName);
				projectAttachmentBeanCustom.setIsOldFile("N");
				projectAttachmentBeanCustom.setUploadTime(new Date());
				UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
				projectAttachmentBeanCustom.setUserNm(userCustom.getUserName());
				fileTransferService.insertFileToDB(projectAttachmentBeanCustom);
				Integer fileID = projectAttachmentBeanCustom.getId();
				logger.info("[uploadFileSubmit] :::  ======insert File To DB ==== FileName:: "+ originFileName + " file ID: "+ fileID);
				
				Path path = Paths.get(targetFilePath);
				if(!Files.exists(path)){
					Files.createDirectories(path);
				}
				String absFilePath = projectAttachmentBeanCustom.getFilePath();
				File newFile = new File(absFilePath);
				eachFile.transferTo(newFile);
				logger.info("[uploadFileSubmit] :::======transfer To Folder::( "+ newFile +" ) Suucceed ====");
				if(!statusBeanCustom.getAttachments_url().toUpperCase().contains(originFileName.toUpperCase())){
					String attachmentURL ="@@@"+ fileID +"|"+originFileName + statusBeanCustom.getAttachments_url();
					statusBeanCustom.setAttachments_url(attachmentURL);
					statusBeanCustom.setHasAttachment("Y");
					statusBeanCustom.setUpdateTime(new Date());
					statusUpdateService.updateStatusRecordByID(projectID, statusBeanCustom);
					logger.info("[uploadFileSubmit] :::======insert Status Record Attachment URL ====>"+ attachmentURL);
				}
				else{
					String[] url = statusBeanCustom.getAttachments_url().split("@@@");
					StringBuilder sb = new StringBuilder();
					for(String attachment:url){
						if(!attachment.isEmpty()){
							String[] adr = attachment.split("\\|");
							for(int i = 1; i < adr.length; i = i+2 ){
								if(adr[i].equalsIgnoreCase(originFileName)){
									adr[i-1] = fileID.toString();
								}
								sb.append("@@@").append(adr[i-1]).append("|").append(adr[i]);
							}
						}
					}
					String attachmentURL = sb.toString();
					statusBeanCustom.setAttachments_url(attachmentURL);
					statusBeanCustom.setUpdateTime(new Date());
					statusUpdateService.updateStatusRecordByID(projectID, statusBeanCustom);
					logger.info("[uploadFileSubmit] :::======update Status Record Attachment URL ====>"+ attachmentURL);
				}
			}
			if(deleteOld != null && originFileName != null){
				logger.info("[uploadFileSubmit] :::======delete Old File ====");
				ProjectAttachmentVo projectAttachmentVo = new ProjectAttachmentVo();
				projectAttachmentVo.setProjectId(projectID);
				projectAttachmentVo.setFileName(originFileName.toUpperCase());
				projectAttachmentVo.setIsOldFile("Y"); 
				List<ProjectAttachmentBeanCustom>  projectAttachmentBeanCustomList = fileBrowserService.fetchAllFilesForSpecifiedProject(projectAttachmentVo);
				logger.info("[uploadFileSubmit] :::======set Old Duplicated File to Y into DB ====");
				List<Integer> attachmentIDList = new ArrayList<Integer>();
				if(!projectAttachmentBeanCustomList.isEmpty()){
					for(ProjectAttachmentBeanCustom attachment:projectAttachmentBeanCustomList){
						attachmentIDList.add(attachment.getId());
					}
					projectAttachmentVo.setIdList(attachmentIDList);
					fileTransferService.deleteAttachmentByID(attachmentIDList, projectAttachmentVo);
					logger.info("[uploadFileSubmit] :::======delete Old File ====");
				}
			}
			
		}
		ModelAndView modelAndView = new ModelAndView();
		setModel(projectID, statusBeanCustom, modelAndView);
		String isOpenFirst = "no";
		modelAndView.addObject("isOpenFirst", isOpenFirst);
		modelAndView.setViewName("upload/uploadFile");
		return modelAndView;
		
		//option return 
		// return uploadFile(projectID);
	}
	
	
	@RequestMapping(value = "/updateFileDescription", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateFileDescription(Integer id,Integer projectID,ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception{
		
		StatusBeanCustom statusBeanCustom = statusService.fetchStatusByID(projectID);
		fileTransferService.updateFileDescriptionByID(id, projectAttachmentBeanCustom);
		logger.info("[updateFileDescription]::====== update File Description ==== File ID :" + id);
		ModelAndView modelAndView = new ModelAndView();
		setModel(projectID, statusBeanCustom, modelAndView);
		String isOpenFirst = "no";
		modelAndView.addObject("isOpenFirst", isOpenFirst);
		modelAndView.setViewName("upload/uploadFile");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteFileSubmit", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView deleteFileSubmit(Integer projectID, String[] selectedId) throws Exception{
		
		 if(selectedId == null){
			  throw new CustomException("No File be Selected");
		    }
		
		ProjectAttachmentVo projectAttachmentVo = new ProjectAttachmentVo();
		List<Integer> fileIDList = new ArrayList<Integer>();
		for(String id:selectedId){
			fileIDList.add(Integer.decode(id));
		}
		projectAttachmentVo.setIdList(fileIDList); 
		// fileTransferService.deleteAttachmentByID(fileIDList, projectAttachmentVo);
	
		StatusBeanCustom statusBeanCustom = statusService.fetchStatusByID(projectID);
		String[] url = statusBeanCustom.getAttachments_url().split("@@@");
		StringBuilder sb = new StringBuilder();
		
		Arrays.asList(url).forEach(attachment ->{
			if(!attachment.isEmpty()){
				String[] adr = attachment.split("\\|");
				for(int i = 0; i < adr.length; i=i+2 ){
					if(!fileIDList.contains(Integer.decode(adr[i]))){
						sb.append("@@@").append(adr[i]).append("|").append(adr[i+1]);
					}
				}
			}
		});
//		for(String attachment:url){
//				if(!attachment.isEmpty()){
//					String[] adr = attachment.split("\\|");
//					for(int i = 0; i < adr.length; i=i+2 ){
//						if(!fileIDList.contains(Integer.decode(adr[i]))){
//							sb.append("@@@").append(adr[i]).append("|").append(adr[i+1]);
//						}
//					}
//				}
//			}
		
		 String attachmentURL = sb.toString();
		 statusBeanCustom.setAttachments_url(attachmentURL);
		 if(attachmentURL.isEmpty()){
		    statusBeanCustom.setHasAttachment("N");;
		 }
		 statusBeanCustom.setUpdateTime(new Date());
		 statusUpdateService.updateStatusRecordByID(projectID, statusBeanCustom);
		 logger.info("====== delete Status Record Attachment that deleted ====");
		 List<ProjectAttachmentBeanCustom>  projectAttachmentBeanCustomList = fileBrowserService.fetchFileListByIDList(projectAttachmentVo);
		 //Delete file from folder
//		 List<String> filePathList = new ArrayList<String>();
//		 projectAttachmentBeanCustomList.forEach(filePath ->filePathList.add(filePath.getFilePath()));
//		 filePathList.forEach(filePath -> {
//			try {
//				Files.deleteIfExists(Paths.get(filePath));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
		Path parentPaht = Paths .get(projectAttachmentBeanCustomList.get(0).getFilePath()).getParent();
		logger.info("====== Delete file from folder ====");
		projectAttachmentBeanCustomList.forEach(filePath -> {
			try {
				Files.deleteIfExists(Paths.get(filePath.getFilePath()));
			} catch (IOException e) {
				logger.error(filePath.getFilePath(), e);
			}
		});
		 //Delete empty folder
		logger.info("====== Delete empty folder ====");
		 if(parentPaht.toFile().listFiles().length==0){
			 try {
				 Files.deleteIfExists(parentPaht);
			 } catch (IOException e) {
				 logger.error(parentPaht, e);
			 }
		 }
		fileTransferService.deleteAttachmentByID(fileIDList, projectAttachmentVo);
		logger.info("[deleteFileSubmit]::====== delete Attachment from DB ====> File IDs:"+ fileIDList.toString());
		
		
		ModelAndView modelAndView = new ModelAndView();
		setModel(projectID, statusBeanCustom, modelAndView);
		String isOpenFirst = "no";
		modelAndView.addObject("isOpenFirst", isOpenFirst);
		modelAndView.setViewName("upload/uploadFile");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/downloadFile", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseEntity<byte[]> downloadFile(Integer id) throws Exception{
    	    
    	   if(id == null){
			  throw new CustomException("No File be Selected");
		    }
	        ProjectAttachmentBeanCustom projectAttachmentBeanCustom = null;
			try {
				projectAttachmentBeanCustom = fileBrowserService.fetchFileByID(id);
			} catch (Exception e) {
				 logger.error(id, e);
			}
	        String downloadFilePath=projectAttachmentBeanCustom.getFilePath();
	        String filename = projectAttachmentBeanCustom.getFileName();
	        File file = new File(downloadFilePath);
	        HttpHeaders headers = new HttpHeaders();
	        String downloadFileName = null;
	        logger.info("[downloadFile]::====== download File ==== ID: "+id+" FileName: "+filename);
			try {
				downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				 logger.error(downloadFileName, e);
			}
	        headers.setContentDispositionFormData("attachment", downloadFileName);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        ResponseEntity<byte[]> fileDownload = null;
			try {
				fileDownload = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
			} catch (IOException e) {
				 logger.error(fileDownload, e);
			}
	        return fileDownload;
	    }
    
    
    
//    public static void mergeObject(Object source, Object target) {
//    	logger.info("====== Merge DB with Web Information ====");
//		Field[] sourceFields = source.getClass().getSuperclass().getDeclaredFields();
//		Field[] targetFields = target.getClass().getSuperclass().getDeclaredFields();
//		for (Field srcField : sourceFields) {
//			srcField.setAccessible(true);
//			try {
//				Object value = srcField.get(source);
//				List<Field> targetFieldsList =  Arrays.asList(targetFields);
//				Field targetField = targetFieldsList.get(targetFieldsList.indexOf(srcField));
////				for(Field targetField : targetFields){
//					targetField.setAccessible(true);
//					if (srcField.getName().equals(targetField.getName())) {
//						if (null != value && value.toString().length() != 0) {
//							targetField.set(target, value);
////							break;
//						}
////					}
//				}
//				targetField.setAccessible(false);
//			} catch (Exception e) {
//				 logger.error(srcField, e);
//			}
//			srcField.setAccessible(false);
//			continue;
//		}
//	}
    
    
    
}
