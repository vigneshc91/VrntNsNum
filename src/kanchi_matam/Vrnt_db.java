package kanchi_matam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;





import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;

import org.freixas.jcalendar.JCalendar;
import org.h2.util.StringUtils;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.Currency;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class Vrnt_db extends JFrame implements ActionListener, MouseListener {

	/**
	 * @param args
	 */
	
	JPanel panel = new JPanel();
	JPanel new_panel = new JPanel();
	JPanel view_panel = new JPanel();
	JPanel edit_panel = new JPanel();
	JPanel bill_panel = new JPanel();
	JPanel donationRegisterPanel = new JPanel();
	JPanel rentPanel = new JPanel();
	JPanel rentRegisterPanel = new JPanel();
	
	JTabbedPane tab_pane = new JTabbedPane();
	JLabel donation_type, pay_mode, pay_num, dated, bank_nam, branch, date, receipt, bank_recvd;
	JLabel addr_line_11, addr_line_21,addr_line_31, area1, city1, pin_code1, addr_line_12, addr_line_22, addr_line_32, area2, city2, pin_code2, addr_line_13, addr_line_23, addr_line_33, area3, city3, pin_code3, addr_line_14, addr_line_24, addr_line_34, area4, city4, pin_code4;
	JLabel viewTabStatusBar;
	JLabel donationTypeLabel, corpusLetterLabel;
	
	JTextField payment_num, bank_name, branch_nam, issue_dat, receipt_no;
	JComboBox don_type, payment_mode, bank_received, donationTypeCombo, corpusCombo;	
	JLabel no_p1, initial_p1, name_p1, add_p1, ph_p1, email_p1, pan_p1, amt_p1, other_ns_num_p1, no_p2, initial_p2, name_p2, add_p2, ph_p2, email_p2, pan_p2, amt_p2, other_ns_num_p2, no_p3, initial_p3, name_p3, add_p3, ph_p3, email_p3, pan_p3, amt_p3, other_ns_num_p3;
	JTextField num_p1, cand_initial_p1, cand_nam_p1, cand_ph_p1, cand_email_p1, cand_pan_p1, cand_amt_p1, cand_other_ns_num_p1, num_p2, cand_initial_p2, cand_nam_p2, cand_ph_p2, cand_email_p2, cand_pan_p2, cand_amt_p2, cand_other_ns_num_p2, num_p3, cand_initial_p3, cand_nam_p3, cand_ph_p3, cand_email_p3, cand_pan_p3, cand_amt_p3, cand_other_ns_num_p3;
	JTextField addr_11, addr_21, addr_31, area_1, city_town1, pin_code_1, addr_12, addr_22, addr_32, area_2, city_town2, pin_code_2, addr_13, addr_23, addr_33, area_3, city_town3, pin_code_3;
	
	//JTextArea cand_add_p1, cand_add_p2, cand_add_p3;
	JButton save;
	JButton reset, reset1, reset2, reset3;
	JButton retrive, retrive1;
	JButton find;
	JButton edit;
	JCalendar calendar1;
	JButton delete;
	JButton proceed, proceed1;
	JFileChooser chose = new JFileChooser();
	JFileChooser gen_pdf = new JFileChooser();
	FileNameExtensionFilter csv_files = new FileNameExtensionFilter("csv files", "csv");
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem Import = new JMenuItem("Import");
	JMenuItem export = new JMenuItem("Export");
	JMenuItem exit = new JMenuItem("Exit");
	
	JMenu annualReport = new JMenu("Annual Report");
	JMenuItem csvAnnualReport = new JMenuItem("CSV");
	JMenuItem pdfAnnualReport = new JMenuItem("PDF");
	
	JMenu prasadam = new JMenu("Prasadam");
	JMenuItem csvPrasadam = new JMenuItem("CSV");
	JMenuItem pdfPrasadam = new JMenuItem("PDF");
	
	JMenuItem pdf = new JMenuItem("Generate Pdf");
	JMenuItem refresh = new JMenuItem("Refresh");
	JMenuItem refreshDonationRegister = new JMenuItem("Refresh Donation");
	JMenuItem refreshRentRegister = new JMenuItem("Refresh Rent");
	JMenuItem search = new JMenuItem("Find");
	JMenuItem searchDonation = new JMenuItem("Find Donation");
	JMenuItem searchRent = new JMenuItem("Find Rent");
	JMenuItem saveStatus = new JMenuItem("Save Status");
	JMenuItem donationRegisterCsv = new JMenuItem("Export Donation");
	JMenuItem rentRegisterCsv = new JMenuItem("Export Rent");
	
	JMenu bill = new JMenu("Bill");
	JMenuItem cancel = new JMenuItem("Cancel Payment");
	JMenuItem statement = new JMenuItem("Statement");
	JMenuItem stmt_full = new JMenuItem("Complete Statement");
	JMenuItem recep = new JMenuItem("Receipt");
	
	JMenu help = new JMenu("Help");
	JMenuItem about = new JMenuItem("About");
	JButton view = new JButton("Refresh");
	DefaultTableModel model = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	DefaultTableModel donationRegisterTableModel = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	DefaultTableModel rentRegisterTableModel = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	DefaultTableModel print_mod = new DefaultTableModel();
	JTable edit_table = new JTable(model);
	JTable donationRegisterTable = new JTable(donationRegisterTableModel);
	JTable rentRegisterTable = new JTable(rentRegisterTableModel);
	
	TableRowSorter sorter, donationSorter, rentSorter;
	JScrollPane jsp, jspDonationRegister, jspRentRegister;
	Dimension dim;
	HashMap annualReportStatus = new HashMap();
	HashMap prasadamStatus = new HashMap();
	JToolBar view_tool = new JToolBar();
	ImageIcon import_img = new ImageIcon(this.getClass().getResource("import.png"));
	ImageIcon export_img = new ImageIcon(this.getClass().getResource("export.png"));
	ImageIcon refresh_img = new ImageIcon(this.getClass().getResource("refresh.png"));
	ImageIcon pdf_img = new ImageIcon(this.getClass().getResource("pdf.png"));
	ImageIcon find_img = new ImageIcon(this.getClass().getResource("find.png"));
	ImageIcon save_img = new ImageIcon(this.getClass().getResource("save.png"));
	ImageIcon about_img = new ImageIcon(this.getClass().getResource("about.png"));
	ImageIcon exit_img = new ImageIcon(this.getClass().getResource("exit.png"));
	ImageIcon cancel_img = new ImageIcon(this.getClass().getResource("cancel.png"));
	ImageIcon st_img = new ImageIcon(this.getClass().getResource("document.png"));
	ImageIcon stmt_img = new ImageIcon(this.getClass().getResource("complete-file.png"));
	ImageIcon rece_img = new ImageIcon(this.getClass().getResource("pay.png"));
	//ImageIcon header = new ImageIcon(this.getClass().getResource("header.png"));
	Font f;
	
	JLabel tenantLabel, date_2, receipt_2, tenant_name, ph_p4, rentMonthLabel, rentYearLabel, corpusLetterLabel_2, amt_p4, serviceTaxRateLabel, ServiceTaxAmountLabel, totalAmountLabel, pay_mode_2, pay_num_2, dated_2, bank_nam_2, branch_2, bank_recvd_2;
	JTextField tenantName, receipt_no_2, addr_14, addr_24, addr_34, area_4, city_town4, pin_code_4, cand_ph_p4, totalAmount, serviceTaxRate, serviceTaxAmount, cand_amt_p4, payment_num_2, bank_name_2, branch_nam_2, issue_dat_2; 
	JComboBox tenantCombo, payment_mode_2, bank_received_2, corpusCombo_2, monthCombo, yearCombo;
	
	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	DateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy");
	DateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Validator validator = new Validator();
	
	Vrnt_db(){
		super("VRNT");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		setSize(dim.width, dim.height-taskht);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JLabel head = new JLabel(header);
		//add(head);
		
		
		//head.setBounds(0, 0, dim.width, 150);
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		//panel.setLayout(new GridBagLayout());
		//add(view);
		//view.addActionListener(this);
		//view.setBounds(10, 10, 100, 20);
		//setLayout(null);
		
		
		chose.addChoosableFileFilter(csv_files);
		f = new Font("Arial", Font.PLAIN, 12);
		new_interior();
		
		view_interior();
		edit_interior();
		bill_interior();
		DonationInterior();
		rentInterior();
		rentRegisterInterior();
		
		tab_pane.addTab("New Entry", new JScrollPane(new_panel));
		tab_pane.addTab("NS Register", view_panel);
		tab_pane.addTab("Update", new JScrollPane(edit_panel));
		tab_pane.addTab("Donation Receipt", new JScrollPane(bill_panel));		
		tab_pane.addTab("Donation Register", new JScrollPane(donationRegisterPanel));
		tab_pane.addTab("Rent", new JScrollPane(rentPanel));
		tab_pane.addTab("Rent Register", new JScrollPane(rentRegisterPanel));
		
		tab_pane.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				// TODO Auto-generated method stub
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
			     int index = sourceTabbedPane.getSelectedIndex();
			     
			     if(index == 1){
			    	 RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");						
			    	 sorter.setRowFilter(rowFilter);
						
			    	 model.setRowCount(0);
			    	 view_tab_data();
											
				 } else if(index == 4){
					 RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");						
			    	 donationSorter.setRowFilter(rowFilter);
						
			    	 donationRegisterTableModel.setRowCount(0);
			    	 DonationRegisterTableData();
						
				 } else if(index == 6){
					 RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");						
			    	 rentSorter.setRowFilter(rowFilter);
						
			    	 rentRegisterTableModel.setRowCount(0);
			    	 rentRegisterTableData();
						
				 }
			}
			
		});
		
		panel.add(tab_pane, BorderLayout.CENTER);
		//interior();
		//add(panel);
		setVisible(true);
	}
	
	public void new_interior(){
		file.setMnemonic(KeyEvent.VK_F);
		bill.setMnemonic(KeyEvent.VK_B);
		help.setMnemonic(KeyEvent.VK_H);
		Import.setMnemonic(KeyEvent.VK_I);
		export.setMnemonic(KeyEvent.VK_E);
		exit.setMnemonic(KeyEvent.VK_X);
		
		annualReport.setMnemonic(KeyEvent.VK_A);
		csvAnnualReport.setMnemonic(KeyEvent.VK_C);
		pdfAnnualReport.setMnemonic(KeyEvent.VK_P);
		
		prasadam.setMnemonic(KeyEvent.VK_P);
		csvPrasadam.setMnemonic(KeyEvent.VK_C);
		pdfPrasadam.setMnemonic(KeyEvent.VK_P);
		
//		pdf.setMnemonic(KeyEvent.VK_P);
		search.setMnemonic(KeyEvent.VK_F);
		refresh.setMnemonic(KeyEvent.VK_R);
		saveStatus.setMnemonic(KeyEvent.VK_S);
		donationRegisterCsv.setMnemonic(KeyEvent.VK_D);
		cancel.setMnemonic(KeyEvent.VK_C);
		statement.setMnemonic(KeyEvent.VK_S);
		
		about.setMnemonic(KeyEvent.VK_A);
		file.add(Import);
		Import.setIcon(import_img);
		Import.addActionListener(this);
		Import.setAccelerator(KeyStroke.getKeyStroke('I', ActionEvent.CTRL_MASK));
		file.add(export);
		export.setIcon(export_img);
		export.addActionListener(this);
		export.setAccelerator(KeyStroke.getKeyStroke('E', ActionEvent.CTRL_MASK));
		file.add(refresh);
		refresh.setIcon(refresh_img);
		refresh.addActionListener(this);
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		refreshDonationRegister.setIcon(refresh_img);
		refreshDonationRegister.addActionListener(this);
		file.add(refreshDonationRegister);
		
		refreshRentRegister.setIcon(refresh_img);
		refreshRentRegister.addActionListener(this);
		file.add(refreshRentRegister);
		
//		file.add(pdf);
//		pdf.setIcon(pdf_img);
//		pdf.addActionListener(this);
//		pdf.setAccelerator(KeyStroke.getKeyStroke('P', ActionEvent.CTRL_MASK));
		
		file.add(annualReport);
		annualReport.setIcon(stmt_img);
		csvAnnualReport.addActionListener(this);
		csvAnnualReport.setIcon(rece_img);
		annualReport.add(csvAnnualReport);
		pdfAnnualReport.addActionListener(this);
		pdfAnnualReport.setIcon(pdf_img);
		annualReport.add(pdfAnnualReport);
		
		file.add(prasadam);
		prasadam.setIcon(st_img);
		csvPrasadam.addActionListener(this);
		csvPrasadam.setIcon(rece_img);
		prasadam.add(csvPrasadam);
		pdfPrasadam.addActionListener(this);
		pdfPrasadam.setIcon(pdf_img);
		prasadam.add(pdfPrasadam);
		
		search.addActionListener(this);
		search.setIcon(find_img);
		search.setAccelerator(KeyStroke.getKeyStroke('F', ActionEvent.CTRL_MASK));
		file.add(search);
		
		searchDonation.addActionListener(this);
		searchDonation.setIcon(find_img);
		searchDonation.setAccelerator(KeyStroke.getKeyStroke('D', ActionEvent.ALT_MASK));
		file.add(searchDonation);
		
		searchRent.addActionListener(this);
		searchRent.setIcon(find_img);
		searchRent.setAccelerator(KeyStroke.getKeyStroke('R', ActionEvent.ALT_MASK));
		file.add(searchRent);
		
		saveStatus.addActionListener(this);
		saveStatus.setIcon(save_img);
		saveStatus.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.CTRL_MASK));
		file.add(saveStatus);
		
		donationRegisterCsv.addActionListener(this);
		donationRegisterCsv.setIcon(rece_img);
		donationRegisterCsv.setAccelerator(KeyStroke.getKeyStroke('D', ActionEvent.CTRL_MASK));
		file.add(donationRegisterCsv);
		
		rentRegisterCsv.addActionListener(this);
		rentRegisterCsv.setIcon(rece_img);
		rentRegisterCsv.setAccelerator(KeyStroke.getKeyStroke('R', ActionEvent.CTRL_MASK));
		file.add(rentRegisterCsv);
		
		file.add(exit);
		exit.setIcon(exit_img);
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.ALT_MASK));
		bill.add(cancel);
		cancel.addActionListener(this);
		cancel.setIcon(cancel_img);
		bill.add(statement);
		statement.addActionListener(this);
		statement.setIcon(st_img);
		bill.add(stmt_full);
		stmt_full.addActionListener(this);
		stmt_full.setIcon(stmt_img);
		bill.add(recep);
		recep.addActionListener(this);
		recep.setIcon(rece_img);
		help.add(about);
		about.setIcon(about_img);
		about.addActionListener(this);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menu.add(file);
		menu.add(bill);
		menu.add(help);
		setJMenuBar(menu);
		no_p1 = new JLabel("NS No");
		
		name_p1 = new JLabel("Name");
		//add_p1 = new JLabel("Address");
		initial_p1 = new JLabel("Initial");
		addr_line_11 = new JLabel("Address Line 1");
		addr_line_21 = new JLabel("Address Line 2");
		addr_line_31 = new JLabel("Address Line 3");
		area1 = new JLabel("Area");
		city1 = new JLabel("City");
		pin_code1 = new JLabel("Pin Code");
		ph_p1 = new JLabel("Phone Num");
		email_p1 = new JLabel("Email");
		pan_p1 = new JLabel("PAN No");
		amt_p1 = new JLabel("Amount");
		other_ns_num_p1 = new JLabel("Other Relatives NS No");
		
		num_p1 = new JTextField(15);
		cand_initial_p1 = new JTextField(15);
		cand_nam_p1 = new JTextField(15);
		//cand_add_p1 = new JTextArea(4, 15);
		addr_11 = new JTextField(15);
		addr_21 = new JTextField(15);
		addr_31 = new JTextField(15);
		area_1 = new JTextField(15);
		city_town1 = new JTextField(15);
		pin_code_1 = new JTextField(15);
		cand_ph_p1 = new JTextField(15);
		cand_email_p1 = new JTextField(15);
		cand_pan_p1 = new JTextField(15);
		cand_amt_p1 = new JTextField(15);
		cand_other_ns_num_p1 = new JTextField(15);
		
		save = new JButton("Save");
		reset = new JButton("Reset");	
		
		cand_other_ns_num_p1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					save.doClick();					
					cand_other_ns_num_p1.transferFocus();
				}
			}
		});
		
		//cand_add_p1.setLineWrap(true);
		//cand_add_p1.setWrapStyleWord(true);
		new_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p1.setBorder(border);
		cand_initial_p1.setBorder(border);
		cand_nam_p1.setBorder(border);
		addr_11.setBorder(border);
		addr_21.setBorder(border);
		addr_31.setBorder(border);
		area_1.setBorder(border);
		city_town1.setBorder(border);
		pin_code_1.setBorder(border);
		//cand_add_p1.setBorder(border);
		cand_ph_p1.setBorder(border);
		cand_email_p1.setBorder(border);
		cand_pan_p1.setBorder(border);
		cand_amt_p1.setBorder(border);
		cand_other_ns_num_p1.setBorder(border);
		save.addActionListener(this);
		reset.addActionListener(this);
		
