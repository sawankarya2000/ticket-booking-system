package main.java.com.hexaware;

public class Customer {
    String customerName;
    String email;
    String phoneNumber;

    Customer(String customerName, String email, String phoneNumber){
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    Customer(Customer customer){
        this.customerName = customer.customerName;
        this.email = customer.email;
        this.phoneNumber = customer.phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void displayCustomerDetails(){
        System.out.println("Name : " + customerName);
        System.out.println("Email : " + email);
        System.out.println("Phone Number : " + phoneNumber);
    }
}
