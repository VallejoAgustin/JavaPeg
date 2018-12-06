import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {

    //checks whether a move is possible
    public static boolean isMove(Board kd, Move m){
        int f = m.getF(), o = m.getO(), t = m.getT();
        //Board c = new Board(kd);//copy over kd

        //if move possible
        if(kd.getIndex(f) == 1 && kd.getIndex(o) == 1 && kd.getIndex(t) == 0){
            return true;
        }
        else{//not valid
            return false;
        }
    }

    public static boolean solve(Board kd, Move[] moves, List<Move> totalMoves){
        final int NUM_OF_POSSIBLE_MOVES = 18;

        int k = kd.getIndex(15);

        if(kd.pegsRemaining() == 1){//found a solution
            return true;
        }
        else if(k < 2 || totalMoves.size() > 12) {//is an exit condition for no moves left
            return false;
        }
        else{
            //go through all possible moves from current state and place in stack
            Stack<Move> possibleMoves = new Stack();
            for(int i = 0; i < NUM_OF_POSSIBLE_MOVES; i++){
                if(isMove(kd, moves[i])){//if move is possible, add it to stack
                    possibleMoves.push(moves[i]);
                }
                if(isMove(kd, moves[i].getReverse())){//if reverse move is possible, add it to stack
                    possibleMoves.push(moves[i].getReverse());
                }
            }

            while(!possibleMoves.empty()){//while we have moves left
                Move currentMove = possibleMoves.pop();//pop move from stack

                Board kc = new Board(kd);
                kc.doMove(currentMove);
                List<Move> curTotalMoves = new LinkedList<>(totalMoves);
                curTotalMoves.add(currentMove);

                if(solve(kc, moves, totalMoves)){
                    totalMoves.add(currentMove);
                    return true;
                }
                else{
                    continue;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        //initializes an array to store all possible moves
        final int NUM_OF_POSSIBLE_MOVES = 18;
        Move[] moves = new Move[NUM_OF_POSSIBLE_MOVES];
        //gives memory
        for(int i = 0; i < NUM_OF_POSSIBLE_MOVES; i++){
            moves[i] = new Move();
        }
        //sets the moves
        moves[0].setMove(0,1,3);
        moves[1].setMove(0,2,5);
        moves[2].setMove(1,3,6);
        moves[3].setMove(1,4,8);
        moves[4].setMove(2,4,7);
        moves[5].setMove(2,5,9);
        moves[6].setMove(3,6,10);
        moves[7].setMove(3,7,12);
        moves[8].setMove(4,7,11);
        moves[9].setMove(4,8,13);
        moves[10].setMove(5,8,12);
        moves[11].setMove(5,9,14);
        moves[12].setMove(3,4,5);
        moves[13].setMove(6,7,8);
        moves[14].setMove(7,8,9);
        moves[15].setMove(10,11,12);
        moves[16].setMove(11,12,13);
        moves[17].setMove(12,13,14);

        Board cells = new Board();//creates a board object

        for(int i = 0; i < 5; i++) {
            cells.init(i);//initializes the board each iteration w/ new starting location
            List<Move> totalMoves = new LinkedList<>();

            solve(cells, moves, totalMoves);//finds first solution

            System.out.println("=== " + i + " ===");
            for(int j = 0; j < totalMoves.size(); j++){//traverses the moves backwards
                Move thisMove = totalMoves.get(totalMoves.size() - j - 1);
                cells.doMove(thisMove);//does the move
                cells.print();//prints board after doing move
                System.out.println();
            }
        }
    }
}
