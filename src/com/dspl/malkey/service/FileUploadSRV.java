package com.dspl.malkey.service;

public interface FileUploadSRV {
	Boolean uploadFileToServer(String fileName, byte[] fileContents, String targetFolder);
	Boolean copyFile(String sSourceFile, String sTargetFile);
	Boolean deleteFile(String sFileToDelete);
	Boolean deleteFolder(String sFolderToDelete);
	Boolean deleteFolderAndItsContents(String sFolderToDelete);
}
