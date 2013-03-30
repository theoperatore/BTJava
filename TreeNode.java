package BTJava;

class TreeNode<K,T> {
	
	private K key;
	private T data;
	private TreeNode<K,T> left;
	private TreeNode<K,T> right;
	
	public TreeNode(K key, T data, TreeNode<K,T> left, TreeNode<K,T> right) {
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public K getKey() { return this.key; }
	public T getData() { return this.data; }
	public TreeNode<K,T> getLeft() { return this.left; }
	public TreeNode<K,T> getRight() { return this.right; }
	
	public void setKey(K nKey) { this.key = nKey; }
	public void setData(T nData) { this.data = nData; }
	public void setLeft(TreeNode<K,T> nLeft) { this.left = nLeft; }
	public void setRight(TreeNode<K,T> nRight) { this.right = nRight; }
	
}
