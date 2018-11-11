package edu.wm.cs.cs301.slidingpuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import edu.wm.cs.cs301.slidingpuzzle.PuzzleState.Operation;

//Class DFSolver uses the DFS algorithm to reach a desired final state from a given initial state. 
public class DFSSolver implements PuzzleSolver {
	
	private int[] initialState;
	private int[] finalState;
	private int counter;
	private PuzzleState curState;

	@Override
	public boolean configure(int[] initial, int[] goal) {
		//initialize variables for the initial state of the puzzle, the desired final state, the counter 
		//(number of operations), returns boolean if the length of the initial array is equal to the length
		//of the provided final state. If it is not then the puzzle is not solvable and the boolean returns false
		// TODO Auto-generated method stub
		initialState = initial;
		finalState = goal;
		counter = 0;
		return initial.length == goal.length; 
	}
	
	private boolean CheckMember(Set<int[]> set, int[] state) {
		//Checks to see the membership of an item in a set
		for (int[] item: set) {
			if (Arrays.equals(state, item)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Operation[] movesToSolve() {
		// TODO Auto-generated method stub
		/*Implement Depth-First-Search (DFS) to find a path from the root node to a provided final state,
		here the starting state of the puzzle will be represented by the root node. DFS
		uses a stack (last-in-first-out based service) and continues with the last state explored. 
		Initialize two stacks, a hash set, and a variable curState that gets the initial state of the puzzle. 
		*/
		Stack<Operation> OpList = new Stack<Operation>();
		Stack<PuzzleState> q = new Stack<PuzzleState>();
		Set<int[]> garbage = new HashSet<int[]>();
		curState = this.getSolverInitialState();
		q.add(curState); //add the current state to the stack
		
		while (!q.isEmpty()) {
			curState = q.pop();  //remove the current state from the stack
			//System.out.println(q.size());
			if (!Arrays.equals(curState.getState(), this.getSolverFinalState().getState())) {
				garbage.add(curState.getState());
				if (curState.moveUp() != null) {
					//If the moveUp operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
				    if (!CheckMember(garbage, curState.moveUp().getState())) {
					    q.push(curState.moveUp());//add the new current state after the op to the stack
					    //garbage.add(curState.moveUp().getState());
				    } //else {System.out.println('1');}
				    
				}
				if (curState.moveDown() != null) {
					//If the moveDown operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
				    if (!CheckMember(garbage, curState.moveDown().getState())) {
				    	q.push(curState.moveDown());//add the new current state after the op to the stack
				    	//garbage.add(curState.moveDown().getState());
				    } //else {System.out.println('1');}
				}
				if (curState.moveRight() != null) {
					//If the moveRight operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
				    if (!CheckMember(garbage, curState.moveRight().getState())) {
				    	q.push(curState.moveRight());//add the new current state after the op to the stack
				    	//garbage.add(curState.moveRight().getState());
				    } //else {System.out.println('1');}
				}
				if (curState.moveLeft() != null) {
					//If the moveLeft operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
					if (!CheckMember(garbage, curState.moveLeft().getState())) {
					//if (!garbage.contains(curState.moveLeft().getState())) {
						q.push(curState.moveLeft());//add the new current state after the op to the stack
						//garbage.add(curState.moveLeft().getState());
					} //else {System.out.println('1');}
				}
			} else {
				while (curState.getParent() != null) {
					//Initialize variable count to represent the number of elements in the stack
					OpList.push(curState.getOperation());
					curState = curState.getParent();
				}
				Operation[] OpArray = new Operation[OpList.size()];
				//Initialize variable count to represent the number of elements in the stack
				int count = OpList.size();
				for (Operation Op: OpList){
					count--;
					OpArray[count] = Op;
					
				}
				//System.out.println(OpArray.length);
				return OpArray;
			}
			
		}
		
		return null;
	}

	@Override
	public PuzzleState getSolverInitialState() {
		//get the initial state of the puzzle
		// TODO Auto-generated method stub
		PuzzleState InitialState = new SimplePuzzleState();
		InitialState.setState(initialState);
		return InitialState;
	}

	@Override
	public PuzzleState getSolverFinalState() {
		//get the desired final state of the puzzle
		// TODO Auto-generated method stub
		PuzzleState FinalState = new SimplePuzzleState();
		FinalState.setState(finalState);
		return FinalState;
	}

	@Override
	public int getNumberOfStatesExplored() {
		//Return the number of states that have been explored, 
		// TODO Auto-generated method stub
		return counter;
	}

	@Override
	public int getMaxSizeOfQueue() {
		//Return the maximum number of states that need to be explored (use n!) where n is the length of the initial
		//state 
		int n = initialState.length;
		int result = 1;
		for (int i = 0; i < n; i++) {
			result = result*i;
		}
		// TODO Auto-generated method stub
		return result; // do we need to set internally or by users
	}

}
