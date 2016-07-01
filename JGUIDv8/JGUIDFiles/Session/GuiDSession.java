//ACTION LISTENERS IN HERE

package Session;
import UI.*;
import Util.*;
import Session.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class GuiDSession implements ActionListener,iGuiDPropMessenger
{
	GuiDUIMainPanel gup;    
	//GuiDUIMenuBar  mbr;
        GuiDUIMenuTabs tabs;
	GuiDUIComponentList gtb;
	GuiDUIToolBar tbr;
	GuiDUIManager gui;
	GuiDComponent curr_sel;
	GuiDParentPropertiesPanel gppp;
	String toolbox_sel;
	String type;
	int curr_no;
	Vector<GuiDComponent> comp_vec,buf_vec;
	File f=null,jf=null;
	iJGuiD itg;
	public GuiDSession(String type,iJGuiD tg)
	{
		itg=tg;
		doInit(type);

		comp_vec = new Vector<GuiDComponent>();
    	gui = new GuiDUIManager(tabs,gtb,gup);

		selectLookAndFeel();		
		try
		{
			gppp = new GuiDParentPropertiesPanel(gup);

			addListeners();
			gui.addParentPropPanel(gppp.getPropPanel());
			gui.invalidate();
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(gui," Exception Occured in GuiDSession constructor "+ee,"Error",0);
		}
		
	}
	
	public void selectLookAndFeel()
	{
		String question = "Would you like to start with 'Nimbus' look and feel?";
		String disclaimer = "<html><i>*You must have a recent version of Java to use Nimbus</i></html>";
		
		int answer = JOptionPane.showConfirmDialog(null,
            question+"\n\n"+disclaimer,
            "Look of the Program",
            JOptionPane.YES_NO_OPTION);

        if(answer == JOptionPane.YES_OPTION)
		{
           	try
			{
				String look ="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
				//String look ="com.sun.java.swing.plaf.nimbus";
				UIManager.setLookAndFeel(look);
				SwingUtilities.updateComponentTreeUI(gui);
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(gui," Exception Occured when setting Look and Feel "+ee,"Error",0);
			}
        }
        else 
		{
            //No Action
        }


		
		
		
	}
	
	public GuiDSession(File f,iJGuiD tg)
	{
		itg =tg;
		this.f = f;
		GuiDFileProjectHandler gpfh = new GuiDFileProjectHandler(f,null,null,null);
		gpfh.parseProjectFile(this);

		doInit(gpfh.getType());

		comp_vec = gpfh.getComponentVector();

		int i;
		for(i=0;i<comp_vec.size();i++)
		{
			GuiDComponent gc = comp_vec.elementAt(i);
			gup.addCmp(gc.getDispComponent());	
		}
		curr_no=i;
		gup.invalidate();

    	gui = new GuiDUIManager(tabs,gtb,gup);

		gppp = new GuiDParentPropertiesPanel(gup,gpfh.getParentProps());
		
		gup.getUserPanel().updateUI();
		addListeners();
		gui.addParentPropPanel(gppp.getPropPanel());
		gui.invalidate();
	}
	private void doInit(String type)
	{
		this.type = type;

		curr_sel=null;
		toolbox_sel=null;
		curr_no=0;
		//mbr = new GuiDUIMenuBar();
                tabs = new GuiDUIMenuTabs();
		tbr = new GuiDUIToolBar();
		gtb = new GuiDUIComponentList();
		gup = new GuiDUIMainPanel(type);

		buf_vec = new Vector<GuiDComponent>();
	}

	private void addListeners()
	{
		//mbr.addListeners(this);
                tabs.addListeners(this);
		tbr.addListeners(this);
		gtb.addListeners(new ActionAdapter()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JButton src = (JButton)ae.getSource();
				toolbox_sel= src.getActionCommand();
				gtb.setToolboxSelection(toolbox_sel);
			}
		});
		gup.getUserPanel().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(toolbox_sel!=null)
				{
					createCmp(me.getX(),me.getY()-25);
					toolbox_sel=null;
					gtb.clearToolboxSelection();
				}
			}
		});
	}

	public void createCmp(int x,int y)
	{
		curr_no++;
		GuiDComponent gc = new GuiDComponent(toolbox_sel,curr_no,x,y,this);
		gup.addCmp(gc.getDispComponent());
		curr_sel=gc;
		comp_vec.add(gc);
		gui.updatePropertiesPanel(gc.getPropertiesPanel());
		updateCompSel(gc.getSelString());
		gui.invalidate();
	}
	public void actionPerformed(ActionEvent ae)
	{
		String action_str= ((AbstractButton)ae.getSource()).getActionCommand();
		if(action_str.equals("about"))
		{
			GuiDUIPopupInfo giv= new GuiDUIPopupInfo("About","GuiDhelp/about.html","html");
		}
		else if(action_str.equals("help"))
		{
			GuiDUIPopupInfo giv= new GuiDUIPopupInfo("GuiD Help","GuiDhelp/index.html","html");
		}
		else if(action_str.equals("code"))
		{
			String code=GuiDCodeGenerator.getCode(gppp.getPropertiesArray(),gup.type,comp_vec);
			GuiDUIPopupInfo giv= new GuiDUIPopupInfo("Your Code",code,"text");
		}
		else if(action_str.equals("code"))
		{
			String code=GuiDCodeGenerator.getCode(gppp.getPropertiesArray(),gup.type,comp_vec);
			GuiDUIPopupInfo giv= new GuiDUIPopupInfo("Your Code",code,"text");
		}
		else if(action_str.contains("look"))
		{
			String look="javax.swing.plaf.metal.MetalLookAndFeel";
				if(action_str.equals("look_java"))
				{
					  look="javax.swing.plaf.metal.MetalLookAndFeel";
				}
				else if(action_str.equals("look_windows"))
				{
						look="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				}
				else if (action_str.equals("look_motif"))
				{
						look="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				}
				else if(action_str.equals("look_nimbus"))
				{
					look ="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
				}				
				try
				{
					UIManager.setLookAndFeel(look);
					SwingUtilities.updateComponentTreeUI(gui);
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(gui," Exception Occured when setting Look and Feel "+ee,"Error",0);
				}
		}
		else if(action_str.equals("copy"))
		{
			if(curr_sel!=null)
			{
				curr_no++;
				copyCurrComp(curr_no);
			}
		}
		else if(action_str.equals("paste"))
		{
			pasteComp();
		}
		else if(action_str.equals("delete"))
		{
			deleteCurrComp();
			if(curr_sel!=null)
			{
				comp_vec.remove(curr_sel);
				curr_sel.finalize();
				curr_sel=null;
			}
		}
		else if(action_str.equals("cut"))
		{
			copyCurrComp(-1);
			deleteCurrComp();
			comp_vec.remove(curr_sel);
		}
		else if(action_str.equals("new"))
		{
			closeProject("Would you like to save existing project before starting new one ?");
			gui.dispose();
			itg.showProjectDialog();
			finalize();
		}
		else if(action_str.equals("open"))
		{
			closeProject("Would you like to save existing project before opening new one ?");
			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("GuiD Project File", "GuiD");
			jfc.setFileFilter(filter);
			if(jfc.showOpenDialog(gui)==JFileChooser.APPROVE_OPTION)
			{
					gui.dispose();
					itg.openExistingProject(jfc.getSelectedFile());
	  				finalize();
			}			
		}
		else if(action_str.equals("save"))
		{
			if(f==null)
			{
				saveNewProject();
			}
			else
			{
				GuiDFileProjectHandler gpfg = new GuiDFileProjectHandler(f,type,gppp.getPropertiesArray(),comp_vec);
				gpfg.generateProjectFile();
			}
		}
		else if(action_str.equals("scode"))
		{
			if(jf==null)
			{
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Source File", "java");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("GuiDFrame.java"));

				if(jfc.showSaveDialog(gui)==JFileChooser.APPROVE_OPTION)
				{
					jf = jfc.getSelectedFile();
					String path = jf.getAbsolutePath();
					if(!(path.endsWith(".java")))
					{
						path+=".java";
						jf = new File(path);
					}
				}
			}
			if(jf!=null)
			{			
				String code=GuiDCodeGenerator.getCode(gppp.getPropertiesArray(),gup.type,comp_vec);
				GuiDFileHandler.writeToFile(jf,code);
			}
		}
		else if(action_str.equals("close"))
		{
			closeProject("Would you like to save project before closing?");
	 	    gui.dispose();
			itg.showProjectDialog();
  			finalize();
		}
		else if(action_str.equals("exit"))
		{
			closeProject("Would you like to save project before leaving?");
		    gui.dispose();
			System.exit(0);
		}
		else if(action_str.equals("settings"))
		{
			JOptionPane.showMessageDialog(null,"Not implemented");
		 }
	}
	private void closeProject(String str)
	{
		     int  answer = JOptionPane.showConfirmDialog( gui,str,"GuiD Save Dialog", JOptionPane.YES_NO_OPTION ); 

	         if( answer == JOptionPane.YES_OPTION)
		     {
				boolean old=false;
			    if(f!=null)
				 {
					old =true;
				 }
				 if(!old)
				 {
					saveNewProject();	
				 }
				 else
				 {
					GuiDFileProjectHandler gpfg = new GuiDFileProjectHandler(f,type,gppp.getPropertiesArray(),comp_vec);
					gpfg.generateProjectFile();
				 }
			 }
	
	}
	private void saveNewProject()
	{
			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("GuiD Project File", "GuiD");
			jfc.setFileFilter(filter);
			if(jfc.showSaveDialog(gui)==JFileChooser.APPROVE_OPTION)
			{
				f = jfc.getSelectedFile();
				GuiDFileProjectHandler gpfg = new GuiDFileProjectHandler(f,type,gppp.getPropertiesArray(),comp_vec);
				gpfg.generateProjectFile();
			}
	}
	private void deleteCurrComp()
	{
    	if(curr_sel!=null)
		{
		   gup.removeCmp(curr_sel.getDispComponent());
		   gui.removePropertiesPanel();
		}
	}
	private void copyCurrComp(int no)
	{
		if(curr_sel!=null)
		{
			String props[][] = curr_sel.getPropertiesArray();
			String tmp[][] = new String[props.length][4];
			for(int i=0;i<props.length;i++)
			{
				for(int j=0;j<3;j++)
					tmp[i][j] = props[i][j];
			}
			GuiDComponent gc = new GuiDComponent(curr_sel.getCast(),tmp,no,0,0,this);
			buf_vec.removeAllElements() ;
			buf_vec.add(gc);
		}
	}
	private void pasteComp()
	{
		if(buf_vec.size()>0)
		{
			GuiDComponent gc=buf_vec.firstElement();
			gup.addCmp(gc.getDispComponent());
			curr_sel=gc;
			comp_vec.add(gc);
			gui.updatePropertiesPanel(gc.getPropertiesPanel());
			updateCompSel(gc.getSelString());
			gui.invalidate();
		}
	}
	public void updateCompSel(String s)
	{
			gup.setCmpSelection(s);
			gup.invalidate();
	}
	public void changePropPanel(GuiDComponent gcmp)
	{
		boolean  flag=false;
		if(curr_sel==null)
		{
			flag=true;
		}
		else if(!curr_sel.equals(gcmp))
		{
			flag=true;
		}
		if(flag)
		{
			curr_sel=gcmp;
			gui.updatePropertiesPanel(gcmp.getPropertiesPanel());
			updateCompSel(gcmp.getSelString());
			gui.invalidate();
		}
	}

	public void finalize()
	{
		try
		{
		   super.finalize();	
		}
		catch (Throwable e)
		{
			System.out.println(e);
		}
		
	}

}
