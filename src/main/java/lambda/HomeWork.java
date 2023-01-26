package lambda;

public class HomeWork {
    public static void main(String[] args) {
        repeat(() -> payroll());
        repeat(() -> loanPayment());
    }

    public static void payroll() {
        System.out.println("Зарплата начислена");
    }

    public static void loanPayment() {
        System.out.println("Кредит погашен");
    }

    public static void repeat(InterfaceHomeWork interfaceHomeWork) {
        int i;
        for (i = 0; i < 5; i++) {
            interfaceHomeWork.payMany();
        }
    }
}
