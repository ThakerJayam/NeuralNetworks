package Layers.Activation;
import Matrix.Matrix;
import Regularizers.Regularizer;

public class ReLU extends Activation {
    private Matrix output;
    private Matrix grad;

    @Override
    public Matrix forward(Matrix input){
       output = new Matrix(input.x_length, input.y_length);
       grad = new Matrix(output.x_length, output.y_length);


        for(int i = 0 ; i < input.y_length; i++){
           for(int j = 0; j < input.x_length; j++){

               output.setValue(Math.max(input.getValue(i,j),0),i,j);
               if(input.getValue(i,j) > 0){
                   output.setValue(input.getValue(i,j),i,j);
                   grad.setValue(1,i,j);
               }
               else{
                   output.setValue(input.getValue(i,j)*0.5,i,j);
                   grad.setValue(0.5,i,j);
               }
           }
       }
   return output;
   }

   @Override
   public Matrix backward(Matrix outGrad,double learningRate) {
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
