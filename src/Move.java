public class Move {
    private int from, over, to;
    private int bFrom, bOver, bTo;

    public Move(){
        //default
    }

    public Move(int from, int over, int to) {
        this.from = from;
        this.over = over;
        this.to = to;
        this.bFrom = to;
        this.bOver = over;
        this.bTo = from;
    }

    public void setMove(int from, int over, int to){
        this.from = from;
        this.over = over;
        this.to = to;
        this.bFrom = to;
        this.bOver = over;
        this.bTo = from;
    }

    public int getF(){ return from; }

    public int getT(){ return to; }

    public int getO(){ return over; }
}
