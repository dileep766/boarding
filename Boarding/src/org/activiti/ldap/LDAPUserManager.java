/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctl.activiti.form.UserEntityInfo;

/**
 * Implementation of the {@link UserIdentityManager} interface specifically for LDAP.
 * 
 * Note that only a few methods are actually implemented, as many of the operations 
 * (save, update, etc.) are done on the LDAP system directly. 
 * 
 * @author Joram Barrez
 */
public class LDAPUserManager extends AbstractManager implements UserIdentityManager {

  private static Logger logger = LoggerFactory.getLogger(LDAPUserManager.class);

  protected LDAPConfigurator ldapConfigurator;

  public LDAPUserManager(LDAPConfigurator ldapConfigurator) {
    this.ldapConfigurator = ldapConfigurator;
  }
  
  @Override
  public User createNewUser(String userId) {
    throw new ActivitiException("LDAP user manager doesn't support creating a new user");
  }


  @Override
  public void insertUser(User user) {
    throw new ActivitiException("LDAP user manager doesn't support inserting a new user");
  }


  @Override
  public void updateUser(User updatedUser) {
    throw new ActivitiException("LDAP user manager doesn't support updating a user");
  }
  
  @Override
  public boolean isNewUser(User user) {
		
		
  	throw new ActivitiException("LDAP user manager doesn't support adding or updating a user");
  
  }


  @Override
  public UserEntityInfo findUserById(final String userId) {
    LDAPTemplate ldapTemplate = new LDAPTemplate(ldapConfigurator);
    return ldapTemplate.execute(new LDAPCallBack<UserEntityInfo>() {
  
      public UserEntityInfo executeInContext(InitialDirContext initialDirContext) {
        try {
        
          String searchExpression = ldapConfigurator.getLdapQueryBuilder().buildQueryByUserId(ldapConfigurator, userId);
      logger.info("::: searchExpression ::: " +searchExpression);

          String baseDn = ldapConfigurator.getUserBaseDn() != null ? ldapConfigurator.getUserBaseDn() : ldapConfigurator.getBaseDn();
        logger.info("::: baseDn ::: " +baseDn);

          NamingEnumeration< ? > namingEnum = initialDirContext.search(baseDn, searchExpression, createSearchControls());
          
          UserEntityInfo user = new UserEntityInfo();
          while (namingEnum.hasMore()) { // Should be only one
          logger.info("user isnide LDAPUser NMaanegtr User found ::: " );

            SearchResult result = (SearchResult) namingEnum.next();
            logger.info("user isnide LDAPUser NMaanegtr result "+result);
            mapSearchResultToUser(result, user);
            logger.info("user isnide LDAPUser NMaanegtr "+user.getId()+user.getEmail());
          }
          namingEnum.close();
          
          return user;

        } catch (NamingException ne) {
          logger.debug("Could not find user " + userId + " : " + ne.getMessage(), ne);
          return null;
        }
      }

    });
  }


  @Override
  public void deleteUser(String userId) {
    throw new ActivitiException("LDAP user manager doesn't support deleting a user");
  }


  @Override
  public List<User> findUserByQueryCriteria(final UserQueryImpl query, final Page page) {
    logger.info(":::findUserByQueryCriteria::: ");
    if (query.getId() != null) {
        logger.info(":::query.getId(::: " + query.getId());

      List<User> result = new ArrayList<User>();
      result.add(findUserById(query.getId()));
      return result;
    } else if (query.getFullNameLike() != null){
        logger.info(":::query.getFullNameLike()::: " +query.getFullNameLike());

      LDAPTemplate ldapTemplate = new LDAPTemplate(ldapConfigurator);
      return ldapTemplate.execute(new LDAPCallBack<List<User>>() {
        
        public List<User> executeInContext(InitialDirContext initialDirContext) {
          List<User> result = new ArrayList<User>();
          try {
            String searchExpression = ldapConfigurator.getLdapQueryBuilder().buildQueryByFullNameLike(ldapConfigurator, query.getFullNameLike());
            logger.info(":::searchExpression()::: " +searchExpression);

            String baseDn = ldapConfigurator.getUserBaseDn() != null ? ldapConfigurator.getUserBaseDn() : ldapConfigurator.getBaseDn();
            logger.info(":::baseDn::: " +baseDn);

            NamingEnumeration< ? > namingEnum = initialDirContext.search(baseDn, searchExpression, createSearchControls());
            
            while (namingEnum.hasMore()) { 
              SearchResult searchResult = (SearchResult) namingEnum.next();
              
              UserEntity user = new UserEntity();
              mapSearchResultToUser(searchResult, user);
              result.add(user);
              
            }
            namingEnum.close();
            
          } catch (NamingException ne) {
            logger.debug("Could not execute LDAP query: " + ne.getMessage(), ne);
            return null;
          }
          return result;
        }
        
      });
      
    } else {
      throw new ActivitiIllegalArgumentException("Query is currently not supported by LDAPUserManager.");
    }
    
  }
  
