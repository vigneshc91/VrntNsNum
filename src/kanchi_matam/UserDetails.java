package kanchi_matam;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.TabStop.Alignment;

public class UserDetails extends JFrame implements ActionListener, KeyListener {
	
	private String nsNum;
	Dimension dim;
	double totalAmountSpend;
	JPanel panel = new JPanel();
	DefaultTableModel model = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	JTable user_table = new JTable(model);
	
	DefaultTableModel listModel = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	JTable listTable = new JTable(listModel);
	
	JScrollPane userTableScroll, listTableScroll;
	JLabel totalAmountDonated = new JLabel();
	
	public void CreateFrame(){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		setSize(dim.width, dim.height-taskht);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
		BorderLayout layout = new BorderLayout();
		BoxLayout boxLayout = new BoxLayout(panel, 1);
		
		panel.setLayout(boxLayout);
		getContentPane().add(panel);
		panel.add(user_table);
		
		
		Font ff = new Font("Arial", Font.PLAIN, 18);
		
		user_table.setFont(ff);
		user_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));
		user_table.setRowHeight(50);
		
		totalAmountDonated.setFont(new Font("Arial", Font.BOLD, 28));
		
		listTable.setFont(ff);
		listTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));
		listTable.setRowHeight(50);
		
		model.addColumn("Ns Number");
		model.addColumn("Name");
		model.addColumn("Address Line 1");
		model.addColumn("Address Line 2");
		model.addColumn("Area");
		model.addColumn("City");
		model.addColumn("Pin Code");
		model.addColumn("Other Relatives Ns No");
		model.addColumn("Annual Report");
		model.addColumn("Prasadam");
		
		listModel.addColumn("Receipt Date");
		listModel.addColumn("Receipt Number");
		listModel.addColumn("Amount");
		listModel.addColumn("Mode Of Payment");
		listModel.addColumn("Bank Received");
		
		AddData();
		
		userTableScroll = new JScrollPane(user_table);
		panel.add(userTableScroll);
		
		
		
		totalAmountDonated.setText("Total Amount Donated "+totalAmountSpend);
		panel.add(totalAmountDonated);
		
		
		panel.add(listTable);
		listTableScroll = new JScrollPane(listTable);
		panel.add(listTableScroll);
		
	}
	
	public void AddData(){
		
		try {
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			Statement stm = conn.createStatement();
			
			String st = "select * from details where no = '" + this.nsNum + "'";
			
			ResultSet rs = stm.executeQuery(st);
			
			
			rs.first();
			
			String no = rs.getString(1);
			String nam = rs.getString(3) + " " + rs.getString(2);
			String addr1 = rs.getString(4);
			String addr2 = rs.getString(5);
			String area1 = rs.getString(6);
			String city1 = rs.getString(7);
			String pinCode1 = rs.getString(8);
//			String num1 = rs.getString(6);
			totalAmountSpend = rs.getDouble(11);
			String otherNsNum = rs.getString(12);
			String annualReport = rs.getString(13).equals("Selected") ? "Yes" : "No" ;
			String prasadam = rs.getString(14).equals("Selected") ? "Yes" : "No";
			
			
			setTitle(no + " "+ nam);
			model.addRow(new Object[] {no, nam, addr1, addr2, area1, city1, pinCode1, otherNsNum, annualReport, prasadam});
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			
			Statement stm1 = conn.createStatement();
			
			String receipt = "select * from bill where no = '" + this.nsNum + "'";
			
			ResultSet rs1 = stm1.executeQuery(receipt);
			
			while(rs1.next()){
				
				int receiptNumber = rs1.getInt(1);
				Date receiptDate = rs1.getDate(2);
				double amount = rs1.getDouble(14);
				String mode = rs1.getString(15);
				
				int num = rs1.getInt(16);
				String bankReceived = rs1.getString(20);
				String modeAndNum = mode.equals("CASH") ? mode : mode + " - " + num;
				String bankRec = (mode.equals("CASH") && bankReceived.length() == 0) ? "CASH" : bankReceived;
				listModel.addRow(new Object[] {receiptDate, receiptNumber, amount, modeAndNum, bankRec});
				
				}
			
			conn.close();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
        
		
	}
	
	public void ViewDetails(String nsNum){
		this.nsNum = nsNum;
		
		CreateFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == e.VK_ESCAPE){
			this.dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
