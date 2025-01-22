import java.util.*;

class Solution {

    static int[][] result;
    static int idx;

    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        Arrays.sort(nodes, (n1, n2) -> n1.y == n2.y ? n1.x - n2.x : n2.y - n1.y);

        Node root = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);
        return result;
    }

    private void insert(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }

    private void preorder(Node node) {
        if (node != null) {
            result[0][idx++] = node.value;
            preorder(node.left);
            preorder(node.right);
        }
    }

    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            result[1][idx++] = node.value;
        }
    }

    static class Node {
        int x, y, value;
        Node left, right;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
