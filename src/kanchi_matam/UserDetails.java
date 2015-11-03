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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserDetails extends JFrame implements ActionListener {
	
	private String nsNum;
	Dimension dim;
	float totalAmountSpend;
	JPanel panel = new JPanel();
	DefaultTableModel model = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	JTable user_table = new JTable(model);
	
	public void CreateFrame(){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		setSize(dim.width, dim.height-taskht);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		getContentPane().add(panel);
		panel.add(user_table);
		
		Font ff = new Font("Arial", Font.PLAIN, 18);
		
		user_table.setFont(ff);
		user_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		user_table.setRowHeight(50);
		
		model.addColumn("Ns Number");
		model.addColumn("Name");
		model.addColumn("Address Line 1");
		model.addColumn("Address Line 2");
		model.addColumn("Area");
		model.addColumn("City");
		model.addColumn("Pin Code");
		model.addColumn("Annual Report");
		model.addColumn("Prasadam");
		AddData();
		
		
		
	}
	
	public void AddData(){
		
		try {
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from details";
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
			totalAmountSpend = rs.getFloat(11);
			String annualReport = rs.getString(13);
			String prasadam = rs.getString(14);
			
			setTitle(no + " "+ nam);
			model.addRow(new Object[] {no, nam, addr1, addr2, area1, city1, pinCode1, annualReport, prasadam});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
