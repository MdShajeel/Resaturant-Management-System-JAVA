package invoice;
import java.io.*; 
import java.awt.*; 
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class InvoicePrint
{
    public InvoicePrint()  //--->Constructor
    {
        JFrame f =new JFrame("Invoice");
        try
        {
            JTextArea ta=new JTextArea();
            FileReader readTextFile=new FileReader("Ordermenu.txt");
            Scanner fileReaderScan=new Scanner(readTextFile);
            String filecontent="";
            while(fileReaderScan.hasNextLine())
            {
                String temp=fileReaderScan.nextLine()+"\n";
                filecontent=filecontent+temp;
            }
            ta.setText(filecontent);
            f.setLayout(new FlowLayout(FlowLayout.CENTER)); 
            Color c =new Color(115, 210, 190);
            f.getContentPane().setBackground(c);
            ta.setForeground(Color.BLACK);
            ta.setFont(new Font("Courier", Font.BOLD, 20));
            ta.setEditable(false); 
            ta.setOpaque(false);
            f.add(ta); 
            f.setSize(600,600); 
            f.setVisible(true); 
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fileReaderScan.close(); 
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }  
    public InvoicePrint(String s)
    {
        JFrame f =new JFrame("Menu");
        try
        {
            JTextArea ta=new JTextArea();
            FileReader readTextFile=new FileReader("Displaymenu.txt");
            Scanner fileReaderScan=new Scanner(readTextFile);
            String filecontent="";
            while(fileReaderScan.hasNextLine())
            {
                String temp=fileReaderScan.nextLine()+"\n";
                filecontent=filecontent+temp;
            }
            ta.setText(filecontent);
            fileReaderScan.close();
            f.setVisible(true); 
            ta.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.black));
            f.setLayout(new FlowLayout(FlowLayout.CENTER)); 
            Color c =new Color(140, 170, 215);
            f.getContentPane().setBackground(c);
            ta.setForeground(Color.BLACK);
            ta.setOpaque(false);
            ta.setFont(new Font("Courier", Font.BOLD, 20));
            ta.setEditable(false); 
            f.add(ta); 
            f.setSize(600,600);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fileReaderScan.close();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
}