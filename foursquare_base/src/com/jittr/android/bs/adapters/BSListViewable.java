/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.HashMap;

/**
 * @author juliomiyares
 *
 */
public interface BSListViewable <T> {
     public String getListViewText();
     public HashMap<String,String> getListViewArray();
}
