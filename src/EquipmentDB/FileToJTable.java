package EquipmentDB;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FileToJTable extends JFrame implements ActionListener{
	JScrollPane sp;
	JButton btn;
	String header[] = {"id","pw","name","email","addr"};
	
//	FileToJTable(){
//		btn = new JButton("불러오기");
//		btn.addActionListener(this);
//		this.add(btn,"South");
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setSize(500, 200);
//		this.setVisible(true);
//	}
//	
	public JScrollPane readFileToJScroll(String file, String[] hHeader, String parser){
		JScrollPane hSp=null; 
		JTable hTbl=null;
		DefaultTableModel hModel= null;
		BufferedReader br=null;
		String contents[][]=null;
		try {
			br = new BufferedReader(new FileReader(file));
			//파일 불러오기
			int row=0; // 나중에 줄의 수를 저장할 변수
			String str=null;
			while((str=br.readLine()) != null){
				row++;
			}// 파일의 줄의 개수만 알아오기
			br.close();// 다썼으니 닫기
			
			contents=new String[row][hHeader.length];
			// 알아온 내용으로 2중배열 만들기
			
			br = new BufferedReader(new FileReader(file));
			// 파일 새로 읽기
			
			for(int i=0;i<row;i++){//줄 반복문
				String temp[] = br.readLine().split(parser);
				for(int j=0;j<hHeader.length;j++){//행 반복문
					contents[i][j]=temp[j];
				}
			}
			
			hModel = new DefaultTableModel(contents, hHeader);
			hTbl = new JTable(hModel);
			hSp = new JScrollPane(hTbl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println();
			System.out.println("header의 컬럼 개수, file의 컬럼 개수를 확인해 주세요");
		} finally {			
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return hSp;
	}
	
	
	public JTable readFileToJTable(String file, String[] hHeader, String parser){		
		JTable hTbl=null;
		DefaultTableModel hModel= null;
		BufferedReader br=null;
		String contents[][]=null;
		try {
			br = new BufferedReader(new FileReader(file));
			//파일 불러오기
			int row=0; // 나중에 줄의 수를 저장할 변수
			String str=null;
			while((str=br.readLine()) != null){
				row++;
			}// 파일의 줄의 개수만 알아오기
			br.close();// 다썼으니 닫기
			
			contents=new String[row][hHeader.length];
			// 알아온 내용으로 2중배열 만들기
			
			br = new BufferedReader(new FileReader(file));
			// 파일 새로 읽기
			
			for(int i=0;i<row;i++){//줄 반복문
				String temp[] = br.readLine().split(parser);
				for(int j=0;j<hHeader.length;j++){//행 반복문
					contents[i][j]=temp[j];
				}
			}
			
			hModel = new DefaultTableModel(contents, hHeader);
			hTbl = new JTable(hModel);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println();
			System.out.println("header의 컬럼 개수, file의 컬럼 개수를 확인해 주세요");
		} finally {			
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return hTbl;
	}
	
	
	
	
	
	
	
	
	
	
	public JTable readFileToJTableWithCkBox(String file, String[] hHeader, String parser){		
		JTable hTbl=null;
		DefaultTableModel hModel= null;
		BufferedReader br=null;
		Object contents[][]=null;
		DefaultTableCellRenderer dcr;		
				
		try {
			
			br = new BufferedReader(new FileReader(file));
			//파일 불러오기
			int row=0; // 나중에 줄의 수를 저장할 변수
			String str=null;
			while((str=br.readLine()) != null){
				row++;
			}// 파일의 줄의 개수만 알아오기
			br.close();// 다썼으니 닫기
			
			contents=new Object[row][hHeader.length];
			// 알아온 내용으로 2중배열 만들기
			
			br = new BufferedReader(new FileReader(file));
			// 파일 새로 읽기
			
			for(int i=0;i<row;i++){//줄 반복문
				String temp[] = br.readLine().split(parser);
				for(int j=0;j<hHeader.length;j++){//행 반복문
					
					contents[i][j]=j==0?Boolean.parseBoolean(temp[j]):temp[j];	
				}
			}
			
			hModel = new DefaultTableModel(contents, hHeader);
			hTbl = new JTable(hModel);		
			
			dcr = new DefaultTableCellRenderer(){
				  public Component getTableCellRendererComponent  // 셀렌더러
				   (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
				  {
				   JCheckBox box= new JCheckBox();
				   box.setSelected(((Boolean)value).booleanValue());   
				   box.setHorizontalAlignment(JLabel.CENTER);
				   return box;
				  }
				 }; 
			   
			hTbl.getColumn(hHeader[0]).setCellRenderer(dcr);
			JCheckBox box = new JCheckBox();
			box.setHorizontalAlignment(JLabel.CENTER);
			hTbl.getColumn(hHeader[0]).setCellEditor(new DefaultCellEditor(box));
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println();
			System.out.println("header의 컬럼 개수, file의 컬럼 개수를 확인해 주세요");
		} finally {			
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return hTbl;
	}
	
	
	
	
	
	
	
	
	
	public DefaultTableModel readFileToModel(String file, String[] hHeader, String parser){		
		DefaultTableModel hModel= null;
		BufferedReader br=null;
		String contents[][]=null;
		try {
			br = new BufferedReader(new FileReader(file));
			//파일 불러오기
			int row=0; // 나중에 줄의 수를 저장할 변수
			String str=null;
			while((str=br.readLine()) != null){
				row++;
			}// 파일의 줄의 개수만 알아오기
			br.close();// 다썼으니 닫기
			
			contents=new String[row][hHeader.length];
			// 알아온 내용으로 2중배열 만들기
			
			br = new BufferedReader(new FileReader(file));
			// 파일 새로 읽기
			
			for(int i=0;i<row;i++){//줄 반복문
				String temp[] = br.readLine().split(parser);
				for(int j=0;j<hHeader.length;j++){//행 반복문
					contents[i][j]=temp[j];
				}
			}
			
			hModel = new DefaultTableModel(contents, hHeader);						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println();
			System.out.println("header의 컬럼 개수, file의 컬럼 개수를 확인해 주세요");
		} finally {			
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return hModel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JTable tbl = readFileToJTableWithCkBox("data/members2.txt",header,"/");
		JScrollPane sp = new JScrollPane(tbl);
		this.add(sp);
		revalidate();
		repaint();
		
	}
//	public static void main(String args[]){
//		new FileToJTable();
//	}
}
