package etu2018.framework.servlet;

import etu2018.framework.*;
import etu2018.framework.annotation.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.lang.reflect.*;
import java.rmi.ServerException;



public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> mappingUrls = new HashMap<>();
    public void setMappingUrls(HashMap<String,Mapping> mappingUrls){
        this.mappingUrls = mappingUrls;
    }
    public HashMap<String,Mapping> getMappingUrls(){
        return this.mappingUrls;
    }
    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (int i = 0;i<files.length;i++) {
            if (files[i].isDirectory()) {
                classes.addAll(findClasses(files[i], packageName + "." + files[i].getName()));
            } else if (files[i].getName().endsWith(".class")) {
                String className = packageName + "." + files[i].getName().substring(0, files[i].getName().length() - 6);
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
        return classes;
    }
    
    @Override
    public void init() throws ServletException{
        File f = null;
        try{
            f = new File("C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/framework/WEB-INF/classes/model");
            List<Class<?>> classes = FrontServlet.findClasses(f,"model");
            for(int i = 0;i<classes.size();i++){
                Class<?> clazz = classes.get(i);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(etu2018.framework.annotation.Annotation.class)) {
                        Annotation annotation = method.getAnnotation(etu2018.framework.annotation.Annotation.class);
                        String url = annotation.url();
                        Mapping newmap = new Mapping();
                        newmap.setClasse(clazz.getName());
                        newmap.setMethod(method.getName());
                        mappingUrls.put(url,newmap);
                    }
                }
            } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h3>Servlet UrlController at " + request.getServletPath() + "</h3>");
        out.println(mappingUrls.size());
        for(Map.Entry<String, Mapping> entry : mappingUrls.entrySet()){
            String url = entry.getKey();
            Mapping map = entry.getValue();
            out.println("<p>" + map.getMethod() + "</p>");
        }   
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
