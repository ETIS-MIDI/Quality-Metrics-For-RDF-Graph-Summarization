package www.etis.ensea.fr.metrics.instance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import org.apache.commons.collections15.CollectionUtils;
import www.etis.ensea.fr.metrics.common.*;

public class InstancePropertyPrecision {
	
	double cover(String pr, Pattern pa){
		ArrayList<String> p=new ArrayList<String>();
		if(pa.getProperites().contains(pr))
			return pa.getSize();
		else
			return 0;
		}
	
	double coverAllPatterns(String pr, ArrayList<Pattern>  pas){
		ArrayList<String> p=new ArrayList<String>();double s=0;
		for (Pattern pa : pas) 
			s=s+cover(pr, pa);
		return s;
		}
	
	double precision(String c, double nb, ArrayList<Pattern> pas){
     double d,prec;
	 double s=coverAllPatterns(c, pas);
	 prec=nb/s;
	 if(prec>1) // for the overlapping 
		 return 1;
	 return prec;
	}
	
	public double getInstancePropertyPrecision( ArrayList<Pattern> pas, ArrayList<String> properties){
		double totalPrec=0;
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
			 double propertyInstanceNumber=Double.parseDouble(a[0]);
			 double weight=propertyInstanceNumber/totalPropertyInstances;
			 if(!a[1].equals("http://www.w3.org/2000/01/rdf-schema#label")){
				 double propertyPrec=precision(a[1],propertyInstanceNumber, pas);
				 totalPrec=totalPrec+weight*propertyPrec;
		        }
			 }
	  return totalPrec;
	}
}

