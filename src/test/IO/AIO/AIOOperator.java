package test.IO.AIO;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class AIOOperator {
  private static String prefix = "D:/develop/workspace_photon/TestJava8/";

  /**
   * 创建文件
   * 
   * @throws IOException
   */
  public static void createFile() throws IOException {
    Path target = Paths.get(prefix + "data/copy.txt");
    Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
    FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
    Files.createFile(target, attr);
  }

  /**
   * 删除文件
   * 
   * @throws IOException
   */
  public static void deleteFile() throws IOException {
    Path target = Paths.get(prefix + "data/paste.txt");
    Files.delete(target);
  }

  /**
   * 拷贝文件
   * 
   * @throws IOException
   */
  public static void copyFile() throws IOException {
    Path source = Paths.get(prefix + "data/copy.txt");
    Path target = Paths.get(prefix + "data/paste.txt");
    Files.copy(source, target, REPLACE_EXISTING);
  }

  /**
   * 移动文件
   * 
   * @throws IOException
   */
  public static void moveFile() throws IOException {
    Path source = Paths.get(prefix + "data/paste.txt");
    Path target = Paths.get(prefix + "data/move/paste.txt");

    Files.move(source, target, REPLACE_EXISTING, COPY_ATTRIBUTES);
  }

  public static void main(String[] args) {
    try {
      long start = System.currentTimeMillis();
      // createFile();
      // deleteFile();
      copyFile();
      // moveFile();

      long end = System.currentTimeMillis();
      System.out.println("consume -> " + (end - start));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
