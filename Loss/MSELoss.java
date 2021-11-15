package Loss;
import Matrix.Matrix;

public class MSELoss implements Loss{

    private Matrix grad;
    public double forward(Matrix y,Matrix y_h){

        grad = Matrix.subtract(y_h,y);

        return (grad.square().sum())/y.y_length;
    }

    public  Matrix backward(){
         return (grad);
    }
}
