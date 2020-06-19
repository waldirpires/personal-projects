package br.org.eteg.curso.javaoo.capitulo11.applet;

import java.applet.Applet;
import java.awt.Graphics;

public class MeuApplet extends Applet {

	@Override
	public void paint(Graphics g) {
		g.drawString("Hello World", 15, 15);
		g.draw3DRect(100, 100, 30, 50, false);
		g.drawOval(50, 50, 50, 50);
	}
}
