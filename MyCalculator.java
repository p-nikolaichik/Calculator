package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCalculator
{
    public static void main(String[] args)
    {
        System.out.println("It is program My");
        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class MyFrame extends JFrame
{
    public MyFrame()
    {
        setTitle("Main frame");
        MyPanel panel = new MyPanel();
        Container pane = getContentPane();
        pane.add(panel);
        pack();
    }
}
class MyPanel extends JPanel
{
    private JTextField display;
    private JPanel panel;
    private boolean start;
    private String lastCommand;
    private double result;
    public MyPanel()
    {
        setLayout(new BorderLayout());
        start = true;
        result = 0;
        lastCommand = "=";
        display = new JTextField("0");
        add(display, BorderLayout.NORTH);
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        addButton("7", 0);
        addButton("8", 0);
        addButton("9", 0);
        addButton("/", 1);

        addButton("4", 0);
        addButton("5", 0);
        addButton("6", 0);
        addButton("*", 1);

        addButton("1", 0);
        addButton("2", 0);
        addButton("3", 0);
        addButton("-", 1);

        addButton("0", 0);
        addButton(".", 0);
        addButton("=", 1);
        addButton("+", 1);
        add(panel, BorderLayout.CENTER);
    }
    private void addButton(String s, int i)
    {
        JButton button = new JButton(s);
        if (i == 0)
        {
            button.addActionListener(new InsertAction());
        }
        if (i == 1)
        {
            button.addActionListener(new CommandAction());
        }
        panel.add(button);

    }
    private class InsertAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

            String input = event.getActionCommand();
            if (start == true)
            {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }
    private class CommandAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String command = event.getActionCommand();
            if (start)
            {
                if (command.equals("-"))
                {
                    display.setText(command);
                    start = false;
                } else lastCommand = command;
            }else
            {
                try {
                    calculate(Double.parseDouble(display.getText()));
                } catch (NumberFormatException e) {
                    JOptionPane.showConfirmDialog(null, "Пожалуйста введите число", "Неверный ввод", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                if (result % 1 == 0.0) {
                    int intResult = (int) result;
                    display.setText("" + intResult);
                } else display.setText("" + result);
                lastCommand = command;
                start = true;
            }
        }
    }

        public void calculate(double x)
        {
           if (lastCommand.equals("+")) result += x;
           else if (lastCommand.equals("-")) result -= x;
           else if (lastCommand.equals("/")) result /= x;
           else if (lastCommand.equals("*")) result *= x;
           else if (lastCommand.equals("=")) result = x;
        }
    }

