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

package com.ctl.activiti.login;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.identity.Authentication;
import org.springframework.beans.factory.annotation.Autowired;



public class DefaultLoginHandler implements LoginHandler {
	
  private IdentityService identityService;
  public LoggedInUserImpl authenticate(String userName, String password) {
	    LoggedInUserImpl loggedInUser = null;
	    if (identityService.checkPassword(userName, password)) {
	      User user = identityService.createUserQuery().userId(userName).singleResult();

	      // Fetch and cache user data
	      loggedInUser = new LoggedInUserImpl(user, password);
	    
	      List<Group> groups = identityService.createGroupQuery().groupMember(user.getId()).list();
	      for (Group group : groups) {
	        if (Constants.SECURITY_ROLE.equals(group.getType())) {
	          loggedInUser.addSecurityRoleGroup(group);
	          if (Constants.SECURITY_ROLE_USER.equals(group.getId())) {
	            loggedInUser.setUser(true);
	          }
	          if (Constants.SECURITY_ROLE_ADMIN.equals(group.getId())) {
	            loggedInUser.setAdmin(true);
	          }
	        } else {
	          loggedInUser.addGroup(group);
	        }
	      }
	    }
	    
	    return loggedInUser;
	  }
	  
	  public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
	    // Noting to do here
	  }

	  public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
	    // Noting to do here
	  }
	  
	  public LoggedInUser authenticate(HttpServletRequest request, HttpServletResponse response) {
	    // No automatic authentication is used by default, always through credentials.
	    return null;
	  }
	  
	  public void logout(LoggedInUser userToLogout) {
	    // Clear activiti authentication context
	    Authentication.setAuthenticatedUserId(null);
	  }
	  
	  public void setIdentityService(IdentityService identityService) {
	    this.identityService = identityService;
	  }

 

}
