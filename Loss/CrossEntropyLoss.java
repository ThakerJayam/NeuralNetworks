package Loss;

import Matrix.Matrix;

public class CrossEntropyLoss implements Loss{

    private Matrix output;
    private Matrix grad;

    @Override
    public double forward(Matrix y,Matrix y_h) {
        output = new Matrix(y.x_length,y.y_length);
        grad = Matrix.subtract(y_h,y);
        //y.printMatrix();
        //y_h.printMatrix();
        for(int i = 0; i < output.y_length; i++){

            for(int j = 0; j < output.x_length; j++) {

                //System.out.println(y.getValue(i,j));
                if (y.getValue(i, j) == 1) {
                    output.setValue(-Math.log(y_h.getValue(i, j)), i, j);
                }
                else {
                    output.setValue(-Math.log(1 - y_h.getValue(i, j)), i, j);
                }
            }
        }
        //output.printMatrix();
        return output.sum()/y.y_length;
    }

    @Override
    public Matrix backward() {
        return grad;
    }


}
