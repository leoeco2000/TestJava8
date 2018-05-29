package test.IO.AIO;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MonitorDir {
  Map<WatchKey, Path> keys = new ConcurrentHashMap<WatchKey, Path>();
  private static WatchService watcher = null;

  static {
    try {
      watcher = FileSystems.getDefault().newWatchService();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void register(Path dir) throws IOException { // IOException ,InterruptedException{
    WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

    Path existing = keys.get(key);
    if (existing == null) {
      System.out.format("register: %s\n", dir);
    } else if (!dir.equals(existing)) {
      System.out.format("update: %s -> %s\n", existing, dir);
    }

    keys.put(key, dir);
  }

  @SuppressWarnings("unchecked")
  static <T> WatchEvent<Path> cast(WatchEvent<?> event) {
    return (WatchEvent<Path>) event;
  }

  private void monitor(Path dir) throws IOException, InterruptedException {
    register(dir);

    // �ȴ������¼�����
    WatchKey key = watcher.take();

    // System.out.println(key.getClass().getName());
    Path path = keys.get(key);
    if (path == null) {
      return;
    }

    for (WatchEvent<?> event : key.pollEvents()) {
      WatchEvent.Kind kind = event.kind();
      if (kind == OVERFLOW) {
        continue;
      }

      // Ŀ¼�����¼������������ļ���
      WatchEvent<Path> evt = cast(event);
      Path name = evt.context();
      Path child = path.resolve(name);
      System.out.format(
          new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "  %s|%s\n",
          event.kind().name(), child);
    }

    // ���� key
    boolean valid = key.reset();
    if (!valid) {
      keys.remove(key);
      if (keys.isEmpty()) {
        return;
      }
    }
  }

  public static void main(String[] args) {
    MonitorDir monitorDir = new MonitorDir();
    String prefix = "D:/develop/workspace_photon/TestJava8/";
    Path target = Paths.get(prefix + "data/move/paste.txt");
    try {
      monitorDir.monitor(target);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
