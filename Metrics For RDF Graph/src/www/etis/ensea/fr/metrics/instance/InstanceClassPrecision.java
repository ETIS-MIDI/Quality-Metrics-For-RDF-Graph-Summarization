package www.etis.ensea.fr.metrics.instance;

import java.util.ArrayList;
import org.apache.commons.collections15.CollectionUtils;
import www.etis.ensea.fr.metrics.common.*;
public class InstanceClassPrecision {
	
	int typeTest(Pattern p, RDFClass c)
	{
		int type=0;
		if(p.getClasses().isEmpty() || p.getClasses()==null){
			if(CollectionUtils.intersection(p.getProperites(), c.getProperties()).size()>3)
				type=1;
		}
		return type;
	}

   int testSameType(Pattern p, RDFClass c){
	   int sim=0;
	   if(p.getClasses().contains(c.getType())){
		   sim=1;
			}
	   return sim;
	}

    ArrayList<String> cover(Pattern pa, RDFClass c){
    	ArrayList<String> p=new ArrayList<String>();
    	int x=testSameType(pa, c);
    	if(x>=1) 
    		return pa.getSubjects();
    		
    	else
    		return p;
    	}
    
    ArrayList<String> coverAll(ArrayList<Pattern> pas, RDFClass c){
    	ArrayList<String> p=new ArrayList<String>();
		for (Pattern pa : pas) {
			ArrayList<String> p1=cover(pa, c);
			if(!p1.isEmpty())
			{ 
				p=(ArrayList<String>) CollectionUtils.union(p, p1); 
			}
			
		}
		return p;
	}
	
	double precision(RDFClass c, ArrayList<Pattern> pas){
		double d,classsize,prec;
		ArrayList<String> p=coverAll(pas, c);
		d=p.size();
		classsize=c.getNbinstances();
		prec=classsize/d;
		if(prec>1) return 1;
		return prec;
	}
	
	public double getInstanceClassPression(ArrayList<Pattern> pas, ArrayList<RDFClass> classes, int totalInstanceNumber ){
		InstanceClassPrecision t=new InstanceClassPrecision();
		double prec=0;
		int totalClassesNumber=0;
		for (RDFClass c : classes) {
			totalClassesNumber=totalClassesNumber+c.getNbinstances();
		}
		for (RDFClass c : classes) {
		double instances=c.getNbinstances();
		double weight=instances/totalClassesNumber+0.0;
		prec=prec+weight*t.precision(c, pas);
        }
	  return prec;
	}
	}


