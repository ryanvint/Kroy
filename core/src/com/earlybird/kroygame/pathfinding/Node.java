package com.earlybird.kroygame.pathfinding;

/**
 * Used for calculating paths for path finding
 */
public class Node {

	private int x;
	private int y;
	
	private int f;
	private int g;
	private int h;
	
	private boolean blocked;
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
    public void setNodeData(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }
	@Override
	public boolean equals(Object obj) {
		return this.getX() == ((Node)obj).getX() && this.getY() == ((Node)obj).getY();
	}
	public void calculateHeuristic(Node finalNode) {
        this.h = Math.abs(finalNode.getX() - getX()) + Math.abs(finalNode.getY() - getY());
    }
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
}
