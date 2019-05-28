/**
 * 
 * @author Tyler Bulley
 * ID: 250498520
 * 
 *  Class storing a node in the Binary Search tree.
 *  Nodes contain parent, left and right child as well as a record object.
 *
 */
public class Node {
	
	private Record record;
	private Node left;
	private Node right;
	private Node parent;
	
	public Node() {
		record = null;
		left = null;
		right = null;
		parent = null;
		
	}
	
	public Node(Node parent) {
		
		this.parent = parent;
		this.record = null;
		this.left = null;
		this.right = null;
		
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * Checks whether node is a leaf by checking if it has a value stored
	 * @return boolean of whether node is a leaf
	 */
	public boolean isLeaf() {
		
		return (record == null);
	}
	
	

}
