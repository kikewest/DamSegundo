package Calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class a extends JFrame implements ActionListener {
	private JTextField inputField;
    private JLabel expressionLabel;
    private JLabel resultLabel;
    private JPanel buttonsPanel;
    private double num1;
    private String operator;
    private String input = "";
    public a() {
        setTitle("Calculator");
        setLocationRelativeTo(null);
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        expressionLabel = new JLabel("");
        expressionLabel.setHorizontalAlignment(JLabel.RIGHT);
        labelsPanel.add(expressionLabel);
        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.RIGHT);
        labelsPanel.add(resultLabel);
        getContentPane().add(labelsPanel, BorderLayout.NORTH);
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        getContentPane().add(inputField, BorderLayout.CENTER);
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 10, 10));
        addButton("CE");
        addButton("C");
        addButton("\u221A");
        addButton("/");
        addButton("7");
        addButton("8");
        addButton("9");
        addButton("x");
        addButton("4");
        addButton("5");
        addButton("6");
        addButton("-");
        addButton("1");
        addButton("2");
        addButton("3");
        addButton("+");
        addButton("0");
        addButton(".");
        addButton("<-");
        addButton("=");
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void addButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(this);
        buttonsPanel.add(button);
    }

    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();
        if (command.equals("CE")) {
            input = "";
            num1 = 0;
            operator = "";
            inputField.setText("");
            expressionLabel.setText("");
            resultLabel.setText("");
        } else if (command.equals("C")) {
            input = "";
            inputField.setText("");
        } else if (command.equals("\u221A")) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                double result = Math.sqrt(num1);
                resultLabel.setText("= " + result);
                input = String.valueOf(result);
                expressionLabel.setText("\u221A(" + num1 + ")");
                inputField.setText("");
            }
        } else if (command.equals("=") && !input.isEmpty() && !operator.isEmpty()) {
            double num2 = Double.parseDouble(input);
            double result = performOperation(num1, num2, operator);
            resultLabel.setText("= " + result);
            expressionLabel.setText(num1 + " " + operator + " " + num2);
            input = String.valueOf(result);
            inputField.setText("");
            num1 = result;
            operator = "";
        } else if (command.equals("<-")) {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                inputField.setText(input);
            }
        } else if (command.matches("[0-9]+") || command.equals(".")) {
            input += command;
            inputField.setText(input);
        } else if (command.equals("+") || command.equals("-") || command.equals("x") || command.equals("/")) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                operator = command;
                expressionLabel.setText(num1 + " " + operator);
                inputField.setText("");
                input = "";
            }
        }
    }

    private double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN;
                }
            default:
                return num2;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new a());
    }
}