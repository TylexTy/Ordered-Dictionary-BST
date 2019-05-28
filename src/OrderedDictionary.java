/**
 * 
 * @author Tyler Bulley
 * ID: 250498520
 * 
 * Dictionary class that uses a binary search tree to order it's entries.
 *
 */

public class OrderedDictionary implements OrderedDictionaryADT {

	Node root;
	
	public OrderedDictionary() {
		
		root = new Node();
	}
	
	public Record get(Pair k) {
		
		Node cur = root; // cur is the current node.
		Node toGet = getHelper(k, cur);
		return toGet.getRecord();
		
		
	}
	
	/**
	 * A helper class for the get method
	 * @param k a pair(word,type)
	 * @param cur a node
	 * @return node which contains k
	 */
	
	private Node getHelper(Pair k, Node cur) {
		
		if (cur.isLeaf()) {
			
			return cur;
		}
		else {
			int comp = k.compareTo(cur.getRecord().getKey());
			
			if (comp == 0) {  // found k
				return cur;
			}
			else if (comp < 0) { // k is possibly to the left
				return getHelper(k, cur.getLeft());
			}
			else if (comp > 0)  // k is possibly to the right
				return getHelper(k, cur.getRight());
		}
		return null;
	}
	/**
	 * Takes in a record r and puts that record into the dictionary
	 */
	public void put (Record r) throws DictionaryException {
		
		Node cur = getHelper(r.getKey(), root);
		
		if (!cur.isLeaf()  ) {
			throw new DictionaryException("Record is already in Dictionary");
		}
		else {
			cur.setRecord(r);
			Node newLeft = new Node(cur);
			Node newRight = new Node(cur);
			cur.setLeft(newLeft);
			cur.setRight(newRight);
		}
	}
	
	/**
	 *  Takes a pair k in and removes that pair from the dictionary
	 */
	
	public void remove(Pair k) throws DictionaryException {
			
		
		Node cur = root;
		Node otherChild;
		Node toRemoveP;
		Node toRemove = getHelper(k, cur);
		if (toRemove.isLeaf()) {
			throw new DictionaryException("Pair was not in the Dictionary");
		}
		else {
			
				if (toRemove.getLeft().isLeaf() || toRemove.getRight().isLeaf()) { // if one of it's child's is a leaf
					
					toRemoveP = toRemove.getParent();
					if (toRemove.getLeft().isLeaf()) {
						otherChild = toRemove.getRight();
					}
					else {
						otherChild = toRemove.getLeft();
					}
					if (toRemove == root) {
						root = otherChild;
					}
					else {
						otherChild.setParent(toRemoveP);
						if (toRemove == toRemoveP.getLeft()) {
							toRemoveP.setLeft(otherChild);
						}
						else {
							toRemoveP.setRight(otherChild);
						}
						
					}
					toRemove = new Node(); // removes node for garbage collecting
				}
				else {
					
					Node curSmall = toRemove;
					while (!curSmall.isLeaf()) {
						curSmall = curSmall.getLeft();
					}
					toRemove.setRecord(curSmall.getRecord());
					curSmall = new Node();
					
				}
		}
		
	}

	/**
	 *  Finds and returns the smallest record in the binary search tree
	 */
	
	public Record smallest(){
		
		Node cur = root;
		
		if (cur.isLeaf()) {
			return null;
		}
		else {
			while (!cur.isLeaf()) {
				cur = cur.getLeft();
			}
		}
		return cur.getParent().getRecord();
	}
	
	/**
	 * Finds and returns the largest record in the tree
	 */
	public Record largest() {
		
		Node cur = root;
		
		if (cur.isLeaf())
				return null;
		else {
			while (!cur.isLeaf()) {
				cur = cur.getRight();
			}
		}
		return cur.getParent().getRecord();
	}
	
	/**
	 *  Takes in a pair k and returns record which represents the next record in the tree
	 */
	
	public Record successor(Pair k) {
		
		if (root.isLeaf()) {
			return null;
		}
		else {
			Node cur = getHelper(k, root);
			if (!cur.isLeaf() && !cur.getRight().isLeaf()) {
				Node curRight = cur.getRight();
				while (!curRight.isLeaf()) {
						curRight = curRight.getLeft();
				}
				return curRight.getParent().getRecord();
			}
			else {
					Node curP = cur.getParent();
					while (cur != root && cur == curP.getRight()) {
							cur = curP;
							curP = cur.getParent();
					}
					if (cur == root) return null;
					else return curP.getRecord();
				}
		}
			
	}
	
	/**
	 * Takes in a pair k and returns record of the previous record in the tree.
	 */
	
	public Record predecessor(Pair k) {
		if (root.isLeaf()) {
			return null;
		}
		else {
			Node cur = getHelper(k, root);
			if (!cur.isLeaf() && !cur.getLeft().isLeaf()) {
				Node curLeft = cur.getLeft();
				while (!curLeft.isLeaf()) {
						curLeft = curLeft.getRight();
				}
				return curLeft.getParent().getRecord();
			}
			else {
					Node curP = cur.getParent();
					while (cur != root && cur == curP.getLeft()) {
							cur = curP;
							curP = cur.getParent();
					}
					if (cur == root) return null;
					else return curP.getRecord();
				}
		}
	}
		
}
