package www.etis.ensea.fr.metrics.common;

import java.io.Serializable;
import java.util.ArrayList;

public class RDFClass implements Serializable{
String type;
ArrayList<String> properties;
int nbinstances;
public int getNbinstances() {
	return nbinstances;
}

public void setNbinstances(int nbinstances) {
	this.nbinstances = nbinstances;
}

public RDFClass(String type, ArrayList<String> properties) {
	super();
	this.type = type;
	this.properties = properties;
}

public RDFClass() {
	// TODO Auto-generated constructor stub
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public ArrayList<String> getProperties() {
	return properties;
}
public void setProperties(ArrayList<String> properties) {
	this.properties = properties;
}


}
