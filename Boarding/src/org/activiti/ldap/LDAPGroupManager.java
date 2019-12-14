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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

/**
 * Implementation of the {@link GroupIdentityManager} interface specifically for LDAP.
 * 
 * Note that only a few methods are actually implemented, as many of the operations 
 * (save, update, etc.) are done on the LDAP system directly. 
 * 
 * @author Joram Barrez
 */
public class LDAPGroupManager extends AbstractManager implements GroupIdentityManager {

  protected LDAPConfigurator ldapConfigurator;
  protected LDAPGroupCache ldapGroupCache;
  
	public LDAPGroupManager(LDAPConfigurator ldapConfigurator) {
		this.ldapConfigurator = ldapConfigurator;
	}
	
	public LDAPGroupManager(LDAPConfigurator ldapConfigurator, LDAPGroupCache ldapGroupCache) {
	  this.ldapConfigurator = ldapConfigurator;
	  this.ldapGroupCache = ldapGroupCache;
	}

  @Override
  public Group createNewGroup(String groupId) {
	  GroupEntity group = new GroupEntity();
			//Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("insert into act_id_group (ID_,REV_) values ('" +groupId+"','1')");
			
			group.setId(groupId);
			group.setRevision(1);
		

	  return group;
   // throw new ActivitiException("LDAP group manager doesn't support creating a new group");
  }

