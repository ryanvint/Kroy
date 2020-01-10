package com.earlybird.kroygame.pathfinding;

import java.util.*;

public class AStar {
	
	private Node[][] map;
	private PriorityQueue<Node> openList;
	private Set<Node> closedSet;
	private Node initialNode;
	private Node finalNode;

	public AStar(int[][] grid, Node i, Node f) {
		this.initialNode = i;
		this.finalNode = f;
		this.map = new Node[grid.length][grid[0].length];
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        generateNodeMap(grid);
        this.closedSet = new HashSet<>();
	}
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

	private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }
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
