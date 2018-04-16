package www.etis.fr.schemaetraction;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;



public class PropertyInstanceExtraction {
	
	public static Model loadModel(String filename) {
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( filename );
	    model.read(filename);
	    return model;
	    }
	
	public ArrayList<String> extract(String idealSummaryPathName ){
		 Model model = loadModel(idealSummaryPathName);
		 Hashtable<String, Double> properties=new Hashtable<String, Double>();
		 ArrayList<String> allproperties= new ArrayList<String>();
		 String sparqlQueryString = "PREFIX rdf:<"+RDF.getURI()+"> PREFIX foaf:<"+FOAF.getURI() +"> select    distinct ?u  (COUNT(?t) AS ?i) WHERE { ?t rdf:type ?u." +
		    		"  } GROUP BY ?u" ;
		 Query query = QueryFactory.create(sparqlQueryString) ;
		 QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		 try {
			 ResultSet results = qexec.execSelect();
			 while(results.hasNext()){
				 QuerySolution q1= results.nextSolution();
				 String type= q1.get("u").toString();
				 String queryString1 ="PREFIX rdf:<"+RDF.getURI()+"> PREFIX foaf:<"+FOAF.getURI() +"> select ?p (COUNT(?p) AS ?count1)  WHERE { ?t rdf:type "+"<"+type+"> ; ?p ?s" +
			        "  } GROUP BY ?p";
				 Query query1 = QueryFactory.create(queryString1);
			     QueryExecution qexec1 = QueryExecutionFactory.create(query1, model) ;
			     ResultSet results1 = qexec1.execSelect();
			     while(results1.hasNext()){
			    	 QuerySolution q= results1.nextSolution();
			    	 String p= q.get("p").toString();
			    	 if(!properties.containsKey(p)){
			    		 properties.put(p, Double.parseDouble(q.get("count1").toString().split("\\^")[0]));
			    		 }
			    	else{
			    		Double d=properties.get(p);
			    		d=d+Double.parseDouble(q.get("count1").toString().split("\\^")[0]);
			    		}
			    	 }
			     }
			 for(String p: properties.keySet() ) {
				 Double d=properties.get(p);
				 allproperties.add(d+";"+p);
				 }
			 }
		 catch(Exception e) {System.out.println("PropertyInstanceExtraction Execption "+e.getMessage());}
		 return allproperties;
			
		 
	}

	

}

