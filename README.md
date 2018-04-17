
# Quality Metrics For RDF Graph Summarization

You find here the  implementation of our Quality Framework for the RDF summaries the  takes as input the results of any RDF Graph Summarization algorithm and your  ideal summary and computes the different metrics that are required to capture the quality of the results at the different levels (schema and instance). For the schema level we compute  the precision, recall and F-measure for each class and its neighbourhood  of the produced summary against the ideal one.
For the Instance level, we  compute if the existing instances are covered (i.e. can be retrieved) and in what degree by the proposed summary. Again we define and compute its precision, recall and F-measure against the data contained in the original KB. It outputs the values for the different metrics in an automated fashion and allows computing F-measures where applicable.

## Setup:
Building this project requires the Apache Jena. All you need is in the lib folder, thus you should add all the jar files in the lib folder to the project build path. If you use  eclipse you need only to download the project and then import it and the all things will be ok.

## Running the Project:
Before running the project you need mapping your summary graph to a text file where each line of this file represents pattern. Each line should have the following format: 
list of the properties mappings of the pattern  (the number of instances)  list of instances mappings represented by this pattern. 
For example, For the Knowledge patterns described in the following table.

| PatternId | class | Properties  | Instances
| -------- |-------------|-----|-----------
|1 | Painter  | fname, lname, paints | Picasso, Rembrandt
|2 |Painting  | exhibited |Woman, Guernica 
|3| Painting   | - | Abraham 
|4| Museum   | - |museum.es|

you should have one text file consists of four lines as following. <br />
1 &nbsp; 2 &nbsp; 3  &nbsp;4 (2) 1&nbsp;  2 <br />
5 &nbsp; 6 (2) 3 &nbsp; 4 <br />
5  (1) 5 <br />
7  (1) 6 <br />

Where the two following tables describe the two necessary property and instance hashMaps respectively. 

| Mapid | property URI        
| -------- |-------------
1 | Painter  
2 | fname
3| lname
4| paints 
5 |Painting  
6| exhibited 
7| Museum

| Mapid | Instance URI        
| -------- |-------------
1 | Picasso  
2 | Rembrandt
3| Woman
4| Guernica 
5 |Abraham  
6| museum.es

To run a project you should Run the Main.java class which you can found in www.etis.ensea.fr.main package. The Main.java takes 3 parameters as input:
1. The maped text file
2. The Property hashMap as a .txt file
3. The RDF file of your ideal summary
 
