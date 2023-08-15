package crudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatelink")
public class updateStudent extends HttpServlet{
	Connection con=null;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee","root","Nikhil@21");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("studentid");
		String name=req.getParameter("studentname");
		String stream=req.getParameter("studentstream");
		String date=req.getParameter("studentdate");
		
		int id=Integer.parseInt(sid);
		
		PreparedStatement pstmt=null;
		
		String query="update studentinfo set studentname=?, studentstream=?, studentdob=? where studentid=?";
		int count=0;
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(4,id);
			pstmt.setString(1, name);
			pstmt.setString(2, stream);
			pstmt.setString(3, date);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+" recordes updated.</h1>");
	}
}
