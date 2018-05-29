package test.JVM.ClassloadSerial;

import java.net.URL;

public class classLoaderSerialTest {
	public static void main(String[] args) {

		Child child = new Child();
		try {
			child.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}
		System.out.println("----- ·Ö¸ô·û------");
		System.out.println(System.getProperty("sun.boot.class.path"));
	}
}
