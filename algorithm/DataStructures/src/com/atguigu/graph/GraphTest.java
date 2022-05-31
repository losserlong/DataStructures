package com.atguigu.graph;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/10    19:41
 * @Version:1.0
 */
public class GraphTest {


    public static void main(String[] args) {
        // 结点的个数
        int n = 5;
        // 结点的值
        String vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边  A-B     A-C     C-B     B-D     B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        graph.bfs();


    }


    /**
     * 测试深度优先遍历和广度优先遍历
     */
    @Test
    public void bfsAndDfs() {
//        String vertexs[] = {"A", "B", "C", "D", "E"};
        String vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        Graph graph = new Graph(vertexs.length);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // a,b  a,c  b,c  b,d  b,e
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);


        graph.showGraph();
        // 深度优先遍历
        graph.dfs();
        System.out.println();
        // 广度优先遍历
        graph.bfs();




    }


    /**
     * 广度优先遍历
     */
    @Test
    public void bfs() {
        // 结点的个数
        int n = 5;
        // 结点的值
        String vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边  A-B     A-C     C-B     B-D     B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        // 广度优先遍历
        graph.bfs();


    }


    /**
     * 测试深度优先遍历
     */
    @Test
    public void dfsTest() {
        // 结点的个数
        int n = 5;
        // 结点的值
        String vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边  A-B     A-C     C-B     B-D     B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        graph.dfs();


    }


    @Test
    public void testFor() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("*");
            }
            System.out.println();
        }


    }


}
