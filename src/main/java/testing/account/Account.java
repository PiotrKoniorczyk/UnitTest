package testing.account;

public class Account {
    private boolean acctive;
    private Address defaultDeliveryAddress;
    private String email;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if(email.matches("[a-zA-Z0-9.%-]+@[a-zA-Z0-9-]+.[a-zA-Z]{2,4}")){
            this.email = email;
        }else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }
}
