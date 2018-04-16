package www.etis.ensea.fr.metrics.instance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.commons.collections15.CollectionUtils;

import www.etis.ensea.fr.metrics.common.*;

public class InstancePropertyRecall {
	ArrayList<String> cover(String property, Pattern pa){
		ArrayList<String> p=new ArrayList<String>();
		if(pa.getProperites().contains(property))
			return pa.getSubjects();
		else
			return p;
		}

	ArrayList<String> getListofAllretrivedPropertyInstances(String property, ArrayList<Pattern>  pas){
		ArrayList<String> listofAllretrivedPropertyInstances=new ArrayList<String>();
		for (Pattern pa : pas) {
			listofAllretrivedPropertyInstances=(ArrayList<String>) CollectionUtils.union(listofAllretrivedPropertyInstances, cover(property, pa)); 
			}
		return listofAllretrivedPropertyInstances;
		
	}
	
	double getPropertRecall(String property, double propertyInstancesNumber, ArrayList<Pattern> pas){
		double d,recall;
		ArrayList<String> listofRetrivedInstances=getListofAllretrivedPropertyInstances(property, pas);
		d=listofRetrivedInstances.size();
		recall=d/propertyInstancesNumber;
		if(recall>1) return 1; //for the overlapping 
		return recall;
	}
	
	public double getInstancePropertyRecall( ArrayList<Pattern> pas, ArrayList<String> properties){
		double totalRecall=0;
		double totalPropertyInstances=0;
		 for (String pr : properties) {
		    	String a []=pr.split(";");
		    	if(!a[1].equals("http://www.w3.org/2000/01/rdf-schema#label")){
		    		double propertyInstanceNumber=Double.parseDouble(a[0]);
				    totalPropertyInstances+=propertyInstanceNumber;
				    }
		 } 
		 for (String pr : properties) {
			 String a []=pr.split(";");
			 double propertyInstancesNumber=Double.parseDouble(a[0]);
			 double weight=propertyInstancesNumber/totalPropertyInstances;
			 if(!a[1].equals("http://www.w3.org/2000/01/rdf-schema#label")){
				 double propertyPrec=getPropertRecall(a[1],propertyInstancesNumber, pas);
				 totalRecall=totalRecall+weight*propertyPrec;
		        }
			 }
	  return totalRecall;
	}

}
