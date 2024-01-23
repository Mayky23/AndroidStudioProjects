package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture flappyBird;
	Texture [] birds;

	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		flappyBird = new Texture("flappybird.png");

		int flapstate = 0;
		birds = new Texture[2];
		birds[0] = new Texture("flappybirdup.png");
		birds[1] = new Texture("flappybirddown.png");
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(flappyBird,
				Gdx.graphics.getWidth() / 2 - flappyBird.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - flappyBird.getHeight() / 2);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		flappyBird.dispose();
	}
}
