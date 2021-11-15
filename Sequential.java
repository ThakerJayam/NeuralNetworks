import Layers.Layers;
import Loss.Loss;
import Matrix.Matrix;
import Regularizers.Regularizer;

import java.util.ArrayList;
import java.util.Collections;

public class Sequential {
    private ArrayList<Layers> layers = new ArrayList<Layers>();
    private Loss lossFun;
    private double learningRate;
    private Regularizer regulizer = null;

    public Sequential(){}

    public Sequential(Layers[] layers){
        Collections.addAll(this.layers,layers);
    }

    public void add(Layers l){
        layers.add(l);

    }

    public Matrix run(Matrix op){

        int size = layers.size();
        for (Layers l : layers) {

            if (regulizer != null) {
                Matrix m = l.getWeights();
                m.printMatrix();
                regulizer.forward(m);
            }
            op = l.forward(op);

        }
        return op;
    }

    private void backwardRun(Matrix output){

        int size = layers.size();
        for(int i = size; i > 0; i--){

            Layers l = layers.get(i-1);
            if(regulizer != null){
                output = l.backward(output,this.learningRate,regulizer);
            }
            else{
                output = l.backward(output,this.learningRate);
            }
        }
    }

    public void build(Loss lossFun){
        this.lossFun = lossFun;
    }

    public void build(Loss lossFun,Regularizer reg){
        this.lossFun = lossFun;
        this.regulizer = reg;
    }

    public void fit(int epochs,Matrix X,Matrix y,double learningRate){
        Matrix y_h;
        Matrix outGrad;
        this.learningRate = learningRate;
        for(int i = 0; i < epochs; i++){
            y_h = run(X);
            double loss = lossFun.forward(y,y_h);

            if(regulizer != null){
                loss += regulizer.computeLoss();
            }

            System.out.println("Epoch: " +i+ " Loss: " + loss);
            outGrad = lossFun.backward();
            backwardRun(outGrad);

        }


    }


}
