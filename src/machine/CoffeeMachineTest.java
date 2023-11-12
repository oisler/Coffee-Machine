package machine;

import machine.data.CoffeeMachine;
import machine.enums.MainAction;

public class CoffeeMachineTest {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        MainAction mainAction;
        do {
            mainAction = coffeeMachine.handleMainAction();
        } while (mainAction != MainAction.EXIT);
    }
}
