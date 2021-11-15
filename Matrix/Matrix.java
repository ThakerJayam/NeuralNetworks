package Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Matrix {

    private double[][] values;
    public int x_length;
    public int y_length;



    public Matrix(int x, int y){
        values = new double[y][x];
        x_length = x;
        y_length = y;
    }
    public Matrix(double[][] value){
        values = value.clone();
        x_length = values[0].length;
        y_length = values.length;
    }

    public Matrix(Matrix m){
        //Copy Constructor

        this.values = m.values.clone();
        this.x_length = m.x_length;
        this.y_length = m.y_length;
    }

    public static Matrix dot(Matrix a,Matrix b){

        //Check for size compatibility of matrix.

       try {
           if (a.x_length != b.y_length) {
               throw new Exception();
           }
       }catch (Exception e){
           System.out.println("Size not compatible for multiplication");

       }

        Matrix c = new Matrix(b.x_length,a.y_length);
        //System.out.println(a.y_length);
        for(int i = 0 ; i < a.y_length; i++){
            for (int j = 0; j < b.x_length; j++){

                double val = 0;
                for(int k = 0; k < b.y_length; k++){
                    //System.out.println(i + " " + j + " "+ k);
                    val += a.values[i][k]*b.values[k][j];

                }
                c.setValue(val,i,j);

            }
        }

        //Implementing Strassen's Algorithm Remaining


        return c;
    }

    public void printMatrix(){
        System.out.println("---------------------");

        for(int i = 0; i < y_length;i++){
            System.out.print("|");
            for (int j = 0; j < x_length; j++){
                System.out.print(values[i][j] + "| ");
            }
            System.out.println();
        }
        System.out.println("---------------------");

    }

    public void setValue(double value,int x,int y){
        values[x][y] = value;
    }

    public void dot(Matrix b){

        //Check for size compatibility of matrix.
        try {
            if (this.x_length != b.y_length) {
                throw(new Exception());
            }
        }catch(Exception e){
            System.out.println("Size not compitable for multiplication");
        }

        double[][] v = new double[x_length][y_length];
        for(int i = 0 ; i < this.x_length; i++){
            for (int j = 0; j < b.y_length; j++){

                double val = 0;
                for(int k = 0; k < this.y_length; k++){
                    val += this.values[i][k]*b.values[k][j];
                }
                v[i][j] = val;
            }
        }
        values = v;
    }

    public void dot(double num){
        for(int i = 0; i < this.y_length; i++){
            for (int j = 0; j < this.x_length; j++){
                this.setValue(this.getValue(i,j) * num,i,j);
            }
        }
    }
    public static Matrix dot(Matrix ip,double num){
        Matrix out = new Matrix(ip.x_length,ip.y_length);
        for(int i = 0; i < ip.y_length; i++){
            for (int j = 0; j < ip.x_length; j++){
                out.setValue(ip.getValue(i,j) * num,i,j);
            }
        }
        return out;
    }
    public double getValue(int x,int y){
        return this.values[x][y];
    }

    public void add(Matrix b) throws Exception{
        if((this.x_length != b.x_length) || (this.y_length != b.y_length)){
            throw new Exception();
        }
        else{
            for(int i = 0; i < x_length;i++){
                for(int j = 0; j < y_length; j++){
                    this.values[i][j] += b.values[i][j];
                }
            }
        }

    }

    public static Matrix add(Matrix a,Matrix b){

        Matrix output = new Matrix(a.x_length,a.y_length);
            for(int i = 0; i < a.y_length;i++){
                for(int j = 0; j < a.x_length; j++){
                    output.setValue(a.getValue(i,j) + b.getValue(i,j),i,j);
                }
            }

        return output;
    }

    public void add(double bias){
        for(int i = 0; i < y_length; i++){
            for(int j = 0; j < x_length; j++){
                this.values[i][j] += bias;
            }
        }
    }

    public static Matrix add(Matrix ip,double value){
        Matrix op = new Matrix(ip);

        for(int i = 0; i < op.y_length; i++){
            for(int j = 0; j < op.x_length; j++){
                op.values[i][j] += value;
            }
        }
        return op;
    }

    public void subtract(Matrix b){
        try {
            if ((this.x_length != b.x_length) || (this.y_length != b.y_length)) {
                throw new Exception();
            }
        }catch(Exception e) {
            System.out.println("Size Mismatch");
        }
        for(int i = 0; i < y_length;i++){
            for(int j = 0; j < x_length; j++){
                this.values[i][j] -= b.values[i][j];
            }
        }

    }

    public static  Matrix subtract(Matrix a,Matrix b){
        try {
            if ((a.x_length != b.x_length) || (a.y_length != b.y_length)) {
                throw new Exception();
            }
        }catch(Exception e) {
            System.out.println("Size Mismatch");
        }
        Matrix ans = new Matrix(a.x_length,a.y_length);
        for(int i = 0; i < a.y_length;i++){
            for(int j = 0; j < a.x_length; j++){
                ans.values[i][j] =  a.values[i][j] - b.values[i][j];
            }
        }
        return ans;
    }


    public static Matrix divide(Matrix a,Matrix b){
        Matrix output = new Matrix(a.x_length,a.y_length);

        for(int i = 0 ; i < a.y_length; i++){
            for(int j = 0; j < a.x_length; j++){

                output.setValue(a.getValue(i,j)/b.getValue(i,j),i,j);
            }
        }
        return output;
    }
    public static Matrix divide(double a,Matrix b){
        Matrix output = new Matrix(b.x_length,b.y_length);

        for(int i = 0 ; i < b.y_length; i++){
            for(int j = 0; j < b.x_length; j++){

                output.setValue(a/b.getValue(i,j),i,j);
            }
        }
        return output;
    }


    public Matrix square(){
        Matrix ans = new Matrix(this.x_length,this.y_length);

        for(int i = 0; i < y_length; i++){
            for(int j = 0; j < x_length; j++){
                ans.setValue(Math.pow(this.values[i][j],2),i,j);
            }
        }

        return ans;
    }

    public double sum(){
        double ans = 0;

        for(int i = 0; i < y_length; i++){
            for(int j = 0; j < x_length; j++){
                ans += this.values[i][j];
            }
        }

        return ans;
    }

    public Matrix transpose(){

        Matrix trans = new Matrix(this.y_length,this.x_length);

        for(int i = 0; i < x_length; i++){

            for (int j = 0; j < y_length; j++){

                double temp = this.values[j][i];
                trans.values[i][j] = temp;
            }
        }

        return trans;
    }

    public void random(){
        Random r = new Random();
        r.setSeed(7);
        for(int i = 0 ; i < y_length; i++){
            for(int j = 0; j< x_length; j++){
                this.values[i][j] = r.nextGaussian();
            }
        }
    }

    public static Matrix absolute(Matrix input){
        Matrix ans = new Matrix(input.x_length,input.y_length);
        double val;
        for(int i = 0; i < input.y_length; i++){
            for(int j = 0; j < input.x_length; j++){
                val = input.getValue(i,j);
                if(val < 0)
                    ans.setValue(-val,i,j);
                else
                    ans.setValue(val,i,j);
            }
        }
        return ans;
    }

    public static Matrix y_sum(Matrix input){
        Matrix ans = new Matrix(1,input.y_length);
        double sum;
        for (int i = 0; i < input.y_length; i++){
            sum = 0;
            for (int j =0; j < input.x_length; j++){
                sum += input.getValue(i,j);
            }
            ans.setValue(sum,i,0);
        }
        return ans;
    }


    public static Matrix x_sum(Matrix input){
        Matrix ans = new Matrix(input.x_length,1);
        double sum;
        for (int i = 0; i < input.x_length; i++){
            sum = 0;
            for (int j =0; j < input.y_length; j++){
                sum += input.getValue(j,i);
            }
            ans.setValue(sum,0,i);
        }
        return ans;
    }

    public static Matrix multiply(Matrix a, Matrix b){
        try {
            if ((a.x_length != b.x_length) || (a.y_length != b.y_length)) {
                throw new Exception();
            }
        }catch(Exception e) {
            System.out.println("Size Mismatch");
        }
        Matrix ans = new Matrix(a.x_length,a.y_length);
        for(int i = 0; i < a.y_length;i++){
            for(int j = 0; j < a.x_length; j++){
                ans.values[i][j] =  a.values[i][j] * b.values[i][j];
            }
        }
        return ans;
    }
    public void multiply(Matrix b){
        try {
            if ((this.x_length != b.x_length) || (this.y_length != b.y_length)) {
                throw new Exception();
            }
        }catch(Exception e) {
            System.out.println("Size Mismatch");
        }
        for(int i = 0; i < this.y_length;i++){
            for(int j = 0; j < this.x_length; j++){
                this.values[i][j] *= b.values[i][j];
            }
        }
    }

    static public Matrix multiply(Matrix ip, double b){
        Matrix output = new Matrix(ip.x_length,ip.y_length);
        for(int i = 0; i < ip.y_length; i++){
            for (int j = 0; j < ip.x_length; j++){
                output.setValue(ip.getValue(i,j)*b,i,j);
            }
        }
        return output;
    }
    public void multiply(double b){
        for(int i = 0; i < this.y_length; i++){
            for (int j = 0; j < this.x_length; j++){
                this.setValue(this.getValue(i,j)*b,i,j);
            }
        }
    }

    static public Matrix exp(Matrix input){
        Matrix output = new Matrix(input.x_length,input.y_length);

        for(int i = 0 ; i < input.x_length; i++){
            for(int j = 0; j < input.y_length; j++){

                output.setValue(Math.exp(input.getValue(j,i)),j,i);
            }
        }
        return output;
    }
    public void exp(){
        for(int i = 0 ; i < this.x_length; i++){
            for(int j = 0; j < this.y_length; j++){

                this.setValue(1/(1 + Math.exp(-1*this.getValue(j,i))),j,i);
            }
        }
    }

    public static Matrix fromCSV(String src,String del){
        double values[][];
        try {
            ArrayList<ArrayList> classValues =new ArrayList<ArrayList>();

            BufferedReader reader = new BufferedReader(new FileReader(src));
            String line;
            Scanner sc;

            while ((line = reader.readLine()) != null){

                sc = new Scanner(line);
                sc.useDelimiter(del);

                ArrayList<Double> row = new ArrayList<Double>();
                while (sc.hasNext()){
                        //System.out.print(sc.next());
                        row.add(Double.parseDouble(sc.next()));
                }
                classValues.add(row);

            }
            values = new double[classValues.size()][classValues.get(0).size()];
            for(int i = 0; i < classValues.size(); i++){
                ArrayList<Double> row = classValues.get(i);
                for(int j = 0; j < row.size(); j++){
                    values[i][j] = row.get(j);
                }

            }

            return new Matrix(values);

        }catch(Exception e ){
            System.out.println("Wrong File Name");
        }
        return null;
    }

}
