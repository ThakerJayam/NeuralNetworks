package Loss;
import Matrix.Matrix;

public interface Loss {
    double forward(Matrix y,Matrix y_h);
    Matrix backward();
}
