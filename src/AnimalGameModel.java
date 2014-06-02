/*
 * AnimalGameModel.java
 * TODO: YOUR NAME HERE.
 * 
 * Implementation of IAnimalGameModel.
 */
import java.io.*;
import java.util.*;

public class AnimalGameModel implements IAnimalModel {
	
	// In Model-View systems, the Model & View must be able to communicate;
	// this pointer to the view lets you tell the view to do things (show messages,
	// for example).
	private AnimalGameViewer myView;
    private ArrayList<String> path;
    private StringBuilder sb = new StringBuilder(); 
    
    
	
	@Override
	public void addNewKnowledge(String question) {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public void addNewQuestion(String noResponse) {
		// TODO Auto-generated method stub
		
	}

	
	public AnimalNode myTree;
	public AnimalNode myNode;
	@Override
	public void initialize(Scanner s) {
		// TODO Auto-generated method stub
		AnimalNode tree = readHelper(s);
		myTree = tree;
		myView.setEnabled(true);
		newGame();
	}
	private AnimalNode readHelper(Scanner s)
	{
		String line = s.nextLine();
		AnimalNode n = new AnimalNode(line, null, null);
		if (!line.contains("#Q")) {
			return n;
		}
		n.setMyData(line.substring(3));
		AnimalNode l = readHelper(s);
		n.setMyYesChild(l);
		AnimalNode k = readHelper(s);
		n.setMyNoChild(k);
		return n;
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		myNode = myTree;
		myView.update(myNode.toString());
		path = new ArrayList<String>();
	}

	@Override
	public void processYesNo(boolean yes) {
		// TODO Auto-generated method stub
		String record;
		String output = "";
		
		if (myNode.getNo() == null && myNode.getYes() == null){
			if (yes == true){
				myView.showDialog("I win!");
			}
			else{
				myView.showDialog("I lost!");
				for (String s: path) output += s+"\n";
				myView.update(output);
				
			}
			// TODO 
		}
		else{
			if (yes == true){
				myNode = myNode.getYes();
				myView.update(myNode.toString());
				record = "You answered YES to <" + myNode.toString() + ">";
				
			}
			else{
				myNode = myNode.getNo();
				myView.update(myNode.toString());
				record = "You answered NO to <" + myNode.toString() + ">";
				
			}
		path.add(record);
			
		}
		}
	


	@Override
	public void setView(AnimalGameViewer view) {
		myView = view;
	}

	@Override
	public void write(FileWriter writer) {
		// TODO Auto-generated method stub
		//Write one line in the file for each node using the FileWriter write() method
		writeHelper(writer, myTree);
		//Write a preorder traversal
		//Prepend “#Q:” for each question (internal node)
		//Append “\n” to the end of each line to signify new line
		
	}
	private void writeHelper(FileWriter writer, AnimalNode tree){
		String line;
		if (tree == null){
			return;
		}
		if (!(tree.getNo() == null && tree.getYes() == null)){
			line = "#Q:" + tree.toString() + "\n";
			}
		else{
			line = tree.toString() + "\n";
		}
		try {
			writer.write(line);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		writeHelper(writer,tree.getYes());
		writeHelper(writer,tree.getNo());
	}
}
