import java.util.*;

class Solution {
    int[][] answer;
    int idx;
    
    class Node implements Comparable<Node> {
        // 노드 번호
        int num;
        // 노드 좌표
        int x, y;
        // 왼쪽 자식노드
        Node left;  
        // 오른쪽 자식노드
        Node right; 
        
        Node(int num, int x, int y, Node left, Node right) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }
        
        // y가 큰 순서대로, 같은 경우 x가 작은 순서대로
        @Override
        public int compareTo(Node o1){
            if(this.y == o1.y) return this.x - o1.x; 
            else return o1.y - this.y;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        ArrayList<Node> list = new ArrayList<>();
        
        for(int i=0;i<nodeinfo.length;i++) {
            list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1], null, null));
        }
        
        // Collections.sort(list, (a, b) -> a.y == b.y ? a.x-b.x : a.y-b.y);
        Collections.sort(list);
        
        Node root = list.get(0);
        for(int i=1;i<list.size();i++) {
            connectNode(root, list.get(i));
        }
        
        // 전위 순회
        preorder(root);
        idx = 0;
        // 후위 순회
        postorder(root);
        
        return answer;
    }
    
    void connectNode(Node parent, Node child) {
        // x가 부모노드보다 작을 경우, 왼쪽 자식노드
        if(parent.x > child.x) {    
            if(parent.left == null) parent.left = child;
            else connectNode(parent.left, child);
        } 
        // x가 부모노드보다 클 경우, 오른쪽 자식노드
        else {    
            if(parent.right == null) parent.right = child;
            else connectNode(parent.right, child);
        }
    }
    
    void preorder(Node root) {
        if(root != null) {
            answer[0][idx++] = root.num;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    void postorder(Node root) {
        if(root != null) {
            postorder(root.left);
            postorder(root.right);
            answer[1][idx++] = root.num;
        }
    }
}