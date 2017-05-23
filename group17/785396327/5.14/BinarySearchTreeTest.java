import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tree.BinaryTreeNode;

import java.util.List;

/**
 * Created by gongxun on 2017/5/15.
 */
public class BinarySearchTreeTest {
    BinarySearchTree<Integer> tree = null;

    @Before
    public void setUp() throws Exception {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
        root.setLeft(new BinaryTreeNode<Integer>(2));
        root.setRight(new BinaryTreeNode<Integer>(8));
        BinaryTreeNode<Integer> left = root.getLeft();
        BinaryTreeNode<Integer> right = root.getRight();
        left.setLeft(new BinaryTreeNode<Integer>(1));
        left.setRight(new BinaryTreeNode<Integer>(4));
        BinaryTreeNode<Integer> leftLeft = left.getLeft();
        BinaryTreeNode<Integer> leftRight = left.getRight();
        leftRight.setLeft(new BinaryTreeNode<Integer>(3));
        tree = new BinarySearchTree<Integer>(root);
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testFindMin() {
        Assert.assertEquals(1, tree.findMin().intValue());

    }

    @Test
    public void testFindMax() {
        Assert.assertEquals(8, tree.findMax().intValue());
    }

    @Test
    public void testHeight() {
        Assert.assertEquals(4, tree.height());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(6, tree.size());
    }

    @Test
    public void testRemoveLeaf() {
        tree.removeUseRecursion(4);
        BinaryTreeNode<Integer> root = tree.getRoot();
        Assert.assertEquals(3, root.getLeft().getRight().getData().intValue());

    }

    @Test
    public void testRemoveMiddleNode() {
        tree.removeUseRecursion(2);
        BinaryTreeNode<Integer> root = tree.getRoot();
        Assert.assertEquals(3, root.getLeft().getData().intValue());
        Assert.assertEquals(4, root.getLeft().getRight().getData().intValue());
    }

    @Test
    public void testFindParent() {
        BinaryTreeNode<Integer> node = tree.findParent(tree.getRoot(), 8);
        System.out.println(node);
    }

    @Test
    public void testLevelVisit() {
        List<Integer> integers = tree.levelVisit();
        Assert.assertEquals("[6, 2, 8, 1, 4, 3]", integers.toString());
    }

    @Test
    public void testIsValid() {
        BinaryTreeNode<Integer> root = tree.getRoot();
        root.getLeft().getLeft().setRight(new BinaryTreeNode<Integer>(7));
        Assert.assertSame(false, tree.isValid());
    }
}
