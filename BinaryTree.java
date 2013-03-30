package BSTJava;

import java.io.PrintStream;

public class BinaryTree<K extends Comparable<K>, T> {
	
	private TreeNode<K,T> root;
	private int size;
	
	public BinaryTree() {
		root = null;
		size = 0;
	}
	
	public void toString(PrintStream p) {
		print(root,p,false);
	}
	
	public void toString(PrintStream p, boolean printKeys) {
		print(root,p,printKeys);
	}
	
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
	
	public void insert(K key, T data) throws DuplicateException {
		root = insert(root,key,data);
		size++;
	}
	
	public T lookup(K key) {
		return lookup(root, key);
	}
	
	public void delete(K key) {
		root = delete(root, key);
		size--;
	}
	
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
