# Quality Metrics For RDF Graph Summarization

You find here the  implementation of our Quality Framework for the RDF summaries the  takes as input the results of any RDF Graph Summarization algorithm and your  ideal summary and computes the different metrics that are required to capture the quality of the results at the different levels (schema and instance). For the schema level we compute  the precision, recall and F-measure for each class and its neighbourhood  of the produced summary against the ideal one.
For the Instance level, we  compute if the existing instances are covered (i.e. can be retrieved) and in what degree by the proposed summary. Again we define and compute its precision, recall and F-measure against the data contained in the original KB. It outputs the values for the different metrics in an automated fashion and allows computing F-measures where applicable.

## Setup:
Building this project requires the Apache Jena. All you need is in the lib folder, thus you should add all the jar files in the lib folder to the project build path. If you use  eclipse you need only to download the project and then import it and the all things will be fixed.

## Running the Project
To run a project you should Run the Main.java class which you can found in www.etis.ensea.fr.main package. The Main.java takes 3 parameters as input 
 
