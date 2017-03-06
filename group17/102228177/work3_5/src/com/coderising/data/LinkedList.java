package com.coderising.data;


import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public LinkedList(){
		size = 0;
		head = null;
	}
	
	public void add(Object o){
		Node node = new Node(o);
		if(head == null){
			head = node;
		}else{
			//p为游标   从头遍历到尾
			Node p = head;
			while(p.next != null){
				p = p.next;
			}
			p.next = node;
		}
		size++;
	}
	
	public void add(int index , Object o){
		//判断不为空链表
		if(head != null){
			Node p = head;
			int k = 0;
			//扫描单链表查找第index-1个节点
			while(k < index-1 && p.next != null){
				k++;
				p = p.next;
			}
			//判断是否找到第index-1个节点
			if(p != null){
				Node node = new Node(o);
				node.next = p.next;
				p.next = node;
			}
			size++;
		}
	}
	
	public Object get(int index){
		if(index <0 || index >= size){
			throw new IndexOutOfBoundsException();
		}else{
			Node p = head;
			int k = 0;
			while(k < index && p.next != null){
				k++;
				p = p.next;
			}
			return p.data;
		}
	}
	public Object remove(int index){
		if(index <0 || index >= size){
			throw new IndexOutOfBoundsException();
		}else{
			if(head != null){
				Node p = head;
				int k = 0;
				while(k > index-1 && p.next != null){
					k++;
					p = p.next; 
				}
				Node next = p.next;
				p.next = next.next;
				size--;
				return next.data;
			}
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}
	
	public void addLast(Object o){
		Node node = new Node(o);
		if(head == null){
			head = node;
		}else{
			Node p = head;
			while(p.next != null){
				p = p.next; 
			}
			p.next = node;
		}
		size++;
	}
	
	public Object removeFirst(){
		if(head == null){
			 throw new NoSuchElementException();
		}
		Node node = head;
		head = node.next;
		size--;
		return node.data;
	}
	public Object removeLast(){
		if(head == null){
			 throw new NoSuchElementException();
		}else{
			Node p = head;
			int k = 0;
			while(k < size-1 && p.next != null){
				k++;
				p = p.next;
			}
			Node last = p.next;
			p.next = null;
			size--;
			return last.data;
		}
	}
	
	private static class Node{
		Object data;
		Node next;
		private Node(Object o){
			this.data = o;
			this.next = null;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}