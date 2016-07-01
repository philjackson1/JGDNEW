package UI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Font;

public class GuiDUITabButton extends JButton
{
	int buttonSize=100; //pixels
	Font buttonFont = new Font("Tw Cen MT", Font.BOLD,11);
	
	public GuiDUITabButton(String text, ImageIcon img)
	{
		this.setSize(buttonSize,buttonSize);
		this.setIcon(img);
		this.setText(text);
		this.setHorizontalTextPosition(AbstractButton.CENTER);
        this.setVerticalTextPosition(AbstractButton.BOTTOM);
		this.setFont(buttonFont);
	}
	
	public GuiDUITabButton(String text)
	{
		this.setSize(buttonSize,buttonSize);
		//this.setIcon(img);
		this.setText(text);
		this.setHorizontalTextPosition(AbstractButton.CENTER);
        this.setVerticalTextPosition(AbstractButton.BOTTOM);
	}
	
	public int getButtonSize()
	{
		return buttonSize;
	}
}
