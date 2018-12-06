public class Move {
    private int from, over, to;

    public Move(){
        //default
    }

    public Move(int from, int over, int to) {
        this.from = from;
        this.over = over;
        this.to = to;
    }

    public void setMove(int from, int over, int to){
        this.from = from;
        this.over = over;
        this.to = to;
    }

    public Move getReverse(){
        return new Move(this.to, this.over, this.from);
    }

    public void printMove(){
        System.out.println(from + " " + over + " " + to);
    }

    public int getF(){ return from; }

    public int getT(){ return to; }

    public int getO(){ return over; }
}
