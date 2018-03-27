# Cloud-based IHC Image Analysis: ML Approach 

Description here about the Software......
 > Some more text here about the Software...
 > A little more.
 > [To be added later]

## Dependencies:
1. For compilation and execution of the Java Servlets, the following dependencies are necessary:
    - DeepLearning4Java (SNAPSHOT-bin.jar version >= 0.4-rc0)
    - ND4J and CANOVA jars
    - JavaML (>=0.1.7) jar
    - OpenCV (.jar, .lib, .pdp and .dll files)
    - Weka
    - nio.jar, ajt-2.9.jar, servlet-api.jar, commons-io-2.5.jar

- Run `install.bat` to download and fetch all these dependencies (mentioned in setup) 
    - If you wish to use your own version of the above dependencies other than the ones provided in the repository, please:
    	1.  Ensure the .jar files are in the `jars` folder of the Web-App.
        2. Copy the .dll files to the `WEB-INF/bin` folder and the .lib, .pdp files to the `WEB-INF/lib` folder.
        3. Reference the .jar files appropriately in the servlet compilation script `[compile.bat]`.
2. Apache Tomcat Server (or an equivalent hosting server) to deploy the Web-Application.
    - The application has been tested on x64 Windows with Apache Tomcat 8.0.33/36 server.
    - Apache Tomcat server 8.0 or later.
3. Java Development Kit (JDK) / Java SE Runtime Environment (JRE) version 7 or better

## Installation

1. Run `install.bat`
    - Extract the jars.rar files into `/jars` folder, the lib.rar and bin.rar files into `WEB-INF/lib` and `WEB-INF/bin` folders.
2. Ensure the application folder `/MachineLearningIITKGP` is in the webapps folder of your Apache Tomcat server installation. (Or equivalently to your local server manager's deployment folder)
3. Install and run Local Server.
    -   Download and Install Apache Tomcat ver 8 or later (http://tomcat.apache.org/)
    -   On Apache Tomcat, check if `%CATALINA_HOME%` env var is set to source path of Apache Tomcat server installation [ like `C:\apache-tomcat-8.0.36`]
4. Open your command prompt inside the `/MachineLearningIITKGP` folder, and execute:
```sh
%CATALINA_HOME%/bin/startup.sh 
```
## Servlet Compilation

 Shutdown the server if running by running this in your command prompt:
```sh
%CATALINA_HOME%/bin/shutdown.sh
```
 - Ensure ```compile.bat``` has the correct paths for the ENV variables and the .jar dependencies.

 - Run the ```compile.bat``` file. This will compile both the ```WEB-INF/classes/ExecuteServlet.java``` and ```WEB-INF/classes/getImagesServlet.java``` servlets.

Proceed to restart the server with ```%CATALINA_HOME%/bin/startup.sh```.

### Machine Learning Models

Currently, all models trained using JavaML/DeepLearning4J/Weka/LibSVM libraries are supported. 
- Following Machine Learning models are currently supported, with the nomenclature in the code followed as:

| Machine Learning Model | Abbrevation in Code: |
| ------ | ------ |
| Nearest Mean Classifier | [nmc] |
| K Nearest Neighbors | [knn] |
| MultiLayer Perceptron | [mlp] |
| AutoEncoders | [aen] |
| Linear SVM| [svm] |
| Random Forest | [rff] |
| Random Tree | [rnt] |

 To train and use your own Machine Learning models (from the above list):
 1. Pickle/serialize your trained models to their respective .bin (+ .json config) files.
 2. Move all .bin (+ .json) files to `'/models'` folder of the application, and rename them with the above nomenclature.

Other kinds of models can also be added, with suitable changes to the `ExecuteServlet.java` file.


## Development

License
----
