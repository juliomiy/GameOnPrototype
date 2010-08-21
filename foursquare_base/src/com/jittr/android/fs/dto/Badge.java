package com.jittr.android.fs.dto;

public class Badge {


    
	private String mDescription;
    private String mIcon;
    private String mId;
    private String mName;
    
    
    public Badge() {
		
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getmIcon() {
		return mIcon;
	}

	public void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	@Override
	public String toString() {
		return "Badge [mDescription=" + mDescription + ", mIcon=" + mIcon
				+ ", mId=" + mId + ", mName=" + mName + "]";
	}
}
