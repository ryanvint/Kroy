package Sepr2Assessment;
import com.badlogic.gdx.math.Vector2;

public class entityRangeTest {
	
	Vector2 mockReferenceLocationVector;
	
	public void setLocationVector(Vector2 mockLocation) {
		this.mockReferenceLocationVector = mockLocation;
	}
	
	public boolean isEntityinRange(Vector2 bottomLeft, Vector2 topRight) {  //Method
		Vector2 currentLocation = new Vector2(mockReferenceLocationVector);
		if(currentLocation.x>=bottomLeft.x && currentLocation.x<=topRight.x && currentLocation.y>=bottomLeft.y && currentLocation.y<=topRight.y) {
			return true;
		}
		return false;
	}
}
