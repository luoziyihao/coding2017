package tree;

/**
 * Created by gongxun on 2017/5/8.
 */
public class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> insert(Object o) {
        return null;
    }

    @Override
    public String toString() {
        return "data : " + data + ", left data : " + (left == null ? "null" : left.getData()) + ", right data : " + (right == null ? "null" : right.getData());
    }
}
