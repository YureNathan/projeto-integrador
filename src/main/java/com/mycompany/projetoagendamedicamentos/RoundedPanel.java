package com.mycompany.projetoagendamedicamentos;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int arcWidth = 30;
    private int arcHeight = 30;

    public RoundedPanel() {
        super();
        setOpaque(false);
        this.backgroundColor = new Color(230, 230, 250); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.dispose();
    }


    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setArc(int arc) {
        this.arcWidth = arc;
        this.arcHeight = arc;
        repaint();
    }
}
