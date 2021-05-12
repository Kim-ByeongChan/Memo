package Project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.File;
import javax.swing.JSeparator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;

public class Mywindow extends JFrame {
   private String exp = "";
   private JMenuBar menuBar;
   private JMenu mnNewMenu;
   private JMenuItem mntmNewMenuItem_1;
   private JMenuItem mntmNewMenuItem_2;
   private JMenuItem mntmNewMenuItem_3;
   private JMenu mnNewMenu_1;
   private JMenu mnNewMenu_2;
   private JMenuItem mntmNewMenuItem_4;
   private JMenuItem mntmNewMenuItem_5;
   private JMenuItem mntmNewMenuItem_6;
   private JSplitPane splitPane;
   private JScrollPane scrollPane;
   private JScrollPane scrollPane_1;
   private JTextArea textArea;
   private JTextArea textArea_1;
   private JMenuItem mntmNewMenuItem_7;
   private JMenuItem mntmNewMenuItem_8;
   private JMenuItem mntmNewMenuItem;
   private String textData; 
   private StringBuffer savingData;
      
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Mywindow frame = new Mywindow();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Mywindow() {
      setTitle("Test");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 435, 299);
      
      menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      
      mnNewMenu = new JMenu("File");
      menuBar.add(mnNewMenu);
      
      mntmNewMenuItem_1 = new JMenuItem("Open");
      mntmNewMenuItem_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
     		JFileChooser chooser = new JFileChooser();
			int result = chooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION) {
				System.out.println("파일 선택");
				File selected = chooser.getSelectedFile();
				System.out.println(selected.getAbsolutePath());
			} else {
				System.out.println("취소");
			}
		}
	});	    
      
      mntmNewMenuItem = new JMenuItem("New");
      mntmNewMenuItem.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		getTextArea().setText("");
      	}
      });
      mnNewMenu.add(mntmNewMenuItem);

     
      mnNewMenu.add(mntmNewMenuItem_1);
      
      mntmNewMenuItem_2 = new JMenuItem("Save");
      mntmNewMenuItem_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            String data = textArea.getText();
            int ret = chooser.showOpenDialog(null);
            if (ret != JFileChooser.APPROVE_OPTION) {
               JOptionPane.showMessageDialog(null, "파일이 없습니다.", "주의", JOptionPane.WARNING_MESSAGE);
            }
            String filePath = chooser.getSelectedFile().getPath();
            filePath += ".java";
            try {
               PrintWriter out = new PrintWriter(new FileWriter(filePath));
               out.print(data);
               out.close();
            } catch(Exception ex) {
               System.out.println(ex);
            }
          }
      });
      mnNewMenu.add(mntmNewMenuItem_2);
     
        	 
      
      mntmNewMenuItem_3 = new JMenuItem("Exit");
      mntmNewMenuItem_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
            
         }
      });
      mnNewMenu.add(mntmNewMenuItem_3);
      
      mnNewMenu_1 = new JMenu("Edite");
      menuBar.add(mnNewMenu_1);
      
      mntmNewMenuItem_4 = new JMenuItem("Copy");
      mntmNewMenuItem_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	  textArea.copy();
        	 }
      });
      mnNewMenu_1.add(mntmNewMenuItem_4);
      
      mntmNewMenuItem_5 = new JMenuItem("Paste");
      mntmNewMenuItem_5.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		textArea.paste();
      	        		
      	}
      });
      mnNewMenu_1.add(mntmNewMenuItem_5);
      
      mntmNewMenuItem_6 = new JMenuItem("Cut");
      mntmNewMenuItem_6.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		textArea.cut();
      	}
      });
      mnNewMenu_1.add(mntmNewMenuItem_6);
      
      mnNewMenu_2 = new JMenu("Compile");
      menuBar.add(mnNewMenu_2);
      
      mntmNewMenuItem_7 = new JMenuItem("Compile");
      mntmNewMenuItem_7.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		textData = textArea.getText();
      		savingData = new StringBuffer(textData);
      		String fileName = getClassName() + ".java";
      		FileUtil.save(savingData, fileName);
      		String cmd = new String("javac " +  fileName);
      		try {
                Process pc = Runtime.getRuntime().exec(cmd);
                
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
               
            }

      		}
      	
      	
     });
      mnNewMenu_2.add(mntmNewMenuItem_7);
      
      mntmNewMenuItem_8 = new JMenuItem("Run");
      mntmNewMenuItem_8.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		String className = getClassName();
            String cmd = new String("java " +  className);

            try {
                Process pc = Runtime.getRuntime().exec(cmd);
                BufferedReader stdOut = new BufferedReader( new InputStreamReader(pc.getInputStream()) );
                String str;
                while( (str = stdOut.readLine()) != null ) {
                    textArea_1.setText(str);
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                textArea_1.setText("실행 에러");
            }
      	}
      });
      mnNewMenu_2.add(mntmNewMenuItem_8);
      
      splitPane = new JSplitPane();
      splitPane.setResizeWeight(0.8);
      splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
      getContentPane().add(splitPane, BorderLayout.CENTER);
      
      scrollPane = new JScrollPane();
      splitPane.setLeftComponent(scrollPane);
      
      textArea = new JTextArea();
      scrollPane.setViewportView(textArea);
      
      scrollPane_1 = new JScrollPane();
      splitPane.setRightComponent(scrollPane_1);
      
      textArea_1 = new JTextArea();
      scrollPane_1.setViewportView(textArea_1);
      
   }

	public JMenuItem actionPerformed() {
		return mntmNewMenuItem;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public String getClassName() {
  		String className = textArea.getText();
  		String[] strArray = className.split(" ");
  		for(int i = 0; i < strArray.length; i++)
  		{
  			if(strArray[i].equals("class")) {
  				className = strArray[i + 1];
  				break;
  			}
  		}
  		return className;
  	}
}
