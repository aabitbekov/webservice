package repositories;

import domain.Teachers;
import domain.UserLoginData;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.ITeacherRepository;

import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TeacherRepository implements ITeacherRepository{
    private IDBRepository dbrepo = new PostgresRepository();

    @Override
    public void add(Teachers entity) {
        try {
            String sql = "INSERT INTO teachers(fname, lname, username, password, s_id) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getFname());
            stmt.setString(2, entity.getLname());
            stmt.setString(3, entity.getUsername());
            stmt.setString(4, entity.getPassword());
            stmt.setString(5,entity.getS_id());
            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public void update(Teachers entity) {
        String sql = "UPDATE teachers SET ";

        if (entity.getFname() != null)
            sql += "fname=?,";
        if (entity.getLname() != null)
            sql += "lname=?,";
        if (entity.getPassword() != null)
            sql += "password=?,";
        if (entity.getS_id() != null)
            sql += "s_id = ?";

        sql = sql.substring(0, sql.length() - 1);
        sql += " WHERE username=?";

        try {
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            int i = 1;
            if (entity.getFname() != null)
                stmt.setString(i++, entity.getFname());
            if (entity.getLname() != null)
                stmt.setString(i++, entity.getLname());
            if (entity.getPassword() != null)
                stmt.setString(i++, entity.getPassword());
            if (entity.getS_id() != null)
                stmt.setString(i++,entity.getS_id());
            stmt.setString(i++, entity.getUsername());

            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }


    @Override
    public List<Teachers> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            LinkedList<Teachers> Teachers = new LinkedList<>();
            while (rs.next()) {
                Teachers teacher = new Teachers(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username"),
                        rs.getString("s_id")
                );
                Teachers.add(teacher);
            }
            return Teachers;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public Teachers queryOne(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Teachers(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("s_id")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }


    public Teachers getTeachersByID(long id) {
        String sql = "SELECT * FROM teachers WHERE id = " + id + " LIMIT 1";
        return queryOne(sql);
    }


    public static Teachers findTeachersByLogin(UserLoginData data) {
        IDBRepository dbrepo = new PostgresRepository();
        try {
            String sql = "SELECT * FROM teachers WHERE username = ? AND password = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, data.getUsername());
            stmt.setString(2, data.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Teachers(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username"),
                        rs.getString("s_id")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }

    public static Teachers getTeachersByUsername(String username) {
        IDBRepository dbrepo = new PostgresRepository();
        try {
            String sql = "SELECT * FROM teachers WHERE username = ? LIMIT 1";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Teachers(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("s_id")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }
}
