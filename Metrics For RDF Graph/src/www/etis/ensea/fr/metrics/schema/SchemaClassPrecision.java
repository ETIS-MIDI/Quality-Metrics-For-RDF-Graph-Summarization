package www.etis.ensea.fr.metrics.schema;
import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import org.apache.commons.collections15.CollectionUtils;
import www.etis.ensea.fr.metrics.common.*;

public class SchemaClassPrecision {
ArrayList<Object> unionArrayList(ArrayList<Object> a, ArrayList<Object> b)
{   
	return b;
	
}
// this Method test if no typeof information exists in the pattern pa  
int typeTest(Pattern p){    
	int type=0;
	if(p.getClasses().isEmpty() || p.getClasses()==null)
	{
		type=1;
	}
	return type;
}

int testSameType(Pattern p, RDFClass c){
	int sim=0;
	if(p.getClasses().contains(c.getType()))
	{
		sim=1;
	}
	
	return sim;
}
/* If a knowledge pattern of a summary carries a typeof link then
this pattern is relevant to a specific class if the typeof
points to this class, if not this is not relevant to this
class. If no typeof information exists then we use the
available properties and attributes to evaluate the similarity
between a class and a pattern Thus we define
the L(c, pa) function to capture this information and
we add this to the similarity function*/

int  L(RDFClass c, Pattern p){
	int i=0;
	if(p.getProperites().isEmpty() && p.getClasses().contains(c.getType()))
		return 1;
	ArrayList<String> commonproperties=(ArrayList<String>) CollectionUtils.intersection(p.getProperites(), c.getProperties());
	double size=commonproperties.size();
	if(size>0)
	{
		int l=testSameType(p, c);
	    int t=typeTest(p);
		i=(t+l);
	}
	return i;
}


double  similarity(Pattern p, RDFClass c){
	double sim=0;
    if(p.getProperites().isEmpty() && p.getClasses().contains(c.getType()))
    	return 1;
    else{
    	int l=testSameType(p, c);
	    int t=typeTest(p);
	    int pattersize=p.getProperites().size();
	   ArrayList<String> commonproperties=(ArrayList<String>) CollectionUtils.intersection(p.getProperites(), c.getProperties());
       double size=commonproperties.size();
	   if(size>0){
		   sim=(l+t)*(size/pattersize);
		   }
	  return sim;
	  }
    }

double avg(RDFClass c, ArrayList<Pattern> pas){    
	double sum=0;
    double j=0;
	for(Pattern p: pas){
		if(L(c, p)>0){ 
			if(p.getProperites().isEmpty() && p.getClasses().contains(c.getType()))
			{sum=sum+1;j++;}
			else{
				ArrayList<String> intersectionlist=(ArrayList<String>) CollectionUtils.intersection(p.getProperites(), c.getProperties());
				sum=sum+intersectionlist.size();
				j++;
				}
			}
}
	return sum/j;
}

double  weaghit(RDFClass c, double d, ArrayList<Pattern> pas)
{
	double x=NumberOfPatternsRepresntClass(c, pas);
	double z=Math.exp(1-Math.pow(x,1/d));
	double y=1-z;
	double w=1-y;
    return w;
}
double getMax(ArrayList<Pattern> pas, ArrayList<RDFClass> classes)
{  
	
	double max =NumberOfPatternsRepresntClass(classes.get(0), pas);
	 
	double count=0;
	for (RDFClass rdfClass : classes) {
		count++;
		double s=NumberOfPatternsRepresntClass(rdfClass,pas);
		if(s>max) {max=s;}
		
	}
	return max;
}
double NumberOfPatternsRepresntClass(RDFClass c, ArrayList<Pattern> pas)
{   double sum=0;
	for(Pattern p: pas)
	{ 
		sum+=L(c, p);
	}
	return sum;
}
double totalSimilarity(RDFClass c, ArrayList<Pattern> pas, double a){
	double sum1=0;
    double sum2=0;
    int k=0;
	for(Pattern p: pas)
	{
		double sim=similarity(p, c);
		sum1+=sim;
		//if(sim>0) System.out.println(c.getType()+ c.getProperties()+"  "+"sim="+sim+"  "+p.getClasses()+"   id=  "+p.getId()+" "+p.getProperites()+"\n");
        sum2+=L(c, p);
        }
	if(sum2!=0)
		return weaghit(c,a, pas)*(sum1/sum2);
	else
		return 0;
}

double getClassPrecision(ArrayList<Pattern> pas, ArrayList<RDFClass> classes,double a, double max)
{
	double dd = 0;
	double totalnumberofclasses=0;
	for( RDFClass c : classes){
		double ss=totalSimilarity(c, pas, a);
		dd=dd+ss;
		if(NumberOfPatternsRepresntClass(c, pas)>0){
			 totalnumberofclasses++;
		 }
	}
	double s=classes.size();
	double precision=dd/totalnumberofclasses;
	return precision;
}
public double getSchemaClassPrecision(ArrayList<Pattern> pas , ArrayList<RDFClass> classes, double a){
	for (Pattern pat : pas) {
		pat.getProperites().remove("http://www.w3.org/2002/07/owl#sameAs");
        pat.getProperites().remove("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        pat.getProperites().remove("http://www.w3.org/2000/01/rdf-schema#label");
        }

    for (RDFClass cl : classes) {
    	cl.getProperties().remove("http://www.w3.org/2002/07/owl#sameAs");
    	cl.getProperties().remove("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
    	cl.getProperties().remove("http://www.w3.org/2000/01/rdf-schema#label"); 
    	}
    double dd=0;
	double max=getMax(pas, classes);
	ArrayList<RDFClass> classes1=new ArrayList<RDFClass>();
	return  getClassPrecision(pas, classes,a,max);
	}
}
