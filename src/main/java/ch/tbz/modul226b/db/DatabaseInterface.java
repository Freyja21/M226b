package ch.tbz.modul226b.db;


import ch.tbz.modul226b.company.Company;
import ch.tbz.modul226b.departement.Departement;
import ch.tbz.modul226b.user.Account;
import ch.tbz.modul226b.user.User;

public interface DatabaseInterface {


    public void saveUser(String table, User user) throws Exception;
    public User loadUser(String id) throws  Exception;
    public void saveCompany(String table, Company company) throws Exception;
    public Company loadCompany(String id) throws  Exception;
    public void saveDepartement(String table, Departement departement) throws Exception;
    public Departement loadDepartement(String id) throws  Exception;
    public void saveAccount(String table, Account departement) throws Exception;
    public Account loadAccount(String id) throws  Exception;
    public void delete(String id) throws Exception;

}
