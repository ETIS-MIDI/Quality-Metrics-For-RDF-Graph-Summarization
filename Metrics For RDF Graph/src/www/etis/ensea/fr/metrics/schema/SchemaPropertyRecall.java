package www.etis.ensea.fr.metrics.schema;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.ListUtils;

import www.etis.ensea.fr.metrics.common.*;
public class SchemaPropertyRecall {
	public double getSchemaPropertyRecall(ArrayList<Pattern> pas , ArrayList<RDFClass> classes){
		ArrayList<String> allPatternsPropertis= new ArrayList<String>();
		for (Pattern pattern : pas) {	
			allPatternsPropertis=(ArrayList<String>) CollectionUtils.union(allPatternsPropertis, pattern.getProperites());
			}
		ArrayList<String> classesPropertiesList= new ArrayList<String>();
		for (RDFClass c : classes) {
			c.getProperties().remove("http://www.w3.org/2000/01/rdf-schema#comment");
			c.getProperties().remove("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
			}
	       for (RDFClass c : classes) {
	        	classesPropertiesList=(ArrayList<String>)CollectionUtils.union(classesPropertiesList, c.getProperties());
			}
	     ArrayList<String> shardProperties=(ArrayList<String>) CollectionUtils.intersection(classesPropertiesList, allPatternsPropertis);
	     ArrayList<String> differnt= (ArrayList<String>) ListUtils.subtract(classesPropertiesList, allPatternsPropertis);
	     double classesPropertiesNumber=classesPropertiesList.size();
	     double shardPropertiesNumber=shardProperties.size();
	     double recall=shardPropertiesNumber/classesPropertiesNumber;
	     return recall;
			}
	
	

}
