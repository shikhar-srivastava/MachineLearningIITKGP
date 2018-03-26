~~~~~~~ I. DEPENDENCIES ~~~~~~~~

For compilation and editing of the Java Servlets, the following dependencies are required:
		- DeepLearning4Java (SNAPSHOT-bin.jar version >= 0.4-rc0), ND4J and CANOVA jars
		- JavaML (>=0.1.7) jar
		- OpenCV (.jar, .lib, .pdp and .dll files)
		- Weka
		- nio.jar, ajt-2.9.jar, servlet-api.jar, commons-io-2.5.jar

- These dependencies have all been provided as part of the repository. (Except the DL4J bin .jar which is provided through the install.bat script (Step 1 in Setup))
- The application has been tested on x64 Windows with Apache Tomcat 8.0.33/36 server.

To use your own version of the above dependencies other than the ones provided in the repository, please:
	1. Ensure the .jar files are in the "jars" folder of the Web-App. 
	2. Copy the .dll files to the WEB-INF/bin folder and the .lib, .pdp files to the WEB-INF/lib folder.
	3. Reference the .jar files appropriately in the servlet compilation script mentioned below.


~~~~~~~ II. SETUP INSTRUCTIONS ~~~~~~~~

	1. Run 'install.bat'. Download the file "deeplearning4j-examples-0.4-rc0-SNAPSHOT-bin.jar" from the link. Move it to /jars folder.

	2. Ensure the application folder "MachineLearningIITKGP" is in the webapps folder of your APACHE TOMCAT server install. (Or equivalently to your local server manager's deployment folder)

	3. Run Local Server. 
		-On Apache Tomcat, run %CATALINA_HOME%/bin/startup.sh in the cmd prmpt. 

