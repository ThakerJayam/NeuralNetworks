package Layers.Activation;
import Matrix.Matrix;
import Regularizers.Regularizer;

public class Sigmoid extends Activation {

    private Matrix output;
    private Matrix grad;

    public Matrix forward(Matrix input){
        output = Matrix.exp(Matrix.multiply(input,-1));
        output = Matrix.divide(1,Matrix.add(output,1));

            return output;
        }

        @Override
        public Matrix backward(Matrix outGrad,double learningRate) {

        grad = Matrix.multiply(output,Matrix.add(Matrix.dot(output,-1),1));
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