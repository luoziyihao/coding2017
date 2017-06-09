package assignment0507;


import assignment.Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        BinaryTreeNode<T> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    public T findMax() {
        BinaryTreeNode<T> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    public int height() {
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public int size() {
        return size(root.left) + size(root.right) + 1;
    }

    private int size(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        else {
            return size(node.left) + size(node.right) + 1;
        }
    }

    public void remove(T e) {
        //找到要删除的节点
        BinaryTreeNode<T> target = findNode(e);
        BinaryTreeNode<T> parent = findParent(e);
        //是叶节点
        if (isLeafNode(target)) {
            if (target == root) {
                root = null;
            } else if (target == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //有一个子树将父节点指向此子树
        else if (target.left != null && target.right == null) {
            if (target == root) {
                root = target.left;
            } else if (target == parent.left) {
                parent.left = target.left;
            } else {
                parent.right = target.left;
            }
        } else if (target.right != null && target.left == null) {
            if (target == root) {
                root = target.right;
            } else if (target == parent.left) {
                parent.left = target.right;
            } else {
                parent.right = target.right;
            }
        }
        //有两个子树，找到右子树最小节点作为被删除节点的替换，将父节点指向替换节点，
        // 替换节点的父节点指向替换节点的右节点，替换节点的左右子树为原节点左右子树
        else {
            if (isLeafNode(target.right)) {
                if (target == root) {
                    root = target.right;
                    root.left = target.left;
                }
                if (target == parent.left) {
                    parent.left = target.right;
                    target.right.left = target.left;
                } else {
                    parent.right = target.right;
                    target.right.left = target.left;
                }
            } else {
                BinaryTreeNode<T> replaceNode = target.right;
                BinaryTreeNode<T> parentOfReplaceNode = findParent(replaceNode.data);
                while (replaceNode.left != null) {
                    replaceNode = replaceNode.left;
                }
                if (target == root) {
                    root = replaceNode;
                    parentOfReplaceNode.left = replaceNode.right;
                    root.left = target.left;
                    root.right = target.right;
                } else if (target == parent.left) {
                    parentOfReplaceNode.left = replaceNode.right;
                    parent.left = replaceNode;
                    replaceNode.left = target.left;
                    replaceNode.right = target.right;
                } else {
                    parentOfReplaceNode.left = replaceNode.right;
                    parent.right = replaceNode;
                    replaceNode.left = target.left;
                    replaceNode.right = target.right;
                }
            }
        }

    }

    public List<T> levelVisit() {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        List<T> list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> head = queue.poll();
            list.add(head.data);
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }

        }
        return list;
    }

    public boolean isValid() {
        return isValid(root);
    }

    private boolean isValid(BinaryTreeNode<T> node) {
        if (node == null) {
            return true;
        }
        if (node.left != null && node.data.compareTo(node.left.data) < 0
                || node.right != null && node.data.compareTo(node.right.data) > 0) {
            return false;
        }
        return isValid(node.left) && isValid(node.right);
    }

    public T getLowestCommonAncestor(T n1, T n2) {
        return getLowestCommonAncestor(root, n1, n2);
    }

    private T getLowestCommonAncestor(BinaryTreeNode<T> commonAncestor, T n1, T n2) {
        if (isAncestor(commonAncestor.left, n1) && isAncestor(commonAncestor.left, n2)) {
            return getLowestCommonAncestor(commonAncestor.left, n1, n2);
        }
        if (isAncestor(commonAncestor.right, n1) && isAncestor(commonAncestor.right, n2)) {
            return getLowestCommonAncestor(commonAncestor.right, n1, n2);
        }
        return commonAncestor.data;
    }

    private boolean isAncestor(BinaryTreeNode<T> node, T child) {
        if (node == null) {
            return false;
        }
        if (node.left != null && node.left.data.equals(child) || node.right != null && node.right.data.equals(child)) {
            return true;
        }
        return isAncestor(node.left, child) || isAncestor(node.right, child);
    }

    /**
     * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
     * 该二叉查找树中的某一节点
     *
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2) {
        return BinaryTreeUtil.inOrderVisit(root)
                .stream()
                .filter(n -> n.compareTo(n1) >= 0 && n.compareTo(n2) <= 0)
                .collect(Collectors.toList());
    }

    private boolean isLeafNode(BinaryTreeNode<T> target) {
        return target.left == null && target.right == null;
    }

    private BinaryTreeNode<T> findNode(T e) {
        BinaryTreeNode<T> node = root;
        while (node != null) {
            if (node.data.equals(e))
                return node;
            if (node.data.compareTo(e) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }


    private BinaryTreeNode<T> findParent(T e) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode<T> node = root;
        while (node != null) {
            if (node.data.equals(e))
                return stack.peek();
            if (node.data.compareTo(e) < 0) {
                stack.push(node);
                node = node.right;
            } else {
                stack.push(node);
                node = node.left;
            }
        }
        return null;
    }

}

