package www.etis.ensea.fr.metrics.schema;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.ListUtils;

import www.etis.ensea.fr.metrics.common.*;

public class SchemaClassRecall {
	public double getSchemaclassRecall(ArrayList<Pattern> pas , ArrayList<RDFClass> classes){  
		ArrayList<String> res= new ArrayList<String>();
	    ArrayList<String> res1= new ArrayList<String>();
		for (Pattern pattern : pas) {
			if(pattern.getClasses()!=null){  
				pattern.getProperites().add("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
				}
			res=(ArrayList<String>) CollectionUtils.union(res, pattern.getProperites());
			}
		double totallrecall=0;
		for (RDFClass c : classes) {
			res1=(ArrayList<String>)CollectionUtils.intersection(res, c.getProperties());
			ArrayList<String> differnt= (ArrayList<String>) ListUtils.subtract(c.getProperties(), res);
        	double classrecall=(double)res1.size()/(double)c.getProperties().size();
        	totallrecall+=classrecall;
        	}
		totallrecall=totallrecall/classes.size();
     return totallrecall;
	}
	}
