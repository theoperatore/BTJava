BSTJava
=======

A generic Binary Search Tree impleneted in Java.

Info
====

This package is a Binary Search Tree with two generic types: keys, and values

**Keys and Values**

Keys may:

* Be any class that has implemented the [Comparable Interface](http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html).

Keys may NOT:

* Be duplicates
* Be null (A NullPointerException will be thrown);

Values may be any class, and can be duplicates with another node already inserted
into the Tree.

**General Implementation**

Import the package BSTJava and then instantiate a new BinaryTree<K,T>:

    BinaryTree<Integer, String> tree = new BinaryTree<Integer, String();

**Methods**

Provided for quick reference:

    void insert(K key, T data) throws DuplicateException;
    void delete(K key);
    T    lookup(K key);


    //prints the values in an in-order fashion (LeftTree, Value, RightTree)
    void toString(PrintStream p); 

    //prints the keys in an in-order fashion
    void toString(PrintStream p, boolean printKeys);

    //returns the total number of nodes in this tree.
    int size();

**Improvements**

* Add methods to return the Height and Depth of the tree.
* Add method to return the Length of the tree.
* Better commenting and organization of BinaryTree class.
