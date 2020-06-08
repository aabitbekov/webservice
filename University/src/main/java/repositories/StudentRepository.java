package repositories;
import domain.Department;
import domain.Students;
import domain.UserLoginData;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IStudentRepository;
import javax.ws.rs.BadRequestException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class StudentRepository implements IStudentRepository {
    private IDBRepository dbrepo = new PostgresRepository();

    @Override
    public void add(Students entity) {
        try {
            String sql = "INSERT INTO students(fname, lname, username, password) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, entity.getFname());
            stmt.setString(2, entity.getLname());
            stmt.setString(3, entity.getUsername());
            stmt.setString(4, entity.getPassword());
            stmt.setString(5,entity.getDepartment());
            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public void update(Students entity) {
        String sql = "UPDATE users SET ";

        if (entity.getFname() != null)
            sql += "fname=?,";
        if (entity.getLname() != null)
            sql += "lname=?,";
        if (entity.getPassword() != null)
            sql += "password=?,";

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
            stmt.setString(i++, entity.getUsername());

            stmt.execute();
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }

    @Override
    public List<Students> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            LinkedList<Students> Students = new LinkedList<>();
            while (rs.next()) {
                Students student = new Students(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username"),
                        rs.getString("department")
                );
                Students.add(student);
            }
            return Students;
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getSQLState());
        }
    }

    @Override
    public Students queryOne(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Students(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("department")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }


    public Students getStudentsByID(long id) {
        String sql = "SELECT * FROM students WHERE id = " + id + " LIMIT 1";
        return queryOne(sql);
    }


    public static Students findStudentsByLogin(UserLoginData data) {
        IDBRepository dbrepo = new PostgresRepository();
        try {
            String sql = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, data.getUsername());
            stmt.setString(2, data.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Students(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username"),
                        rs.getString("department")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }

    public static Students getStudentsByUsername(String username) {
        IDBRepository dbrepo = new PostgresRepository();
        try {
            String sql = "SELECT * FROM students WHERE username = ? LIMIT 1";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Students(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("department")
                );
            }
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Students findStudentByDepartment(Department department) {
        IDBRepository dbrepo = new PostgresRepository();
        try{
            String sql = "SELECT * FROM students WHERE department = ?";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.setString(1,"department");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Students(
                        rs.getLong("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("department")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
