package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row, column;
        int ticketsSold = 0;
        int currentIncome = 0;
        System.out.println("Enter the number of rows:");
        row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        column = sc.nextInt();
        char seat = 'S';
        char[][] Theatre = new char[row][column];
        for(int i = 0; i < row; i++){
            for(int j=0 ;j <column; j++){
                Theatre[i][j] = seat;
            }
        }
        while (true) {
            displayMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    printTheatre(Theatre, row, column);
                }
                case 2 -> {
                    currentIncome += buyTicket(Theatre, row, column);
                    ticketsSold++;
                }
                case 3 -> displayStatistics(ticketsSold, currentIncome, row, column);
                case 0 -> { return; }

            }
        }


    }

    private static void displayStatistics(int ticketsSold, int currentIncome, int row, int column) {
        int totalIncome = 0;
        if (row * column < 60) {
            totalIncome += row * column * 10;
        } else {
            totalIncome += row % 2 == 0 ? row/2 * column * 18 : (row - row/2) * column * 8 + (row/2) * column * 10;
        }
        System.out.printf("Number of purchased tickets: %d%n", ticketsSold);
        System.out.printf("Percentage: %.2f%%%n", (ticketsSold * 1.0 /(row * column * 1.0)) * 100.0);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public static void printTheatre(char[][] Theatre, int rows, int cols) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if(i == 0 && j ==0){
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0){
                    System.out.print(i + " ");
                }
                else {
                    System.out.print(Theatre[i-1][j-1] + " ");
                }
            }
            System.out.println();
        }
    }

    public static int calculateTicketPrice(int rows, int seats, int rowNum, int colNum) {
        int price;
        if (rows * seats < 60)
            price = 10;
        else {
            if (rowNum <= rows / 2) {
                price = 10;
            }
            else
                price = 8;
        }
        return price;
    }
    public static int buyTicket(char[][] Theatre, int row, int column) {
        Scanner sc = new Scanner(System.in);
        int colNum;
        int rowNum;
        while ( true ) {
            System.out.println("Enter a row number:");
            rowNum = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            colNum = sc.nextInt();
            if (rowNum <= row && colNum <= column ) {
                if (Theatre[rowNum-1][colNum-1] != 'B') {
                    break;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
        int seatPrice = calculateTicketPrice(row, column, rowNum, colNum);
        System.out.println("Ticket price: $" + seatPrice);
        Theatre[rowNum-1][colNum-1] = 'B';
        return seatPrice;
    }
    public static void displayMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}