package calculator;
/**
 *
 * @author jdiaz
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuildCalculator extends JFrame{

    private Color lightGrey = new Color(245,245,245);
    
    ButtonGridBuilder1 b1;
    ButtonGridBuilder2 b2;
    ButtonGridBuilder3 b3;
    
    JButton[] buttons1;
    JButton[] buttons2;
    JButton[] buttons3;
    
    JTextField t1;
    JTextField input;
    
    String op1, op2, operator;
    double operand1, operand2;
    double result;
    //boolean equalsPressed = false;
    
    int counter = 0;
    String temp;
    double tempNum;
    double negate;
    
    String memAdd;
    double mPlus;
    
    public BuildCalculator(){
        super();
        //calculator icon in image folder inside project folder
        ImageIcon icon = new ImageIcon("image/icon.png");
        
        //building the JFrame
        setLayout(null);
        setResizable(false);
        setLocation(500, 200);
        setSize(343, 404);//350 width, 400 height
        setTitle("Calculator");
        setIconImage(icon.getImage());
        getContentPane().setBackground(lightGrey);
        
        //building menubar for jframe
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");
        
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(help);
        
        // portion of menu strip
        JMenuItem options = new JMenuItem("Options");
        JMenuItem properties = new JMenuItem("Properties");
        JMenuItem exit = new JMenuItem("Exit");
        edit.add(options);
        edit.addSeparator();
        edit.add(properties);
        edit.addSeparator();
        edit.add(exit);
        
        exit.addActionListener(new exit());
        
        // portion of menu strip
        JMenuItem standard = new JMenuItem("Standard");
        JMenuItem scientific = new JMenuItem("Scientific");
        JMenuItem programmer = new JMenuItem("Programmer");
        view.add(standard);
        view.addSeparator();
        view.add(scientific);
        view.addSeparator();
        view.add(programmer);
        
        JMenuItem faq = new JMenuItem("FAQ");
        JMenuItem about = new JMenuItem("About");
        help.add(faq);
        help.addSeparator();
        help.add(about);
        
        //jpanel components
        buttons1 = new JButton[20];
        b1 = new ButtonGridBuilder1();
        
        buttons2 = new JButton[4];
        b2 = new ButtonGridBuilder2();
        
        buttons3 = new JButton[3];
        b3 = new ButtonGridBuilder3();
        
        //textfield for empty M
        t1 = new JTextField();
        t1.setBackground(lightGrey);
        t1.setLocation(9, 80); //x , y position
        t1.setSize(48, 40); //width and height
        t1.setEditable(false);
        t1.setFont(new Font("Arial", Font.PLAIN, 16));
        t1.setHorizontalAlignment(JTextField.CENTER);
        
        //text field for user input
        input = new JTextField();
        input.setEditable(false);
        input.setLocation(9, 20);
        input.setSize(320, 40);
        input.setVisible(true);
        input.setHorizontalAlignment(JTextField.RIGHT);
        input.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // major buttons
        buttons1[0].addActionListener(new input());     // 7
        buttons1[1].addActionListener(new input());     // 8
        buttons1[2].addActionListener(new input());     // 9
        buttons1[3].addActionListener(new quotient());  // /
        buttons1[4].addActionListener(new sqrt());      // square root
        buttons1[5].addActionListener(new input());     // 4
        buttons1[6].addActionListener(new input());     // 5
        buttons1[7].addActionListener(new input());     // 6
        buttons1[8].addActionListener(new product());   // * 
        buttons1[9].addActionListener(new percent());   // %
        buttons1[10].addActionListener(new input());    // 1
        buttons1[11].addActionListener(new input());    // 2
        buttons1[12].addActionListener(new input());    // 3
        buttons1[13].addActionListener(new subtract()); // -
        buttons1[14].addActionListener(new oneOverX()); // 1/x
        buttons1[15].addActionListener(new input());    // 0
        buttons1[16].addActionListener(new negate());   // +/-
        buttons1[17].addActionListener(new input());    // .
        buttons1[18].addActionListener(new add());      // + 
        buttons1[19].addActionListener(new equals());   // =
        
        // minor buttons
        buttons2[0].addActionListener(new memoryClear());  // MC
        buttons2[1].addActionListener(new memoryRecall()); // MR
        buttons2[2].addActionListener(new memoryStore());  // MS
        buttons2[3].addActionListener(new memoryAdd());    // M+
        
        // minor buttons
        buttons3[0].addActionListener(new input());        // backspace
        buttons3[1].addActionListener(new clearEntry());   // CE
        buttons3[2].addActionListener(new clear());        // C
        
        add(b1);
        add(b2);
        add(b3);
        add(t1);
        add(input);
        repaint();
    }
    
    public class memoryAdd implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("M+")){
                memAdd = input.getText();
                tempNum = Double.parseDouble(memAdd);
                mPlus = mPlus + tempNum;   
            }
        }
    }
    
    public class memoryRecall implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("MR")){
                input.setText(String.valueOf(mPlus));
            }
        }
    }
    
    public class memoryClear implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("MC")){
                mPlus = 0.0;
                t1.setText("");
            }
        }
    }
    
    public class memoryStore implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("MS")){
                t1.setText(String.valueOf(mPlus));
            }
        }
    }
    
    public class exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public class add implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("+")){
                op1 = input.getText();
                operand1 = Double.parseDouble(op1);
                System.out.println("Operand 1 is " + operand1);
                input.setText("");
                operator = "+";
            }
        }
    }
    
    public class subtract implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("-")){
                op1 = input.getText();
                operand1 = Double.parseDouble(op1);
                System.out.println("Operand 1 is " + operand1);
                input.setText("");
                operator = "-";
            }
        }
    }
    
    public class product implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("*")){
                op1 = input.getText();
                operand1 = Double.parseDouble(op1);
                System.out.println("Operand 1 is " + operand1);
                input.setText("");
                operator = "*";
            }
        }
    }
    
    public class quotient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("/")){
                op1 = input.getText();
                operand1 = Double.parseDouble(op1);
                System.out.println("Operand 1 is " + operand1);
                input.setText("");
                operator = "/";
            }
        }
    }
    
    public class sqrt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("sqrt")){
                temp = input.getText();
                tempNum = Double.parseDouble(temp);
                input.setText(String.valueOf(Math.sqrt(tempNum)) );
            }
          
        }
    }
    
    public class oneOverX implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("1/x")){
                temp = input.getText();
                tempNum = Double.parseDouble(temp);
                input.setText(String.valueOf(1/tempNum));
            }
        }
    }
    
    public class percent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("%")){
                temp = input.getText();
                tempNum = Double.parseDouble(temp);
                input.setText(String.valueOf(tempNum/100));
            }
        }
    }
    
    public class equals implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("=") && operator.equals("+")){
                op2 = input.getText();
                operand2 = Double.parseDouble(op2);
                System.out.println("Operand 1 is " + operand1);
                System.out.println("Operand 2 is " + operand2);
                result = operand1 + operand2;
                System.out.println("Sum = " + result);
                input.setText(String.valueOf(result));
            }
            
            if(e.getActionCommand().equals("=") && operator.equals("-")){
                op2 = input.getText();
                operand2 = Double.parseDouble(op2);
                System.out.println("Operand 1 is " + operand1);
                System.out.println("Operand 2 is " + operand2);
                result = operand1 - operand2;
                System.out.println("Difference = " + result);
                input.setText(String.valueOf(result));
            }
            
            if(e.getActionCommand().equals("=") && operator.equals("*")){
                op2 = input.getText();
                operand2 = Double.parseDouble(op2);
                System.out.println("Operand 1 is " + operand1);
                System.out.println("Operand 2 is " + operand2);
                result = operand1 * operand2;
                System.out.println("Product = " + result);
                input.setText(String.valueOf(result));
            }
            
            if(e.getActionCommand().equals("=") && operator.equals("/")){
                op2 = input.getText();
                operand2 = Double.parseDouble(op2);
                System.out.println("Operand 1 is " + operand1);
                System.out.println("Operand 2 is " + operand2);
                result = operand1 / operand2;
                System.out.println("Quotient = " + result);
                input.setText(String.valueOf(result));
            }
        }
    }
    
    public class clearEntry implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("CE")){
                op1 = "";
                op2 = "";
                operator = "";
                operand1 = 0.0; 
                operand2 = 0.0;
                result = 0.0;
                input.setText("");
            }
        }
    }
    
    public class clear implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("C")){
                input.setText("");
            }
        }
    }
    
    public class negate implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("+/-") && counter%2 == 0){
                temp = input.getText();
                tempNum = Double.parseDouble(temp);
                negate = -tempNum;
                System.out.println("Negate is " + negate);
                input.setText(String.valueOf(negate));
                counter++;
            }else if(e.getActionCommand().equals("+/-") && counter%2 == 1){
                temp = input.getText();
                tempNum = Double.parseDouble(temp);
                negate = -tempNum;
                System.out.println("Negate is " + negate);
                input.setText(String.valueOf(negate));
                counter++;
            }else{
                return;
            }
        }
    }
    
    public class input implements ActionListener{
            private int num = 0;
            private char period = '.';
            private String temporary = "";  
            
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Current button pressed is " + e.getActionCommand());
            
            if(e.getActionCommand().equals("0")){
                num = 0;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("1")){
                num = 1;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("2")){
                num = 2;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("3")){
                num = 3;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("4")){
                num = 4;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("5")){
                num = 5;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("6")){
                num = 6;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("7")){
                num = 7;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("8")){
                num = 8;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals("9")){
                num = 9;
                input.setText(input.getText() + num);
            }
            
            if(e.getActionCommand().equals(".")){
                input.setText(input.getText() + period);
            }
            
            if(e.getActionCommand().equals("Backspace")){
                temporary = input.getText();
                
                if(temporary.length() != 0){
                    input.setText(temporary.substring(0, temporary.length()-1));
                }
            }
            repaint();
        }
    }
    public class ButtonGridBuilder1 extends JPanel{
        
        public ButtonGridBuilder1(){
            setLayout(new GridLayout(4, 5, 4, 4) );
            setBounds(70, 130, 257, 215);//x, y, width, height
            setBackground(lightGrey);
            
            buttons1[0] = new JButton("7");
            buttons1[0].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[0].setForeground(Color.BLUE);
            buttons1[0].setBackground(lightGrey);
            buttons1[0].setMargin(new Insets(0,0,0,0));
            add(buttons1[0]);
            
            buttons1[1] = new JButton("8");
            buttons1[1].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[1].setForeground(Color.BLUE);
            buttons1[1].setBackground(lightGrey);
            buttons1[1].setMargin(new Insets(0,0,0,0));
            add(buttons1[1]);
            
            buttons1[2] = new JButton("9");
            buttons1[2].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[2].setForeground(Color.BLUE);
            buttons1[2].setBackground(lightGrey);
            buttons1[2].setMargin(new Insets(0,0,0,0));
            add(buttons1[2]);
            
            buttons1[3] = new JButton("/");
            buttons1[3].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[3].setForeground(Color.RED);
            buttons1[3].setBackground(lightGrey);
            buttons1[3].setMargin(new Insets(0,0,0,0));
            add(buttons1[3]);
            
            buttons1[4] = new JButton("sqrt");
            buttons1[4].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[4].setForeground(Color.BLUE);
            buttons1[4].setBackground(lightGrey);
            buttons1[4].setMargin(new Insets(0,0,0,0));
            add(buttons1[4]);
            
            buttons1[5] = new JButton("4");
            buttons1[5].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[5].setForeground(Color.BLUE);
            buttons1[5].setBackground(lightGrey);
            buttons1[5].setMargin(new Insets(0,0,0,0));
            add(buttons1[5]);
            
            buttons1[6] = new JButton("5");
            buttons1[6].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[6].setForeground(Color.BLUE);
            buttons1[6].setBackground(lightGrey);
            buttons1[6].setMargin(new Insets(0,0,0,0));
            add(buttons1[6]);
            
            buttons1[7] = new JButton("6");
            buttons1[7].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[7].setForeground(Color.BLUE);
            buttons1[7].setBackground(lightGrey);
            buttons1[7].setMargin(new Insets(0,0,0,0));
            add(buttons1[7]);
            
            buttons1[8] = new JButton("*");
            buttons1[8].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[8].setForeground(Color.RED);
            buttons1[8].setBackground(lightGrey);
            buttons1[8].setMargin(new Insets(0,0,0,0));
            add(buttons1[8]);
            
            buttons1[9] = new JButton("%");
            buttons1[9].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[9].setForeground(Color.BLUE);
            buttons1[9].setBackground(lightGrey);
            buttons1[9].setMargin(new Insets(0,0,0,0));
            add(buttons1[9]);
            
            buttons1[10] = new JButton("1");
            buttons1[10].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[10].setForeground(Color.BLUE);
            buttons1[10].setBackground(lightGrey);
            buttons1[10].setMargin(new Insets(0,0,0,0));
            add(buttons1[10]);
            
            buttons1[11] = new JButton("2");
            buttons1[11].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[11].setForeground(Color.BLUE);
            buttons1[11].setBackground(lightGrey);
            buttons1[11].setMargin(new Insets(0,0,0,0));
            add(buttons1[11]);
            
            buttons1[12] = new JButton("3");
            buttons1[12].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[12].setForeground(Color.BLUE);
            buttons1[12].setBackground(lightGrey);
            buttons1[12].setMargin(new Insets(0,0,0,0));
            add(buttons1[12]);
            
            buttons1[13] = new JButton("-");
            buttons1[13].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[13].setForeground(Color.RED);
            buttons1[13].setBackground(lightGrey);
            buttons1[13].setMargin(new Insets(0,0,0,0));
            add(buttons1[13]);
            
            buttons1[14] = new JButton("1/x");
            buttons1[14].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[14].setForeground(Color.BLUE);
            buttons1[14].setBackground(lightGrey);
            buttons1[14].setMargin(new Insets(0,0,0,0));
            add(buttons1[14]);
            
            buttons1[15] = new JButton("0");
            buttons1[15].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[15].setForeground(Color.BLUE);
            buttons1[15].setBackground(lightGrey);
            buttons1[15].setMargin(new Insets(0,0,0,0));
            add(buttons1[15]);
            
            buttons1[16] = new JButton("+/-");
            buttons1[16].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[16].setForeground(Color.BLUE);
            buttons1[16].setBackground(lightGrey);
            buttons1[16].setMargin(new Insets(0,0,0,0));
            add(buttons1[16]);
            
            buttons1[17] = new JButton(".");
            buttons1[17].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[17].setForeground(Color.BLUE);
            buttons1[17].setBackground(lightGrey);
            buttons1[17].setMargin(new Insets(0,0,0,0));
            add(buttons1[17]);
            
            buttons1[18] = new JButton("+");
            buttons1[18].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[18].setForeground(Color.RED);
            buttons1[18].setBackground(lightGrey);
            buttons1[18].setMargin(new Insets(0,0,0,0));
            add(buttons1[18]);
            
            buttons1[19] = new JButton("=");
            buttons1[19].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons1[19].setForeground(Color.RED);
            buttons1[19].setBackground(lightGrey);
            buttons1[19].setMargin(new Insets(0,0,0,0));
            add(buttons1[19]);
        }
    }
    
    public class ButtonGridBuilder2 extends JPanel{
        public ButtonGridBuilder2(){
            setLayout(new GridLayout(4, 1, 4, 4) );
            setBounds(9, 130, 48, 215);//x, y, width, height
            setBackground(lightGrey);
            
            buttons2[0] = new JButton("MC");
            buttons2[0].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons2[0].setForeground(Color.RED);
            buttons2[0].setBackground(lightGrey);
            buttons2[0].setMargin(new Insets(0,0,0,0));
            add(buttons2[0]);
            
            buttons2[1] = new JButton("MR");
            buttons2[1].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons2[1].setForeground(Color.RED);
            buttons2[1].setBackground(lightGrey);
            buttons2[1].setMargin(new Insets(0,0,0,0));
            add(buttons2[1]);
            
            buttons2[2] = new JButton("MS");
            buttons2[2].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons2[2].setForeground(Color.RED);
            buttons2[2].setBackground(lightGrey);
            buttons2[2].setMargin(new Insets(0,0,0,0));
            add(buttons2[2]);
            
            buttons2[3] = new JButton("M+");
            buttons2[3].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons2[3].setForeground(Color.RED);
            buttons2[3].setBackground(lightGrey);
            buttons2[3].setMargin(new Insets(0,0,0,0));
            add(buttons2[3]);
        }
    }
    
    public class ButtonGridBuilder3 extends JPanel{
        
        public ButtonGridBuilder3(){
            setLayout(new GridLayout(0, 3, 4, 4) );
            setBounds(70, 80, 257, 40);//x, y, width, height
            setBackground(lightGrey);
            
            buttons3[0] = new JButton("Backspace");
            buttons3[0].setFont(new Font("Arial", Font.PLAIN, 15));
            buttons3[0].setForeground(Color.RED);
            buttons3[0].setBackground(lightGrey);
            buttons3[0].setMargin(new Insets(0,0,0,0));
            
            add(buttons3[0]);
            
            buttons3[1] = new JButton("CE");
            buttons3[1].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons3[1].setForeground(Color.RED);
            buttons3[1].setBackground(lightGrey);
            buttons3[1].setMargin(new Insets(0,0,0,0));
            add(buttons3[1]);
            
            buttons3[2] = new JButton("C");
            buttons3[2].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons3[2].setForeground(Color.RED);
            buttons3[2].setBackground(lightGrey);
            buttons3[2].setMargin(new Insets(0,0,0,0));
            add(buttons3[2]);
        }
    }   
}