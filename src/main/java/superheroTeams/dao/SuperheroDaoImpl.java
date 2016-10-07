package superheroTeams.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import superheroTeams.entities.Power;
import superheroTeams.entities.PowerType;
import superheroTeams.entities.Search;
import superheroTeams.entities.Superhero;
import superheroTeams.entities.SuperheroTeam;

@Repository
@Transactional
public class SuperheroDaoImpl implements SuperheroDao {

	@PersistenceContext
	private EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	private CriteriaBuilder cb;

	public void setCb(CriteriaBuilder cb) {
		this.cb = cb;
	}

	@Override
	public List<SuperheroTeam> getTeams() {
		return em.createQuery("SELECT t FROM SuperheroTeam t", SuperheroTeam.class).getResultList();
	}

	@Override
	public SuperheroTeam getTeam(int id) {
		return em.createQuery("SELECT t FROM SuperheroTeam t WHERE t.id = :id", SuperheroTeam.class)
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public void addTeam(SuperheroTeam team) {
		em.merge(team);

	}

	@Override
	public void deleteTeam(int id) {
		SuperheroTeam team = em.createQuery("SELECT t FROM SuperheroTeam t WHERE t.id = :id", SuperheroTeam.class)
				.setParameter("id", id).getSingleResult();
		em.remove(team);

	}

	@Override
	public void updateTeam(SuperheroTeam team) {
		em.merge(team);
	}

	@Override
	public List<Superhero> getHeros() {
		return em.createQuery("SELECT s FROM Superhero s", Superhero.class).getResultList();
	}

	@Override
	public Superhero getHero(int id) {
		return em.createQuery("SELECT s FROM Superhero s WHERE s.id = :id", Superhero.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void addHero(Superhero hero) {
		em.merge(hero);
	}

	@Override
	public void deleteHero(int id) {
		Superhero hero = em.createQuery("SELECT s FROM Superhero s WHERE s.id = :id", Superhero.class)
				.setParameter("id", id).getSingleResult();
		em.remove(hero);
	}

	@Override
	public void updateHero(Superhero hero) {
		em.merge(hero);
	}

	@Override
	public List<Power> getPowers() {
		return em.createQuery("SELECT p FROM Power p", Power.class).getResultList();
	}

	@Override
	public Power getPower(int id) {
		return em.createQuery("SELECT p FROM Power p WHERE p.id = :id", Power.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void addPower(Power power) {
		int id = power.getType().getId();
		String type = em.createQuery("SELECT p.type.type FROM Power p WHERE p.type.id = :id", String.class)
				.setParameter("id", id).getSingleResult();
		power.getType().setType(type);
		em.merge(power);
	}

	@Override
	public void deletePower(int id) {
		Power power = em.createQuery("SELECT p FROM Power p WHERE p.id = :id", Power.class).setParameter("id", id)
				.getSingleResult();
		em.remove(power);
	}

	@Override
	public void updatePower(Power power) {
		em.merge(power);
	}

	@Override
	public List<PowerType> getPowerTypes() {
		return em.createQuery("SELECT p FROM PowerType p", PowerType.class).getResultList();
	}

	@Override
	public List<Power> searchPowers(Search search) {
		List<Predicate> restrictions = new ArrayList<Predicate>();
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Power> cq = cb.createQuery(Power.class);
		Root<Power> p = cq.from(Power.class);
		Predicate[] predicates = new Predicate[search.getSearchList().size()];
		int i=0;
		for(String searchword: search.getSearchList()){
			
			Predicate predicateType = cb.like(p.<PowerType> get("type").<String> get("type"), searchword);

			Predicate predicateDesc = cb.like(p.<String> get("description"), "%" + searchword + "%");

			Predicate predicateName = cb.like(p.<String> get("name"), searchword);

			Predicate predicate = cb.or(predicateType, predicateDesc, predicateName);
			predicates[i] = predicate;
			i++;
		}
		Predicate predicate = search.isAll()? cb.and(predicates): cb.or(predicates);
		restrictions.add(predicate);
		cq.where(restrictions.toArray(new Predicate[0]));
		cq.select(p);
		TypedQuery<Power> q = em.createQuery(cq);
		List<Power> powers = q.getResultList();
		return powers;
	}

	@Override
	public List<Superhero> searchHeros(Search search) {
		List<Predicate> restrictions = new ArrayList<Predicate>();
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Superhero> cq = cb.createQuery(Superhero.class);
		Root<Superhero> p = cq.from(Superhero.class);
		Predicate[] predicates = new Predicate[search.getSearchList().size()];
		int i=0;
		for(String searchword: search.getSearchList()){
			Predicate predicateName = cb.like(p.<String> get("heroName"), searchword);

			Predicate predicateMutant = cb.like(p.<String> get("mutantName"), searchword);
			
			Predicate predicateOrigin = cb.like(p.<String> get("originType"), searchword);

			Predicate predicate = cb.or(predicateName, predicateMutant,predicateOrigin);
			predicates[i] = predicate;
			i++;
		}
		Predicate predicate = search.isAll()? cb.and(predicates): cb.or(predicates);
		restrictions.add(predicate);
		cq.where(restrictions.toArray(new Predicate[0]));
		cq.select(p);
		TypedQuery<Superhero> q = em.createQuery(cq);
		List<Superhero> heros = q.getResultList();
		return heros;
	}

	@Override
	public List<SuperheroTeam> searchTeams(Search search) {
		List<Predicate> restrictions = new ArrayList<Predicate>();
		cb = em.getCriteriaBuilder();
		CriteriaQuery<SuperheroTeam> cq = cb.createQuery(SuperheroTeam.class);
		Root<SuperheroTeam> p = cq.from(SuperheroTeam.class);
		Predicate[] predicates = new Predicate[search.getSearchList().size()];
		int i=0;
		for(String searchword: search.getSearchList()){
			Predicate predicateName = cb.like(p.<String> get("name"), searchword);

			Predicate predicateHQ = cb.like(p.<String> get("headquarters"), searchword);
			
			Predicate predicateSupport = cb.like(p.<String> get("publicSupportLevel"), searchword);

			Predicate predicateLeader = cb.like(p.<Superhero> get("teamLeader").<String> get("heroName"), searchword);
			
			Predicate predicate = cb.or(predicateName, predicateHQ,predicateSupport, predicateLeader);
			predicates[i] = predicate;
			i++;
		}
		Predicate predicate = search.isAll()? cb.and(predicates): cb.or(predicates);
		restrictions.add(predicate);
		cq.where(restrictions.toArray(new Predicate[0]));
		cq.select(p);
		TypedQuery<SuperheroTeam> q = em.createQuery(cq);
		List<SuperheroTeam> heros = q.getResultList();
		return heros;
	}



}
