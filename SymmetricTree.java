import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 * LeetCode 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {


    /**
     * Given the root of a binary tree, 
     * check whether it is a mirror of itself.
     * Entry point for recursive call.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 36.9 MB, less than 63.58% of Java online submissions.
     */
    static boolean isSymmetricR(TreeNode root) {
        return isSymmetricR(root.left, root.right);
    }


    /**
     * Given the root of a binary tree, 
     * check whether it is a mirror of itself.
     * Recursive call.
     */
    static private boolean isSymmetricR(TreeNode left, TreeNode right) {

        // **** base case(es) ****
        if (left == null || right == null)
            return left == right;

        if (left.val != right.val)
            return false;

        // **** recurse ****
        return  isSymmetricR(left.left, right.right) && 
                isSymmetricR(left.right, right.left);
    }


    /**
     * Given the root of a binary tree, 
     * check whether it is a mirror of itself.
     * Iterative approach using BFS.
     * 
     * Runtime: 1 ms, faster than 22.70% of Java online submissions.
     * Memory Usage: 38.5 MB, less than 8.37% of Java online submissions.
     */
    static boolean isSymmetricI(TreeNode root) {

        // **** initialization ****
        Queue<TreeNode> q = new LinkedList<>();

        // **** prime the queue ****
        q.add(root);
        q.add(root);

        // **** loop while we have nodes in the queue ****
        while (!q.isEmpty()) {

            // ???? ????
            System.out.println("<<< q: " + q.toString());

            // **** retrieve and remove head nodes from the queue ****
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();

            // ???? ????
            System.out.println("<<< t1: " + t1 + " ? t2: " + t2);

            // **** both nodes are null (move to next pair of nodes) ****
            if (t1 == null && t2 == null)
                continue;

            // **** one of nodes is null (tree not symmetric) ****
            if (t1 == null || t2 == null)
                return false;

            // **** node values are different (tree not symmetric) ****
            if (t1.val != t2.val)
                return false;

            // **** the right and left children of the two nodes 
            //      are inserted in the queue in opposite order ****
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);     
        }

        // **** tree is symmetric ****
        return true;
    }


    /**
     * Test scaffold
     * @throws IOException
     * 
     * NOT PART OF SOLUTION
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        
        // **** create String[] with values for the BST ****
        String[] strArr = br.readLine().trim().split(",");
 
        // **** close the buffered reader ****
        br.close();

        // ???? ????
        // System.out.println("main <<< strArr.length: " + strArr.length);
        // System.out.println("main <<< strArr: " + Arrays.toString(strArr));

        // **** generate an Integer[] to build the BST ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null") || strArr[i].isEmpty())
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }
 
        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** create and populate the BT ****
        BST bst = new BST();
        bst.root = bst.populate(arr);

        // ???? ????
        // System.out.println("main <<< DFS: " + bst.inOrder(bst.root));
        // System.out.println("main <<< BFS: " + bst.levelOrder());

        // **** determine if BT is or is NOT symmetric ****
        System.out.println("main <<< output: " + isSymmetricR(bst.root));

        // **** determine if BT is or is NOT symmetric ****
        System.out.println("main <<< output: " + isSymmetricI(bst.root));
    }
}