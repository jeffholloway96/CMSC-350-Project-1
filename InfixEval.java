/**InfixEval.java*/

import java.util.EmptyStackException;

import java.util.Stack;

import javax.swing.JOptionPane;

public class InfixEval

{

     Stack<String> operandsStk; //for operands

     Stack<Character> operatorsStk;//for operators +,-,*,/,(,)

     InfixEval()

     {

          //instantiate the stacks

          operandsStk=new Stack<String>();

          operatorsStk=new Stack<Character>();

     }

     //evaluates and returns the result

     public int evaluate(String exp) throws ArithmeticException

     {

          boolean fail=false;

          try

          {

              //tokenize the expression

          String tokens[]=exp.split(" ");

          for(int i=0;i<tokens.length;i++)

          {

              //if it is an operand push it onto the operand stack

              if(!tokens[i].equals("+")&& !tokens[i].equals("*")&&!tokens[i].equals("-")

                        &&!tokens[i].equals("/") && !tokens[i].equals("(")&&!tokens[i].equals(")"))

              {

                   operandsStk.push(tokens[i]);

              }

              //if it is a left parenthesis

              else if(tokens[i].equals("("))

              {

                   //push it onto the operator stack

                   operatorsStk.push((Character)tokens[i].charAt(0));

              }

              //it is a right parenthesis

              else if(tokens[i].equals(")"))

              {

                   //while top of the operator stack is not a left parenthesis   

                   while(operatorsStk.peek()!='(')

                   {

                        // pop two operands and an operator

                        int x=Integer.parseInt(operandsStk.pop());

                        int y=Integer.parseInt(operandsStk.pop());

                        char opr=operatorsStk.pop();

                        //    perform the calculation   

                        int result=0;

                        if (opr=='+')

                        {

                             result= y+x;

                             //push the result onto the operand stack    

                             operandsStk.push(result+"");

                        }

                        else if(opr=='-')

                        {

                             result= y-x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                        else if(opr=='*')

                        {

                             result= y*x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                        else if(opr=='/')

                        {   

                             result=y/x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                                 

                   }

                   //pop top of the operator stack and ignore it

                   operatorsStk.pop();

              }

              //if it is an operator

              else if(tokens[i].equals("+")||tokens[i].equals("-")

                        ||tokens[i].equals("*")||tokens[i].equals("/"))

              {

                   //while the operator stack is not empty and         

                   //the operator at the top of the stack has higher

                   //or the same precedence than the current operator

                   while(!operatorsStk.empty()&&hasHigherPrecedence(operatorsStk.peek(),tokens[i].charAt(0)))

                   {

                        //pop two operands

                        int x=Integer.parseInt(operandsStk.pop());

                        int y=Integer.parseInt(operandsStk.pop());

                        char opr=operatorsStk.pop();

                        //perform the calculation   

                        int result=0;

                        if (opr=='+')

                        {

                             result= y+x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                        else if(opr=='-')

                        {

                             result= y-x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                        else if(opr=='*')

                        {

                             result= y*x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                        else if(opr=='/')

                        {   

                             result=y/x;

                             //push the result onto the operand stack

                             operandsStk.push(result+"");

                        }

                   }

                  

                   operatorsStk.push(tokens[i].charAt(0));

              }

          }

          //while the operator stack is not empty

          while(!operatorsStk.empty())

          {

              // pop two operands and an operator

              int x=Integer.parseInt(operandsStk.pop());

              int y=Integer.parseInt(operandsStk.pop());

              char opr=operatorsStk.pop();

              //perform the calculation

              int result=0;

              if (opr=='+')

              {

                   result= y+x;

                   // push the result onto the operand stack

                   operandsStk.push(result+"");

              }

              else if(opr=='-')

              {

                   result= y-x;

                   // push the result onto the operand stack

                   operandsStk.push(result+"");

              }

              else if(opr=='*')

              {

                   result= y*x;

                   // push the result onto the operand stack

                   operandsStk.push(result+"");

              }

              else if(opr=='/')

              {   

                   result=y/x;

                   // push the result onto the operand stack

                   operandsStk.push(result+"");

              }

          }

          }

          catch(EmptyStackException e)

          {        

              //if an error occurs

              fail=true;

          }

          if(fail==false)

              //the final result is at the top of the operand stack and return it

              return Integer.parseInt(operandsStk.pop());

          else

              return -1;

     }

     //returns true, if the top has high precedence than current

     boolean hasHigherPrecedence(char top, char current)

     {

          int topPre=-1;

          int curPre=-1;

          if(top == '+' || top == '-')

          {

              topPre=0;

          }

          if(top == '*' || top == '/' || top== '%')

          {

              topPre=1;

          }

          if(current == '+' || current == '-')

          {

              curPre=0;

          }

          if(current == '*' || current == '/' )

          {

              curPre=1;

          }

          if(topPre>=curPre)

              return true;

          else

              return false;

     }

}