package www.etis.ensea.fr.metrics.instance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import www.etis.ensea.fr.metrics.common.*;
import www.etis.fr.schemaetraction.Triplenumber;

import org.apache.commons.collections15.CollectionUtils;



public class InstanceClassRecall {
	
	public double getInstanceClassRecall(ArrayList<Pattern>	pas, String idealSummaryRDFFile ){
		ArrayList<String> res=new ArrayList<String>();
		for (Pattern pattern : pas) {
			res=(ArrayList<String>) CollectionUtils.union(res, pattern.getSubjects());	
		}
		Triplenumber tp=new Triplenumber();
		double patternsClassInstancesNumber=res.size();
		double idealsummaryClassInstancesNumber=tp.getClassInstancessNumber(idealSummaryRDFFile);
		double instanceClassRecallValue=patternsClassInstancesNumber/idealsummaryClassInstancesNumber;
		return instanceClassRecallValue;
	
	}

	

}
