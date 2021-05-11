/**
 *
 * @author curstinjr
 */
package com.curstinjr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class SwingForm extends JFrame
{
    
    // index for the array: set it 0
    int i = personList().indexOf(0);
    
    JPanel headerPanel;
    JPanel mainPanel;
    JPanel footerPanel;
    
    JLabel lblLogo;
    JLabel lblHeadName;
    
    JLabel lblName;
    JLabel lblSurname;
    JLabel lblAge;
    JLabel lblEmail;
    
    JLabel lblRecord;
    
    JTextField txtName;
    JTextField txtSurname;
    JTextField txtAge;
    JTextField txtEmail;
    
    JButton btnNext;
    JButton btnPrev;
    JButton btnExit;
    
    public SwingForm()
    {
        initComponents();
    }
    
    public void initComponents()
    {
        
        UIManager.put("Label.foreground", Color.decode(white));
        UIManager.put("Label.font", bodyfont);
        
        UIManager.put("TextField.font", bodyfont);
        UIManager.put("TextField.foreground", Color.decode(white));
        UIManager.put("TextField.background", Color.decode(gray));
        UIManager.put("TextField.border", BorderFactory.createMatteBorder(
                0, 0, 3, 0, Color.decode(blue)));
        UIManager.put("TextField.caretForeground", Color.decode(white));
        
        UIManager.put("Button.background", Color.decode(blue));
        UIManager.put("Button.font", bodyfont);
        UIManager.put("Button.border", BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), 
                BorderFactory.createMatteBorder(3, 3, 3, 3, Color.decode(blue))));
        UIManager.put("Button.select", Color.decode(darkblue));
        UIManager.put("Button.foreground", Color.decode(white));
        
        headerPanel = new JPanel();
        mainPanel = new JPanel();
        footerPanel = new JPanel();
        
        lblLogo = new JLabel(new ImageIcon("src/resources/icons8-male-user-48.png"));
        lblHeadName = new JLabel("User List");
        
        lblName = new JLabel("Name:");
        lblSurname = new JLabel("Surname:");
        lblAge = new JLabel("Age:");
        lblEmail = new JLabel("Email:");
        
        String record = String.format("Record %d of %d", i+1, personList().size());
        lblRecord = new JLabel(record);
        
        
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtAge = new JTextField();
        txtEmail = new JTextField();
        
        txtName.setColumns(15);
        txtSurname.setColumns(15);
        txtAge.setColumns(15);
        txtEmail.setColumns(15);
        
        btnNext = new JButton("Next");
        btnPrev = new JButton("Previous");
        btnExit = new JButton("Exit");
        
    }
    
    public void setGUI()
    {
        // header
        this.add(headerPanel, BorderLayout.NORTH);
        headerPanel.add(lblLogo);
        headerPanel.add(lblHeadName);
        headerPanel.setBackground(Color.decode(darkgray));
        
        // footer
        this.add(footerPanel, BorderLayout.SOUTH);
        footerPanel.add(btnPrev);
        // add actionListener to button
        btnPrev.addActionListener(new PrevHandler());
        
        footerPanel.add(btnNext);
        // add actionListener to button
        btnNext.addActionListener(new NextHandler());
        footerPanel.setBackground(Color.decode(darkgray));
        
        footerPanel.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SwingForm.this.dispose();
            }
        });

        // mainPanel
        this.add(mainPanel);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 22, 10);
        mainPanel.add(lblName, gbc);
        mainPanel.add(txtName);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblSurname, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtSurname, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblAge, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtAge, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtEmail, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(lblRecord, gbc);
        
        // mainPanel settings
        mainPanel.setBackground(Color.decode(gray));
        
        // frame properties
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    /**
     * An arrayList method with all people objects in the arrayList
     * @return arrayList with people objects
     */
    public ArrayList<Person> personList()
    {
        ArrayList<Person> pList = new ArrayList<>();
        
        Person p1 = new Person("Jack", "Samuel", 22, "jack@thunderbolt.com");
        Person p2 = new Person("Joe", "Biden", 57, "joe@gmail.com");
        Person p3 = new Person("Priyanka", "Chopra", 31, "pri@thunderbolt.com");
        Person p4 = new Person("Candice", "Mandice", 19, "candy@gmail.com");
        Person p5 = new Person("Flappy", "Mappy", 26, "flap@thunderbolt.com");
        
        
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);
        pList.add(p5);
        
        return pList;
    }
    
    public void showPerson()
    {
        txtName.setText(personList().get(i).name);
        txtSurname.setText(personList().get(i).surname);
        txtAge.setText(String.valueOf(personList().get(i).age));
        txtEmail.setText(personList().get(i).email);
        
        String lblrecord = String.format("Record %d of %d", i+1, personList().size());
        lblRecord.setText(lblrecord);
    }
    
    /**
     * class that handles the Prev button function
     */
    class PrevHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (--i < 0)
            {
                i = personList().size() - 1;
            }
            showPerson();
        }
        
    }
    
    
    /**
     * class that handles the Next button function
     */
    class NextHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            
            if (++i == personList().size())
            {
                i = 0;
            }
            showPerson();
        }
    }
    
    public static void main(String[] args) 
    {
        
        try {
            UIManager.getCrossPlatformLookAndFeelClassName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new SwingForm().setGUI();
    }
    
    // properties
    /*-----------*/
    // Colors
    String gray = "#444444";
    String darkgray = "#222222";
    String white = "#ffffff";
    String blue = "#2069e0";
    String darkblue = "#082c6c";
    // fonts
    String font = "Arial";
    Font bodyfont = new Font(font, Font.BOLD, 20);
}
