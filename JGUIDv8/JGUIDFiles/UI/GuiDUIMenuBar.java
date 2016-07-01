package UI;

import javax.swing.*;
import java.awt.event.*;

// This class control the entire menu bar
public class GuiDUIMenuBar extends JMenuBar
{
	JMenu m_file;
	JMenu m_edit;
	JMenu m_LnF;
	JMenu m_help;
	JMenu m_code;
	JMenu m_frame;
	
	//Project
	JMenuItem mi_f_new;
	JMenuItem mi_f_open;
	JMenuItem mi_f_close;
	JMenuItem mi_f_save;
	JMenuItem mi_f_exit;
	
	//Edit
	JMenuItem mi_e_cut;
	JMenuItem mi_e_copy;
	JMenuItem mi_e_paste;
	JMenuItem mi_e_delete;
	
	//View
	JMenuItem mi_v_motif;
	JMenuItem mi_v_java;
	JMenuItem mi_v_windows;
	JMenuItem mi_v_nimbus;
	
	//Help
	JMenuItem mi_h_help;
	JMenuItem mi_h_about;
	
	//Code
	JMenuItem mi_c_export;
	JMenuItem mi_c_viewcode;
	
	//JFrame
	JMenuItem mi_f_settings;
	
	public GuiDUIMenuBar()
	{
		setBorderPainted(true);
		makeFileMenu();
		makeEditMenu();
		makeLookFeelMenu();
		makeHelpMenu();
		makeCodeMenu();
		makeFrameMenu();
	}
	
	
	void makeFileMenu()
	{
		m_file = new JMenu("Project");
		m_file.setMnemonic('P');
		
		//*********************************************
		mi_f_new = new JMenuItem("New Project",new ImageIcon("Images/icons/new_project.png"));
		mi_f_new.setMnemonic('N');
		mi_f_open = new JMenuItem("Open Project",new ImageIcon("Images/icons/open_project.png"));
		mi_f_open.setMnemonic('O');
		mi_f_close = new JMenuItem("Close Project",new ImageIcon("Images/icons/close.png"));
		mi_f_close.setMnemonic('C');
		mi_f_save = new JMenuItem("Save Project",new ImageIcon("Images/icons/save.png"));
		mi_f_save.setMnemonic('S');
		mi_f_exit = new JMenuItem("Exit JGuiD",new ImageIcon("Images/icons/exit.png"));
		//mi_f_exit.setMnemonic('X');


		mi_f_new.setAccelerator(KeyStroke.getKeyStroke("control N"));
		mi_f_open.setAccelerator(KeyStroke.getKeyStroke("control O"));
		mi_f_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,InputEvent.ALT_MASK));
		mi_f_save.setAccelerator(KeyStroke.getKeyStroke("control S"));
		mi_f_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,InputEvent.ALT_MASK));

		mi_f_new.setActionCommand("new");
		mi_f_open.setActionCommand("open");
		mi_f_close.setActionCommand("close");
		mi_f_save.setActionCommand("save");
		mi_f_exit.setActionCommand("exit");
		
		//*********************************************
		
		m_file.add(mi_f_new);
		m_file.add(mi_f_open);
		m_file.add(mi_f_close);
		m_file.addSeparator();
		m_file.add(mi_f_save);
		m_file.addSeparator();
		m_file.add(mi_f_exit);

		add(m_file);
	}
	
	
	void makeEditMenu()
	{
		m_edit = new JMenu("Edit");
		m_edit.setMnemonic('E');
		
		//*********************************************
		mi_e_cut = new JMenuItem("Cut",new ImageIcon("Images/icons/cut.png"));
		mi_e_cut.setMnemonic('X');
		mi_e_copy = new JMenuItem("Copy",new ImageIcon("Images/icons/copy.png"));
		mi_e_copy.setMnemonic('C');
		mi_e_paste = new JMenuItem("Paste",new ImageIcon("Images/icons/paste.png"));
		mi_e_paste.setMnemonic('P');
		mi_e_delete = new JMenuItem("Delete",new ImageIcon("Images/icons/delete.png"));
		mi_e_delete.setMnemonic('D');


		mi_e_cut.setAccelerator(KeyStroke.getKeyStroke("control X"));
		mi_e_copy.setAccelerator(KeyStroke.getKeyStroke("control C"));
		mi_e_paste.setAccelerator(KeyStroke.getKeyStroke("control V"));
		mi_e_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));

		mi_e_cut.setActionCommand("cut");
		mi_e_copy.setActionCommand("copy");
		mi_e_paste.setActionCommand("paste");
		mi_e_delete.setActionCommand("delete");
		//*********************************************
		m_edit.add(mi_e_cut);
		m_edit.add(mi_e_copy);
		m_edit.add(mi_e_paste);
		m_edit.addSeparator();
		m_edit.add(mi_e_delete);

		add(m_edit);
	}
	
	
	void makeLookFeelMenu()
	{
		m_LnF = new JMenu("Look and Feel");
		m_LnF.setMnemonic('L');
		
		//*********************************************
		mi_v_motif = new JMenuItem("Motif");
		mi_v_motif.setMnemonic('M');
		mi_v_java = new JMenuItem("Java");
		mi_v_java.setMnemonic('J');
		mi_v_windows = new JMenuItem("Windows");
		mi_v_windows.setMnemonic('W');
		mi_v_nimbus = new JMenuItem("Nimbus");
		//mi_v_windows.setMnemonic('N');
		
		mi_v_motif.setActionCommand("look_motif");
		mi_v_java.setActionCommand("look_java");
		mi_v_windows.setActionCommand("look_windows");
		mi_v_nimbus.setActionCommand("look_nimbus");
		//mi_v_code.setActionCommand("code");

		m_LnF.add(mi_v_motif);
		m_LnF.add(mi_v_java);
		m_LnF.add(mi_v_windows);
		m_LnF.add(mi_v_nimbus);
		
		//*********************************************
		
		add(m_LnF);
	}
	
	
	void makeHelpMenu()
	{
		m_help = new JMenu("Help");
		m_help.setMnemonic('H');

		mi_h_help = new JMenuItem("Help",new ImageIcon("Images/icons/help.png"));
		mi_h_help.setMnemonic('H');
		mi_h_about = new JMenuItem("About");
		mi_h_about.setMnemonic('A');
		
		mi_h_help.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mi_h_about.setAccelerator(KeyStroke.getKeyStroke("control A"));

		mi_h_help.setActionCommand("help");
		mi_h_about.setActionCommand("about");

		m_help.add(mi_h_help);
		m_help.addSeparator();
		m_help.add(mi_h_about);

		add(m_help);
	}
	
	
	void makeCodeMenu()
	{
		m_code = new JMenu("Code");
		//m_file.setMnemonic('P');

		mi_c_export = new JMenuItem("Export Code",new ImageIcon("Images/icons/saveas.png"));
		mi_c_export.setMnemonic('E');
		mi_c_viewcode = new JMenuItem("View Code");
		//mi_c_viewcode.setMnemonic('C');
		mi_c_viewcode.setAccelerator(KeyStroke.getKeyStroke("control alt C"));
		
			mi_c_export.setActionCommand("scode");
		mi_c_viewcode.setActionCommand("code");
		
		m_code.add(mi_c_export);
		m_code.add(mi_c_viewcode);
		
		add(m_code);
	}
	
	void makeFrameMenu()
	{
		m_frame = new JMenu("JFrame");
		//m_frame.setMnemonic('F');

		mi_f_settings = new JMenuItem("Settings");
		//mi_f_settings.setMnemonic('E');		
	
		mi_f_settings.setActionCommand("settings");
		
		m_frame.add(mi_f_settings);
		
		add(m_frame);
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
