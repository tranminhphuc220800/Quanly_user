import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static String driverclass = null;
    static String url = null;
    static String name = null;
    static String password = null;
    private static DriverManager DiverManager;

    static{
        try{
            Properties pros = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream( "jdbc.properties");
            pros.load(is);

            driverclass = pros.getProperty("driverclass");
            url = pros.getProperty("url");
            name = pros.getProperty("name");
            password = pros.getProperty("password");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn = null;
        try{
            Class.forName(driverclass);
            conn = DiverManager.getConnection(url, name, password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConn ( Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e){
           e.printStackTrace();
        }finally{
           conn = null;
        }
    }

    public static void closePs (PreparedStatement ps){
        try {
            if(ps != null){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            ps = null;
        }
    }
    public static void closeRs (PreparedStatement rs){
        try {
            if(rs != null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            rs = null;
        }
    }

    public static void release (Connection conn, PreparedStatement ps){
        closeConn(conn);
        closePs(ps);
    }
    public static void release (Connection conn, PreparedStatement ps, ResultSet rs){
        closePs(ps);
        closeConn(conn);
        closeRs((PreparedStatement) rs);
    }
}
