# Framework for Dynamic Access to Machine Learning Models
###### This Framework provides a web-based GUI access to Dynamic pre-trained Machine learning models, for predictions based on the feature inputs, in **realtime**.

The aim behind this framework was to create an environment where a user can conveniently utilize the functionality of a Machine learning model, simply by loading and executing a pre-trained model, skipping the inconvenience of preprocessing ,training and fitting it on data.

It allows web-based access to the models, where users can simply choose a model of their liking, and proceed to enter a Comma-seperated input to obtain the model's predctions. 

###### The focus was on making access to the models as a plug-and-play system, with a good GUI to accompany it.

#### Functionality:

1. Easy access to pre-trained serialized ML models such as Random Forest, K Nearest Neighbors, Neural Networks and Support Vector           Machines over a network/Server.  
2. The models have been currently trained on Medical Datasets for the purpose of Multiclass Classification problems and are serialized    and stored in .model files.
3. The Framework is built such that the GUI and its functioning is independent of the underlying Machine Learning models and any            additions/updates required to the Models in the future can be done by simply replacing the orignal .model files.

#### Implementation:
The framework was mainly coded in Java, with some data-preprocessing done in R and Python for the Model training. 
The trained Machine Learning models are stored as .model binary files which are accessed using ObjectInputStreams. 

The trained Neural Network is stored as a .json (Network configuration) and .bin (Parameters) in model directory.
    
      Front-End : JavaScript, JQuery, HTML, CSS
      Client-End : Java (Servlets)
      Back-End : Java ML/Deeplearning 
    
      Libaries Used are: JavaML, Deeplearning4j, Weka

#### Screenshots of the UI:

Here are some screenshots for reference to the functionality.

> Index

![index1](https://cloud.githubusercontent.com/assets/12572543/16007486/6bef6798-3190-11e6-8a11-5432ee83b799.JPG)

> Model Select

![index2](https://cloud.githubusercontent.com/assets/12572543/16007723/d0e66178-3191-11e6-8ee0-dc2715f400c1.JPG)

> Enter input to Model

![inputs](https://cloud.githubusercontent.com/assets/12572543/16007727/d5e7c798-3191-11e6-88e9-0c14bbae214e.JPG)

> Model Output prediction

![results](https://cloud.githubusercontent.com/assets/12572543/16007730/d9068108-3191-11e6-9789-ad8e6b3bbe39.JPG)

