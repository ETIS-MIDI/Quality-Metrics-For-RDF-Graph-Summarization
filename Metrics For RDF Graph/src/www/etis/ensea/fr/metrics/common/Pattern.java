package www.etis.ensea.fr.metrics.common;

import java.io.Serializable;
import java.util.ArrayList;

public class Pattern implements Serializable{
	int id ;
	ArrayList<String> properites;
	ArrayList<String> classes;
	int size;
	String subjects_ref;
	ArrayList<String> subjects;
	
	public ArrayList<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}
	/**
	 * @return the subjects_ref
	 */
	public String getSubjects_ref() {
		return subjects_ref;
	}
	public Pattern(int id, ArrayList<String> properites, ArrayList<String> classes, int size, String subjects_ref) {
	 
		this.id = id;
		this.properites = properites;
		this.classes = classes;
		this.size = size;
		this.subjects_ref = subjects_ref;
	}
	public Pattern() {
		super();
	}
	/**
	 * @param subjects_ref the subjects_ref to set
	 */
	public void setSubjects_ref(String subjects_ref) {
		this.subjects_ref = subjects_ref;
	}
	public ArrayList<String> getClasses() {
		return classes;
	}
	public void setClasses(ArrayList<String> classes) {
		this.classes = classes;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setProperites(ArrayList<String> properites) {
		this.properites = properites;
	}
	public int getId() {
		return id;
	}
	public ArrayList<String> getProperites() {
		return properites;
	}
	
	public void setSize(int size) {
		this.size = size;
}
	public int getSize() {
		return size;
}	
}
