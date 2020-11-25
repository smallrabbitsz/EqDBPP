package EquipmentDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class AccessorInfo extends JPanel implements ActionListener {
	JLabel employeeNum; // 사원 번호
	JLabel accessorName; // 접속자 이름
	JLabel department; // 부서
	JLabel official_respon; // 직급
	JLabel accessorPic; // 접속자 사진
	JButton btn1, btn2, btn3;
	JButton logout;
	JPanel p1;
	JPanel p2;
	String str;
	BufferedReader br;
	// Color iv = new Color(255, 0, 0);

	AccessorInfo(String empNum) {
		employeeNum = new JLabel(empNum); // 로그인 아이디랑 같은 사원 번호 가져오는 방법이 필요함
		accessorName = new JLabel();
		official_respon = new JLabel();
		department = new JLabel();
		accessorPic = new JLabel();
		btn1 = new JButton("관리자 권한");
		btn1.setEnabled(false);
		logout = new JButton("로그아웃");

		try {
			br = new BufferedReader(new FileReader("Data/accessorinfo.txt"));
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] temp = str.split("/");
				if (temp[0].trim().equals(employeeNum.getText().trim())) {
					accessorName.setText("이름 : " + temp[2]);
					department.setText("부서 : " + temp[3]);
					official_respon.setText("직급 : " + temp[4]);
					ImageIcon pic = new ImageIcon("Data/accessorPic/" + temp[0] + ".jpg");
					accessorPic.setIcon(pic);
					if (temp[5].trim().equals("Manager")) {
						btn1.setEnabled(true);
					}
				}
			}
		} catch (IOException e) {
		}

		logout.addActionListener(this);
		btn1.addActionListener(this);

		this.setPreferredSize(new Dimension(300, 300));
		this.setLayout(null);
		// this.setBackground(iv);
		this.add(btn1);
		this.add(employeeNum);
		this.add(accessorName);
		this.add(department);
		this.add(official_respon);
		this.add(accessorPic);
		this.add(logout);
		this.setBorder(BorderFactory.createTitledBorder("접속자 인적사항"));
		TitledBorder line = new TitledBorder(new LineBorder(Color.black, 1), "접속자 인적사항");
		this.setBorder(line);

		employeeNum.setBounds(40, 30, 100, 30);
		accessorName.setBounds(40, 60, 100, 30);
		department.setBounds(40, 90, 100, 30);
		official_respon.setBounds(40, 120, 100, 30);
		accessorPic.setBounds(180, 20, 100, 140);

		btn1.setBounds(30, 180, 80, 30);
		logout.setBounds(180, 230, 100, 30);

		// p1 = new JPanel();
		// p1.setSize(400,900);
		// p1.setBackground(Color.blue);
		// p2 = new JPanel();
		// p2.setSize(1200,900);
		// p2.setBackground(Color.WHITE);
		// this.setLayout(new GridLayout(0,2));
		// this.add(p1);
		// this.add(p2);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setSize(1600, 900);
		// this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) { // 관리자창 구현 (계정 추가/삭제)
			new ManagerMod();
		}
		if (e.getSource() == logout) { // logout 구현 (프레임 탈출 후 로그인프레임으로 전환)
			// new mfDispose();
			new LoginFrame();
		}
	}
}

class ManagerMod extends JFrame implements ActionListener {
	JLabel southLabel;
	JPanel mainPanel;
	JButton create, accountlist, cencel;

	ManagerMod() {
		southLabel = new JLabel("관리자 권한");
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(300, 200);

		this.add(mainPanel);

		create = new JButton("계정 추가");
		accountlist = new JButton("계정 명단");
		cencel = new JButton("취소");

		mainPanel.add(southLabel);
		mainPanel.add(create);
		mainPanel.add(accountlist);
		mainPanel.add(cencel);
		create.addActionListener(this);
		accountlist.addActionListener(this);
		cencel.addActionListener(this);

		southLabel.setBounds(120, 10, 90, 27);
		create.setBounds(50, 40, 90, 27);
		accountlist.setBounds(150, 40, 90, 27);
		cencel.setBounds(160, 120, 78, 25);

		TitledBorder line = new TitledBorder(new LineBorder(Color.black, 1));
		mainPanel.setBorder(line);

		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == create) {
			new Create();
		} else if (e.getSource() == accountlist) {
			new AccountList();
		}

	}
}

