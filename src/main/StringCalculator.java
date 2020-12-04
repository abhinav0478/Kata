package main;

import java.util.*;
class StringCalculator
{
    ArrayList<String> list = new ArrayList<String>();
    int count = 0;
    public static void main(String...s)
    {
        StringCalculator s1 = new StringCalculator();
        ArrayList<String> list = new ArrayList<String>();
        //Test cases to Execute
        int sum = s1.add("");
        System.out.println("sum of values for Empty String:"+sum);
        sum = s1.add("1,2");
        System.out.println("sum of values For two numbers:"+sum);

        sum = s1.add("1,2,3");
        System.out.println("sum of values For Unknown numbers:"+sum);

        sum = s1.add("1\n2,3");
        System.out.println("sum of values for \\n:"+sum);

        sum = s1.add("//;\n1;2");
        System.out.println("Sum of values For Different Delimeters: "+sum);




        sum = s1.add("1,10000");
        System.out.println("Sum of values For value more than 1000: "+sum);


        sum = s1.add("//[***]\n1***2***3");
        System.out.println("Sum of Values for Delimeters with Different length: "+sum);

        sum = s1.add("//[*][%]\n1*2%3");
        System.out.println("Sum of Values with Different Delimeters: "+sum);

        sum = s1.add("//[**][%%]\n1**2%%3");
        System.out.println("Sum of Values with Different Delimeters with Different length: "+sum);




        sum = s1.add("-1");
        System.out.println("Sum of values For Single Negatve: "+sum);

        sum = s1.add("-1,-32");
        System.out.println("Sum of values For more Negatve: "+sum);












        sum = s1.add("100");
        System.out.println("sum of values :"+sum);
        sum = s1.add("//[*][%]\n1*2%3");
        System.out.println("sum of values : "+sum);
        System.out.println("The number of times add method is called:"+ s1.count);
    }
    public int add(String numbers)
    {  //
        count = this.getCalledCount();
        int flag = 0;
        list.clear();
        ArrayList<Integer> negativenumberss = new ArrayList<Integer>();
        int sum = 0;
// Base condition for empty String and returning 0
        if(numbers.equals(""))
        {
            return sum;
        }
// In case of Custom Delimiter
        if((numbers.charAt(0))==('/'))
        {
            String customString = numbers.substring(2, numbers.indexOf("\n"));
            List<String> customDelimiters = null;
            if(customString.contains("[") && customString.contains("]")) {
                customDelimiters = new ArrayList<String>();
                String custom = "";
                for(int i=0;i<customString.length();i++) {

                    if(customString.charAt(i) == '[') {
                        custom = "";
                    }
                    else if(customString.charAt(i) == ']') {
                        customDelimiters.add(custom);
                    }
                    else {
                        custom+=customString.charAt(i);
                    }

                }
            }
            String customDelimeter = "";
            if(customDelimiters != null) {
                for(String customDelimiterNew : customDelimiters) {

                    for(int k = 0;k<customDelimiterNew.length();k++) {
                        customDelimeter+="\\"+customDelimiterNew.charAt(k);
                    }
                    customDelimeter+="|";

                }
                customDelimeter = customDelimeter.substring(0,customDelimeter.length()-1);



                    String realnumber = numbers.substring( numbers.indexOf("\n")+1);
                    String splitnumbers[] = realnumber.split(customDelimeter);
                    for(int i=0;i<splitnumbers.length;i++)
                    {
                        list.add(splitnumbers[i]);
                    }


            }
            else {

                String realnumber = numbers.substring( numbers.indexOf("\n")+1);
                String splitnumbers[] = realnumber.split(customString);
                for(int i=0;i<splitnumbers.length;i++)
                {
                    list.add(splitnumbers[i]);
                }

            }


            //customdelimiter = customdelimiter.substring(customdelimiter.indexOf("[")+1, customdelimiter.indexOf("]"));

        }
// Splitting the String on the basis of "," and "/n"
        else
        {
            String splitnumbers[] = numbers.split(",|\n");
            for(int i=0;i<splitnumbers.length;i++)
            {
                list.add(splitnumbers[i]);
            }}
        int number;
        for(int i=0;i<list.size();i++)
        {
            number = Integer.parseInt(list.get(i));
            if(number<0)
            {
                negativenumberss.add(number);
// flag will indicate that there is a negative number
// flag is 1 means it is a negative number
                flag = 1;
            }
            else
            {
// if value greater than 1000 then just ignore it
                if(number>1000)
                {
                    number = 0;
                }
                else
                {

                }
                sum+= number;
            }
        }
// in case of negative no. it will throw runtime exception
        if(flag==1)
        {
            throw new Negativenumbers("Negative numbers not allowed :",negativenumberss);
        }
        /*System.out.println("the values are :" + sum);*/
        return sum;
    }
    public int getCalledCount()
    {
        count++;
        return count;
    }
}
class Negativenumbers extends RuntimeException
{

    Negativenumbers(String statement,ArrayList<Integer> negativenumbers)
    {
        System.out.print(statement);
        for(int i =0;i<negativenumbers.size();i++)
        {
            System.out.print(negativenumbers.get(i)+"");
        }
    }
}

