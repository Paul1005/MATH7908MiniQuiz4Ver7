package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Paul on 2018-10-05.
 */
public class Main {
    private static MatrixMultiplier matrixMultiplier = new MatrixMultiplier();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader("mq4data7.csv"));
        ArrayList<ArrayList<Float>> augmentedMatrix = new ArrayList<>();
        int matrixNum = 0;
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            if (line[0].startsWith("#")) {
                if (matrixNum > 0) {
                    System.out.println(matrixMultiplier.solveEquations(augmentedMatrix));
                }
                augmentedMatrix = new ArrayList<>();
                matrixNum++;
            } else {
                ArrayList<Float> temp = new ArrayList<>();
                for (String number : line) {
                    temp.add(Float.parseFloat(number));
                }
                augmentedMatrix.add(temp);
            }
        }
    }
}
