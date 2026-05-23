package task_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class Box {

    static int n_boxes = 7;
    //0 - red
//1 - white
//2 - black
//3 - green
//4 - blue
//5 - yellow
    static int[] box_total = new int[]{280, 260, 280, 240, 270, 230, 260};
    static int[][] box = new int[][]{{44, 61, 76, 63, 8, 28}, {53, 49, 67, 19, 34, 38}, {38, 41, 26, 63, 68, 44},
            {19, 9, 31, 68, 54, 59}, {63, 10, 35, 31, 69, 62}, {46, 29, 29, 16, 53, 57},
            {65, 7, 58, 46, 73, 11}};
    static int m = 6;
    static double[][] ballRatio = new double[n_boxes][m];
    static int nExp = 10000;
    static int[][] counterOfBalls = new int[n_boxes][m];
    static double[] currentBalls = new double[]{0, 0, 0, 0, 0, 0};
    static double[][] probability = new double[n_boxes][m];
    static int[][] hypothesis = new int[n_boxes][nExp];
    static int[] hyp_together = new int[nExp + 1];
    static Integer[] mostProbableBoxesSeparate = new Integer[]{0, 0, 0, 0, 0, 0, 0};
    static int[] mostProbableBoxesTogether = new int[]{0, 0, 0, 0, 0, 0, 0};
    static Integer[] mostProbableBoxesFrequency = new Integer[]{0, 0, 0, 0, 0, 0, 0};
    static Integer[] boxes = new Integer[n_boxes];

    public static void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("task1_ball_boxes.txt")));
        for (int i = 0; i < n_boxes; i++) {
            for (int j = 0; j < 6; j++) {
                ballRatio[i][j] = (double) box[i][j] / box_total[i];
                counterOfBalls[i][j] = 0;
            }
        }
        for (int i = 0; i < n_boxes; ++i) {
            for (int j = 0; j < n_boxes; j++) {
                probability[i][j] = 1.0 / n_boxes;
            }
        }
        for (int i = 0; i < 8; i++) reader.readLine();
        for (int i = 0; i < nExp; ++i) {
            currentBalls = new double[]{0, 0, 0, 0, 0, 0};
            String[] experiment = reader.readLine().split("[#,:]\\s");
            for (String ball : experiment) {
                //0 - red
                //1 - white
                //2 - black
                //3 - green
                //4 - blue
                //5 - yellow
                switch (ball) {
                    case "Red":
                        counterOfBalls[i % n_boxes][0]++;
                        currentBalls[0]++;
                        break;
                    case "White":
                        counterOfBalls[i % n_boxes][1]++;
                        currentBalls[1]++;
                        break;
                    case "Black":
                        counterOfBalls[i % n_boxes][2]++;
                        currentBalls[2]++;
                        break;
                    case "Green":
                        counterOfBalls[i % n_boxes][3]++;
                        currentBalls[3]++;
                        break;
                    case "Blue":
                        counterOfBalls[i % n_boxes][4]++;
                        currentBalls[4]++;
                        break;
                    case "Yellow":
                        counterOfBalls[i % n_boxes][5]++;
                        currentBalls[5]++;
                        break;
                }
            }
            for (int j = 0; j < m; ++j) {
                double max = 0;
                double a = 0;
                if (currentBalls[j] != 0) {
                    for (int l = 0; l < currentBalls[j]; l++) {
                        for (int k = 0; k < n_boxes; ++k) {
                            a += probability[i % n_boxes][k] * ballRatio[k][j];
                        }
                        for (int k = 0; k < n_boxes; k++) {
                            probability[i % n_boxes][k] = probability[i % n_boxes][k] *
                                    ballRatio[k][j] / a;
                            if (probability[i % n_boxes][k] > max) {
                                max = probability[i % n_boxes][k];
                            }

                        }
                    }
                } else {
                    for (int k = 0; k < n_boxes; ++k) {
                        a += probability[i % n_boxes][k] * ballRatio[k][j] / (1.0 - ballRatio[k][j]);
                    }
                    for (int k = 0; k < n_boxes; ++k) {
                        probability[i % n_boxes][k] = probability[i % n_boxes][k] *
                                (1.0 - ballRatio[k][j]) / a;
                        if (probability[i % n_boxes][k] > max) {
                            max = probability[i % n_boxes][k];
                        }
                    }
                }
            }//
            for (int k = 0; k < n_boxes; k++) {
                double test = probability[i % n_boxes][k];
                for (double value : probability[i % n_boxes]) {
                    if ((value > 0.9 * test) && (value < 1.1 * test)) {
                        hypothesis[k][i]++;
                    }
                }
            }
            List<Integer> separate = Arrays.asList(-1, -1, -1, -1, -1, -1, -1);
            for (int j = 0; j < n_boxes; j++) {
                double max = 0;
                int index = 0;
                for (int k = 0; k < n_boxes; k++) {
                    if (probability[k][j] > max && separate.get(k) == -1) {
                        max = probability[k][j];
                        index = k;
                    }
                }
                separate.set(index, j);
            }
            mostProbableBoxesSeparate = separate.toArray(new Integer[0]);
            int[] numberOfBox = new int[n_boxes];
            boxes = mostProbableBoxesSeparate;
            double together = 0;
            //
            for (numberOfBox[0] = boxes[0] - hypothesis[0][i];
                 numberOfBox[0] <= boxes[0] + hypothesis[0][i]; numberOfBox[0]++) {
                for (numberOfBox[1] = boxes[1] - hypothesis[1][i];
                     numberOfBox[1] <= boxes[1] + hypothesis[1][i]; numberOfBox[1]++) {
                    for (numberOfBox[2] = boxes[2] - hypothesis[2][i];
                         numberOfBox[2] <= boxes[2] + hypothesis[2][i]; numberOfBox[2]++) {
                        for (numberOfBox[3] = boxes[3] - hypothesis[3][i];
                             numberOfBox[3] <= boxes[3] + hypothesis[3][i]; numberOfBox[3]++) {
                            for (numberOfBox[4] = boxes[4] - hypothesis[4][i];
                                 numberOfBox[4] <= boxes[4] + hypothesis[4][i]; numberOfBox[4]++) {
                                for (numberOfBox[5] = boxes[5] - hypothesis[5][i];
                                     numberOfBox[5] <= boxes[5] + hypothesis[5][i]; numberOfBox[5]++) {
                                    for (numberOfBox[6] = boxes[6] - hypothesis[6][i];
                                         numberOfBox[6] <= boxes[6] + hypothesis[6][i]; numberOfBox[6]++) {
                                        if ((numberOfBox[0] != numberOfBox[1]) && (numberOfBox[0] != numberOfBox[2])
                                                && (numberOfBox[0] != numberOfBox[3]) && (numberOfBox[0] != numberOfBox[4]) &&
                                                (numberOfBox[0] != numberOfBox[5]) && (numberOfBox[0] != numberOfBox[6])
                                                && (numberOfBox[1] != numberOfBox[2]) && (numberOfBox[1] != numberOfBox[3])
                                                && (numberOfBox[1] != numberOfBox[4]) && (numberOfBox[1] != numberOfBox[5])
                                                && (numberOfBox[1] != numberOfBox[6])
                                                && (numberOfBox[2] != numberOfBox[3]) && (numberOfBox[2] != numberOfBox[4])
                                                && (numberOfBox[2] != numberOfBox[5]) && (numberOfBox[2] != numberOfBox[6]) &&
                                                (numberOfBox[3] != numberOfBox[4]) && (numberOfBox[3] != numberOfBox[5]) &&
                                                (numberOfBox[3] != numberOfBox[6])
                                                && (numberOfBox[4] != numberOfBox[5]) && (numberOfBox[4] != numberOfBox[6])
                                                && (numberOfBox[5] != numberOfBox[6])) {
                                            hyp_together[i]++;
                                            double max = 0;
                                            double value = 0;
                                            for (int j = 0; j < n_boxes; ++j) {
                                                if (probability[j][numberOfBox[j]] > max) {
                                                    max = probability[j][numberOfBox[j]];
                                                }
                                            }
                                            int ball = 0;
                                            double factorialDiv = 1;
                                            for (int j = 0; j < n_boxes; j++) {
                                                for (int k = 0; k < numberOfBox[j]; k++) {
                                                    ball++;
                                                    factorialDiv *= (double) ball / (k + 1);
                                                    value += Math.exp(probability[j][numberOfBox[j]] - max);
                                                }
                                            }
                                            value = max + Math.log(value);
                                            double togetherVar = factorialDiv * value;
                                            if (togetherVar > together) {
                                                together = togetherVar;
                                                mostProbableBoxesTogether = numberOfBox.clone();
                                            }
                                        }
                                    }


                                }

                            }
                        }
                    }
                }
            }
        }
        List<Integer> order = Arrays.asList(-1, -1, -1, -1, -1, -1, -1);
        for (int i = 0; i < n_boxes; ++i) {
            int[] current = new int[]{0, 0, 0, 0, 0, 0};
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += counterOfBalls[i][j];
            }
            for (int j = 0; j < m; ++j) {
                double ratio = (double) counterOfBalls[i][j] / sum;
                int mostProbableBox = 0;
                double difference = 1;
                for (int k = 0; k < n_boxes; k++) {
                    if (Math.abs(ballRatio[k][j] - ratio) < difference) {
                        mostProbableBox = k;
                        difference = Math.abs(ballRatio[k][j] - ratio);
                    }
                }
                current[mostProbableBox] += 1;
            }
            int max = -1;
            int box = -1;
            for (int j = 0; j < m; j++) {
                if (current[j] > max && !order.contains(j)) {
                    max = current[j];
                    box = i;
                }
            }
            order.set(i, box);
        }
        mostProbableBoxesFrequency = order.toArray(new Integer[0]);
        for (int i = 0; i < n_boxes; i++) {
            double sum = 0;
            for (int j = 0; j < m; j++) { //n_boxes -> m
                sum += probability[i][j];
            }
            for (int j = 0; j < m; j++) { //n_boxes -> m
                probability[i][j] = probability[i][j] / sum;
            }
        }
        System.out.println("Результаты на основе " + nExp + " оптытов");
        System.out.println("Наиболее вероятное расположение корзин в совокупности: ");
        System.out.println(mostProbableBoxesTogether[0] + "\t" + mostProbableBoxesTogether[1] + "\t"
                + mostProbableBoxesTogether[2] + "\t" + mostProbableBoxesTogether[3] +
                "\t" + mostProbableBoxesTogether[4] + "\t" + mostProbableBoxesTogether[5] + "\t" +
                mostProbableBoxesTogether[6]);
        System.out.println("Наиболее вероятное расположение корзин по отдельности: ");
        System.out.println(mostProbableBoxesSeparate[0] + "\t" + mostProbableBoxesSeparate[1] + "\t"
                + mostProbableBoxesSeparate[2] + "\t" + mostProbableBoxesSeparate[3] +
                "\t" + mostProbableBoxesSeparate[4] + "\t" + mostProbableBoxesSeparate[5] + "\t" +
                mostProbableBoxesSeparate[6]);
        out.println("Наиболее вероятное расположение корзин по частоте вытаскивания:");
        System.out.println(mostProbableBoxesFrequency[0] + "\t" + mostProbableBoxesFrequency[1] + "\t"
                + mostProbableBoxesFrequency[2] + "\t" + mostProbableBoxesFrequency[3] +
                "\t" + mostProbableBoxesFrequency[4] + "\t" + mostProbableBoxesFrequency[5] + "\t" +
                mostProbableBoxesFrequency[6]);
    }

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
