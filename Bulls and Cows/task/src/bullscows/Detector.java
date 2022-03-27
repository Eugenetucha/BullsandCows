package bullscows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Detector {
    String code;
    private final int length;
    private final boolean flagup;
    int length_sym;

    Detector(String code, int length, int length_sym, boolean flagup) {
        this.code = code;
        this.flagup = flagup;
        this.length = length;
        this.length_sym = length_sym - 11;
    }


    public StringBuilder uni(StringBuilder old, int oops) {
        int i;
        for (i = 0; i < oops - 1; i++) {
            for (int j = i + 1; j < oops; j++) {
                if (old.charAt(i) == old.charAt(j)) {
                    old.deleteCharAt(j);
                    --oops;
                    uni(old, oops);
                }
            }
        }
        ArrayList<Character> now = new ArrayList<>();
        if (old.length() != this.length) {
            char[] a = new char[10];
            a[0] = '0';
            a[1] = '1';
            a[2] = '2';
            a[3] = '3';
            a[4] = '4';
            a[5] = '5';
            a[6] = '6';
            a[7] = '7';
            a[8] = '8';
            a[9] = '9';
            boolean flag;
            for (i = 0; i < a.length; i++) {
                flag = false;
                for (int b = 0; b < old.length(); b++) {
                    if (a[i] == old.charAt(b)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    now.add(a[i]);
                }
            }
            for (int h = 0; h < length - old.length(); h++) {
                old.append(now.get(h));
            }
        }
        if (flagup) {
            return change_abc(old);
        } else {
            return old;
        }
    }

    public StringBuilder change_abc(StringBuilder old) {
        List<String> listOfCharacters = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        Collections.shuffle(listOfCharacters);
        Random random = new Random(1);
        int end = random.nextInt(length);
        int start = 0;
        StringBuilder pop = new StringBuilder();
        for (int i = start; i < end; i++) {
            pop.append(listOfCharacters.get(i));
        }
        String pop_1 = String.valueOf(pop);
        listOfCharacters.subList(start, end);
        old.replace(start, end, pop_1);
        return old;
    }

    public String change(String cod) {
        StringBuilder nov = new StringBuilder(String.valueOf(cod));
        if (length != nov.length()) {
            nov.delete(length, nov.length());
        }
        return String.valueOf(uni(nov, nov.length()));
    }
}
