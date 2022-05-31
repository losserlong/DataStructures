package com.atguigu.tree.stackorderTree;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/28    16:43
 * @Version:1.0
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}
