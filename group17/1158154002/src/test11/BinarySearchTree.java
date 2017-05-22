package test11;

import test10.BinaryTreeNode;

public class BinarySearchTree<T extends Comparable> {
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		return findMin(root);
	}
	
	public T findMin(BinaryTreeNode<T> node){
		while (node.left!=null) {
			node=node.left;
		}
		return (T) node.data;
	}
	
	public T findMax(){
		BinaryTreeNode<T> temp=root;
		while (temp.right!=null) {
			temp=temp.right;
		}
		return (T) temp.data;
	}
	
	public int height() {
	    return height(root);
	}
	
	public int height(BinaryTreeNode<T> node) {
		int left,right;
		if (node==null) {
			return 0;
		} else {
			left=height(node.left);
			right=height(node.right);
			return (left<right)?right+1:left+1;
		}
	}

	public int size() {
		return size(root);
	}
	
	public int size(BinaryTreeNode<T> node){
		if (node==null) {
			return 0;
		} else {
			int left=size(node.left);
			int right=size(node.right);
			return 1+left+right;  
		}
	}
	
	public void remove(T e){
		remove(e, root);
	}
	
	public BinaryTreeNode<T> remove(T x,BinaryTreeNode<T> t){
		if (t==null) {
			return t;
		}
		int compareResult=x.compareTo(t.data);
		if (compareResult>0) {
			t.right=remove(x, t.right);
		}else if (compareResult<0) {
			t.left=remove(x, t.left);
		}else {
			if (t.left!=null&&t.right!=null) {
				t.data=findMin(t.right);
				t.right=remove(t.data, t.right);
			} else {
				t=(t.left!=null)?t.left:t.right;
			}
		}
		return t;
	}
}
