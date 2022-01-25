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

    /**
     *
     * @return
     */
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

    /**
     *
     * @param table
     * @param user
     * @throws Exception
     */
    @Override
    public void saveUser(String table, User user) throws Exception {
        //SQL commands for saving the Useraccount
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

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    //Gets userinfos from
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

    /**
     *
     * @param table
     * @param company
     * @throws Exception
     */
    //Saves data for companyinfo in table company
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

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    //load infos  for company
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

    /**
     *
     * @param table
     * @param departement
     * @throws Exception
     */
    // save infos for departement
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

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    //load departement infos
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

    /**
     *
     * @param table
     * @param account
     * @throws Exception
     */
    //save Accountinfo in table account
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
    //load account infos
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

    /**
     *
     * @return
     * @throws Exception
     */
    //load account infos
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

    /**
     *
     * @param id
     * @throws Exception
     */
    // for future oportunities for deleting accounts
    @Override
    public void delete(String id) throws Exception {
        if (connection == null) connect();

        System.out.println("not implemented");
    }
}
