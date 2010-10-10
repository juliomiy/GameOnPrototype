package com.jittr.android.api.betsquared;

public interface DataFetchingCallBack {

	 public void preDataLoading();
	 public void dataLoading();
	 public void dataLoadCancelled();
	 public void dataLoaded(Object response);
	 public void dataLoadException(String message);
}
