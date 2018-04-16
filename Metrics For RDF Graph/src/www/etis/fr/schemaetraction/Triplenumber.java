package www.etis.fr.schemaetraction;



import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import www.etis.ensea.fr.metrics.common.RDFClass;

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


public class Triplenumber {

	
	public static Model loadModel(String filename)
	{
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( filename );
	    model.read(filename);
	     return model;
	}
	
	
	public int getClassInstancessNumber(String rdfinputidealsummary)
	{
		Model m=loadModel(rdfinputidealsummary);
		 //  TDB.getContext().set(TDB.symUnionDefaultGraph, true) ;
		    String sparqlQueryString = "PREFIX rdf:<"+RDF.getURI()+"> PREFIX foaf:<"+FOAF.getURI() +"> select (COUNT(distinct ?t) AS ?i) WHERE {?t ?y ?v."
		    		+ "" +
		    		"  }  ";
		    int classInstancesNumber=1;
		    ArrayList<RDFClass> classes= new ArrayList<RDFClass>();
		    Query query = QueryFactory.create(sparqlQueryString) ;
		    QueryExecution qexec = QueryExecutionFactory.create(query, m) ;
		   // qexec.getContext().set(TDB.symUnionDefaultGraph, true) ;
             
		    try {
				ResultSet results = qexec.execSelect();
				
					QuerySolution q1= results.nextSolution();
					String instancenb=q1.get("i").toString().split("\\^")[0];
					System.out.println(instancenb);
					classInstancesNumber=Integer.parseInt(instancenb);
					
				
		    }
			finally {}
		    return classInstancesNumber;
	}
	
	}

