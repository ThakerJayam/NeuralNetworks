package Layers;

import Matrix.Matrix;
import Regularizers.Regularizer;

public interface Layers {

    Matrix forward(Matrix input);
    Matrix backward(Matrix outGrad,double learningRate);
    Matrix backward(Matrix outGrad,double learningRate,Regularizer reg);
    Matrix getWeights();
}
