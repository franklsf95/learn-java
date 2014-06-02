
/**
 * An object representing a single node in a Twenty Questions game tree.
 * @author Mac Mason <mac@cs.duke.edu>
 */
public class AnimalNode {
	private AnimalNode myYesChild;
	private AnimalNode myNoChild;
	private String myData;
	public AnimalNode(String data, AnimalNode yes, AnimalNode no) {
		myYesChild = yes;
		myNoChild = no;
		myData = data;
	}
	
	public void setMyData(String myData) {
		this.myData = myData;
	}

	public String toString(){
		return myData;
	}
	
	public AnimalNode getYes() { 
		return myYesChild;
	}
	
	public AnimalNode getNo() {
		return myNoChild;
	}
	
	/*
	 * You may need to add setter functions. Go right ahead.
	 */

	public void setMyYesChild(AnimalNode myYesChild) {
		this.myYesChild = myYesChild;
	}
	
	public void setMyNoChild(AnimalNode myNoChild) {
		this.myNoChild = myNoChild;
	}

	
}
