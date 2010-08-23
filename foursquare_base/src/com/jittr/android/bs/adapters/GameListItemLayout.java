package com.jittr.android.bs.adapters;

import com.jittr.android.bs.dto.Game;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameListItemLayout extends LinearLayout {

	private TextView textView;
	private Game game;
	public GameListItemLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GameListItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		textView = (TextView)findViewById(android.R.id.text1);
		textView.setText("Game/Event Item");
	}
	public void setEvent(Game game) {
		this.game = game;
		textView.setText(game.getEventname());
	}

	public Game getEvent() {
	    return game;
	}
}
