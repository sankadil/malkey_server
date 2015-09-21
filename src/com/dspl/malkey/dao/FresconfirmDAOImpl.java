package com.dspl.malkey.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.domain.Fresconfirm;
import com.dspl.malkey.domain.Freservation;

public class FresconfirmDAOImpl implements FresconfirmDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public List<Fresconfirm> list(int startIndex, int numItems) {
		return em.createNamedQuery("FresconfirmListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Fresconfirm> listAll() {
		return em.createNamedQuery("FresconfirmListAll").getResultList();
	}

	@Transactional
	@Override
	public List<Fresconfirm> listUnconfirmedResevations() {
		/* -- Confirmation email sent to customer and customer is yet to leave a feedback on the Reservation details */
		int liConfirmationEmail = 1;		// Indicates the confirmation request email has been sent  
		int liGotFeedback = 0;				// Means the customer haven't yet responded to the reservation
		
		return em.createQuery("SELECT f FROM Fresconfirm f WHERE f.confemailsent=?1 AND f.gotfeedback=?2")
			.setParameter(1, liConfirmationEmail)
			.setParameter(2, liGotFeedback)
			.getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(recordid) AS COUNT FROM Fresconfirm").getSingleResult());
	}

	@Transactional
	@Override
	public void create(Fresconfirm fresconfirm) {
		em.persist(fresconfirm);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void createByFreservation(Freservation fresservation,String debCode) {
		
		//check whether had sent mail? Or not ?
		List<Fresconfirm> tmplst=em.createQuery("SELECT f FROM Fresconfirm f WHERE f.resno=?1").setParameter(1, fresservation.getResno()).getResultList();
		// If hadnot sent email.then send email.
		if(tmplst.size()>0)
		{
			Fdebtor fdebtor=em.find(Fdebtor.class, debCode);
			Fresconfirm fresconfirm=new Fresconfirm();
			fresconfirm.setAttachment("");
			fresconfirm.setComments(fresservation.getItinerary());
			fresconfirm.setConfemailsent(0);
			fresconfirm.setCustconfirmsts(0);;
			fresconfirm.setCustname(fdebtor.getDebname());
			fresconfirm.setDate(null);
			fresconfirm.setDconfemailsent(null);
			fresconfirm.setGotfeedback(0);
			fresconfirm.setResno(fresservation.getResno());
			fresconfirm.setToemail(fdebtor.getDebemail());
			em.persist(fresconfirm);
		}
	}

	@Transactional
	@Override
	public Fresconfirm findByID(int recordid) {
		return em.find(Fresconfirm.class, recordid);
	}

	@Transactional
	@Override
	public void removeByID(String recordid) {
		em.remove(em.find(Fresconfirm.class, recordid));
	}

	@Transactional
	@Override
	public List<Fresconfirm> listByResNo(String sResNo) {
		return em.createQuery("SELECT f FROM Fresconfirm f WHERE f.resno=?1").setParameter(1, sResNo).getResultList();
	}

	@Transactional
	@Override
	public int awaitingConfirmation(String sResNo) {
		// Get the record count records for the provided ResNo where a confirmation is being expected 
		return (Integer)(em.createNativeQuery("SELECT COUNT(f.recordid) FROM Fresconfirm f WHERE f.confemailsent=1 AND f.gotfeedback=0 AND f.resno=?1").setParameter(1, sResNo).getSingleResult());
	}

	@Transactional
	@Override
	public void updateResAsConfirmed(String sResNo) {
		// Update the provided ResNo as feedback received, confirmation status as confirmed and the date to current system date  
		em.createQuery("UPDATE Fresconfirm f " +
				"SET f.gotfeedback=1, custconfirmsts=1, date=?1 " +
				"WHERE f.resno=?2")
			.setParameter(1, new Date())
			.setParameter(2, sResNo)
			.executeUpdate();
	}

	@Transactional
	@Override
	public void updateResWithAlterationDtl(String sResNo, String sComment) {
		// Update the provided ResNo as feedback received, confirmation status as "0" (un-confirmed), comments with users comments and the date to current system date
		em.createQuery("UPDATE Fresconfirm f " +
				"SET f.gotfeedback=1, custconfirmsts=0, comments=?1, date=?2 " +
			"WHERE f.resno=?3")
		.setParameter(1, sComment)
		.setParameter(2, new Date())
		.setParameter(3, sResNo)
		.executeUpdate();
	}

}
