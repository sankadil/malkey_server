package com.dspl.malkey.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import flex.messaging.FlexContext;

/**
 * @author Fuzuli Hameed
 *
 */
public class FileUploadSRVImpl implements FileUploadSRV {
	
	@Override
	public Boolean uploadFileToServer(String fileName, byte[] fileContents, String targetFolder) {
		/**
		 * Purpose			-	Uploads a file where the server is hosted locally
		 *   
		 * Parameters
		 *	fileName		-	File Name to be created (eg. mycar.jpg)
		 *	fileContents	-	Contents of the file to be created
		 *	targetFolder	-	Name of the target folder, only the relative path to be specified 
		 *						(eg. If the file needs to be created on folder 'resource\images' where the server resides, just pass 'resource\images\')
		 */
				
		// Format the given target folder to make it a valid path
		targetFolder = targetFolder.replace("/", "\\");							// Convert the forward slashes (/) to back slashes (\) 
		
		if (!targetFolder.substring(0, 1).equals("\\"))							// Leading back slash  
				targetFolder = "\\"+targetFolder;
		
		if (!targetFolder.substring(targetFolder.length()-1).equals("\\"))		// Trailing back slash  
			targetFolder = targetFolder+"\\";
		
		// Construct the local path to target folder
		String lsLocalServerPath = constructLocalServerPath();					// Local path to the server
		String localPathToTargetFolder = lsLocalServerPath + targetFolder;		// Full local path to the target folder within the server
		
		try {
			File targetDir = new File(localPathToTargetFolder);
			targetDir.mkdirs();
			
			File targetFile = new File(localPathToTargetFolder + fileName);
			FileOutputStream output = new FileOutputStream(targetFile);
			output.write(fileContents);
			output.close();
			
		} catch (SecurityException e) {
			System.err.println("ERROR:	No security access to create file! **********");
			e.printStackTrace();
			return false;
		
		} catch (FileNotFoundException e) {
			System.err.println("ERROR:	Unable to create file! **********");
			e.printStackTrace();
			return false;
		
		} catch (IOException e) {
			System.err.println("ERROR:	An error encounetered, check out the below stack trace! **********");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	private String constructLocalServerPath(){
		/** 
		 * Purpose			-	Returns the full local path where the server is hosted   
		 */
		
		String lsServerPath = FlexContext.getHttpRequest().getPathTranslated();	// Server path, returns something similar to:	D:\JAVA\WorkSpace_MALKEY\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\malkey_server\amf
		String lsContextPath = FlexContext.getHttpRequest().getContextPath();		// Only the server name (eg. /malkey_server)
		lsContextPath = lsContextPath.replace("/", "\\");							// Convert the forward slash (/) to back slash (\)
		String lsLocalServerPath = lsServerPath.substring(0, lsServerPath.lastIndexOf(lsContextPath));	// Extract the path upto the server name
		lsLocalServerPath += "/resource"; 
		return lsLocalServerPath;
	}
	
	
	@Override
	public Boolean copyFile(String sSourceFile, String sTargetFile){
		/** 
		 * Purpose			-	Copy a file between folders where the server is hosted locally
		 *   
		 * Parameters
		 *	sSourceFile		-	Source file name with relative path on the local server (eg. attachments\_SD3455D56\myimage.jpg) 
		 *	sTargetFile		-	Target file name with relative path on the local server (eg. attachments\_AA345DDF6\myimage.jpg)
		 */
		
		Boolean lbReturn = false;
		String lsLocalServerPath = constructLocalServerPath();					// Local path to the server
		
		// Format the given target folder to make it a valid path
		sSourceFile = sSourceFile.replace("/", "\\");							// Convert the forward slashes (/) to back slashes (\) 
		if (!sSourceFile.substring(0, 1).equals("\\"))							// Leading back slash  
				sSourceFile = "\\"+sSourceFile;

		sTargetFile = sTargetFile.replace("/", "\\");							// Convert the forward slashes (/) to back slashes (\) 
		if (!sTargetFile.substring(0, 1).equals("\\"))							// Leading back slash  
			sTargetFile = "\\"+sTargetFile;
								
		File sourceFile = new File(lsLocalServerPath + sSourceFile);
		if (sourceFile.exists()){

			try {
				FileInputStream sourceFileContents = new FileInputStream(sourceFile);
				
				// Create the target file
				try {
					File targetFile = new File(lsLocalServerPath + sTargetFile);
					File targetFolder = targetFile.getParentFile();
					targetFolder.mkdirs();			// Creates the target directory, if not already exists
										
					FileOutputStream targetFileContents = new FileOutputStream(targetFile);
					byte[] buf = new byte[1024];
					int len;
					while ((len = sourceFileContents.read(buf))>0){						
						targetFileContents.write(buf, 0, len);
					}
					
					targetFileContents.close();
					sourceFileContents.close();
					
					lbReturn = true;
					
				} catch (SecurityException e) {
					System.err.println("ERROR:	No security access to create file! **********");
					e.printStackTrace();
					
				} catch (FileNotFoundException e) {
					System.err.println("ERROR:	Unable to create file! **********");
					e.printStackTrace();
					
				} catch (IOException e) {
					System.err.println("ERROR:	An error encounetered, check out the below stack trace! **********");
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				System.err.println("ERROR:	Unable to locate source file - '" + sSourceFile + "' ! **********");
				e.printStackTrace();
			}
		}
		
		return lbReturn;
	}
	
	
	@Override
	public Boolean deleteFile(String sFileToDelete){
		/** 
		 * Purpose			-	Delete a file from folder where the server is hosted locally
		 *   
		 * Parameters
		 *	sFileToDelete	-	File to be deleted with relative path on the local server (eg. attachments\_SD3455D56\myimage.jpg)
		 */
		
		Boolean lbReturn = false;
		String lsLocalServerPath = constructLocalServerPath();					// Local path to the server
		
		// Format the given target folder to make it a valid path
		sFileToDelete = sFileToDelete.replace("/", "\\");							// Convert the forward slashes (/) to back slashes (\) 
		if (!sFileToDelete.substring(0, 1).equals("\\"))							// Leading back slash  
				sFileToDelete = "\\"+sFileToDelete;
								
		File sourceFile = new File(lsLocalServerPath + sFileToDelete);
		try {
			sourceFile.delete();
			lbReturn = true;
		} catch (SecurityException e) {
			System.err.println("ERROR:	No security access to delete file! **********");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.err.println("ERROR:	Unable to delete file! **********");
			e.printStackTrace();
		}
		return lbReturn;
	}

	@Override
	public Boolean deleteFolder(String sFolderToDelete){
		/** 
		 * Purpose			-	Delete a local folder where the server is hosted
		 *   
		 * Parameters
		 *	sFolderToDelete	-	Folder to be deleted with relative path on the local server (eg. attachments\_SD3455D56\)
		 *
		 * NOTE: The folder would not deleted if any files contained within the folder. So ensure to delete all contents of the folder before calling this method
		 */
		
		Boolean lbReturn = false;
		String lsLocalServerPath = constructLocalServerPath();					// Local path to the server
		
		// Format the given target folder to make it a valid path
		sFolderToDelete = sFolderToDelete.replace("/", "\\");							// Convert the forward slashes (/) to back slashes (\) 
		if (!sFolderToDelete.substring(0, 1).equals("\\"))							// Leading back slash  
			sFolderToDelete = "\\"+sFolderToDelete;
		
		File sourceDir = new File(lsLocalServerPath + sFolderToDelete);
		try {
			sourceDir.delete();
			lbReturn = true;
		} catch (SecurityException e) {
			System.err.println("ERROR:	No security access to delete file! **********");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.err.println("ERROR:	Unable to delete file! **********");
			e.printStackTrace();
		}
		return lbReturn;
	}
	
	
	@Override
	public Boolean deleteFolderAndItsContents(String sFolderToDelete){
		/** 
		 * Purpose			-	Delete a given directory and any files/ folders contained within it
		 *   
		 * Parameters
		 *	sFolderToDelete	-	Folder to be deleted (along with its contents) with relative path on the local server (eg. attachments\_SD3455D56\)
		 *
		 */
		
		Boolean lbReturn = false;
		String lsLocalServerPath = constructLocalServerPath();					// Local path to the server
		
		// Format the given target folder to make it a valid path
		sFolderToDelete = sFolderToDelete.replace("/", "\\");					// Convert the forward slashes (/) to back slashes (\) 
		if (!sFolderToDelete.substring(0, 1).equals("\\"))						// Leading back slash  
			sFolderToDelete = "\\"+sFolderToDelete;
		
		File sourceDir = new File(lsLocalServerPath + sFolderToDelete);
		
		if (sourceDir.exists()){
			File[] fileList = sourceDir.listFiles();
			if (fileList.length>0){
				for (File file : fileList) {
					if (file.isFile()){
						String lsSubFileName = file.getPath();
						// Verify if this already carries the full path to the local server					
						if (lsSubFileName.contains(lsLocalServerPath))
							lsSubFileName = lsSubFileName.replace(lsLocalServerPath,	"");		// Remove the local server path to derive the relative path
											
						deleteFile(lsSubFileName);
					} else {
						String lsSubDirName = file.getPath();
						// Verify if this already carries the full path to the local server					
						if (lsSubDirName.contains(lsLocalServerPath))
							lsSubDirName = lsSubDirName.replace(lsLocalServerPath,	"");		// Remove the local server path to derive the relative path
							
						// This is directory, check if it contains any files/folders within it
						File[] subFileList = file.listFiles();
		
						if (subFileList.length==0)
							deleteFolder(lsSubDirName);
						else
							deleteFolderAndItsContents(lsSubDirName);						
					}	 
				}
			}
				
			lbReturn = deleteFolder(sFolderToDelete);
		} else 
			System.out.println("ERROR: Unable to locate the folder to delete - " + sFolderToDelete);
			
		return lbReturn;
	}	

}
