package Session;

import UI.GuiDUIMainPanel;
import UI.GuiDUIPropertiesPanel;
import Session.*;
import Util.*;

import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.Container;


public class GuiDParentPropertiesPanel
{
	//MSO: Defined these constants:
	public final int PROP_TITLE=0;
	public final int PROP_WIDTH=1;
	public final int PROP_HEIGHT=2;
	public final int PROP_FOREGROUND=3;
	public final int PROP_BACKGROUND=4;
	public final int PROP_VISIBLE=5;
	public final int PROP_RESIZABLE=6;	
	public final int PROP_N_PROPS=7;    //Number of properties above
	public final int PROP_VAL_ID=0;
	public final int PROP_VAL_TYPE=1;
	public final int PROP_VAL_VALUE=2;
	
	
	private GuiDUIMainPanel gup; 
	private JInternalFrame frm;
	private String props[][];
	private GuiDUIPropertiesPanel gpp;
	private int width,height,min=0;
	
	
	//Frame currently being developed by user
	public GuiDParentPropertiesPanel(GuiDUIMainPanel gup)
	{
		try
		{
			props = new String[PROP_N_PROPS][3];
			this.gup = gup;
			if(gup.type.contains("Applet"))
			{  
				min=1;
			}
			frm = gup.getUserPanel();
			initProperties();
			width =400;
			height=400;
			createPropPanel();
			addResizeListener();
		}
		catch(Exception e)
		{
			System.out.println("Error in GuiDParentPropPanel consturctor");
		}
	}
	
	public GuiDParentPropertiesPanel(GuiDUIMainPanel gup,String props[][])
	{
		this.props=props;
		this.gup = gup;
		if(gup.type.contains("Applet"))
		{
			min=1;
		}
		frm = gup.getUserPanel();
		width=Integer.parseInt(props[PROP_WIDTH][PROP_VAL_VALUE]);
		height=Integer.parseInt(props[PROP_HEIGHT][PROP_VAL_VALUE]);
		setProperties();
		createPropPanel();
		addResizeListener();
	}
	private void addResizeListener()
	{
		frm.addComponentListener( new ComponentAdapter()
		{
			public void componentResized(ComponentEvent ce)
			{
				width=frm.getWidth();
				props[PROP_WIDTH][PROP_VAL_VALUE]=String.valueOf(width);
				((JTextField)gpp.getPropComponent(1-min)).setText(String.valueOf(width));

				height=frm.getHeight();
				props[PROP_HEIGHT][PROP_VAL_VALUE]=String.valueOf(height);
				((JTextField)gpp.getPropComponent(2-min)).setText(String.valueOf(height));
			}
		});

	}
	private void setProperties()
	{
		gup.setUsrPanelTitle(props[PROP_TITLE][PROP_VAL_VALUE]);
		frm.setSize(width,height);
		frm.getContentPane().setForeground(new Color(Integer.parseInt(props[PROP_FOREGROUND][PROP_VAL_VALUE])));
		frm.getContentPane().setBackground(new Color(Integer.parseInt(props[PROP_BACKGROUND][PROP_VAL_VALUE])));
		frm.setResizable(Boolean.parseBoolean(props[PROP_RESIZABLE][PROP_VAL_VALUE]));
	}
	public String[][] getPropertiesArray()
	{
		return props;
	}
	public GuiDUIPropertiesPanel getPropPanel()
	{
		return gpp;
	}
	public String getPropTitle()
	{
		return props[PROP_TITLE][PROP_VAL_VALUE];
	}
	private void initProperties()
	{
		props[PROP_TITLE][PROP_VAL_ID]="Title";
		props[PROP_TITLE][PROP_VAL_TYPE]="String";
		props[PROP_TITLE][PROP_VAL_VALUE]=gup.getUsrPanelTitle();

		props[PROP_WIDTH][PROP_VAL_ID]="Width";
		props[PROP_WIDTH][PROP_VAL_TYPE]="int";
		props[PROP_WIDTH][PROP_VAL_VALUE]="400";

		props[PROP_HEIGHT][PROP_VAL_ID]="Height";
		props[PROP_HEIGHT][PROP_VAL_TYPE]="int";
		props[PROP_HEIGHT][PROP_VAL_VALUE]="400";

		props[PROP_FOREGROUND][PROP_VAL_ID]="Foreground";
		props[PROP_FOREGROUND][PROP_VAL_TYPE]="int";
		props[PROP_FOREGROUND][PROP_VAL_VALUE]=String.valueOf(frm.getForeground().getRGB());

		props[PROP_BACKGROUND][PROP_VAL_ID]="Background";
		props[PROP_BACKGROUND][PROP_VAL_TYPE]="int";
		props[PROP_BACKGROUND][PROP_VAL_VALUE]=String.valueOf(frm.getBackground().getRGB());

		props[PROP_VISIBLE][PROP_VAL_ID]="Visible";
		props[PROP_VISIBLE][PROP_VAL_TYPE]="boolean";
		props[PROP_VISIBLE][PROP_VAL_VALUE]="true";

		props[PROP_RESIZABLE][PROP_VAL_ID]="Resizable";
		props[PROP_RESIZABLE][PROP_VAL_TYPE]="boolean";
		props[PROP_RESIZABLE][PROP_VAL_VALUE]="true";

	}

