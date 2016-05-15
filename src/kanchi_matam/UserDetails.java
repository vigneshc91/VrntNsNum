package kanchi_matam;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ibm.icu.text.NumberFormat;
import com.itextpdf.text.Element;
import com.itextpdf.text.TabStop.Alignment;

public class UserDetails extends JFrame implements ActionListener {
	
	private String nsNum;
	Dimension dim;
	String openingBalance;
	NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
	double totalAmount;
	DateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
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
	JLabel totalAmountDonated = new JLabel("",SwingConstants.CENTER);
	
	public void CreateFrame(){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		setSize(dim.width, dim.height-taskht);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
//		BorderLayout layout = new BorderLayout();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		
		panel.setLayout(boxLayout);
		getContentPane().add(panel);
		panel.add(user_table);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Font ff = new Font("Arial", Font.PLAIN, 12);
		
		user_table.setFont(ff);
		user_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		user_table.setRowHeight(150);
		
		totalAmountDonated.setFont(new Font("Arial", Font.BOLD, 28));
		
		listTable.setFont(ff);
		listTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		listTable.setRowHeight(50);
		
		model.addColumn("NS. NO.");
		model.addColumn("NAME");
		model.addColumn("ADDRESS");
		model.addColumn("PH. NO.");
		model.addColumn("EMAIL");		
		model.addColumn("RELATIVES");
		model.addColumn("AR");
		model.addColumn("PR");
		
		Dimension tableSize = user_table.getPreferredSize();
		user_table.getColumn("NS. NO.").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		user_table.getColumn("NAME").setPreferredWidth(Math.round(tableSize.width * 0.20f));
		user_table.getColumn("ADDRESS").setPreferredWidth(Math.round(tableSize.width * 0.30f));
		user_table.getColumn("PH. NO.").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		user_table.getColumn("EMAIL").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		user_table.getColumn("RELATIVES").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		user_table.getColumn("AR").setPreferredWidth(Math.round(tableSize.width * 0.05f));
		user_table.getColumn("PR").setPreferredWidth(Math.round(tableSize.width * 0.05f));
		
		
		AddData();
		
		userTableScroll = new JScrollPane(user_table);
		panel.add(userTableScroll);
		
		
		
		totalAmountDonated.setText("Total Amount Donated Rs. "+formatter.format(totalAmount)+"    Opening Balance "+openingBalance);
		panel.add(totalAmountDonated);
		
		
		panel.add(listTable);
		listTableScroll = new JScrollPane(listTable);
		panel.add(listTableScroll);
		
	}
	
	public void AddData(){
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
	    
		try {
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			Statement stm = conn.createStatement();
			
			String st = "select * from details where no = '" + this.nsNum + "'";
			
			ResultSet rs = stm.executeQuery(st);
			
			
			rs.first();
			
			String no = rs.getString(1);
			String nam = rs.getString(2) + " " + rs.getString(3);
			String addr1 = rs.getString(4);
			String addr2 = rs.getString(5);
			String addr3 = rs.getString(6);
			String area1 = rs.getString(7);
			String city1 = rs.getString(8);
			String pinCode1 = rs.getString(9);
			String ph = rs.getString(10);
			String email = rs.getString(11);
			
			String address = "<html>"+addr1;
			address += (addr2 != null && addr2.length() != 0) ? "<br>"+addr2 : "";
			address += (addr3 != null && addr3.length() != 0) ? "<br>"+addr3 : "";
			address += (area1 != null && area1.length() != 0) ? "<br>"+area1 : "";
			address += (city1 != null && city1.length() != 0) ? "<br>"+city1 : "";
			address += (pinCode1 != null && pinCode1.length() != 0) ? " - "+pinCode1 : "";
			
			totalAmount = rs.getDouble(13);
			openingBalance = "Rs. "+formatter.format(totalAmount);
			String otherNsNum = rs.getString(14);
			String annualReport = rs.getString(15);
			String prasadam = rs.getString(16);
			
			
			setTitle(no + " "+ nam);
			model.addRow(new Object[] {no, nam, address, ph, email, otherNsNum, annualReport, prasadam});
			
			conn.close();
			
		    user_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		    //user_table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		    //user_table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		    //user_table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		    user_table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		    user_table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			
			Statement stm1 = conn.createStatement();
			
			String receipt = "select * from bill where no = '" + this.nsNum + "' and status = 'cleared'";
			
			ResultSet rs1 = stm1.executeQuery(receipt);
			
			if(!rs1.isBeforeFirst()){
				
				listModel.addColumn("DONATION DETAILS");
				listModel.addRow(new Object[] {"No entries found"});
				
				listTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			} else {
				
				listModel.addColumn("RT. DT.");
				listModel.addColumn("RT. NO.");
				listModel.addColumn("AMOUNT");
				listModel.addColumn("MODE OF PAYMENT");
				listModel.addColumn("BANK REC");
				
			while(rs1.next()){
				
				int receiptNumber = rs1.getInt("RECEIPT");
				Date receiptDate = rs1.getDate("DAT");
				String donType = rs1.getString("TYPE_DONATN");
				
				String currency = (!donType.equals("FOREIGN CORPUS")) ? "Rs. " : "";
				
				String amount = currency+formatter.format(rs1.getDouble("AMT"));
				String mode = rs1.getString("PAY_MODE");
				
				String num = rs1.getString("CHQNO");
				String bankDrawn = rs1.getString("BANK");
				String bankReceived = rs1.getString("BANK_RECEIVED");
				String modeAndNum;
				
				totalAmount += rs1.getDouble("AMT");
				
				if(mode.equals("CASH"))
					modeAndNum = mode;
				else if(mode.equals("CHQ"))
					modeAndNum = mode + " - " + num + " - " + bankDrawn;
				else
					modeAndNum = mode + " - " + num;
				
				String bankRec = (mode.equals("CASH") && bankReceived.length() == 0) ? "CASH" : bankReceived;
				
				
				
				listModel.addRow(new Object[] { simpleFormat.format(receiptDate), receiptNumber, amount, modeAndNum, bankRec});
				
				listTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				listTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				listTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				listTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				}
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

	
}
