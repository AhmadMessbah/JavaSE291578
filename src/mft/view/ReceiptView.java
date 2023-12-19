package mft.view;

import mft.controller.ReceiptController;

import java.util.Scanner;

public class ReceiptView {

    public static void main(String[] args) {
        System.out.println("ReceiptView");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Id : ");
        int id = Integer.parseInt(scanner.nextLine());


        System.out.print("Enter Amount : ");
        int amount = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Description : ");
        String desecration = scanner.nextLine();

        ReceiptController.save(id,amount, desecration);
    }
    }

