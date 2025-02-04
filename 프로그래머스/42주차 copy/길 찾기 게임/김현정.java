import java.util.*;

class Solution {

    static class Node {
        int x;
        int y;
        int value;
        Node leftNode;
        Node rightNode;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public int[][] solution(int[][] nodeinfo) {

        ArrayList<Node> list = new ArrayList<>();

        for (int i = 0, loop = nodeinfo.length; i < loop; i++) {
            list.add(new Node(nodeinfo[i][0],nodeinfo[i][1],i+1));
        }

        Collections.sort(list,new Comparator<Node>() {

            @Override
            public int compare(Node o1,Node o2) {

                if (o1.y == o2.y) {

                    if (o1.x < o2.x) return -1;
                    else return 1;

                } else if (o1.y < o2.y) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        Node top = list.get(0);

        ArrayList<Node> leftSide  = new ArrayList<>();
        ArrayList<Node> rightSide = new ArrayList<>();

        for (int i = 1, loop = list.size(); i < loop; i++) {

            Node cur = list.get(i);

            if (top.x < cur.x) rightSide.add(cur);
            else leftSide.add(cur);
        }

        makeEdge(top,leftSide,0);
        makeEdge(top,rightSide,1);

        ArrayList<Integer> preList  = new ArrayList<>();
        ArrayList<Integer> postList = new ArrayList<>();

        preorder(top,preList);
        postorder(top,postList);

        int[][] answer = new int[2][preList.size()];

        for (int i = 0, loop = preList.size(); i < loop; i++) {
            answer[0][i] = preList.get(i);
            answer[1][i] = postList.get(i);
        }

        return answer;
    }

    public void preorder(Node top,ArrayList<Integer> list) {

        if (top == null) return;

        list.add(top.value);
        preorder(top.leftNode,list);
        preorder(top.rightNode,list);
    }

    public void postorder(Node top, ArrayList<Integer> list) {

        if (top == null) return;

        postorder(top.leftNode,list);
        postorder(top.rightNode,list);
        list.add(top.value);
    }

    public void makeEdge(Node prev, ArrayList<Node> list,int dir) {

        if (list.size() == 0) return;

        Node top = list.get(0);

        if (dir == 0) prev.leftNode = top;
        else prev.rightNode = top;
        
        ArrayList<Node> leftSide = new ArrayList<>();
        ArrayList<Node> rightSide = new ArrayList<>();

        for (int i = 1, loop = list.size(); i < loop; i++) {

            Node cur = list.get(i);

            if (top.x < cur.x) rightSide.add(cur);
            else leftSide.add(cur);
        }

        makeEdge(top,leftSide,0);
        makeEdge(top,rightSide,1);
    }

}
