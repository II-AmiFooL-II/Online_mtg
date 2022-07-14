package com.mtg_bank.online_mtg;

import java.io.IOException;
import java.sql.Connection;
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
import javax.servlet.http.HttpSession;

import com.mtg_bank.online_mtg.database.JDBC_Connect;
import com.mtg_bank.online_mtg.model.AccountModel;

public class DepositServlet extends HttpServlet {
	String account_no, username, password;
	Connection conn;
	Statement stmt;
	boolean pass_wrong = false;
	int current_amount, deposit_amount;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("lll");
		account_no = request.getParameter("account_no");
		System.out.println("lll");
		username = request.getParameter("username");
		System.out.println("lll");
		password = request.getParameter("password");
		System.out.println("lll");
		deposit_amount = Integer.parseInt(request.getParameter("amount"));

		try {
			JDBC_Connect connect = new JDBC_Connect();
			conn = connect.getConnection();

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from account where id='" + account_no + "' and username='" + username
					+ "' and password='" + password + "'");

			if (!rs.isBeforeFirst()) {
				request.setAttribute("isPassOK", "No");
				RequestDispatcher rd = request.getRequestDispatcher("deposit.jsp");
				rd.forward(request, response);
			} else {
				System.out.println("I am in");
				ResultSet rs1 = stmt.executeQuery("select * from amount where id ='" + account_no + "'");

				while (rs1.next()) {
					current_amount = rs1.getInt(2);

					System.out.println(current_amount);
				}

				current_amount += deposit_amount;

				PreparedStatement ps = conn.prepareStatement("update amount set amount=? where id= ?");
				ps.setInt(1, current_amount);
				ps.setString(2, account_no);
				ps.executeUpdate();
				
				conn.close();
				
				RequestDispatcher rd = request.getRequestDispatcher("Deposit_process.jsp");
				rd.forward(request, response);

			}

		} catch (SQLException e) {
			System.out.println("AAAAAAAAA");
			e.printStackTrace();
		}
	}

}
