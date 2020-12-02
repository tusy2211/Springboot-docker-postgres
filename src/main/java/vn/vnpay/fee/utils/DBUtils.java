package vn.vnpay.fee.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ytb
 */
public final class DBUtils {

    private final static Logger log = LogManager.getLogger(DBUtils.class);

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void closeQuietly(Connection conn) {
        try {
            close(conn);
        } catch (SQLException e) {
            log.warn("CloseQuietly connection fail: ", e);
        }
    }

    public static void closeQuietly(Statement stmt, Connection conn) {
        try {
            closeQuietly(stmt);
        } finally {
            closeQuietly(conn);
        }
    }

    public static void closeQuietly(ResultSet rs, Statement stmt, Connection conn) {
        try {
            closeQuietly(rs);
        } finally {
            try {
                closeQuietly(stmt);
            } finally {
                closeQuietly(conn);
            }
        }
    }

    public static void closeQuietly(ResultSet rs) {
        try {
            close(rs);
        } catch (SQLException e) {
            log.warn("CloseQuietly ResultSet fail: ", e);
        }
    }

    public static void closeQuietly(Statement stmt) {
        try {
            close(stmt);
        } catch (SQLException e) {
            log.warn("CloseQuietly Statement fail: ", e);
        }
    }

    public static void closeQuietly(Connection conn, Statement stmt) {
        try {
            closeQuietly(stmt);
        } finally {
            closeQuietly(conn);
        }
    }

    public static void closeQuietly(Connection conn, PreparedStatement pstmt) {
        try {
            closeQuietly(pstmt);
        } finally {
            closeQuietly(conn);
        }
    }

    public static void closeQuietly(Connection conn, CallableStatement cstmt) {
        try {
            closeQuietly(cstmt);
        } finally {
            closeQuietly(conn);
        }
    }

    public static void closeQuietly(Statement stmt, ResultSet rs) {
        try {
            closeQuietly(stmt);
        } finally {
            closeQuietly(rs);
        }
    }

    public static void closeQuietly(Connection conn, Statement stmt,
            ResultSet rs) {
        try {
            closeQuietly(rs);
        } finally {
            closeQuietly(conn, stmt);
        }
    }

    public static void closeQuietly(Connection conn, CallableStatement cstmt,
            ResultSet rs) {
        try {
            closeQuietly(rs);
        } finally {
            closeQuietly(conn, cstmt);
        }
    }

    public static void closeQuietly(Connection conn, PreparedStatement pstmt,
            ResultSet rs) {
        try {
            closeQuietly(rs);
        } finally {
            closeQuietly(conn, pstmt);
        }
    }

    /*
    
     */
    public static void closeQuietly(PreparedStatement pstmt) {
        try {
            close(pstmt);
        } catch (SQLException e) {
            log.error("Error while CLOSE PreparedStatement.", e);
        }
    }
}
