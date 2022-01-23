package ch.tbz.modul226b.db;

import ch.tbz.modul226b.company.Company;
import ch.tbz.modul226b.departement.Departement;
import ch.tbz.modul226b.departement.Hr;
import ch.tbz.modul226b.departement.IT;
import ch.tbz.modul226b.departement.Production;
import ch.tbz.modul226b.user.Account;
import ch.tbz.modul226b.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabase implements DatabaseInterface {

    private Connection connection = null;


    public static SQLDatabase getInstance() {
        if (instance == null) {
            instance = new SQLDatabase();
        }
        return instance;
    }

    private static SQLDatabase instance;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/modul226b", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @Override
    public void saveUser(String table, User user) throws Exception {
        if (connection == null) connect();
        String WRITE_OBJECT_SQL = "";
        PreparedStatement statement;
        if (loadUser(user.getId()) == null) {
            WRITE_OBJECT_SQL = "INSERT INTO " + table + " VALUES (?, ?,?,?)";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
        } else {
            WRITE_OBJECT_SQL = "UPDATE " + table + " SET firstName=?,lastName=?,email=?, WHERE id = ?";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getId());
        }


        statement.executeUpdate();
        // get the generated key for the id
        ResultSet rs = statement.getGeneratedKeys();


        rs.close();
        statement.close();
        System.out.println("writeJavaObject: done serializing: " + user.getId());
        System.out.println("saved");
        connection.commit();


    }

    @Override
    public User loadUser(String id) throws Exception {
        if (connection == null) connect();

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE id=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        rs.close();
        pstmt.close();
        return user;
    }

    @Override
    public void saveCompany(String table, Company company) throws Exception {
        if (connection == null) connect();
        String WRITE_OBJECT_SQL = "";
        PreparedStatement statement;
        if (loadCompany(company.getId()) == null) {
            WRITE_OBJECT_SQL = "INSERT INTO " + table + " VALUES (?, ?,?,?)";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, company.getId());
            statement.setString(2, company.getName());
            statement.setString(3, company.getLocation());
            statement.setBoolean(4, company.getHeadquater());
        } else {
            WRITE_OBJECT_SQL = "UPDATE " + table + " SET name=?,location=?,isHeadquater=?, WHERE id = ?";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, company.getName());
            statement.setString(2, company.getLocation());
            statement.setBoolean(3, company.getHeadquater());
            statement.setString(4, company.getId());
        }


        statement.executeUpdate();
        // get the generated key for the id
        ResultSet rs = statement.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();
        statement.close();
        System.out.println("writeJavaObject: done serializing: " + company.getId());
        System.out.println("saved");
        connection.commit();
    }

    @Override
    public Company loadCompany(String id) throws Exception {
        if (connection == null) connect();

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM company WHERE id=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Company company = null;
        if (rs.next()) {
            company = new Company(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
        }
        rs.close();
        pstmt.close();
        return company;
    }

    @Override
    public void saveDepartement(String table, Departement departement) throws Exception {
        if (connection == null) connect();
        String WRITE_OBJECT_SQL = "";
        PreparedStatement statement;
        if (loadDepartement(departement.getId()) == null) {
            WRITE_OBJECT_SQL = "INSERT INTO " + table + " VALUES (?, ?)";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, departement.getId());
            statement.setString(2, departement.getName());

        } else {
            WRITE_OBJECT_SQL = "UPDATE " + table + " SET name=?, WHERE id = ?";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, departement.getName());

            statement.setString(2, departement.getId());
        }


        statement.executeUpdate();
        // get the generated key for the id
        ResultSet rs = statement.getGeneratedKeys();


        rs.close();
        statement.close();
        System.out.println("writeJavaObject: done serializing: " + departement.getId());
        System.out.println("saved");
        connection.commit();
    }

    @Override
    public Departement loadDepartement(String id) throws Exception {
        if (connection == null) connect();

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM departement WHERE id=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Departement departement = null;
        if (rs.next()) {
            switch (rs.getString(2)) {
                case "hr" -> departement = new Hr(rs.getString(1), rs.getString(2));
                case "IT" -> departement = new IT(rs.getString(1), rs.getString(2));
                case "Production" -> departement = new Production(rs.getString(1), rs.getString(2));
            }
        }
        rs.close();
        pstmt.close();
        return departement;
    }


    @Override
    public void saveAccount(String table, Account account) throws Exception {
        if (connection == null) connect();

        saveUser("user",account.getUserInfo());
        String WRITE_OBJECT_SQL = "";
        PreparedStatement statement;
        if (loadDepartement(account.getId()) == null) {
            WRITE_OBJECT_SQL = "INSERT INTO " + table + " VALUES (?,?,?, ?)";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getId());
            statement.setString(2, account.getUserInfo().getId());
            statement.setString(3, account.getWorkingInCompany().getId());
            statement.setString(4, account.getWorkingInDepartement().getId());

        } else {
            WRITE_OBJECT_SQL = "UPDATE " + table + " SET name=?, WHERE id = ?";
            statement = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account .getUserInfo().getId());
            statement.setString(2, account.getId());
        }


        statement.executeUpdate();
        // get the generated key for the id
        ResultSet rs = statement.getGeneratedKeys();


        rs.close();
        statement.close();
        System.out.println("writeJavaObject: done serializing: " + account.getId());
        System.out.println("saved");
        connection.commit();
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Account loadAccount(String id) throws Exception {
        if (connection == null) connect();

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM account WHERE id=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Account account = null;
        if (rs.next()) {
           account = new Account(rs.getString(1),loadUser(rs.getString(2)),loadCompany(rs.getString(3)),loadDepartement(rs.getString(4)));
        }
        rs.close();
        pstmt.close();
        return account;
    }
    public List<Account> loadAccounts() throws  Exception{
        if (connection == null) connect();

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM account");
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Account> accounts = new ArrayList<Account>();
        while (rs.next()){
            accounts.add(new Account(rs.getString(1),loadUser(rs.getString(2)),loadCompany(rs.getString(3)),loadDepartement(rs.getString(4))));
        }
        return accounts;
    }

    @Override
    public void delete(String id) throws Exception {
        if (connection == null) connect();

        System.out.println("not implemented");
    }
}
