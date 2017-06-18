package notepad;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class menuing extends JFrame implements ActionListener {

    JMenuBar me;
    JMenu files, edit, format, view, help;
    JMenuItem menu, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10, menu11, menu12, menu13, menu14, menu15, menu16, menu17, menu18, menu19, menu20, menu21;
    JMenuItem menu22, redo;
    JToolBar tb;
    JPanel p1, p2;
    JTextArea t1, t2;
    String filename = "New";
    JCheckBox wrap, status;
    JScrollPane jsp;
    JLabel l1;
    JTabbedPane j1;
    Clipboard clip = getToolkit().getSystemClipboard();
    public int count = 0, countstatus = 0;
    public int n = 0;
    UndoManager undo = new UndoManager();

    public menuing(String d) {
        super(d);

        me = new JMenuBar();
        this.add(me, BorderLayout.NORTH);
        files = new JMenu("File");
        files.setMnemonic(KeyEvent.VK_F);
        me.add(files);
        menu = new JMenuItem("New                                             ");
        menu.addActionListener(new New());
        menu.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        files.add(menu);
        edit = new JMenu("Edit");
        me.add(edit);
        edit.setMnemonic(KeyEvent.VK_E);
        menu1 = new JMenuItem("Open");
        menu1.addActionListener(new Open());
        menu1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        files.add(menu1);
        menu2 = new JMenuItem("Save");
        menu2.addActionListener(new Save());
        menu2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        files.add(menu2);
        menu3 = new JMenuItem("Save As");
        menu3.addActionListener(new SaveAs());
        files.add(menu3);
        files.addSeparator();
        menu4 = new JMenuItem("Page setup");
        files.add(menu4);
        menu5 = new JMenuItem("Print");
        menu5.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        files.add(menu5);
        files.addSeparator();

        menu6 = new JMenuItem("Exit");
        menu6.addActionListener(new Exit());
        files.add(menu6);

        menu8 = new JMenuItem("Undo                              ");
        menu8.addActionListener(this);
        menu8.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        edit.add(menu8);
        redo = new JMenuItem("Redo                      ");
        redo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        edit.add(redo);
        redo.addActionListener(this);
        edit.addSeparator();
        menu9 = new JMenuItem("Cut            ");
        menu9.addActionListener(new Cut());
        menu9.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(menu9);
        menu10 = new JMenuItem("Copy             ");
        menu10.addActionListener(new Copy());
        menu10.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(menu10);
        edit.addSeparator();
        menu11 = new JMenuItem("Paste             ");
        menu11.addActionListener(new Paste());
        menu11.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(menu11);
        menu12 = new JMenuItem("Delete");
        menu12.addActionListener(this);

        edit.add(menu12);
        edit.addSeparator();
        menu13 = new JMenuItem("Find");
        menu13.addActionListener(new findz());
        menu13.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        edit.add(menu13);
        menu20 = new JMenuItem("Find Next..");
        menu20.addActionListener(new findnext());
        edit.add(menu20);
        menu21 = new JMenuItem("Replace");
        menu21.addActionListener(new replace());
        edit.add(menu21);
        menu22 = new JMenuItem("Goto");
        menu22.addActionListener(new gotoz());
        edit.add(menu22);
        edit.addSeparator();
        menu14 = new JMenuItem("Select All");
        menu14.addActionListener(new select());
        menu14.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(menu14);

        format = new JMenu("Format");
        format.setMnemonic(KeyEvent.VK_O);
        this.me.add(format);
        // menu15=new JMenuItem("Word Wrap           ");

        wrap = new JCheckBox("Word Wrap             ");
        format.add(wrap);
        wrap.addActionListener(this);

        format.addSeparator();
        menu16 = new JMenuItem("Font                            ");
        menu16.addActionListener(this);
        format.add(menu16);

        view = new JMenu("View");
        me.add(view);
        view.setMnemonic(KeyEvent.VK_V);
        status = new JCheckBox("Status bar           ");
        status.addActionListener(new Status());
        view.add(status);
        help = new JMenu("Help");



        me.add(help);
        help.setMnemonic(KeyEvent.VK_H);
        menu18 = new JMenuItem("View Help");
        this.help.add(menu18);
        help.addSeparator();
        menu19 = new JMenuItem("About Notepad");
        menu19.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "This is a new notepad type applicatio called \"MPAD\" created Manoj Sureddi\n try this new notepad guys!!!!!", "", JOptionPane.PLAIN_MESSAGE);
            }
        });
        this.help.add(menu19);

        t1 = new JTextArea();

        jsp = new JScrollPane(t1);
        Font sf = new Font("Lucida Console", 0, 13);
        t1.setFont(sf);
        t1.setSelectionColor(Color.orange);

        //j1=new JTabbedPane();
        //  j1.addTab(filename,jsp);

        this.add(jsp, BorderLayout.CENTER);


        this.setVisible(true);

        l1 = new JLabel("");
        this.add(l1, BorderLayout.SOUTH);
        l1.setVisible(false);

        this.add(me, BorderLayout.NORTH);
        Document doc = t1.getDocument();
        doc.addUndoableEditListener(
                new UndoableEditListener() {

                    @Override
                    public void undoableEditHappened(UndoableEditEvent event) {
                        undo.addEdit(event.getEdit());
                    }
                });


        menu5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.printDialog();
            }
        });
        menu4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                {
                    PageFormat pf = pj.pageDialog(pj.defaultPage());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu16) {
            FONT f = new FONT();


        }
        if ((e.getSource() == menu12)) {
            t1.replaceSelection(null);
        }




        //word Wrap 
        if (e.getSource() == wrap) {
            count++;
            if (count % 2 == 0) {
                t1.setLineWrap(false);
            } else {
                t1.setLineWrap(true);
            }
        }
        if (e.getSource() == menu8) {
            if (undo.canUndo()) {
                try {
                    undo.undo();
                } catch (CannotUndoException f) {
                }
            }
        }

        if (e.getSource() == redo) {
            if (undo.canRedo()) {
                try {
                    undo.redo();
                } catch (CannotRedoException l) {
                }
            }



        }




    }

    class select implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            String text = t1.getText();
            t1.setSelectionColor(Color.ORANGE);
            t1.setSelectionStart(0);
            t1.setSelectionEnd(text.length());
        }
    }

    class New implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                public void run() {


                    JFrame f1 = new menuing("new");
                    f1.setVisible(true);
                    f1.setSize(600, 800);

                }
            });



        }
    }

    class Open implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fd = new FileDialog(menuing.this, "select File", FileDialog.LOAD);
            fd.show();
            if (fd.getFile() != null) {
                filename = fd.getDirectory() + fd.getFile();
                setTitle(filename);
                ReadFile();
            }
            t1.requestFocus();
        }
    }

    void ReadFile() {
        BufferedReader d;
        StringBuilder sb = new StringBuilder();
        try {
            d = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = d.readLine()) != null) {
                sb.append(line).append("\n");
            }
            t1.setText(sb.toString());
            d.close();
        } catch (FileNotFoundException fe) {
            System.out.println("File not Found");
        } catch (IOException ioe) {
        }
    }

    class Save implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fd = new FileDialog(menuing.this, "Save File", FileDialog.SAVE);
            fd.show();
            if (fd.getFile() != null) {
                filename = fd.getDirectory() + fd.getFile();
                setTitle(filename);
                try {
                    String source = t1.getText();
                    byte buf[] = source.getBytes();
                    try (OutputStream f0 = new FileOutputStream(filename)) {
                        f0.write(buf);
                    }

                } catch (Exception ex) {
                    System.out.println("File not found");
                }
                t1.requestFocus();
            }
        }
    }

    class FONT extends JFrame implements ActionListener {

        String availableFontString[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JList fontList = new JList(availableFontString);
        JLabel fontLabel = new JLabel("Font");
        JTextField valueFont = new JTextField("Arial");
        JScrollPane fontPane = new JScrollPane(fontList);
        String fontStyleString[] = {"Normal", "Bold", "Italic", "Bold Italic"};
        JList styleList = new JList(fontStyleString);
        JLabel styleLabel = new JLabel("Style");
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        JScrollPane stylePane = new JScrollPane(styleList, v, h);
        JTextField valueStyle = new JTextField("Normal");
        String fontSizeString[] = {"8", "10", "12", "14", "16", "18", "20", "22", "24", "28"};
        JList sizeList = new JList(fontSizeString);
        JLabel sizeLabel = new JLabel("Font size");
        JScrollPane sizePane = new JScrollPane(sizeList);
        JTextField valueSize = new JTextField("12");
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        JLabel sampleLabel = new JLabel("Sample: ");
        JTextField sample = new JTextField(" AaBbCc");
        Font selectedFont;
        JTextArea k;

        public FONT() {
            setSize(500, 300);
            setTitle("Font");
            setVisible(true);
            sample.setEditable(false);
            getContentPane().setLayout(null);
            fontLabel.setBounds(10, 10, 170, 20);
            valueFont.setBounds(10, 35, 170, 20);
            fontPane.setBounds(10, 60, 170, 150);

            styleLabel.setBounds(200, 10, 100, 20);
            valueStyle.setBounds(200, 35, 100, 20);
            stylePane.setBounds(200, 60, 100, 150);

            sizeLabel.setBounds(320, 10, 50, 20);
            valueSize.setBounds(320, 35, 50, 20);
            sizePane.setBounds(320, 60, 50, 150);

            okButton.setBounds(400, 35, 80, 20);
            cancelButton.setBounds(400, 60, 80, 20);

            sampleLabel.setBounds(150, 235, 100, 30);
            sample.setBounds(200, 235, 100, 30);

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

            fontList.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        valueFont.setText(fontList.getSelectedValue().toString());
                        selectedFont = new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                        sample.setFont(selectedFont);

                    }
                }
            });
            styleList.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        valueStyle.setText(styleList.getSelectedValue().toString());
                        selectedFont = new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                        sample.setFont(selectedFont);
                    }
                }
            });
            sizeList.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        valueSize.setText(sizeList.getSelectedValue().toString());
                        selectedFont = new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                        sample.setFont(selectedFont);
                    }
                }
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okButton) {
                selectedFont = new Font(valueFont.getText(), styleList.getSelectedIndex(), Integer.parseInt(valueSize.getText()));
                t1.setFont(selectedFont);
                setVisible(false);

            }
        }
    }

    class gotoz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String t = JOptionPane.showInputDialog(null, "Goto", "Line no", JOptionPane.PLAIN_MESSAGE);
            try {
                int v = Integer.parseInt(t);
                int l = t1.getLineCount();
                System.out.println(v + "  " + l);
                if (v <= l) {
                    System.out.println(v + "  " + l);

                    try {
                        int li = t1.getLineStartOffset(v - 1);
                        t1.setCaretPosition(li);
                    } catch (Exception ba) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Line number is beyond the total number of lines", "", JOptionPane.PLAIN_MESSAGE);
                }

            } catch (NumberFormatException | HeadlessException g) {
                JOptionPane.showMessageDialog(null, "Error", "Should Be a number!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class Status implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            countstatus++;
            if (countstatus % 2 == 0) {
                l1.setVisible(false);
            } else {
                l1.setVisible(true);
                final Caret c = t1.getCaret();
                t1.addCaretListener(new CaretListener() {

                    @Override
                    public void caretUpdate(CaretEvent e) {
                        int p = 0;
                        p = e.getDot();

                        Point kl = c.getMagicCaretPosition();
                        double x = (kl.getX() + 1);
                        double y = ((kl.getY()) / 16) * 1;



                        l1.setText("Line: " + (int) (y + t1.getLineCount()) + " Column: " + (int) x);
                    }
                });
            }
        }
    }

    class SaveAs implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fd = new FileDialog(menuing.this, "Save File", FileDialog.SAVE);
            fd.show();
            if (fd.getFile() != null) {
                filename = fd.getDirectory() + fd.getFile();
                setTitle(filename);
                try {
                    String source = t1.getText();
                    byte buf[] = source.getBytes();
                    try (OutputStream f0 = new FileOutputStream(filename)) {
                        f0.write(buf);
                    }


                } catch (Exception ex) {
                    System.out.println("File not found");
                }
                t1.requestFocus();
            }
        }
    }
    //finding of text

    class findz implements ActionListener {

        int r1 = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = t1.getText();
            t1.setSelectionColor(Color.orange);
            int k = text.length();
            String tr = JOptionPane.showInputDialog(null, "Find", "Find Next", JOptionPane.INFORMATION_MESSAGE);

            r1 = text.indexOf(tr, r1);
            t1.setCaretPosition(r1);
            t1.setSelectionStart(r1);
            int a = r1 + tr.length();
            //txtArea.SelectionEnd( a );
            t1.setSelectionEnd(a);
        }
    }

    class findnext implements ActionListener {

        String text, tr;
        int r1;

        @Override
        public void actionPerformed(ActionEvent e) {
            t1.setSelectionColor(Color.orange);
            text = t1.getText();
            tr = JOptionPane.showInputDialog(null, "Find what", "Find Next", JOptionPane.INFORMATION_MESSAGE);
            r1 = text.indexOf(tr, t1.getCaretPosition());
            t1.setCaretPosition(r1);
            t1.setSelectionStart(r1);
            t1.setSelectionEnd(r1 + tr.length());
        }
    }

    class replace implements ActionListener {

        String text, tr, kr;
        int r1;

        @Override
        public void actionPerformed(ActionEvent e) {
            t1.setSelectionColor(Color.orange);
            text = t1.getText();
            tr = JOptionPane.showInputDialog(null, "Find what", "Find", JOptionPane.INFORMATION_MESSAGE);
            kr = JOptionPane.showInputDialog(null, "Replace With", "Replace with", JOptionPane.INFORMATION_MESSAGE);
            String replaceAll = text.replaceAll(tr, kr);
            t1.setText(replaceAll);

        }
    }

    class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object[] a = {"Save", "Don't Save", "Cancel"};
            int v = JOptionPane.showOptionDialog(menuing.this,
                    "Do you want to save the file ?",
                    "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    a,
                    a[0]);
            if (v == 0) {
                Save s = new Save();
                ActionEvent k = null;
                s.actionPerformed(k);
            } else if (v == 1) {
                System.exit(0);
            }

        }
    }

    class Cut implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String sel = t1.getSelectedText();
            StringSelection ss = new StringSelection(sel);
            clip.setContents(ss, ss);
            t1.replaceRange(" ", t1.getSelectionStart(), t1.getSelectionEnd());
        }
    }

    class Copy implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String sel = t1.getSelectedText();
            StringSelection clipString = new StringSelection(sel);
            clip.setContents(clipString, clipString);
        }
    }

    class Paste implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transferable cliptran = clip.getContents(menuing.this);
            try {
                String sel = (String) cliptran.getTransferData(DataFlavor.stringFlavor);
                t1.replaceRange(sel, t1.getSelectionStart(), t1.getSelectionEnd());
            } catch (UnsupportedFlavorException | IOException exc) {
                System.out.println("not string flavour");
            }
        }
    }

    public static void main(String args[]) {



        JFrame f = new menuing("new");
        f.setSize(600, 800);
        f.setVisible(true);




    }
}
