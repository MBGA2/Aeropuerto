package Vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JViewport;

public class ImageViewport extends JViewport {

	private static final long serialVersionUID = 1L;

	public ImageViewport() {
	}

	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage background = null;
		try {
			background = ImageIO.read(MainView.class.getResource("/Iconos/map.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		background = resize(background, 1353, 703);
		super.paintComponent(g);
		if (background != null) {
			Rectangle bounds = getViewRect();
			int x = Math.max(0, (bounds.width - background.getWidth()) / 2);
			int y = Math.max(0, (bounds.height - background.getHeight()) / 2);
			g.drawImage(background, x, y, this);
		}
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}