  protected void mapSearchResultToUser( SearchResult result, UserEntity user) throws NamingException {
    logger.info("ldapConfigurator.getUserIdAttribute() =======> "+ldapConfigurator.getUserIdAttribute() );
	  if (ldapConfigurator.getUserIdAttribute() != null) {
      user.setId(result.getAttributes().get(ldapConfigurator.getUserIdAttribute()).get().toString());
      logger.info("ldapConfigurator.getUserIdAttribute() inside configurator "+ldapConfigurator.getUserIdAttribute());
      logger.info("result.getAttributes().get(ldapConfigurator.getUserIdAttribute()).get().toString() inside configurator "+result.getAttributes().get(ldapConfigurator.getUserIdAttribute()).get().toString());
      logger.info("user id inside configurator "+user.getId());
    }
    if (ldapConfigurator.getUserFirstNameAttribute() != null) {
    	try{
    		user.setFirstName(result.getAttributes().get(ldapConfigurator.getUserFirstNameAttribute()).get().toString());
    	}catch(NullPointerException e){
    		user.setFirstName("");
    	}
    }
    if (ldapConfigurator.getUserLastNameAttribute() != null) {
    	try{
    		user.setLastName(result.getAttributes().get(ldapConfigurator.getUserLastNameAttribute()).get().toString());
    	}catch(NullPointerException e){
    		user.setLastName("");
    	}
    }
    if (ldapConfigurator.getUserEmailAttribute() != null) {
    	if(result.getAttributes().get(ldapConfigurator.getUserEmailAttribute())!=null)
      user.setEmail(result.getAttributes().get(ldapConfigurator.getUserEmailAttribute()).get().toString());
    }
    
    /*if (ldapConfigurator.getGroupIdAttribute() != null) {
    	if(result.getAttributes().get(ldapConfigurator.getGroupIdAttribute())!=null)
      user.setTitle(result.getAttributes().get(ldapConfigurator.getGroupIdAttribute()).get().toString());
    }*/
  }
  protected void mapSearchResultToUser( SearchResult result, UserEntityInfo user) throws NamingException {
	    logger.info("ldapConfigurator.getUserIdAttribute() =======> "+ldapConfigurator.getUserIdAttribute() );
		  if (ldapConfigurator.getUserIdAttribute() != null) {
	      user.setId(result.getAttributes().get(ldapConfigurator.getUserIdAttribute()).get().toString());
	      logger.info("ldapConfigurator.getUserIdAttribute() inside configurator "+ldapConfigurator.getUserIdAttribute());
	      logger.info("result.getAttributes().get(ldapConfigurator.getUserIdAttribute()).get().toString() inside configurator "+result.getAttributes().get(ldapConfigurator.getUserIdAttribute()).get().toString());
	      logger.info("user id inside configurator "+user.getId());
	    }
	    if (ldapConfigurator.getUserFirstNameAttribute() != null) {
	    	try{
	    		user.setFirstName(result.getAttributes().get(ldapConfigurator.getUserFirstNameAttribute()).get().toString());
	    	}catch(NullPointerException e){
	    		user.setFirstName("");
	    	}
	    }
	    if (ldapConfigurator.getUserLastNameAttribute() != null) {
	    	try{
	    		user.setLastName(result.getAttributes().get(ldapConfigurator.getUserLastNameAttribute()).get().toString());
	    	}catch(NullPointerException e){
	    		user.setLastName("");
	    	}
	    }
	    if (ldapConfigurator.getUserEmailAttribute() != null) {
	    	if(result.getAttributes().get(ldapConfigurator.getUserEmailAttribute())!=null)
	      user.setEmail(result.getAttributes().get(ldapConfigurator.getUserEmailAttribute()).get().toString());
	    }
	    if (ldapConfigurator.getNumber() != null) {
	    	try{
	      user.setTelephoneNumber(result.getAttributes().get(ldapConfigurator.getNumber()).get().toString());
	    	}catch(NullPointerException e){
	    		user.setTelephoneNumber("");
	    	}
	    	}
	    if (ldapConfigurator.getEmployeeid()!= null) {
	    	
		      user.setEmployeeNumber(result.getAttributes().get(ldapConfigurator.getEmployeeid()).get().toString());
		    }
	    /*if (ldapConfigurator.getGroupIdAttribute() != null) {
	    	if(result.getAttributes().get(ldapConfigurator.getGroupIdAttribute())!=null)
	      user.setTitle(result.getAttributes().get(ldapConfigurator.getGroupIdAttribute()).get().toString());
	    }*/
	  }
  @Override
  public long findUserCountByQueryCriteria(UserQueryImpl query) {
    return findUserByQueryCriteria(query, null).size(); // Is there a generic way to do counts in ldap?
  }


