package main;

import java.util.ArrayList;

/**
 * Created by Paul on 2018-10-05.
 */
public class MatrixMultiplier {
    private ArrayList<ArrayList<Float>> constants = new ArrayList<>();
    private ArrayList<ArrayList<Float>> matrix = new ArrayList<>();

    ArrayList<ArrayList<Float>> solveEquations(ArrayList<ArrayList<Float>> augmentedMatrix) throws Exception {
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

        ArrayList<ArrayList<Float>> invertMatrix = invertMatrix(matrix);

        return multiplyMatrices(invertMatrix, constants);
    }

    private ArrayList<ArrayList<Float>> multiplyMatrices(ArrayList<ArrayList<Float>> matrix1, ArrayList<ArrayList<Float>> matrix2) throws Exception {
        ArrayList<ArrayList<Float>> result = new ArrayList<>();

        int rows = matrix1.size();
        int columns = matrix2.get(0).size();

        if (rows != columns) {
            throw new Exception();
        }

        for (int i = 0; i < rows; i++) {
            ArrayList<Float> temp1 = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < matrix1.size(); k++) {
                    Float value = null;
                    for (int l = 0; l < matrix1.get(i).size(); l++) {
                        value += matrix2.get(j).get(i) * matrix1.get(i).get(j);
                    }
                    temp1.add(value);
                }
            }
            result.add(temp1);
        }
        return result;
    }

    private ArrayList<ArrayList<Float>> invertMatrix(ArrayList<ArrayList<Float>> matrix) {
        Float determinant = findDeterminant(matrix);
        ArrayList<ArrayList<Float>> adjucate = findAdjucate(matrix);

        ArrayList<ArrayList<Float>> inverse = new ArrayList<>();

        for (ArrayList<Float> row : adjucate) {
            ArrayList<Float> temp = new ArrayList<>();
            for (Float number : row) {
                temp.add(number * determinant);
            }
            inverse.add(temp);
        }
        return inverse;
    }

    private ArrayList<ArrayList<Float>> findAdjucate(ArrayList<ArrayList<Float>> matrix) {
        ArrayList<ArrayList<Float>> adjucateMatrix = new ArrayList<>();
        int sign = 1;
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Float> temp2 = new ArrayList<>();
            ArrayList<ArrayList<Float>> matrix1 = new ArrayList<>();
            for (int j = 0; j < matrix.get(j).size(); j++) {
                for (int k = 0; k < matrix.size(); k++) {
                    ArrayList<Float> temp1 = new ArrayList<>();
                    for (int l = 0; l < matrix.get(k).size(); l++) {
                        if (k != i && l != j) {
                            temp1.add(matrix.get(k).get(i));
                        }
                    }
                    matrix1.add(temp1);
                }
                temp2.add(sign * findDeterminant(matrix1));
            }
            adjucateMatrix.add(temp2);
        }
        return adjucateMatrix;
    }

    private Float findDeterminant(ArrayList<ArrayList<Float>> matrix) {
        Float determinant = null;
        int sign = 1;
        if (matrix.size() > 2) {
            for (int i = 0; i < matrix.get(0).size(); i++) {
                ArrayList<ArrayList<Float>> detMatrix = new ArrayList<>();
                for (int j = 1; j < matrix.size(); j++) {
                    ArrayList<Float> temp = new ArrayList<>();
                    for (int k = 0; k < matrix.size(); k++) {
                        if (k != i) {
                            temp.add(matrix.get(j).get(k));
                        }
                    }
                    detMatrix.add(temp);
                }
                determinant += sign * matrix.get(0).get(i) * findDeterminant(detMatrix);
                sign *= -1;
            }
        } else {
            determinant = matrix.get(0).get(0) * matrix.get(1).get(1) - matrix.get(0).get(1) * matrix.get(1).get(0);
        }

        return determinant;
    }
}
