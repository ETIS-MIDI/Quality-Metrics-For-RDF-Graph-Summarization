package www.etis.ensea.fr.metrics.schema;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import www.etis.ensea.fr.metrics.common.Pattern;
import www.etis.ensea.fr.metrics.common.PatternsCreator;
import www.etis.ensea.fr.metrics.common.RDFClass;
import www.etis.ensea.fr.metrics.common.TableBuilder;
import www.etis.ensea.fr.metrics.instance.InstanceClassPrecision;
import www.etis.ensea.fr.metrics.instance.InstanceClassRecall;
import www.etis.ensea.fr.metrics.instance.InstancePropertyPrecision;
import www.etis.ensea.fr.metrics.instance.InstancePropertyRecall;
import www.etis.fr.schemaetraction.PropertyInstanceExtraction;
import www.etis.fr.schemaetraction.SchemaExtractionBySPARQLQuery;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PatternsCreator p= new PatternsCreator();
		SchemaClassRecall sehemaCR=new SchemaClassRecall();
		SchemaClassPrecision schemaCP=new SchemaClassPrecision();
		SchemaPropertyRecall schemaPR=new SchemaPropertyRecall();
		SchemaPropertyPrecision schemaPP=new SchemaPropertyPrecision();
		InstanceClassPrecision instanceCP=new InstanceClassPrecision();
		InstanceClassRecall instanceCR=new InstanceClassRecall();
		InstancePropertyPrecision instancePP=new InstancePropertyPrecision();
		InstancePropertyRecall instancePR=new InstancePropertyRecall();
		ArrayList<Pattern>	pas=p.createPatterns1(args[0],args[1]);
		SchemaExtractionBySPARQLQuery q=new SchemaExtractionBySPARQLQuery();
		ArrayList<RDFClass> classes=q.extractSchemaInformation(args[2]);
		PropertyInstanceExtraction prex=new PropertyInstanceExtraction();
		ArrayList<String> properties=prex.extract(args[2]);
		System.out.println();
		System.out.println();
		System.out.println();
		
		//Schema Metrics Values
		double scpv=schemaCP.getSchemaClassPrecision(pas, classes, 3.0);
		double scrv= sehemaCR.getSchemaclassRecall(pas,classes);
		double schemaCF1=2*((scpv*scrv)/(scpv+scrv));
		double sprv=schemaPR.getSchemaPropertyRecall(pas, classes);
		double sppv=schemaPP.getSchemaPropertyRecall(pas, classes);
		double schemaPF1=2*((sppv*sprv)/(sppv+sprv));
		double schemaF1=(schemaCF1+schemaPF1)/2;
		TableBuilder tb = new TableBuilder();
		tb.addRow("-------------------- ", "-------------------- ", "-------------------- ", "--------------------", "------------------- ", "-------------------- ","-------------------- ");
		tb.addRow("SchemaClaasPrecision   ", "SchemaClassRecall   ", "SchemaClassF1   ",  "SchemaPropertyPrecision   ",  " SchemaPropertyRecall   " ,"SchemaPropertyF1    " , "OverallSchemaF1    ");
		tb.addRow("-------------------- ", "-------------------- ", "-------------------- ", "-------------------- ", "------------------- ", "-------------------- ","-------------------- ");
		//tb.addRow("--------------------", "--------------------", "--------------------", "--------------------", "-------------------", "--------------------","--------------------");
		tb.addRow(new DecimalFormat("##.###").format(scpv),new DecimalFormat("##.###").format(scrv),new DecimalFormat("##.###").format(schemaCF1), new DecimalFormat("##.###").format(sppv), new DecimalFormat("##.###").format(sprv),new DecimalFormat("##.###").format(schemaPF1),new DecimalFormat("##.###").format(schemaF1));
		tb.addRow("-------------------- ", "-------------------- ", "-------------------- ", "-------------------- ", "------------------- ", "-------------------- ","-------------------- ");
		System.out.println(tb.toString());
		
		//Instance Metrics Values
		
		double icpv=instanceCP.getInstanceClassPression(pas, classes, 335925);
		double icrv= instanceCR.getInstanceClassRecall(pas, "jamendo.rdf");
		double instanceCF1=2*((icpv*icrv)/(icpv+icrv));
		double iprv=instancePR.getInstancePropertyRecall(pas, properties);
		double ippv=instancePP.getInstancePropertyPrecision(pas, properties);
		double instancePF1=2*((sppv*sprv)/(sppv+sprv));
		double instanceF1=(schemaCF1+schemaPF1)/2;
		System.out.println();
		System.out.println();
		System.out.println();
		
		TableBuilder tb1 = new TableBuilder();
		tb1.addRow("-------------------- ", "-------------------- ", "-------------------- ", "--------------------", "------------------- ", "-------------------- ","-------------------- ");
		tb1.addRow("InstanceClaasPrecision   ", "InstanceClassRecall   ", "InsatnceClassF1   ",  "InstancePropertyPrecision   ",  " InstancePropertyRecall   " ,"InstancePropertyF1    " , "OverallInstanceF1    ");
		tb1.addRow("-------------------- ", "-------------------- ", "-------------------- ", "-------------------- ", "------------------- ", "-------------------- ","-------------------- ");
		//tb.addRow("--------------------", "--------------------", "--------------------", "--------------------", "-------------------", "--------------------","--------------------");
		tb1.addRow(new DecimalFormat("##.###").format(icpv),new DecimalFormat("##.###").format(icrv),new DecimalFormat("##.###").format(instanceCF1), new DecimalFormat("##.###").format(ippv), new DecimalFormat("##.###").format(iprv),new DecimalFormat("##.###").format(instancePF1),new DecimalFormat("##.###").format(instanceF1));
		tb1.addRow("-------------------- ", "-------------------- ", "-------------------- ", "-------------------- ", "------------------- ", "-------------------- ","-------------------- ");
		System.out.println(tb1.toString());
		
		
		//System.out.println(instanceCR.getInstanceClassRecall(pas, "jemendo.rdf") +"   "+instancCP.getInstanceClassPression(pas, classes, 335925));
	}

}
