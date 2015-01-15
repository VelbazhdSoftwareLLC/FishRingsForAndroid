package eu.veldsoft.fish.rings;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {
	private Rings rings = null;

	private ImageView views[] = {};

	private MediaPlayer beep02Player = null;

	private MediaPlayer hit01Player = null;

	private MediaPlayer cartoon007Player = null;

	private MediaPlayer cartoon012Player = null;

	private View.OnClickListener aRingClockwiseClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rings.ccwa();
			GameActivity.this.updateInfo();
		}
	};

	private View.OnClickListener aRingCounterClockwiseClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rings.cwa();
			GameActivity.this.updateInfo();
		}
	};

	private View.OnClickListener bRingClockwiseClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rings.ccwb();
			GameActivity.this.updateInfo();
		}
	};

	private View.OnClickListener bRingCounterClockwiseClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rings.cwb();
			GameActivity.this.updateInfo();
		}
	};

	private View.OnClickListener cRingClockwiseClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rings.ccwc();
			GameActivity.this.updateInfo();
		}
	};

	private View.OnClickListener cRingCounterClockwiseClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rings.cwc();
			GameActivity.this.updateInfo();
		}
	};

	private void updateInfo() {
		sound();
		repaint();
		congratulate();
	}

	private void congratulate() {
		if (rings.isDone() == true) {
			Toast.makeText(this, R.string.you_win, Toast.LENGTH_LONG).show();
		}
	}

	private void sound() {
		if (rings.isDone() == true) {
			beep02Player.start();
		} else {
			hit01Player.start();
		}
	}

	private void repaint() {
		int state[] = rings.getState();

		if (views.length != state.length) {
			return;
		}

		for (int i = 0; i < views.length; i++) {
			switch (state[i]) {
			case 1:
				views[i].setImageResource(R.drawable.red);
				break;
			case 2:
				views[i].setImageResource(R.drawable.green);
				break;
			case 3:
				views[i].setImageResource(R.drawable.blue);
				break;
			case 4:
				views[i].setImageResource(R.drawable.violet);
				break;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		beep02Player = MediaPlayer.create(this, R.raw.beep_02);
		hit01Player = MediaPlayer.create(this, R.raw.hit_01);
		cartoon007Player = MediaPlayer.create(this, R.raw.cartoon007);
		cartoon012Player = MediaPlayer.create(this, R.raw.cartoon012);

		ImageView views[] = { (ImageView) findViewById(R.id.imageView000),
				(ImageView) findViewById(R.id.imageView001),
				(ImageView) findViewById(R.id.imageView002),
				(ImageView) findViewById(R.id.imageView003),
				(ImageView) findViewById(R.id.imageView004),
				(ImageView) findViewById(R.id.imageView005),
				(ImageView) findViewById(R.id.imageView006),
				(ImageView) findViewById(R.id.imageView007),
				(ImageView) findViewById(R.id.imageView008),
				(ImageView) findViewById(R.id.imageView009),
				(ImageView) findViewById(R.id.imageView010),
				(ImageView) findViewById(R.id.imageView011),
				(ImageView) findViewById(R.id.imageView100),
				(ImageView) findViewById(R.id.imageView101),
				(ImageView) findViewById(R.id.imageView102),
				(ImageView) findViewById(R.id.imageView103),
				(ImageView) findViewById(R.id.imageView104),
				(ImageView) findViewById(R.id.imageView105),
				(ImageView) findViewById(R.id.imageView106),
				(ImageView) findViewById(R.id.imageView107),
				(ImageView) findViewById(R.id.imageView108),
				(ImageView) findViewById(R.id.imageView109),
				(ImageView) findViewById(R.id.imageView110),
				(ImageView) findViewById(R.id.imageView111),
				(ImageView) findViewById(R.id.imageView200),
				(ImageView) findViewById(R.id.imageView201),
				(ImageView) findViewById(R.id.imageView202),
				(ImageView) findViewById(R.id.imageView203),
				(ImageView) findViewById(R.id.imageView204),
				(ImageView) findViewById(R.id.imageView205),
				(ImageView) findViewById(R.id.imageView206),
				(ImageView) findViewById(R.id.imageView207),
				(ImageView) findViewById(R.id.imageView208),
				(ImageView) findViewById(R.id.imageView209),
				(ImageView) findViewById(R.id.imageView210),
				(ImageView) findViewById(R.id.imageView211), };
		this.views = views;

		findViewById(R.id.arrow01).setOnClickListener(aRingClockwiseClick);
		findViewById(R.id.arrow02).setOnClickListener(
				aRingCounterClockwiseClick);
		findViewById(R.id.arrow03).setOnClickListener(bRingClockwiseClick);
		findViewById(R.id.arrow04).setOnClickListener(
				bRingCounterClockwiseClick);
		findViewById(R.id.arrow05).setOnClickListener(cRingClockwiseClick);
		findViewById(R.id.arrow06).setOnClickListener(
				cRingCounterClockwiseClick);

		findViewById(R.id.imageView004).setOnClickListener(aRingClockwiseClick);
		findViewById(R.id.imageView005).setOnClickListener(aRingClockwiseClick);
		findViewById(R.id.imageView006).setOnClickListener(
				aRingCounterClockwiseClick);
		findViewById(R.id.imageView007).setOnClickListener(
				aRingCounterClockwiseClick);

		findViewById(R.id.imageView108).setOnClickListener(bRingClockwiseClick);
		findViewById(R.id.imageView109).setOnClickListener(bRingClockwiseClick);
		findViewById(R.id.imageView110).setOnClickListener(bRingClockwiseClick);
		findViewById(R.id.imageView105).setOnClickListener(
				bRingCounterClockwiseClick);
		findViewById(R.id.imageView106).setOnClickListener(
				bRingCounterClockwiseClick);
		findViewById(R.id.imageView107).setOnClickListener(
				bRingCounterClockwiseClick);

		findViewById(R.id.imageView208).setOnClickListener(cRingClockwiseClick);
		findViewById(R.id.imageView209).setOnClickListener(cRingClockwiseClick);
		findViewById(R.id.imageView210).setOnClickListener(cRingClockwiseClick);
		findViewById(R.id.imageView205).setOnClickListener(
				cRingCounterClockwiseClick);
		findViewById(R.id.imageView206).setOnClickListener(
				cRingCounterClockwiseClick);
		findViewById(R.id.imageView207).setOnClickListener(
				cRingCounterClockwiseClick);

		((ImageView) findViewById(R.id.ebinqoLogo))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						GameActivity.this.startActivity(new Intent(
								Intent.ACTION_VIEW, Uri.parse(getResources()
										.getString(R.string.ebinqo_url))));
					}
				});

		rings = new Rings(this.getWindow().getDecorView().getWidth(), this
				.getWindow().getDecorView().getHeight());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.reset_game:
			rings.init(0, 0);
			cartoon007Player.start();
			GameActivity.this.repaint();
			break;
		case R.id.shuffle_game:
			rings.shuffle();
			cartoon012Player.start();
			GameActivity.this.repaint();
			break;
		case R.id.help_game:
			GameActivity.this.startActivity(new Intent(GameActivity.this,
					HelpActivity.class));
			break;
		case R.id.about_game:
			GameActivity.this.startActivity(new Intent(GameActivity.this,
					AboutActivity.class));
			break;
		}
		return true;
	}
}
