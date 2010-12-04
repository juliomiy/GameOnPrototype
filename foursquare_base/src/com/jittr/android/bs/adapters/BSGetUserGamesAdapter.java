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
		UserGame userGame = userGames.get(position);
    	Game game = new Game();
 
		if (null == convertView) {
			tli = (GameListItemLayout)View.inflate(context, R.layout.gamelistitem, null);
            tli.textView.setTextColor(userGame.getInitiatorFlag() ? this.context.getResources().getColor(R.color.GREEN) : this.context.getResources().getColor( R.color.BLUE));
		//	tli.setBackgroundColor(userGame.getInitiatorFlag() ? R.color.GREEN : R.color.BLUE);
//    		game.setEventname(userGames.get(position).getEventname() + (userGame.getInitiatorFlag() ? " initiator" : " subscriber"));
		} else {
			tli = (GameListItemLayout)convertView;
		}
   		game.setEventname(userGames.get(position).getEventname() + (userGame.getInitiatorFlag() ? " initiator" : " subscriber"));
		tli.setEvent(game);
		return tli;	
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

}
