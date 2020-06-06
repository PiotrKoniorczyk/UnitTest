package testing;

public class Account {
    private boolean acctive;
    private Address defaultDeliveryAddress;

    public Account(){
        this.acctive = false;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if(defaultDeliveryAddress != null){
            activate();
        }else {
            this.acctive = false;
        }
    }

    public void activate(){
        this.acctive = true;
    }

    public boolean isActive(){
        return this.acctive;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
