/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package perceptron;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
/**
 *
 * @author Khori
 */
public class Perceptron {
    
     public static double[] weight = new double[4];;
     static double[][] T = new double[50][5];
     static double learning_rate = 0.001;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //baca data dari csv
        FileReader fr = new FileReader("data.csv");
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        int x = 0;
        while ((line = br.readLine()) != null) {
            String[] s = line.split(",");
            for (int i = 0; i < 5; i++) {
                T[x][i] = Double.parseDouble(s[i]); //asign nilai ke array T dari data.csv
            }
            x++;
        }
        
        //random bobot
        Random rand = new Random();
        for (int iter = 0; iter < 4 ; iter++){
            weight[iter] = rand.nextInt(20)+ 10;
            weight[iter] /= 10;
        }
        System.out.println("Random Weight Awal : ");
        for (int iter = 0; iter < 4 ; iter++){
            System.out.println("w-"+ (iter + 1) +" = "+ weight[iter]);
        }
        System.out.println("");
        
        boolean loop = true;
        int epoch = 1;
        double output = 0;
        
        while (loop){
            double error = 0;
//            System.out.println("Epoch ke-" + epoch);
            for(int i = 0; i < 40 ; i++){
                for(int j = 0; j < 4 ; j++){
                    output += weight[j] * T[i][j];
                }
                double err = (T[i][4] - output);

                for(int k = 0; k < 4 ; k++){
                    weight[k] += (learning_rate * err * T[i][k]);
                }
                error = Math.abs(T[i][4] - output);

                output = 0;
            }
            loop = false;
            
            if(error > 0.01){
                loop = true;
                epoch++;
            } else{
                loop = false;
            }
        }
        System.out.println("Epoch = " + epoch);
        for(int nw = 0; nw < 4 ; nw++){
            System.out.println("Wn-"+ (nw + 1) + ": " + weight[nw]);
        }
        
        for(int i = 40; i < 50; i++){
            output = 0;
            for(int j = 0 ; j < 4 ; j++){
                output += weight[j] * T[i][j];
            }
            System.out.println("Target " + T[i][4] + " dengan output " + output + " error " + (Math.abs(output - T[i][4])));
        }
    }
    
}
