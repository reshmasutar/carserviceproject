package test;

import java.sql.*;

public class Service extends Controller {
	
	String mini1="mini";
	String prime1="prime";
	String micro1="micro";
	double amt;
	
	//for mini
	
	public double cab1(String cab){
		if(cab.equals(mini1)){
			amt = Controller.kmeter*10+0.05;		 
		}else if(cab.equals(prime1)){
			amt = Controller.kmeter*10+0.010;	
		}else if(cab.equals(micro1)){
			amt = Controller.kmeter*10+0.08;	
		}
		
	
	Connection con = null;		
	
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7", "root", "sql@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		PreparedStatement pstmt = null;
		String query="insert into cab(CAB_TYPE,DISTANCE,FAIR_AMT) values(?,?,?)";
		
		try {
		pstmt = con.prepareStatement(query);		
		pstmt.setString(1, prime1);
		pstmt.setDouble(2, kmeter);
		pstmt.setDouble(3, amt);
		int count = pstmt.executeUpdate();
		System.out.println("RECORED INSERTED");
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	Statement stmt = null;
	ResultSet rs = null;
	
	query = "select * from demo_info";
	
	try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7", "root", "sql@123");
	
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	System.out.println("SRN\t\tCAB_TYPE\t\tDISTANCE\t\tFAIR_AMT");
	System.out.println("===========================================================================================================================");
	while(rs.next()){
		int srn=rs.getInt(1);
		cab = rs.getString(2);
		String dist = rs.getString(3);
		int famt = rs.getInt(4);
		
		System.out.println(srn+"\t\t"+cab+"\t\t"+dist+"\t\t"+famt);
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return amt;

}


}
