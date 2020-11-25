package EquipmentDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class Department extends JPanel implements ActionListener {
//	MainFrame mf = new MainFrame();
	JButton hrTeam, tnTeam, adTeam, plTeam, pdTeam, dpTeam;
	// 부서영어 인사, 교육, 행정, 기획, 제작, 개발

	Department() {
		hrTeam = new JButton("인사팀");
		tnTeam = new JButton("교육팀");
		adTeam = new JButton("행정팀");
		plTeam = new JButton("기획팀");
		pdTeam = new JButton("제작팀");
		dpTeam = new JButton("개발팀");

		this.setLayout(null);

		this.add(hrTeam);
		this.add(tnTeam);
		this.add(adTeam);
		this.add(plTeam);
		this.add(pdTeam);
		this.add(dpTeam);
		TitledBorder line = new TitledBorder(new LineBorder(Color.black, 1), "부서팀");
		this.setBorder(line);
		hrTeam.setBorderPainted(false);
		plTeam.setBorderPainted(false);
		adTeam.setBorderPainted(false);
		tnTeam.setBorderPainted(false);
		pdTeam.setBorderPainted(false);
		dpTeam.setBorderPainted(false);
		hrTeam.setBounds(55, 30, 190, 60);
		tnTeam.setBounds(55, 120, 190, 60);
		adTeam.setBounds(55, 210, 190, 60);
		plTeam.setBounds(55, 300, 190, 60);
		pdTeam.setBounds(55, 390, 190, 60);
		dpTeam.setBounds(55, 480, 190, 60);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == hrTeam) {
//			remove(mf.bgPanel2);
		}else if(e.getSource() == tnTeam ) {
			
		}else if(e.getSource() == adTeam) {
			
		}else if(e.getSource() == plTeam) {
			
		}else if(e.getSource() == pdTeam) {
			
		}else if(e.getSource() == dpTeam) {
			
		}

	}
}