import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
/*
 * Redirects console output to JTextArea
 */
public class CustomOutputStream extends OutputStream //output field in swing gui
{
	 private JTextArea textArea;
	 public CustomOutputStream(JTextArea textArea)
	 {
		 this.textArea = textArea;
	 }
	 @Override
	 public void write(int b) throws IOException
	 {
		 textArea.append(String.valueOf((char)b));//redirects data output -- system out -- to the text area
		 textArea.setCaretPosition(textArea.getDocument().getLength());//scrolls the text area to the end of the data
	 }
}
