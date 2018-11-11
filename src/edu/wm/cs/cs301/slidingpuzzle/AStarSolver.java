package edu.wm.cs.cs301.slidingpuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;

import edu.wm.cs.cs301.slidingpuzzle.PuzzleState.Operation;

//Class AStar Solver uses the A* algorithm to reach a desired final state from a given initial state. 
public class AStarSolver implements PuzzleSolver {
	
	private int[] initialState;
	private static int[] finalState;
	private int counter;
	private PuzzleState curState;

	@Override
	public boolean configure(int[] initial, int[] goal) {
		//initialize variables for the initial state of the puzzle, the desired final state, the counter 
		//(number of operations), returns boolean if the length of the initial array is equal to the length
		//of the provided final state. If it is not then the puzzle is not solvable and the boolean returns false
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
	
	/* Compare the heurisics method from simplePuzzleState
	*/
	public static Comparator<PuzzleState> Distance = new Comparator<PuzzleState>(){
        @Override
        public int compare(PuzzleState c1, PuzzleState c2) {
        	//System.out.println(c1.getDistance());
        	if (c1.getHeurisics(finalState) > c2.getHeurisics(finalState) ) {
        		return 1;//compare value in the queue, if greater return 1
        	} 
        	else if (c1.getHeurisics(finalState) == c2.getHeurisics(finalState) ) {
        		return 0; //compare value in the queue, if equal return 0
        	}
        	else {
        		return -1;//else return 1
        	}
        }
    };
    
    private int setParameters(PuzzleState state) {
    	//Find the distance between the current state and the initial state. 
		int count = 0;
		PuzzleState temp = state;
		while (temp.getParent() != null) {
			temp = temp.getParent();
			count++;
		}
		//state.setDistance(count);
		//System.out.println(state.getDistance());
		return count;
	 
	}

	
	@Override
	/*Implement the A* algorithm to find a path from the root node to a provided final state.The Manhattan distance is 
	 *  used in the A* search to calculate the distance between the distance between two puzzle states, the 
	 *  calculation is provided in the SimplePuzzleState class. Initialize a queue, set, stack, a hash set,
	 *  a variable curState that gets the initial state of the puzzle, and a variable to be used to represent the 
	 *  temporary state of the puzzle state. 
	 *  */  
	
	public Operation[] movesToSolve() {
		// TODO Auto-generated method stub
		Queue<PuzzleState> q =  new PriorityQueue<PuzzleState>(1,Distance);
		//Queue<PuzzleState> q = new LinkedList<PuzzleState>();
		Set<int[]> garbage = new HashSet<int[]>();
		Stack<Operation> OpList = new Stack<Operation>();
		PuzzleState temp = new SimplePuzzleState();
		curState = this.getSolverInitialState();
		curState.setDistance(setParameters(curState));
		q.add(curState);//add the current state to the queue
		while (!q.isEmpty() ){//&& q.size() < this.getMaxSizeOfQueue()) {
			curState = q.poll(); //retrieve/ remove the head of the queue
			if (!Arrays.equals(curState.getState(), this.getSolverFinalState().getState())) {
				garbage.add(curState.getState()); //add the state of the current puzzle state to the hash set
				if (curState.moveUp() != null) {
				//If the moveUp operation at the current state is valid, check if the state generated 
				//from the op is in the hash set (i.e. has been previously visited)
				    if (!CheckMember(garbage, curState.moveUp().getState())) {
				    	temp = curState.moveUp(); //set temporary puzzle state equal to the state after the operation
				    	temp.setDistance(setParameters(temp)); //set the distance using the setParameters method
				    	//System.out.println(curState.getDistance());
					    q.add(temp);//add the temp puzzle state to the queue
					
				    } //else {System.out.println('1');}    
				}
				if (curState.moveDown() != null) {
					//If the moveDown operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
				    if (!CheckMember(garbage, curState.moveDown().getState())) {
				    	temp = curState.moveDown();//set temporary puzzle state equal to the state after the operation
				    	temp.setDistance(setParameters(temp));//set the distance using the setParameters method
				    	q.add(temp);//add the temp puzzle state to the queue
				    	//garbage.add(curState.moveDown().getState());
				    } //else {System.out.println('1');}
				}
				if (curState.moveRight() != null) {
					//If the moveRight operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
				    if (!CheckMember(garbage, curState.moveRight().getState())) {
				    	temp = curState.moveRight();//set temporary puzzle state equal to the state after the operation
				    	temp.setDistance(setParameters(temp));//set the distance using the setParameters method
				    	q.add(temp);//add the temp puzzle state to the queue
				    	//garbage.add(curState.moveRight().getState());
				    } //else {System.out.println('1');}
				}
				if (curState.moveLeft() != null) {
					//If the moveUp operation at the current state is valid, check if the state generated 
					//from the op is in the hash set (i.e. has been previously visited)
					if (!CheckMember(garbage, curState.moveLeft().getState())) {
						temp = curState.moveLeft();//set temporary puzzle state equal to the state after the operation
				    	temp.setDistance(setParameters(temp));//set the distance using the setParameters method
						q.add(temp);
						//garbage.add(curState.moveLeft().getState());
					} 
				}
			} else {
				//While the current state puzzle has a parent, add the operation to the queue and make the parent
				//node the current node
				while (curState.getParent() != null) {
					OpList.push(curState.getOperation());
					curState = curState.getParent();
				}
				Operation[] OpArray = new Operation[OpList.size()];
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


