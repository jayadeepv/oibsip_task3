import java.util.Scanner;

class BankAccount{

    String accountHolder;
    String accountNumber;
    String userId;
    String userPin;
    float accountBalance = 10000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your Name: ");
        this.accountHolder = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountNumber = sc.nextLine();
        System.out.println("\nEnter your User ID: ");
        this.userId = sc.nextLine();
        System.out.println("\nEnter your User Pin: ");
        this.userPin = sc.nextLine();
        System.out.println("\nThe registration process was successful");
        System.out.println("_______________________________________________________________");
        System.out.println("Kindly log-in and access your bank account");
    }
    public boolean login() {
        boolean isLogin = false;
        Scanner sc=new Scanner(System.in);
        while( !isLogin) {
            System.out.println("\nEnter your User ID: ");
            String userID = sc.nextLine();
            if (userID.equals(userId)) {
                while(!isLogin) {
                    System.out.println("\nEnter your User Pin: ");
                    String userpin = sc.nextLine();
                    if(userpin.equals(userPin)) {
                        System.out.println("\nLogin Successful");
                        isLogin = true;
                    }
                    else {
                        System.out.println("\nIncorrect User Pin");
                    }
                }
            }else {
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("\nEnter Amount to Withdraw:");
        Scanner sc=new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if(accountBalance >= amount) {
                transactions++;
                accountBalance -= amount;
                System.out.println("\nWithdrawal Successful");
                String str = "\nDebited Rs."+amount;
                transactionHistory = transactionHistory.concat(str);
            }else {
                System.out.println("\nInsufficient Funds");
            }
        }catch(Exception e) {

        }

    }

    public void deposit() {
        System.out.println("\nEnter Amount to Deposit:");
        Scanner sc=new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if(amount <= 10000f) {
                transactions++;
                accountBalance += amount;
                System.out.println("\nDeposit Successful");
                String str = "\nCredited Rs."+amount;
                transactionHistory = transactionHistory.concat(str);
            }else {
                System.out.println("\nSorry. The limit is 10000.");
            }
        }catch(Exception e) {

        }

    }

    public void transfer() {
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter Amount to transfer: ");
        float amount = sc.nextFloat();
        try {
            if(accountBalance>= amount) {
                if(amount <= 50000f) {
                    transactions++;
                    accountBalance -= amount;
                    System.out.println("\nSuccessfully Transferred to "+recipient);
                    String str ="\nTransferred to "+recipient+" Rs."+amount;
                    transactionHistory = transactionHistory.concat(str);
                }else {
                    System.out.println("\nSorry. The limit is 50000.");
                }
            }else{
                System.out.println("\nInsufficient Balance.");
            }}catch(Exception e) {
        }

    }

    public void checkBalance() {
        System.out.println("\nAvailable balance: Rs."+accountBalance);
    }




    public void transHistory() {
        if(transactions ==0) {
            System.out.println("\nNo Transactions");
        }else {
            System.out.println("\nTransactions History");
            System.out.print(transactionHistory+"\n");
        }
    }
}

public class AtmInterface {

    public static int takenIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while(!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if(flag && input>limit || input<1) {
                    System.out.println("Choose the number between 1 to "+limit);
                    flag=false;
                }
            }catch(Exception e) {
                System.out.println("Enter only integer value.");
                flag=false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n********************WELCOME to ATM Interface*******************");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option: ");
        int choose = takenIntegerInput(2);

        if(choose == 1) {
            BankAccount b= new BankAccount();
            b.register();
            while(true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your choice: ");
                int ch = takenIntegerInput(2);
                if(ch==1) {
                    if(b.login()) {
                        System.out.println("\n********************WELCOME TO OUR BANKING SERVICES********************");
                        boolean isFinished = false;
                        while(!isFinished) {
                            System.out.println("\n1.Cash Withdraw \n2.Cash Deposit \n3.Fund Transfer \n4.Balance Enquiry \n5.Transactions History \n6.Exit");
                            System.out.println("\nEnter your choice: ");
                            int c = takenIntegerInput(6);
                            switch(c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                }else {
                    System.exit(0);
                }

            }
        }else {
            System.exit(0);
        }
    }

}


