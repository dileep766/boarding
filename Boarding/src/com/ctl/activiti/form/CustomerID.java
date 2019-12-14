package com.ctl.activiti.form;

import java.util.HashMap;
import java.util.Map;

public class CustomerID {
	  private long crmID;
	  private int nameSpace;

	  public CustomerID(long crmID, int nameSpace) {
	    super();
	    this.crmID = crmID;
	    this.nameSpace = nameSpace;
	  }

	  public boolean equals(Object obj) {
	    //null instanceof Object will always return false
	    if (!(obj instanceof CustomerID))
	      return false;
	    if (obj == this)
	      return true;
	    return  this.crmID == ((CustomerID) obj).crmID &&
	            this.nameSpace == ((CustomerID) obj).nameSpace;
	  }

	  public int hashCode() {
	    int result = 0;
	    result = (int)(crmID/12) + nameSpace;
	    return result;
	  }

	  public static void main(String[] args) {
	    Map m = new HashMap();
	    m.put(new CustomerID(2345891234L,0),"Jeff Smith");
	    m.put(new CustomerID(2345891234L,0),"Jeff Smith");
	    System.out.println(m.size());
	  }

	}