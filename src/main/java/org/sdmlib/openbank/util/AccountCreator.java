/*
   Copyright (c) 2017 FA
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.openbank.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.openbank.Account;
import de.uniks.networkparser.IdMap;
import org.sdmlib.openbank.User;
import org.sdmlib.openbank.Transaction;

import java.util.Date;

public class AccountCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Account.PROPERTY_BALANCE,
      Account.PROPERTY_ACCOUNTNUM,
      Account.PROPERTY_CREATIONDATE,
      Account.PROPERTY_OWNER,
      Account.PROPERTY_CREDIT,
      Account.PROPERTY_DEBIT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Account();
   }
   
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Account.PROPERTY_BALANCE.equalsIgnoreCase(attribute))
      {
         return ((Account) target).getBalance();
      }

      if (Account.PROPERTY_ACCOUNTNUM.equalsIgnoreCase(attribute))
      {
         return ((Account) target).getAccountnum();
      }

      if (Account.PROPERTY_CREATIONDATE.equalsIgnoreCase(attribute))
      {
         return ((Account) target).getCreationdate();
      }

      if (Account.PROPERTY_OWNER.equalsIgnoreCase(attribute))
      {
         return ((Account) target).getOwner();
      }

      if (Account.PROPERTY_CREDIT.equalsIgnoreCase(attribute))
      {
         return ((Account) target).getCredit();
      }

      if (Account.PROPERTY_DEBIT.equalsIgnoreCase(attribute))
      {
         return ((Account) target).getDebit();
      }
      
      return null;
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Account.PROPERTY_CREATIONDATE.equalsIgnoreCase(attrName))
      {
         ((Account) target).setCreationdate((Date) value);
         return true;
      }

      if (Account.PROPERTY_ACCOUNTNUM.equalsIgnoreCase(attrName))
      {
         ((Account) target).setAccountnum(Integer.parseInt(value.toString()));
         return true;
      }

      if (Account.PROPERTY_BALANCE.equalsIgnoreCase(attrName))
      {
         ((Account) target).setBalance(Double.parseDouble(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Account.PROPERTY_OWNER.equalsIgnoreCase(attrName))
      {
         ((Account) target).setOwner((User) value);
         return true;
      }

      if (Account.PROPERTY_CREDIT.equalsIgnoreCase(attrName))
      {
         ((Account) target).withCredit((Transaction) value);
         return true;
      }
      
      if ((Account.PROPERTY_CREDIT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Account) target).withoutCredit((Transaction) value);
         return true;
      }

      if (Account.PROPERTY_DEBIT.equalsIgnoreCase(attrName))
      {
         ((Account) target).withDebit((Transaction) value);
         return true;
      }
      
      if ((Account.PROPERTY_DEBIT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Account) target).withoutDebit((Transaction) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.openbank.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Account) entity).removeYou();
   }
}
