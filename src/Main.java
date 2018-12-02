public class Main {

    //returns the 'cells' and count in index 15
    public static int[] init(int in){
        int[] cells = new int[16];

        for(int i = 0; i < 16; i++){
            cells[i] = 1;
        }

        //sets the index passed-in to be 0
        cells[in] = 0;
        cells[15] = 14;

        return cells;
    }

    //performs a move if possible
    public static int[] move(int[] kd, Move m){
        int f = m.getF(), o = m.getO(), t = m.getT();
        int[] c = kd;//copy over kd

        //if move possible
        if(kd[f] == 1 && kd[o] == 1 && kd[t] == 0){
            //move items around
            c[f] = 0;
            c[o] = 0;
            c[t] = 1;

            c[15] = c[15] - 1;//how I tell whether it is valid or not
        }
        else{//not valid
            c[15] = -100;
        }

        return c;
    }

    //finds all solutions
    public static int[] solve(int[] kd, Move[] moves){
        final int NUM_OF_POSSIBLE_MOVES = 18;

        if(kd[15] < 2){//if k<2 yield none, kd
            kd[15] = -100;
            return kd;//yield none, kd
        }
        else{
            //go through all possible moves
            for(int i = 0; i < NUM_OF_POSSIBLE_MOVES; i++){
                //frontwards move
                int kc[] = move(kd, new Move(moves[i].getF(), moves[i].getO(), moves[i].getT()));
                if(kc[15] != -100){//is valid

                }

                //backwards move
                int kc2[] = move(kd, new Move(moves[i].getT(), moves[i].getO(), moves[i].getF()));
                if(kc2[15] != -100){//is valid

                }
            }
        }
    }


    //takes in an array for the javapeg game and prints it
    public static void show(int[] kd){
        final int NUM_OF_LINES = 5;

        Line[] lines = new Line[NUM_OF_LINES];

        //gives each array element the necessary memory
        for(int i = 0; i < NUM_OF_LINES; i++){
            lines[i] = new Line();
        }

        //in order to print
        lines[0].setLine(4,0,0);
        lines[1].setLine(3,1,2);
        lines[2].setLine(2,3,5);
        lines[3].setLine(1,6,9);
        lines[4].setLine(0,10,14);

        for(int i = 0; i < NUM_OF_LINES; i++){
            String tab = "";

            //creates the 'tab' effect based on position
            for(int j = 0; j < lines[i].getT(); j++){
                tab += " ";
            }
            System.out.print(tab);

            for(int j = lines[i].getA(); j < lines[i].getB() + 1; j++){
                if(kd[j] == 0){
                    System.out.print(". ");
                }
                else{
                    System.out.print("x ");
                }
            }
            System.out.println();
        }

    }

    //main is our go() function
    public static void main(String[] args) {
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

        for(int i = 0; i < 5; i++){
            System.out.println(" ===" + i + "===");

            int[] cell = init(i);
            show(cell);

            System.out.println();
        }

    }
}