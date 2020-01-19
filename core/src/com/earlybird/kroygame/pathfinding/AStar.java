package com.earlybird.kroygame.pathfinding;

import java.util.*;

/**
 * Implements the AStar Algorithm for Unit movement
 */
public class AStar {
	
	private Node[][] map;
	private PriorityQueue<Node> openList;
	private Set<Node> closedSet;
	private Node initialNode;
	private Node finalNode;

	/**
	 * 
	 * @param grid	boolean grid to show untraversable tiles. 
	 * @param i	start node
	 * @param f	end Node
	 */
	public AStar(int[][] grid, Node i, Node f) {
		this.initialNode = i;
		this.finalNode = f;
		this.map = new Node[grid.length][grid[0].length];
		// a priority queue whose elements are ordered from lowest to highest f
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        generateNodeMap(grid);
        this.closedSet = new HashSet<>();
	}
	/**
	 * generates a node map that corresponds to grid. If the value held in grid for a particular node is 0 then the
	 * setBlocked parameter of the Node is set to true.
	 * @param grid
	 */
	public void generateNodeMap(int[][] grid){
		for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                if (grid[i][j] == 0) {
                	node.setBlocked(true);
                }
                this.map[i][j] = node;
            }
        }
	}
	public List<Node> findPath(){	
		this.openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<Node>();
	}
	/**
	 * finds the shortest path between the start and end node by backtracking through each node's parent node until it
	 * reaches the start node while adding these nodes to the start of a list
	 * @param currentNode
	 * @return
	 */
	private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }
	/**
	 * call checkNode for all neighbouring nodes of currentNode depending on where it is on the map
	 * @param currentNode
	 */
	private void addAdjacentNodes(Node currentNode) {
		int x = currentNode.getX();
		int y = currentNode.getY();
		if (x-1 >= 0) {
			checkNode(currentNode, x-1, y, 1);
		}
		if (x+1 < this.map.length) {
			checkNode(currentNode, x+1, y, 1);
		}
		if (y-1 >= 0) {
			checkNode(currentNode,x, y-1, 1);
		}
		if (y+1 < this.map[0].length) {
			checkNode(currentNode,x, y+1, 1);
		}
	}
	/**
	 * If the node at map[col][row] is not blocked and is not in the closed set or openList then it's NodeData is updated
	 * and it is added to the openList. If it is not blocked or in the closed set but in the openList then we check to
	 * see if the path from the currentNode to the adjacent Node is better than its previous path to its parentNode.
	 * If it changed the node it then updates it in the opeList by removing it and adding it.
	 * @param currentNode
	 * @param col
	 * @param row
	 * @param cost
	 */
    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = map[col][row];
        if (!adjacentNode.isBlocked() && !closedSet.contains(adjacentNode)) {
            if (!openList.contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                openList.add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    openList.remove(adjacentNode);
                    openList.add(adjacentNode);
                }
            }
        }
    }
    /**
     * checks if current node is the final node
     * @param currentNode
     * @return
     */
	private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }
	/**
	 * checks if openList is empty
	 * @param openList
	 * @return
	 */
	private boolean isEmpty(PriorityQueue<Node> openList) {
	        return openList.size() == 0;
	}

	public Node[][] getMap() {
		return map;
	}
	public void setMap(Node[][] map) {
		this.map = map;
	}
	public PriorityQueue<Node> getOpenList() {
		return openList;
	}
	public void setOpenList(PriorityQueue<Node> openList) {
		this.openList = openList;
	}
	public Set<Node> getClosedSet() {
		return closedSet;
	}
	public void setClosedSet(Set<Node> closedSet) {
		this.closedSet = closedSet;
	}
	public Node getInitialNode() {
		return initialNode;
	}
	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}
	public Node getFinalNode() {
		return finalNode;
	}
	public void setFinalNode(Node finalNode) {
		this.finalNode = finalNode;
	}
}
