package cn.fyg.pa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.model.Fychkitem;




@Repository
public class FychkitemDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Fychkitem find(Long id) {
		return entityManager.find(Fychkitem.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Fychkitem> getAllByIdAsc(){
		return entityManager.createQuery("select p from Fychkitem p order by id asc").getResultList();
	}
	
	@Transactional
	public Fychkitem persist(Fychkitem fychkitem) {
			entityManager.persist(fychkitem);
			return fychkitem;
	}	
	
	public void flush(){
		entityManager.flush();
	}
	
	public void refresh(Fychkitem fychkitem){
		entityManager.refresh(fychkitem);
	}
	
	@Transactional
	public Fychkitem merge(Fychkitem fychkitem) {
			Fychkitem ret=entityManager.merge(fychkitem);
			return ret;
	}	
}
