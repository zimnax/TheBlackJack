package com.andrew.safronov.sintez.theblackjack.webserver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        WebServer webServer = new WebServer(8084);
        try {
            webServer.start();
            webServer.join();
        } catch (Exception e) {
            System.out.println("IO EX occured while starting sever");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
