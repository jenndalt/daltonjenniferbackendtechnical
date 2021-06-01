import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("ready");
        CashRegister cashRegister = new CashRegister();
        while(!cashRegister.isQuit()) {
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            try {
                if (command.contains("show")) {
                    System.out.println(cashRegister.show());
                } else if (command.contains("put")) {
                    System.out.println(cashRegister.put(command));
                } else if (command.contains("take")) {
                    System.out.println(cashRegister.take(command));
                } else if (command.contains("change")) {
                    System.out.println(cashRegister.change(command));
                } else if (command.contains("quit")) {
                    cashRegister.setQuit(true);
                } else {
                    System.out.println("Unknown action, please try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