  @Override
  public void insertGroup(Group group) {
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  Connection con = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection();
	  try {
		  
			ps = con.prepareStatement("insert into act_id_group (ID_,REV_,NAME_,TYPE_) values (?,?,?,?)");
			ps.setString(1, group.getId());
			ps.setInt(2, ((GroupEntity) (group)).getRevision());
			ps.setString(3, group.getName());
			ps.setString(4, group.getType());

			ps.execute();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//  	throw new ActivitiException("LDAP group manager doesn't support inserting or updating a group");
  
  
  }

  @Override
  public void updateGroup(Group updatedGroup) {
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  Connection con = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection();
	  try {
		  
		  	ps = con.prepareStatement("update act_id_group set  REV_ = ?, NAME_=?, TYPE_=? where ID_=?");
			ps.setInt(1, ((GroupEntity) (updatedGroup)).getRevision()+1);
			ps.setString(2, updatedGroup.getName());
			ps.setString(3, updatedGroup.getType());
			ps.setString(4, updatedGroup.getId());

			ps.execute();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//  	throw new ActivitiException("LDAP group manager doesn't support inserting or updating a group");
  
  
  }
  
  @Override
  public boolean isNewGroup(Group group) {
	  ResultSet rs = null;
	  try {
			rs = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("select * from act_id_group where ID_='"+group.getId()+"'");
			
			if(rs.next())
			{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
return true;
//  	throw new ActivitiException("LDAP group manager doesn't support inserting or updating a group");
  }

  @Override
  public void deleteGroup(String groupId) {
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  Connection con = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection();
	  try {
		  
		  	ps = con.prepareStatement("delete from act_id_group where ID_= ?");
			ps.setString(1, groupId);
			ps.execute();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//  	throw new ActivitiException("LDAP group manager doesn't support inserting or updating a group");
  
  
  }

  @Override
  public GroupQuery createNewGroupQuery() {
    return new GroupQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor());
  }

  @Override
  public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
    // Only support for groupMember() at the moment
	  
	String user = Authentication.getAuthenticatedUserId();
	if(query.getId()!=null)
	{ String groupId = query.getId();
		 GroupEntity group = new GroupEntity();
		    List<Group> groups = new ArrayList<Group>();
		 try {
			ResultSet rs1 = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("select * from act_id_group where id_='"+groupId+"'");
			if(rs1!=null && rs1.next())
			{
				group = new GroupEntity();
				group.setId(rs1.getString(1));
				group.setName(rs1.getString(3));
				group.setRevision(rs1.getInt(2));
				group.setType(rs1.getString(4));
				groups.add(group);	
				}
			rs1.close();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //	return findGroupByQueryCriteria(query, null).size(); // Is there a generic way to do a count(*) in ldap?
	  
		return groups;
	}
	else
    if (query.getUserId() != null) {
      return findGroupsByUser(query.getUserId());
    } else {
    	 return findGroupsByUser("");
    	 
    	
    //  throw new ActivitiIllegalArgumentException("This query is not supported by the LDAPGroupManager");
    }
	
  }

  @Override
  public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
	  int count =0;
	 try {
		ResultSet rs = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("select count(*) from act_id_group");
		if(rs!=null && rs.next())
		{
			count=rs.getInt(1);
		}
		rs.close();
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count; 
   //	return findGroupByQueryCriteria(query, null).size(); // Is there a generic way to do a count(*) in ldap?
  }

  @Override
  public List<Group> findGroupsByUser(final String userId) {
    
    // First try the cache (if one is defined)
    if (ldapGroupCache != null) {
      List<Group> groups = ldapGroupCache.get(userId);
      if (groups != null) {
        return groups;
      }
    }
    GroupEntity group = new GroupEntity();
    List<Group> groups = new ArrayList<Group>();
    String groupId="";
    try {
    	ResultSet rs=null;
    	if(!userId.equalsIgnoreCase(""))
    		rs = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("select group_id_ from act_id_membership where user_id_ ='"+userId+"'");
    	else
    	{
    		 rs = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("select id_ from act_id_group");

    	}
    	ResultSet rs1 =null;
		while(rs.next())
		{
			groupId=rs.getString(1);
			rs1 = Context.getCommandContext().getDbSqlSession().getSqlSession().getConnection().createStatement().executeQuery("select * from act_id_group where id_='"+groupId+"'");
			if(rs1.next())
			{
				group = new GroupEntity();
				group.setId(rs1.getString(1));
				group.setName(rs1.getString(3));
				group.setRevision(rs1.getInt(2));
				group.setType(rs1.getString(4));
				groups.add(group);
			}
		}
		
		rs.close();
		if(rs1!=null)
		rs1.close();
		
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return groups;
    
/*    // Do the search against Ldap
    LDAPTemplate ldapTemplate = new LDAPTemplate(ldapConfigurator);
    return ldapTemplate.execute(new LDAPCallBack<List<Group>>() {
      
      public List<Group> executeInContext(InitialDirContext initialDirContext) {
        
        String searchExpression = ldapConfigurator.getLdapQueryBuilder().buildQueryGroupsForUser(ldapConfigurator, userId);
        
        List<Group> groups = new ArrayList<Group>();
        try {
          String baseDn = ldapConfigurator.getGroupBaseDn() != null ? ldapConfigurator.getGroupBaseDn() : ldapConfigurator.getBaseDn();
          NamingEnumeration< ? > namingEnum = initialDirContext.search(baseDn, searchExpression, createSearchControls());
          while (namingEnum.hasMore()) { // Should be only one
            SearchResult result = (SearchResult) namingEnum.next();
            
            GroupEntity group = new GroupEntity();
            if (ldapConfigurator.getGroupIdAttribute() != null) {
              group.setId(result.getAttributes().get(ldapConfigurator.getGroupIdAttribute()).get().toString());
            }
            if (ldapConfigurator.getGroupNameAttribute() != null) {
              group.setName(result.getAttributes().get(ldapConfigurator.getGroupNameAttribute()).get().toString());
            }
            if (ldapConfigurator.getGroupTypeAttribute() != null) {
              group.setType(result.getAttributes().get(ldapConfigurator.getGroupTypeAttribute()).get().toString());
            }
            groups.add(group);
          }
          
          namingEnum.close();
          
          // Cache results for later
          if (ldapGroupCache != null) {
            ldapGroupCache.add(userId, groups);
          }
          
          return groups;
          
        } catch (NamingException e) {
          throw new ActivitiException("Could not find groups for user " + userId, e);
        }
      }
      
    });*/
  }

  @Override
  public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
    throw new ActivitiException("LDAP group manager doesn't support querying");
  }

  @Override
  public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
    throw new ActivitiException("LDAP group manager doesn't support querying");
  }
  
  protected SearchControls createSearchControls() {
    SearchControls searchControls = new SearchControls();
    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    searchControls.setTimeLimit(ldapConfigurator.getSearchTimeLimit());
    return searchControls;
  }
	
}
