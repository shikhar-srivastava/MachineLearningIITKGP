# Cloud-based IHC Image Analysis: ML Approach 

Description here about the Software......
 > Some more text here about the Software...
 > A little more.
 > [To be added later]
---
## Dependencies:

1. Apache Tomcat Server (or an equivalent hosting server) to deploy the Web-Application.

    - Apache Tomcat server 8.0 or later.
    - The application has been tested on x64 Windows with Apache Tomcat 8.0.33/36 & 9.0.6 servers.
    
2. Java Development Kit (JDK) / Java SE Runtime Environment (JRE) version 7 or better

3. For compilation and execution of the Java Servlets, the following dependencies will be used (available through `install.bat` in setup) :

    - DeepLearning4Java (SNAPSHOT-bin.jar version >= 0.4-rc0)
    - ND4J and CANOVA jars
    - JavaML (>=0.1.7) jar
    - OpenCV (.jar, .lib, .pdp and .dll files)
    - Weka
    - nio.jar, ajt-2.9.jar, servlet-api.jar, commons-io-2.5.jar
    
    
## Installation

1. Ensure Java JDK/JRE is installed,`JAVA_HOME` env variable is set. 

   **Install Apache Tomcat.**
    -   Download and Install Apache Tomcat ver 8 or later (http://tomcat.apache.org/)
    -   On Apache Tomcat, check if `%CATALINA_HOME%` env var is set to source path of Apache Tomcat server installation [ like `C:\apache-tomcat-8.0.36`]
    
2. **Clone the repository inside your Apache Tomcat's webapps folder** (`%CATALINA_HOME%/webapps/`) using:
   ```sh
   cd %CATALINA_HOME%/webapps/
   git clone https://github.com/soilad/MachineLearningIITKGP.git
   ```
   Ensure the application folder `/MachineLearningIITKGP` is in the webapps folder of your Apache Tomcat server installation. (Or equivalently to your local server manager's deployment folder)

3. **Run `install.bat` from the repository.**

    - Extract the `jars.rar` files into `MachineLearningIITKGP/jars` folder, the `lib.rar` and `bin.rar` files each into `MachineLearningIITKGP/WEB-INF/lib` and `MachineLearningIITKGP/WEB-INF/bin` folders respectively.
    - Extract both `jars.rar` and `lib.rar` into `%CATALINA_HOME%/lib` folder of your Apache Tomcat source location.
    
    - If you wish to use your own version of the above dependencies other than the ones provided in the repository, please:
        1. Ensure the .jar files are extracted to the `WEB-INF/bin` and `%CATALINA_HOME%/lib` folders as in above steps.
        2. Copy the .dll files to the `WEB-INF/bin` folder and the .lib, .pdp files to both `WEB-INF/lib` and `%CATALINA_HOME%/lib` folders.
        3. Edit the servlet compilation script `compile.bat` and reference the .jar files appropriately.
        
    
4.  **Edit the `%CATALINA_HOME%/bin/catalina.bat` file as follows:**
     - In the file `catalina.bat`, search for the line 
       ```sh
       :noJuliConfig`.
       ```
     - Add the following to the line after `:noJuliConfig`:
       ```sh
       set "JAVA_OPTS=%JAVA_OPTS% %LOGGING_CONFIG%"
       set JAVA_OPTS="-Djava.library.path=%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\lib;%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\bin;%CATALINA_HOME%\webapps\MachineLearningIITKGP\jars;"
       ```
     - The file should now look like:
        ```sh
       :noJuliConfig
       set "JAVA_OPTS=%JAVA_OPTS% %LOGGING_CONFIG%"
       set JAVA_OPTS="-Djava.library.path=%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\lib;%CATALINA_HOME%\webapps\MachineLearningIITKGP\WEB-INF\bin;%CATALINA_HOME%\webapps\MachineLearningIITKGP\jars;"
       ```


5. **Open your command prompt and execute:**
   ```sh
   %CATALINA_HOME%/bin/startup
   ```
   [Run `http://localhost:8080/docs/` in your browser to check if the server is running correctly.]
   
    If the above test doesn't work, check your firewall and create an exception. Alternatively, you could change the default server port   number as follows:
      - Goto tomcat>conf folder
      - Edit server.xml
      - Search "Connector port"
      - Replace "8080" by your port number
      - Restart tomcat server.

6. **Open `http://localhost:[portno]/MachineLearningIITKGP` in your browser.** (By default, portno is 8080)

## Servlet Compilation

 Shutdown the server if running by running this in your command prompt:
```sh
%CATALINA_HOME%/bin/shutdown
```
 - Ensure ```compile.bat``` has the correct paths for the ENV variables and the .jar dependencies.

 - Run the ```compile.bat``` file. This will compile both the ```WEB-INF/classes/ExecuteServlet.java``` and ```WEB-INF/classes/getImagesServlet.java``` servlets.

Proceed to restart the server with ```%CATALINA_HOME%/bin/startup```.

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
