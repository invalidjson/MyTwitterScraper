import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Displays License splash page and calls main gui
 */
public class Main 
{
	JFrame licensePopup = new JFrame();
	public void run()
	{
		//License Splash Page
	    JOptionPane.showMessageDialog(licensePopup, "MyTwitterScraper is free software: you can redistribute it and/or modify\n it under the terms of the GNU General Public License as published by\n the Free Software Foundation, either version 3 of the License, or\n any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut without any warranty; without even the implied warranty of\nmerchantability or fitness for a particular purpose. See the\nGNU General Public License for more details at: \nhttp://www.gnu.org/licenses\n\n.");
	   	 
		MainGui mainGui = new MainGui();
		mainGui.buildGui();
	}
	
	public static void main(String[] args){
		new Main().run();
	}
}
