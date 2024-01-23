package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture flappyBird;
	Texture[] birds;
	float birdY = 0;
	float velocity = 0;
	int gameState = 0;
	int flapState = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		flappyBird = new Texture("flappybird.png");

		flapState = 0;
		birds = new Texture[2];
		birds[0] = new Texture("flappybirdup.png");
		birds[1] = new Texture("flappybirddown.png");

		birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2; // Corrected method name
	}

	@Override
	public void render() {

		if (gameState != 0) {
			if (Gdx.input.justTouched()) {
				velocity = -30;
			}
			if (birdY > 0 || velocity < 0) {
				velocity += 2;
				birdY -= velocity;
			}
		} else {
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		}
		if (flapState == 0) {
			flapState = 1;
		} else {
			flapState = 0;
		}

		batch.begin();
		batch.draw(background,
				0,
				0,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		batch.draw(birds[flapState],
				Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2,
				birdY);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		flappyBird.dispose();
	}
}
