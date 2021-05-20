package com.example;


import java.util.Scanner;

public class Cinema {
    static int purchasedTickets = 0;
    static int totalTickets;
    static int currentIncome = 0;
    static int totalIncome;

    public static void createCinema(int rows, int noOfSeats, char[][] cinema) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < noOfSeats; j++) {
                cinema[i][j] = 'S';
            }
        }
    }


    public static void printCinema(int rows, int noOfSeats, char[][] cinema) {
        System.out.println("Cinema:");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= noOfSeats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0 && j != 0) {
                    System.out.print(j + " ");
                } else if (i != 0 && j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(cinema[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getTotalIncome(int rows, int noOfSeats) {
        int income = 0;
        if (noOfSeats * rows <= 60) {
            income = noOfSeats * rows * 10;
        } else {
            income = (noOfSeats * (rows / 2) * 10) + (noOfSeats * (rows - (rows / 2)) * 8);
        }
        //System.out.println("Total income:");
        //System.out.println("$" + income);
        return income;
    }

    public static void buyTicket(int rows, int noOfSeats, char[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        int rowNum;
        int seatNum;

        while (true) {
            System.out.println("Enter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();

            if (rowNum > rows || seatNum > noOfSeats) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (cinema[rowNum - 1][seatNum - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                break;
            }
        }

        int ticketPrice = getTicketPrice(rows, noOfSeats, rowNum);
        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
        currentIncome += ticketPrice;

        cinema[rowNum - 1][seatNum - 1] = 'B';
        purchasedTickets += 1;
    }

    public static int getTicketPrice(int rows, int noOfSeats, int rowNum) {

        int ticketPrice;
        if (noOfSeats * rows <= 60) {
            ticketPrice = 10;
        } else if (rowNum <= rows / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        return ticketPrice;
    }

    public static void getStats() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        float percentage = (float) purchasedTickets * 100 / totalTickets;
        System.out.format("Percentage: %.2f%s", percentage,"%");
        System.out.println();
        System.out.println("Current Income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int noOfSeats = scanner.nextInt();

        char[][] cinema = new char[rows][noOfSeats];
        totalTickets = rows * noOfSeats;
        totalIncome = getTotalIncome(rows, noOfSeats);
        createCinema(rows, noOfSeats, cinema);

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int selectOption = scanner.nextInt();

            switch (selectOption) {
                case 0:
                    return;
                case 1:
                    printCinema(rows, noOfSeats, cinema);
                    break;
                case 2:
                    buyTicket(rows, noOfSeats, cinema);
                    break;
                case 3:
                    getStats();
                    break;
            }
        }
    }
}

