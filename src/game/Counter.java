package game;

public class Counter {
    private int count;
    // add number to current count.
    public Counter(){
        this.count = 0;
    }
    void increase(int number){
        this.count += number;

    }
    // subtract number from current count.
    void decrease(int number){
        this.count -= number;

    }
    // get current count.
    int getValue(){
        return this.count;

    }
}
