			


		================================================
							README
		================================================



~~~~~~~~~~~~~ (I) DEPENDENCIES ~~~~~~~~~~~~~~~

	(1.1) For compilation and execution of the Java Servlets, the following dependencies are necessary:

			- DeepLearning4Java (SNAPSHOT-bin.jar version >= 0.4-rc0), ND4J and CANOVA jars
			- JavaML (>=0.1.7) jar
			- OpenCV (.jar, .lib, .pdp and .dll files)
			- Weka
			- nio.jar, ajt-2.9.jar, servlet-api.jar, commons-io-2.5.jar

		*Run install.bat to download and fetch all these dependencies (As mentioned in Setup Instructions)

			If you wish to use your own version of the above dependencies other than the ones provided in the repository, please:

				1. Ensure the .jar files are in the "jars" folder of the Web-App. 
				2. Copy the .dll files to the WEB-INF/bin folder and the .lib, .pdp files to the WEB-INF/lib folder.
				3. Reference the .jar files appropriately in the servlet compilation script [compile.bat].

	(1.2) Apache Tomcat Server (or an equivalent hosting server) to deploy the Web-Application.

		- The application has been tested on x64 Windows with Apache Tomcat 8.0.33/36 server.

		- Apache Tomcat server 8.0 or later.

	(1.3) Java Development Kit (JDK) / Java SE Runtime Environment (JRE) version 7 or better

		- Apache Tomcat 8.0 requires a Java Standard Edition Runtime Environment (JRE) version 7 or later. 

		- Download a Java SE Runtime Environment (JRE), release version 7 or later, or Java Development Kit JDK 




~~~~~~~~~~~~~~~ (II) SETUP INSTRUCTIONS ~~~~~~~~~~~~~~~

	(2.1) Run 'install.bat'. Extract the jars.rar files into '/jars' folder, the lib.rar and bin.rar files into 'WEB-INF/lib' and 'WEB-INF/bin' folders.

	(2.2) Ensure the application folder "MachineLearningIITKGP" is in the webapps folder of your APACHE TOMCAT server installation. (Or equivalently to your local server manager's deployment folder)

	(2.3) Install and run Local Server. 

		(2.3.1)	Download and Install Apache Tomcat ver 8 or later (http://tomcat.apache.org/)

		(2.3.2)	On Apache Tomcat, check if %CATALINA_HOME% env var is set to source path of APACHE TOMCAT [like C:\apache-tomcat-8.0.36]

		(2.3.3)	Execute %CATALINA_HOME%/bin/startup.sh in the cmd prmpt.




~~~~~~~~~~~~~~~ (III) SERVLET COMPILATION ~~~~~~~~~~~~~~~

	(3.1) Shutdown the server if running. (Execute %CATALINA_HOME%/bin/shutdown.sh in cmd prmpt)

	(3.2) Ensure compile.bat has the correct paths for the ENV variables and the .jar dependencies.

	(3.3) Run the compile.bat file. This will compile both the WEB-INF/classes/ExecuteServlet.java and WEB-INF/classes/getImagesServlet.java servlets.

	(3.4) Re-start the server. (Execute %CATALINA_HOME%/bin/startup.sh in cmd prmpt)


~~~~~~~~~~~~~~~~ (IV) MACHINE LEARNING MODELS ~~~~~~~~~~~~~~~~~


	(4.1) Currently, all models trained using JavaML/DeepLearning4J/Weka/LibSVM libraries are supported.

	     Following Machine Learning models are currently supported, with the nomenclature followed as:

	     "Nearest Mean Classifier"   =   nmc
	     "K Nearest Neighbors"       =   knn
	     "MultiLayer Perceptron"     =   mlp
	     "AutoEncoders"              =   aen
	     "Linear SVM"                =   svm
	     "Self Optimizing SVM"       =   sosvm
	     "Random Forest"             =   rff
	     "Random Tree"               =   rnt

	     To train and use your own Machine Learning models (from the above list):

		(4.1.1) Pickle/serialize your trained models to their respective .bin (+ .json config) files.

		(4.1.2) Move all .bin (+ .json) files to '/models' folder of the application, and rename them with the above nomenclature.

				Other kinds of models can also be added, with suitable changes to the ExecuteServlet.java file.

