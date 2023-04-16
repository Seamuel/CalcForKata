import java.util.Scanner;
public class Calc {
    public static void main(String[] args){
        Scanner inTask = new Scanner(System.in);
        System.out.println("Введите пример:");
        String task = inTask.nextLine();
        System.out.println(calc(task));
    }
    public static String calc(String input){
        String myTask = input;
        String[] operandArray = myTask.split("[+*/-]",2);
        try {
            char operator = myTask.charAt(operandArray[0].length());
            if (operandArray[0].equals("")) {
                return "Ошибка: первый операнд не соответствует условию (положительные числа от 1 до 10)";
            } else {
                int[] operandInt = {0, 0};
                int countArabianOperand = 0;
                for (int i = 0; i < 2; i++) {
                    String operandStr = operandArray[i].trim();
                    try {
                        operandInt[i] = Integer.valueOf(operandStr);
                        if (operandInt[i]<1 || operandInt[i]>10) {
                            return "Ошибка: операнд не соответствет условию (от 1 до 10 арабскимил или римскими цифрами)";
                        }
                        countArabianOperand = countArabianOperand+1;
                    } catch (NumberFormatException e) {
                        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
                        for (int n=0; n<10; n++) {
                            if (operandStr.equals(roman[n])) {
                                operandInt[i] = n+1;
                            }
                        }
                        if (operandInt[i] == 0) {
                            return "Ошибка: операнд не соответствет условию (от 1 до 10 арабскими или римскими цифрами)";
                        }
                    }
                }
                int result = 0;
                switch (operator) {
                    case '+':
                        result = operandInt[0]+operandInt[1];
                        break;
                    case '-':
                        result = operandInt[0]-operandInt[1];
                        break;
                    case '*':
                        result = operandInt[0]*operandInt[1];
                        break;
                    case '/':
                        result = operandInt[0]/operandInt[1];
                }
                String resultStr = "";
                switch (countArabianOperand) {
                    case 0:
                        if (result<1) {
                            return "Ошибка: в римской системе счисления нет отрицательных числе";
                        } else {resultStr = "Ответ: "+toRoman(result);
                            break;
                        }
                    case 1:
                        resultStr = "Ошибка: используются одновременно разные системы счисления";
                        break;
                    case 2:
                        resultStr = "Ответ: "+String.valueOf(result);
                }
                return resultStr;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "Ошибка: пример не содержит допустимый оператор (+, -, *, .)";
        }
    }
    public static String toRoman(int input) {
        int arg = input, argCumulative = 0, k = 100;
        String result = "";
        for (int m=0; m<3; m++) {
            int argInterim = (arg-argCumulative)/k;
            argCumulative = argCumulative+argInterim*k;
            k = k/10;
            switch (argInterim) {
                case 1:
                    if (k == 10) {
                        result = result + "C";
                    }
                    if (k == 1) {
                        result = result + "X";
                    }
                    if (k == 0) {
                        result = result + "I";
                    }
                    break;
                case 2:
                    if (k == 1) {
                        result = result + "XX";
                    }
                    if (k == 0) {
                        result = result + "II";
                    }
                    break;
                case 3:
                    if (k == 1) {
                        result = result + "XXX";
                    }
                    if (k == 0) {
                        result = result + "III";
                    }
                    break;
                case 4:
                    if (k == 1) {
                        result = result + "XL";
                    }
                    if (k == 0) {
                        result = result + "IV";
                    }
                    break;
                case 5:
                    if (k == 1) {
                        result = result + "L";
                    }
                    if (k == 0) {
                        result = result + "V";
                    }
                    break;
                case 6:
                    if (k == 1) {
                        result = result + "LX";
                    }
                    if (k == 0) {
                        result = result + "VI";
                    }
                    break;
                case 7:
                    if (k == 1) {
                        result = result + "LXX";
                    }
                    if (k == 0) {
                        result = result + "VII";
                    }
                    break;
                case 8:
                    if (k == 1) {
                        result = result + "LXXX";
                    }
                    if (k == 0) {
                        result = result + "VIII";
                    }
                    break;
                case 9:
                    if (k == 1) {
                        result = result + "XC";
                    }
                    if (k == 0) {
                        result = result + "IX";
                    }
                    break;
                case 0:
                        result = result + "";
            }
        }
        return result;
    }
}