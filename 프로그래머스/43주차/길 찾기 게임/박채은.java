import java.util.*;

class Node{
    public int x;
    public int y;
    public int value;
    public Node left;
    public Node right;

    public Node(int x, int y,int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
class Solution {
    static int[][] answer;
    static int idx = 0;
    public int[][] solution(int[][] nodeinfo) {
        int nodeCnt = nodeinfo.length;
        answer = new int[2][nodeCnt];
        Node[] tree = new Node[nodeCnt];

        // 1. 이진트리 만들기
        for(int i=0;i<nodeCnt;i++){
            tree[i] = new Node(nodeinfo[i][0],nodeinfo[i][1],i+1);
        }
        // y좌표로 내림차순, x좌표로 오름차순
        Arrays.sort(tree, new Comparator<Node>(){
            public int compare(Node a, Node b){
                if(a.y == b.y){
                    return a.x - b.x;
                }
                return b.y- a.y;
            }
        });

        Node parent = tree[0];
        for(int i=1;i<nodeCnt;i++){
            makeTree(parent, tree[i]);
        }

        for(int i=0;i<nodeCnt;i++){
            System.out.println(tree[i].x + " : "+ tree[i].y+ " : "+ tree[i].value);
        }

        // 2. 전위순회
        preorder(parent);

        idx = 0;
        // 3. 후위순회
        postorder(parent);

        return answer;
    }

    public void makeTree(Node parent, Node child){
        if(parent.x > child.x){ // 왼쪽 자식 노드
            if(parent.left == null){
                parent.left = child;
            }else{
                makeTree(parent.left, child);
            }
        }
        else{ // 오른쪽 자식 노드
            if(parent.right == null){
                parent.right = child;
            }else{
                makeTree(parent.right, child);
            }
        }
    }

    public void preorder(Node root){
        if(root != null){
            answer[0][idx++] = root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void postorder(Node root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            answer[1][idx++] = root.value;
        }
    }
}