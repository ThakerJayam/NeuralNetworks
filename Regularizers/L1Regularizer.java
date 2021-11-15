package Regularizers;

import Matrix.Matrix;

public class L1Regularizer implements Regularizer{
    double output;
    double alpha;
    public L1Regularizer(double alpha){
        this.alpha = alpha;
    }

    public void forward(Matrix weight){
        output += weight.sum() * (0.5*alpha);

    }
    public Matrix computeReg(Matrix weight){
        return weight;
    }

    public double computeLoss(){
        return output;
    }
}
