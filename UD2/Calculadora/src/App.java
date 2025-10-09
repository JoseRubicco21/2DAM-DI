import java.util.List;

import javax.swing.SwingUtilities;

import controller.CalculadoraController;
import libs.logger.Logger;
import libs.logger.enums.LogLevel;
import model.HistoryEntry;
import view.CalculatorView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorView());
    }
}
