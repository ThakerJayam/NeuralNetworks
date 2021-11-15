package Layers.Activation;

import Matrix.Matrix;
import Regularizers.Regularizer;

public class Tanh extends Activation{
    Matrix output;
    Matrix grad;
    @Override
    public Matrix forward(Matrix input) {

        //Sigmoid s = new Sigmoid();
        //output = Matrix.multiply(s.forward(Matrix.multiply(input,2)),2);
        //output.add(-1);
        Matrix pos = Matrix.exp(input);
        Matrix neg = Matrix.exp(Matrix.multiply(input,-1));
        output = Matrix.divide(Matrix.subtract(pos,neg),Matrix.add(pos,neg));
        return output;
    }

    @Override
    public Matrix backward(Matrix outGrad, double learningRate) {

        //output.printMatrix();
        //output.square().printMatrix();
        grad = Matrix.multiply(output.square(),-1);
        //grad.printMatrix();
        grad.add(1);
        //outGrad.printMatrix();
        //grad.printMatrix();
        outGrad.multiply(grad);
        return outGrad;
    }


    public Matrix getWeights(){
        return null;
    }

    public Matrix backward(Matrix outGrad, double learningRate, Regularizer reg){
        return null;
    }

}
