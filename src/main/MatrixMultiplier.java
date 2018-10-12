package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Paul on 2018-10-05.
 */
public class MatrixMultiplier {
    private ArrayList<ArrayList<Float>> constants = new ArrayList<>();
    private ArrayList<ArrayList<Float>> matrix = new ArrayList<>();

    ArrayList<Float> solveEquations(ArrayList<ArrayList<Float>> augmentedMatrix) {
        for (int i = 0; i < augmentedMatrix.size(); i++) {
            ArrayList<Float> temp1 = new ArrayList<>();
            ArrayList<Float> temp2 = new ArrayList<>();
            for (int j = 0; j < augmentedMatrix.get(i).size(); j++) {
                if (j < augmentedMatrix.get(i).size() - 1) {
                    temp1.add(augmentedMatrix.get(i).get(j));
                } else {
                    temp2.add(augmentedMatrix.get(i).get(j));
                }
            }
            matrix.add(temp1);
            constants.add(temp2);
        }

        ArrayList<ArrayList<Float>> invertMetrix = invertMetrix(matrix);

        return new ArrayList<>();
    }

    private ArrayList<ArrayList<Float>> invertMetrix(ArrayList<ArrayList<Float>> matrix) {
        Float determinant = findDeterminant(matrix);
        ArrayList<ArrayList<Float>> adjucate = findAdjucate(matrix);

        ArrayList<ArrayList<Float>> inverse = new ArrayList<>();

        for(ArrayList<Float> row : adjucate){
            ArrayList<Float> temp = new ArrayList<>();
            for(Float number : row){
                temp.add(number * determinant);
            }
            inverse.add(temp);
        }
        return inverse;
    }

    private ArrayList<ArrayList<Float>> findAdjucate(ArrayList<ArrayList<Float>> matrix) {

        return null;
    }

    private Float findDeterminant(ArrayList<ArrayList<Float>> matrix) {

        return null;
    }
}
