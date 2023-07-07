@echo off 

javac framework\*.java -d framework\WEB-INF\classes

jar -cvf "G:\frameworkTravailDeBureau\testFramework\WEB-INF\lib\myframework.jar" -C "G:\frameworkTravailDeBureau\framework\WEB-INF\classes" .

mkdir "temp/WEB-INF/classes"

mkdir "temp/WEB-INF/lib"

xcopy testFramework\WEB-INF\classes temp\WEB-INF\classes /E

xcopy testFramework\WEB-INF\lib temp\WEB-INF\lib /E

copy "testFramework\*.jsp" "temp" 

copy "testFramework\WEB-INF\web.xml" "temp\WEB-INF"

jar -cvf framework.war -C "temp" .

copy "G:\frameworkTravailDeBureau\framework.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps" 

rmdir /s /q temp