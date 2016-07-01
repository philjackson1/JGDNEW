package UI;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

// This class control the entire menu bar
public class GuiDUIMenuTabs extends JTabbedPane
{
    //GridLayout panelGrid = new GridLayout(1,15);
    int currentX=0;
    int currentY=0;
    int tempsize; //Used for button sizes
    
    
    JPanel m_file;
    JPanel m_edit;
    JPanel m_LnF;
    JPanel m_help;
    JPanel m_code;
    JPanel m_frame;

    //Project
    GuiDUITabButton mi_f_new;
    GuiDUITabButton mi_f_open;
    GuiDUITabButton mi_f_close;
    GuiDUITabButton mi_f_save;
    GuiDUITabButton mi_f_exit;

    //Edit
    GuiDUITabButton mi_e_cut;
    GuiDUITabButton mi_e_copy;
    GuiDUITabButton mi_e_paste;
    GuiDUITabButton mi_e_delete;

    //View
    GuiDUITabButton mi_v_motif;
    GuiDUITabButton mi_v_java;
    GuiDUITabButton mi_v_windows;
    GuiDUITabButton mi_v_nimbus;

    //Help
    GuiDUITabButton mi_h_help;
    GuiDUITabButton mi_h_about;

    //Code
    GuiDUITabButton mi_c_export;
    GuiDUITabButton mi_c_viewcode;

    //JFrame
    GuiDUITabButton mi_f_settings;

    public GuiDUIMenuTabs()
    {
        makeFileMenu();
        makeEditMenu();
        makeLookFeelMenu();
        makeHelpMenu();
        makeCodeMenu();
        makeFrameMenu();
    }


    void makeFileMenu()
    {
        m_file = new JPanel(null);
        
        
        //m_file = new JPanel(panelGrid);
       // m_file.setMnemonic('P');

        //*********************************************
        mi_f_new = new GuiDUITabButton("New Project",new ImageIcon("Images/icons/new_project.png"));
        mi_f_new.setMnemonic('N');
        mi_f_open = new GuiDUITabButton("Open Project",new ImageIcon("Images/icons/open_project.png"));
        mi_f_open.setMnemonic('O');
        mi_f_close = new GuiDUITabButton("Close Project",new ImageIcon("Images/icons/close.png"));
        mi_f_close.setMnemonic('C');
        mi_f_save = new GuiDUITabButton("Save Project",new ImageIcon("Images/icons/save.png"));
        mi_f_save.setMnemonic('S');
        mi_f_exit = new GuiDUITabButton("Exit JGuiD",new ImageIcon("Images/icons/exit.png"));
        //mi_f_exit.setMnemonic('X');

        mi_f_new.setActionCommand("new");
        mi_f_open.setActionCommand("open");
        mi_f_close.setActionCommand("close");
        mi_f_save.setActionCommand("save");
        mi_f_exit.setActionCommand("exit");

      
        
        tempsize = mi_f_new.getButtonSize();
        
        currentX=0;
        
        mi_f_new.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_f_open.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_f_close.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_f_save.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_f_exit.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        
        //*********************************************

        m_file.add(mi_f_new);
        m_file.add(mi_f_open);
        m_file.add(mi_f_close);
        m_file.add(mi_f_save);
        m_file.add(mi_f_exit);

        this.add("File",m_file);
    }


    void makeEditMenu()
    {
       m_edit = new JPanel(null);
        //m_edit = new JPanel(panelGrid);
       // m_edit.setMnemonic('E');

        //*********************************************
        mi_e_cut = new GuiDUITabButton("Cut",new ImageIcon("Images/icons/cut.png"));
        mi_e_cut.setMnemonic('X');
        mi_e_copy = new GuiDUITabButton("Copy",new ImageIcon("Images/icons/copy.png"));
        mi_e_copy.setMnemonic('C');
        mi_e_paste = new GuiDUITabButton("Paste",new ImageIcon("Images/icons/paste.png"));
        mi_e_paste.setMnemonic('P');
        mi_e_delete = new GuiDUITabButton("Delete",new ImageIcon("Images/icons/delete.png"));
        mi_e_delete.setMnemonic('D');


        /*mi_e_cut.setAccelerator(KeyStroke.getKeyStroke("control X"));
        mi_e_copy.setAccelerator(KeyStroke.getKeyStroke("control C"));
        mi_e_paste.setAccelerator(KeyStroke.getKeyStroke("control V"));
        mi_e_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
*/
        mi_e_cut.setActionCommand("cut");
        mi_e_copy.setActionCommand("copy");
        mi_e_paste.setActionCommand("paste");
        mi_e_delete.setActionCommand("delete");
        //*********************************************
        
        currentX=0;
        
        mi_e_cut.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_e_copy.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_e_paste.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_e_delete.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        
        
        m_edit.add(mi_e_cut);
        m_edit.add(mi_e_copy);
        m_edit.add(mi_e_paste);
      //  m_edit.addSeparator();
        m_edit.add(mi_e_delete);

        this.add("Edit",m_edit);
    }


