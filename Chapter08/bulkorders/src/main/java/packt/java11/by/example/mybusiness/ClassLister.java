package packt.java11.by.example.mybusiness;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassLister {
    private static Logger log = LoggerFactory.getLogger(ClassLister.class);

    public void listClasses() {
        ClassLoader loader = this.getClass().getClassLoader();
        while (loader != null) {
            listClasses(loader);
            loader = loader.getParent();
        }
    }

    private void listClasses(ClassLoader loader) {
        if (loader instanceof URLClassLoader) {
            listClasses((URLClassLoader) loader);
        } else {
            log.info("Class loader {} is not an url classloader, can not list classes.", loader);
        }
    }

    private void listClasses(URLClassLoader loader) {
        URL[] urls = loader.getURLs();
        for (final URL url : urls) {
            log.info("Listing classes for URL {}", url);
            listClasses(loader, url);
        }
    }

    private void listClasses(URLClassLoader loader, URL url) {
        if ("file".equals(url.getProtocol())) {
            File file = new File(url.getFile());
            if (file.isDirectory()) {
                log.info("Listing the classes from directory {}", file);
                listClasses(loader, file);
            } else {
                try {
                    JarFile jarFile = new JarFile(file);
                    log.info("Listing classes from jar file {}", jarFile.getName());
                    listClasses(loader, jarFile);
                } catch (IOException e) {
                    log.info("File {} can not be opened as a jar file", file);
                }
            }
        } else {
            log.info("Url {} is not a file url, can not list");
        }
    }

    private void listClasses(URLClassLoader loader, File directory) {
        listClasses(loader, directory, "");
    }

    private void listClasses(URLClassLoader loader, File directory, String pckage) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listClasses(loader, file, pckage + file.getName() + ".");
            } else {
                final String className = pckage + withoutExt(file.getName());
                try {
                    listClass(loader, className);

                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    log.info("File {} does not seem to be a class of {}, probably resource file", file, className);
                }
            }
        }
    }

    private void listClasses(URLClassLoader loader, JarFile jarFile) {
        for (Enumeration<JarEntry> entries = jarFile.entries();
             entries.hasMoreElements(); ) {
            JarEntry entry = entries.nextElement();
            final String className = withoutExt(entry.getName()).replaceAll("/", ".");
            try {
                listClass(loader, className);
            } catch (ClassNotFoundException | NoClassDefFoundError e) {
                log.info("File [{}]{} does not seem to be a class of {}, probably resource file", jarFile.getName(), entry.getName(), className);
            }
        }
    }

    private void listClass(URLClassLoader loader, String className) throws ClassNotFoundException {
        Class klass = loader.loadClass(className);
        log.info("Listing class {}", klass);
        listClass(klass);
    }

    private void listClass(Class klass) {
        log.info("Class {} extends {} implements {}", klass.getCanonicalName(), klass.getSuperclass(), ccatInterfaces(klass.getInterfaces()));
    }

    private String ccatInterfaces(Class[] interfaces) {
        if (interfaces.length > 0) {
            final String[] ifNames = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                ifNames[i] = interfaces[i].getCanonicalName();
            }
            return String.join(", ", ifNames);
        } else {
            return null;
        }
    }

    private String withoutExt(String fn) {
        return fn.replaceAll("\\..*", "");
    }
}
