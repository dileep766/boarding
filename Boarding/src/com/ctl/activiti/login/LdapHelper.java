package com.ctl.activiti.login;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

public class LdapHelper{
    private static final String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
    private static final String connectionURL = "ldaps://ldap.qintra.com:1636";
    private static final String connectionName = "CN=Query,CN=Users,DC=qintra,DC=com";
    private static final String connectionPassword = "Coolgear14";

    // Optioanl
    private static final String authentication = null;
    private static final String protocol = null;

    private static String username = "uid=vrouteop,ou=People,dc=mnet,dc=qintra,dc=com";

    private static final String MEMBER_OF = "CTLI_RX_DEV_TEAM";
    private static final String[] attrIdsToSearch = new String[] { MEMBER_OF };
    public static final String SEARCH_BY_SAM_ACCOUNT_NAME = "(sAMAccountName=%s)";
    public static final String SEARCH_GROUP_BY_GROUP_CN = "(&(objectCategory=group)(cn={0}))";
    private static String userBase = "ou=People,dc=mnet,dc=qintra,dc=com";

    public static void main(String[] args) throws NamingException {
        InitialLdapContext ctx = constructInitialLdapContext();
        // the name of the context to search
        String contextName = "ou=groups,o=CTLI_EBONDING_DEV_TEAM";
        // Filter expression
        String filterExpr = "(uniquemember={0})"; // selects the groups a user belongs to.
   
        // Filter parameters (name of the user)
        String userDN = "cn=Dilleswara Gudla,ou=people,o=sevenSeas";
        Object[] filterArgs = { userDN };
   
        SearchControls constraints = new javax.naming.directory.SearchControls();
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE); // SUBTREE_SCOPE means recursive search
   
        NamingEnumeration<SearchResult> search = ctx.search(contextName,
              filterExpr, filterArgs, constraints);
        while (search.hasMoreElements()) {
           System.out.println(search.next().getName());
        }
     }
   
     private static InitialLdapContext constructInitialLdapContext()
           throws NamingException {
        Properties env = new Properties();
        env.put("java.naming.factory.initial",
              "com.sun.jndi.ldap.LdapCtxFactory");
        // LDAP url
        env.put("java.naming.provider.url", "ldaps://ldap.qintra.com:1636");
        // ldap login
        env.put("java.naming.security.principal", "uid=vrouteop,ou=People,dc=mnet,dc=qintra,dc=com");
        env.put("java.naming.security.credentials", "Coolgear14");
   
        return new InitialLdapContext(env, null);
     }
}