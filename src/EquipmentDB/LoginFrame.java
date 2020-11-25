package EquipmentDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {
	JTextField tfLogin;
	JPasswordField pfLogin;
	JPanel pnLogin;
	JButton btLogin, btCancel;
	JLabel lbLogin, lbPass;
	BufferedReader br;

	LoginFrame() {
		pnLogin = new JPanel();
		lbLogin = new JLabel("아이디");
		lbPass = new JLabel("비밀번호");
		tfLogin = new JTextField();
		pfLogin = new JPasswordField();
		btLogin = new JButton("로그인");
		btCancel = new JButton("취소");

		pnLogin.setLayout(null);
		pnLogin.add(lbLogin);
		pnLogin.add(lbPass);
		pnLogin.add(tfLogin);
		pnLogin.add(pfLogin);
		pnLogin.add(btLogin);
		pnLogin.add(btCancel);
		lbLogin.setBounds(63, 40, 90, 27);
		lbPass.setBounds(50, 72, 90, 27);
		tfLogin.setBounds(150, 40, 90, 27);
		pfLogin.setBounds(150, 70, 90, 27);
		btLogin.setBounds(45, 120, 78, 25);
		btCancel.setBounds(160, 120, 78, 25);

		btLogin.addActionListener(this);
		tfLogin.addKeyListener(this);
		pfLogin.addKeyListener(this);
		btCancel.addActionListener(this);

		this.add(pnLogin, "Center");

		TitledBorder line = new TitledBorder(new LineBorder(Color.black, 1), "로그인");
		pnLogin.setBorder(line);

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 3, (screenSize.height - frameSize.height) / 3);

		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void Login() {
		try {
			br = new BufferedReader(new FileReader("Data/accessorinfo.txt"));
			boolean isOk = false;
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] temp = str.split("/");
				if (temp[0].trim().equals(tfLogin.getText().trim())
						&& temp[1].trim().equals(pfLogin.getText().trim())) {
					isOk = true;
					break;
				}
			}
			if (isOk) {
				new MainFrame(tfLogin.getText());
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호가 일치하지 않습니다.");
				tfLogin.setText("");
				pfLogin.setText("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//			if()
			Login();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btLogin) {
			Login();
		} else if (e.getSource() == btCancel) {
			System.exit(0); // �۵� ����
		}

	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}
