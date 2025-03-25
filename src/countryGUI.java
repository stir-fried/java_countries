//Javier Colon

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;
import java.util.*;


public class countryGUI {

    private JPanel panel1;
    private JLabel infoJL;
    private JLabel imageJL;
    private JButton nextJB;

    private country[] countryArray = new country[10];
    private int currentCountry = -1;

    public countryGUI() throws IOException {
        ImageIcon img = new ImageIcon("Resources/worldmap.jpg");
        imageJL.setIcon(img);
        infoJL.setText("Countries!");
        loadFile();


        nextJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCountry = (currentCountry + 1) % countryArray.length;
                updateGUI();
            }
        });
    }

    private void loadFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Resources/countries-data.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(",");
            countryArray[i] = new country(data[0], data[1], data[2], data[3]);
        }
    }

    private void updateGUI(){
        country current = countryArray[currentCountry];
        infoJL.setText("<html>" + current.getName() + "'s capital is " + current.getCapital() + "<br>" +
                " and it's primary language is " + current.getLanguage() + "<html/>");
        ImageIcon img = new ImageIcon("Resources/" + current.getImage());
        imageJL.setIcon(img);
    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Countries");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new countryGUI().panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400, 300
        );
    }
}
