package authentication;

import entity.User;
import java.sql.*;
import java.util.Scanner;
import static db.ConnectorDB.getConnection;
public class Login{
    Connection conn = getConnection();
    Scanner sc = new Scanner(System.in);
    public Login() throws SQLException{}
    private static Scanner input = new Scanner(System.in);
    public String loginStatement(String username, String password) throws SQLException{
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String dbQuery = "Select username from user where username ='"+user.getUsername()+
                "'and password ='"+user.getPassword()+"'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(dbQuery);
        while (resultSet.next()){
            return "Login Success";
        }
        return "Login Fail";
    }
    public String loginPrepareStatement(String username, String password) throws SQLException{
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String dbQuery = "select username from user where username = ? and password = ?";

        PreparedStatement prepared = conn.prepareStatement(dbQuery);
        prepared.setString(1,user.getUsername());
        prepared.setString(2,user.getPassword());

        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()){
            return "Login Success";
        }
        return "Login Fail";
    }
    public static void createAcc() throws SQLException{
        int id;
        String username;
        String password;
        try {
            Connection connection = getConnection();
            String query = "insert into user values(?,?,?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query);
            System.out.println("Create new id: ");
            id = input.nextInt();
            preparedStatement.setInt(1,id);
            System.out.println("Create new username: ");
            username = input.next();
            preparedStatement.setString(2,username);
            System.out.println("Create new password: ");
            password = input.next();
            preparedStatement.setString(3,password);

            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted>0) {
                System.out.println("Account created successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void menu() throws SQLException {
        int action = 0;
        do {
            System.out.println("============Menu============");
            System.out.println("1. Register");
            System.out.println("2. Login Statement");
            System.out.println("3. Login PrepareStatement");
            System.out.println("3. Exit");
            System.out.println("Your choice: ");
            action = sc.nextInt();
            switch (action){
                case 1:
                    createAcc();
                    break;
                case 2:
                    System.out.println("Enter username");
                    String username = sc.next();
                    System.out.println("Enter password");
                    String password = sc.next();
                    String message = loginStatement(username,password);
                    System.out.println(message);
                    break;
                case 3:
                    System.out.println("Enter username");
                    String user = sc.next();
                    System.out.println("Enter password");
                    String pass = sc.next();
                    String mess = loginPrepareStatement(user,pass);
                    System.out.println(mess);
                    break;
                default:
                    System.out.println("Enter from 1 to 4 only");
                    break;
            }
        } while (action != 4);
    }

    public static void main(String[] args) throws SQLException {
        Login login = new Login();
        login.menu();

    }
}
