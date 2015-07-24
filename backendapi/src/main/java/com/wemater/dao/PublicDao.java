package com.wemater.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.wemater.dto.Article;
import com.wemater.exception.EvaluateException;
import com.wemater.util.SessionUtil;

public class PublicDao {
	
	private final SessionUtil su;
    //inject sessionUtil object at the runtime to use the session
	public PublicDao(SessionUtil sessionUtil) {
				this.su = sessionUtil;
	}


	public SessionUtil getSessionUtil() throws InstantiationException {
		
			if (su == null)  
	            throw new InstantiationException("SessionUtil has not been set on DAO before usage");
			return su;
	}
	
	
	
	
	@SuppressWarnings({ "unchecked" })
    public List<Article> fetchLatestArticles(){
    	
		List<Article> articleList = null;
    	try {
			su.beginSessionWithTransaction();
			
			articleList = su.getSession()
					.createQuery("from Article as article order by article.date desc")
					.setMaxResults(10)
		            .list();			
			
			
			su.CommitCurrentTransaction();
		
			
		} catch (HibernateException e) {
			su.rollBackCurrentTransaction();
			throw new EvaluateException(e);
			
		}
		return articleList;
    }
	
	@SuppressWarnings({ "unchecked" })
    public List<Article> fetchTrendingArticles(){
    	
		List<Article> articleList = null;
    	try {
			su.beginSessionWithTransaction();
			
			articleList = su.getSession()
					.createQuery("from Article as article order by article.likes desc")
		            .setMaxResults(4)
		 			.list();			
			
			
			su.CommitCurrentTransaction();
		
			
		} catch (HibernateException e) {
			su.rollBackCurrentTransaction();
			throw new EvaluateException(e);
			
		}
		return articleList;
    }
	
	
	@SuppressWarnings({ "unchecked" })
    public List<Article> fetchQuickReadArticles(){
    	
		List<Article> articleList = null;
    	try {
			su.beginSessionWithTransaction();
			
			articleList = su.getSession()
					.createQuery("from Article as article order by article.commentCount desc")
		            .setMaxResults(2)
		 			.list();			
			
			
			su.CommitCurrentTransaction();
		
			
		} catch (HibernateException e) {
			su.rollBackCurrentTransaction();
			throw new EvaluateException(e);
			
		}
		return articleList;
    }

	
	//do it again
	@SuppressWarnings({ "unchecked" })
    public List<Article> fetchExploreArticles(){
		System.out.println("fetchexplorearticles .. ");
    	
		List<Article> articleList = null;
        final int SIZE =5;
			try {
			su.beginSessionWithTransaction();
			
			 articleList = su.getSession()
					 .createQuery("from Article as article order by article.date desc")
					 .setMaxResults(SIZE)
		            .list();		
			
			su.CommitCurrentTransaction();
		
		} catch (HibernateException e) {
			su.rollBackCurrentTransaction();
			throw new EvaluateException(e);
			
		}
		return articleList;
    }
	
	@SuppressWarnings("unchecked")
	public List<Article> fetchAgainExploreArticles(int start){
		System.out.println("fetchexplorearticles again called here ");
    	
		List<Article> articleList = null;
        final int SIZE =5;
			try {
			su.beginSessionWithTransaction();
			
			 articleList = su.getSession()
					 .createQuery("from Article as article order by article.date desc")
					 .setFirstResult(start)
					 .setMaxResults(SIZE)
		            .list();		
			
			su.CommitCurrentTransaction();
		
		} catch (HibernateException e) {
			su.rollBackCurrentTransaction();
			throw new EvaluateException(e);
			
		}
		return articleList;
    }

	

}
