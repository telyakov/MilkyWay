package milkyway.excel;

import java.util.Stack;

public class SumToString {

    private static enum Ranges {
        UNITS, DECADES, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS
    };

    private static Stack<ThreeChar> threeChars;

    private static class ThreeChar {

        char h, d, u;
        Ranges range;
    }

    public static String rublsToString(String s) {
        try {
            return digits2text(new Double(s));
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static String digits2text(Double d) {
        // TODO: Научиться парсить числа > 1000000
        String s = d.toString();
        int n = s.length() - s.lastIndexOf('.');
        if (d == null || d < 0.0 || d > 1000000.0 || n > 3) {
            System.out.println("Пока гарантированно преобразуются лишь числа от 0.0 до 1000000.0");
            return null;
        }
        if (n == 2) {
            s += "0";
        }
        String[] sa = s.split("\\.");
        threeChars = new Stack<ThreeChar>();
        threeChars.push(new ThreeChar());
        threeChars.peek().range = Ranges.UNITS;
        StringBuilder sb = new StringBuilder(sa[0]).reverse();
        for (int i = 0; i < sb.length(); i++) {
            if (i > 0 && i % 3 == 0) {
                threeChars.push(new ThreeChar());
            }
            ThreeChar threeChar = threeChars.peek();
            switch (i) {
                case 0:
                    threeChar.u = sb.charAt(i);
                    break;
                case 3:
                    threeChar.range = Ranges.THOUSANDS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 6:
                    threeChar.range = Ranges.MILLIONS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 9:
                    threeChar.range = Ranges.BILLIONS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 2:
                case 5:
                case 8:
                    threeChar.h = sb.charAt(i);
                    break;
                default:
                    threeChar.d = sb.charAt(i);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!threeChars.isEmpty()) {
            ThreeChar thch = threeChars.pop();
            if(thch.h == '0' && thch.d == '0' && thch.u == '0' && !threeChars.isEmpty()) continue;
            if (thch.h > 0) {
                result.append(getHundreds(thch.h));
                result.append(' ');
            }
            if (thch.d > '0') {
                if (thch.d > '1' || (thch.d == '1' && thch.u == '0')) {
                    result.append(getDecades(thch.d));
                } else if (thch.d > '0') {
                    result.append(getTeens(thch.d));
                }
                result.append(' ');
            }
            if (thch.u > '0' && thch.d != '1') {
                result.append(getUnits(thch.u, thch.range == Ranges.THOUSANDS));
                result.append(' ');
            }
            switch (thch.range) {
                case BILLIONS:
                    if (thch.d == '1' || thch.u == '0') {
                        result.append("миллиардов");
                    } else if (thch.u > '4') {
                        result.append("миллиардов");
                    } else if (thch.u > '1') {
                        result.append("миллиарда");
                    } else {
                        result.append("миллиард");
                    }
                    break;
                case MILLIONS:
                    if (thch.d == '1' || thch.u == '0') {
                        result.append("миллионов");
                    } else if (thch.u > '4') {
                        result.append("миллионов");
                    } else if (thch.u > '1') {
                        result.append("миллиона");
                    } else {
                        result.append("миллион");
                    }
                    break;
                case THOUSANDS:
                    if (thch.d == '1' || thch.u == '0') {
                        result.append("тысяч");
                    } else if (thch.u > '4') {
                        result.append("тысяч");
                    } else if (thch.u > '1') {
                        result.append("тысячи");
                    } else {
                        result.append("тысяча");
                    }
                    break;
                default:
                    result.append(')');
                    if (thch.d == '1' || thch.u == '0' || thch.u > '4') {
                        result.append("рублей");
                    } else if (thch.u > '1') {
                        result.append("рубля");
                    } else {
                        result.append("рубль");
                    }
            }
            result.append(' ');
        }



        result.append(sa[1] + ' ');
        switch (sa[1].charAt(1)) {
            case '1':
                result.append(sa[1].charAt(0) != '1' ? "копейка" : "копеек");
                break;
            case '2':
            case '3':
            case '4':
                result.append(sa[1].charAt(0) != '1' ? "копейки" : "копеек");
                break;
            default:
                result.append("копеек");
        }
        char first = Character.toUpperCase(result.charAt(0));
        result.setCharAt(0, first);
        return result.toString().replaceAll("null", "");
    }

    private static String getHundreds(char dig) {
        switch (dig) {
            case '1':
                return "сто";
            case '2':
                return "двести";
            case '3':
                return "триста";
            case '4':
                return "четыреста";
            case '5':
                return "пятьсот";
            case '6':
                return "шестсот";
            case '7':
                return "семсот";
            case '8':
                return "восемсот";
            case '9':
                return "девятьсот";
            default:
                return null;
        }
    }

    private static String getDecades(char dig) {
        switch (dig) {
            case '1':
                return "десять";
            case '2':
                return "двадцать";
            case '3':
                return "тридцать";
            case '4':
                return "сорок";
            case '5':
                return "пятьдесят";
            case '6':
                return "шестьдесят";
            case '7':
                return "семьдесят";
            case '8':
                return "восемьдесят";
            case '9':
                return "девяносто";
            default:
                return null;
        }
    }

    private static String getUnits(char dig, boolean female) {
        switch (dig) {
            case '1':
                return female ? "одна" : "один";
            case '2':
                return female ? "две" : "два";
            case '3':
                return "три";
            case '4':
                return "четыре";
            case '5':
                return "пять";
            case '6':
                return "шесть";
            case '7':
                return "семь";
            case '8':
                return "восемь";
            case '9':
                return "девять";
            default:
                return null;
        }
    }

    private static String getTeens(char dig) {
        String s = "";
        switch (dig) {
            case '1':
                s = "один";
                break;
            case '2':
                s = "две";
                break;
            case '3':
                s = "три";
                break;
            case '4':
                s = "четыр";
                break;
            case '5':
                s = "пят";
                break;
            case '6':
                s = "шест";
                break;
            case '7':
                s = "сем";
                break;
            case '8':
                s = "восем";
                break;
            case '9':
                s = "девят";
                break;
        }
        return s + "надцать";
    }

}
