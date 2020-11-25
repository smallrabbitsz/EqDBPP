package EquipmentDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{

//	
//	FilterAndPic fap = new FilterAndPic();
	Department panel = new Department();
	AccessorInfo aiPanel;
//	HeaderJTable hjt = new HeaderJTable();
	JPanel bgPanel, bgPanel2;

	MainFrame(String empNum) {
		Color c = new Color(117, 209, 220); // bg패널 색
		Color ci = new Color(255, 0, 0, 0); // 패널 투명색
		aiPanel = new AccessorInfo(empNum);
		bgPanel = new JPanel();
		bgPanel.setBackground(c);
		bgPanel.setPreferredSize(new Dimension(350, 900));
		bgPanel2 = new JPanel();
		bgPanel2.setPreferredSize(new Dimension(1200, 900));
		bgPanel2.setBackground(Color.white);
		panel.setBackground(ci); // 투명색
		panel.setPreferredSize(new Dimension(300, 565));
		aiPanel.setPreferredSize(new Dimension(300, 280));
		aiPanel.setBackground(ci);
		this.setLayout(new FlowLayout(0, 0, 0));
		
		this.add(bgPanel);
		this.add(bgPanel2);
		aiPanel.logout.addActionListener(this);

		bgPanel2.setLayout(new FlowLayout(0, 0, 0));
//		bgPanel2.add(fap);
//		bgPanel2.add(hjt);
//		hjt.setPreferredSize(new Dimension(1170, 570));
//		hjt.setBackground(ci);
//		fap.setPreferredSize(new Dimension(1170, 280));
//		fap.setBackground(ci);
		bgPanel.add(panel);
		bgPanel.add(aiPanel);

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 10, (screenSize.height - frameSize.height) / 22);

		this.setSize(1556, 900);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aiPanel.logout) {
			this.dispose();
			new LoginFrame();
		}
	}
	
	

}