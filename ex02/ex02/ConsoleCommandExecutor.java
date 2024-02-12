package ex02;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ConsoleCommandExecutor {

  private File currentFolder;

  public ConsoleCommandExecutor(String filePath) {
    this.currentFolder = new File(filePath);
  }

  public void listFilesAndFolders() {
    File[] files = this.currentFolder.listFiles();
    if (files != null) {
      for (File file : files)
        if (file.isDirectory()) out.println(file.getName() + " " + getFolderSize(file) + " KB");
        else System.out.println(file.getName() + " " + getFileSize(file) + " KB");
    }
  }

  public void changeDirectory(String folderPath) {
    File newFoldr = new File(this.currentFolder, folderPath);
    if (!newFoldr.exists() || !newFoldr.isDirectory()) {
      out.println("cd: not a directory: " + folderPath);
    } else {
      currentFolder = newFoldr;
    }
  }

  public void moveFileOrRename(String source, String destination) {
    File sourceFile = new File(currentFolder, source);
    File destinationFile = new File(currentFolder, destination);

    if (!sourceFile.exists()) {
      out.println("file does not exist:" + sourceFile);
      return;
    }

    if (destinationFile.isDirectory()) {
      destinationFile = new File(destinationFile, sourceFile.getName());
    }
    try {
      Files.move(
          sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      out.println(e);
    }
  }

  private long getFileSize(File file) {
    if (file != null) return (file.length() / 1024);
    return 0;
  }

  private long getFolderSize(File folder) {
    File[] files = folder.listFiles();
    if (files != null) {
      return Arrays.stream(files)
          .mapToLong(file -> file.isDirectory() ? getFolderSize(file) : getFileSize(file))
          .sum();
    }
    return 0;
  }
}
