package Regularizers;

import Matrix.Matrix;

public interface Regularizer {
    public void forward(Matrix weight);

    public Matrix computeReg(Matrix weight);
    public double computeLoss();
}
