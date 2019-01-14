package calculator;

import javax.swing.JFrame;

/**
 *
 * @author jdiaz
 */
public class Calculator {

    public static void main(String[] args) {
        BuildCalculator calculator = new BuildCalculator();
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
