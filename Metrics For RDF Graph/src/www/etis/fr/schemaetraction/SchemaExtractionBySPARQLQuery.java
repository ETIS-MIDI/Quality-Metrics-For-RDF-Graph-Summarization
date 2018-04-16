package www.etis.fr.schemaetraction;

import java.awt.GradientPaint;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import www.etis.ensea.fr.metrics.common.RDFClass;
import jena.rdfcat;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.VCARD;


public class SchemaExtractionBySPARQLQuery {
	public static Model loadModel(String filename) {
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( filename );
	    model.read(filename);
	    return model;
	    }
	// query to  extract all the different used classes and their used properties
	public ArrayList<RDFClass> extractSchemaInformation(String idealSummaryPathName) throws IOException {

		 Model model = loadModel(idealSummaryPathName);
		String queryString ="PREFIX rdf:<"+RDF.getURI()+"> PREFIX foaf:<"+FOAF.getURI() +"> select    distinct ?u  (COUNT(distinct ?t) AS ?i) WHERE { ?t rdf:type ?u." +
		"  } GROUP BY ?u";
		int classcounter=0; 
		ArrayList<RDFClass> classes= new ArrayList<RDFClass>();
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);  
		try {
			ResultSet results = qexec.execSelect();
			while(results.hasNext()){
				QuerySolution q1= results.nextSolution();
				String type= q1.get("u").toString();
				RDFClass r=new RDFClass();
		        r.setType(type);
		        String instancenb=q1.get("i").toString().split("\\^")[0];
		        String queryString1 ="PREFIX rdf:<"+RDF.getURI()+"> PREFIX foaf:<"+FOAF.getURI() +"> select ?p (COUNT(?p) AS ?count1)  WHERE { ?t rdf:type "+"<"+type+"> ; ?p ?s" +
		        "  } GROUP BY ?p";
		        Query query1 = QueryFactory.create(queryString1);
				QueryExecution qexec1 = QueryExecutionFactory.create(query1, model);
				ResultSet results1 = qexec1.execSelect();
				ArrayList<String> prop=new ArrayList<String>();
				// ResultSetFormatter.out(System.out, results, query);
				prop.add(RDF.type+"");
				while(results1.hasNext()){
					QuerySolution q= results1.nextSolution();
					String p= q.get("p").toString();
					prop.add(p);
					}
				r.setProperties(prop);
				//System.out.println("instances"+Integer.parseInt(instancenb));
				r.setNbinstances(Integer.parseInt(instancenb));
				classes.add(r);
				classcounter++;
				}
		 
			}
		finally {}
		return classes;
		}
	
	

}