    void makeLookFeelMenu()
    {
       // m_LnF = new JPanel(panelGrid);	
         m_LnF = new JPanel(null);
//        m_LnF.setMnemonic('L');

        //*********************************************
        mi_v_motif = new GuiDUITabButton("Motif");
        mi_v_motif.setMnemonic('M');
        mi_v_java = new GuiDUITabButton("Java");
        mi_v_java.setMnemonic('J');
        mi_v_windows = new GuiDUITabButton("Windows");
        mi_v_windows.setMnemonic('W');
        mi_v_nimbus = new GuiDUITabButton("Nimbus");
        //mi_v_windows.setMnemonic('N');

        mi_v_motif.setActionCommand("look_motif");
        mi_v_java.setActionCommand("look_java");
        mi_v_windows.setActionCommand("look_windows");
        mi_v_nimbus.setActionCommand("look_nimbus");
        //mi_v_code.setActionCommand("code");

         currentX=0;
        mi_v_motif.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_v_java.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_v_windows.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_v_nimbus.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        
        m_LnF.add(mi_v_motif);
        m_LnF.add(mi_v_java);
        m_LnF.add(mi_v_windows);
        m_LnF.add(mi_v_nimbus);

        //*********************************************

        this.add("Look and Feel",m_LnF);
    }


    void makeHelpMenu()
    {
        m_help = new JPanel(null);
//m_help = new JPanel(panelGrid);
//        m_help.setMnemonic('H');

        mi_h_help = new GuiDUITabButton("Help",new ImageIcon("Images/icons/help.png"));
        mi_h_help.setMnemonic('H');
        mi_h_about = new GuiDUITabButton("About");
        mi_h_about.setMnemonic('A');

      //  mi_h_help.setAccelerator(KeyStroke.getKeyStroke("F1"));
       // mi_h_about.setAccelerator(KeyStroke.getKeyStroke("control A"));

        mi_h_help.setActionCommand("help");
        mi_h_about.setActionCommand("about");

         currentX=0;
         mi_h_help.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_h_about.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        
        
        m_help.add(mi_h_help);
       // m_help.addSeparator();
        m_help.add(mi_h_about);

        this.add("Help",m_help);
    }


    void makeCodeMenu()
    {
       // m_code = new JPanel(panelGrid);	
         m_code = new JPanel(null);

        //m_code = new JMenu();
        //m_file.setMnemonic('P');

        mi_c_export = new GuiDUITabButton("Export Code",new ImageIcon("Images/icons/saveas.png"));
        mi_c_export.setMnemonic('E');
        mi_c_viewcode = new GuiDUITabButton("View Code");
        //mi_c_viewcode.setMnemonic('C');
      //  mi_c_viewcode.setAccelerator(KeyStroke.getKeyStroke("control alt C"));

                mi_c_export.setActionCommand("scode");
        mi_c_viewcode.setActionCommand("code");

         currentX=0;
         mi_c_export.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        mi_c_viewcode.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        
        m_code.add(mi_c_export);
        m_code.add(mi_c_viewcode);

        this.add("Code",m_code);
    }

    void makeFrameMenu()
    {
        //m_frame = new JPanel(panelGrid);	
        m_frame = new JPanel(null);	

       // m_frame = new JMenu();
        //m_frame.setMnemonic('F');

        mi_f_settings = new GuiDUITabButton("Settings");
        //mi_f_settings.setMnemonic('E');		

        mi_f_settings.setActionCommand("settings");

         currentX=0;
        mi_f_settings.setLocation(currentX,currentY);
        currentX=currentX+tempsize;
        
        
        m_frame.add(mi_f_settings);

        this.add("JFrame",m_frame);
    }

    public void addListeners(ActionListener al)
    {
        mi_f_new.addActionListener(al);
        mi_f_open.addActionListener(al);
        mi_f_close.addActionListener(al);
        mi_f_save.addActionListener(al);
        mi_f_exit.addActionListener(al);

        mi_e_cut.addActionListener(al);
        mi_e_copy.addActionListener(al);
        mi_e_paste.addActionListener(al);
        mi_e_delete.addActionListener(al);

        mi_v_motif.addActionListener(al);
        mi_v_java.addActionListener(al);
        mi_v_windows.addActionListener(al);
        mi_v_nimbus.addActionListener(al);

        mi_h_help.addActionListener(al);
        mi_h_about.addActionListener(al);

        mi_c_export.addActionListener(al);
        mi_c_viewcode.addActionListener(al);

        mi_f_settings.addActionListener(al);
    }

}
