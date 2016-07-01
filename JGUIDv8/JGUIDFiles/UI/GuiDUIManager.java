package UI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Util.ChangeAdapter;
import javax.swing.event.ChangeEvent;

import UI.*;
import Session.*;

public class GuiDUIManager extends JFrame 
{
	GuiDUIPropertiesPanel curr_gpp=null;
	GuiDUIPropertiesPanel par_gpp=null;
	GuiDUIMainPanel gup;
	Container c;
	JTabbedPane tb;
	public GuiDUIManager(GuiDUIMenuTabs tabs,GuiDUIComponentList gtb,GuiDUIMainPanel gp)
	{
		super("Java Gui Designer");
		
		this.gup= gp;
		c = new Container();
		c.setLayout(new BorderLayout());
		this.add(c);

		Image img = getToolkit().createImage("Images/icons/GuiD_icon.png");
		this.setIconImage(img);
		setDefaultLookAndFeelDecorated(true);
		
		
					
		

		//setJMenuBar(mbr);
		c.add(tabs,BorderLayout.NORTH);
		c.add(gtb,BorderLayout.WEST);
		c.add(new JScrollPane(gup),BorderLayout.CENTER);
		tb = new JTabbedPane();
		c.add(tb,BorderLayout.EAST);

		//JSplitPane splitPaneNS = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(gup), tb);
		
		//JSplitPane splitPaneLR = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gtb, splitPaneNS);
		
		//this.add(splitPaneLR);
		//splitPaneNS.setDividerLocation(540);
		//splitPaneLR.setDividerLocation(160);
		
		
		tb.addChangeListener(new ChangeAdapter()
		{
			public void stateChanged(ChangeEvent ce)
			{
				if(tb.getSelectedIndex()==0)
				{
					if(!gup.type.contains("Applet"))
					 gup.setUsrPanelTitle(((JTextField)par_gpp.getPropComponent(0)).getText());
				}
				else
				{
					gup.setCmpSelection(((JTextField)curr_gpp.getPropComponent(0)).getText());
				}
			}
		});
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		
		//setSize(1024,768);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() 
			{
				@Override
				public void windowClosing(WindowEvent we)
				{ 
					String ObjButtons[] = {"Yes","No"};
					//int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit? \n\n You will lose all unsaved work","JGuiD",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                                        int PromptResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit? \n\n You will lose all unsaved work","Close JGuiD",JOptionPane.YES_NO_OPTION);
					if(PromptResult==JOptionPane.YES_OPTION)
					{
						System.exit(0);
					}
			}
				});
	}
	public void removePropertiesPanel()
	{
		if(curr_gpp!=null)
		{
			tb.removeTabAt(1);
			curr_gpp=null;
		}
		if(!gup.type.contains("Applet"))
			 gup.setUsrPanelTitle(((JTextField)par_gpp.getPropComponent(0)).getText());


	}
	public void addParentPropPanel(GuiDUIPropertiesPanel pp)
	{
		par_gpp=pp;
		tb.addTab(pp.getPropTitle(),pp);
	}
	public void updatePropertiesPanel(GuiDUIPropertiesPanel pp)
	{
		if(curr_gpp!=null)
		{
			tb.removeTabAt(1);
		}
		curr_gpp=pp;
		tb.addTab(curr_gpp.getPropTitle(),curr_gpp);
		tb.setSelectedIndex(1);
		tb.updateUI();
		curr_gpp.updatePanel();
	}
}
