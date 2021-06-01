import org.omg.SendingContext.RunTime;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CashRegister {
    private Map<Integer, Integer> cashDrawer = new HashMap<>();
    private Integer sum = 0;
    private boolean quit;

    public CashRegister() {
        cashDrawer.put(20, 0);
        cashDrawer.put(10, 0);
        cashDrawer.put(5, 0);
        cashDrawer.put(2, 0);
        cashDrawer.put(1, 0);
    }

    private String show() {
        return buildReturnString();
    }

    private String put(String command) {
        List<Integer> quantities = convertStringToIntList(command);
        cashDrawer.put(20, cashDrawer.get(20) + quantities.get(0));
        cashDrawer.put(10, cashDrawer.get(10) + quantities.get(1));
        cashDrawer.put(5, cashDrawer.get(5) + quantities.get(2));
        cashDrawer.put(2, cashDrawer.get(2) + quantities.get(3));
        cashDrawer.put(1, cashDrawer.get(1) + quantities.get(4));
        return buildReturnString();
    }

    private String take(String command) {
        List<Integer> quantities = convertStringToIntList(command);
        cashDrawer.put(20, cashDrawer.get(20) - quantities.get(0));
        cashDrawer.put(10, cashDrawer.get(10) - quantities.get(1));
        cashDrawer.put(5, cashDrawer.get(5) - quantities.get(2));
        cashDrawer.put(2, cashDrawer.get(2) - quantities.get(3));
        cashDrawer.put(1, cashDrawer.get(1) - quantities.get(4));
        return buildReturnString();
    }

    private String change(String command) {
        String[] changeArray = command.split(" ");
        Integer changeAmount = Integer.parseInt(changeArray[1]);
        Integer balancingRemaining = changeAmount;
        if (changeAmount >  sum) {
            return "Not enough money in drawer line 49";
        }
        balancingRemaining = adjustNumberOfBills(20, balancingRemaining);
        System.out.println("20: " + balancingRemaining);
        balancingRemaining = adjustNumberOfBills(10, balancingRemaining);
        System.out.println("10: " +balancingRemaining);
        balancingRemaining = adjustNumberOfBills(5, balancingRemaining);
        System.out.println("5: " +balancingRemaining);
        balancingRemaining = adjustNumberOfBills(2, balancingRemaining);
        System.out.println("2: " +balancingRemaining);
        balancingRemaining = adjustNumberOfBills(1, balancingRemaining);
        System.out.println("1: " +balancingRemaining);
        System.out.println(buildReturnString());
        if (balancingRemaining == 0) {
            return buildReturnString();
        } else {
            System.out.println(balancingRemaining);
            return "Insufficient money in drawer line 59";
        }
    }

    private List<Integer> convertStringToIntList(String inputString) {
        String[] stringList = inputString.split(" ");
        List<Integer> intList = new ArrayList<>();
        for (int i = 1; i < stringList.length; i++) {
            try {
                Integer newInt = Integer.parseInt(stringList[i]);
                intList.add(newInt);
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        }
        if (intList.size() == 5) {
            return intList;
        } else throw new RuntimeException("Must put in 5 numbers");
    }


    private void quit() {
        quit = true;
    }

    private Integer calculateSum() {
        sum = 0;
        for (Map.Entry<Integer, Integer> entry: cashDrawer.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    private String buildReturnString() {
        calculateSum();

        String string = "";
        StringBuilder returnVal = new StringBuilder(string);
        returnVal.append("$");
        returnVal.append(sum + " ");
        returnVal.append(cashDrawer.get(20) + " ");
        returnVal.append(cashDrawer.get(10) + " ");
        returnVal.append(cashDrawer.get(5) + " ");
        returnVal.append(cashDrawer.get(2) + " ");
        returnVal.append(cashDrawer.get(1) + " ");
        string = returnVal.toString();
        return string;
    }

    private Integer adjustNumberOfBills(int denominationValue, int balancingRemaining) {
        // if balance = 40
        int numberOfBillsPerDenomination = cashDrawer.get(denominationValue);
        if (balancingRemaining >= denominationValue) {
            for (int i = numberOfBillsPerDenomination; i > 0; i--) {
                balancingRemaining = balancingRemaining -  denominationValue;
                if (balancingRemaining >= 0) {
                    cashDrawer.put(denominationValue, i - 1);
                    return balancingRemaining;
                } else {

                }
            }
        }
        calculateSum();
        return balancingRemaining;

//        int numberOfBillsPerDenomination = cashDrawer.get(denominationValue);
//        if (balancingRemaining >= denominationValue) {
//            for (int i = numberOfBillsPerDenomination; i > 0; i--) {
////                balancingRemaining = balancingRemaining -  denominationValue;
//                if ( balancingRemaining -  denominationValue > 0) {
//                    cashDrawer.put(denominationValue, i);
//                    System.out.println("denominiaton: " + denominationValue + " , index: " + i);
//                    balancingRemaining = balancingRemaining -  denominationValue;
//
//                } else {
//                    return balancingRemaining;
//                }
//            }
//        }
//        calculateSum();
//        return balancingRemaining;

        //        double numberToSubtract = Math.floor(balancingRem/aining / billValue);
//        System.out.println("number to subtract: " + numberToSubtract + ", bill value " + billValue);
//        Integer numSubtract = (int) numberToSubtract;
//        System.out.println("bill value map: " + cashDrawer.get(billValue));
//            Integer newSum = cashDrawer.get(billValue) - numSubtract;
//            System.out.println("new sum: " + newSum);
//            if (cashDrawer.get(billValue) > newSum) {
//                cashDrawer.put(billValue, newSum);
//                balancingRemaining =  balancingRemaining - (billValue * newSum);
//                sum = calculateSum();
//                System.out.println(buildReturnString());
//                return balancingRemaining;
//            }

//        return balancingRemaining;
    }


    public static void main(String[] args) {
        System.out.println("ready");
        CashRegister cashRegister = new CashRegister();
        while(!cashRegister.quit) {
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
                    cashRegister.quit();
                } else {
                    System.out.println("Unknown action, please try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
