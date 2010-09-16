/**
 * 
 */
package com.jittr.android.bs.adapters;

import java.util.ArrayList;

import com.jittr.android.R;
import com.jittr.android.bs.dto.Game;
import com.jittr.android.bs.dto.UserGame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author juliomiyares
 *
 */
public class BSGetUserGamesAdapter extends BaseAdapter {

	private ArrayList<UserGame> userGames;
	private Context context;

   public BSGetUserGamesAdapter(Context context, ArrayList<UserGame> games) {
    	this.context = context;
    	userGames = games;
    } //constructor

	/**
	 * 
	 */
	public BSGetUserGamesAdapter() {
	}  //default Constructor

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return (null == userGames) ? null : userGames.size();
	} //getCount

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public UserGame getItem(int position) {
		return (null == userGames) ? null : userGames.get(position);	
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GameListItemLayout tli;
		if (null == convertView) {
			tli = (GameListItemLayout)View.inflate(context, R.layout.gamelistitem, null);
		} else {
			tli = (GameListItemLayout)convertView;
		}
		Game game = new Game();
		game.setEventname(userGames.get(position).getEventname());
		tli.setEvent(game);
		return tli;	
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

}
