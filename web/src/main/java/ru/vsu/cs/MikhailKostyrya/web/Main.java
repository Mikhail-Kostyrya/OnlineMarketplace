package ru.vsu.cs.MikhailKostyrya.web;


import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        // Add the standard ContextConfig, which scans for annotations
        // from here https://stackoverflow.com/questions/67253024/tomcat-catalina-context-add-existing-servlet-to-context
        context.addLifecycleListener(new ContextConfig());
        // Add the JAR/folder containing this class to PreResources
        final WebResourceRoot root = new StandardRoot(context);
        final URL url = findClassLocation(Main.class);
        root.createWebResourceSet(WebResourceRoot.ResourceSetType.PRE, "/WEB-INF/classes", url, "/");
        context.setResources(root);
        // Run Tomcat
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static URL findClassLocation(Class< ? > clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation();
    }

}