class Create extends JFrame implements ActionListener {
	JLabel north_Label;
	JPanel main_Panel, South_Panel;
	JLabel[] l;
	JTextField[] f;
	JButton btn1, btn2;
	String i;
	BufferedReader br;
	PrintWriter pw;

	Create() {
		this.setTitle("SIGN UP");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);

		north_Label = new JLabel("DB계정 생성");
		this.add(north_Label, "North");
		// ===============================================================
		main_Panel = new JPanel();
		this.add(main_Panel, "Center");
		main_Panel.setLayout(new GridLayout(0, 2));

		l = new JLabel[6];
		f = new JTextField[6];

		for (int i = 0; i < 6; i++) {
			l[i] = new JLabel();
			main_Panel.add(l[i]);
			f[i] = new JTextField(10);
			main_Panel.add(f[i]);
		}
		l[0].setText("사원번호(숫자6자리)");
		l[1].setText("비밀번호");
		l[2].setText("이름");
		l[3].setText("부서 (XX팀)");
		l[4].setText("직급");
		l[5].setText("권한(Manager/NotManager");
		// ===============================================================
		South_Panel = new JPanel();
		South_Panel.setLayout(new GridLayout(0, 2));
		this.add(South_Panel, "South");

		btn1 = new JButton("생성");
		South_Panel.add(btn1);
		btn1.addActionListener(this);
		btn2 = new JButton("취소");
		South_Panel.add(btn2);
		btn2.addActionListener(this);

		this.setVisible(true);
	}

	void FWriter() {
		try {
			br = new BufferedReader(new FileReader("Data/accessorinfo.txt"));
			String str = null;
			boolean isOk = false;
			while ((str = br.readLine()) != null) {
				String[] temp = str.split("/");
				if (temp[0].trim().equals(f[0].getText().trim())) {
					isOk = true;
					break;
				}
			}
			if (isOk) {
				JOptionPane.showMessageDialog(this, "이미 있는 아이디입니다.");
				for (int i = 0; i < 6; i++) {
					f[i].setText("");
				}
			} else {
				String Result = f[0].getText() + "/" + f[1].getText() + "/" + f[2].getText() + "/" + f[3].getText()
						+ "/" + f[4].getText() + "/" + f[5].getText() + "\n";
				pw = new PrintWriter(new FileWriter("Data/accessorinfo.txt", true));
				pw.println(Result);
				JOptionPane.showMessageDialog(this, " 계정생성 완료");
				for (int i = 0; i < 6; i++) {
					f[i].setText("");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btn1) {
			FWriter();
		} else if (e.getSource() == btn2) {
			System.exit(0);
		}
	}
}

class AccountList extends JFrame implements ActionListener {
	JPanel alPanel;
	JScrollPane sp;
	DefaultTableModel model = null;
	JTable table;
	FileToJTable ftj;
	String [] header = {"사원번호","비밀번호","이름","부서","직급","권한"};
	BufferedReader br = null;
	
	JButton delete, info, cencel;
	AccountList(){
		alPanel= new JPanel(null);
		delete = new JButton("계정 삭제");
		info = new JButton("계정 정보");
		cencel = new JButton("취소");
		
		ftj = new FileToJTable();
		model = ftj.readFileToModel("Data/accessorinfo.txt", header, "/");
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		TitledBorder line = new TitledBorder(new LineBorder(Color.black, 1),"직원 계정 명단");
		alPanel.setBorder(line);
		
		alPanel.add(sp);
		alPanel.add(delete);
		alPanel.add(info);
		alPanel.add(cencel);
		
		sp.setBounds(50, 30, 400, 380);
		delete.setBounds(40,425, 100, 30);
		info.setBounds(200,425, 100, 30);
		cencel.setBounds(360,425, 100, 30);
		delete.addActionListener(this);
		info.addActionListener(this);
		cencel.addActionListener(this);
		this.add(alPanel);
		this.setSize(500,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}	
	void deleteCell(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == delete) {
			deleteCell();
			
		} else if (e.getSource() == info) {
			this.dispose();
		} else if (e.getSource() == cencel) {

		}
	}

}
