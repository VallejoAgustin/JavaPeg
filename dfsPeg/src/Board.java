public class Board {
    private int[] cells;

    public Board(){
        this.cells = new int[16];
    }

    public Board(Board rhs){
        this.cells = new int[16];

        for(int i = 0; i < 16; i++){
            cells[i] = rhs.getIndex(i);
        }
    }

    public void init(int i){
        for(int j = 0; j < 15; j++){
            cells[j] = 1;
        }
        cells[i] = 0;
        cells[15] = 14;
    }

    public int getIndex(int i){
        return cells[i];
    }

    public void doMove(Move m){
        cells[m.getF()] = 0;//moved away
        cells[m.getO()] = 0;//remove the piece that was jumped over
        cells[m.getT()] = 1;//landing here

        cells[15] = cells[15] - 1;//removes 1 from the move set
    }

    public void print(){
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
                if(cells[j] == 0){
                    System.out.print(". ");
                }
                else{
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
    }

    public void printAsInt(){
        for(int i = 0; i < 16; i++){
            System.out.print(cells[i] + " ");
        }
        System.out.println();
    }

    public int pegsRemaining(){
        int counter = 0;

        for(int i = 0; i < 15; i++){
            if(cells[i] == 1){
                counter++;
            }
        }

        return counter;
    }
}
