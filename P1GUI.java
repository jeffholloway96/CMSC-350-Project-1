/**P1GUI.java */

import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.EmptyStackException;

import java.util.Stack;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.SwingConstants;

public class P1GUI extends JFrame implements ActionListener

{

     JTextField userInput;

     JLabel inputDescLbl,resultLbl,helpLbl;

     JPanel inputPanel,resultPanel;

     JButton evlBtn;

     Stack<Object>stk;

     P1GUI()

     {

          //Make main GUI

          super("Evaluate Infix using stacks");

          inputPanel=new JPanel(new FlowLayout());

          resultPanel=new JPanel(new FlowLayout());

          setLayout(new GridLayout(2,1));

          userInput =new JTextField(20);

          helpLbl=new JLabel("Note: 1. Give a spaces ( 1 * 2 ) 2. Use only (, ), +, -, * , / ");

          inputDescLbl=new JLabel("Enter an airthmatic expression:");

          evlBtn=new JButton("Evaluate");

          evlBtn.addActionListener(this);

          resultLbl=new JLabel("Result:",SwingConstants.LEFT);

          add(inputPanel);

          add(resultPanel);

          inputPanel.add(inputDescLbl);

          inputPanel.add(userInput);

          inputPanel.add(helpLbl);

          inputPanel.add(evlBtn);

          resultPanel.add(resultLbl);

          stk=new Stack<Object>();

     }

    

     public static void main(String args[])

     {

          P1GUI gui=new P1GUI();

          gui.setVisible(true);

          gui.setSize(400,250);

         

     }

     //Returns false, if the expression is not balanced,

     //true, otherwise

     boolean isBalance(String exp)

     {

          int index = 0;

          boolean fail = false;

          int length=exp.length();

          try

          {

              while (index < length && !fail)

              {

                   char ch = exp.charAt(index);

                   switch(ch)

                   {

                        case '(':

                        stk.push( new Character(ch) );

                        break;

                        case ')':

                             char rightBrace=(char)stk.pop();

                             if(rightBrace!='(')

                                  fail=true;

                             break;

                        default:

                        //   skip all other items

                        break;

                   }

                   index++;

              }

          }

          catch(EmptyStackException e)

          {

              fail = true;

          }

          return stk.empty() && !fail;

     }

     //when button clicked

     public void actionPerformed(ActionEvent arg0)

     {

          InfixEval ife=new InfixEval();

              //copy the text box value into expression

              String expression=userInput.getText();

              //if the expression is balanced, call evaluate

              if(isBalance(expression))

              {

                   try

                   {

                        int result=ife.evaluate(expression);

                        //Display the result

                        if(result==-1)

                             resultLbl.setText("Result: Expresssion is not balanced.");

                        else

                             resultLbl.setText("Result: "+expression+" = "+result);

                   }

                   //if divide by zero exception is raised, show error messge

                   catch(ArithmeticException e)

                   {

                        JOptionPane.showMessageDialog(this, "Can't divid with zero",

                                 "Divid by zero error",

                                 JOptionPane.ERROR_MESSAGE);

                   }

              }

              //if the expression is not balanced, show error messgae

              else

                   resultLbl.setText("Result: Expresssion is not balanced.");

              userInput.setText("");

     }

}