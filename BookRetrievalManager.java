package assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookRetrievalManager {
    private Connection connection;

    public void connectToDatabase() throws SQLException {
        String url = "jdbc:sqlserver://NINA:1433;databaseName=Books;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        connection = DriverManager.getConnection(url);
    }

    public void getAllBooks() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM [dbo].[books]");

            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Author: " + resultSet.getString("author"));
                System.out.println("Publication Year: " + resultSet.getInt("publication_year"));
                System.out.println("Price: " + resultSet.getDouble("price"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getBooksByAuthor(String authorName) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM [dbo].[books] WHERE author = '" + authorName + "'";
            resultSet = statement.executeQuery(query);

       
            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Author: " + resultSet.getString("author"));
                System.out.println("Publication Year: " + resultSet.getInt("publication_year"));
                System.out.println("Price: " + resultSet.getDouble("price"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
       
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getBooksPublishedAfterYear(int year) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM [dbo].[books] WHERE publication_year > " + year;
            resultSet = statement.executeQuery(query);

         
            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Author: " + resultSet.getString("author"));
                System.out.println("Publication Year: " + resultSet.getInt("publication_year"));
                System.out.println("Price: " + resultSet.getDouble("price"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
       
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        BookRetrievalManager manager = new BookRetrievalManager();
        try {

            manager.connectToDatabase();

            System.out.println("All Books:");
            manager.getAllBooks();

            System.out.println("\nBooks by Author 'Dhonielle Clayton':");
            manager.getBooksByAuthor("Dhonielle Clayton");

            System.out.println("\nBooks Published After 2010:");
            manager.getBooksPublishedAfterYear(2010);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager.connection != null) {
                try {
                    manager.connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
