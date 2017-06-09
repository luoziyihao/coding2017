package tree;

import stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/5/8.
 */
public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        if (root != null) {
            result.add(root.getData());
            if (root.getLeft() != null)
                result.addAll(preOrderVisit(root.getLeft()));
            if (root.getRight() != null)
                result.addAll(preOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        if (root != null) {
            result.addAll(inOrderVisit(root.getLeft()));
            result.add(root.getData());
            result.addAll(inOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        if (root != null) {
            result.addAll(postOrderVisit(root.getLeft()));
            result.addAll(postOrderVisit(root.getRight()));
            result.add(root.getData());
        }
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     * 思路：
     * 栈有两个特性：1.适合深度遍历，从底层向上；2.采用先进后出，是正常的逆序
     * 按照 右子节点 左子节点 根节点    的顺序放入栈中，利用栈的第二个特性，弹栈的时候正好满足前序的结果顺序
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        if (root != null)
            stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<T> node = stack.pop();
            result.add(node.getData());
            if (node.getRight() != null)
                stack.push(node.getRight());
            if (node.getLeft() != null)
                stack.push(node.getLeft());
        }
        return result;
    }


    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * 思路：1.首先一层层遍历节点的左子节点，并把遍历到节点存入栈中
     * 2.当遍历到叶子节点，在向下就没有左子节点入栈了，所以开始弹栈
     * 3.每弹出一个元素，实际上都是叶子节点的根节点
     * 4.每弹出一个元素，都需要判断该节点是否存在右子节点，如果有，则将右子节点压栈
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        while (root != null || !stack.isEmpty()) {
            //如果存在左子节点一直入栈
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            //某个子树没有左子节点了，从栈中弹一个元素，并指向弹出节点的右子节点（不管是否存在，由上面的while循环判断）
            root = stack.pop();
            result.add(root.getData());
            root = root.getRight();
        }
        return result;
    }

}
