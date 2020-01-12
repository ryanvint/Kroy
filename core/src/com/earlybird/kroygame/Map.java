package com.earlybird.kroygame;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.earlybird.kroygame.pathfinding.*;

public class Map {
	
	private int[][] grid;
	
	public Map(TiledMap map) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);
		this.grid = new int[layer.getWidth()][layer.getHeight()]; 
		System.out.println(layer.getHeight());
        for(int x = 0; x < layer.getWidth();x++){
        	for(int y = 0; y < layer.getHeight();y++){
        		TiledMapTileLayer.Cell cell = layer.getCell(x,y);
        		if(cell!=null) {
        			if (cell.getTile().getId() == 925 || cell.getTile().getId() == 926 || cell.getTile().getId() == 957 || cell.getTile().getId() == 958 || cell.getTile().getId() == 959 || cell.getTile().getId() == 960 || cell.getTile().getId() == 989 || cell.getTile().getId() == 990 || cell.getTile().getId() == 991 || cell.getTile().getId() == 992 || cell.getTile().getId() == 1021 || cell.getTile().getId() == 1022 || cell.getTile().getId() == 1023 || cell.getTile().getId() == 1024) {
                    	this.grid[x][y] = 1;
                    }
                    else {
                    	this.grid[x][y] = 0;
                    }
        		}
            }
        }
        printMap();
	}
	public void printMap() {
		for (int x = 0; x < this.grid.length; x++) {
        	for (int y = 0; y < this.grid[x].length; y++) {
        		System.out.print(grid[x][y]);
        	}
        	System.out.println();
        }
	}
	//public method to query grid
	public boolean isRoad(Vector2 point) {
		//POINT IS NULL
		if(point!=null) {
			int x = (int) Math.floor(point.x / 32);
			int y = (int) Math.floor(point.y / 32);
			
			if (grid[x][y] == 1) {
				return true;
			}
		}
		return false;
	}
	// start - actors current position
		// end - actors moveToLocation
		public List<Action> pathfind(Vector2 start, Vector2 end, float speed) {
			
			List<Action> actions = new ArrayList<Action>();
			//calculate start tile and move towards it
			Vector2 startTile = new Vector2((float) Math.floor(start.x / 32), (float) Math.floor(start.y / 32));
			Vector2 endTile = new Vector2((float) Math.floor(end.x / 32), (float) Math.floor(end.y / 32));

			AStar pathfinder = new AStar(grid, new Node((int) startTile.x, (int) startTile.y),
					new Node((int) endTile.x, (int) endTile.y));
			List<Node> nodes = pathfinder.findPath();
			nodes.remove(0);
			Node prevNode = new Node((int) startTile.x, (int) startTile.y);
			for (Node n : nodes) {
				MoveToAction action = new MoveToAction();
				action.setPosition(n.getX()*32, n.getY()*32);
				action.setDuration(speed);
				//add rotation
				RotateToAction rotation = new RotateToAction();
				if (n.getX() > prevNode.getX()) {
					rotation.setRotation(270);
				}
				if (n.getX() < prevNode.getX()) {
					rotation.setRotation(90);
				}
				if (n.getY() > prevNode.getY()) {
					rotation.setRotation(180);
				}
				if (n.getY() > prevNode.getY()) {
					rotation.setRotation(0);
				}
				//actions.add(rotation);
				actions.add(action);
				prevNode = n;
//				System.out.println(n.getX() + " " + n.getY());
			}
			
			// move to centre of start tile
			
			
//			if (startTile != endTile) {
//				
//			}
			return actions;
		}
}
