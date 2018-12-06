public class Line {
    private int t, a, b;

    public Line(){
        //default
    }

    public Line(int t, int a, int b){
        this.t = t;
        this.a = a;
        this.b = b;
    }

    public void setLine(int t, int a, int b){
        this.t = t;
        this.a = a;
        this.b = b;
    }

    public int getT(){
        return t;
    }

    public int getA(){
        return a;
    }

    public int getB(){
        return b;
    }
}
