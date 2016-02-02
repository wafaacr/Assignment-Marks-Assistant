package ama.maduwafaa.entity;

/**
 * @author Wafaa Che Rose, Madushani Dilanka
 *
 */
public class FileUpload {
	String name; 
	String directory;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDirectory(String directory){
		this.directory = directory;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDirectory(){
		return directory;
	}
}
