package bullscows;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    String code;
    private final int length;
    private int turn = 0;

    Game(String code, int length) {
        this.code = code;
        this.length = length;
    }

    public Boolean Turn() {
        Scanner in = new Scanner(System.in);
        System.out.printf("Turn %d \n", ++turn);
        String str = in.nextLine();
        return rules(str);
    }

    private Boolean rules(String codes) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(codes.charAt(i), code.charAt(i))) {
                count++;
            }
        }
        print(codes);
        return count == length;
    }

    public void print(String codes) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < length; i++) {

            for (int j = 0; j < length; j++) {

                if (Objects.equals(code.charAt(i), codes.charAt(j))) {
                    ++cows;
                    if (Objects.equals(code.charAt(j), codes.charAt(j))) {
                        --cows;
                        ++bulls;
                    }
                }
            }
        }
        if (bulls == 0 && cows != 0) {
            System.out.printf("Grade: %d cow(s)\n", cows);
        } else if (cows == 0 && bulls != 0) {
            System.out.printf("Grade: %d bull(s).\n", bulls);
            System.out.println("Congratulations! You guessed the secret code.\n");
        } else if (bulls != 0 && cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bulls, cows);
        } else {
            System.out.print("Grade: None.\n");
        }
    }
}
