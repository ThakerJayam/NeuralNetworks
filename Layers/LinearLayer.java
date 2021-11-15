package Layers;

import Matrix.Matrix;
import Regularizers.Regularizer;

public class LinearLayer implements Layers{

    public Matrix weight;
    private Matrix input;
    private boolean useBias;
    private Matrix bias;


    public LinearLayer(int inputSize,int outputSize,boolean useBias){

        weight = new Matrix(outputSize,inputSize);
        weight.random();
        this.useBias = useBias;
        if(useBias){
            bias = new Matrix(outputSize,1);
        }
    }

    public LinearLayer(Matrix x,int outputSize,boolean useBias){
        weight = new Matrix(x.x_length,outputSize);
        weight.random();
        this.useBias = useBias;
        if(useBias){
            bias = new Matrix(outputSize,1);
        }
    }

    public Matrix forward(Matrix input){
        this.input = input;
        /*weight.setValue(1,0,0);
        weight.setValue(1,0,1);
        weight.setValue(1,1,0);
        weight.setValue(1,1,1);*/

        Matrix zMat = Matrix.dot(input,weight);
        //newMat.printMatrix();
        if(useBias){
            addBias(zMat,bias);
        }

        return zMat;

    }

    public void  set_weights(Matrix m){
        this.weight = m;
    }

    public Matrix getWeights(){

        this.weight.square().printMatrix();

        return weight;
    }

    public Matrix backward(Matrix outGrad,double learningRate){
        if(useBias){
            this.bias.subtract(Matrix.dot(Matrix.x_sum(outGrad),learningRate));

        }


        Matrix sub =  Matrix.dot(outGrad.transpose(),input);
        this.weight.subtract(Matrix.dot(sub.transpose(),learningRate));


        outGrad = Matrix.dot(outGrad,this.weight.transpose());
        return outGrad;

    }

    public Matrix backward(Matrix outGrad,double learningRate,Regularizer reg){

        outGrad = backward(outGrad,learningRate);

        Matrix regTerm  = reg.computeReg(this.weight);

        outGrad = Matrix.add(outGrad,regTerm);

        return outGrad;
    }

    private void addBias(Matrix ip,Matrix bias){
        for(int i = 0; i < ip.y_length; i++){
            for(int j = 0; j < ip.x_length; j++){
                ip.setValue(ip.getValue(i,j) + bias.getValue(0,j),i,j);
            }
        }
    }
}
