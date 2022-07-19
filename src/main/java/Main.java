import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int row;
    private static int colum;
   
    private static final String[][] draw = {{" ", "|", " ", "|", " "},
                                            {"-", "-", "-", "-", "-"}, 
                                            {" ", "|", " ", "|", " "},
                                            {"-", "-", "-", "-", "-"},
                                            {" ", "|", " ", "|", " "}};

    static ArrayList<Integer> usedNumber = new ArrayList<>();

    public static void main(String[] args) {
        if (introduction() == 1) {
            firsDrawing();
            playingWithUser();
        } else {
            firsDrawing();
            playingWithPc();
        }
    }

    public static void playingWithUser() {
        Scanner input = new Scanner(System.in);
        double i = 0.0;

        System.out.println("First Player enter number to place: ");
        int placement = input.nextInt();
        usedNumber.add(placement);
        processing(placement, "firstPlayer");

        System.out.println("Second Player enter number to place: ");
        int placement2 = input.nextInt();
        while (stepUsed(placement2)) {
            System.out.println("Number has already been entered. Please choose another number: ");
            placement2 = input.nextInt();
        }
        usedNumber.add(placement2);

        processing(placement2, "secondPlayer");

        while (i < 2.0) {
            //asking first user to add the placement
            System.out.println("First Player enter number to place: ");
            placement = input.nextInt();

            while (stepUsed(placement)) {
                System.out.println("Number has already been entered. Please choose another number: ");
                placement = input.nextInt();
            }
            usedNumber.add(placement);

            processing(placement, "firstPlayer");

            //if firstPlayer win break the loop
            if (winning("firstPlayer")) {
                break;
            }
            //breaking the loop when It's for the final time to select
            if (i == 1.5) {
                System.out.println("The match has been drawn.");
                break;
            }
            //asking second user to add the placement
            System.out.println("Second Player enter number to place: ");
            placement2 = input.nextInt();

            while (stepUsed(placement2)) {
                System.out.println("Number has already been entered. Please choose another number: ");
                placement2 = input.nextInt();
            }

            usedNumber.add(placement2);

            processing(placement2, "secondPlayer");

            //if secondPlayer win break the loop
            if (winning("secondPlayer")) {
                break;
            }
            i += 0.5;
        }
    }

    public static void playingWithPc() {

        Scanner input = new Scanner(System.in);
        double i = 0.0;

        System.out.println("Player enter number to place: ");
        int placement = input.nextInt();
        usedNumber.add(placement);
        processing(placement, "firstPlayer");

        System.out.println("PC enter number and chosen the place: ");
        Random randomNumber = new Random();
        int placement2 = randomNumber.nextInt(1, 10);
        while (stepUsed(placement2)) {
            placement2 = input.nextInt();
        }
        usedNumber.add(placement2);
        processing(placement2, "PC");

        while (i < 2.0) {
            System.out.println("First Player enter number to place: ");
            placement = input.nextInt();

            while (stepUsed(placement)) {
                System.out.println("Number has already been entered. Please choose another number: ");
                placement = input.nextInt();
            }
            usedNumber.add(placement);

            processing(placement, "firstPlayer");

            //if firstPlayer win break the loop
            if (winning("firstPlayer")) {
                break;
            }
            //breaking the loop when It's for the final time to select
            if (i == 1.5) {
                System.out.println("The match has been drawn.");
                break;
            }

            System.out.println("PC enter number and chosen the place: ");
            placement2 = randomNumber.nextInt(1, 10);

            while (stepUsed(placement2)) {
                placement2 = randomNumber.nextInt(1, 10);
            }
            usedNumber.add(placement2);
            processing(placement2, "PC");

            //if secondPlayer win break the loop
            if (winning("PC")) {
                break;
            }
            i += 0.5;
        }
    }

    public static void firsDrawing() {
        for (String[] strings : draw) {
            for (int j = 0; j < draw.length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }

    }

    public static void drawing(String sing) {
        draw[row][colum] = sing;

        for (String[] strings : draw) {
            for (int j = 0; j < draw.length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }

    public static int introduction() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to tic tac toe game......");
        System.out.println("""
                Please choose the option:\s
                1: Playing with users\s
                2: Playing with PC\s""");

        return input.nextInt();
    }

    public static void processing(int position, String player) {
        String sign = switch (player) {
            case "firstPlayer" ->
                    "O";
            case "secondPlayer", "PC" ->
                    "X";
            default ->
                    "";
        };
        switch (position) {
            case 1 -> {
                row = 0;
                colum = 0;
                drawing(sign);
            }
            case 2 -> {
                row = 0;
                colum = 2;
                drawing(sign);
            }
            case 3 -> {
                row = 0;
                colum = 4;
                drawing(sign);
            }
            case 4 -> {
                row = 2;
                colum = 0;
                drawing(sign);
            }
            case 5 -> {
                row = 2;
                colum = 2;
                drawing(sign);
            }
            case 6 -> {
                row = 2;
                colum = 4;
                drawing(sign);
            }
            case 7 -> {
                row = 4;
                colum = 0;
                drawing(sign);
            }
            case 8 -> {
                row = 4;
                colum = 2;
                drawing(sign);
            }
            case 9 -> {
                row = 4;
                colum = 4;
                drawing(sign);
            }
        }
    }

    public static boolean winning(String player) {
        String playerName = switch (player) {
            case "firstPlayer" ->
                    "First Player";
            case "secondPlayer" ->
                    "Second Player";
            case "PC" ->
                    "PC";
            default ->
                    "";
        };
        String sign = switch (player) {
            case "firstPlayer" ->
                    "O";
            case "secondPlayer", "PC" ->
                    "X";
            default ->
                    "";
        };
        if (draw[0][0].equals(sign) && draw[0][2].equals(sign) && draw[0][4].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[2][0].equals(sign) && draw[2][2].equals(sign) && draw[2][4].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[4][0].equals(sign) && draw[4][2].equals(sign) && draw[4][4].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[0][0].equals(sign) && draw[2][0].equals(sign) && draw[4][0].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[0][2].equals(sign) && draw[2][2].equals(sign) && draw[4][2].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[0][4].equals(sign) && draw[2][4].equals(sign) && draw[4][4].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[0][0].equals(sign) && draw[2][2].equals(sign) && draw[4][4].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        } else if (draw[0][4].equals(sign) && draw[2][2].equals(sign) && draw[4][0].equals(sign)) {
            System.out.println(playerName + " has won.");
            return true;
        }
        return false;
    }

    public static boolean stepUsed(int placement) {
        for (Integer integer : usedNumber) {
            if (integer.equals(placement)) {
                return true;
            }
        }
        return false;
    }
}

