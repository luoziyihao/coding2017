package lesson1;

public class Stack {
	private int size = 0;
	private ArrayList elementData = new ArrayList();

	public void push(Object o) {
		elementData.add(o);
		size++;
	}

	public Object pop() {
		size--;
		Object o = elementData.get(size);
		elementData.remove(size);
		return o;
	}

	public Object peek() {
		size--;
		Object o = elementData.get(size);
		return o;
	}

	public boolean isEmpty() {
		if(size==0)
			return true;
		return false;
	}

	public int size() {
		return size;
	}
}
