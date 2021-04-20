package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayervsComputer {
    int boardSize;
    String[][] board;
    boolean continuity = true;
    boolean isTie = false;
    String typeP;
    String typeC;

    public PlayervsComputer(int boardSize) {
        this.boardSize = boardSize - 1;
    }

    public void createBoard() {
        String[][] board = new String[boardSize + 1][boardSize + 1];
        for (int i = 0; i <= boardSize; i++) {
            for (int l = 0; l <= boardSize; l++) {
                board[i][l] = "_";
            }
        }
        this.board = board;
        board();
    }

    public void board() {
        for (int i = 0; i <= boardSize; i++) {
            for (int n = 0; n <= boardSize; n++) {
                System.out.print(board[i][n] + "|");
            }
            System.out.println();
        }
    }

    public void game() {
        createBoard();
        Player1 player1 = new Player1("O");
        Computer computer = new Computer("X");
        GameWinCheck gameWinCheck = new GameWinCheck();
        player1.move();
        while(true) {
            int random1 = (int) (Math.random() * (boardSize + 1));
            int random2 = (int) (Math.random() * (boardSize + 1));
            if(board[random1][random2].equals("_")){
                board[random1][random2] = typeC;
                board();
                break;
            }
        }
        try {
            while (true) {
                player1.move();
                gameWinCheck.allInOneGameWinCheck();
                if (!continuity) {
                    System.out.println("Player 1 Won!");
                    board();
                    break;
                }
                gameTieCheck();
                if (isTie) {
                    System.out.println("Tie Game.No Winners");
                    board();
                    break;
                }
                board();
                computer.allInOneMove();
                gameWinCheck.allInOneGameWinCheck();
                if (!continuity) {
                    System.out.println("Player 2 Won!");
                    board();
                    break;
                }
                gameTieCheck();
                if (isTie) {
                    System.out.println("Tie Game.No Winners");
                    board();
                    break;
                }
                board();
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong.Shutting down");
        }
    }

    public class Player1 {

        public Player1(String typePlayer) {
            typeP = typePlayer;

        }

        public void move() {
            boolean carryOn = true;
            System.out.println("Player 1 Enter row number :");
            Scanner row = new Scanner(System.in);
            int rowInt = row.nextInt() - 1;
            if (rowInt > boardSize || rowInt < 0) {
                System.out.println("Invalid row number.Start over.");
                carryOn = false;
                move();
            }
            if (carryOn) {
                System.out.println("Player 1 Enter column number :");
                Scanner column = new Scanner(System.in);
                int columnInt = column.nextInt() - 1;
                if (columnInt > boardSize || columnInt < 0) {
                    System.out.println("Invalid column number.Start over.");
                    carryOn = false;
                    move();
                }
                if (carryOn) {
                    try {
                        if (board[rowInt][columnInt].equals("_")) {
                            board[rowInt][columnInt] = typeP;
                        } else {
                            System.out.println("That location is filled");
                            move();
                        }
                    } catch (Exception exception) {
                        System.out.println("Something is wrong.Start over");
                        move();
                    }
                }
            }

        }
    }

    public class Computer {

        public Computer(String typeComputer) {
            typeC = typeComputer;
        }

        boolean checkSingleMoveForWin = false;
        boolean isMoveMade = false;

        public void rowSingleMoveForWinCheck() {
            for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                boolean containsTypeP = false;
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][j].equals(typeP)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                    for (int m = j; m <= boardSize; m++) {
                        if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals("_")) {
                            board[i][j] = typeC;
                            isMoveMade = true;
                            checkSingleMoveForWin = true;
                            break;
                        }
                    }
                }
            }

        }

        public void columnSingleMoveForWinCheck() {
            for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                boolean containsTypeP = false;
                for (int j = 0; j <= boardSize; j++) {
                    if (board[j][i].equals(typeP)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                    for (int m = j; m <= boardSize; m++) {
                        if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals("_")) {
                            board[j][i] = typeC;
                            isMoveMade = true;
                            checkSingleMoveForWin = true;
                            break;
                        }
                    }
                }
            }

        }

        public void leftDiagonalSingleMoveForWinCheck() {
            boolean containsTypeP = false;
            for (int i = 0; i <= boardSize; i++) {
                if (board[i][i].equals(typeP)) {
                    containsTypeP = true;
                    break;
                }
            }
            int counter = 0;
            for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                for (int j = i; j <= boardSize; j++) {
                    if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC)) {
                        counter += 1;
                    }
                }
            }
            if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals("_")) {
                        board[i][i] = typeC;
                        isMoveMade = true;
                        checkSingleMoveForWin = true;
                        break;
                    }
                }
            }

        }

        public void rightDiagonalSingleMoveForWinCheck() {
            boolean containsTypeP = false;
            for (int i = 0; i <= boardSize; i++) {
                if (board[i][boardSize - i].equals(typeP)) {
                    containsTypeP = true;
                    break;
                }
            }
            int counter = 0;
            for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                for (int j = i; j <= boardSize; j++) {
                    if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC)) {
                        counter += 1;
                    }
                }
            }
            if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals("_")) {
                        board[i][boardSize - i] = typeC;
                        isMoveMade = true;
                        break;
                    }
                }
            }
        }

        public void rowPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeC = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals(typeC)) {
                            containsTypeC = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeP)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                        for (int j = 0; j <= boardSize; j++) {
                            if (board[i][j].equals("_")) {
                                board[i][j] = typeC;
                                isMoveMade = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void columnPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeC = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals(typeC)) {
                            containsTypeC = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeP)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                        for (int j = 0; j <= boardSize; j++) {
                            if (board[j][i].equals("_")) {
                                board[j][i] = typeC;
                                isMoveMade = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void leftDiagonalPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                boolean containsTypeC = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals(typeC)) {
                        containsTypeC = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeP)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int i = 0; i <= boardSize; i++) {
                        if (board[i][i].equals("_")) {
                            board[i][i] = typeC;
                            isMoveMade = true;
                            break;
                        }
                    }
                }
            }
        }

        public void rightDiagonalPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                boolean containsTypeC = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals(typeC)) {
                        containsTypeC = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeP)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int i = 0; i <= boardSize; i++) {
                        if (board[i][boardSize - i].equals("_")) {
                            board[i][boardSize - i] = typeC;
                            isMoveMade = true;
                            break;
                        }
                    }
                }
            }
        }

        public class TryingToWin {
            List<Integer> highestCounter = new ArrayList<>();
            List<String> highestIndexInfo = new ArrayList<>();
            List<Integer> highestLocation = new ArrayList<>();

            public void findHighestOfRows() {
                highestCounter.add(0);
                highestLocation.add(0);
                highestIndexInfo.add("Empty");
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeP = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals(typeP)) {
                            containsTypeP = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter >= highestCounter.get(0) && !containsTypeP) {
                        highestCounter.set(0, counter);
                        highestIndexInfo.set(0, "Row");
                        highestLocation.set(0, i);
                    }
                }
            }

            public void findHighestOfColumns() {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeP = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals(typeP)) {
                            containsTypeP = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter >= highestCounter.get(0) && !containsTypeP) {
                        highestCounter.set(0, counter);
                        highestIndexInfo.set(0, "Col");
                        highestLocation.set(0, i);
                    }
                }
            }

            public void findLeftDiagonal() {
                boolean containsTypeP = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals(typeP)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC)) {
                            counter += 1;
                        }
                    }
                }
                if (counter >= highestCounter.get(0) && !containsTypeP) {
                    highestCounter.set(0, counter);
                    highestIndexInfo.set(0, "LeftDiagonal");
                }
            }

            public void findRightDiagonal() {
                boolean containsTypeP = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals(typeP)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC)) {
                            counter += 1;
                        }
                    }
                }
                if (counter >= highestCounter.get(0) && !containsTypeP) {
                    highestCounter.set(0, counter);
                    highestIndexInfo.set(0, "RightDiagonal");
                }
            }

            public void makeMove() {
                if (!highestIndexInfo.get(0).equals("Empty")) {
                    if (highestIndexInfo.get(0).equals("Row") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[highestLocation.get(0)][i].equals("_")) {
                                board[highestLocation.get(0)][i] = typeC;
                                isMoveMade = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("Col") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[i][highestLocation.get(0)].equals("_")) {
                                board[i][highestLocation.get(0)] = typeC;
                                isMoveMade = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("LeftDiagonal") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[i][i].equals("_")) {
                                board[i][i] = typeC;
                                isMoveMade = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("RightDiagonal") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[i][boardSize].equals("_")) {
                                board[i][boardSize] = typeC;
                                isMoveMade = true;
                            }
                        }
                    }
                }
            }

            public void allInOneTryingToWin() {
                findHighestOfRows();
                findHighestOfColumns();
                findLeftDiagonal();
                findRightDiagonal();
                makeMove();
            }
        }

        public void fillSpaces() {
            if (!isMoveMade) {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    for (int j = 0; j <= boardSize && !isMoveMade; j++) {
                        if (board[i][j].equals("_")) {
                            board[i][j] = typeC;
                            isMoveMade = true;
                        }
                    }
                }
            }
        }

        public void reset() {
            isMoveMade = false;
        }

        public void allInOneMove() {
            rowSingleMoveForWinCheck();
            columnSingleMoveForWinCheck();
            leftDiagonalSingleMoveForWinCheck();
            rightDiagonalSingleMoveForWinCheck();
            rowPreventionCheck();
            columnPreventionCheck();
            leftDiagonalPreventionCheck();
            rightDiagonalPreventionCheck();
            TryingToWin tryingToWin = new TryingToWin();
            tryingToWin.allInOneTryingToWin();
            fillSpaces();
            reset();

        }
    }

    public class GameWinCheck {
        public void rowCheck() {
            int counter = -1;
            for (int i = 0; i <= boardSize; i++) {
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][i].equals(board[i][j]) && !board[i][i].equals("_")) {
                        counter += 1;
                    }
                }
                if (counter == boardSize) {
                    continuity = false;
                    break;
                }
                counter = -1;
            }
        }

        public void columnCheck() {
            int counter = -1;
            for (int i = 0; i <= boardSize; i++) {
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][i].equals(board[j][i]) && !board[i][i].equals("_")) {
                        counter += 1;
                    }
                }
                if (counter == boardSize) {
                    continuity = false;
                }
                counter = -1;
            }
        }

        public void leftDiagonalCheck() {
            int counter = -1;
            for (int i = 0; i <= boardSize; i++) {
                if (board[0][0].equals(board[i][i]) && !board[0][0].equals("_")) {
                    counter += 1;
                }
            }
            if (counter == boardSize) {
                continuity = false;
            }
        }

        public void rightDiagonalCheck() {
            int counter = -1;

            if (!board[0][boardSize].equals("_")) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[0][boardSize].equals(board[i][boardSize - i])) {
                        counter += 1;
                    }
                }
                if (counter == boardSize) {
                    continuity = false;
                }
            }
        }

        public void allInOneGameWinCheck() {
            rowCheck();
            columnCheck();
            leftDiagonalCheck();
            rightDiagonalCheck();
        }
    }

    public void gameTieCheck() {
        int counter = 0;
        for (int i = 0; i <= boardSize; i++) {
            for (int j = 0; j <= boardSize; j++) {
                if (!board[i][j].equals("_")) {
                    counter += 1;
                }
            }
        }
        if (counter == (boardSize + 1) * (boardSize + 1)) {
            isTie = true;
        }
    }
}
