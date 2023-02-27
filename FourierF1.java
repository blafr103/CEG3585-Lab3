public class FourierF1 {

  public static void main(String[] args) {

    double pi = Math.PI;
    double a0 = 0.0;
    double an = 0.0;
    double sum = 0.0;
    
    for (int n = 1; n <= 100; n++) {
      double bn = (-20 * Math.cos(n * pi)) / (n * pi);
      sum += a0 + an*Math.cos(n*Math.PI)+bn*Math.sin(n*Math.PI);
    }
    
    System.out.println("Sum of Fourier series up to 100th harmonic: " + sum);
  }
}