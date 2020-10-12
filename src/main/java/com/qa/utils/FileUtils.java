package com.qa.utils;

import com.qa.constants.DirectoryConstants;
import com.qa.parameters.FIleConstants;
import com.qa.scripts.clusters.yarn.Yarn;
import org.testng.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Logger;

public class FileUtils {
    public static File downloadFolder;
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    /* Create a directory with given name */
    public static void createDirectory(String dir) {
      File f = new File(dir);
      if (!f.exists()) {
        f.mkdirs();
      }
    }

    /* Delete directory with given name */
    public static void deleteDirectory(String dir) {
      File f = new File(dir);
      if (!f.exists()) {
        f.deleteOnExit();
      }
    }

    /**
     * @param filePath - File which you want to move in archive folder
     * @param isAppendDate - If true then current date will be appended in file name
     * before moving file in archive folder otherwise file will be moved as it is
     */
    public static void moveFileToArchive(String filePath, Boolean isAppendDate) {
      File file = new File(filePath);
      if (file.exists()) {
        String fileDir = file.getParent() + File.separator + DirectoryConstants.ARCHIVE;
        createDirectory(fileDir);
        String fileName = file.getName();
        if (isAppendDate) {
          fileName = file.getName().split("\\.")[0] + DateUtils.getCurrentDateTime().replaceAll("[: ]", "-") +
                  "." + file.getName().split("\\.")[1];
        }
        String resultFilePath = fileDir + File.separator + fileName;
        if (file.renameTo(new File(resultFilePath))) {
          file.delete();
        }
      }
    }

    /* Create a known folder 'downloadsFolder' for downloading any thing under this project. */
    public static File createDownloadsFolder() {
      String downloadsFolder = DirectoryConstants.getDownloadsDir();
      FileUtils.createDirectory(downloadsFolder);
      downloadFolder = new File(downloadsFolder);
      FIleConstants.setDownloadFolderUUID(downloadFolder.getPath());
      if (!downloadFolder.exists()) {
        downloadFolder.mkdirs();
      }
      return downloadFolder;
    }

    /* Create a random UUID folder for downloading files */
    public static File createRandomUUIDDirectory() {
      downloadFolder = new File(UUID.randomUUID().toString());
      FIleConstants.setDownloadFolderUUID(downloadFolder.getPath());
      if (!downloadFolder.exists()) {
        downloadFolder.mkdirs();
      }
      return downloadFolder;
    }

    /* Check the file is present in downloaded folder */
    public static void isFileDownloadedInUUIDFolder() {

      if (downloadFolder.exists()) {
        File listOfFiles[] = downloadFolder.listFiles();
        Assert.assertTrue(listOfFiles.length > 0);

        for (File file: listOfFiles) {
          Assert.assertTrue(file.length() > 0);
        }
      }
    }

    /* Delete downloaded files and folder */
    public static void deleteDownloadFolderUUIDFiles() {
      //File listOfFiles[] = downloadFolderUUID.listFiles();
      if (downloadFolder.exists()) {
        //File listOfFiles[] = downloadFolderUUID.fil.listFiles();
        if (!downloadFolder.isFile()) {
          downloadFolder.delete();
        } else {
          for (File file: downloadFolder.listFiles()) {
            file.delete();
          }
          downloadFolder.delete();
        }
      }
    }

    /* Delete all downloaded files only from 'downloadsFolder' */
    public static void deleteDownloadsFolderFiles() {
      if (downloadFolder.exists()) {
        for (File f: downloadFolder.listFiles()) {
          f.deleteOnExit();
        }
      }
    }

    /* Verify the file is present and check its size */
    public static boolean checkForFileNameInDownloadsFolder(String fileName) {
      if (downloadFolder.exists()) {
        for (File f: downloadFolder.listFiles()) {
          if (f.getName().equals(fileName)) {
            String fileNamePath = f.getAbsolutePath();
            LOGGER.info("Absolute filename path: " + fileName);
            Path path = Paths.get(fileNamePath);
            LOGGER.info("File path" + path.getFileName());
            try {
              long bytes = Files.size(path);
              LOGGER.info(String.format("%,d bytes", bytes));
              LOGGER.info(String.format("%,d kilobytes", bytes / 1024));
              if (bytes > 0) {
                return true;
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
      return false;
    }

    /* Reads first column i.e [User] of csv files from download UUID folder. */
    public static void downloadCSVFileReader() {
      String line = "";
      for (File filePath: downloadFolder.listFiles()) {
        LOGGER.info("Reading file: " + filePath.toString());
        try {
          BufferedReader br = new BufferedReader(new FileReader(filePath));
          while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            LOGGER.info(values[0]);
          }
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

}