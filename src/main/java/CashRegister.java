import java.util.*;

public class CashRegister {
    private Map<Integer, Integer> cashDrawer = new HashMap<>();
    private Integer sum = 0;

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    private boolean quit;

    protected CashRegister() {
        cashDrawer.put(20, 0);
        cashDrawer.put(10, 0);
        cashDrawer.put(5, 0);
        cashDrawer.put(2, 0);
        cashDrawer.put(1, 0);
    }

    public String show() {
        return buildReturnString();
    }

    public String put(String command) {
        List<Integer> quantities = convertStringToIntList(command);
        cashDrawer.put(20, cashDrawer.get(20) + quantities.get(0));
        cashDrawer.put(10, cashDrawer.get(10) + quantities.get(1));
        cashDrawer.put(5, cashDrawer.get(5) + quantities.get(2));
        cashDrawer.put(2, cashDrawer.get(2) + quantities.get(3));
        cashDrawer.put(1, cashDrawer.get(1) + quantities.get(4));
        return buildReturnString();
    }

    public String take(String command) {
        List<Integer> quantities = convertStringToIntList(command);
        cashDrawer.put(20, cashDrawer.get(20) - quantities.get(0));
        cashDrawer.put(10, cashDrawer.get(10) - quantities.get(1));
        cashDrawer.put(5, cashDrawer.get(5) - quantities.get(2));
        cashDrawer.put(2, cashDrawer.get(2) - quantities.get(3));
        cashDrawer.put(1, cashDrawer.get(1) - quantities.get(4));
        return buildReturnString();
    }

    public String change(String command) {
        Map<Integer, Integer> tempCashDrawer = cashDrawer;

        String[] changeArray = command.split(" ");
        Integer changeAmount = Integer.parseInt(changeArray[1]);
        Integer balancingRemaining = changeAmount;
        if (changeAmount >  sum) {
            return "Not enough money in drawer line 49";
        }

        balancingRemaining = adjustNumberOfBills(20, balancingRemaining);
        balancingRemaining = adjustNumberOfBills(10, balancingRemaining);
        balancingRemaining = adjustNumberOfBills(5, balancingRemaining);
        balancingRemaining = adjustNumberOfBills(2, balancingRemaining);
        balancingRemaining = adjustNumberOfBills(1, balancingRemaining);
        if (balancingRemaining == 0) {
            return buildReturnString();
        } else {
            cashDrawer = new HashMap<>();
            cashDrawer = tempCashDrawer;

            buildReturnString();
            return "sorry";
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
        int numberOfBillsPerDenomination = cashDrawer.get(denominationValue);
        if (balancingRemaining >= denominationValue) {
            for (int i = numberOfBillsPerDenomination; i > 0; i--) {
                if (balancingRemaining -  denominationValue >= 0) {
                    balancingRemaining = balancingRemaining -  denominationValue;
                    cashDrawer.put(denominationValue, i - 1);
                } else {
                    return balancingRemaining;
                }
            }
        }
        calculateSum();
        return balancingRemaining;
    }


}