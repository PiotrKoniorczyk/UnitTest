package testing;

public class Account {
    private boolean acctive;

    public Account(){
        this.acctive = false;
    }

    public void activate(){
        this.acctive = true;
    }

    public boolean isActive(){
        return this.acctive;
    }
}
