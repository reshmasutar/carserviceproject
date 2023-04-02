package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet{
	
	static double kmeter;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String meter = req.getParameter("kmeter");
		String cab1 = req.getParameter("cab");
		
		//parsing
		kmeter = Double.parseDouble(meter);
		
		Service s1 = new Service();
		s1.cab1(cab1);
		RequestDispatcher rd = req.getRequestDispatcher("view.jsp");
		rd.forward(req, resp);
		

		Connection con = null;		
		
		
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7", "root", "sql@123");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

			PreparedStatement pstmt = null;
			String query="insert into cab(CAB_TYPE,DISTANCE,FAIR_AMT) values(?,?,?)";
			
			try {
			pstmt = con.prepareStatement(query);		
			pstmt.setString(1, cab1);
			pstmt.setDouble(2, kmeter);
			pstmt.setDouble(3, s1.amt);
			int count = pstmt.executeUpdate();
			System.out.println("RECORED INSERTED");
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		Statement stmt = null;
		ResultSet rs = null;
		
		query = "select * from cab";
				
		try {
			stmt = con.createStatement();
		
		rs = stmt.executeQuery(query);
		System.out.println("SRN\t\tCAB_TYPE\t\tDISTANCE\t\tFAIR_AMT");
		System.out.println("===========================================================================================================================");
		while(rs.next()){
			int srn=rs.getInt(1);
			String cab = rs.getString(2);
			String dist = rs.getString(3);
			int famt = rs.getInt(4);
			
			System.out.println(srn+"\t\t"+cab+"\t\t"+dist+"\t\t"+famt);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
}
