package BTJava;

import java.io.PrintStream;

public class BinaryTree<K extends Comparable<K>, T> {
	
	private TreeNode<K,T> root;
	private int size;
	
	/**
	 * Constructs an empty BinaryTree with 0 nodes and a null
	 * root node.
	 */
	public BinaryTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Convenience method to print the values of the tree to the
	 * given printstream.
	 * 
	 * @param p - stream to output the values.
	 */
	public void toString(PrintStream p) {
		print(root,p,false);
	}
	
	/**
	 * Method to print the values or keys in the current tree.
	 * 
	 * @param p - stream to output the values/keys.
	 * @param printKeys - true prints the keys, false prints the values.
	 */
	public void toString(PrintStream p, boolean printKeys) {
		print(root,p,printKeys);
	}
	
	/**
	 * Method to insert a node into the tree in the correct position.
	 * 
	 * @param key - the key of the node to add.
	 * @param data - the data for the node to hold.
	 * @throws DuplicateException - thrown in the case that they given key is 
	 * already used as a key for another node in the tree.
	 */
	public void insert(K key, T data) throws DuplicateException {
		root = insert(root,key,data);
		size++;
	}
	
	/**
	 * Get the data from the node with the given key.
	 * 
	 * @param key - the key of the node to search for.
	 * @return - the data from the node with the given key.
	 */
	public T lookup(K key) {
		return lookup(root, key);
	}
	
	/**
	 * Method to delete a node with the given key.
	 * @param key - the given key of the node to delete.
	 */
	public void delete(K key) {
		root = delete(root, key);
		size--;
	}
	
	/**
	 * Helper method to print the values/keys of this tree in an 
	 * in-order fashion.
	 * 
	 * @param n - the current node.
	 * @param p - where to print to.
	 * @param printKeys - true to print keys, false to print values.
	 */
	private void print(TreeNode<K,T> n, PrintStream p, boolean printKeys) {
		if (printKeys) {
			if (n == null) return;
			
			print(n.getLeft(), p, printKeys);
			p.println(n.getKey());
			print(n.getRight(), p, printKeys);
		}
		else {
			if (n == null) return;
			
			print(n.getLeft(), p, printKeys);
			p.println(n.getData());
			print(n.getRight(), p, printKeys);
		}
	}
	
	/**
	 * Helper method to insert a node into the correct space. 
	 * @param n - the current node.
	 * @param key - the key to compare against.
	 * @param data - the data in insert.
	 * @return
	 * @throws DuplicateException - if the given key is already used
	 * in this tree for another node.
	 */
	private TreeNode<K,T> insert(TreeNode<K,T> n, K key, T data) throws DuplicateException {
		if (n == null) {
			return new TreeNode<K,T>(key,data,null,null);
		}
		
		if (n.getKey().equals(key)) {
			throw new DuplicateException();
		}
		
		if (key.compareTo(n.getKey()) < 0) {
			n.setLeft( insert(n.getLeft(), key, data) );
			return n;
		}
		else {
			n.setRight( insert(n.getRight(), key, data) );
			return n;
		}
	}
	
	/**
	 * Helper method to find the node with the given key.
	 * @param n - the current node
	 * @param key - the key to check
	 * @return - the data from the node with the given key
	 */
	private T lookup(TreeNode<K,T> n, K key) {
		if (n == null) return null;
		
		if (n.getKey().equals(key)) {
			return n.getData();
		}
		
		if (key.compareTo(n.getKey()) < 0) {
			return lookup(n.getLeft(), key);
		}
		else {
			return lookup(n.getRight(), key);
		}
	}
	
	/**
	 * Helper method to remove a node from the tree while keeping the 
	 * main structure intact.
	 * 
	 * @param n - the current node
	 * @param key - the key of the current node.
	 * @return
	 */
	private TreeNode<K,T> delete(TreeNode<K,T> n, K key) {
		if (n == null) {
			return null;
		}
		
		//if true, this is the node to delete, handle children.
		if (key.equals(n.getKey())) {
			//if n doesn't have any children, return null
			if (n.getLeft() == null && n.getRight() == null) {
				return null;
			}
			
			//if n doesn't have any left children, but has right...
			if (n.getLeft() == null) {
				return n.getRight();
			}
			
			//if n doesn't have any right children, but has left...
			if (n.getRight() == null) {
				return n.getLeft();
			}
			
			//if n has both right and left children,
			//replace the key value of the node to delete with 
			//an appropriate value to keep both trees intact.
			K val = smallest(n.getRight());
			n.setKey(val);
			n.setRight( delete(n.getRight(), val));
			return n;
		}
		
		else if (key.compareTo(n.getKey()) < 0 ) {
			n.setLeft( delete(n.getLeft(), key));
			return n;
		}
		else {
			n.setRight( delete(n.getRight(), key));
			return n;
		}
	}
	
	/**
	 * Helper method to find the smallest key in the right 
	 * tree of the node to delete.
	 * 
	 * @param n- the current node
	 * @return - the key of the node with the smallest key in the right subtree
	 * of the node to delete.
	 */
	private K smallest(TreeNode<K,T> n) {
		if (n.getLeft() == null) {
			return n.getKey();
		}
		else {
			return smallest(n.getLeft());
		}
	}
	
	/**
	 * Returns the number of nodes in this tree.
	 * @return -- the number of nodes in this tree.
	 */
	public int size() {
		return size;
	}

}
