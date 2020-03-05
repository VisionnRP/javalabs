import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
class Skeleton extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;
    private static int padding = 350;

    private double scale = 1;
    private double delta = 0.01;
    private double dx = 1;
    private double tx = 0;
    private double dy = 1;
    private double ty = 6;
    private double angle = 0;

    Timer timer;
    public Skeleton() {
        timer = new Timer(10,this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh =
        new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setBackground(new Color(71,92,10));

        g2d.clearRect(0, 0, maxWidth, maxHeight);
        BasicStroke bs3 = new BasicStroke(16, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs3);
        g2d.drawRect(padding, padding, maxWidth - padding * 2, maxHeight - padding * 2);
        BasicStroke bs4 = new BasicStroke();
        g2d.setStroke(bs4);
        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);
        int radius  = (int) (scale * 150);
        int radius2 = (int) (scale * 130);
        int radius3 = (int) (scale * 15);
        g2d.rotate(angle,maxWidth/2,maxHeight/2);
        g2d.fillOval(maxWidth/2 - radius/2,maxHeight/2-radius/2,radius,radius);
        g2d.setColor(Color.white);
        g2d.fillOval((maxWidth/2 - radius2/2),(maxHeight/2 - radius2/2),radius2,radius2);
        g2d.setColor(Color.yellow);
        g2d.fillOval(((maxWidth/2 + (int) (50 * scale))- radius3/2),(maxHeight/2)- radius3/2,radius3,radius3);
        g2d.fillOval((maxWidth/2) - radius3/2,(maxHeight/2 + (int)(50 * scale)- radius3/2),radius3,radius3);
        g2d.fillOval(((maxWidth/2 - (int) (50 * scale))- radius3/2),maxHeight/2 - radius3/2,radius3,radius3);
        g2d.fillOval(maxWidth/2 - radius3/2 ,((maxHeight/2 - (int)(50 * scale))- radius3/2),radius3,radius3);
        g2d.setColor(Color.black);
        Polygon arrowSmall = new Polygon();
        arrowSmall.addPoint(maxWidth/2 - (int)(scale * 5),maxHeight/2);
        arrowSmall.addPoint(maxWidth/2, maxHeight/2 + (int)(scale * 5));
        arrowSmall.addPoint(maxWidth/2 + (int)(scale * 30),maxHeight/2);
        arrowSmall.addPoint(maxWidth/2,maxHeight/2 - (int)(scale * 5));
        g2d.drawPolygon(arrowSmall);

        Polygon arrowBig = new Polygon();
        arrowBig.addPoint(maxWidth/2,maxHeight/2 + (int)(scale * 5));
        arrowBig.addPoint(maxWidth/2 - (int)(scale * 5),maxHeight/2);
        arrowBig.addPoint(maxWidth/2,maxHeight/2 - (int)(scale * 55));
        arrowBig.addPoint(maxWidth/2 +(int)(scale * 5),maxHeight/2);
        g2d.fillPolygon(arrowBig);


    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Привіт, Java 2D!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
        frame.add(new Skeleton());
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if ( scale < 0.01 ) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        if ( tx < -maxWidth/2 ) {
            dx = -dx;
        } else if ( tx > maxWidth/2 ) {
            dx = -dx;
        }

        if ( ty < -maxHeight/2 ) {
            dy = -dy;
        } else if ( ty > maxHeight/2 ) {
            dy = -dy;
        }
        scale += delta;
        angle +=0.01;
        tx += dx;
        ty += dy;

        repaint();
    }
}