  @Override
  public List<Group> findGroupsByUser(String userId) {
    throw new ActivitiException("LDAP user manager doesn't support querying");
  }


  @Override
  public UserQuery createNewUserQuery() {
    return new UserQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor());
  }


  @Override
  public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
    throw new ActivitiException("LDAP user manager doesn't support querying");
  }


  @Override
  public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
    throw new ActivitiException("LDAP user manager doesn't support querying");
  }


  @Override
  public List<User> findPotentialStarterUsers(String proceDefId) {
    throw new ActivitiException("LDAP user manager doesn't support querying");
  }


  @Override
  public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
    throw new ActivitiException("LDAP user manager doesn't support querying");
  }


  @Override
  public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
    throw new ActivitiException("LDAP user manager doesn't support querying");
  }
  
  @Override
  public void setUserPicture(String userId, Picture picture) {
  	throw new ActivitiException("LDAP user manager doesn't support user pictures");
  }
  
  @Override
  public Picture getUserPicture(String userId) {
  	logger.debug("LDAP user manager doesn't support user pictures. Returning null");
  	return null;
  }

  @Override
  public Boolean checkPassword(final String userId, final String password) {
	  
	  // Extra password check, see http://forums.activiti.org/comment/22312
  	if (password == null || password.length() == 0) {
  		throw new ActivitiException("Null or empty passwords are not allowed!");
  	}
	  
    try {
      LDAPTemplate ldapTemplate = new LDAPTemplate(ldapConfigurator);
      return ldapTemplate.execute(new LDAPCallBack<Boolean>() {

        public Boolean executeInContext(InitialDirContext initialDirContext) {

          if (initialDirContext == null) {
            return false;
          }

          // Do the actual search for the user
          String userDn = null;
          try {

            String searchExpression = ldapConfigurator.getLdapQueryBuilder().buildQueryByUserId(ldapConfigurator, userId);
            String baseDn = ldapConfigurator.getUserBaseDn() != null ? ldapConfigurator.getUserBaseDn() : ldapConfigurator.getBaseDn();
            NamingEnumeration< ? > namingEnum = initialDirContext.search(baseDn, 
                    searchExpression, createSearchControls());
            
            while (namingEnum.hasMore()) { // Should be only one
              SearchResult result = (SearchResult) namingEnum.next();
              logger.info(" :: result ::: " + result);

              userDn = result.getNameInNamespace();
              logger.info(" :: userDn ::: " + userDn);
            }
            namingEnum.close();

          } catch (NamingException ne) {
            logger.info("Could not authenticate user " + userId + " : " + ne.getMessage(), ne);
            return false;
          }

          // Now we have the user DN, we can need to create a connection it
          // ('bind' in ldap lingo)
          // to check if the user is valid
          if (userDn != null) {
            InitialDirContext verificationContext = null;
            try {
              verificationContext = LDAPConnectionUtil.createDirectoryContext(ldapConfigurator, userDn, password);
            } catch (ActivitiException e) {
              // Do nothing, an exception will be thrown if the login fails
            }

            if (verificationContext != null) {
              LDAPConnectionUtil.closeDirectoryContext(verificationContext);
              return true;
            }
          }

          return false;

        }
      });

    } catch (ActivitiException e) {
      logger.info("Could not authenticate user : " + e);
      return false;
    }
  }

  protected SearchControls createSearchControls() {
    SearchControls searchControls = new SearchControls();
    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    searchControls.setTimeLimit(ldapConfigurator.getSearchTimeLimit());
    return searchControls;
  }

}
