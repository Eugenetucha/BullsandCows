package bullscows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        Scanner leave = new Scanner(System.in);
        int length = 0;
        int length_sym = 0;
        boolean game = false;
        System.out.println("Please, enter the secret code's length:");
        try {
            length = leave.nextInt();
        } catch (Exception e) {
            System.out.printf("Error: \"%s\"isn't a valid number.", leave.nextLine());
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        try {
            length_sym = leave.nextInt();
            GameEx.getEx(length, length_sym);
            game = true;
        } catch (GameException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.printf("Error: \"%s\"isn't a valid number.", leave.nextLine());
        }
        Random pseudo = new Random();
        StringBuilder b = new StringBuilder();
        while (b.length() < length) {
            int num = pseudo.nextInt(9) + 1;
            b.append(abs(num));
        }
        String cod = String.valueOf(b);
        List<String> listOfCharacters = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        boolean chOrAbc = length_sym > 10;
        Detector wow = new Detector(cod, length, length_sym, chOrAbc);
        if (game) {
            String oops = (wow.change(cod));
            boolean flag = false;
            Game n = new Game(oops, length);
            StringBuilder length_word = new StringBuilder();
            length_word.append("*".repeat(Math.max(0, length)));
            if (chOrAbc) {
                String last_simv = String.valueOf(listOfCharacters.get(length_sym - 11));
                System.out.printf("The secret is prepared: %s (0-9, a-%s). \n", length_word, last_simv);
            } else {
                System.out.printf("The secret is prepared: %s (0-%d).\n", length_word, length_sym - 1);
            }
            System.out.println("Okay, let's start a game!\n");
            while (!flag) {
                System.out.println(oops);
                flag = n.Turn();
            }
        } else {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
        }
    }
}


