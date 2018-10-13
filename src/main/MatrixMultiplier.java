package main;

import java.util.ArrayList;

/**
 * Created by Paul on 2018-10-05.
 */
class MatrixMultiplier {
    private ArrayList<ArrayList<Double>> constants = new ArrayList<>();
    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    ArrayList<ArrayList<Double>> solveEquations(ArrayList<ArrayList<Double>> augmentedMatrix) throws Exception {
        for (ArrayList<Double> anAugmentedMatrix : augmentedMatrix) {
            ArrayList<Double> temp1 = new ArrayList<>();
            ArrayList<Double> temp2 = new ArrayList<>();
            for (int j = 0; j < anAugmentedMatrix.size(); j++) {
                if (j < anAugmentedMatrix.size() - 1) {
                    temp1.add(anAugmentedMatrix.get(j));
                } else {
                    temp2.add(anAugmentedMatrix.get(j));
                }
            }
            matrix.add(temp1);
            constants.add(temp2);
        }

        ArrayList<ArrayList<Double>> invertMatrix = invertMatrix(matrix);

        return multiplyMatrices(invertMatrix, constants);
    }

    private ArrayList<ArrayList<Double>> multiplyMatrices(ArrayList<ArrayList<Double>> matrix1, ArrayList<ArrayList<Double>> matrix2) throws Exception {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();

        int columnsMatrix1 = matrix1.get(0).size();
        int rowsMatrix2 = matrix2.size();

        int columnsMatrix2 = matrix2.get(0).size();
        int rowsMatrix1 = matrix1.size();

        if (columnsMatrix1 != rowsMatrix2) {
            throw new Exception();
        }

        for (int i = 0; i < rowsMatrix1; i++) {
            ArrayList<Double> temp1 = new ArrayList<>();
            for (int j = 0; j < columnsMatrix2; j++) {
                double value = 0d;
                for (int k = 0; k < matrix1.size(); k++) {
                    value += matrix1.get(i).get(k) * matrix2.get(k).get(j);
                }
                temp1.add(value);
            }
            result.add(temp1);
        }
        return result;
    }

    private ArrayList<ArrayList<Double>> invertMatrix(ArrayList<ArrayList<Double>> matrix) {
        Double determinant = findDeterminant(matrix);

        ArrayList<ArrayList<Double>> adjucateMatrix = findAdjucate(matrix);

        ArrayList<ArrayList<Double>> inverse = new ArrayList<>();

        for (ArrayList<Double> row : adjucateMatrix) {
            ArrayList<Double> temp = new ArrayList<>();
            for (Double number : row) {
                temp.add(number * (1 / determinant));
            }
            inverse.add(temp);
        }
        return inverse;
    }

    private ArrayList<ArrayList<Double>> findAdjucate(ArrayList<ArrayList<Double>> matrix) {
        ArrayList<ArrayList<Double>> adjucateMatrix = new ArrayList<>();
        int sign = 1;
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Double> temp = new ArrayList<>();
            for (int j = 0; j < matrix.get(i).size(); j++) {
                ArrayList<ArrayList<Double>> determinantMatrix = new ArrayList<>();
                for (int k = 0; k < matrix.size(); k++) {
                    ArrayList<Double> row = new ArrayList<>();
                    for (int l = 0; l < matrix.get(k).size(); l++) {
                        if (k != i && l != j) {
                            row.add(matrix.get(k).get(l));
                        }
                    }
                    if (row.size() > 0) {
                        determinantMatrix.add(row);
                    }
                }
                temp.add(sign * findDeterminant(determinantMatrix));
                sign *= -1;
            }
            adjucateMatrix.add(temp);
            if (matrix.size() % 2 == 0) {
                sign *= -1;
            }
        }

        ArrayList<ArrayList<Double>> transposeMatrix = new ArrayList<>();

        for (int i = 0; i < adjucateMatrix.size(); i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < adjucateMatrix.get(i).size(); j++) {
                row.add(adjucateMatrix.get(j).get(i));
            }
            transposeMatrix.add(row);
        }
        return transposeMatrix;
    }

    private Double findDeterminant(ArrayList<ArrayList<Double>> matrix) {
        double determinant = 0d;
        int sign = 1;
        if (matrix.size() > 2) {
            for (int i = 0; i < matrix.get(0).size(); i++) {
                ArrayList<ArrayList<Double>> detMatrix = new ArrayList<>();
                for (int j = 1; j < matrix.size(); j++) {
                    ArrayList<Double> temp = new ArrayList<>();
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
