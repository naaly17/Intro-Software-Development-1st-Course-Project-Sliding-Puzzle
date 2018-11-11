 package edu.wm.cs.cs301.slidingpuzzle;
/*Authors: Chaoran Wei and Nadia Aly
 * Software Development, CSCI 303
 * September 16, 2015
*/
import java.util.Arrays;

public class SimplePuzzleState implements PuzzleState {

	private PuzzleState state1;
	private Operation Op;
	private int[] state2;
	private int dist;
	
	@Override
	public void setState(int[] gameState) {
	    state2 = gameState;
	}

	@Override
	public int[] getState() {
		return state2;
	}

	@Override
	public PuzzleState getParent() {
		// TODO Auto-generated method stub
		return state1;
	}

	@Override
	public void setParent(PuzzleState parentState) {
		// TODO Auto-generated method stub
		state1 = parentState;
	}

	@Override
	public void setOperation(Operation op) {
		// TODO Auto-generated method stub
		Op = op;

	}

	@Override
	public Operation getOperation() {
		// TODO Auto-generated method stub
		return Op;
	}

	@Override
	public void setDistance(int distance) {
		// TODO Auto-generated method stub
		dist = distance;
		//System.out.println(dist);
	}

	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		//System.out.println(dist);
		return dist;
	}

	@Override
	public boolean equals(PuzzleState other) {
		// TODO Auto-generated method stub
		if (other == null) {
			return false;
		} else {
			return Arrays.equals(other.getState(), state2);
		}
	}
	/*  moveUp Returns a new instance of PuzzleState where the 0 (gap) and the element above the
	 *  0 are swapped. The state, parent and operation attributes are properly set to represent the 
	 *  puzzle state after the move.
	 */
	@Override
	public PuzzleState moveUp() {
		
		int boardSize = (int) Math.sqrt(state2.length);
		for (int i = boardSize; i < state2.length; i++) {
		//Do not iterate over the top row of the puzzle board to look for the 0, this is invalid. 
		//If the 0 is not found, must be in the bottom, return null.
		    if (state2[i] == 0) {
		    	int[] state3 = state2.clone();
		   	    state3[i]=state3[i-boardSize] ;//place the item above the gap into the gap's position
		   	    state3[i-boardSize] = 0;//replace the previous element above the gap with 0 (the gap)
		   	    PuzzleState NewState = new SimplePuzzleState();	   	    
		   	    NewState.setState(state3);
		   	    PuzzleState ThisState = this;	  
		   	    NewState.setParent(ThisState);//set the parent state of the new current state produced from the operation
		   	    NewState.setOperation(Operation.MOVEUP);
				return NewState;//return the new puzzle state representing the puzzle after the operation
		    	   	 
		    }
		}
		return null;//return null if the move is invalid
	}
	/*  moveDown Returns a new instance of PuzzleState where the 0 (gap) and the element below the
	 *  0 are swapped. The state, parent and operation attributes are properly set to represent the 
	 *  puzzle state after the move.
	 */
	@Override
	public PuzzleState moveDown() {
		// TODO Auto-generated method stub
		int boardSize = (int) Math.sqrt(state2.length);
		for (int i = 0; i < state2.length-boardSize; i++) {
		//Do not iterate over the bottom row of the puzzle board to look for the 0, this is invalid. 
		//If the 0 is not found, must be in the bottom, return null.
		    if (state2[i] == 0) {
		      int[] state3 = state2.clone();
		   	  state3[i]=state3[i+boardSize] ;//place the item below the gap into the gap's position
		   	  state3[i+boardSize] = 0; //replace the previous element below the gap with 0 (the gap)
		   	  PuzzleState NewState = new SimplePuzzleState();
			  NewState.setState(state3);
			 // System.out.println(Arrays.equals(NewState.moveUp().getState(), state2));
			  PuzzleState ThisState = this;	  
	   	      NewState.setParent(ThisState);//set the parent state of the new current state produced from the operation
			  NewState.setOperation(Operation.MOVEDOWN);
			  return NewState;//return the new puzzle state representing the puzzle after the operation
		    }
		    
		}
		return null;//return null if the move is invalid
	}
	/*  moveLeft Returns a new instance of PuzzleState where the 0 (gap) and the element to the left of the
	 *  0 are swapped. The state, parent and operation attributes are properly set to represent the 
	 *  puzzle state after the move.
	 */
	@Override
	public PuzzleState moveLeft() {
		// TODO Auto-generated method stub
		int boardSize = (int) Math.sqrt(state2.length);
		for (int i = 0; i < state2.length; i++) {
		    if (state2[i] == 0) {
		    	int[] state3 = state2.clone();
		    	if (i % boardSize != 0 ) {
		    	//Make sure the gap is not on the left edge of the puzzle board, this is invalid. If it is invalid, return null.
		   	       state3[i]=state3[i-1] ;//place the item to the left of the gap into the gap's position
		   	       state3[i-1] = 0; //replace the previous Left element with 0 (the gap)
		   	    PuzzleState NewState = new SimplePuzzleState();
				NewState.setState(state3);
				PuzzleState ThisState = this;	  
		   	    NewState.setParent(ThisState);//set the parent state of the new current state produced from the operation
				NewState.setOperation(Operation.MOVELEFT);
				return NewState;//return the new puzzle state representing the puzzle after the operation
		    	}
		    }
		}
		return null;//return null if the move is invalid
	}
	
	/*  moveRight Returns a new instance of PuzzleState where the 0 (gap) and the element to the right of the
	 *  0 are swapped. The state, parent and operation attributes are properly set to represent the 
	 *  puzzle state after the move.
	 */
	@Override
	public PuzzleState moveRight() {
		// TODO Auto-generated method stub
		int boardSize = (int) Math.sqrt(state2.length);
		for (int i = 0; i < state2.length - 1; i++) { 
		    if (state2[i] == 0) {
		    	int[] state3 = state2.clone();
				//Make sure the gap is not on the right edge of the puzzle board, this is invalid. If it is invalid, return null.
		    	if ((i+1) % boardSize!=0) {
		   	       state3[i]=state3[i+1] ;//place the item to the right of the gap into the gap's position
		   	       state3[i+1] = 0; //replace the Right element with 0 (the gap)
		   	    PuzzleState NewState = new SimplePuzzleState();
				NewState.setState(state3);//set the state for the puzzle state representing after the operation
				PuzzleState ThisState = this;	  
		   	    NewState.setParent(ThisState); //set the parent state of the new current state produced from the operation
				NewState.setOperation(Operation.MOVERIGHT);
				return NewState; //return the new puzzle state representing the puzzle after the operation
		    	}
		    }
		}
		return null;//return null if the move is invalid
	}
	

	/* The Manhattan distance is used in A*, it defines the distance between the current state and the goal state
	*  as the sum of the absolute value of their Cartesian coordinates. For each element in the initial state 
	*  check the position of this element in the final state. Take the position of the element in each state
	*  represented by a 1-D array and sum the absolute value of the difference between the positions divided 
	*  by the board length and the remainder after division of the board length by the absolute value of the 
	*  difference between the position on each board 
	* */
	private int mandist(int[] Final) {
		//System.out.println(dist);
		int count = 0;
		int boardsize = (int) Math.sqrt(Final.length);
		for (int i = 0; i < state2.length; i++) {
			for (int j = 0; j < Final.length; j++) {
				if (state2[i] == Final[j]) {
					//System.out.println(i - j);
					count = (int) (count + Math.floor(Math.abs((i - j) / boardsize) 
							+ Math.abs((i - j) % boardsize)));
				}
			}
		}
	    //System.out.println(count);
		return count;
	}
	
	private int heuristics(int[] state) {
		int count2 = getDistance();
		int count1 = mandist(state);
		//System.out.println(getDistance());
		return count1 + count2;
	}
	
	public int getHeurisics(int[] state) {
		//System.out.println(Final);
		return this.heuristics(state);
	}

}

