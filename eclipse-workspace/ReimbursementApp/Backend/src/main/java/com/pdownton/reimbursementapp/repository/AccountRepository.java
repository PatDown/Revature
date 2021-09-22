package com.pdownton.reimbursementapp.repository;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.utils.HibernateSessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pat Down
 */
public class AccountRepository implements Repository<Account>{

    public AccountRepository() {
        super();
    }//AccountRepository()
    
    @Override
    public Account get(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction tran = session.beginTransaction();
        Account account = null;
        
        try {
            account = session.get(Account.class, id);
            tran.commit();
        } catch (HibernateException e) {
            tran.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }//finally

        return account;
    }//get(int)

    @Override
    public List<Account> getAll(){
        List<Account> accounts = new ArrayList<>();
        Session session = HibernateSessionFactory.getSession();
        Transaction tran = session.beginTransaction();
        
        try {
            accounts = session.createQuery("FROM Account", Account.class).getResultList();
            tran.commit();
        } catch (HibernateException e) {
            tran.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }//finally
        return accounts;
    }//getAll()

    @Override
    public void save(Account a){
        //No implementation needed
//        Session session = HibernateSessionFactory.getSession();
//        Transaction tran = session.beginTransaction();
//        
//        try { 
//            session.save(a);
//            tran.commit();
//        } catch(HibernateException e){
//            tran.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }//finally
    }//save(Account)

    @Override
    public void update(Account a){
        //No implementation needed
//        Session session = HibernateSessionFactory.getSession();
//        Transaction tran = session.beginTransaction();
//        
//        try {
//            session.update(a);
//            tran.commit();
//        } catch (HibernateException e){
//            tran.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }//finally
    }//update(Account)

    @Override
    public void delete(Account a){
        //No implementation needed
//        Session session = HibernateSessionFactory.getSession();
//        Transaction tran = session.beginTransaction();
//        
//        try {
//            session.delete(a);
//            tran.commit();
//        } catch (HibernateException e){
//            tran.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }//finally
    }//delete(Account)
}//AccountRepository
