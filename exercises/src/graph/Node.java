package graph;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/5/18 16:27
 * @Description: TODO
 * @Author: code_hlb
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
