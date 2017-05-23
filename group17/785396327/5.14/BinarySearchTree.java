import queue.Queue;
import tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongxun on 2017/5/15.
 */
public class BinarySearchTree<T extends Comparable> {
    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        return getExtremeRecursive(root, false);
    }

    public T findMax() {
        return getExtremeRecursive(root, true);
    }

    /**
     * 递归获取极值对应的节点
     *
     * @param node  当前递归遍历到的节点
     * @param isMax 是否取最大值     true    最大值    false   最小值
     * @return
     */
    private BinaryTreeNode<T> getExtremeNodeRecursive(BinaryTreeNode<T> node, boolean isMax) {
        BinaryTreeNode<T> next = isMax == true ? node.getRight() : node.getLeft();
        if (next == null)
            return node;
        return getExtremeNodeRecursive(next, isMax);
    }

    /**
     * 递归获取极值
     *
     * @param node  当前递归遍历到的节点
     * @param isMax 是否取最大值     true    最大值    false   最小值
     * @return
     */
    private T getExtremeRecursive(BinaryTreeNode<T> node, boolean isMax) {
        BinaryTreeNode<T> next = isMax == true ? node.getRight() : node.getLeft();
        if (next == null)
            return node.getData();
        return getExtremeRecursive(next, isMax);
    }

    /**
     * 非递归获得极值
     *
     * @param isMax
     * @return
     */
    private T getExtreme(boolean isMax) {
        T extreme = root.getData();
        Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            extreme = node.getData();
            BinaryTreeNode<T> next = (isMax == true) ? node.getRight() : node.getLeft();
            if (next != null) {
                queue.offer(next);
            }
        }
        return extreme;
    }

    /**
     * 获得二叉树的深度
     *
     * @return
     */
    public int height() {
        return maxHeight(root);
    }

    /**
     * 递归获取二叉树的深度（高度）
     *
     * @param node
     * @return
     */
    private int maxHeight(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        int leftHeight = maxHeight(node.getLeft()) + 1;
        int rightHeight = maxHeight(node.getRight()) + 1;
        return leftHeight > rightHeight ? leftHeight : rightHeight;
    }

    /**
     * 获取二叉树节点个数
     *
     * @return
     */
    public int size() {
        return getSizeUseRecursion(root);
    }

    /**
     * 递归获得二叉搜索树节点个数
     *
     * @param node
     * @return
     */
    private int getSizeUseRecursion(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        else
            return getSizeUseRecursion(node.getLeft()) + getSizeUseRecursion(node.getRight()) + 1;
    }

    /**
     * 非递归实现获得二叉搜索树节点个数
     *
     * @return
     */
    private int getSizeNotUseRecursion() {
        if (root != null) {
            int size = 1;
            Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                BinaryTreeNode<T> node = queue.poll();
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                    size++;
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                    size++;
                }
            }
            return size;
        }
        return -1;
    }

    /**
     * 递归实现删除节点
     *
     * @param e
     */
    public void removeUseRecursion(T e) {
        remove(e, root);
    }

    private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> node) {
        if (node == null)
            return node;
        int compareResult = e.compareTo(node.getData());
        /**
         * 在没有找到待删除节点时，一直递归查找，并将下一次查找的结果设置到当前节点中
         */
        if (compareResult > 0)
            node.setRight(remove(e, node.getRight()));
        else if (compareResult < 0)
            node.setLeft(remove(e, node.getLeft()));
        else {
            //实际上执行到最后最先执行的是else里面的代码，表示找到了待删除节点
            if (node.getLeft() != null && node.getRight() != null) {
                /**
                 * 待删除节点左右子树都存在
                 */
                //默认用待删除节点的右子树的最小值节点代替被删除节点
                //因为此时node表示要删除的节点，因此要将替换节点的数据设置到要被删除的节点中
                node.setData(new BinarySearchTree<T>(node.getRight()).findMin());
                //上面一步只是替换了删除节点，还需要递归到替换的那个节点，将其作为要删除节点
                node.setRight(remove(e, node.getRight()));
            } else {
                //统一处理左子节点和右子节点只存在一个或者都不存在的情况
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            }
        }
        return node;
    }

    /**
     * 非递归实现删除节点
     *
     * @param e
     */
    public void removeNoUseRecursion(T e) {
        BinaryTreeNode<T> parent = findParent(root, e);
        if (parent == null)
            throw new RuntimeException("cannot remove non-exist node");

        BinaryTreeNode<T> removeNode;
        boolean isLeft = true;//待删除的节点是否是parent的左节点
        BinaryTreeNode<T> left = parent.getLeft();
        BinaryTreeNode<T> right = parent.getRight();
        if (left != null && left.getData().compareTo(e) == 0)
            removeNode = left;
        else {
            removeNode = right;
            isLeft = false;
        }
        //删除叶子节点
        if (removeNode.getLeft() == null && removeNode.getRight() == null) {
            if (isLeft)
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
        //删除只存在右子节点的根节点
        else if (removeNode.getLeft() == null && removeNode.getRight() != null) {
            if (isLeft)
                parent.setLeft(removeNode.getRight());
            else
                parent.setRight(removeNode.getRight());
        }
        //删除只存在左子节点的根节点
        else if (removeNode.getRight() == null && removeNode.getLeft() != null) {
            if (isLeft)
                parent.setLeft(removeNode.getLeft());
            else
                parent.setRight(removeNode.getLeft());
        }
        //删除根节点
        else {
            if (isLeft) {
                //删除的是左节点，找所有右子节点中最小的
                BinaryTreeNode<T> minNode = getExtremeNodeRecursive(removeNode.getRight(), false);
                //以最小的右子节点为根，构造新的右子树
                minNode = createSubTree(minNode, removeNode.getRight());
                //新子树设置原本的左子树
                minNode.setLeft(parent.getLeft());
                //父节点的左子树指向新子树
                parent.setLeft(minNode);
            } else {
                BinaryTreeNode<T> maxNode = getExtremeNodeRecursive(removeNode.getLeft(), true);
                maxNode = createSubTree(maxNode, removeNode.getLeft());
                maxNode.setRight(parent.getRight());
                parent.setRight(maxNode);
            }
        }
    }

    /**
     * 用新节点作为根节点重新构造二叉搜索树
     *
     * @param root
     * @param oldRoot
     */
    private BinaryTreeNode<T> createSubTree(BinaryTreeNode<T> root, BinaryTreeNode<T> oldRoot) {
        if (root == null)
            throw new RuntimeException("cannot create a new binary search tree by new root");
        Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
        queue.offer(oldRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            if (root.getData().compareTo(node.getData()) > 0)
                root.setLeft(node);
            if (root.getData().compareTo(node.getData()) < 0)
                root.setRight(node);
        }
        return root;
    }

    /**
     * 返回和targetData值相等的node节点所属的父节点
     *
     * @param node
     * @param targetData
     * @return
     */
    public BinaryTreeNode<T> findParent(BinaryTreeNode<T> node, T targetData) {
        if (node != null) {
            BinaryTreeNode<T> left = node.getLeft();
            BinaryTreeNode<T> right = node.getRight();
            if (targetData.compareTo(node.getData()) > 0) {
                if (right != null && right.getData().equals(targetData))
                    return node;
                return findParent(right, targetData);
            } else if (targetData.compareTo(node.getData()) < 0) {
                if (left != null && left.getData().equals(targetData))
                    return node;
                return findParent(left, targetData);
            }
        }
        return null;
    }


    /**
     * 层级遍历
     *
     * @return
     */
    public List<Integer> levelVisit() {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<Integer>();
        Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> node = (BinaryTreeNode<Integer>) queue.poll();
            result.add(node.getData());
            if (node.getLeft() != null)
                queue.offer(node.getLeft());
            if (node.getRight() != null)
                queue.offer(node.getRight());

        }
        return result;
    }

    /**
     * 判断一个二叉树是不是二叉查找树
     *
     * @return
     */
    public boolean isValid() {
        return isBST(root);
    }

    private boolean isBST(BinaryTreeNode<T> node) {
        if (node == null)
            return true;
        BinaryTreeNode<T> left = node.getLeft();
        BinaryTreeNode<T> right = node.getRight();
        boolean leftIsBST = true;
        boolean rightIsBST = true;
        if (left != null) {
            if (left.getData().compareTo(node.getData()) >= 0)
                leftIsBST = false;
            else
                leftIsBST = isBST(left);
        }
        if (right != null) {
            if (right.getData().compareTo(node.getData()) <= 0)
                rightIsBST = false;
            else
                rightIsBST = isBST(right);
        }
        return leftIsBST && rightIsBST;
    }

    public T getLowestCommonAncestor(T n1, T n2) {
        return null;

    }

    public List<T> getNodesBetween(T n1, T n2) {
        return null;
    }

}
