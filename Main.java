
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


interface Transactable{
    public double deposit(double deposit);
    public double withdraw(double withdraw);

}
abstract class Account implements Transactable{
    private String ownerName;
    private String accountNumber;
    private double balance;

    public Account(String ownerName, String accountNumber, double balance) {
        this.ownerName = ownerName;
        setBalance(balance);
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance<0) System.out.println("Balance Can't Be Negative");
        else this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }



    @Override
    public double deposit(double deposit) {
        return this.balance = this.getBalance()+deposit;
    }

    @Override
    public double withdraw(double withdraw) {


        return this.balance = this.getBalance()-withdraw;
    }

    abstract void bankOwnerInfo();


    abstract double getInterestRate();
}
class SavingAccount extends Account{

    public SavingAccount(String ownerName, String accountNumber, double balance) {
        super(ownerName, accountNumber, balance);
    }

    @Override
    void bankOwnerInfo() {
        System.out.println("\nBank Details"+"\nName: "+getOwnerName()+"\nBank Account Number: "+getAccountNumber()+"\nBalance: "+getBalance()+"\nInterest Rate: "+getInterestRate()+"\nBank Type: Saving Account");
    }


    @Override
    double getInterestRate() {
        return 0.05;
    }
}
class CheckingAccount extends  Account{
    public CheckingAccount(String ownerName, String accountNumber, double balance) {
        super(ownerName, accountNumber, balance);
    }

    @Override
    void bankOwnerInfo() {
        System.out.println("\nBank Details"+"\nName: "+getOwnerName()+"\nBank Account Number: "+getAccountNumber()+"\nBalance: "+getBalance()+"\nInterest Rate: "+getInterestRate()+"\nBank Type: Checking Account");
    }


    @Override
    double getInterestRate() {

        return 0.15;
    }
}
public class Main {
    public static void Menu(){
        System.out.println("[A] Create Account");
        System.out.println("[B] Check Account Balance");
        System.out.println("[C] Withdraw Account");
        System.out.println("[D] Deposit Account");
        System.out.println("[E] Exit");
    }

    public static String generateAccountNumber(HashMap<String, Account> bank){
        boolean dup;
        String bankacc;
        do {
            bankacc = String.format("%06d", (int)(Math.random() * 1000000));
            dup = bank.containsKey(bankacc);
        } while(dup);
        return bankacc;
}

    public static void main(String[] args) {
        Account s1;
        String c = "";
        Scanner sc = new Scanner(System.in);
        HashMap<String, Account> Bank = new HashMap<>();
        do{
            Menu();
            c = sc.nextLine();

            switch (c.toUpperCase()){
                case "A" : {

                    System.out.print("Enter OwnerName: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Starting Balance: ");
                    double balance = 0;
                    boolean validBalance = false;
                    while (!validBalance) {
                        try {
                            balance = sc.nextDouble();
                            validBalance = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Enter a number.");
                            sc.nextLine();
                            System.out.print("Enter Starting Balance: ");
                        }
                    }
                    sc.nextLine();


                    System.out.println("[A]Savings or [B]Checking?");
                    String choice = sc.nextLine();
                    if(choice.equalsIgnoreCase("a")){
                        String bankacc = generateAccountNumber(Bank);
                        s1 = new SavingAccount(name, bankacc, balance);
                        Bank.put(bankacc, s1);
                        s1.bankOwnerInfo();
                        System.out.println("\nAccount Successfully Created\n");
                    }
                    else if (choice.equalsIgnoreCase("b")) {
                        String bankacc = generateAccountNumber(Bank);
                        s1 = new CheckingAccount(name,bankacc,balance);
                        Bank.put(bankacc, s1);
                        s1.bankOwnerInfo();
                        System.out.println("\nAccount Successfully Created\n");
                    }
                    else System.out.println("Wrong Input. Try Again");




                    break;
                }
                case "B" : {
                    System.out.print("Enter Bank Account number: ");
                    String bankAccNum = sc.nextLine();
                    s1 = Bank.get(bankAccNum);

                    if (s1 != null) {

                        s1.bankOwnerInfo();
                    } else {
                        System.out.println("Cannot Find Account. Please Try Again");
                    }
                    break;
                }
                case "C" : {
                    System.out.println("\n==Withdraw==");
                    System.out.println("Enter Bank Account Number: ");
                    String bankacc = sc.nextLine();

                    double amount = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("Enter Amount: ");
                        try {
                            amount = sc.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number.");
                            sc.nextLine();
                        }
                    }

                    sc.nextLine();
                   if(amount<0){ System.out.println("Amount Cannot be below 0. Please Try Again"); break;}
                    s1 = Bank.get(bankacc);

                    if (s1 != null) {
                        if (amount > s1.getBalance()) {
                            System.out.println("Insufficient Balance");
                        }
                        else {
                            s1.withdraw(amount);
                            s1.bankOwnerInfo();
                            System.out.println("Successfully Withdraw");
                        }
                    } else {
                        System.out.println("Cannot Find Account. Please Try Again");
                    }

                    break;
                }
                case "D" : {
                    System.out.println("\n==Deposit=="); System.out.println("Enter Bank Account Number: ");
                    String bankacc = sc.nextLine();

                    double amount = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("Enter Amount: ");
                        try {
                            amount = sc.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number.");
                            sc.nextLine();
                        }
                    }
                    sc.nextLine();
                    if(amount<0){ System.out.println("Amount Cannot be below 0. Please Try Again"); break;}
                    s1 = Bank.get(bankacc);

                    if (s1 != null) {
                        if(amount>10000000){
                            System.out.println("Deposit Limit 1000000");
                        }
                        else {
                            s1.deposit(amount);
                            s1.bankOwnerInfo();
                            System.out.println("Successfully Deposit");
                        }
                    } else {
                        System.out.println("Cannot Find Account. Please Try Again");
                    }


                    break;
                }
                case "E" : {
                    System.out.println("Exiting");
                    c = "e";
                    break;
                }

                default:
                    System.out.println("Invalid Input. Please Try Again.");
            }
        }while(!c.equalsIgnoreCase("e"));







    }




}
