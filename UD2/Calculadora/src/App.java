import controller.CalculadoraController;

public class App {
    public static void main(String[] args) throws Exception {
        CalculadoraController calc = new CalculadoraController();
        calc.parseOperation("2+3*4-1/2");
        
    }
}
