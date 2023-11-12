package machine.data;

import machine.data.Cappuccino;
import machine.data.Coffee;
import machine.data.Espresso;
import machine.data.Latte;
import machine.enums.CoffeeAction;
import machine.enums.MainAction;

import java.util.Scanner;

import static machine.enums.MainAction.*;

public final class CoffeeMachine {

    private int mlWater;
    private int mlMilk;
    private int gBeans;
    private int cups;
    private int money;

    private static final Scanner scanner = new Scanner(System.in);

    public CoffeeMachine() {
        this.mlWater = 400;
        this.mlMilk = 540;
        this.gBeans = 120;
        this.cups = 9;
        this.money = 550;
    }

    public void printState() {
        System.out.println();
        System.out.printf("""
                The coffee coffeeMachineSimulator has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                                
                """, this.mlWater, this.mlMilk, this.gBeans, this.cups, this.money);
    }

    public MainAction handleMainAction() {
        System.out.printf("Write action (%s, %s, %s, %s, %s):\n", BUY.label, FILL.label, TAKE.label, REMAINING.label, EXIT.label);
        String userInput = scanner.next();
        if (BUY.label.equals(userInput)) {
            System.out.printf("What do you want to buy? 1 - %s, 2 - %s, 3 - %s, 4 - %s:\n",
                    CoffeeAction.ESPRESSO.toString().toLowerCase(),
                    CoffeeAction.LATTE.toString().toLowerCase(),
                    CoffeeAction.CAPPUCCINO.toString().toLowerCase(),
                    CoffeeAction.BACK.toString().toLowerCase());
            makeCoffee();
            return BUY;
        } else if (FILL.label.equals(userInput)) {
            fill();
            return FILL;
        } else if (TAKE.label.equals(userInput)) {
            takeMoney();
            return TAKE;
        } else if (REMAINING.label.equals(userInput)) {
            printState();
            return REMAINING;
        } else if (EXIT.label.equals(userInput)) {
            return EXIT;
        } else {
            throw new RuntimeException();
        }
    }

    private void makeCoffee() {
        String userInput = scanner.next();
        if (CoffeeAction.BACK.toString().toLowerCase().equals(userInput)) {
            return;
        }

        Coffee coffee = switch (Integer.parseInt(userInput)) {
            case 1 -> new Espresso();
            case 2 -> new Latte();
            case 3 -> new Cappuccino();
            default -> throw new RuntimeException();
        };

        if (!hasEnoughResources(coffee)) {
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");
        this.mlWater -= coffee.getWater();
        this.mlMilk -= coffee.getMilk();
        this.gBeans -= coffee.getBeans();
        this.cups--;
        this.money += coffee.getPrice();
    }

    private void fill() {
        System.out.println("Write how many ml of water you want to add:\n");
        this.mlWater += Integer.parseInt(scanner.next());
        System.out.println("Write how many ml of milk you want to add:\n");
        this.mlMilk += Integer.parseInt(scanner.next());
        System.out.println("Write how many grams of coffee beans you want to add:\n");
        this.gBeans += Integer.parseInt(scanner.next());
        System.out.println("Write how many disposable cups you want to add:\n");
        this.cups += Integer.parseInt(scanner.next());
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d", this.money);
        this.money = 0;
    }

    private boolean hasEnoughResources(Coffee coffee) {
        if (this.mlWater < coffee.getWater()) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (this.mlMilk < coffee.getMilk()) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (this.gBeans < coffee.getBeans()) {
            System.out.println("Sorry, not enough beans!");
            return false;
        }
        if (this.cups == 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }

        return true;
    }
}
