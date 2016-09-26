package de.janjaap;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * Created by Jan Jaap on 23.09.2016.
 */
public class Main {
    public static int[][] FeldMatrix = new int[7][5];
    public static HashMap<String, JLabel> Felder = new HashMap<>();
    public static int activePlayer = 1;
    public static JLabel statusLbl = new JLabel("Next Player: 1");

    public static void main (String[] Args)
    {
        erstellen();
    }

    public static void erstellen()
    {
        for (int y = 0; y < 5; y++)
        {
            for (int x = 0; x < 7; x++)
            {
                FeldMatrix[x][y] = 0;
            }
            System.out.println();
        }

        JPanel Status = new JPanel(new GridLayout(1,1));
        statusLbl.setOpaque(true);
        statusLbl.setBackground(Color.BLACK);
        statusLbl.setForeground(Color.white);

        Status.add(statusLbl);

        JPanel SlctBtns = new JPanel(new GridLayout(1,7));
        HashMap<String, JButton> slctCol = new HashMap<>();

        for (int i = 0; i < 7; i++)
        {
            slctCol.put("B" + i, new JButton("\\/"));
            JButton Button = slctCol.get("B" + i);
            Button.setName("B" + i);
            final int Col = i;
            Button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    addCoin(Col);
                }
            });
            SlctBtns.add(Button);
        }

        Border border = LineBorder.createGrayLineBorder();

        JPanel Feld = new JPanel(new GridLayout(5,7));
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 7; y++)
            {
                Felder.put(x + "," + y, new JLabel(" ", SwingConstants.CENTER));
                Feld.add(Felder.get(x + "," + y));
                Felder.get(x + "," + y).setBorder(border);
                Felder.get(x + "," + y).setBackground(Color.DARK_GRAY);
                Felder.get(x + "," + y).setOpaque(true);
            }
        }


        JFrame Fenster = new JFrame("Vier Gewinnt");

        Fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Fenster.add(SlctBtns, BorderLayout.PAGE_START);
        Fenster.add(Feld, BorderLayout.CENTER);
        Fenster.add(Status, BorderLayout.PAGE_END);

        Fenster.pack();
        Fenster.setVisible(true);
    }

    public static void addCoin(int Col)
    {
        int height;
        for (height = 4; height >= 0; height--)
        {
            JLabel Feld = Felder.get(height + "," + Col);
            if (Feld.getBackground() == Color.DARK_GRAY)
            {
                if (activePlayer == 1)
                {
                    Feld.setBackground(Color.BLUE);
                    activePlayer = 2;
                    statusLbl.setText("Next Player: 2");
                    statusLbl.setBackground(Color.BLACK);
                }else {
                    Feld.setBackground(Color.RED);
                    activePlayer = 1;
                    statusLbl.setText("Next Player: 1");
                    statusLbl.setBackground(Color.BLACK);
                }
                WinCheck(Col, height);
                break;
            }
        }

        if (height < 0)
        {
            statusLbl.setText("Spalte bereits Voll!");
            statusLbl.setBackground(Color.red);
        }
    }

    public static void WinCheck (int Col, int Row)
    {
        if (Col < 4)
        {
            //Nach Rechts
            if (Row < 2)
            {
                //Nach Unten
            }
            if (Row > 2)
            {
                //Nach Oben
            }
        }
        if (Col > 3)
        {
            //Nach Links
            if (Row < 2)
            {
                //Nach Unten
            }
            if (Row > 2)
            {
                //Nach Oben
            }
        }
    }
}
