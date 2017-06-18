import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.datatransfer.*;
import java.awt.print.PrinterJob;
import javax.swing.JTextArea;
import java.util.*;
import java.awt.FileDialog;
import java.awt.print.PageFormat;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.awt.event.*;
import java.io.FileReader;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.GraphicsEnvironment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class A extends JFrame implements ActionListener
{

    JFrame flogin=new JFrame("Untitled java Notepad");
     //JPanel P=new JPanel();
    String Filename;
    String filename;
  final Clipboard clip= Toolkit.getDefaultToolkit().getSystemClipboard();  
    JMenuBar mb=new JMenuBar(); 
   JTextArea txtArea=new JTextArea(0,0); 
   JScrollPane scroll=new JScrollPane(txtArea);
    JMenu mfile=new JMenu("File");
    JMenuItem mmNew=new JMenuItem("New"); 
    //mfile.addSeparator();
    JMenuItem mmOpen=new JMenuItem("Open");
    JMenuItem mmSave=new JMenuItem("Save"); 
    JMenuItem mmSaveAs=new JMenuItem("SaveAs");
    JMenuItem mmPG=new JMenuItem("PageSetup..");
    JMenuItem mmPrint=new JMenuItem("Print..");
    JMenuItem mmExit=new JMenuItem("Exit");
    //MenuShortcutKeyMask(); 
    JMenuItem mmUndo=new JMenuItem("Undo");
    JMenuItem mmCut=new JMenuItem("Cut");
    JMenuItem mmCopy=new JMenuItem("Copy");
    JMenuItem mmPaste=new JMenuItem("Paste");
    JMenuItem mmDelete=new JMenuItem("Delete");
    JMenuItem mmFind=new JMenuItem("Find");
    JMenuItem mmFindNext=new JMenuItem("FindNext");
    JMenuItem mmReplace=new JMenuItem("Replace");
    JMenuItem mmGoto=new JMenuItem("Goto");
    JMenuItem mmSelectAll=new JMenuItem("" +
            "SelectAll");
    JMenuItem mmTIMEDATE=new JMenuItem("Time/Date");
    JTextField s = new JTextField(10);
   JCheckBoxMenuItem WORD =new JCheckBoxMenuItem("WordWrap");  
    JMenuItem mmFONT=new JMenuItem("Font..");
    JCheckBoxMenuItem STATUS=new JCheckBoxMenuItem("StatusBar");  
    JMenuItem mmHT=new JMenuItem("Help Topics");
    JMenuItem mmAN=new JMenuItem("About Notepad");
    JMenu medit=new JMenu("Edit");
    JMenu mformat=new JMenu("Format");
    JMenu mview=new JMenu("View");
    JMenu mhelp=new JMenu("Help");
    boolean opened=false;
    String wholeText,findString,fileName=null;
    int ind=0;

    public A() 
    {


    flogin.setSize (500,500);
    flogin.setVisible (true);
    //P.setSize(100,100); 
    //P.setBackground(Color.WHITE);
    flogin.setJMenuBar(mb);
    txtArea.setLineWrap(true);
    WORD.setState(true);
    getContentPane().setLayout(new BorderLayout());
   getContentPane().add(scroll,BorderLayout.CENTER);
    flogin.add(txtArea); 
    mb.add(mfile);
    mmNew.addActionListener(new New());
    mfile.add(mmNew);  
    mmOpen.addActionListener(new Open());
    mfile.add(mmOpen);
    mfile.add(mmSave);
    mmSaveAs.addActionListener(new SaveAs());
    mfile.add(mmSaveAs);
    mfile.addSeparator();
    mfile.add(mmPG);
    mfile.add(mmPrint);
    mfile.addSeparator();
    mmExit.addActionListener(new Exit());
    mfile.add(mmExit);

    medit.add(mmUndo);
    medit.addSeparator();
    medit.add(mmCut);
    medit.add(mmCopy);
    medit.add(mmPaste);
    medit.add(mmDelete);
   mmDelete.addActionListener(this);
    medit.addSeparator();
    medit.add(mmFind);
    mmFind.addActionListener(this);
    medit.add(mmFindNext);
    mmFindNext.addActionListener(this);
    medit.add(mmReplace);
    medit.add(mmGoto);
    medit.addSeparator();
    medit.add(mmSelectAll);
   mmSelectAll.addActionListener(this);
   flogin.add(s);
    medit.add(mmTIMEDATE);
    mmTIMEDATE.addActionListener(this);
    mformat.add(mmFONT);
    mmFONT.addActionListener(this);
   mformat.add(WORD);
    WORD.addActionListener(this);
    mview.add(STATUS);
   STATUS.addActionListener(this);
    mhelp.add(mmHT);
    mhelp.addSeparator();
    mhelp.add(mmAN);
    mb.add(medit);
    mb.add(mformat);
    mb.add(mview);
    mb.add(mhelp); 
     mmAN.addActionListener(this);
    //mmSelectAll.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e2) {if(e2.getSource()==SelectAll)txtArea.selectAll();}});
    mmCopy.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) {String selection = txtArea.getSelectedText();StringSelection data = new StringSelection(selection);clip.setContents(data, data); }});
    mmPaste.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent actionEvent) {Transferable clipData = clip.getContents(clip);try {if(clipData.isDataFlavorSupported(DataFlavor.stringFlavor)) {String s = (String)(clipData.getTransferData(DataFlavor.stringFlavor));txtArea.replaceSelection(s);}} catch (Exception a){}}}); 
    mmCut.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent a){ String selection = txtArea.getSelectedText(); StringSelection data = new StringSelection(selection);clip.setContents(data, data);txtArea.replaceRange("",txtArea.getSelectionStart(),txtArea.getSelectionEnd());}});
    //mmDelete.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e1){String selection = txtArea.getSelectedText(); StringSelection data = new StringSelection(selection);clip.setContents(data, data);txtArea.replaceRange("",txtArea.getSelectionStart(),txtArea.getSelectionEnd());}});
    mmPrint.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent a){PrinterJob pj = PrinterJob.getPrinterJob(); pj.printDialog();}});
    //mmFont.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent a){JFontChooser fd = new JFontChooser(this,txtDoc.getFont());fd.show();if(fd.getReturnStatus() == fd.RET_OK){txtDoc.setFont(fd.getFont());}fd.dispose();}});
   mmPG.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent a){PrinterJob pj = PrinterJob.getPrinterJob();{PageFormat pf = pj.pageDialog(pj.defaultPage());}}});
  mylistener mylist = new mylistener();
        addWindowListener(mylist);  

    }
    public void actionPerformed(ActionEvent e)
    {
        if((e.getSource()==mmDelete))
        {
            txtArea.replaceSelection(null);
        }
        if((e.getSource()==mmSelectAll))
        {
            txtArea.selectAll();
        }
        if((e.getSource()==WORD))
        {
            if(WORD.isSelected())
                txtArea.setLineWrap(true);
            else
                txtArea.setLineWrap(false);
        }
        if((e.getSource()==mmFONT))
        {
            FONT f=new FONT();
        }
        if((e.getSource()==mmFind))
        {
            wholeText=txtArea.getText();
           findString=JOptionPane.showInputDialog(null,"Find what","Find",JOptionPane.INFORMATION_MESSAGE);
            ind = wholeText.indexOf(findString,0);
            txtArea.setCaretPosition(ind);
            txtArea.setSelectionStart(ind);
           int a = ind+findString.length();
           //txtArea.SelectionEnd( a );
           txtArea.setSelectionEnd(a);
        }
        if((e.getSource()==mmFindNext))
        {
            wholeText=txtArea.getText();
            findString = JOptionPane.showInputDialog(null,"Find what","Find Next",JOptionPane.INFORMATION_MESSAGE);
            ind = wholeText.indexOf(findString,txtArea.getCaretPosition());
            txtArea.setCaretPosition(ind);
            txtArea.setSelectionStart(ind);
            txtArea.setSelectionEnd(ind+findString.length());
        }
      if((e.getSource()==mmAN))
      {
            JOptionPane.showMessageDialog(null,"This is a simple Notepad Application built using java.","About Notepad",JOptionPane.INFORMATION_MESSAGE);
      }
    }

    class FONT extends JFrame implements ActionListener
    {
        String availableFontString[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JList fontList=new JList(availableFontString);
        JLabel fontLabel=new JLabel("Font");
        JTextField valueFont=new JTextField("Arial");
        JScrollPane fontPane= new JScrollPane(fontList); 
        String fontStyleString[]={"Normal","Bold","Italic","Bold Italic"};
        JList styleList=new JList(fontStyleString);
        JLabel styleLabel= new JLabel("Style");
        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        JScrollPane stylePane=new JScrollPane(styleList,v,h);
        JTextField valueStyle=new JTextField("Normal");
        String fontSizeString[]={"8","10","12","14","16","18","20","22","24","28"};
        JList sizeList=new JList(fontSizeString);
        JLabel sizeLabel= new JLabel("Font size");
        JScrollPane sizePane=new JScrollPane(sizeList);
        JTextField valueSize=new JTextField("12");
        JButton okButton= new JButton("Ok");
        JButton cancelButton= new JButton("Cancel");
        JLabel sampleLabel=new JLabel("Sample: ");
        JTextField sample=new JTextField(" AaBbCc");
        Font selectedFont; 
        public FONT()
        {
            setSize(500,300);
            setTitle("Font");
            setVisible(true);
            sample.setEditable(false);
            getContentPane().setLayout(null);
            fontLabel.setBounds(10,10,170,20);
            valueFont.setBounds(10,35,170,20);
            fontPane.setBounds(10,60,170,150);

            styleLabel.setBounds(200,10,100,20);
            valueStyle.setBounds(200,35,100,20);
            stylePane.setBounds(200,60,100,150);

            sizeLabel.setBounds(320,10,50,20);
            valueSize.setBounds(320,35,50,20);
            sizePane.setBounds(320,60,50,150);

            okButton.setBounds(400,35,80,20);
            cancelButton.setBounds(400,60,80,20);

            sampleLabel.setBounds(150,235,100,30);
            sample.setBounds(200,235,100,30);

            getContentPane().add(fontLabel);
            getContentPane().add(fontPane);
            getContentPane().add(valueFont);


            getContentPane().add(styleLabel);
            getContentPane().add(stylePane);
            getContentPane().add(valueFont);

           getContentPane().add(sizeLabel);
           getContentPane().add(sizePane);
           getContentPane().add(valueSize);

           getContentPane().add(okButton);
           getContentPane().add(cancelButton);
           getContentPane().add(sampleLabel);
           getContentPane().add(sample); 
           okButton.addActionListener(this);
           cancelButton.addActionListener(this);

           fontList.addListSelectionListener(new ListSelectionListener(){
               public void valueChanged(ListSelectionEvent event)
               {
                   if(!event.getValueIsAdjusting())
                   {
                       valueFont.setText(fontList.getSelectedValue().toString());
                       selectedFont= new Font(valueFont.getText(),styleList.getSelectedIndex(),Integer.parseInt(valueSize.getText()));
                       sample.setFont(selectedFont);

                   }
               }
           });
           styleList.addListSelectionListener(new ListSelectionListener()
           {
               public void valueChanged(ListSelectionEvent event)
               {
                   if(!event.getValueIsAdjusting())
                   {
                       valueStyle.setText(styleList.getSelectedValue().toString());
                       selectedFont= new Font(valueFont.getText(),styleList.getSelectedIndex(),Integer.parseInt(valueSize.getText()));
                       sample.setFont(selectedFont);
                   }
               }
           });
           sizeList.addListSelectionListener(new ListSelectionListener()
           {
               public void valueChanged(ListSelectionEvent event)
               {
                   if(!event.getValueIsAdjusting())
                   {
                       valueSize.setText(sizeList.getSelectedValue().toString());
                       selectedFont= new Font (valueFont.getText(),styleList.getSelectedIndex(),Integer.parseInt(valueSize.getText()));
                       sample.setFont(selectedFont);
                   }
               }
           });
        }
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource()==okButton)
            {
                selectedFont= new Font(valueFont.getText(),styleList.getSelectedIndex(),Integer.parseInt(valueSize.getText()));
                txtArea.setFont(selectedFont);
                setVisible(false);

            }
        }
    }
    class mylistener extends WindowAdapter
         {
         public void windowClosing (WindowEvent e)
             {
             System.exit(0);
         }
     }

     class New implements ActionListener
         {
         public void actionPerformed(ActionEvent e)
             {
             txtArea.setText(" ");
             setTitle(filename);
         }
     }

     class Open implements ActionListener
         {
         public void actionPerformed(ActionEvent e)
             {
             FileDialog fd = new FileDialog(A.this, "select File",FileDialog.LOAD);
             fd.show();
             if (fd.getFile()!=null)
                 {
                 filename = fd.getDirectory() + fd.getFile();
                 setTitle(filename);
                 ReadFile();
             }
             txtArea.requestFocus();
         }
     }

     class SaveAs implements ActionListener
         {
         public void actionPerformed(ActionEvent e)
             {
             FileDialog fd = new FileDialog(A.this,"Save File",FileDialog.SAVE);
             fd.show();
             if (fd.getFile()!=null)
                 {
                 filename = fd.getDirectory() + fd.getFile();
                 setTitle(filename);
                 try
                     {
                     DataOutputStream d = new DataOutputStream(new FileOutputStream(filename));
                     String line = txtArea.getText();
                     BufferedReader br = new BufferedReader(new StringReader(line));
                     while((line = br.readLine())!=null)
                         {
                         d.writeBytes(line + "\r\n");
                         d.close();
                     }
                 }
                 catch(Exception ex)
                     {
                     System.out.println("File not found");
                 }
                 txtArea.requestFocus();
             }
         }
     }

     class Exit implements ActionListener
         {
         public void actionPerformed(ActionEvent e)
             {
             System.exit(0);
         }
     }
     void ReadFile()
         {
         BufferedReader d;
         StringBuffer sb = new StringBuffer();
         try
             {
             d = new BufferedReader(new FileReader(filename));
             String line;
             while((line=d.readLine())!=null)
             sb.append(line + "\n");
             txtArea.setText(sb.toString());
             d.close();
         }
         catch(FileNotFoundException fe)
             {
             System.out.println("File not Found");
         }
         catch(IOException ioe){}
     }
      public static void main(String[] args) 
    {


        A a=new A(); 



    }
      class TIMEDATE implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
    {

        try 
        {
        Thread.sleep(1000);
        Calendar cal = new GregorianCalendar();
        String hour = String.valueOf(cal.get(Calendar.HOUR));
        String minute = String.valueOf(cal.get(Calendar.MINUTE));

        String m1 = String.valueOf(cal.get(Calendar.AM_PM));
        String d1 = String.valueOf(cal.get(Calendar.DATE));
        String d2 = String.valueOf(cal.get(Calendar.MONTH));
        String d3 = String.valueOf(cal.get(Calendar.YEAR));
       s.setText(hour + ":" + minute+" " +d1 +"/"+d2+"/"+d3);
        }
        catch(Exception e1)
        {

        }

     }

      }
     }