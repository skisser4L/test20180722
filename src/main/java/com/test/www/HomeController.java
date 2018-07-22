package com.test.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*
		 * logger.info("Welcome home! The client locale is {}.", locale);
		 * 
		 * Date date = new Date(); DateFormat dateFormat =
		 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		 * 
		 * String formattedDate = dateFormat.format(date);
		 * 
		 * model.addAttribute("serverTime", formattedDate );
		 */
		try {
			Class.forName("org.postgresql.Driver");

			System.out.println("JDBC Driver ---- Loaded");

			String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
			String userName = "postgres";
			String password = "postgres";

			Connection connection = null;
			connection = DriverManager.getConnection(url, userName, password);

			String sql = "insert into sbcircle_register values (?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, "santos");
			pstmt.setString(3, "1234");

			int result = pstmt.executeUpdate();

			System.out.println("add rows :" + result);

			connection.close();

			System.out.println("Mori Fire");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home";
	}

}
