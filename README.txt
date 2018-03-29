			


		================================================
							README
		================================================



~~~~~~~~~~~~~ (I) DEPENDENCIES ~~~~~~~~~~~~~~~

	(1.1) For compilation and execution of the Java Servlets, the following dependencies will be used:

			- DeepLearning4Java (SNAPSHOT-bin.jar version >= 0.4-rc0), ND4J and CANOVA jars
			- JavaML (>=0.1.7) jar
			- OpenCV (.jar, .lib, .pdp and .dll files)
			- Weka
			- nio.jar, ajt-2.9.jar, servlet-api.jar, commons-io-2.5.jar

	(1.2) Apache Tomcat Server (or an equivalent hosting server) to deploy the Web-Application.

		- The application has been tested on x64 Windows with Apache Tomcat 8.0.33/36 & 9.0.6 server.

		- Apache Tomcat server 8.0 or later.

	(1.3) Java Development Kit (JDK) / Java SE Runtime Environment (JRE) version 7 or better

		- Apache Tomcat 8.0 requires a Java Standard Edition Runtime Environment (JRE) version 7 or later. 

		- Download a Java SE Runtime Environment (JRE), release version 7 or later, or Java Development Kit JDK 




~~~~~~~~~~~~~~~ (II) SETUP INSTRUCTIONS ~~~~~~~~~~~~~~~

	(2.1) Ensure Java JDK/JRE is installed,JAVA_HOME env variable is set.

	(2.2) Install Apache Tomcat.

		(2.2.1)	Download and Install Apache Tomcat ver 8 or later (http://tomcat.apache.org/)

				On Apache Tomcat, check if %CATALINA_HOME% env var is set to source path of Apache Tomcat server installation [ like C:\apache-tomcat-8.0.36]

	(2.3) Clone the repository inside your Apache Tomcat's webapps folder (%CATALINA_HOME%/webapps/) using:

			cd %CATALINA_HOME%/webapps/
			git clone https://github.com/soilad/MachineLearningIITKGP.git

		(2.3.1) Ensure the application folder /MachineLearningIITKGP is in the webapps folder of your Apache Tomcat server installation. 
			    (Or equivalently to your local server manager's deployment folder)

	(2.4) Run install.bat from the repository.

		(2.4.1) Extract the jars.rar files into MachineLearningIITKGP/jars folder, the lib.rar and bin.rar files each into MachineLearningIITKGP/WEB-INF/lib and MachineLearningIITKGP/WEB-INF/bin folders respectively.

		(2.4.2) Extract both jars.rar and lib.rar into %CATALINA_HOME%/lib folder of your Apache Tomcat source location.

		(2.4.3) If you wish to use your own version of the above dependencies other than the ones provided in the repository, please:

			(a) Ensure the .jar files are extracted to the WEB-INF/bin and %CATALINA_HOME%/lib folders as in above steps.

			(b) Copy the .dll files to the WEB-INF/bin folder and the .lib, .pdp files to both WEB-INF/lib and %CATALINA_HOME%/lib folders.

			(c) Edit the servlet compilation script compile.bat and reference the .jar files appropriately.

	(2.5) Edit the %CATALINA_HOME%/bin/catalina.bat file as follows:

		(2.5.1) In the file catalina.bat, search for the line

					:noJuliConfig

		(2.5.2) Copy the following to the line after ":noJuliConfig" :

				set JAVA_OPTS="-Djava.library.path=%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\lib;%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\bin;%CATALINA_HOME%\webapps\MachineLearningIITKGP\jars;"
				set "JAVA_OPTS=%JAVA_OPTS% %LOGGING_CONFIG% -Dcatalina_home=%CATALINA_HOME%"

		(2.5.3) The file should now look like:

				:noJuliConfig
				set JAVA_OPTS="-Djava.library.path=%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\lib;%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\bin;%CATALINA_HOME%\webapps\MachineLearningIITKGP\jars;"
				set "JAVA_OPTS=%JAVA_OPTS% %LOGGING_CONFIG% -Dcatalina_home=%CATALINA_HOME%"

	(2.6) Open your command prompt and execute:

			%CATALINA_HOME%/bin/startup

		This will start the server.
		[Run http://localhost:8080/docs/ in your browser to check if the server is running correctly.]

		If the above test doesn't work, check your firewall and create an exception. Alternatively, you could change the default server port number as follows:

			(a) Goto tomcat>conf folder
			
			(b)	Edit server.xml

			(c) Search "Connector port"

			(d) Replace "8080" by your port number

			(e) Restart tomcat server.

	(2.7) Run http://localhost:[portno]/MachineLearningIITKGP in your browser. (By default, portno is 8080)


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

