@rem ~~~~~~~ ABOUT: This batch file compiles the WEB-INF/ExecuteServlet.java and WEB-INF/getImagesServlet.java files ~~~~~~~~

@rem ~~~~~~~ INSTRUCTIONS: ~~~~~~~~
@rem 1. Edit the PATHs below to match your system paths for Java JDK and APACHE-TOMCAT server installation folders
@rem 2. If you're using your own version of the dependencies, edit out the jar paths to reflect your own files.

@rem ~~~~~~ -newline script below ~~~~~~
@echo off
REM Creating a Newline variable (the two blank lines are required!)
set NLM=^


set NL=^^^%NLM%%NLM%^%NLM%%NLM%
echo %NL%%NL%%NL%%NL% If there are errors in compilation please: %NL%%NL% 1. Edit the PATH vars in this batch file for Java JDK and APACHE-TOMCAT %NL%%NL% 2. Edit the jar paths as well, to reflect your own files. %NL%%NL% 3. Check if all .jar dependencies are satisfied
echo.
pause
@echo on

set JAVA_HOME=C:/Program Files/Java/jdk1.8.0_73
set CATALINA_HOME=C:/apache-tomcat-8.0.36
set APP_PATH=%CATALINA_HOME%/webapps/MachineLearningIITKGP

echo Compiling ExecuteServlet.java
@rem ~~~~~~~~ ExecuteServlet ~~~~~~~~~~~
echo 
javac -cp ".;%APP_PATH%/jars/servlet-api.jar;%APP_PATH%/jars/javaml-0.1.7.jar;%APP_PATH%/jars/deeplearning4j-examples-0.4-rc0-SNAPSHOT-bin.jar;%APP_PATH%/jars/ajt-2.9.jar;%APP_PATH%/jars/commons-math-1.2.jar;%APP_PATH%/jars/Jama-1.0.2.jar;%APP_PATH%/jars/libsvm.jar;%APP_PATH%/jars/deeplearning4j-examples-0.4-rc0-SNAPSHOT.jar;%APP_PATH%/jars/canova-api-0.0.0.16.jar;%APP_PATH%/jars/canova-nd4j-codec-0.0.0.16.jar;%APP_PATH%/jars/canova-nd4j-image-0.0.0.16.jar;%APP_PATH%/jars/nd4j-buffer-0.4-rc3.10.jar;%APP_PATH%/jars/nd4j-jblas-0.4-rc3.6.jar;%APP_PATH%/jars/nd4j-api-0.0.3.5.3.jar;%APP_PATH%/jars/nio.jar;%APP_PATH%/jars/opencv-2413.jar;" %APP_PATH%/WEB-INF/classes/ExecuteServlet.java

pause 
echo Compiling getImagesServlet.java

@rem ~~~~~~~~ getImagesServlet ~~~~~~~~~~
javac -cp ".;%APP_PATH%/jars/servlet-api.jar;%APP_PATH%/jars/javaml-0.1.7.jar;%APP_PATH%/jars/deeplearning4j-examples-0.4-rc0-SNAPSHOT-bin.jar;%APP_PATH%/jars/ajt-2.9.jar;%APP_PATH%/jars/commons-math-1.2.jar;%APP_PATH%/jars/Jama-1.0.2.jar;%APP_PATH%/jars/libsvm.jar;%APP_PATH%/jars/deeplearning4j-examples-0.4-rc0-SNAPSHOT.jar;%APP_PATH%/jars/canova-api-0.0.0.16.jar;%APP_PATH%/jars/canova-nd4j-codec-0.0.0.16.jar;%APP_PATH%/jars/canova-nd4j-image-0.0.0.16.jar;%APP_PATH%/jars/nd4j-buffer-0.4-rc3.10.jar;%APP_PATH%/jars/nd4j-jblas-0.4-rc3.6.jar;%APP_PATH%/jars/nd4j-api-0.0.3.5.3.jar;%APP_PATH%/jars/nio.jar;%APP_PATH%/jars/opencv-2413.jar;" %APP_PATH%/WEB-INF/classes/getImagesServlet.java

echo.
pause