	private void createPropPanel()
	{
		gpp = new GuiDUIPropertiesPanel();
		gpp.setPropTitle(gup.type);

		if(!(gup.type.contains("Applet")))
		{
			JTextField p1 = new JTextField(props[PROP_TITLE][PROP_VAL_VALUE],10);
			p1.addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
					JTextField tm = (JTextField)ke.getSource();
					props[PROP_TITLE][PROP_VAL_VALUE] = tm.getText();
					gup.setUsrPanelTitle(props[PROP_TITLE][PROP_VAL_VALUE]);
					frm.updateUI();
				}
			});
			gpp.addProperty(new JLabel("Title"),p1);
		}

		JTextField p2 = new JTextField(props[PROP_WIDTH][PROP_VAL_VALUE],10);
		p2.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[PROP_WIDTH][PROP_VAL_VALUE] = tm.getText();
					frm.setSize(Integer.parseInt(props[PROP_WIDTH][PROP_VAL_VALUE]),Integer.parseInt(props[PROP_HEIGHT][PROP_VAL_VALUE]));
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Width"),p2);

		JTextField p3 = new JTextField(props[PROP_HEIGHT][PROP_VAL_VALUE],10);
		p3.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[PROP_HEIGHT][PROP_VAL_VALUE] = tm.getText();
					frm.setSize(Integer.parseInt(props[PROP_WIDTH][PROP_VAL_VALUE]),Integer.parseInt(props[PROP_HEIGHT][PROP_VAL_VALUE]));
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Height"),p3);

		JButton p4 = new JButton("Frg Color");
		p4.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ke)
			{
				Color col = JColorChooser.showDialog(null,"Foreground Color Chooser",frm.getForeground());
				if(col!=null)
				{
					props[PROP_FOREGROUND][PROP_VAL_VALUE]=String.valueOf(col.getRGB());
					frm.getContentPane().setForeground(col);
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Foreground Color"),p4);

		JButton p5 = new JButton("Bck Color");
		p5.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ke)
			{
				Color col = JColorChooser.showDialog(null,"Background Color Chooser",frm.getBackground());
				if(col!=null)
				{
					props[PROP_BACKGROUND][PROP_VAL_VALUE]=String.valueOf(col.getRGB());
					frm.getContentPane().setBackground(col);
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Background Color"),p5);


		String bl[]={"true","false"};
		JComboBox p6 = new JComboBox(bl);
		p6.addItemListener(new ItemAdapter()
		{
			public void itemStateChanged(ItemEvent e)
			{
				JComboBox tm = (JComboBox)e.getSource();
				props[PROP_VISIBLE][PROP_VAL_VALUE] = String.valueOf(tm.getSelectedItem());
			}
		});
		gpp.addProperty(new JLabel("Visible"),p6);

		if(!(gup.type.contains("Applet")))
		{
			JComboBox p7 = new JComboBox(bl);
			p7.addItemListener(new ItemAdapter()
			{
				public void itemStateChanged(ItemEvent e)
				{
					JComboBox tm = (JComboBox)e.getSource();
					props[PROP_RESIZABLE][PROP_VAL_VALUE] = String.valueOf(tm.getSelectedItem());
					frm.setResizable(Boolean.parseBoolean(props[PROP_RESIZABLE][PROP_VAL_VALUE]));
				}
			});
			gpp.addProperty(new JLabel("Resizable"),p7);
		}
		
	}
}
