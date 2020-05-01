package javaapplication4;

import java.util.*;
import java.util.Scanner;

/**
 *
 * @author frmnjn & fadhlanmuhammad
 */

public class temp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int datauji[] = new int[3];
        
        int datalatih[][] = {
            {6, 2, 1}, {4, 1, 1}, {3, 5, 1},
            {2, 2, 0}, {2, 2, 1}, {3, 2, 1},
            {3, 1, 1}, {4, 1, 2}, {5, 1, 2},
            {2, 5, 1}, {3, 3, 1}, {5, 3, 1},
            {3, 3, 0}, {4, 1, 1}, {5, 0, 0},
            {0, 3, 1}, {6, 2, 3}, {3, 1, 0},
            {1, 3, 0}, {4, 2, 1}, {1, 2, 1},
            {4, 1, 3}, {4, 2, 0}, {2, 4, 0},
            {7, 0, 2}, {3, 2, 0}, {2, 3, 1},
            {3, 1, 1}, {2, 3, 1}, {1, 4, 1},
            {2, 1, 1}, {4, 5, 2}, {2, 5, 1},
            {4, 2, 0}, {5, 3, 1}};
        
        String kelasdatalatih[] = {
            "Pecandu", "Pecandu", "Sehat", "Sehat",
            "Sehat", "Sehat", "Sehat", "Pecandu",
            "Pecandu", "Cukup", "Cukup", "Pecandu",
            "Sehat", "Pecandu", "Pecandu", "Sehat",
            "Pecandu", "Cukup", "Sehat", "Cukup",
            "Sehat", "Pecandu", "Pecandu", "Sehat",
            "Pecandu", "Cukup", "Cukup", "Cukup",
            "Cukup", "Sehat", "Cukup", "Pecandu",
            "Sehat", "Pecandu", "Cukup"};
        
        double jarak[][] = new double[datalatih.length][2];

        System.out.println("Tingkat Kecanduan Micin Seseorang\n");
        System.out.print("Rata-rata Jumlah Makanan Mengandung MSG Yang Anda Konsumsi Dalam 1 Hari       : ");
        datauji[0] = in.nextInt();
        System.out.print("Rata-rata Jumlah Makanan Tidak Mengandung MSG Yang Anda Konsumsi Dalam 1 Hari : ");
        datauji[1] = in.nextInt();
        System.out.println("Kategori Tubuh Anda : ");
        System.out.println("0. Kurus");
        System.out.println("1. Ideal");
        System.out.println("2. Gemuk");
        System.out.println("3. Obesitas");
        System.out.print("Masukkan Kategori Tubuh Anda : ");
        datauji[2] = in.nextInt();
        
        
        
        System.out.println("=========================================================");
        
        //menghitung distance
        for (int i = 0; i < datalatih.length; i++) {
            jarak[i][0] = Math.sqrt(Math.pow((datalatih[i][0] - datauji[0]), 2) + 
                                    Math.pow((datalatih[i][1] - datauji[1]), 2) + 
                                    Math.pow((datalatih[i][2] - datauji[2]), 2));
            jarak[i][1] = i;
        }

        //array 2d sort
        Arrays.sort(jarak, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        }
        );
        
        for (int i = 0; i < datalatih.length; i++) {
            //System.out.println(jarak[i][0] + "\t\t\t" + kelasdatalatih[(int) jarak[i][1]]);
            System.out.printf("%.3f\t%s \n",jarak[i][0],kelasdatalatih[(int)jarak[i][1]]);
        }

        System.out.println("=========================================================");
        
        
        //weighted voting
        double jumlah_weight[] = {0, 0, 0};

        for (int i = 0; i < jarak.length; i++) {
            if (jarak[i][0] != 0) {
                if (kelasdatalatih[(int) jarak[i][1]].equals("Sehat")) {
                    jumlah_weight[0] += 1 / Math.pow(jarak[i][0], 2);
                }
                if (kelasdatalatih[(int) jarak[i][1]].equals("Cukup")) {
                    jumlah_weight[1] += 1 / Math.pow(jarak[i][0], 2);
                }
                if (kelasdatalatih[(int) jarak[i][1]].equals("Pecandu")) {
                    jumlah_weight[2] += 1 / Math.pow(jarak[i][0], 2);
                }
            }
        }
        
        System.out.println("bobot Sehat : " + jumlah_weight[0]);
        System.out.println("bobot Cukup : " + jumlah_weight[1]);
        System.out.println("bobot Pecandu : " + jumlah_weight[2]);
       
        double indeks_weight[][] = {
            {0, jumlah_weight[0]},
            {1, jumlah_weight[1]},
            {2, jumlah_weight[2]}
        };

        
        double max = Math.max(Math.max(jumlah_weight[0], jumlah_weight[1]), jumlah_weight[2]);
        
        /*
        Arrays.sort(indeks_weight, new java.util.Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });*/
        
        
        String tingkatan_kelas[] = {"Sehat", "Cukup", "Pecandu"};
        for (int i = 0; i < 3; i++) {
            if(max == indeks_weight[i][1]){
                System.out.println("Maka Anda tergolong "+tingkatan_kelas[i]);
            }
        }
        
    }

}
