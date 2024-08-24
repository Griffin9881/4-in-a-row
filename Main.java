import java.util.Scanner;

public class Main {
    private static String[][] board = {{"-","-","-","-","-","-","-"}, {"-","-","-","-","-","-","-"}, {"-","-","-","-","-","-","-"}, {"-","-","-","-","-","-","-"}, {"-","-","-","-","-","-","-"}, {"-","-","-","-","-","-","-"}};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int round = 1;
        String player;
        boolean end = false;
        while (!end) {
            if(round % 2 == 0) {
                player = "Player 2";
            } else {
                player = "Player 1";
            }
            printBoard();
            int answer = pickNumber(scanner, player);
            placePiece(answer, player);
            String winner = checkWinner();
            switch (winner) {
                case "X":
                    printBoard();
                    System.out.println("Player 1 Wins!");
                    end = true;
                    break;
                case "O":
                    printBoard();
                    System.out.println("Player 2 Wins!");
                    end = true;
                    break;
                default:
                    break;
            }
            round++;
        }
        scanner.close();
    }

    private static void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[5-i][j]);
            }
            System.out.println();
        }
    }

    private static int pickNumber(Scanner scanner, String player) {
        System.out.println(player + " Please pick a row to drop a piece in");
        while (!scanner.hasNextInt()) {
            System.out.println("Please select a number 1-6");
            scanner.nextLine();
        }
        int answer = scanner.nextInt();
        boolean zero = false;
        boolean one = false;
        boolean two = false;
        boolean three = false;
        boolean four = false; 
        boolean five = false;
        boolean six = false;

        for (int i = 0; i < board[5].length; i++) {
            if (board[5][i] != "-") {
                switch (i) {
                    case 0:
                        zero = true;
                        break;
                    case 1:
                        one = true;
                        break;
                    case 2:
                        two = true;
                        break;
                    case 3:
                        three = true;
                        break;
                    case 4:
                        four = true;
                        break;
                    case 5:
                        five = true;
                        break;
                    case 6:
                        six = true;
                        break;
                }
            }
        }

        while (answer < 0 || answer > 7 || (zero && answer == 1) || (one && answer == 2) || (two && answer == 3) || (three && answer == 4) || (four && answer == 5) || (five && answer == 6) || (six && answer == 7)) {
            System.out.println("Fail please enter a valid number");
            while (!scanner.hasNextInt()) {
                System.out.println("Please select a number 1-6");
                scanner.nextLine();
        }
            answer = scanner.nextInt();        }

        return answer;
    }

    private static void placePiece(int answer, String player) {
        boolean pieceplaced = false;
        if (player.equals("Player 1")) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (answer - 1 == j && board[i][j].equals("-") && !pieceplaced) {
                        board[i][j] = "X";
                        pieceplaced = true;
                    }
                }
            }
        }
        if (player.equals("Player 2")) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (answer - 1 == j && board[i][j].equals("-") && !pieceplaced) {
                        board[i][j] = "O";
                        pieceplaced = true;
                    }
                }
            }
        }
    }

    private static String checkHorazonalWin() {
        String substring;
        for (int i = 0; i < board.length; i++) {
            substring = board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][4] + board[i][5] + board[i][6];
            if (substring.contains("XXXX")) {
                return "X";
            } else if (substring.contains("OOOO")) {
                return "O";
            }
        }
        return "-";
    }

    private static String checkVerticalWin() {
        String substring;
        for (int i = 0; i < board.length; i++) {
            substring = board[0][i] + board[1][i] + board[2][i] + board[3][i] + board[4][i] + board[5][i]; 
            if (substring.contains("XXXX")) {
                return "X";
            } else if (substring.contains("OOOO")) {
                return "O";
            }
        }
        return "-";
    }

    private static String checkDiagonalLeftWin() {
        String subString;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                subString = board[j][i] + board[j+1][i+1] + board[j+2][i+2] + board[j+3][i+3];
                if (subString.equals("XXXX")) {
                    return "X";
                } else if (subString.contains("OOOO")) {
                    return "O";
                }
            }
        }
        return "-";
    }

    private static String checkDiagonalRightWin() {
        String subString;
        for (int j = 0; j < 3; j++) {
            for (int i = 6; i > 2; i--) {
                subString = board[j][i] + board[j+1][i-1] + board[j+2][i-2] + board[j+3][i-3];
                if (subString.equals("XXXX")) {
                    return "X";
                } else if (subString.contains("OOOO")) {
                    return "O";
                }
            }
        }
        return "-";
    }

    private static String checkWinner() {
        String checkHorazonalWin = checkHorazonalWin();
        String checkVerticalWin = checkVerticalWin();
        String checkDiagonalWinLeft = checkDiagonalLeftWin();
        String checkDiagonalWinRight = checkDiagonalRightWin();

        if (checkHorazonalWin.equals("X") || checkVerticalWin.equals("X") || checkDiagonalWinLeft.equals("X") || checkDiagonalWinRight.equals("X")) {
            return "X";
        } else if (checkHorazonalWin.equals("O") || checkVerticalWin.equals("O") || checkDiagonalWinLeft.equals("O") || checkDiagonalWinRight.equals("O")) {
            return "O";
        } else {
            return "-";
        }
    }
}