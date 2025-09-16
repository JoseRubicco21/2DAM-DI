import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class App {
    public static void main(String[] args) throws Exception {
      final int MARGIN = 10;
      final int WIDTH = 800;
      final int HEIGHT = 600; 
      final Color BACKGROUND_COLOR_A = new Color(205, 92, 92);
      final Color BACKGROUND_COLOR_B = new Color(189, 183, 107);
        
      PanelBuilder pb = new PanelBuilder();
     
      JFrame frame = new JFrame("Estructura web basica");
      frame.setSize(WIDTH, HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(null);
      frame.setVisible(true);

      // Main header at top
      JPanel headerPanel = pb.createPanel(MARGIN, MARGIN, WIDTH - 2 * MARGIN, 100, BACKGROUND_COLOR_A, "Header");
      frame.add(headerPanel);
      
      // Calculate positions based on actual dimensions
      int headerHeight = 100;
      int navWidth = 200;
      int asideWidth = 200;
      int sectionWidth = WIDTH - navWidth - asideWidth - 4 * MARGIN;
      int middleHeight = 300;
      
      // Section specific heights and widths
      int sectionHeaderPadding = 30;
      
      // Navigation (left column)
      JPanel navPanel = pb.createPanel(MARGIN, MARGIN * 2 + headerHeight, navWidth, middleHeight, BACKGROUND_COLOR_A, "Nav");
      frame.add(navPanel);
      
      // Section (middle column) with its sub-elements
      int sectionX = MARGIN * 2 + navWidth;
      int sectionY = MARGIN * 2 + headerHeight;
      
      // Section header
      JPanel sectionHeaderPanel = pb.createPanel(sectionX+MARGIN, sectionY + sectionHeaderPadding + MARGIN, sectionWidth-2*MARGIN, 60, BACKGROUND_COLOR_B, "Section Header");
      frame.add(sectionHeaderPanel);
      
      // Section article
      JPanel articlePanel = pb.createPanel(sectionX+MARGIN,  sectionHeaderPanel.getY() +  sectionHeaderPadding*2 + MARGIN, sectionWidth-2*MARGIN, 100, BACKGROUND_COLOR_B, "Article");
      frame.add(articlePanel);
      
      // Section footer
      JPanel sectionFooterPanel = pb.createPanel(sectionX+MARGIN,  articlePanel.getY() + articlePanel.getHeight() + MARGIN, sectionWidth-2*MARGIN, 60, BACKGROUND_COLOR_B, "Section Footer");
      frame.add(sectionFooterPanel);
      
      // Section main container 
      JPanel mainSectionPanel = pb.createPanel(sectionX, sectionY, sectionWidth, middleHeight, BACKGROUND_COLOR_A, "Main Section");
      frame.add(mainSectionPanel);

      // Aside (right column)
      int asideX = MARGIN * 3 + navWidth + sectionWidth;
      JPanel asidePanel = pb.createPanel(asideX, sectionY, asideWidth, middleHeight, BACKGROUND_COLOR_A, "Aside");
      frame.add(asidePanel);
      
      // Main footer at bottom
      JPanel footerPanel = pb.createPanel(MARGIN, MARGIN * 3 + headerHeight + middleHeight, WIDTH - 2 * MARGIN, 100, BACKGROUND_COLOR_A, "Footer");
      frame.add(footerPanel);

    }


}
