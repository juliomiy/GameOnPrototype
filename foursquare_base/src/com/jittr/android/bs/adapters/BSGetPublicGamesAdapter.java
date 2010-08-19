package com.jittr.android.bs.adapters;

import java.util.ArrayList;

import com.jittr.android.R;
import com.jittr.android.fs.dto.Game;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BSGetPublicGamesAdapter extends BaseAdapter {

	private ArrayList<Game> games;
	private Context context;
	public BSGetPublicGamesAdapter() {
	}
	
    public BSGetPublicGamesAdapter(Context context, ArrayList<Game> games) {
    	this.context = context;
    	this.games = games;
    }
	@Override
	public int getCount() {
		return (null == games) ? null : games.size();
	}

	@Override
	public Game getItem(int position) {
		return (null == games) ? null : games.get(position);	
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GameListItemLayout tli;
		if (null == convertView) {
			tli = (GameListItemLayout)View.inflate(context, R.layout.gamelistitem, null);
		} else {
			tli = (GameListItemLayout)convertView;
		}
		tli.setEvent(games.get(position));
		return tli;	
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

}
