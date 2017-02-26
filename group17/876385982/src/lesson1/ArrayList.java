package lesson1;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[10];

	public void add(Object o) {
		if (size < elementData.length) {
			elementData[size] = o;
		} else {
			elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2 + 1);
			elementData[size] = o;
		}
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > elementData.length - 1) {
			return;
		} else {
			if (size == elementData.length - 1)
				elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2 + 1);
			System.arraycopy(elementData, index, elementData, index + 1, size - index);
			elementData[index] = o;
		}
		size++;
	}

	public Object remove(int index) {
		Object o = elementData[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[size] = null;
		size--;
		return o;
	}

	public Object get(int index) {
		return elementData[index];
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return null;
	}

}
