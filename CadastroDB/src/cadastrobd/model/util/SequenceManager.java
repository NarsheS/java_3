package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    private ConectorBD conector = new ConectorBD();

    public int getValue(String sequenceName) {
        int nextVal = 0;
        String sql = "SELECT NEXT VALUE FOR " + sequenceName; // Usando a sintaxe do SQL Server
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conector.getPrepared(conn, sql);
             ResultSet rs = conector.getSelect(stmt)) {
            if (rs.next()) {
                nextVal = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextVal;
    }
}
