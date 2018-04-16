package www.etis.ensea.fr.metrics.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PatternsCreator {
	HashMap<Integer, String> allPredicates=new HashMap<Integer, String>();
	public void  createPredicatesHashTable(String predicate_file) throws IOException
	{
		FileReader f=new FileReader(predicate_file);
	    BufferedReader inp=new BufferedReader(f);
	    while(inp.ready())
	    {
		String res=inp.readLine();
		String [] a =res.split(" ");
	    allPredicates.put(Integer.parseInt(a[0]), a[1]);
	    }
	    inp.close();
	    
	}
	public void writePatternSubjects(String filename, String context) throws IOException
	{
		FileWriter f=new FileWriter(filename);
		BufferedWriter bw=new BufferedWriter(f);
		bw.write(context);bw.flush();f.flush();f.close();
		bw.close();;
		
		
	}
	public  ArrayList<Pattern> createPatterns(String pattern_file, String predicate_file) throws IOException 
	{
		createPredicatesHashTable(predicate_file);
		ArrayList<Pattern> patterns=new ArrayList<Pattern>();
		FileReader f1=new FileReader(pattern_file);
		BufferedReader inp1=new BufferedReader(f1);
		int i=1;	
		while(inp1.ready())
		{  
			String pattern=inp1.readLine()+"";
			if(!pattern.isEmpty()){
				//the format of pattern is like this :  1 4 6(5000) 9 8 22 12 45 788 96 
			String [] a=pattern.split("\\(");
			
			a[0].trim();
			String a1[]=a[1].split("\\)");
			String [] patternelements=a[0].split(" ");
			ArrayList<String> pattern_properites =new ArrayList<String>();
			ArrayList<String> pattern_classes =new ArrayList<String>();
			i++;
			for(int j=0;j<patternelements.length;j++){
				if(!patternelements[j].isEmpty()){ 
					String s=allPredicates.get(Integer.parseInt(patternelements[j]));
					if(s.contains("::C"))
					{
						pattern_classes.add(s);
					}
					else{
				    pattern_properites.add(s);
					}
				    }
			}
			Pattern pat=new Pattern(i, pattern_properites, pattern_classes, Integer.parseInt(a1[0]), "patterns/Pattern"+i);
			a1[1].trim();
			writePatternSubjects("patterns/Pattern"+i, a1[1].substring(2, a1[1].length()-1));
			patterns.add(pat);
		}
		}
		inp1.close();
		return patterns;
		}
	public  ArrayList<Pattern> createPatterns1(String pattern_file, String predicate_file) throws IOException 
	{
		createPredicatesHashTable(predicate_file);
		ArrayList<Pattern> patterns=new ArrayList<Pattern>();
		FileReader f1=new FileReader(pattern_file);
		BufferedReader inp1=new BufferedReader(f1);
	
		List<String> lis=Arrays.asList("","");
		ArrayList<String> sub=new ArrayList<String>();
		int i=1;
		int ui=0;
		while(inp1.ready())
		{  
			String pattern=inp1.readLine()+"";
			if(!pattern.isEmpty()){
				//the format of pattern is like this :  1 4 6(5000) 9 8 22 12 45 788 96 
			String [] a=pattern.split("\\(");
			a[0].trim();
			String a1[]=a[1].split("\\)");
		 ArrayList<String> subjs=new ArrayList<String>(Arrays.asList(a1[1].split(" ")));
			String [] patternelements=a[0].split(" ");
			ArrayList<String> pattern_properites =new ArrayList<String>();
			ArrayList<String> pattern_classes =new ArrayList<String>();
			i++;
			 for(int j=0;j<patternelements.length;j++){
				if(!patternelements[j].isEmpty()){ 
				String s=allPredicates.get(Integer.parseInt(patternelements[j]));
					//System.out.println(s);
				//System.out.println("s========="+s);
					if(s.contains("::C"))
					{
						pattern_classes.add(s.split("::C")[0]);
					}
					else if(!s.contains("::R")){
				    pattern_properites.add(s);
					}
				    }
			}
			Pattern pat=new Pattern(i, pattern_properites, pattern_classes, Integer.parseInt(a1[0]), pattern);
			pat.subjects=subjs;
		    patterns.add(pat);	
		}
		}
		//inp1.close();
		return patterns;
		}
	public ArrayList<Pattern> getPatternsWithNoClass(ArrayList<Pattern> pats){
		ArrayList<Pattern> pats1=new ArrayList<Pattern>();
		for (Pattern pattern : pats) {
			if(pattern.getClasses().size()==0)
			{
				pats1.add(pattern);
			}
			
		}
		return pats1;
	}
	public ArrayList<Pattern> getPatternsWithClasses(ArrayList<Pattern> pats){
		ArrayList<Pattern> pats1=new ArrayList<Pattern>();
		for (Pattern pattern : pats) {
			if(pattern.getClasses().size()!=0)
			{
				pats1.add(pattern);
			}
			
		}
		return pats1;
	}
	
}
