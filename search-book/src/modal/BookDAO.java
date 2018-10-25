package modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Book;

public class BookDAO {
	private String jdbcURL;
	private String jdbcTitle;
	private String jdbcAuthor;
	private Connection jdbcConnection;
	
	public BookDAO(String jdbcURL, String jdbcTitle, String jdbcAuthor) {
		this.jdbcURL = jdbcURL;
		this.jdbcTitle = jdbcTitle;
		this.jdbcAuthor = jdbcAuthor;
	}
	
	protected void connect() throws SQLException {
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcTitle, jdbcAuthor);
		}
	}
	
	protected void disconnect() throws SQLException {
		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.isClosed();
		}
	}
	
	public List<Book> searchBook(String keyword) throws SQLException{
		List<Book> listBook = new ArrayList<>();
		
		String sql = keyword == null ? "SELECT * FROM book" : "SELECT * FROM book where title LIKE ? ";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		if(keyword != null) {
			statement.setString(1, "%" + keyword + "%");
		}
		
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			Book book = new Book(title, author);
			listBook.add(book);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listBook;
	}
	
	
}









