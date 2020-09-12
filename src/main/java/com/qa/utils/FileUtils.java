package com.qa.utils;

import com.qa.parameters.FIleConstants;
import org.testng.Assert;

import java.io.*;
import java.util.UUID;

public class FileUtils {

  public static File downloadFolderUUID;

  public static void createDirectory(String dir) {
    File f = new File(dir);
    if (!f.exists()) {
      f.mkdirs();
    }
  }

  public static void deleteDirectory(String dir) {
    File f = new File(dir);
    if (!f.exists()) {
      f.deleteOnExit();
    }
  }

  //Create a random UUID folder for downloading files
  public static File createRandomUUIDDirectory() {
    downloadFolderUUID = new File(UUID.randomUUID().toString());
    FIleConstants.setDownloadFolderUUID(downloadFolderUUID.getPath());

    if (!downloadFolderUUID.exists()) {
      downloadFolderUUID.mkdirs();
    }
    return downloadFolderUUID;
  }

  //Check the file is present in downloaded folder
  public static void isFileDownloadedInUUIDFolder(){

    if(downloadFolderUUID.exists()) {
      File listOfFiles[] = downloadFolderUUID.listFiles();
      Assert.assertTrue(listOfFiles.length > 0);

      for (File file : listOfFiles) {
        Assert.assertTrue(file.length() > 0);
      }
    }
  }

  //Delete downloaded files and folder
  public static void deleteDownloadFolderUUIDFiles(){
    //File listOfFiles[] = downloadFolderUUID.listFiles();
    if(downloadFolderUUID.exists()){
      //File listOfFiles[] = downloadFolderUUID.fil.listFiles();
      if(!downloadFolderUUID.isFile()){
        downloadFolderUUID.delete();
      }else{
        for(File file: downloadFolderUUID.listFiles()){
          file.delete();
        }
        downloadFolderUUID.delete();
      }
    }
  }

  //Reads first column i.e [User] of csv files from download UUID folder.
  public static void downloadCSVFileReader() {
    String line = "";

    for (File filePath : downloadFolderUUID.listFiles()) {
      System.out.println("Reading file: "+ filePath.toString());
      try {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null) {
          String[] values = line.split(",");
          System.out.println(values[0]);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


}
