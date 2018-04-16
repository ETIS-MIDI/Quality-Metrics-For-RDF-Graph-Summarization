package www.etis.ensea.fr.metrics.schema;

import java.util.ArrayList;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.ListUtils;

import www.etis.ensea.fr.metrics.common.Pattern;
import www.etis.ensea.fr.metrics.common.RDFClass;

public class SchemaPropertyPrecision {
	public double getSchemaPropertyRecall(ArrayList<Pattern> pas , ArrayList<RDFClass> classes){
		ArrayList<String> allPatternsPropertis= new ArrayList<String>();
		for (Pattern pattern : pas) {	
			allPatternsPropertis=(ArrayList<String>) CollectionUtils.union(allPatternsPropertis, pattern.getProperites());
			}
		/*for (String string : allPatternsPropertis) {
			System.out.println(string);
			
		}*/
		ArrayList<String> classesPropertiesList= new ArrayList<String>();
	       for (RDFClass c : classes) {
	        	classesPropertiesList=(ArrayList<String>)CollectionUtils.union(classesPropertiesList, c.getProperties());
			}
	       
	    /*   System.out.println("--------------------------");
	       for (String string : classesPropertiesList) {
	    	   System.out.println(string);
			
		}*/
	     // System.err.println("preci"+allPatternsPropertis.size()+"     "+classesPropertiesList.size());
	     ArrayList<String> shardProperties=(ArrayList<String>) CollectionUtils.intersection(classesPropertiesList, allPatternsPropertis);
	     ArrayList<String> differnt= (ArrayList<String>) ListUtils.subtract(classesPropertiesList, allPatternsPropertis);
	     double patternsPropertiesNumber=allPatternsPropertis.size();
	     double shardPropertiesNumber=shardProperties.size();
	     double precision=shardPropertiesNumber/patternsPropertiesNumber;
	     return precision;
			}
}