//		save.setDefaultCapable(true);
//		getRootPane().setDefaultButton(save);
		c.insets = new Insets(15, 15, 10, 10);
		
		c.gridx = 0; c.gridy = 0; 
		new_panel.add(no_p1, c);
		c.gridx = 1; c.gridy = 0;
		new_panel.add(num_p1, c);
		
		c.gridx = 0; c.gridy = 1; 
		new_panel.add(initial_p1, c);
		c.gridx = 1; c.gridy = 1;
		new_panel.add(cand_initial_p1, c);
		
		c.gridx = 0; c.gridy = 2;
		new_panel.add(name_p1, c);
		c.gridx = 1; c.gridy = 2;
		new_panel.add(cand_nam_p1, c);
		
		c.gridx = 0; c.gridy = 3;
		new_panel.add(addr_line_11, c);
		c.gridx = 1; c.gridy = 3;		
		new_panel.add(addr_11, c);
		
		c.gridx = 0; c.gridy = 4;
		new_panel.add(addr_line_21, c);
		c.gridx = 1; c.gridy = 4;
		new_panel.add(addr_21, c);
		
		c.gridx = 0; c.gridy = 5;
		new_panel.add(addr_line_31, c);
		c.gridx = 1; c.gridy = 5;
		new_panel.add(addr_31, c);
		
		c.gridx = 0; c.gridy = 6; 
		new_panel.add(area1, c);
		c.gridx = 1; c.gridy = 6;
		new_panel.add(area_1, c);
		
		
		c.gridx = 0; c.gridy = 7;
		new_panel.add(city1, c);
		c.gridx = 1; c.gridy = 7;
		new_panel.add(city_town1, c);
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		c.gridx = 0; c.gridy = 8;
		new_panel.add(pin_code1, c);
		c.gridx = 1; c.gridy = 8;
		new_panel.add(pin_code_1, c);
		
		c.gridx = 0; c.gridy = 9;
		new_panel.add(ph_p1, c);
		c.gridx = 1; c.gridy = 9;
		new_panel.add(cand_ph_p1, c);
		
		c.gridx = 0; c.gridy = 10;
		new_panel.add(email_p1, c);
		c.gridx = 1; c.gridy = 10;
		new_panel.add(cand_email_p1, c);
		
		c.gridx = 0; c.gridy = 11;
		new_panel.add(pan_p1, c);
		c.gridx = 1; c.gridy = 11;
		new_panel.add(cand_pan_p1, c);
		
		c.gridx = 0; c.gridy = 12;
		new_panel.add(amt_p1, c);
		c.gridx = 1; c.gridy = 12;
		new_panel.add(cand_amt_p1, c);
		
		c.gridx = 0; c.gridy = 13;
		new_panel.add(other_ns_num_p1, c);
		c.gridx = 1; c.gridy = 13;
		new_panel.add(cand_other_ns_num_p1, c);
		
		c.gridx = 1; c.gridy = 14;
		new_panel.add(save, c);
		c.gridx = 2; c.gridy = 14;
		new_panel.add(reset, c);
	/*	cand_add_p1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					if (e.getModifiers() > 0){
						cand_add_p1.transferFocusBackward();
					} else {
						cand_add_p1.transferFocus();
					}
					e.consume();
				}
			}
		}); */
	}
	
	public void view_interior(){
		viewTabStatusBar = new JLabel("");
		
		BorderLayout layout = new BorderLayout();
		view_panel.setLayout(layout);
					
		sorter = new TableRowSorter<DefaultTableModel>(model);
		edit_table.setRowSorter(sorter);
		edit_table.addMouseListener(this);
		edit_table.setRowHeight(60);
		Font ff = new Font("Arial", Font.PLAIN, 16);
		Font tableFont = new Font("Calibiri", Font.PLAIN, 12);
		viewTabStatusBar.setFont(ff);
		edit_table.setFont(tableFont);
		edit_table.getTableHeader().setFont(new Font("Calibiri", Font.BOLD, 12));
		
		InputMap im = edit_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = edit_table.getActionMap();

		KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

		im.put(escapeKey, "Action.escape");
		am.put("Action.escape", new AbstractAction() {
		    public void actionPerformed(ActionEvent evt) {
		    	
		    	RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");
				
				sorter.setRowFilter(rowFilter);  
		    }
		});
		
		model.addColumn("NS NO");
		model.addColumn("INITIAL");
		model.addColumn("NAME");			
		model.addColumn("ADDRESS");
		model.addColumn("AREA");
		model.addColumn("CITY");
		model.addColumn("PINCODE");
		model.addColumn("AMOUNT");
		model.addColumn("RELATIVES");
		model.addColumn("AR");
		model.addColumn("PR");
		
		Dimension tableSize = edit_table.getPreferredSize();
		edit_table.getColumn("NS NO").setPreferredWidth(Math.round(tableSize.width * 0.04f));
		edit_table.getColumn("INITIAL").setPreferredWidth(Math.round(tableSize.width * 0.04f));
		edit_table.getColumn("NAME").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		edit_table.getColumn("ADDRESS").setPreferredWidth(Math.round(tableSize.width * 0.28f));
		edit_table.getColumn("AREA").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		edit_table.getColumn("CITY").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		edit_table.getColumn("PINCODE").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		edit_table.getColumn("AMOUNT").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		edit_table.getColumn("RELATIVES").setPreferredWidth(Math.round(tableSize.width * 0.10f));
		edit_table.getColumn("AR").setPreferredWidth(Math.round(tableSize.width * 0.02f));
		edit_table.getColumn("PR").setPreferredWidth(Math.round(tableSize.width * 0.02f));
		
		view_tab_data();
		
//		edit_table.setEnabled(false);
		//edit_table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    
        jsp = new JScrollPane(edit_table);        
        
        view_panel.add(jsp, layout.CENTER);
        view_panel.add(viewTabStatusBar, layout.SOUTH);
	}
	
	
	void view_tab_data(){
		int count = 0, annualCount = 0, prasadamCount = 0;
		NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
	    
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from details order by last_updated_at desc";
			ResultSet rs = stm.executeQuery(st);
		
		while (rs.next()){
			String no = rs.getString(1).toUpperCase();
			String initial = rs.getString(2).toUpperCase();
			String nam = rs.getString(3).toUpperCase(); 
			String addr1 = rs.getString(4).toUpperCase() + " ";
			String addr2 = (StringUtils.isNullOrEmpty(rs.getString(5))) ? rs.getString(5) : rs.getString(5).toUpperCase();
			addr1 += addr2;
			String addr3 = (StringUtils.isNullOrEmpty(rs.getString(6))) ? rs.getString(6) : rs.getString(6).toUpperCase();
			addr1 += addr3;			
			String area1 = rs.getString(7).toUpperCase();
			String city1 = rs.getString(8).toUpperCase();
			String pinCode1 = rs.getString(9).toUpperCase();
			
			
			String amount = "Rs. "+formatter.format(rs.getFloat(13));
			String relatives = rs.getString(14);
			String annualReport = rs.getString(15);
			String prasadam = rs.getString(16);
			
			count++;
			if(annualReport.equals("S"))annualCount++;
			if(prasadam.equals("S"))prasadamCount++;
			
//			edit_table.getColumnModel().getColumn(7).setCellRenderer(new checkBoxRenderer("unselect"));
			model.addRow(new Object[] {no, initial, nam, addr1, area1, city1, pinCode1, amount, relatives, annualReport, prasadam});
			
		}
		
		viewTabStatusBar.setText("    Total Count : "+count+"    Annual Report Count : "+annualCount+"    Prasadam Count : "+prasadamCount);
				
				
		edit_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		edit_table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		edit_table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		edit_table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		edit_table.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		edit_table.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);

		
		
	}
		catch(Exception e){
			System.err.print(e);
		}
	}
	
	class checkBoxRenderer extends DefaultTableCellRenderer{
				
		public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row, int column)
	    {
			JCheckBox check = new JCheckBox();			
			
			return check;
	    }
		
		
	}
	
	public void DonationInterior(){
		donationRegisterPanel.setLayout(new BorderLayout());
		donationRegisterTable.addMouseListener(this);
//		donationRegisterTable.setRowHeight(150);
		
		donationSorter = new TableRowSorter<DefaultTableModel>(donationRegisterTableModel);
		donationRegisterTable.setRowSorter(donationSorter);
		
		Font ff = new Font("Arial", Font.PLAIN, 12);
		donationRegisterTable.setFont(ff);
		donationRegisterTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		donationRegisterTable.getTableHeader().setPreferredSize(new Dimension(500, 50));
		donationRegisterTable.setRowHeight(60);
		
		InputMap im = donationRegisterTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = donationRegisterTable.getActionMap();

		KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

		im.put(escapeKey, "Action.escape");
		am.put("Action.escape", new AbstractAction() {
		    public void actionPerformed(ActionEvent evt) {
		    	
		    	RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");
				
		    	donationSorter.setRowFilter(rowFilter);  
		    }
		});
		
		donationRegisterTableModel.addColumn("RT. DT.");
		donationRegisterTableModel.addColumn("RT. No.");
		donationRegisterTableModel.addColumn("Donor Type");
		donationRegisterTableModel.addColumn("Name");
		donationRegisterTableModel.addColumn("Address");
		donationRegisterTableModel.addColumn("Type");
		donationRegisterTableModel.addColumn("Amount");
		donationRegisterTableModel.addColumn("Mode of Receipt");
		donationRegisterTableModel.addColumn("<html><center>CHQ/TRF. <br>No.</center></html>");
		donationRegisterTableModel.addColumn("<html><center>CHQ/TRF. <br>DT</center></html>");
		donationRegisterTableModel.addColumn("<html><center>Bank <br>Drawn</center></html>");
		donationRegisterTableModel.addColumn("Branch");
		donationRegisterTableModel.addColumn("<html><center>Bank <br>Received</center></html>");
		donationRegisterTableModel.addColumn("Realz. DT.");
		
		DonationRegisterTableData();				    
		    
		jspDonationRegister = new JScrollPane(donationRegisterTable);        
		donationRegisterPanel.add(jspDonationRegister);
	}
	
	void DonationRegisterTableData(){
		NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
		
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from bill where status = 'cleared'";
			ResultSet rs = stm.executeQuery(st);
		
			while (rs.next()){
				int receiptNo = rs.getInt("RECEIPT");
				String receiptDate = simpleFormat.format( rs.getDate("DAT"));
				String donorType = rs.getString("DONOR_TYPE");
				String nsNum = rs.getString("NO");
				String nam = rs.getString("NAME") + " " + rs.getString("INITIAL");
				String addr1 = rs.getString("ADDR_1");
				String addr2 = rs.getString("ADDR_2");
				String addr3 = rs.getString("ADDR_3");
				String area1 = rs.getString("AREA");
				String city1 = rs.getString("CITY");
				String pinCode1 = rs.getString("PINCODE");
				String ph = rs.getString("PHONE_NUM");
				String email = rs.getString("EMAIL");
				
				if(donorType.equals("NS NO."))
					donorType = "NS NO. "+nsNum;
				
				String address = "<html>"+addr1;
				address += (addr2.length() != 0) ? "<br/>"+addr2 : "";
				address += (addr3.length() != 0) ? "<br/>"+addr3 : "";
				address += (area1.length() != 0) ? "<br/>"+area1 : "";
				address += (city1.length() != 0) ? "<br/>"+city1 : "";
				address += (pinCode1.length() != 0) ? " "+pinCode1 : "";
				address += (ph.length() != 0) ? "<br/>("+ph+")" : "";
				address += (email.length() != 0) ? "<br/>("+email+")" : "";
				address += "</html>";
				String donType = rs.getString("TYPE_DONATN");
				
				String currency = (!donType.equals("FOREIGN CORPUS")) ? "Rs. " : "";
	
				String amount = currency+formatter.format(rs.getDouble("AMT"));
				String mode = rs.getString("PAY_MODE");
				String chqNum = rs.getString("CHQNO");
				String issueDate = rs.getString("ISSUE_DATE");
				String bank = rs.getString("BANK");
				String branch = rs.getString("BRANCH");
				String bankRecvd = rs.getString("BANK_RECEIVED");
				String relizationDate = rs.getString("RELIZATION_DATE");
				String bankRec = (mode.equals("CASH") && bankRecvd.length() == 0) ? "CASH" : bankRecvd;
				
				donationRegisterTableModel.addRow(new Object[] {receiptDate, receiptNo, donorType, nam, address, donType, amount, mode, chqNum, issueDate, bank, branch, bankRec, relizationDate});
			}
	
		} catch (Exception e){
			System.err.println(e);
		}
		
		for (int row = 0; row < donationRegisterTable.getRowCount(); row++)
		{
		    int rowHeight = donationRegisterTable.getRowHeight();

		    for (int column = 0; column < donationRegisterTable.getColumnCount(); column++)
		    {
		        Component comp = donationRegisterTable.prepareRenderer(donationRegisterTable.getCellRenderer(row, column), row, column);
		        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
		     }

		    donationRegisterTable.setRowHeight(row, rowHeight);
		}
		    
		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		    
		    donationRegisterTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		    donationRegisterTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		    donationRegisterTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		    donationRegisterTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		    //donationRegisterTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		    //donationRegisterTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		    donationRegisterTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		    //donationRegisterTable.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
		    //donationRegisterTable.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
		    donationRegisterTable.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
		    donationRegisterTable.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);


	}
	
	void edit_interior(){
		
		no_p3 = new JLabel("Enter NS No");
		initial_p3 = new JLabel("Initial");
		name_p3 = new JLabel("Name");
		//add_p3 = new JLabel("Address");
		addr_line_13 = new JLabel("Address Line 1");
		addr_line_23 = new JLabel("Address Line 2");
		addr_line_33 = new JLabel("Address Line 3");
		area3 = new JLabel("Area");
		city3 = new JLabel("City");
		pin_code3 = new JLabel("Pin Code");
		ph_p3 = new JLabel("Phone Num");
		email_p3 = new JLabel("Email");
		pan_p3 = new JLabel("PAN No");
		amt_p3 = new JLabel("Amount");
		other_ns_num_p3 = new JLabel("Other Relatives NS No");
		
		num_p3 = new JTextField(15);
		cand_initial_p3 = new JTextField(15);
		cand_nam_p3 = new JTextField(15);
		//cand_add_p3 = new JTextArea(4, 15);
		addr_13 = new JTextField(15);
		addr_23 = new JTextField(15);
		addr_33 = new JTextField(15);
		area_3 = new JTextField(15);
		city_town3 = new JTextField(15);
		pin_code_3 = new JTextField(15);
		cand_ph_p3 = new JTextField(15);
		cand_email_p3 = new JTextField(15);
		cand_pan_p3 = new JTextField(15);
		cand_amt_p3 = new JTextField(15);
		cand_other_ns_num_p3 = new JTextField(15);
		
		//save = new JButton("Save");
		//reset = new JButton("Reset");
		find = new JButton("Find");
		edit = new JButton("Update");
		delete = new JButton("Delete");
		reset1 = new JButton("Reset");
		
		num_p3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					find.doClick();
					num_p3.transferFocus();
				}
			}
		});
		
		cand_amt_p3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					edit.doClick();
					cand_amt_p3.transferFocus();
				}
			}
		});
		
		//cand_add_p3.setLineWrap(true);
		//cand_add_p3.setWrapStyleWord(true);
		edit_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p3.setBorder(border);
		cand_initial_p3.setBorder(border);
		cand_nam_p3.setBorder(border);
		//cand_add_p3.setBorder(border);
		addr_13.setBorder(border);
		addr_23.setBorder(border);
		addr_33.setBorder(border);
		area_3.setBorder(border);
		city_town3.setBorder(border);
		pin_code_3.setBorder(border);
		cand_ph_p3.setBorder(border);
		cand_email_p3.setBorder(border);
		cand_pan_p3.setBorder(border);
		cand_amt_p3.setBorder(border);
		cand_other_ns_num_p3.setBorder(border);
		
		cand_nam_p3.setEditable(false);
		cand_initial_p3.setEditable(false);
		addr_13.setEditable(false);
		addr_23.setEditable(false);
		addr_33.setEditable(false);
		area_3.setEditable(false);
		city_town3.setEditable(false);
		pin_code_3.setEditable(false);
		cand_ph_p3.setEditable(false);
		cand_email_p3.setEditable(false);
		cand_pan_p3.setEditable(false);
		//cand_add_p3.setEditable(false);
		cand_amt_p3.setEditable(false);
		cand_other_ns_num_p3.setEditable(false);
		//save.addActionListener(this);
		//reset.addActionListener(this);
		find.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
		reset1.addActionListener(this);
		
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(15, 15, 10, 10);
		edit_panel.add(no_p3, c);
		c.gridx = 1; c.gridy = 0;
		edit_panel.add(num_p3, c);
		c.gridx = 2; c.gridy = 0;
		edit_panel.add(find, c);
		
		c.gridx = 0; c.gridy = 1;
		edit_panel.add(initial_p3, c);
		c.gridx = 1; c.gridy = 1;
		edit_panel.add(cand_initial_p3, c);
		
		c.gridx = 0; c.gridy = 2;
		edit_panel.add(name_p3, c);
		c.gridx = 1; c.gridy = 2;
		edit_panel.add(cand_nam_p3, c);
		
		c.gridx = 0; c.gridy = 3;
		edit_panel.add(addr_line_13, c);
		c.gridx = 1; c.gridy = 3;
		edit_panel.add(addr_13, c);
		
		c.gridx = 0; c.gridy = 4;
		edit_panel.add(addr_line_23, c);
		c.gridx = 1; c.gridy = 4;
		edit_panel.add(addr_23, c);
		
		c.gridx = 0; c.gridy = 5;
		edit_panel.add(addr_line_33, c);
		c.gridx = 1; c.gridy = 5;
		edit_panel.add(addr_33, c);
		
		c.gridx = 0; c.gridy = 6;
		edit_panel.add(area3, c);
		c.gridx = 1; c.gridy = 6;
		edit_panel.add(area_3, c);
		
		c.gridx = 0; c.gridy = 7;
		edit_panel.add(city3, c);
		c.gridx = 1; c.gridy = 7;
		edit_panel.add(city_town3, c);
		
		c.gridx = 0; c.gridy = 8;
		edit_panel.add(pin_code3, c);
		c.gridx = 1; c.gridy = 8;
		edit_panel.add(pin_code_3, c);
		
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 9;
		edit_panel.add(ph_p3, c);
		c.gridx = 1; c.gridy = 9;
		edit_panel.add(cand_ph_p3, c);
		
		c.gridx = 0; c.gridy = 10;
		edit_panel.add(email_p3, c);
		c.gridx = 1; c.gridy = 10;
		edit_panel.add(cand_email_p3, c);
		
		c.gridx = 0; c.gridy = 11;
		edit_panel.add(pan_p3, c);
		c.gridx = 1; c.gridy = 11;
		edit_panel.add(cand_pan_p3, c);
		
		c.gridx = 0; c.gridy = 12;
		edit_panel.add(amt_p3, c);
		c.gridx = 1; c.gridy = 12;
		edit_panel.add(cand_amt_p3, c);
		
		c.gridx = 0; c.gridy = 13;
		edit_panel.add(other_ns_num_p3, c);
		c.gridx = 1; c.gridy = 13;
		edit_panel.add(cand_other_ns_num_p3, c);
		
		c.gridx = 1; c.gridy = 14;
		edit_panel.add(edit, c);
		c.gridx = 2; c.gridy = 14;
		edit_panel.add(delete, c);
		c.gridx = 3; c.gridy = 14;
		edit_panel.add(reset1, c);
	/*	cand_add_p3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					if (e.getModifiers() > 0){
						cand_add_p3.transferFocusBackward();
					} else {
						cand_add_p3.transferFocus();
					}
					e.consume();
				}
			}
		}); */
		
	}
	
	public void bill_interior(){
		String[] donorType = {"NS NO.", "GENERAL DONOR", "UNKNOWN DONOR"};
		String[] pay = {"CORPUS DONATION", "GENERAL DONATION", "FOREIGN CORPUS", "FOREIGN DONATION"};
		String[] acc = {"CASH","CHQ","A/C TRANSFER"};
		String[] corpusDonation = {"YES", "NO"};
		String[] bankReceivedDropDown = {"", "ICICI", "BOB", "IB - WM", "CB - 732", "IB - K", "CB - 645"};
		calendar1 = new JCalendar(JCalendar.DISPLAY_DATE, false);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		final String dat = f.format(d);
		 
		date = new JLabel(dat);
		date.setToolTipText("Not right click to change date");
		date.addMouseListener(new MouseListener(){
			
			public void mouseClicked(MouseEvent e){
				String newdat = JOptionPane.showInputDialog(null, "Enter date", dat);
				if (newdat != null){
					date.setText(newdat);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				date.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		receipt = new JLabel("Receipt No");
		donationTypeLabel = new JLabel("Donor Type");
		no_p2 = new JLabel("Enter NS no");
		initial_p2 = new JLabel("Initial");
		name_p2 = new JLabel("Name");
		//add_p2 = new JLabel("Address");
		addr_line_12 = new JLabel("Address Line 1");
		addr_line_22 = new JLabel("Address Line 2");
		addr_line_32 = new JLabel("Address Line 3");
		area2 = new JLabel("Area");
		city2 = new JLabel("City");
		pin_code2 = new JLabel("Pin Code");
		ph_p2 = new JLabel("Phone Num");
		email_p2 = new JLabel("Email");
		pan_p2 = new JLabel("PAN No");
		amt_p2 = new JLabel("Amount");
		
		donation_type = new JLabel("Type of Donation");
		corpusLetterLabel = new JLabel("Corpus Letter");
		pay_mode = new JLabel("Mode of Payment");
		pay_num = new JLabel("cheque/Transfer No");
		dated = new JLabel("Cheque/Transfer Date");
		bank_nam = new JLabel("Bank Drawn");
		branch = new JLabel("Branch");
		bank_recvd = new JLabel("Bank Received By VRNT");
		
		receipt_no = new JTextField(15);
		donationTypeCombo = new JComboBox(donorType);
		num_p2 = new JTextField(15);
		cand_initial_p2 = new JTextField(15);
		cand_nam_p2 = new JTextField(15);
		//cand_add_p2 = new JTextArea(4, 15);
		addr_12 = new JTextField(15);
		addr_22 = new JTextField(15);
		addr_32 = new JTextField(15);
		area_2 = new JTextField(15);
		city_town2 = new JTextField(15);
		pin_code_2 = new JTextField(15);
		cand_ph_p2 = new JTextField(15);
		cand_email_p2 = new JTextField(15);
		cand_pan_p2 = new JTextField(15);
		cand_amt_p2 = new JTextField(15);
		payment_num = new JTextField(15);
		bank_name = new JTextField(15);
		branch_nam = new JTextField(15);
		issue_dat = new JTextField(15);
		bank_received = new JComboBox(bankReceivedDropDown);
		don_type = new JComboBox(pay);
		payment_mode = new JComboBox(acc);
		corpusCombo = new JComboBox(corpusDonation);
		
		don_type.addActionListener(this);
		donationTypeCombo.addActionListener(this);
		payment_mode.addActionListener(this);
		retrive = new JButton("Retrive");
		proceed = new JButton("Proceed");
		reset2 = new JButton("Reset");
		retrive.addActionListener(this);
		proceed.addActionListener(this);
		reset2.addActionListener(this);
		
		num_p2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					retrive.doClick();
					num_p2.transferFocus();
				}
			}
		});
		
		cand_amt_p2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if((payment_mode.getSelectedIndex() == 0) && (ke.getKeyCode() == KeyEvent.VK_ENTER)){
					proceed.doClick();
					cand_amt_p2.transferFocus();
				}
			}
		});
		
		bank_received.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if((payment_mode.getSelectedIndex() != 0) && (ke.getKeyCode() == KeyEvent.VK_ENTER)){
					proceed.doClick();
					bank_received.transferFocus();
				}
			}
		});
		
		//cand_add_p2.setLineWrap(true);
		//cand_add_p2.setWrapStyleWord(true);
		bill_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		receipt_no.setBorder(border);
		donationTypeCombo.setBorder(border);
		num_p2.setBorder(border);
		cand_initial_p2.setBorder(border);
		cand_nam_p2.setBorder(border);
		//cand_add_p2.setBorder(border);
		addr_12.setBorder(border);
		addr_22.setBorder(border);
		addr_32.setBorder(border);
		area_2.setBorder(border);
		city_town2.setBorder(border);
		pin_code_2.setBorder(border);
		cand_ph_p2.setBorder(border);
		cand_email_p2.setBorder(border);
		cand_pan_p2.setBorder(border);
		cand_amt_p2.setBorder(border);
		payment_num.setBorder(border);
		bank_name.setBorder(border);
		issue_dat.setBorder(border);
		branch_nam.setBorder(border);
		bank_received.setBorder(border);
		corpusCombo.setBorder(border);
		//don_type.setBorder(border);
		//payment_mode.setBorder(border);
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 15; c.gridy = 0;
		bill_panel.add(date, c);
		
		c.gridx = 0; c.gridy = 1;
		bill_panel.add(receipt, c);
		c.gridx = 1; c.gridy = 1;
		bill_panel.add(receipt_no, c);
		
		c.gridx = 0; c.gridy = 2;
		bill_panel.add(donationTypeLabel, c);
		c.gridx = 1; c.gridy = 2;
		bill_panel.add(donationTypeCombo, c);
		
		c.gridx = 0; c.gridy = 3;
		bill_panel.add(no_p2, c);
		c.gridx = 1; c.gridy = 3;
		bill_panel.add(num_p2, c);
		c.gridx = 2; c.gridy = 3;
		bill_panel.add(retrive, c);
		
		c.gridx = 0; c.gridy = 4;
		bill_panel.add(initial_p2, c);
		c.gridx = 1; c.gridy = 4;
		bill_panel.add(cand_initial_p2, c);
		
		
		c.gridx = 0; c.gridy = 5;
		bill_panel.add(name_p2, c);
		c.gridx = 1; c.gridy = 5;
		bill_panel.add(cand_nam_p2, c);
		
		c.gridx = 0; c.gridy = 6;
		bill_panel.add(addr_line_12, c);
		c.gridx = 1; c.gridy = 6;
		bill_panel.add(addr_12, c);
		
		c.gridx = 0; c.gridy = 7;
		bill_panel.add(addr_line_22, c);
		c.gridx = 1; c.gridy = 7;
		bill_panel.add(addr_22, c);
		
		c.gridx = 0; c.gridy = 8;
		bill_panel.add(addr_line_32, c);
		c.gridx = 1; c.gridy = 8;
		bill_panel.add(addr_32, c);
		
		c.gridx = 0; c.gridy = 9;
		bill_panel.add(area2, c);
		c.gridx = 1; c.gridy = 9;
		bill_panel.add(area_2, c);
		
		c.gridx = 0; c.gridy = 10;
		bill_panel.add(city2, c);
		c.gridx = 1; c.gridy = 10;
		bill_panel.add(city_town2, c);
		
		c.gridx = 0; c.gridy = 11;
		bill_panel.add(pin_code2, c);
		c.gridx = 1; c.gridy = 11;
		bill_panel.add(pin_code_2, c);
		
		c.gridx = 0; c.gridy = 12;
		bill_panel.add(ph_p2, c);
		c.gridx = 1; c.gridy = 12;
		bill_panel.add(cand_ph_p2, c);
		
		c.gridx = 0; c.gridy = 13;
		bill_panel.add(email_p2, c);
		c.gridx = 1; c.gridy = 13;
		bill_panel.add(cand_email_p2, c);
		
		c.gridx = 0; c.gridy = 14;
		bill_panel.add(pan_p2, c);
		c.gridx = 1; c.gridy = 14;
		bill_panel.add(cand_pan_p2, c);
		
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 15;
		bill_panel.add(donation_type, c);
		c.gridx = 1; c.gridy = 15;
		bill_panel.add(don_type, c);
		
		c.gridx = 0; c.gridy = 16;
		bill_panel.add(corpusLetterLabel, c);
		c.gridx = 1; c.gridy = 16;
		bill_panel.add(corpusCombo, c);
		
		c.gridx = 0; c.gridy = 17;
		bill_panel.add(amt_p2, c);
		c.gridx = 1; c.gridy = 17;
		bill_panel.add(cand_amt_p2, c);
		
		c.gridx = 0; c.gridy = 18;
		bill_panel.add(pay_mode, c);
		c.gridx = 1; c.gridy = 18;
		bill_panel.add(payment_mode, c);
		
		c.gridx = 0; c.gridy = 19;
		bill_panel.add(pay_num, c);
		c.gridx = 1; c.gridy = 19;
		bill_panel.add(payment_num, c);
		
		c.gridx = 0; c.gridy = 20;
		bill_panel.add(dated, c);
		c.gridx = 1; c.gridy = 20;
		bill_panel.add(issue_dat, c);
		
		c.gridx = 0; c.gridy = 21;
		bill_panel.add(bank_nam, c);
		c.gridx = 1; c.gridy = 21;
		bill_panel.add(bank_name, c);
		
		c.gridx = 0; c.gridy = 22;
		bill_panel.add(branch, c);
		c.gridx = 1; c.gridy = 22;
		bill_panel.add(branch_nam, c);
		
		c.gridx = 0; c.gridy = 23;
		bill_panel.add(bank_recvd, c);
		c.gridx = 1; c.gridy = 23;
		bill_panel.add(bank_received, c);
		
		c.gridx = 1; c.gridy = 24;
		bill_panel.add(proceed, c);
		c.gridx = 2; c.gridy = 24;
		bill_panel.add(reset2, c);
		
		if (payment_mode.getSelectedItem().equals("CASH")){
			payment_num.setEditable(false);
			bank_name.setEditable(false);
			branch_nam.setEditable(false);
			issue_dat.setEditable(false);
			bank_received.setEnabled(false);;
		}
		
		try{
			Class.forName("org.h2.Driver");
   			Connection conn=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
			Statement stm=conn.createStatement();
			String st = "select max(receipt) from bill";
			ResultSet rs = stm.executeQuery(st);
			rs.next();
			int s = rs.getInt(1);		
			if (s == 0){
				receipt_no.setEditable(true);
			} else {
				receipt_no.setText(String.valueOf(s+1));
				receipt_no.setEditable(false);
			}
			conn.close();
		}
		catch (ClassNotFoundException e)
		{
			Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
		}
		catch(SQLException ee)
		{
			System.err.println(ee);
			
			Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void print_interior(){
		JFrame print_frame = new JFrame("Receipt");
		print_frame.setSize(dim.width, dim.height);
		print_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String h = new String("SRI GURUBHYO NAMAHA");
		
		String pan = new String("PAN No.: AAATV3147P");
		String vrnt = new String("VEDA RAKSHNA NIDHI TRUST (Regd.)");
		String vrnt_add = new String("No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.");
		String mail = new String("e-mail: vrnt@vsnl.net");
		String ph = new String("Tel: 044-24740549");
		JTable tab = new JTable(print_mod);
		print_frame.add(tab);
		print_mod.addColumn("No");
		//print_mod.addColumn("Name");
		
		print_mod.addRow(new Object[] {"SRI GURUBHYO NAMAHA", "PAN No.: AAATV3147P", "VEDA RAKSHNA NIDHI TRUST (Regd.)", "No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", "e-mail: vrnt@vsnl.net", "Tel: 044-24740549"});
		print_mod.addRow(new Object[] {"PAN No.: AAATV3147P", "VEDA RAKSHNA NIDHI TRUST (Regd.)", "No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", "e-mail: vrnt@vsnl.net", "Tel: 044-24740549"});
		
		print_frame.setVisible(true);
	}
	
	public void rentInterior(){
	
		String[] tenant = {"", "LV BANK", "WEALTH ADVISOR", "SSSV PATASALA"};
		String[] acc = {"CASH","CHQ","A/C TRANSFER"};
		String[] corpusDonation = {"YES", "NO"};
		String[] month = {"", "Jan", "Feb", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		String[] year = {"", "2015", "2016", "2017", "2018", "2019", "2020"};
		String[] bankReceivedDropDown = {"", "ICICI", "BOB", "IB - WM", "CB - 732", "IB - K", "CB - 645"};
		calendar1 = new JCalendar(JCalendar.DISPLAY_DATE, false);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		final String dat = f.format(d);
		 
		date_2 = new JLabel(dat);
		date_2.setToolTipText("Not right click to change date");
		date_2.addMouseListener(new MouseListener(){
			
			public void mouseClicked(MouseEvent e){
				String newdat = JOptionPane.showInputDialog(null, "Enter date", dat);
				if (newdat != null){
					date_2.setText(newdat);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				date_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		receipt_2 = new JLabel("Receipt No");
		
		
		tenantLabel = new JLabel("Tenant");
		tenant_name = new JLabel("Tenant Name");
		//add_p2 = new JLabel("Address");
		addr_line_14 = new JLabel("Address Line 1");
		addr_line_24 = new JLabel("Address Line 2");
		addr_line_34 = new JLabel("Address Line 3");
		area4 = new JLabel("Area");
		city4 = new JLabel("City");
		pin_code4 = new JLabel("Pin Code");
		ph_p4 = new JLabel("Phone Num");
		rentMonthLabel = new JLabel("Rent For The Month");
		rentYearLabel = new JLabel("Rent For The Year");
		
		
		amt_p4 = new JLabel("Rent Amount");
		serviceTaxRateLabel = new JLabel("Service Tax Rate");
		ServiceTaxAmountLabel = new JLabel("Service Tax Amount");
		totalAmountLabel = new JLabel("Total Amount Received");
		
		
		
		pay_mode_2 = new JLabel("Mode of Payment");
		pay_num_2 = new JLabel("cheque/Transfer No");
		dated_2 = new JLabel("Cheque/Transfer Date");
		bank_nam_2 = new JLabel("Bank Drawn");
		branch_2 = new JLabel("Branch");
		bank_recvd_2 = new JLabel("Bank Received By VRNT");
		
		
		receipt_no_2 = new JTextField(15);
		tenantCombo = new JComboBox(tenant);
		tenantName = new JTextField(15);
		//cand_add_p2 = new JTextArea(4, 15);
		addr_14 = new JTextField(15);
		addr_24 = new JTextField(15);
		addr_34 = new JTextField(15);
		area_4 = new JTextField(15);
		city_town4 = new JTextField(15);
		pin_code_4 = new JTextField(15);
		cand_ph_p4 = new JTextField(15);
		
		cand_amt_p4 = new JTextField(15);		
		serviceTaxRate = new JTextField(15);
		serviceTaxAmount = new JTextField(15);
		totalAmount = new JTextField(15);
		
		payment_num_2 = new JTextField(15);
		bank_name_2 = new JTextField(15);
		branch_nam_2 = new JTextField(15);
		issue_dat_2 = new JTextField(15);
		monthCombo = new JComboBox(month);
		yearCombo = new JComboBox(year);
		payment_mode_2 = new JComboBox(acc);
		bank_received_2 = new JComboBox(bankReceivedDropDown);		
		
		
		
		
		tenantCombo.addActionListener(this);
		payment_mode_2.addActionListener(this);
		retrive1 = new JButton("Retrive");
		proceed1 = new JButton("Proceed");
		reset3 = new JButton("Reset");
		retrive1.addActionListener(this);
		proceed1.addActionListener(this);
		reset3.addActionListener(this);
		
		
		
		serviceTaxRate.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if((payment_mode_2.getSelectedIndex() == 0) && (ke.getKeyCode() == KeyEvent.VK_ENTER)){
					proceed1.doClick();
					serviceTaxRate.transferFocus();
				}
			}
		});
		
		bank_received_2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if((payment_mode_2.getSelectedIndex() != 0) && (ke.getKeyCode() == KeyEvent.VK_ENTER)){
					proceed1.doClick();
					bank_received_2.transferFocus();
				}
			}
		});
		
		//cand_add_p2.setLineWrap(true);
		//cand_add_p2.setWrapStyleWord(true);
		rentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		receipt_no_2.setBorder(border);
		
		
		tenantCombo.setBorder(border);
		tenantName.setBorder(border);
		//cand_add_p2.setBorder(border);
		addr_14.setBorder(border);
		addr_24.setBorder(border);
		addr_34.setBorder(border);
		area_4.setBorder(border);
		city_town4.setBorder(border);
		pin_code_4.setBorder(border);
		cand_ph_p4.setBorder(border);
		totalAmount.setBorder(border);
		serviceTaxRate.setBorder(border);
		serviceTaxAmount.setBorder(border);
		cand_amt_p4.setBorder(border);
		payment_num_2.setBorder(border);
		bank_name_2.setBorder(border);
		issue_dat_2.setBorder(border);
		branch_nam_2.setBorder(border);
		bank_received_2.setBorder(border);		
		monthCombo.setBorder(border);
		yearCombo.setBorder(border);
		//don_type.setBorder(border);
		//payment_mode.setBorder(border);
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 15; c.gridy = 0;
		rentPanel.add(date_2, c);
		
		c.gridx = 0; c.gridy = 1;
		rentPanel.add(receipt_2, c);
		c.gridx = 1; c.gridy = 1;
		rentPanel.add(receipt_no_2, c);
		
		
		
		c.gridx = 0; c.gridy = 2;
		rentPanel.add(tenantLabel, c);
		c.gridx = 1; c.gridy = 2;
		rentPanel.add(tenantCombo, c);
		
		c.gridx = 0; c.gridy = 3;
		rentPanel.add(tenant_name, c);
		c.gridx = 1; c.gridy = 3;
		rentPanel.add(tenantName, c);
		
		c.gridx = 0; c.gridy = 4;
		rentPanel.add(addr_line_14, c);
		c.gridx = 1; c.gridy = 4;
		rentPanel.add(addr_14, c);
		
		c.gridx = 0; c.gridy = 5;
		rentPanel.add(addr_line_24, c);
		c.gridx = 1; c.gridy = 5;
		rentPanel.add(addr_24, c);
		
		c.gridx = 0; c.gridy = 6;
		rentPanel.add(addr_line_34, c);
		c.gridx = 1; c.gridy = 6;
		rentPanel.add(addr_34, c);
		
		c.gridx = 0; c.gridy = 7;
		rentPanel.add(area4, c);
		c.gridx = 1; c.gridy = 7;
		rentPanel.add(area_4, c);
		
		c.gridx = 0; c.gridy = 8;
		rentPanel.add(city4, c);
		c.gridx = 1; c.gridy = 8;
		rentPanel.add(city_town4, c);
		
		c.gridx = 0; c.gridy = 9;
		rentPanel.add(pin_code4, c);
		c.gridx = 1; c.gridy = 9;
		rentPanel.add(pin_code_4, c);
		
		c.gridx = 0; c.gridy = 10;
		rentPanel.add(ph_p4, c);
		c.gridx = 1; c.gridy = 10;
		rentPanel.add(cand_ph_p4, c);
		
		c.gridx = 0; c.gridy = 11;
		rentPanel.add(rentMonthLabel, c);
		c.gridx = 1; c.gridy = 11;
		rentPanel.add(monthCombo, c);
		c.gridx = 2; c.gridy = 11;
		rentPanel.add(yearCombo, c);
		
		
		
		c.gridx = 0; c.gridy = 12;
		rentPanel.add(amt_p4, c);
		c.gridx = 1; c.gridy = 12;
		rentPanel.add(cand_amt_p4, c);
		
		c.gridx = 0; c.gridy = 13;
		rentPanel.add(serviceTaxRateLabel, c);
		c.gridx = 1; c.gridy = 13;
		rentPanel.add(serviceTaxRate, c);
		
		c.gridx = 0; c.gridy = 14;
		rentPanel.add(ServiceTaxAmountLabel, c);
		c.gridx = 1; c.gridy = 14;
		rentPanel.add(serviceTaxAmount, c);
		
		c.gridx = 0; c.gridy = 15;
		rentPanel.add(totalAmountLabel, c);
		c.gridx = 1; c.gridy = 15;
		rentPanel.add(totalAmount, c);
		
		c.gridx = 0; c.gridy = 16;
		rentPanel.add(pay_mode_2, c);
		c.gridx = 1; c.gridy = 16;
		rentPanel.add(payment_mode_2, c);
		
		c.gridx = 0; c.gridy = 17;
		rentPanel.add(pay_num_2, c);
		c.gridx = 1; c.gridy = 17;
		rentPanel.add(payment_num_2, c);
		
		c.gridx = 0; c.gridy = 18;
		rentPanel.add(dated_2, c);
		c.gridx = 1; c.gridy = 18;
		rentPanel.add(issue_dat_2, c);
		
		c.gridx = 0; c.gridy = 19;
		rentPanel.add(bank_nam_2, c);
		c.gridx = 1; c.gridy = 19;
		rentPanel.add(bank_name_2, c);
		
		c.gridx = 0; c.gridy = 20;
		rentPanel.add(branch_2, c);
		c.gridx = 1; c.gridy = 20;
		rentPanel.add(branch_nam_2, c);
		
		c.gridx = 0; c.gridy = 21;
		rentPanel.add(bank_recvd_2, c);
		c.gridx = 1; c.gridy = 21;
		rentPanel.add(bank_received_2, c);
		
		c.gridx = 1; c.gridy = 22;
		rentPanel.add(proceed1, c);
		c.gridx = 2; c.gridy = 22;
		rentPanel.add(reset3, c);
		
		if (payment_mode_2.getSelectedItem().equals("CASH")){
			payment_num_2.setEditable(false);
			bank_name_2.setEditable(false);
			branch_nam_2.setEditable(false);
			issue_dat_2.setEditable(false);
			bank_received_2.setEnabled(false);;
		}
		
		try{
			Class.forName("org.h2.Driver");
   			Connection conn=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
			Statement stm=conn.createStatement();
			String st = "select max(receipt) from rent";
			ResultSet rs = stm.executeQuery(st);
			rs.next();
			int s = rs.getInt(1);		
			if (s == 0){
				receipt_no_2.setEditable(true);
			} else {
				receipt_no_2.setText(String.valueOf(s+1));
				receipt_no_2.setEditable(false);
			}
			conn.close();
		}
		catch (ClassNotFoundException e)
		{
			Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
		}
		catch(SQLException ee)
		{
			System.err.println(ee);
			
			Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
		}

	}
	
	public void rentRegisterInterior(){
		rentRegisterPanel.setLayout(new BorderLayout());
		rentRegisterTable.addMouseListener(this);
//		donationRegisterTable.setRowHeight(150);
		
		rentSorter = new TableRowSorter<DefaultTableModel>(rentRegisterTableModel);
		rentRegisterTable.setRowSorter(rentSorter);
		
		Font ff = new Font("Arial", Font.PLAIN, 12);
		rentRegisterTable.setFont(ff);
		rentRegisterTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		rentRegisterTable.getTableHeader().setPreferredSize(new Dimension(500, 50));
		rentRegisterTable.setRowHeight(60);
		
		InputMap im = rentRegisterTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = rentRegisterTable.getActionMap();

		KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

		im.put(escapeKey, "Action.escape");
		am.put("Action.escape", new AbstractAction() {
		    public void actionPerformed(ActionEvent evt) {
		    	
		    	RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");
				
		    	rentSorter.setRowFilter(rowFilter);  
		    }
		});
		
		rentRegisterTableModel.addColumn("RT. DT.");
		rentRegisterTableModel.addColumn("RT. No.");		
		rentRegisterTableModel.addColumn("Tenant Name");
		rentRegisterTableModel.addColumn("Address");
		rentRegisterTableModel.addColumn("Rent Amt");
		rentRegisterTableModel.addColumn("Service Tax");		
		rentRegisterTableModel.addColumn("Total Amt");
		rentRegisterTableModel.addColumn("Mode of Receipt");
		rentRegisterTableModel.addColumn("<html><center>CHQ/TRF. <br>No.</center></html>");
		rentRegisterTableModel.addColumn("<html><center>CHQ/TRF. <br>DT</center></html>");
		rentRegisterTableModel.addColumn("<html><center>Bank <br>Drawn</center></html>");
		rentRegisterTableModel.addColumn("Branch");
		rentRegisterTableModel.addColumn("<html><center>Bank <br>Received</center></html>");
		rentRegisterTableModel.addColumn("Realz. DT.");
		
		rentRegisterTableData();				    
		    
		jspRentRegister = new JScrollPane(rentRegisterTable);        
		rentRegisterPanel.add(jspRentRegister);
	}
	
	void rentRegisterTableData(){
		NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
		
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/vrnt", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from rent where status = 'cleared'";
			ResultSet rs = stm.executeQuery(st);
		
			while (rs.next()){
				int receiptNo = rs.getInt("RECEIPT");
				String receiptDate = simpleFormat.format( rs.getDate("DAT"));
				
				
				String nam = rs.getString("NAME");
				String addr1 = rs.getString("ADDR_1");
				String addr2 = rs.getString("ADDR_2");
				String addr3 = rs.getString("ADDR_3");
				String area1 = rs.getString("AREA");
				String city1 = rs.getString("CITY");
				String pinCode1 = rs.getString("PINCODE");
				
				
				
				
				String address = "<html>"+addr1;
				address += (addr2.length() != 0) ? "<br/>"+addr2 : "";
				address += (addr3.length() != 0) ? "<br/>"+addr3 : "";
				address += (area1.length() != 0) ? "<br/>"+area1 : "";
				address += (city1.length() != 0) ? "<br/>"+city1 : "";
				address += (pinCode1.length() != 0) ? " "+pinCode1 : "";				
				address += "</html>";
				
				
				
				String currency = "Rs. ";
	
				String rentAmount = currency+formatter.format(rs.getDouble("RENT_AMT"));
				String serviceTax = String.valueOf(rs.getFloat("SERVICE_TAX"));
				String totalAmount = currency+formatter.format(rs.getDouble("TOTAL_AMT"));
				String mode = rs.getString("PAY_MODE");
				String chqNum = rs.getString("CHQNO");
				String issueDate = rs.getString("ISSUE_DATE");
				String bank = rs.getString("BANK");
				String branch = rs.getString("BRANCH");
				String bankRecvd = rs.getString("BANK_RECEIVED");
				String relizationDate = rs.getString("RELIZATION_DATE");
				String bankRec = (mode.equals("CASH") && bankRecvd.length() == 0) ? "CASH" : bankRecvd;
				
				rentRegisterTableModel.addRow(new Object[] {receiptDate, receiptNo, nam, address, rentAmount, serviceTax, totalAmount, mode, chqNum, issueDate, bank, branch, bankRec, relizationDate});
			}
	
		} catch (Exception e){
			System.err.println(e);
		}
		
		for (int row = 0; row < rentRegisterTable.getRowCount(); row++)
		{
		    int rowHeight = rentRegisterTable.getRowHeight();

		    for (int column = 0; column < rentRegisterTable.getColumnCount(); column++)
		    {
		        Component comp = rentRegisterTable.prepareRenderer(rentRegisterTable.getCellRenderer(row, column), row, column);
		        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
		     }

		    rentRegisterTable.setRowHeight(row, rowHeight);
		}
		    
		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		    
		    rentRegisterTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		    rentRegisterTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		    
		    rentRegisterTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		    rentRegisterTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		    rentRegisterTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		   
		   
		    rentRegisterTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		    
		    
		    rentRegisterTable.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
		    rentRegisterTable.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);


	}
	
	public String GetCurrentDateTime(){
		Calendar cal = Calendar.getInstance();
		String currentDateTime = dateformat.format(cal.getTime());
		
		return currentDateTime;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Connection conn = null;
		try {
			conn = DriverManager.
			    getConnection("jdbc:h2:~/vrnt", "sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // add application code here
		Statement stm;
		try {
			stm = conn.createStatement();
			
			String st = "create table if not exists details(no varchar(10) primary key, initial varchar(7), name varchar(50), addr_1 varchar(50), addr_2 varchar(50), addr_3 varchar(50), area varchar(30), city varchar(30), pincode varchar(8), phone_num varchar(40), email varchar(60), pan_no varchar(20), amount double, other_ns_num varchar(60), annual_report varchar(10), prasadam varchar(10), last_updated_at timestamp)";
			String s = "create table if not exists bill(receipt number(10), dat date, donor_type varchar(20), no varchar(10), initial varchar(7), name varchar(50), addr_1 varchar(50), addr_2 varchar(50), addr_3 varchar(50), area varchar(30), city varchar(30), pincode varchar(8), phone_num varchar(40), email varchar(60), pan_no varchar(20), type_donatn varchar(20), amt double, pay_mode varchar(20), chqno varchar(30), issue_date varchar(10), bank varchar(100), branch varchar(100), relization_date varchar(10), bank_received varchar(100),  status varchar(10))";
			String rent = "create table if not exists rent(receipt number(10), dat date, name varchar(70), addr_1 varchar(50), addr_2 varchar(50), addr_3 varchar(50), area varchar(30), city varchar(30), pincode varchar(8), phone_num varchar(40), month varchar(10), year varchar(5), rent_amt double, service_tax float,  total_amt double, pay_mode varchar(20), chqno varchar(30), issue_date varchar(10), bank varchar(100), branch varchar(100), relization_date varchar(10), bank_received varchar(100),  status varchar(10))";
			stm.executeUpdate(st);
			stm.executeUpdate(s);
			stm.executeUpdate(rent);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // NumberFormat formatter = 
       //     new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
       // String result = formatter.format(num);
        

		Vrnt_db vr = new Vrnt_db();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Import")){
			int ret = chose.showOpenDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String fi = s.toString();
			}
			Connection conn = null;
			try{
				Class.forName("org.h2.Driver");
				conn = DriverManager.
					    getConnection("jdbc:h2:~/vrnt", "sa", "");
				Statement stm = conn.createStatement();
				String st = "insert into details (select * from csvread('"+s+"'))";
				stm.executeUpdate(st);
				Telegraph tele = new Telegraph("Success", "Imported successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue que = new TelegraphQueue();
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Imported Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch(SQLException e6){
				System.err.println(e6);
				if (e6.getErrorCode() == 23505){
					Telegraph tele = new Telegraph("Data already exist", "Duplicate entry found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Duplicate Entry Found...", "Data already exists", JOptionPane.ERROR_MESSAGE);
				} else {
					Telegraph tele = new Telegraph("Error", "Some problem occured...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getActionCommand().equals("Export")){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/vrnt", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select * from details')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "Successfully Exported...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getActionCommand().equals("Save")){
			TelegraphQueue que = new TelegraphQueue();
			if(!validator.IsValidNsNumber(num_p1.getText())){
				Telegraph tele = new Telegraph("Enter NS No.", "NS Number can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "NS number can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				num_p1.setFocusable(true);
			} else if(cand_initial_p1.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Initial", "Initial can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "NS number can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				cand_initial_p1.setFocusable(true);
			}else if (cand_nam_p1.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Name can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
					cand_nam_p1.setFocusable(true);
			} else if (pin_code_1.getText().length() != 0 && !validator.IsVaidPinCode(pin_code_1.getText())){
				Telegraph tele = new Telegraph("Invalid Pin Code", "Pin Code format is invalid", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				pin_code_1.setFocusable(true);
			}
			
			else {
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/vrnt", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
		        try {
					Statement stm = conn.createStatement();
					
					double amount = 0;
					if(cand_amt_p1.getText().length() != 0)
						amount =  Double.parseDouble(cand_amt_p1.getText());
					
					String st = "insert into details values("+"'"+num_p1.getText()+"'"+","+" '"+cand_initial_p1.getText()+"', '"+cand_nam_p1.getText()+"'"+","+"'"+addr_11.getText()+"'"+", '"+addr_21.getText()+"', '"+addr_31.getText()+"', '"+area_1.getText()+"', '"+city_town1.getText()+"' ,"+" '"+pin_code_1.getText()+"', '"+cand_ph_p1.getText()+"', '"+cand_email_p1.getText()+"', '"+cand_pan_p1.getText()+"', "+amount+", '"+cand_other_ns_num_p1.getText()+"', 'S', 'S', '"+GetCurrentDateTime()+"')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "Saved successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					if (e2.getErrorCode() == 23505){
						Telegraph tele = new Telegraph("Data already exist", "The NS Number You Entered Is Already Registered...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					//JOptionPane.showMessageDialog(null, "NS number "+num_p1.getText()+" already found...", "Duplicate entry found", JOptionPane.ERROR_MESSAGE);
					}
				}
				num_p1.setText("");
				cand_initial_p1.setText("");
				cand_nam_p1.setText("");
				addr_11.setText("");
				addr_21.setText("");
				addr_31.setText("");
				area_1.setText("");
				city_town1.setText("");
				pin_code_1.setText("");
				cand_ph_p1.setText("");
				cand_email_p1.setText("");
				cand_pan_p1.setText("");
				cand_amt_p1.setText("");
				cand_other_ns_num_p1.setText("");
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
					
				
			
			
			
		} 
		
		else if (e.getSource() == reset){
			num_p1.setText("");
			cand_initial_p1.setText("");
			cand_nam_p1.setText("");
			addr_11.setText("");
			addr_21.setText("");
			addr_31.setText("");
			area_1.setText("");
			city_town1.setText("");
			pin_code_1.setText("");
			cand_ph_p1.setText("");
			cand_email_p1.setText("");
			cand_pan_p1.setText("");
			cand_amt_p1.setText("");
			cand_other_ns_num_p1.setText("");
		} 
		
		
		
		else if (e.getActionCommand().equals("Refresh")){
			model.setRowCount(0);
			view_tab_data();
		}
		
		else if (e.getSource() == refreshDonationRegister){
			donationRegisterTableModel.setRowCount(0);
			DonationRegisterTableData();
		}
		
		else if (e.getSource() == refreshRentRegister){
			rentRegisterTableModel.setRowCount(0);
			rentRegisterTableData();
		}
		
		else if (e.getSource() == csvAnnualReport){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/vrnt", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select no,initial,name,addr_1,addr_2,addr_3,area,city,pincode,phone_num,email from details where annual_report = ''S'' ')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "CSV Generated Successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getSource() == pdfAnnualReport){
			int x = 0, y = 0;
			PdfWriter writer = null;
			int ret = gen_pdf.showSaveDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = gen_pdf.getSelectedFile();
				String stm = s.toString();
				if(!stm.endsWith(".pdf")){
					s = new File(stm+".pdf");
				}
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A4);
			doc.setMargins(30, 30, 10, 10);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 11);
			PdfPTable table = new PdfPTable(3);
			table.setComplete(true);
			table.setWidthPercentage(110);
			
			//Paragraph para = new Paragraph();
			//para.setAlignment(Element.RECTANGLE);
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/vrnt", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "select * from details where annual_report = 'S'";
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	
		        	
		        	String resultAddress = "NS No: "+rs.getString(1)+"\n"+rs.getString(3)+" "+rs.getString(2);
		        	
		        	//address line 1 can't be empty so directly append it
		        	resultAddress += "\n"+rs.getString(4);
		        	
		        	//address line 2 can be empty so we must check it before append
		        	if(rs.getString(5) != null)
		        		resultAddress += "\n"+rs.getString(5);
		        	
		        	//address line 3 can be empty
		        	if(rs.getString(6) != null)
		        		resultAddress += "\n"+rs.getString(6);
		        	
		        	//area can also be empty
		        	if(rs.getString(7) != null)
		        		resultAddress += "\n"+rs.getString(7);
		        	
		        	//city can't be empty anyhow checking for type safety
		        	if(rs.getString(8) != null)
		        		resultAddress += "\n"+rs.getString(8);
		        	
		        	//pin code can be empty
		        	if(rs.getString(9) != null)
		        		resultAddress += "\n"+rs.getString(9);
		        	
		        	//phone number can be empty
		        	if(rs.getString(910) != null)
		        		resultAddress += "\n"+rs.getString(10);
		        	
		        	Phrase h = new Phrase(resultAddress);
		        	
		        	/*if (rs.getString(4) != null && rs.getString(5) != null){
		        		//para.add(rs.getString(1)+rs.getString(2)+rs.getString(3));
		        		h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(5), n);
		        		//ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, h, 10, 800-x, 0);
		        	//	x+=10;
		        	 } else if (rs.getString(4) == null && rs.getString(5) != null) {
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(5), n);
		        	 
		        	 } else if (rs.getString(4) != null && rs.getString(5) == null){
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4), n);
		        		 
		        	 }*/
		        	 
		        	PdfPCell cell = new PdfPCell(h);
		        	 cell.setFixedHeight(100f);
		        	table.addCell(cell);
		        	
		        }
		        
		        table.addCell("");
		        table.addCell("");
		        //table.addCell("");
			}
			catch(Exception e4){
				System.err.println(e4);
			}
			try {
				doc.add(table);
				
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.close();
			//System.out.println("sucess");
			Telegraph tele = new Telegraph("Success", "Pdf generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null, "Pdf generated successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if (e.getSource() == csvPrasadam){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/vrnt", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select no,initial,name,addr_1,addr_2,addr_3,area,city,pincode,phone_num,email from details where prasadam = ''S'' ')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "CSV Generated Successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getSource() == pdfPrasadam){
			int x = 0, y = 0;
			PdfWriter writer = null;
			int ret = gen_pdf.showSaveDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = gen_pdf.getSelectedFile();
				String stm = s.toString();
				if(!stm.endsWith(".pdf")){
					s = new File(stm+".pdf");
				}
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A4);
			doc.setMargins(30, 30, 10, 10);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 11);
			PdfPTable table = new PdfPTable(3);
			table.setComplete(true);
			table.setWidthPercentage(110);
			
			//Paragraph para = new Paragraph();
			//para.setAlignment(Element.RECTANGLE);
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/vrnt", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "select * from details where prasadam = 'S'";
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	
		        	
		        	String resultAddress = "NS No: "+rs.getString(1)+"\n"+rs.getString(3)+" "+rs.getString(2);
		        	
		        	//address line 1 can't be empty so directly append it
		        	resultAddress += "\n"+rs.getString(4);
		        	
		        	//address line 2 can be empty so we must check it before append
		        	if(rs.getString(5) != null)
		        		resultAddress += "\n"+rs.getString(5);
		        	
		        	//address line 3 can be empty
		        	if(rs.getString(6) != null)
		        		resultAddress += "\n"+rs.getString(6);
		        	
		        	//area can also be empty
		        	if(rs.getString(7) != null)
		        		resultAddress += "\n"+rs.getString(7);
		        	
		        	//city can't be empty anyhow checking for type safety
		        	if(rs.getString(8) != null)
		        		resultAddress += "\n"+rs.getString(8);
		        	
		        	//pin code can be empty
		        	if(rs.getString(9) != null)
		        		resultAddress += "\n"+rs.getString(9);
		        	
		        	//phone number can be empty
		        	if(rs.getString(10) != null)
		        		resultAddress += "\n"+rs.getString(10);
		        	
		        	Phrase h = new Phrase(resultAddress);
		        	
		        	/*if (rs.getString(4) != null && rs.getString(5) != null){
		        		//para.add(rs.getString(1)+rs.getString(2)+rs.getString(3));
		        		h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(5), n);
		        		//ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, h, 10, 800-x, 0);
		        	//	x+=10;
		        	 } else if (rs.getString(4) == null && rs.getString(5) != null) {
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(5), n);
		        	 
		        	 } else if (rs.getString(4) != null && rs.getString(5) == null){
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4), n);
		        		 
		        	 }*/
		        	 
		        	PdfPCell cell = new PdfPCell(h);
		        	 cell.setFixedHeight(100f);
		        	table.addCell(cell);
		        	
		        }
		        
		        table.addCell("");
		        table.addCell("");
		        //table.addCell("");
			}
			catch(Exception e4){
				System.err.println(e4);
			}
			try {
				doc.add(table);
				
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.close();
			//System.out.println("sucess");
			Telegraph tele = new Telegraph("Success", "Pdf generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null, "Pdf generated successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource() == search){
			tab_pane.setSelectedIndex(1);
			String nameFilter = JOptionPane.showInputDialog(null, "Enter Name to Find");
			int[] ar = {0,1,2,3,4,5,6,7}; 
			if(nameFilter != null){
				RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)"+nameFilter, ar);
			
				sorter.setRowFilter(rowFilter);
			}
			
		}
		
		else if(e.getSource() == searchDonation){
			tab_pane.setSelectedIndex(4);
			String nameFilter = JOptionPane.showInputDialog(null, "Enter Name to Find");
			 
			if(nameFilter != null){
				RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)"+nameFilter);
			
				donationSorter.setRowFilter(rowFilter);
			}
			
		}
		
		else if(e.getSource() == searchRent){
			tab_pane.setSelectedIndex(6);
			String nameFilter = JOptionPane.showInputDialog(null, "Enter Name to Find");
			 
			if(nameFilter != null){
				RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)"+nameFilter);
			
				rentSorter.setRowFilter(rowFilter);
			}
			
		}
		
		else if(e.getSource() == saveStatus){
			tab_pane.setSelectedIndex(1);
			
//			for(Object key : status.keySet()){
//			System.out.println(key + " : "+ status.get(key));
//		}
			if(annualReportStatus.size() == 0 && prasadamStatus.size() == 0){
				Telegraph tele = new Telegraph("Warning", "No Changes to made...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
			} else{	
				
				Statement stm = null;
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
			        conn = DriverManager.
			            getConnection("jdbc:h2:~/vrnt", "sa", "");
			        stm = conn.createStatement();
				}
				catch(Exception e1){
					System.err.println(e1);
				}
				
				Iterator annualReportIterator = annualReportStatus.entrySet().iterator();
				Iterator prasadamIterator = prasadamStatus.entrySet().iterator();
				try{
					if(annualReportStatus.size() != 0){
						while(annualReportIterator.hasNext()){
							Map.Entry pair = (Map.Entry)annualReportIterator.next();
							
							String st = "update details set annual_report = "+"'"+pair.getValue()+"'"+" where no = "+"'"+pair.getKey()+"'";
						    stm.executeUpdate(st);
						    						
							annualReportIterator.remove();
						} 
						//clear the hashmap dictionary
						annualReportStatus.clear();
					}
					if(prasadamStatus.size() != 0){
						while(prasadamIterator.hasNext()){
							Map.Entry pair = (Map.Entry)prasadamIterator.next();
							
							String st = "update details set prasadam = "+"'"+pair.getValue()+"'"+" where no = "+"'"+pair.getKey()+"'";
						    stm.executeUpdate(st);
						    						
						    prasadamIterator.remove();
						} 
						//clear the hashmap dictionary
						prasadamStatus.clear();
					}
					
					
					
					model.setRowCount(0);
					view_tab_data();
					
					Telegraph tele = new Telegraph("Success", "Operations Completed successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					
				} catch(Exception e1){
					Telegraph tele = new Telegraph("Error", "Some Problem occured. Try Again Later...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					System.err.println(e1);
					
				}
			
			}
		}
		
		else if (e.getSource() == donationRegisterCsv){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				
				 try{
				        TableModel model = donationRegisterTable.getModel();
				        FileWriter excel = new FileWriter(s);

				        for(int i = 0; i < model.getColumnCount(); i++){
				            excel.write(model.getColumnName(i).replaceAll("<[^>]*>", "") + ",");				            
				        }

				        excel.write("\n");

				        for(int i=0; i< model.getRowCount(); i++) {				        	
				            for(int j=0; j < model.getColumnCount(); j++) {
				            	if(model.getValueAt(i,j) != null)
				            		excel.write(model.getValueAt(i,j).toString().replaceAll(",", " ").replaceAll("<[br^>]*>", " ").replaceAll("<[^>]*>", "")+",");				                
				            }
				            excel.write("\n");
				        }

				        excel.close();
				        
				        Telegraph tele = new Telegraph("Success", "Donation Register Successfully Exported...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);

				 	}catch(IOException e1){ 
				    	System.out.println(e1); 
				    	Telegraph tele = new Telegraph("Error", "Some Problem Occured...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
				    }
			}
		}
		
		else if (e.getSource() == rentRegisterCsv){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				
				 try{
				        TableModel model = rentRegisterTable.getModel();
				        FileWriter excel = new FileWriter(s);

				        for(int i = 0; i < model.getColumnCount(); i++){
				            excel.write(model.getColumnName(i).replaceAll("<[^>]*>", "") + ",");				            
				        }

				        excel.write("\n");

				        for(int i=0; i< model.getRowCount(); i++) {				        	
				            for(int j=0; j < model.getColumnCount(); j++) {
				            	if(model.getValueAt(i,j) != null)
				            		excel.write(model.getValueAt(i,j).toString().replaceAll(",", " ").replaceAll("<[br^>]*>", " ").replaceAll("<[^>]*>", "")+",");				                
				            }
				            excel.write("\n");
				        }

				        excel.close();
				        
				        Telegraph tele = new Telegraph("Success", "Rent Register Successfully Exported...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);

				 	}catch(IOException e1){ 
				    	System.out.println(e1); 
				    	Telegraph tele = new Telegraph("Error", "Some Problem Occured...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
				    }
			}
		}

		
		else if (e.getActionCommand().equals("Exit")){
			System.exit(1);
		}
		
		else if (e.getActionCommand().equals("About")){
			JOptionPane.showMessageDialog(null, "Content created by vignesh c", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == find){
			Statement stm = null;
			Connection conn = null;
			if(num_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Warning", "Ns Number Can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
    			TelegraphQueue que = new TelegraphQueue();
    			que.add(tele);
			} else{
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/vrnt", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p3.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p3.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_initial_p3.setText(rs.getString(2));
		        		cand_nam_p3.setText(rs.getString(3));
		        		addr_13.setText(rs.getString(4));
		        		addr_23.setText(rs.getString(5));
		        		addr_33.setText(rs.getString(6));
		        		area_3.setText(rs.getString(7));
		        		city_town3.setText(rs.getString(8));
		        		pin_code_3.setText(rs.getString(9));
		        		cand_ph_p3.setText(rs.getString(10));
		        		cand_email_p3.setText(rs.getString(11));
		        		cand_pan_p3.setText(rs.getString(12));
		        		Double f = rs.getDouble(13);
		        		//System.out.println(rs.getString(2));
		        		cand_amt_p3.setText(f.toString());
		        		cand_other_ns_num_p3.setText(rs.getString(14));
		        		
		        		num_p3.setEditable(false);
		        		cand_initial_p3.setEditable(true);
		        		cand_nam_p3.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		addr_13.setEditable(true);
		        		addr_23.setEditable(true);
		        		addr_33.setEditable(true);
		        		area_3.setEditable(true);
		        		city_town3.setEditable(true);
		        		pin_code_3.setEditable(true);
		        		cand_ph_p3.setEditable(true);
		        		cand_email_p3.setEditable(true);
		        		cand_pan_p3.setEditable(true);
		        		cand_amt_p3.setEditable(true);
		        		cand_other_ns_num_p3.setEditable(true);
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
		    			TelegraphQueue que = new TelegraphQueue();
		    			que.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
			}
			
		} 
		
		else if(e.getActionCommand().equals("Update")){
			TelegraphQueue que = new TelegraphQueue();			
			if(cand_initial_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Initial", "Initial can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "NS number can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				cand_initial_p3.setFocusable(true);
			}else if (cand_nam_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Name can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				cand_nam_p3.setFocusable(true);
			} else if (pin_code_3.getText().length() != 0 && !validator.IsVaidPinCode(pin_code_3.getText())){
				Telegraph tele = new Telegraph("Invalid Pin Code", "Pin Code format is invalid", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				pin_code_3.setFocusable(true);
			}
				
			 else {
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/vrnt", "sa", "");
			        Statement stm = conn.createStatement();
			        String st = "update details set initial = '"+cand_initial_p3.getText()+"', name = '"+cand_nam_p3.getText()+"', "+"addr_1 = "+"'"+addr_13.getText()+"'"+", addr_2 = '"+addr_23.getText()+"', addr_3 = '"+addr_33.getText()+"', area = '"+area_3.getText()+"', city = '"+city_town3.getText()+"', pincode = '"+pin_code_3.getText()+"', phone_num = '"+cand_ph_p3.getText()+"', email = '"+cand_email_p3.getText()+"', pan_no = '"+cand_pan_p3.getText()+"', amount = "+cand_amt_p3.getText()+", other_ns_num = '"+cand_other_ns_num_p3.getText()+"', last_updated_at = '"+GetCurrentDateTime()+"'  where no = "+"'"+num_p3.getText()+"'";
			        stm.executeUpdate(st);
			        Telegraph tele = new Telegraph("Success", "Updated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
			        //JOptionPane.showMessageDialog(null, "Updated Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			        
					num_p3.setText("");
					cand_initial_p3.setText("");
					cand_nam_p3.setText("");
			       // cand_add_p3.setText("");
			        addr_13.setText("");
			        addr_23.setText("");
			        addr_33.setText("");
			        area_3.setText("");
			        city_town3.setText("");
			        pin_code_3.setText("");
			        cand_ph_p3.setText("");
			        cand_email_p3.setText("");
			        cand_pan_p3.setText("");
			        cand_amt_p3.setText("");
			        cand_other_ns_num_p3.setText("");
			        num_p3.setEditable(true);
			        cand_initial_p3.setEditable(false);
			        cand_nam_p3.setEditable(false);
			        //cand_add_p3.setEditable(false);
			        addr_13.setEditable(false);
			        addr_23.setEditable(false);
			        addr_33.setEditable(false);
			        area_3.setEditable(false);
			        city_town3.setEditable(false);
			        pin_code_3.setEditable(false);
			        cand_ph_p3.setEditable(false);
			        cand_email_p3.setEditable(false);
			        cand_pan_p3.setEditable(false);
			        cand_amt_p3.setEditable(false);
			        cand_other_ns_num_p3.setEditable(false);
				}
				catch(Exception e2){
					System.err.println(e2);
				}
			}
			
		} 
		
		else if (e.getActionCommand().equals("Delete")){
			int t = JOptionPane.showConfirmDialog(null, "Are you sure want to delete "+cand_nam_p3.getText(), "Confirm delete", JOptionPane.YES_NO_OPTION);
			if (t == JOptionPane.YES_OPTION){
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/vrnt", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "delete from details where no = "+"'"+num_p3.getText()+"'";
		        stm.executeUpdate(st);
		        Telegraph tele = new Telegraph("Success", "Deleted successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
		        //JOptionPane.showMessageDialog(null, "Deleted Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		        num_p3.setText("");
		        cand_initial_p3.setText("");
		        cand_nam_p3.setText("");
		       // cand_add_p3.setText("");
		        addr_13.setText("");
		        addr_23.setText("");
		        addr_33.setText("");
		        area_3.setText("");
		        area_3.setText("");
		        city_town3.setText("");
		        pin_code_3.setText("");
		        cand_ph_p3.setText("");
		        cand_email_p3.setText("");
		        cand_pan_p3.setText("");
		        cand_amt_p3.setText("");
		        cand_other_ns_num_p3.setText("");
		        
		        num_p3.setEditable(true);
		        cand_initial_p3.setEditable(false);
		        cand_nam_p3.setEditable(false);
		        //cand_add_p3.setEditable(false);
		        addr_13.setEditable(false);
		        addr_23.setEditable(false);
		        addr_33.setEditable(false);
		        area_3.setEditable(false);
		        city_town3.setEditable(false);
		        pin_code_3.setEditable(false);
		        cand_ph_p3.setEditable(false);
		        cand_email_p3.setEditable(false);
		        cand_pan_p3.setEditable(false);
		        cand_amt_p3.setEditable(false);
		        cand_other_ns_num_p3.setEditable(false);
			} catch(Exception e5){
				System.err.println(e5);
			}
		  }
		} 
		
		else if (e.getSource() == reset1){
			num_p3.setText("");
			cand_initial_p3.setText("");
			cand_nam_p3.setText("");
			//cand_add_p3.setText("");
			addr_13.setText("");
	        addr_23.setText("");
	        addr_33.setText("");
	        area_3.setText("");
	        city_town3.setText("");
	        pin_code_3.setText("");
	        cand_ph_p3.setText("");
	        cand_email_p3.setText("");
	        cand_pan_p3.setText("");
			cand_amt_p3.setText("");
			cand_other_ns_num_p3.setText("");
			
			num_p3.setEditable(true);
			cand_initial_p3.setEditable(false);
			cand_nam_p3.setEditable(false);
			
			//cand_add_p3.setEditable(false);
			 addr_13.setEditable(false);
		     addr_23.setEditable(false);
		     addr_33.setEditable(false);
		     area_3.setEditable(false);
		     city_town3.setEditable(false);
		     pin_code_3.setEditable(false);
		     cand_ph_p3.setEditable(false);
		     cand_email_p3.setEditable(false);
		     cand_pan_p3.setEditable(false);
		     cand_amt_p3.setEditable(false);
		     cand_other_ns_num_p3.setEditable(false);
		}
		
		
		
		if (e.getActionCommand().equals("Retrive")){
			Statement stm = null;
			Connection conn = null;
			if(num_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Warning", "Ns Number Can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
			} else{
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/vrnt", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p2.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p2.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_initial_p2.setText(rs.getString(2));
		        		cand_nam_p2.setText(rs.getString(3));
		        		addr_12.setText(rs.getString(4));
		        		addr_22.setText(rs.getString(5));
		        		addr_33.setText(rs.getString(6));
		        		area_2.setText(rs.getString(7));
		        		city_town2.setText(rs.getString(8));
		        		pin_code_2.setText(rs.getString(9));
		        		cand_ph_p2.setText(rs.getString(10));
		        		cand_email_p2.setText(rs.getString(11));
		        		cand_pan_p2.setText(rs.getString(12));
		        		Float f = rs.getFloat(13);
		        		//System.out.println(rs.getString(2));
		        		//cand_amt_p3.setText(f.toString());
		        		num_p2.setEditable(false);
		        		//cand_nam_p3.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		//cand_amt_p3.setEditable(true);
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
			}
		}
		if (e.getSource().equals(payment_mode)){
			if (payment_mode.getSelectedItem().equals("CASH")){
				payment_num.setText("");
				bank_name.setText("");
				branch_nam.setText("");
				issue_dat.setText("");
				bank_received.setSelectedIndex(0);
				payment_num.setEditable(false);
				bank_name.setEditable(false);
				branch_nam.setEditable(false);
				issue_dat.setEditable(false);
				bank_received.setEnabled(false);
			} else if (payment_mode.getSelectedItem().equals("CHQ")){
				payment_num.setText("");
				bank_name.setText("");
				branch_nam.setText("");
				issue_dat.setText("");
				bank_received.setSelectedIndex(0);
				payment_num.setEditable(true);
				bank_name.setEditable(true);
				branch_nam.setEditable(true);
				issue_dat.setEditable(true);
				bank_received.setEnabled(true);
			} else if (payment_mode.getSelectedItem().equals("A/C TRANSFER")){
				payment_num.setText("");
				bank_name.setText("");
				branch_nam.setText("");
				issue_dat.setText("");
				bank_received.setSelectedIndex(0);
				payment_num.setEditable(true);
				bank_name.setEditable(false);
				branch_nam.setEditable(false);
				issue_dat.setEditable(true);
				bank_received.setEnabled(true);
			}
			
		}
		
		if (e.getSource().equals(payment_mode_2)){
			if (payment_mode_2.getSelectedItem().equals("CASH")){
				payment_num_2.setText("");
				bank_name_2.setText("");
				branch_nam_2.setText("");
				issue_dat_2.setText("");
				bank_received_2.setSelectedIndex(0);
				payment_num_2.setEditable(false);
				bank_name_2.setEditable(false);
				branch_nam_2.setEditable(false);
				issue_dat_2.setEditable(false);
				bank_received_2.setEnabled(false);
			} else if (payment_mode_2.getSelectedItem().equals("CHQ")){
				payment_num_2.setText("");
				bank_name_2.setText("");
				branch_nam_2.setText("");
				issue_dat_2.setText("");
				bank_received_2.setSelectedIndex(0);
				payment_num_2.setEditable(true);
				bank_name_2.setEditable(true);
				branch_nam_2.setEditable(true);
				issue_dat_2.setEditable(true);
				bank_received_2.setEnabled(true);
			} else if (payment_mode_2.getSelectedItem().equals("A/C TRANSFER")){
				payment_num_2.setText("");
				bank_name_2.setText("");
				branch_nam_2.setText("");
				issue_dat_2.setText("");
				bank_received_2.setSelectedIndex(0);
				payment_num_2.setEditable(true);
				bank_name_2.setEditable(false);
				branch_nam_2.setEditable(false);
				issue_dat_2.setEditable(true);
				bank_received_2.setEnabled(true);
			}
			
		}
		
		
		if(e.getSource().equals(donationTypeCombo)){
			if(donationTypeCombo.getSelectedItem().equals("NS NO.")){
				num_p2.setText("");
				num_p2.setEditable(true);
				retrive.setEnabled(true);
				cand_nam_p2.setText("");
			} else if(donationTypeCombo.getSelectedItem().equals("GENERAL DONOR")){
				num_p2.setText("");
				num_p2.setEditable(false);
				retrive.setEnabled(false);
				cand_nam_p2.setText("");
			} else {
				num_p2.setText("");
				num_p2.setEditable(false);
				retrive.setEnabled(false);
				cand_nam_p2.setText("UNKNOWN");
			}
		}
		
		if (e.getSource() == reset2){
			donationTypeCombo.setSelectedIndex(0);
			retrive.setEnabled(true);
			num_p2.setEditable(true);
			num_p2.setText("");
			cand_initial_p2.setText("");
			cand_nam_p2.setText("");
			addr_12.setText("");
			addr_22.setText("");
			addr_33.setText("");
			area_2.setText("");
			city_town2.setText("");
			pin_code_2.setText("");
			cand_ph_p2.setText("");
			cand_email_p2.setText("");
			cand_pan_p2.setText("");
			don_type.setSelectedIndex(0);
			corpusCombo.setSelectedIndex(0);
			cand_amt_p2.setText("");
			payment_mode.setSelectedIndex(0);
			payment_num.setText("");
			issue_dat.setText("");
			bank_name.setText("");
			branch_nam.setText("");
			bank_received.setSelectedIndex(0);
			
			
		}
		if (e.getSource() == reset3){
			tenantCombo.setSelectedIndex(0);
			monthCombo.setSelectedIndex(0);
			yearCombo.setSelectedIndex(0);			
			cand_amt_p4.setText("");
			serviceTaxRate.setText("");
			serviceTaxAmount.setText("");
			totalAmount.setText("");
			
			payment_mode_2.setSelectedIndex(0);
			payment_num_2.setText("");
			issue_dat_2.setText("");
			bank_name_2.setText("");
			branch_nam_2.setText("");
			bank_received_2.setSelectedIndex(0);
			
			
		}
		if(e.getSource() == tenantCombo){
			if(tenantCombo.getSelectedIndex() == 0){
				tenantName.setText("");
				addr_14.setText("");
				addr_24.setText("");
				addr_34.setText("");
				area_4.setText("");
				city_town4.setText("");
				pin_code_4.setText("");
				cand_ph_p4.setText("");
			} else if(tenantCombo.getSelectedIndex() == 1){
				tenantName.setText("LAKSHMI VILAS BANK");
				addr_14.setText("NO. 29 GOVINDU STREET");
				addr_24.setText("( GROUND FLOOR )");
				addr_34.setText("");
				area_4.setText("T NAGAR");
				city_town4.setText("CHENNAI");
				pin_code_4.setText("600017");
				cand_ph_p4.setText("044-28155499");
				
			} else if(tenantCombo.getSelectedIndex() == 2){
				tenantName.setText("WEALTH ADVISORS (INDIA)PVT LTD");
				addr_14.setText("NO. 29 GOVINDU STREET");
				addr_24.setText("( FIRST FLOOR )");
				addr_34.setText("");
				area_4.setText("T NAGAR");
				city_town4.setText("CHENNAI");
				pin_code_4.setText("600017");
				cand_ph_p4.setText("044-42922366");
			} else {
				tenantName.setText("SRI SADGURU SABHA VEDA PATASALA C/O SRI. BALASUBRAMANIA GANAPATIGAL");
				addr_14.setText("NO. 64/31");
				addr_24.setText("SUBRAMANIAN STREET");
				addr_34.setText("");
				area_4.setText("WEST MAMBALAM");
				city_town4.setText("CHENNAI");
				pin_code_4.setText("600033");
				cand_ph_p4.setText("044-23710602 / 9444054943");
			}
		}
		
		if (e.getSource() == proceed){
			TelegraphQueue que = new TelegraphQueue();
			if (cand_nam_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(cand_amt_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Amount", "Amount can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if((payment_mode.getSelectedIndex() != 0) && (payment_num.getText().length() == 0)) {
					Telegraph tele = new Telegraph("Enter Cheque/D.D No.", "Cheque/D.D number can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
													
			} else if((payment_mode.getSelectedIndex() != 0) && (issue_dat.getText().length() == 0)) {
				Telegraph tele = new Telegraph("Enter Cheque/Transfer Date", "Cheque/Transfer Date can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
												
			}else if((payment_mode.getSelectedIndex() != 0) && (bank_received.getSelectedIndex() == 0)) {
				Telegraph tele = new Telegraph("Select Bank Received", "Bank Received By VRNT can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
												
			} else {				
				
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/vrnt", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
				
		        try {
		        	String realizationDate = "";
		        	if(payment_mode.getSelectedItem().equals("CASH"))
		        		realizationDate = date.getText();
		        	else if(payment_mode.getSelectedItem().equals("CHQ"))
		        		realizationDate = "";
		        	else
		        		realizationDate = issue_dat.getText();
					Statement stm = conn.createStatement();
					String st = "insert into bill values("+receipt_no.getText()+", '"+date.getText()+"', '"+donationTypeCombo.getSelectedItem()+"', '"+num_p2.getText()+"', '"+cand_initial_p2.getText()+"', '"+cand_nam_p2.getText()+"'"+","+"'"+addr_12.getText()+"'"+", '"+addr_22.getText()+"', '"+addr_32.getText()+"', '"+area_2.getText()+"', '"+city_town2.getText()+"', '"+pin_code_2.getText()+"', '"+cand_ph_p2.getText()+"', '"+cand_email_p2.getText()+"', '"+cand_pan_p2.getText()+"', '"+don_type.getSelectedItem()+"', "+cand_amt_p2.getText()+", '"+payment_mode.getSelectedItem()+"', '"+payment_num.getText()+"', '"+issue_dat.getText()+"', '"+bank_name.getText()+"', '"+branch_nam.getText()+"', '"+realizationDate+"', '"+bank_received.getSelectedItem()+"', 'cleared'"+")";
					stm.executeUpdate(st);
					
					//currently this option is dropped storing the opening balance and then based on the donations in the bill table using the total amount donated
					/*if(num_p2.getText().length() != 0){
						String st1 = "select amount from details where no = '"+num_p2.getText()+"'";
						ResultSet rs2 = stm.executeQuery(st1);
						rs2.next();
						double amt = rs2.getDouble(1);
						
						
						amt += Integer.parseInt(cand_amt_p2.getText());
						String st2 = "update details set amount = "+amt+"where no = '"+num_p2.getText()+"'";
						stm.executeUpdate(st2);
					}*/
					
					Telegraph tele = new Telegraph("Success", "Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
		        
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
			PdfWriter writer = null;
			
			File vrntBill = new File("Vrnt Donation Bill");
			if(!vrntBill.exists()){
				try{
					vrntBill.mkdir();
				} catch(Exception e1){
					System.err.println(e1);
				}
			}
			
			File vrntCorpusLetter = new File("Vrnt Corpus Letter");
			if(!vrntCorpusLetter.exists()){
				try{
					vrntCorpusLetter.mkdir();
				} catch(Exception e1){
					System.err.println(e1);
				}
			}
			
			File s = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			NumberFormat numberFormatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			
			String receivedDate = dateFormat.format(Date.valueOf(date.getText()));
			
			String resultNameFile = "";
			if(donationTypeCombo.getSelectedIndex() == 0)
				resultNameFile = cand_nam_p2.getText() + " ( NS NO. "+num_p2.getText().replace("/", "-") + " )";
			else if(donationTypeCombo.getSelectedIndex() == 1)
				resultNameFile = cand_nam_p2.getText() + " ( GENERAL )";
			else
				resultNameFile = cand_nam_p2.getText();
			
			try {
//				s = File.createTempFile("vrnt_bill", ".pdf");
				
				s = new File(vrntBill.getAbsolutePath()+"/RT. NO. "+receipt_no.getText()+", "+receivedDate+" - "+resultNameFile+" - Rs. "+numberFormatter.format(Integer.parseInt(cand_amt_p2.getText()))+".pdf");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A5);
			//doc.setMargins(36, 72, 108, 180);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD);			
			com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
			com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
			com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
			PdfPTable tab = new PdfPTable(3);
			PdfPCell a, b, c, d, f, g;
			//tab.setWidths(new int[] {80, 60, 60});
			tab.setWidthPercentage(100);
			
			NumberFormat formatter = new RuleBasedNumberFormat(Locale.ENGLISH, RuleBasedNumberFormat.SPELLOUT);
			
			String result = formatter.format(Integer.parseInt(cand_amt_p2.getText()));
			

			Phrase vrnt = new Phrase(" ", fi);

			c = new PdfPCell(vrnt);

			c.setFixedHeight(40);

			Phrase rece = new Phrase("Receipt Number: "+receipt_no.getText(), si);
			
			
			Phrase donat = new Phrase(don_type.getSelectedItem().toString(), si);
			g = new PdfPCell(donat);
			g.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			
			Phrase dat = new Phrase("Date: "+receivedDate, si);
			
			Phrase donationFrom = new Phrase(don_type.getSelectedItem().toString()+" RECEIVED FROM:", n);
			
			String currency = (don_type.getSelectedIndex() != 2) ? "Rs. " : "";
			String currencyFull = (don_type.getSelectedIndex() != 2) ? "RUPEES " : "";
			
			Phrase rs = new Phrase("AMOUNT "+currency+numberFormatter.format(Integer.parseInt(cand_amt_p2.getText())), si);
			Phrase nsno;
			
			if(donationTypeCombo.getSelectedIndex() == 0)
				nsno = new Phrase("N.S No. ( Donor Reference Number ): "+num_p2.getText(), n);
			else
				nsno = new Phrase(donationTypeCombo.getSelectedItem().toString(), n);
			
			Phrase rup = new Phrase(currencyFull+result.toUpperCase()+" ONLY", ni);
			String address = "";
			
			address += (addr_12.getText().length() != 0) ?  addr_12.getText() : "";
				
			address += (addr_22.getText().length() != 0) ? "\n"+addr_22.getText() : "";
			
			address += (addr_32.getText().length() != 0) ? "\n"+addr_32.getText() : "";
			
			address += (area_2.getText().length() != 0) ? "\n"+area_2.getText() : "";
			
			address += (city_town2.getText().length() != 0) ? "\n"+city_town2.getText() : "";
			
			address += (pin_code_2.getText().length() != 0) ? " "+pin_code_2.getText() : " ";
			
			address += (cand_ph_p2.getText().length() != 0) ? "\nPh No: "+cand_ph_p2.getText() : "";
			
			address += (cand_email_p2.getText().length() != 0) ? "\nEmail: "+cand_email_p2.getText() : "";
			
			address += (cand_pan_p2.getText().length() != 0) ? "\nPAN No: "+cand_pan_p2.getText() : "";
			
			
			
			Phrase addr = new Phrase(address, si);
			
			String paymentDetails = ""; 
			paymentDetails += "Mode Of Receipt: "+payment_mode.getSelectedItem();
			switch(payment_mode.getSelectedItem().toString()){
				case "CHQ":
					paymentDetails += "\n" + "CHEQUE NO : " + payment_num.getText()  + "\n" +"Date: " + issue_dat.getText();
					paymentDetails += "\n" + "Bank Drawn: " + bank_name.getText();
					paymentDetails += "\n" + "Branch: " + branch_nam.getText();
					break;
				case "A/C TRANSFER":
					paymentDetails += "\n" + "TRNF NO : " + payment_num.getText()  + "\n" +"Date: " + issue_dat.getText();
					break;
			}
			
			Phrase mod = new Phrase(paymentDetails, si);

			
			Phrase don_for = new Phrase("THIS DONATION IS FOR CORPUS OF THE TRUST", ni);
			Phrase sign_don = new Phrase("SIGNATURE OF DONOR", si);
			Phrase sign_rec = new Phrase("Signature of Receiver", si);
			Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
			

			String name = cand_nam_p2.getText()+" "+cand_initial_p2.getText();

			Phrase nam = new Phrase(name, n);

			

			Phrase amt = new Phrase("Donation towards "+don_type.getSelectedItem()+" fund", si);
			
			Phrase pay = new Phrase("Payment Details", si);
			
			
			Phrase emp = new Phrase("\0");
			
			
			Phrase ins1 = new Phrase(" ", ni);
			
			PdfPCell donationFromCell = new PdfPCell(donationFrom);
			
			PdfPCell nam_c = new PdfPCell(nam);
			nam_c.setFixedHeight(20);
			
			PdfPCell address_c = new PdfPCell(addr);
			address_c.setFixedHeight(80);
			
			PdfPCell dummy = new PdfPCell(emp);
			PdfPCell rece_c = new PdfPCell(rece);
			
			PdfPCell dat_c = new PdfPCell(dat);
			dat_c.setHorizontalAlignment(Element.ALIGN_RIGHT);

			PdfPCell nsno_c = new PdfPCell(nsno);
			PdfPCell amt_c = new PdfPCell(amt);
			PdfPCell rs_c = new PdfPCell(rs);
			
			PdfPCell rup_c = new PdfPCell(rup);
			PdfPCell pay_c = new PdfPCell(pay);
			PdfPCell mod_c = new PdfPCell(mod);
			mod_c.setFixedHeight(80);

			PdfPCell don_for_c = new PdfPCell(don_for);
			PdfPCell empty = new PdfPCell(emp);
			
			PdfPCell sign_don_c = new PdfPCell(sign_don);
			PdfPCell sign_rec_c = new PdfPCell(sign_rec);
			PdfPCell trust_c = new PdfPCell(trust);
			PdfPCell ins1_c = new PdfPCell(ins1);
			ins1_c.setFixedHeight(20);

			empty.setFixedHeight(6);

//			pay_c.setColspan(2);

//			rs_c.setColspan(2);
			rup_c.setColspan(3);
//			mod_c.setColspan(2);
			
			donationFromCell.setColspan(2);
			
			nsno_c.setColspan(2);
			nam_c.setColspan(2);
			address_c.setColspan(2);

			empty.setColspan(3);
			don_for_c.setColspan(3);
			ins1_c.setColspan(3);


			c.setBorder(0);
			g.setBorder(0);
			empty.setBorder(0);
			dummy.setBorder(0);
			donationFromCell.setBorder(0);
			nam_c.setBorder(0);
			rece_c.setBorder(0);
			address_c.setBorder(0);

			dat_c.setBorder(0);

			nsno_c.setBorder(0);

			amt_c.setBorder(0);
			rs_c.setBorder(0);
			rup_c.setBorder(0);
			pay_c.setBorder(0);
			mod_c.setBorder(0);

			don_for_c.setBorder(0);
			sign_don_c.setBorder(0);
			sign_rec_c.setBorder(0);
			trust_c.setBorder(0);
			ins1_c.setBorder(0);

//			PdfContentByte cb = writer.getDirectContent();
//			cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
//			cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
			
//			cb.closePath();
//			cb.stroke();
//			com.itextpdf.text.Image donor_img = null;
//			com.itextpdf.text.Image off_img = null;
			com.itextpdf.text.Image  sign = null;
			PdfPCell signature = null;
			
			try {
//				donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
//				off_img = Image.getInstance(this.getClass().getResource("off.png"));
				sign = Image.getInstance(this.getClass().getResource("ET-SIGN.png"));
				signature = new PdfPCell(sign);
//				donor_img.scaleAbsolute(150, 150);

				sign.scaleAbsolute(80, 15);
//				off_img.scaleAbsolute(150, 150);
//				donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
//				off_img.setAbsolutePosition(120, 90);
				
			} catch (BadElementException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (MalformedURLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			signature.setBorder(0);
			signature.setColspan(3);
			signature.setHorizontalAlignment(Element.ALIGN_RIGHT);
			signature.setPaddingRight(30);
			
			//donor copy

			tab.addCell(c);

			
			tab.addCell(empty);
			
			
			tab.addCell(rece_c);
			tab.addCell(g);
			tab.addCell(dat_c);
			tab.addCell(empty);
			
			tab.addCell(donationFromCell);
			tab.addCell(dummy);
			
			tab.addCell(nsno_c);
			tab.addCell(rs_c);
			
			
			
			tab.addCell(nam_c);
			tab.addCell(dummy);
			
			
			
			tab.addCell(address_c);
			tab.addCell(mod_c);
			
			tab.addCell(rup_c);
			tab.addCell(don_for_c);

			//tab.addCell(empty);
			
			
			
			tab.addCell(signature);
			tab.addCell(sign_don_c);
			tab.addCell(sign_rec_c);
			tab.addCell(trust_c);
			//tab.addCell(empty);
			tab.addCell(ins1_c);

			
			
			tab.addCell(empty);
			tab.addCell(empty);
			
			
			//office copy
			
//			tab.addCell(c);

			
			tab.addCell(empty);
			
			PdfPCell veda = new PdfPCell(new Phrase("VEDA RAKSHANA NIDHI TRUST, CHENNAI", n));
			veda.setBorder(0);
			veda.setColspan(2);
			
			PdfPCell officeCopy = new PdfPCell(new Phrase("OFFICE COPY", n));
			officeCopy.setBorder(0);
			
			
			tab.addCell(veda);
			tab.addCell(officeCopy);
			
			tab.addCell(rece_c);
			tab.addCell(g);
			tab.addCell(dat_c);
			tab.addCell(empty);
			
			tab.addCell(donationFromCell);
			tab.addCell(dummy);
			
			tab.addCell(nsno_c);
			tab.addCell(rs_c);
			
			
			String bankRecString = (bank_received.getSelectedItem().toString().length() !=0 ) ? bank_received.getSelectedItem().toString() : "CASH";
			PdfPCell bankRec = new PdfPCell(new Phrase("Bank Received: "+bankRecString + "\nCORPUS LETTER: "+corpusCombo.getSelectedItem(), n));
			bankRec.setBorder(0);
			
			tab.addCell(nam_c);
			tab.addCell(bankRec);
			
			
			
			tab.addCell(address_c);
			tab.addCell(mod_c);
			
			tab.addCell(rup_c);
			tab.addCell(don_for_c);

			//tab.addCell(empty);
			PdfPCell forVrnt = new PdfPCell(new Phrase("FOR VRNT:", n));
			forVrnt.setBorder(0);
			
			tab.addCell(dummy);
			tab.addCell(dummy);
			tab.addCell(forVrnt);
			
			tab.addCell(empty);
			
			tab.addCell(sign_don_c);
			tab.addCell(dummy);
			tab.addCell(sign_rec_c);
			//tab.addCell(empty);
			
			
			if(donationTypeCombo.getSelectedIndex() == 0){
			
			double totalAmount = 0;
			try{
				Connection conn1 = DriverManager.
					    getConnection("jdbc:h2:~/vrnt", "sa", "");
				
				Statement stm1 = conn1.createStatement();
				
				String st = "select amt from bill where no = '"+num_p2.getText()+"'";
				
				ResultSet rs1 = stm1.executeQuery(st);
				
				while(rs1.next()){
					double amount = rs1.getDouble("AMT");
					totalAmount += amount;
				}
				
			} catch(SQLException e1){
				e1.printStackTrace();
			}
			
			PdfPCell donationAmount = new PdfPCell(new Phrase("TOTAL DONATION AMOUNT TO VRNT UNDER CORPUS LIFE DONOR DONOR NUMBER -\n N.S. NO. "+num_p2.getText()+" AS ON "+receivedDate+" : Rs. "+numberFormatter.format(totalAmount), n)); 
			donationAmount.setBorder(0);
			donationAmount.setColspan(3);
			donationAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			tab.addCell(donationAmount);
			
			}
			
			//tab.addCell(ins1_c);
			
			try {
				doc.add(tab);
				
//				doc.add(donor_img);
//				doc.add(off_img);
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if((donationTypeCombo.getSelectedIndex() == 0) && (corpusCombo.getSelectedIndex() == 0)){
				File corpusLetter = null;
				try {					
					corpusLetter = new File(vrntCorpusLetter.getAbsolutePath()+"/RT. NO. "+receipt_no.getText()+".pdf");
					Document document = new Document();
					writer = PdfWriter.getInstance(document, new FileOutputStream(corpusLetter));
					document.setPageSize(PageSize.A4);
					
					Paragraph receipt = new Paragraph("RECEIPT NO. "+receipt_no.getText());
					receipt.setAlignment(Element.ALIGN_RIGHT);
					
					Paragraph date = new Paragraph("DATE: "+receivedDate);
					date.setAlignment(Element.ALIGN_RIGHT);
					
					Paragraph from = new Paragraph("THE EXECUTIVE TRUSTEE\nVEDA RAKSHANA NIDHI TRUST,\nNO. 64/31 SUBRAMANIAN STREET\nWEST MAMBALAM\nCHENNAI 600033\nPHONE:  044-24740549\nEMAIL:  vrnt@vsnl.net / office@vrnt.org");
					
					Paragraph to = new Paragraph("Dear Sir,");
					
					Paragraph sub = new Paragraph("Sub: Donation towards Corpus Fund of the trust");
					Paragraph content = new Paragraph("I am happy to know the efforts of VRNT, constituted under the directions of Pujyasri Mahaswamigal of kanchi kamakoti Peetam for preservation of Vedic Vibrations through traditional Vedic education. I am furnishing below details about me and I would request you to kindly accept  Donation of Rs. "+numberFormatter.format(Integer.parseInt(cand_amt_p2.getText()))+" ( "+payment_mode.getSelectedItem()+" ) for corpus fund of the trust and utilise the amount, for the activities of the Trust.");
					content.setAlignment(Element.ALIGN_JUSTIFIED);
					
					Paragraph enrollment = new Paragraph("ENROLMENT FOR LIFE DONOR CORPUS");
					enrollment.setAlignment(Element.ALIGN_CENTER);
					
					Phrase nsNum = new Phrase(num_p2.getText()+" "+cand_initial_p2.getText()+" "+cand_nam_p2.getText());
					
					String addressString = "";
					addressString += (addr_12.getText().length() != 0) ?  addr_12.getText() : "";
					
					addressString += (addr_22.getText().length() != 0) ? "\n"+addr_22.getText() : "";
					
					addressString += (addr_32.getText().length() != 0) ? "\n"+addr_32.getText() : "";
					
					addressString += (area_2.getText().length() != 0) ? "\n"+area_2.getText() : "";
					
					addressString += (city_town2.getText().length() != 0) ? "\n"+city_town2.getText() : "";
					
					addressString += (pin_code_2.getText().length() != 0) ? " "+pin_code_2.getText() : "";
					
					Phrase addrPhrase = new Phrase(addressString);
					Phrase phNum = new Phrase(cand_ph_p2.getText());
					Phrase email = new Phrase(cand_email_p2.getText());
					Phrase star = new Phrase("");
					Phrase pan = new Phrase(cand_pan_p2.getText());
					Paragraph signPara = new Paragraph("SIGNATURE OF THE DONOR");
					signPara.setAlignment(Element.ALIGN_RIGHT);
					Paragraph datePara = new Paragraph("DATE:                                      ");
					datePara.setAlignment(Element.ALIGN_RIGHT);
					
					PdfPTable table1 = new PdfPTable(new float[]{1,5,1,5});
					
					PdfPCell oneCell = new PdfPCell(new Phrase("1"));
					PdfPCell twoCell = new PdfPCell(new Phrase("2"));
					PdfPCell threeCell = new PdfPCell(new Phrase("3"));
					PdfPCell fourCell = new PdfPCell(new Phrase("4"));
					PdfPCell fiveCell = new PdfPCell(new Phrase("5"));
					PdfPCell sixCell = new PdfPCell(new Phrase("6"));
					
					oneCell.setBorder(0);
					twoCell.setBorder(0);
					threeCell.setBorder(0);
					fourCell.setBorder(0);
					fiveCell.setBorder(0);
					sixCell.setBorder(0);
					
					PdfPCell nsNumCell = new PdfPCell(new Phrase("NS NO. & NAME"));
					PdfPCell addrCell = new PdfPCell(new Phrase("ADDRESS WITH PINCODE"));
					PdfPCell phCell = new PdfPCell(new Phrase("PHONE NO."));
					PdfPCell mailCell = new PdfPCell(new Phrase("EMAIL ID"));
					PdfPCell starCell = new PdfPCell(new Phrase("BIRTH STAR"));
					PdfPCell panCell = new PdfPCell(new Phrase("PAN"));
					
					nsNumCell.setBorder(0);
					addrCell.setBorder(0);
					phCell.setBorder(0);
					mailCell.setBorder(0);
					starCell.setBorder(0);
					panCell.setBorder(0);
					
					PdfPCell colon = new PdfPCell(new Phrase(":"));
					colon.setBorder(0);
					
					PdfPCell numCell = new PdfPCell(nsNum);
					PdfPCell addrsCell = new PdfPCell(addrPhrase);
					PdfPCell phNumCell = new PdfPCell(phNum);
					PdfPCell emailCell = new PdfPCell(email);
					PdfPCell birthStarCell = new PdfPCell(star);
					PdfPCell panNumCell = new PdfPCell(pan);
					
					numCell.setBorder(0);
					addrsCell.setBorder(0);
					phNumCell.setBorder(0);
					emailCell.setBorder(0);
					birthStarCell.setBorder(0);
					panNumCell.setBorder(0);
					
					
					
					table1.addCell(oneCell);
					table1.addCell(nsNumCell);
					table1.addCell(colon);
					table1.addCell(numCell);
					
					table1.addCell(twoCell);
					table1.addCell(addrCell);
					table1.addCell(colon);
					table1.addCell(addrsCell);
					
					table1.addCell(threeCell);
					table1.addCell(phCell);
					table1.addCell(colon);
					table1.addCell(phNumCell);
					
					table1.addCell(fourCell);
					table1.addCell(mailCell);
					table1.addCell(colon);
					table1.addCell(emailCell);
					
					table1.addCell(fiveCell);
					table1.addCell(starCell);
					table1.addCell(colon);
					table1.addCell(birthStarCell);
					
					table1.addCell(sixCell);
					table1.addCell(panCell);
					table1.addCell(colon);
					table1.addCell(panNumCell);
					
					DottedLineSeparator line = new DottedLineSeparator();
					line.setOffset(-2);
					line.setGap(2f);
					
					Paragraph cond1 = new Paragraph("( PLEASE SIGN & SEND THIS  LETTER TO  VRNT CHENNAI OFFICE BY POST )", nb);
					cond1.setAlignment(Element.ALIGN_CENTER);
					
					
					Paragraph cond2 = new Paragraph("( KINDLY TAKE XEROX COPIES OF THIS LETTER AND IN FUTURE, FILL AND SEND TO US FOR  DONATIONS ABOVE RS.2000 , SO THAT THE DONATION AMOUNT CAN BE TAKEN TO THE CORPUS OF THE TRUST)", n);
					cond2.setAlignment(Element.ALIGN_CENTER);
					
					
					Paragraph cond3 = new Paragraph("We request the donors to mention their Complete Address with Phone Number or E-Mail ID for communication regarding the donation receipt.", n);
					cond3.setAlignment(Element.ALIGN_CENTER);
					
					
					
					document.open();
					document.add(receipt);
					document.add(date);
					document.add(Chunk.NEWLINE);
					document.add(from);
					document.add(Chunk.NEWLINE);
					document.add(to);
					document.add(Chunk.NEWLINE);
					document.add(sub);
					document.add(Chunk.NEWLINE);
					document.add(content);
					document.add(enrollment);
					document.add(Chunk.NEWLINE);
					document.add(table1);
					
					document.add(Chunk.NEWLINE);
					document.add(signPara);
					document.add(datePara);
					document.add(Chunk.NEWLINE);
					document.add(line);
					document.add(cond1);
					document.add(Chunk.NEWLINE);
					document.add(cond2);
					document.add(Chunk.NEWLINE);
					document.add(cond3);
					
					//Desktop.getDesktop().open(corpusLetter);
					document.close();
					
					
					
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}				
				
			}
			
			
			
			donationTypeCombo.setSelectedIndex(0);
			retrive.setEnabled(true);
			num_p2.setEditable(true);
			num_p2.setText("");
			cand_initial_p2.setText("");
			cand_nam_p2.setText("");
			addr_12.setText("");
			addr_22.setText("");
			addr_32.setText("");
			area_2.setText("");
			city_town2.setText("");
			pin_code_2.setText("");
			cand_ph_p2.setText("");
			cand_email_p2.setText("");
			cand_pan_p2.setText("");
			don_type.setSelectedIndex(0);
			corpusCombo.setSelectedIndex(0);
			cand_amt_p2.setText("");
			payment_mode.setSelectedIndex(0);
			payment_num.setText("");
			issue_dat.setText("");
			bank_name.setText("");
			branch_nam.setText("");			
			bank_received.setSelectedIndex(0);
			try{
				Class.forName("org.h2.Driver");
	   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
				Statement stm1=conn1.createStatement();
				String st = "select max(receipt) from bill";
				
				ResultSet rs1 = stm1.executeQuery(st);
				rs1.next();
				int s1 = rs1.getInt(1);		
				if (s1 == 0){
					receipt_no.setEditable(true);
				} else {
					receipt_no.setText(String.valueOf(s1+1));
					receipt_no.setEditable(false);
				}
				
				
				conn1.close();
			}
			catch (ClassNotFoundException e1)
			{
				Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
			}
			catch(SQLException ee)
			{
				System.err.println(ee);
				
				Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
			}
			
//			s.deleteOnExit();
			doc.close();
			try {
				Desktop.getDesktop().open(s);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//print_interior();
		  }
		}
		
		if (e.getSource() == proceed1){
			TelegraphQueue que = new TelegraphQueue();
			if (tenantCombo.getSelectedIndex() == 0){
				Telegraph tele = new Telegraph("Select Tenant", "Tenant Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(cand_amt_p4.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Rent Amount", "Rent Amount can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(serviceTaxRate.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Service Tax", "Service Tax can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if((payment_mode_2.getSelectedIndex() != 0) && (payment_num_2.getText().length() == 0)) {
					Telegraph tele = new Telegraph("Enter Cheque/D.D No.", "Cheque/D.D number can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
													
			} else if((payment_mode_2.getSelectedIndex() != 0) && (issue_dat_2.getText().length() == 0)) {
				Telegraph tele = new Telegraph("Enter Cheque/Transfer Date", "Cheque/Transfer Date can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
												
			}else if((payment_mode_2.getSelectedIndex() != 0) && (bank_received_2.getSelectedIndex() == 0)) {
				Telegraph tele = new Telegraph("Select Bank Received", "Bank Received By VRNT can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
												
			} else {				
				
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/vrnt", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
				
		        try {
		        	String realizationDate = "";
		        	if(payment_mode_2.getSelectedItem().equals("CASH"))
		        		realizationDate = date.getText();
		        	else if(payment_mode_2.getSelectedItem().equals("CHQ"))
		        		realizationDate = "";
		        	else
		        		realizationDate = issue_dat_2.getText();
					Statement stm = conn.createStatement();
					String st = "insert into rent values("+receipt_no_2.getText()+", '"+date_2.getText()+"', '"+tenantName.getText()+"'"+","+"'"+addr_14.getText()+"'"+", '"+addr_24.getText()+"', '"+addr_34.getText()+"', '"+area_4.getText()+"', '"+city_town4.getText()+"', '"+pin_code_4.getText()+"', '"+cand_ph_p4.getText()+"', '"+monthCombo.getSelectedItem()+"', '"+yearCombo.getSelectedItem()+"', "+cand_amt_p4.getText()+","+serviceTaxRate.getText()+", "+totalAmount.getText()+", '"+payment_mode_2.getSelectedItem()+"', '"+payment_num_2.getText()+"', '"+issue_dat_2.getText()+"', '"+bank_name_2.getText()+"', '"+branch_nam_2.getText()+"', '"+realizationDate+"', '"+bank_received_2.getSelectedItem()+"', 'cleared'"+")";
					stm.executeUpdate(st);
					
					//currently this option is dropped storing the opening balance and then based on the donations in the bill table using the total amount donated
					/*if(num_p2.getText().length() != 0){
						String st1 = "select amount from details where no = '"+num_p2.getText()+"'";
						ResultSet rs2 = stm.executeQuery(st1);
						rs2.next();
						double amt = rs2.getDouble(1);
						
						
						amt += Integer.parseInt(cand_amt_p2.getText());
						String st2 = "update details set amount = "+amt+"where no = '"+num_p2.getText()+"'";
						stm.executeUpdate(st2);
					}*/
					
					Telegraph tele = new Telegraph("Success", "Rent Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
		        
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
			PdfWriter writer = null;
			
			File vrntRentBill = new File("Vrnt Rent Bill");
			if(!vrntRentBill.exists()){
				try{
					vrntRentBill.mkdir();
				} catch(Exception e1){
					System.err.println(e1);
				}
			}
						
			
			File s = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			NumberFormat numberFormatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			
			String receivedDate = dateFormat.format(Date.valueOf(date.getText()));
			
			String resultNameFile = tenantName.getText();
			
			
			try {
//				s = File.createTempFile("vrnt_bill", ".pdf");
				
				s = new File(vrntRentBill.getAbsolutePath()+"/RT. NO. "+receipt_no_2.getText()+", "+receivedDate+" - "+resultNameFile+" - Rs."+numberFormatter.format(Integer.parseInt(totalAmount.getText()))+".pdf");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A5);
			//doc.setMargins(36, 72, 108, 180);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD);			
			com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
			com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
			com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
			PdfPTable tab = new PdfPTable(3);
			PdfPCell a, b, c, d, f, g;
			//tab.setWidths(new int[] {80, 60, 60});
			tab.setWidthPercentage(100);
			
			NumberFormat formatter = new RuleBasedNumberFormat(Locale.ENGLISH, RuleBasedNumberFormat.SPELLOUT);
			
			String result = formatter.format(Integer.parseInt(totalAmount.getText()));
			

			Phrase vrnt = new Phrase(" ", fi);

			c = new PdfPCell(vrnt);

			c.setFixedHeight(40);

			Phrase rece = new Phrase("Receipt Number: "+receipt_no_2.getText(), si);
			
			
			Phrase donat = new Phrase("Rent", si);
			g = new PdfPCell(donat);
			g.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			
			Phrase dat = new Phrase("Date: "+receivedDate, si);
			
			Phrase donationFrom = new Phrase("RENT RECEIVED FROM:", n);
			
			String currency = "Rs. ";
			String currencyFull = "RUPEES ";
			
			Phrase rs = new Phrase("AMOUNT "+currency+numberFormatter.format(Integer.parseInt(totalAmount.getText())), si);
			
			
			Phrase rup = new Phrase(currencyFull+result.toUpperCase()+" ONLY", ni);
			String address = "";
			
			address += (addr_14.getText().length() != 0) ?  addr_14.getText() : "";
				
			address += (addr_24.getText().length() != 0) ? "\n"+addr_24.getText() : "";
			
			address += (addr_34.getText().length() != 0) ? "\n"+addr_34.getText() : "";
			
			address += (area_4.getText().length() != 0) ? "\n"+area_4.getText() : "";
			
			address += (city_town4.getText().length() != 0) ? "\n"+city_town4.getText() : "";
			
			address += (pin_code_4.getText().length() != 0) ? " "+pin_code_4.getText() : " ";
			
			address += (cand_ph_p4.getText().length() != 0) ? "\nPh No: "+cand_ph_p4.getText() : "";
			
			
			
			
			Phrase addr = new Phrase(address, si);
			
			String paymentDetails = ""; 
			paymentDetails += "Mode Of Receipt: "+payment_mode_2.getSelectedItem();
			switch(payment_mode_2.getSelectedItem().toString()){
				case "CHQ":
					paymentDetails += "\n" + "CHEQUE NO : " + payment_num_2.getText()  + "\n" +"Date: " + issue_dat_2.getText();
					paymentDetails += "\n" + "Bank Drawn: " + bank_name_2.getText();
					paymentDetails += "\n" + "Branch: " + branch_nam_2.getText();
					break;
				case "A/C TRANSFER":
					paymentDetails += "\n" + "TRNF NO : " + payment_num_2.getText()  + "\n" +"Date: " + issue_dat_2.getText();
					break;
			}
			
			Phrase mod = new Phrase(paymentDetails, si);

			
			Phrase don_for = new Phrase("THIS DONATION IS FOR CORPUS OF THE TRUST", ni);
			Phrase sign_don = new Phrase("SIGNATURE OF DONOR", si);
			Phrase sign_rec = new Phrase("Signature of Receiver", si);
			Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
			

			String name = tenantName.getText();

			Phrase nam = new Phrase(name, n);

			

			Phrase amt = new Phrase("Rent For "+monthCombo.getSelectedItem()+" "+yearCombo.getSelectedItem(), si);
			
			Phrase pay = new Phrase("Payment Details", si);
			
			
			Phrase emp = new Phrase("\0");
			
			
			Phrase ins1 = new Phrase(" ", ni);
			
			PdfPCell donationFromCell = new PdfPCell(donationFrom);
			
			PdfPCell nam_c = new PdfPCell(nam);
			nam_c.setFixedHeight(20);
			
			PdfPCell address_c = new PdfPCell(addr);
			address_c.setFixedHeight(80);
			
			PdfPCell dummy = new PdfPCell(emp);
			PdfPCell rece_c = new PdfPCell(rece);
			
			PdfPCell dat_c = new PdfPCell(dat);
			dat_c.setHorizontalAlignment(Element.ALIGN_RIGHT);

			
			PdfPCell amt_c = new PdfPCell(amt);
			PdfPCell rs_c = new PdfPCell(rs);
			
			PdfPCell rup_c = new PdfPCell(rup);
			PdfPCell pay_c = new PdfPCell(pay);
			PdfPCell mod_c = new PdfPCell(mod);
			mod_c.setFixedHeight(80);

			PdfPCell don_for_c = new PdfPCell(don_for);
			PdfPCell empty = new PdfPCell(emp);
			
			PdfPCell sign_don_c = new PdfPCell(sign_don);
			PdfPCell sign_rec_c = new PdfPCell(sign_rec);
			PdfPCell trust_c = new PdfPCell(trust);
			PdfPCell ins1_c = new PdfPCell(ins1);
			ins1_c.setFixedHeight(20);

			empty.setFixedHeight(6);

//			pay_c.setColspan(2);

//			rs_c.setColspan(2);
			rup_c.setColspan(3);
//			mod_c.setColspan(2);
			
			donationFromCell.setColspan(2);
			
			
			nam_c.setColspan(2);
			address_c.setColspan(2);

			empty.setColspan(3);
			don_for_c.setColspan(3);
			ins1_c.setColspan(3);


			c.setBorder(0);
			g.setBorder(0);
			empty.setBorder(0);
			dummy.setBorder(0);
			donationFromCell.setBorder(0);
			nam_c.setBorder(0);
			rece_c.setBorder(0);
			address_c.setBorder(0);

			dat_c.setBorder(0);

			

			amt_c.setBorder(0);
			rs_c.setBorder(0);
			rup_c.setBorder(0);
			pay_c.setBorder(0);
			mod_c.setBorder(0);

			don_for_c.setBorder(0);
			sign_don_c.setBorder(0);
			sign_rec_c.setBorder(0);
			trust_c.setBorder(0);
			ins1_c.setBorder(0);

//			PdfContentByte cb = writer.getDirectContent();
//			cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
//			cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
			
//			cb.closePath();
//			cb.stroke();
//			com.itextpdf.text.Image donor_img = null;
//			com.itextpdf.text.Image off_img = null;
			com.itextpdf.text.Image  sign = null;
			PdfPCell signature = null;
			
			try {
//				donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
//				off_img = Image.getInstance(this.getClass().getResource("off.png"));
				sign = Image.getInstance(this.getClass().getResource("ET-SIGN.png"));
				signature = new PdfPCell(sign);
//				donor_img.scaleAbsolute(150, 150);

				sign.scaleAbsolute(80, 15);
//				off_img.scaleAbsolute(150, 150);
//				donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
//				off_img.setAbsolutePosition(120, 90);
				
			} catch (BadElementException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (MalformedURLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			signature.setBorder(0);
			signature.setColspan(3);
			signature.setHorizontalAlignment(Element.ALIGN_RIGHT);
			signature.setPaddingRight(30);
			for (int i = 0; i < 2; i++){

			tab.addCell(c);

			
			tab.addCell(empty);
			
			
			tab.addCell(rece_c);
			tab.addCell(g);
			tab.addCell(dat_c);
			tab.addCell(empty);
			
			tab.addCell(donationFromCell);
			tab.addCell(dummy);
			
			tab.addCell(nam_c);
			tab.addCell(rs_c);
			
			tab.addCell(address_c);
			tab.addCell(mod_c);
			
			tab.addCell(rup_c);
			tab.addCell(don_for_c);

			//tab.addCell(empty);
			
			
			
			tab.addCell(signature);
			tab.addCell(sign_don_c);
			tab.addCell(sign_rec_c);
			tab.addCell(trust_c);
			//tab.addCell(empty);
			tab.addCell(ins1_c);

			
			if (i == 0){
				tab.addCell(empty);
				tab.addCell(empty);
			}
			}

			try {
				doc.add(tab);
				
//				doc.add(donor_img);
//				doc.add(off_img);
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			tenantCombo.setSelectedIndex(0);
			monthCombo.setSelectedIndex(0);
			yearCombo.setSelectedIndex(0);			
			cand_amt_p4.setText("");
			serviceTaxRate.setText("");
			serviceTaxAmount.setText("");
			totalAmount.setText("");			
			payment_mode_2.setSelectedIndex(0);
			payment_num_2.setText("");
			issue_dat_2.setText("");
			bank_name_2.setText("");
			branch_nam_2.setText("");
			bank_received_2.setSelectedIndex(0);
			
			try{
				Class.forName("org.h2.Driver");
	   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
				Statement stm1=conn1.createStatement();
				String st = "select max(receipt) from rent";
				
				ResultSet rs1 = stm1.executeQuery(st);
				rs1.next();
				int s1 = rs1.getInt(1);		
				if (s1 == 0){
					receipt_no_2.setEditable(true);
				} else {
					receipt_no_2.setText(String.valueOf(s1+1));
					receipt_no_2.setEditable(false);
				}
				
				
				conn1.close();
			}
			catch (ClassNotFoundException e1)
			{
				Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
			}
			catch(SQLException ee)
			{
				System.err.println(ee);
				
				Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
			}
			
//			s.deleteOnExit();
			doc.close();
			try {
				Desktop.getDesktop().open(s);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//print_interior();
		  }
		} 

		
		if (e.getSource() == cancel){
			String rece = JOptionPane.showInputDialog(null, "Enter Receipt No.");
			if (rece != null){
				try{
					Class.forName("org.h2.Driver");
		   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
					Statement stm1=conn1.createStatement();
					String st = "select no,amt,status from bill where receipt = "+Integer.parseInt(rece);
					
					ResultSet rs1 = stm1.executeQuery(st);
					rs1.next();
					String s1 = rs1.getString(1);		
					double s2 = rs1.getDouble(2);
					String s3 = rs1.getString(3);
					if (s3.equals("cleared")){
					
						/*if (s1.length() != 0){
							String st1 = "select amount from details where no = '"+s1+"'";
							ResultSet rs2 = stm1.executeQuery(st1);
							rs2.next();
							double d1 = rs2.getDouble(1);
							double tot = d1 - s2;
							String st2 = "update details set amount = "+tot+" where no = '"+s1+"'";
							stm1.executeUpdate(st2);
						} */
					
						String st3 = "update bill set status = 'cancelled' where receipt = "+Integer.parseInt(rece);
						stm1.executeUpdate(st3);
					} else{
						Telegraph tele = new Telegraph("Warning!", "Receipt is already cancelled...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}
					Telegraph tele = new Telegraph("Success", "Receipt cancelled successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					conn1.close();
				}
				catch (ClassNotFoundException e1)
				{
					System.err.println(e1);
					
					Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
				}
				catch(SQLException ee)
				{
					System.err.println(ee);
					if (ee.getErrorCode() == 2000){
						Telegraph tele = new Telegraph("Warning!", "Receipt not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}else {
						Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}
					//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
			
			if (e.getSource() == statement){
				String strtdate = JOptionPane.showInputDialog(null, "Enter starting date", date.getText());
				if(strtdate != null){
					String enddate = JOptionPane.showInputDialog(null, "Enter ending date", date.getText());
				if(enddate != null){
					
				NumberFormat numberFormatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
					
				PdfWriter writer = null;
				
				//Paragraph para = new Paragraph();
				//para.setAlignment(Element.RECTANGLE);
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/vrnt", "sa", "");
			        Statement stmt = conn.createStatement();
			        String st = "select receipt,dat,no,initial,name,amt,pay_mode,chqno from bill where dat between '"+strtdate+"' and '"+enddate+"' and status = 'cleared'";
			        ResultSet rs = stmt.executeQuery(st);
			       if(rs.isBeforeFirst()){
			    	   int ret = gen_pdf.showSaveDialog(this);
						File s = null;
						if (ret == JFileChooser.APPROVE_OPTION){
							s = gen_pdf.getSelectedFile();
							String stm = s.toString();
							if(!stm.endsWith(".pdf")){
								s = new File(stm+".pdf");
							}
						
						Document doc = new Document();
						try {
							writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						doc.setPageSize(PageSize.A4);
						//doc.setMargins(36, 72, 108, 180);
						doc.setMarginMirroring(true);
						doc.open();
						com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
						PdfPTable table = new PdfPTable(6);
						PdfPTable tab = new PdfPTable(2);
						table.setComplete(true);
						table.setWidthPercentage(100);
						table.addCell(new Phrase("Receipt No"));
						table.addCell(new Phrase("Date"));
						table.addCell(new Phrase("N.S No"));
						table.addCell(new Phrase("Name"));
						table.addCell(new Phrase("CHQ/TRF No"));
						table.addCell(new Phrase("Amount"));
						table.setHeaderRows(6);
						double cashtot=0, chqtot=0, ddtot=0, total, amt;
			        while(rs.next()){
			        	 table.addCell(new Phrase(rs.getString("RECEIPT"), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getDate("DAT")), n));
			        	 table.addCell(new Phrase(rs.getString("NO"), n));
			        	 table.addCell(new Phrase(rs.getString("NAME") + " " + rs.getString("INITIAL"), n));			        	 			        	 
			        	 table.addCell(new Phrase(rs.getString("CHQNO"), n));
			        	 amt = rs.getDouble("AMT");
			        	 table.addCell(new Phrase("Rs. "+numberFormatter.format(amt), n));
			        	 String sss = rs.getString(7);
			        	 if (sss.equals("CASH")){
			        		 cashtot += amt;
			        	 } else if(sss.equals("CHQ")){
			        		 chqtot += amt;
			        	 } else if(sss.equals("A/C TRANSFER")){
			        		 ddtot += amt;
			        	 }
			        	
			        	
			        }
			        total = cashtot+chqtot+ddtot;
			        try {
			        	
			        	doc.add(new Phrase("Statement From "+strtdate+" To "+enddate));
						doc.add(table);
						PdfPCell e1 = new PdfPCell(new Phrase("\0"));
						e1.setColspan(2);
						e1.setBorder(0);
						tab.addCell(e1);
						PdfPCell p2 = new PdfPCell(new Phrase("Total Amount By Cash "));
						p2.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p2.setBorder(0);
						tab.addCell(p2);
						PdfPCell p3 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(cashtot)));
						p3.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p3.setBorder(0);
						tab.addCell(p3);
						PdfPCell p5 = new PdfPCell(new Phrase("Total Amount By Cheque "));
						p5.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p5.setBorder(0);
						tab.addCell(p5);
						PdfPCell p4 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(chqtot)));
						p4.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p4.setBorder(0);
						tab.addCell(p4);
						PdfPCell p6 = new PdfPCell(new Phrase("Total Amount By A/C Transfer "));
						p6.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p6.setBorder(0);
						tab.addCell(p6);
						PdfPCell p7 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(ddtot)));
						p7.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p7.setBorder(0);
						tab.addCell(p7);
						PdfPCell p8 = new PdfPCell(new Phrase("Total "));
						p8.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p8.setBorder(0);
						tab.addCell(p8);
						PdfPCell p9 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(total)));
						p9.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p9.setBorder(0);
						tab.addCell(p9);
						tab.addCell(e1);
						Phrase p = new Phrase("*** End Of Statement ***");
						PdfPCell p1 = new PdfPCell(p);
						p1.setHorizontalAlignment(Element.ALIGN_CENTER);
						p1.setColspan(2);
						p1.setBorder(0);
						tab.addCell(p1);
						doc.add(tab);
						
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					doc.close();
					//System.out.println("sucess");
					Telegraph tele = new Telegraph("Success", "Statement generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
						}} else{
			        	Telegraph tele = new Telegraph("Invalid Date Range", "The Date Range you entered is not valid...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
			        }
			       }
				catch(Exception e4){
					System.err.println(e4);
				}
				
			  
			 }
			}
		  }
		
			
			if (e.getSource() == stmt_full){
				String startdate = JOptionPane.showInputDialog(null, "Enter starting date", date.getText());
				if(startdate != null){
					String endate = JOptionPane.showInputDialog(null, "Enter ending date", date.getText());
				if(endate != null){
				
				NumberFormat numberFormatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
				
				//Paragraph para = new Paragraph();
				//para.setAlignment(Element.RECTANGLE);
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/vrnt", "sa", "");
			        Statement stmt = conn.createStatement();
			        String st = "select * from bill where dat between '"+startdate+"' and '"+endate+"' and status = 'cleared'";
			        ResultSet rs = stmt.executeQuery(st);
			        String aa,ab,a1,a2,a3,a4,a5,a6,a7;
			        
			      // System.out.println(rs.isBeforeFirst());
			        if (rs.isBeforeFirst()){
			        	PdfWriter writer = null;
						int ret = gen_pdf.showSaveDialog(this);
						File s = null;
						if (ret == JFileChooser.APPROVE_OPTION){
							s = gen_pdf.getSelectedFile();
							String stm = s.toString();
							if(!stm.endsWith(".pdf")){
								s = new File(stm+".pdf");
							}
						
						Document doc = new Document();
						try {
							writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						doc.setPageSize(PageSize.A2);
						//doc.setMargins(36, 72, 108, 180);
						doc.setMarginMirroring(true);
						doc.open();
						com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
						PdfPTable table = new PdfPTable(9);
						PdfPTable tab = new PdfPTable(2);
						table.setComplete(true);
						table.setWidthPercentage(100);
						table.addCell(new Phrase("Receipt No"));
						table.addCell(new Phrase("Date"));
						table.addCell(new Phrase("N.S No"));
						table.addCell(new Phrase("Name"));
						table.addCell(new Phrase("Address"));
					//	table.addCell(new Phrase("Address Line 2"));
					//	table.addCell(new Phrase("City"));
						table.addCell(new Phrase("Donation Type"));				
						table.addCell(new Phrase("Amount"));
						table.addCell(new Phrase("Payment Mode"));
						table.addCell(new Phrase("Payment Details"));
					//	table.addCell(new Phrase("Date Of Issue"));
				//		table.addCell(new Phrase("Bank"));
				//		table.addCell(new Phrase("Branch"));
						
						//table.setHeaderRows(9);
						double cashtot=0, chqtot=0, ddtot=0, total, amt;
			        while(rs.next()){
			        	
			        	 table.addCell(new Phrase(rs.getString("RECEIPT"), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getDate("DAT")), n));
			        	 table.addCell(new Phrase(rs.getString("NO"), n));
			        	 table.addCell(new Phrase(rs.getString("NAME") + " " + rs.getString("INITIAL"), n));
			        	// table.addCell(new Phrase(rs.getString(5), n));
			        	// table.addCell(new Phrase(rs.getString(6), n));
			        	// table.addCell(new Phrase(rs.getString(7), n));
			        	 table.addCell(new Phrase(rs.getString("ADDR_1")+"\n"+rs.getString("ADDR_2")+"\n"+rs.getString("ADDR_3")+"\n"+rs.getString("AREA")+"\n"+rs.getString("CITY")+" - "+rs.getString("PINCODE"), n));
			        				        	
			        	
			        	 table.addCell(new Phrase(rs.getString("TYPE_DONATN"), n));
			        	// System.out.println(rs.getString(1)+aa);
			        	 amt = rs.getDouble("AMT");
			        	 table.addCell(new Phrase("Rs. "+numberFormatter.format(amt), n));
			        	 String sss = rs.getString("PAY_MODE");
			        	 table.addCell(new Phrase(sss, n));
			        	// table.addCell(new Phrase(String.valueOf(rs.getInt(11)), n));
			        	// table.addCell(new Phrase(rs.getString(12), n));
			        	// table.addCell(new Phrase(rs.getString(13), n));
			        	// table.addCell(new Phrase(rs.getString(14), n));
			        	table.addCell(new Phrase(String.valueOf(rs.getString("CHQ_NO"))+"\n"+rs.getString("ISSUE_DATE")+"\n"+rs.getString("BANK")+"\n"+rs.getString("BRANCH"), n));
			        	
			        	 if (sss.equals("CASH")){
			        		 cashtot += amt;
			        	 } else if(sss.equals("CHQ")){
			        		 chqtot += amt;
			        	 } else if(sss.equals("A/C TRANSFER")){
			        		 ddtot += amt;
			        	 }
			        	 
			        }
			        total = cashtot+chqtot+ddtot;
			        try {
			        	 doc.add(new Phrase("Statement From "+startdate+" To "+endate));
		        		 
			        	 doc.add(table);
		        		 PdfPCell e1 = new PdfPCell(new Phrase("\0"));
							e1.setColspan(2);
							e1.setBorder(0);
							tab.addCell(e1);
							PdfPCell p2 = new PdfPCell(new Phrase("Total Amount By Cash "));
							p2.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p2.setBorder(0);
							tab.addCell(p2);
							PdfPCell p3 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(cashtot)));
							p3.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p3.setBorder(0);
							tab.addCell(p3);
							PdfPCell p5 = new PdfPCell(new Phrase("Total Amount By Cheque "));
							p5.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p5.setBorder(0);
							tab.addCell(p5);
							PdfPCell p4 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(chqtot)));
							p4.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p4.setBorder(0);
							tab.addCell(p4);
							PdfPCell p6 = new PdfPCell(new Phrase("Total Amount By A/C Transfer "));
							p6.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p6.setBorder(0);
							tab.addCell(p6);
							PdfPCell p7 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(ddtot)));
							p7.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p7.setBorder(0);
							tab.addCell(p7);
							PdfPCell p8 = new PdfPCell(new Phrase("Total "));
							p8.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p8.setBorder(0);
							tab.addCell(p8);
							PdfPCell p9 = new PdfPCell(new Phrase("Rs. "+numberFormatter.format(total)));
							p9.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p9.setBorder(0);
							tab.addCell(p9);
							tab.addCell(e1);
							Phrase p = new Phrase("*** End Of Statement ***");
							PdfPCell p1 = new PdfPCell(p);
							p1.setHorizontalAlignment(Element.ALIGN_CENTER);
							p1.setColspan(2);
							p1.setBorder(0);
							tab.addCell(p1);
							doc.add(tab);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					doc.close();
					Telegraph tele = new Telegraph("Success", "Complete Statement generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
			        }} else{
			        	Telegraph tele = new Telegraph("Invalid Date Range", "The Date Range you entered is not valid...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
			        }
				}
				catch(Exception e4){
					System.err.println(e4);
				}
				
				//System.out.println("sucess");
				
				
			 
			}
		  }
		}
			
			if (e.getSource() == recep){
				String r = JOptionPane.showInputDialog(null, "Enter Receipt No.");
				if (r != null){
					try {
						Class.forName("org.h2.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        Connection conn = null;
					try {
						conn = DriverManager.
						    getConnection("jdbc:h2:~/vrnt", "sa", "");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        // add application code here
					String re = null, dates = null, nums= null, name = null, addrs1 = null, addrs2 = null, addrs3 = null, citys = null, types = null, mods = null, dats = null, ban = null, bran = null, stat = null;
					int chqs = 0;
					double amts = 0;
			        try {
						Statement stm = conn.createStatement();
						String st = "select * from bill where receipt = "+Integer.parseInt(r);
						ResultSet rs = stm.executeQuery(st);
						rs.next();
						re = rs.getString(1);
						dates = String.valueOf(rs.getDate(2));
						nums = rs.getString(3);
						name = rs.getString(5) + " " +rs.getString(4);
						addrs1 = rs.getString(6);
						addrs2 = rs.getString(7);
						addrs3 = rs.getString(8);
						citys = rs.getString(9);
						types = rs.getString(10);
						amts = rs.getDouble(11);
						mods = rs.getString(12);
						chqs = rs.getInt(13);
						dats = rs.getString(14);
						ban = rs.getString(15);
						bran = rs.getString(16);
						stat = rs.getString(17);
						
						//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						if(e2.getErrorCode() == 2000){
							Telegraph tele = new Telegraph("Receipt Not Found", "The Receipt Number you entered is not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
							TelegraphQueue que = new TelegraphQueue();
							que.add(tele);
						}
						
					}
			        if (stat.equals("cleared")){
			        
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
					
				PdfWriter writer = null;
				
				File s = null;
				try {
					s = File.createTempFile("vrnt_bill", ".pdf");
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Document doc = new Document();
				try {
					writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doc.setPageSize(PageSize.A4);
				//doc.setMargins(36, 72, 108, 180);
				doc.setMarginMirroring(true);
				doc.open();
				com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
				com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
				com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
				com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
				com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
				PdfPTable tab = new PdfPTable(3);
				PdfPCell a, b, c, d, f, g;
				//tab.setWidths(new int[] {80, 60, 60});
				tab.setWidthPercentage(100);
				
				NumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
				
				String result = formatter.format(amts);
				Phrase h = new Phrase("SRI GURUBHYO NAMAHA", ni);
				
				Phrase pan = new Phrase("PAN No.: AAATV3147P", ni);
				Phrase vrnt = new Phrase("VEDA RAKSHNA NIDHI TRUST (Regd.)", fi);
				Phrase vrnt_add = new Phrase("No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", ni);
				Phrase mail = new Phrase("e-mail: vrnt@vsnl.net \t Tel: 044-24740549", si);
				Phrase donat = new Phrase(types+" Donation", si);
				//Phrase ph = new Phrase("Tel: 044-24740549");
				a = new PdfPCell(h);
				b = new PdfPCell(pan);
				c = new PdfPCell(vrnt);
				d = new PdfPCell(vrnt_add);
				f = new PdfPCell(mail);
				g = new PdfPCell(donat);
				//g = new PdfPCell(ph);
				a.setColspan(2);
				//b.setColspan(3);
				c.setColspan(3);
				d.setColspan(3);
				f.setColspan(3);
				g.setColspan(3);
				a.setHorizontalAlignment(Element.ALIGN_RIGHT);
				b.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				d.setHorizontalAlignment(Element.ALIGN_CENTER);
				f.setHorizontalAlignment(Element.ALIGN_CENTER);
				g.setHorizontalAlignment(Element.ALIGN_CENTER);
				//g.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				Phrase t = new Phrase("To", si);
				PdfPCell to = new PdfPCell(t);
				to.setColspan(3);
				
				Phrase nam = new Phrase("     "+name, si);
				Phrase addr1 = new Phrase("       "+addrs1, ni);
				Phrase addr2 = null;
				Phrase city = null;
				if (addrs2.length() != 0){
				addr2 = new Phrase("       "+addrs2, ni);
				city = new Phrase("       "+citys, ni);
				} else{
					addr2 =  new Phrase("       "+citys, ni);
					city = new Phrase("\0", ni);
				}
				
				Phrase rece = new Phrase("Receipt No: "+re, si);
				Phrase dat = new Phrase("Date: "+dates, si);
				Phrase nsno = new Phrase("N.S No: "+nums, si);
				Phrase amt = new Phrase("Donation towards "+types+" fund", si);
				Phrase rs = new Phrase("Rs. "+amts, si);
				Phrase rup = new Phrase("Rupees "+result+" only", si);
				Phrase pay = new Phrase("Payment Details", si);
				Phrase mod = new Phrase("Mode Of Payment: "+mods, si);
				Phrase chq = new Phrase("Cheque / DD No: "+chqs, si);
				Phrase dated = new Phrase("Dated: "+dats, si);
				Phrase bank = new Phrase("Bank: "+ban, si);
				Phrase branch = new Phrase("Branch: "+bran, si);
				Phrase emp = new Phrase("\0");
				Phrase sign_don = new Phrase("Signature of Donor", si);
				Phrase sign_rec = new Phrase("Signature of Receiver", si);
				Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
				Phrase ins1 = new Phrase("1. Donations to the trust are exempt from income Tax Section 80-G of income Tax Act.", ni);
				Phrase ins2 = new Phrase("2. Please quote your N.S No. given above in future correspondence with us.", ni);
				PdfPCell nam_c = new PdfPCell(nam);
				PdfPCell addr1_c = new PdfPCell(addr1);
				PdfPCell addr2_c = new PdfPCell(addr2);
				PdfPCell city_c = new PdfPCell(city);
				PdfPCell rece_c = new PdfPCell(rece);
				PdfPCell dat_c = new PdfPCell(dat);
				PdfPCell nsno_c = new PdfPCell(nsno);
				PdfPCell amt_c = new PdfPCell(amt);
				PdfPCell rs_c = new PdfPCell(rs);
				PdfPCell rup_c = new PdfPCell(rup);
				PdfPCell pay_c = new PdfPCell(pay);
				PdfPCell mod_c = new PdfPCell(mod);
				PdfPCell chq_c = new PdfPCell(chq);
				PdfPCell dated_c = new PdfPCell(dated);
				PdfPCell bank_c = new PdfPCell(bank);
				PdfPCell branch_c = new PdfPCell(branch);
				PdfPCell empty = new PdfPCell(emp);
				PdfPCell sign_don_c = new PdfPCell(sign_don);
				PdfPCell sign_rec_c = new PdfPCell(sign_rec);
				PdfPCell trust_c = new PdfPCell(trust);
				PdfPCell ins1_c = new PdfPCell(ins1);
				PdfPCell ins2_c = new PdfPCell(ins2);
				empty.setFixedHeight(6);
				nam_c.setColspan(2);
				addr1_c.setColspan(2);
				addr2_c.setColspan(2);
				city_c.setColspan(3);
				amt_c.setColspan(2);
				rup_c.setColspan(3);
				pay_c.setColspan(3);
				chq_c.setColspan(2);
				bank_c.setColspan(2);
				branch_c.setColspan(3);
				empty.setColspan(3);
				ins1_c.setColspan(3);
				ins2_c.setColspan(3);
				a.setBorder(0);
				b.setBorder(0);
				c.setBorder(0);
				d.setBorder(0);
				f.setBorder(0);
				g.setBorder(0);
				empty.setBorder(0);
				to.setBorder(0);
				nam_c.setBorder(0);
				rece_c.setBorder(0);
				addr1_c.setBorder(0);
				dat_c.setBorder(0);
				addr2_c.setBorder(0);
				nsno_c.setBorder(0);
				city_c.setBorder(0);
				amt_c.setBorder(0);
				rs_c.setBorder(0);
				rup_c.setBorder(0);
				pay_c.setBorder(0);
				mod_c.setBorder(0);
				chq_c.setBorder(0);
				dated_c.setBorder(0);
				bank_c.setBorder(0);
				branch_c.setBorder(0);
				sign_don_c.setBorder(0);
				sign_rec_c.setBorder(0);
				trust_c.setBorder(0);
				ins1_c.setBorder(0);
				ins2_c.setBorder(0);
				PdfContentByte cb = writer.getDirectContent();
				cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
				cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
				
				//cb.closePath();
				cb.stroke();
				com.itextpdf.text.Image donor_img = null;
				com.itextpdf.text.Image off_img = null;
				try {
					donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
					off_img = Image.getInstance(this.getClass().getResource("off.png"));
					donor_img.scaleAbsolute(150, 150);
					
					off_img.scaleAbsolute(150, 150);
					donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
					off_img.setAbsolutePosition(120, 90);
					
				} catch (BadElementException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (MalformedURLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} 
				for (int i = 0; i < 2; i++){
				tab.addCell(a);
				tab.addCell(b);
				tab.addCell(c);
				tab.addCell(d);
				tab.addCell(f);
				tab.addCell(g);
				tab.addCell(empty);
				tab.addCell(to);			
				tab.addCell(nam_c);
				tab.addCell(rece_c);
				tab.addCell(addr1_c);
				tab.addCell(dat_c);
				tab.addCell(addr2_c);
				tab.addCell(nsno_c);
				tab.addCell(city_c);
				tab.addCell(empty);
				tab.addCell(amt_c);
				tab.addCell(rs_c);
				tab.addCell(rup_c);
				tab.addCell(empty);
				tab.addCell(pay_c);
				tab.addCell(mod_c);
				tab.addCell(chq_c);
				tab.addCell(dated_c);
				tab.addCell(bank_c);
				tab.addCell(branch_c);
				tab.addCell(empty);
				tab.addCell(sign_don_c);
				tab.addCell(sign_rec_c);
				tab.addCell(trust_c);
				tab.addCell(empty);
				tab.addCell(ins1_c);
				tab.addCell(ins2_c);
				if (i == 0){
					tab.addCell(empty);
					//tab.addCell(empty);
				}
				}
				//tab.addCell(g);
				try {
					doc.add(tab);
					doc.add(donor_img);
					doc.add(off_img);
				} catch (DocumentException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				Telegraph tele = new Telegraph("Success", "Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				
				try {
					Desktop.getDesktop().open(s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s.deleteOnExit();
				doc.close(); 
			        } else {
			        	Telegraph tele = new Telegraph("Receipt Cancelled", "The Receipt is cancelled", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
			        }
				}
			}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		int col = edit_table.columnAtPoint(e.getPoint());
		
		if((tab_pane.getSelectedIndex() == 1) && (col == 9)){
			int row = edit_table.rowAtPoint(e.getPoint());			
			String nsNum = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 0).toString();
			String select = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 9).toString();
			
			if(select.equals("S")){
				
				edit_table.getModel().setValueAt("DS", edit_table.convertRowIndexToModel(row), 9);
				select = "DS";
			}
			else{
				edit_table.getModel().setValueAt("S", edit_table.convertRowIndexToModel(row), 9);
				select = "S";
			}
			
			annualReportStatus.put(nsNum, select);
		}else if((tab_pane.getSelectedIndex() == 1) && (col == 10)){
			int row = edit_table.rowAtPoint(e.getPoint());			
			String nsNum = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 0).toString();
			String select = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 10).toString();
			
			if(select.equals("S")){
				
				edit_table.getModel().setValueAt("DS", edit_table.convertRowIndexToModel(row), 10);
				select = "DS";
			}
			else{
				edit_table.getModel().setValueAt("S", edit_table.convertRowIndexToModel(row), 10);
				select = "S";
			}
			
			prasadamStatus.put(nsNum, select);
		}
		
		if(e.getClickCount() == 2){
			
			if((tab_pane.getSelectedIndex() == 1) && (col == 0 || col == 1 || col == 2)){
				int row = edit_table.rowAtPoint(e.getPoint());			
				String nsNum = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 0).toString();
				new UserDetails().ViewDetails(nsNum);
			}
			else if(tab_pane.getSelectedIndex() == 4 ){
				
			int donationCol = donationRegisterTable.columnAtPoint(e.getPoint());
			int row = donationRegisterTable.rowAtPoint(e.getPoint());
			
			String donorType = donationRegisterTable.getModel().getValueAt(donationRegisterTable.convertRowIndexToModel(row), 2).toString();
			String reliDat = donationRegisterTable.getModel().getValueAt(donationRegisterTable.convertRowIndexToModel(row), 13).toString();
			
			if(donationCol == 2 && !donorType.contains("NS NO.")){				
				String nsNum = JOptionPane.showInputDialog(null, "Enter Ns Number.");
				
				if(nsNum != null){
					String rtNum = donationRegisterTable.getModel().getValueAt(donationRegisterTable.convertRowIndexToModel(row), 1).toString();
					
										
					try{
						Class.forName("org.h2.Driver");
						Connection conn = DriverManager.
					            getConnection("jdbc:h2:~/vrnt", "sa", "");
					    Statement stm = conn.createStatement();
						
						String st = "select * from details where no = "+"'"+nsNum+"'";
				        ResultSet rs = stm.executeQuery(st);
				        
				        
				        
				        if(rs.first()){	        	
				        	
				        	String initial = rs.getString(2);
				        	String name = rs.getString(3);
				        	String addr_1 = rs.getString(4);
				        	String addr_2 = rs.getString(5);
				        	String addr_3 = rs.getString(6);
				        	String area = rs.getString(7);
				        	String city = rs.getString(8);
				        	String pin = rs.getString(9);
				        	String ph = rs.getString(10);
				        	String email = rs.getString(11);
				        	String pan = rs.getString(12);
				        	
				        	try{
								Class.forName("org.h2.Driver");
						        Connection conn1 = DriverManager.
						            getConnection("jdbc:h2:~/vrnt", "sa", "");
						        Statement stm1 = conn1.createStatement();
						        String st1 = "update bill set donor_type = 'NS NO.', no = '"+nsNum+"', initial = '"+initial+"', name = '"+name+"', addr_1 = '"+addr_1+"', addr_2 = '"+addr_2+"', addr_3 = '"+addr_3+"', area = '"+area+"', city = '"+city+"', pincode = '"+pin+"', phone_num = '"+ph+"', email = '"+email+"', pan_no = '"+pan+"' where receipt = '"+rtNum+"'";
						        stm1.executeUpdate(st1);
						        Telegraph tele = new Telegraph("Success", "Donor Type Changed to Ns No. successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
								TelegraphQueue quee = new TelegraphQueue();
								quee.add(tele);				        
								
								donationRegisterTableModel.setRowCount(0);
								DonationRegisterTableData();
						        
							}
							catch(Exception e2){
								System.err.println(e2);
							}
				        		
				        	} 
				        	else{
				        		//System.out.println("not found");
				        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
								TelegraphQueue quee = new TelegraphQueue();
								quee.add(tele);
				        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
				        	}
				       // }
				        // add application code here
				        conn.close();
					} catch(Exception e6){
						System.err.println(e6);
						
					}
					
					
				}
			}			
			else if(donationCol == 13 && reliDat.length() == 0){
				String dat = standardFormat.format(Calendar.getInstance().getTime());
				String realizationDate = JOptionPane.showInputDialog(null, "Enter Relization Date", dat);
				 
				
				if(realizationDate != null){
					String rtNum = donationRegisterTable.getModel().getValueAt(donationRegisterTable.convertRowIndexToModel(row), 1).toString();
					
					try{
						Class.forName("org.h2.Driver");
				        Connection conn = DriverManager.
				            getConnection("jdbc:h2:~/vrnt", "sa", "");
				        Statement stm = conn.createStatement();
				        String st = "update bill set relization_date = '"+realizationDate+"' where receipt = '"+rtNum+"'";
				        stm.executeUpdate(st);
				        Telegraph tele = new Telegraph("Success", "Relization Date Updated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);				        
						
						donationRegisterTableModel.setRowCount(0);
						DonationRegisterTableData();
				        
					}
					catch(Exception e2){
						System.err.println(e2);
					}
				}
				
				
			}
		  } else if(tab_pane.getSelectedIndex() == 6 ){
			  int rentCol = rentRegisterTable.columnAtPoint(e.getPoint());
			  int row = rentRegisterTable.rowAtPoint(e.getPoint());
				
				
				String reliDat = rentRegisterTable.getModel().getValueAt(rentRegisterTable.convertRowIndexToModel(row), 13).toString();
				if(rentCol == 13 && reliDat.length() == 0){
					String dat = standardFormat.format(Calendar.getInstance().getTime());
					String realizationDate = JOptionPane.showInputDialog(null, "Enter Relization Date", dat);
					 
					
					if(realizationDate != null){
						String rtNum = rentRegisterTable.getModel().getValueAt(rentRegisterTable.convertRowIndexToModel(row), 1).toString();
						
						try{
							Class.forName("org.h2.Driver");
					        Connection conn = DriverManager.
					            getConnection("jdbc:h2:~/vrnt", "sa", "");
					        Statement stm = conn.createStatement();
					        String st = "update rent set relization_date = '"+realizationDate+"' where receipt = '"+rtNum+"'";
					        stm.executeUpdate(st);
					        Telegraph tele = new Telegraph("Success", "Relization Date Updated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
							TelegraphQueue quee = new TelegraphQueue();
							quee.add(tele);				        
							
							rentRegisterTableModel.setRowCount(0);
							rentRegisterTableData();
					        
						}
						catch(Exception e2){
							System.err.println(e2);
						}
					}
					
					
				}
				
		  }
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
