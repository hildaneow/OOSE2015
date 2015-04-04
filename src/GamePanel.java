import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private List<Brick> bricks = new ArrayList<Brick>();
	private List<Ball> balls;

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        createBricks();
        drawBricks(g);
        drawPlayer(g);
    }
	
	private void createBricks() {
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				Brick brick = new Brick(x*5+50, y*5+50);
				bricks.add(brick);
			}
		}
	}
	
	private void drawBricks(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);
        
		for (Brick brick : bricks) {
			Rectangle2D rec = new Rectangle2D.Double(brick.Height, brick.Width, brick.posX, brick.posY);
			g2d.draw(rec);
		}
	}
	
	private void drawPlayer(Graphics g) {
		
	}
	
	private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at
                    = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
    }
}
