import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;


public class TryingOI {

    public static void main(String[] args) throws IOException {
        File file = new File("highscore");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        try {
            line = bufferedReader.readLine();
            String[] score = line.split(": ");
            String rec = score[1];
        } catch (IOException e) {
            System.out.println("Can't read data from file!");
        }
    }
}
