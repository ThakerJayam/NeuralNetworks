import Layers.Activation.Activation;
import Layers.Activation.*;
import Layers.Layers;
import Layers.LinearLayer;
import Loss.*;
import Loss.Loss;
import Matrix.Matrix;
import Regularizers.L1Regularizer;
import Regularizers.Regularizer;

public class Main {

    public static void main(String[] args) {

        //System.out.println(a[0][2]);

        Matrix m = new Matrix(new double[][]{{10,0,1,1},{9,15,1,4},{17,6,0,7},{1,1,1,1}});
        Matrix n = new Matrix(new double[][]{{1},{1},{1},{0}});
        Regularizer r = new L1Regularizer(0.2);

        Layers la = new LinearLayer(4,3,false);
        Matrix w  = la.getWeights();
        w.printMatrix();


        n = Matrix.fromCSV("/home/jayam/ML/x.csv",",");
        LinearLayer l1 = new LinearLayer(4,3,true);
        Activation a1 = new ReLU();
        LinearLayer l2 = new LinearLayer(3,2,true);
        Activation a2 = new ReLU();
        LinearLayer l3 = new LinearLayer(2,1,true);
        Activation  s1 = new Tanh();
        Loss l = new MSELoss();

        Sequential model = new Sequential();
        model.add(l1);
        model.add(a1);
        model.add(l2);
        model.add(a2);
        model.add(l3);
        model.add(s1);
        model.build(l,new L1Regularizer(0.2));
        model.fit(100,m,n,0.1);



    }

}
