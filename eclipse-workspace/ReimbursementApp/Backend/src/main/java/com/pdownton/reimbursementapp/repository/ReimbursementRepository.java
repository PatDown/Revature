package com.pdownton.reimbursementapp.repository;

import com.pdownton.reimbursementapp.models.Reimbursement;
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
public class ReimbursementRepository implements Repository<Reimbursement> {
    

    public ReimbursementRepository() {
        super();
    }//ReimbursementRepository()
    
    @Override
    public Reimbursement get(int id){
        Session session = HibernateSessionFactory.getSession();
        Transaction tran = session.beginTransaction();
        Reimbursement reimbursement = null;
        try {
            reimbursement = session.get(Reimbursement.class, id);
            tran.commit();
        } catch (HibernateException e) {
            tran.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            
        }//finally
        return reimbursement;
    }//get(int)

    @Override
    public List<Reimbursement> getAll() {
        List<Reimbursement> reimbursements = new ArrayList<>();
        Session session = HibernateSessionFactory.getSession();
        Transaction tran = session.beginTransaction();
        
        try {
            reimbursements = session.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
            tran.commit();
        } catch (HibernateException e) {
            tran.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }//finally
        return reimbursements;
    }//getAll()

    @Override
    public void save(Reimbursement r){
        Session session = HibernateSessionFactory.getSession();
        Transaction tran = session.beginTransaction();
        
        try { 
            session.save(r);
            tran.commit();
        } catch(HibernateException e){
            tran.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }//finally
    }//save(Reimbursement)

    @Override
    public void update(Reimbursement r){
        Session session = HibernateSessionFactory.getSession();
        Transaction tran = session.beginTransaction();
        
        try {
            session.update(r);
            tran.commit();
        } catch (HibernateException e){
            tran.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }//finally
        
    }//update(Reimbursement)

    @Override
    public void delete(Reimbursement r){
        //No implementaion needed
//        Session session = HibernateSessionFactory.getSession();
//        Transaction tran = session.beginTransaction();
//        
//        try {
//            session.delete(r);
//            tran.commit();
//        } catch (HibernateException e){
//            tran.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }//finally
    }//delete(Reimbursement)
    
}//ReimbursementRepository
