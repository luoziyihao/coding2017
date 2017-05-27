package test11;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import test10.BinaryTreeNode;

public class BinarySearchTree<T extends Comparable> {
	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		return findMin(root);
	}

	public T findMin(BinaryTreeNode<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return (T) node.data;
	}

	public T findMax() {
		BinaryTreeNode<T> temp = root;
		while (temp.right != null) {
			temp = temp.right;
		}
		return (T) temp.data;
	}

	public int height() {
		return height(root);
	}

	public int height(BinaryTreeNode<T> node) {
		int left, right;
		if (node == null) {
			return 0;
		} else {
			left = height(node.left);
			right = height(node.right);
			return (left < right) ? right + 1 : left + 1;
		}
	}

	public int size() {
		return size(root);
	}

	public int size(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			int left = size(node.left);
			int right = size(node.right);
			return 1 + left + right;
		}
	}

	public void remove(T e) {
		remove(e, root);
	}

	public BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if (t == null) {
			return t;
		}
		int compareResult = x.compareTo(t.data);
		if (compareResult > 0) {
			System.out.println("compareResult>0");
			t.right = remove(x, t.right);
			System.out.println("right:" + t.data);
		} else if (compareResult < 0) {
			System.out.println("compareResult<0");
			t.left = remove(x, t.left);
			System.out.println("left:" + t.data);
		} else {
			if (t.left != null && t.right != null) {
				t.data = findMin(t.right);
				t.right = remove(t.data, t.right);
			} else {
				t = (t.left != null) ? t.left : t.right;
			}
		}
		return t;
	}

	public List<T> levelVisit() {
		List<T> list=new ArrayList<>();
		Stack<BinaryTreeNode> stack=new Stack<>();
		stack.add(root);
		list.add(root.data);
		
		while (stack.size()>0) {
			BinaryTreeNode<T> node=stack.pop();
			if (node.left!=null) {
				list.add(node.left.data);
				stack.push(node.left);
			}
			if (node.right!=null) {
				list.add(node.right.data);
				stack.push(node.right);
			}
		}
		
		return list;
	}

	public boolean isValid() {
		Stack<BinaryTreeNode> stack=new Stack<>();
		stack.add(root);
		
		while (stack.size()>0) {
			BinaryTreeNode<T> node=stack.pop();
			if (node.left!=null) {
				stack.push(node.left);
				if (node.data.compareTo(node.left.data)<0) {
					return false;
				}
			}
			if (node.right!=null) {
				stack.push(node.right);
				if (node.data.compareTo(node.right.data)>0) {
					return false;
				}
			}
		}
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		List<T> list1=find(n1,root);
		List<T> list2=find(n2,root);
		T  result=null;
		if (list1.size()>list2.size()) {
			for (int i = 0; i < list2.size(); i++) {
				if (list1.get(i)==list2.get(i)) {
					result=list1.get(i);
				}
			}
		} else {
			for (int i = 0; i < list1.size(); i++) {
				if (list2.get(i)==list1.get(i)) {
					result=list1.get(i);
				}
			}
		}
		return result;
	}

	public List<T> find(T n,BinaryTreeNode<T> node){
		List<T> list=new ArrayList<>();
		while (true) {
			if (n.compareTo(node.data)<0) {
				list.add(node.data);
				node=node.left;
			}else if(n.compareTo(node.data)>0){
				list.add(node.data);
				node=node.right;
			}else{
				break;
			}
		}
		return list;
	}
	
	public BinaryTreeNode<T> find(T n){
		BinaryTreeNode<T> node=root;
		while (true) {
			if (n.compareTo(node.data)<0) {
				node=node.left;
			}else if(n.compareTo(node.data)>0){
				node=node.right;
			}else{
				return node;
			}
		}
	}
	
	public List<T> getNodesBetween(T n1, T n2) {
		T com=getLowestCommonAncestor(n1,n2);
		BinaryTreeNode<T> node=find(com);
		List<T> list1=find(n1, node);
		List<T> list2=find(n2, node);
		list1.remove(0);
		list1.addAll(list2);
		return list1;
	}
}
