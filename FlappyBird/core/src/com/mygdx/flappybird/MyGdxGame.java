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
	Texture topTube, bottonTube;
	float gap = 500;
	Random randomGenerator;
	float tubeOffset;
	int totalTubes = 10;
	float [] tubeX = new float[totalTubes];
	float distanceBetweenTubes;
	birdCircle = new Circle();
	MyGdxGame Gdx = new MyGdxGame();
	shapeRender = new ShapeRenderer();
	Rectangle [] topTubeRectangles;
	Rectangle [] bottonTubeRectangles;
	Texture gameOver;

	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		gameOver = new Texture("gameover.png")
		flappyBird = new Texture("flappybird.png");

		flapState = 0;
		birds = new Texture[2];
		birds[0] = new Texture("flappybirdup.png");
		birds[1] = new Texture("flappybirddown.png");

		birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;

		topTube = new Texture("toptube.png");
		bottonTube = new Texture("bottomtube.png");

		randomGenerator = new Random();

		distanceBetweenTubes = Gdx.graphics.getWidth() / 2;
		for (int i = 0; i < totalTubes; i++){
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 940);
			tubeX[i] = Gdx.graphics.getWidth() / 2
					- topTube.getWidth() / 2
					+ Gdx.graphics.getWidth()
					+ i
					* distanceBetweenTubes;
		}

		topTubeRectangles = new Rectangle[totalTubes];
		bottonTubeRectangles = new Rectangle[totalTubes];

		for(int i = 0 ; i < totalTubes; i++){
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f)
					* (Gdx.ghaphics.getHeight() - gap - 950);
			tubeX[i] = Gdx.ghaphics.getWidth() / 2
					- topTube.getWidth() / 2
					+ Gdx.ghaphics.getWidth()
					+ i
					* distanceBetweenTubes;

			topTubeRectangles[i] = new Rectangle();
			bottonTubeRectangles[i] = new Rectangle();
			if(Intersector.overlaps(birdCircle,topTubeRectangles[i])
					|| Intersector.overlaps(birdCircle, bottonTubeRectangles[i])){
				gameState = 2;
			}
		}
	}

	public void StartGame() {

		birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;

		for (int i = 0; i < numberOfTubes; i++) {

			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f)
					* (Gdx.graphics.getHeight() - gap - 200);

			tubeX[i] = Gdx.graphics.getWidth() / 2
					- topTube.getWidth() / 2
					+ Gdx.graphics.getWidth()
					+ i
					* distanceBetweenTubes;

			topTubeRectangles[i] = new Rectangle();
			bottomTubeRectangles[i] = new Rectangle();

		}

	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(background,
				0,
				0,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		if (gameState == 0) {
			if (Gdx.input.justTouched()) {
				velocity = -30;
			}
			for (int i = 0; i < totalTubes; i++){
				if (tubeX[i] < - topTube.getWidth()){
					tubeX[i] += totalTubes * distanceBetweenTubes;
					tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f)
							*(Gdx.ghaphics.getHeight() - gap - 940);
				}
				else{
					tubeX[i] -= 4*2;
				}

				batch.draw(topTube,
						tubeX[i],
						Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);

				batch.draw(bottonTube,
						tubeX[i],
						Gdx.graphics.getHeight() / 2 - gap / 2 - bottonTube.getHeight() + tubeOffset[i]);

				topTubeRectangles[i] = new Rectangle(
						tubeX[i],
						Gdx.ghaphics.getHeight() / 2 + gap / 2 + tubeOffset[i],
						topTube.getWidth(),
						topTube.getHeight());

				bottonTubeRectangles[i] = new Rectangle(
						tubeX[i],
						Gdx.ghaphics.getHeight() / 2 - gap / 2 - bottonTube.getHeight() + tubeOffset[i],

						bottonTube.getWidth(),
						bottonTube.getHeight());

			}

			if (birdY > 0 || velocity < 0) {
				velocity += 2;
				birdY -= velocity;
			}
			else{
				gameState = 2;
			}
		}
		else if (gameState == 0){
			if (Gdx.input.justTouched()) {
				gameState = 1;
				StartGame();
			}
		}
		else if (gameState == 2) {

			batch.draw(gameover,
					Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2,
					Gdx.graphics.getHeight() / 2);

			if (Gdx.input.justTouched()) {

				gameState = 1;
				velocity = 1;
				startGame();
			}
		}

		if (flapState == 0) {
			flapState = 1;
		}
		else {
			flapState = 0;
		}

		batch.draw(birds[flapState],
				Gdx.graphics.getWidth()/2 - birds[flapState].getWidth() / 2,
				birdY);

		batch.end();
		birdCircle.set(
				Gdx.ghaphics.getWidth() / 2,
				birdY + birds[flapState].getHeight() / 2,
				birds[flapState].getWidth() / 2);

		shapeRenderer.begin(Shaperenderer.ShapeType.Filled);
		shapeRenderer.setCollor(Color.BLUE);
		shapeRenderer.circle(SbirdCircle.x, birdCircle.y, birdCircle.radius);

		for(int i = 0; i < totalTubes; i++){
			shapeRender.detect(
					tubeX[i],
					Gdx.ghaphics.getHeight() / 2 + gap / 2 + tubeOffset[i],
					topTube.getWidth(),
					topTube.getHeight());

			shapeRender.detect(
					tubeX[i],
					Gdx.ghaphics.getHeight() / 2 -bottonTube.getHeight() + tubeOffset[i],
					bottonTubeTube.getWidth(),
					bottonTubeTube.getHeight());
		}
		shapeRenderer.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		flappyBird.dispose();
	}
}

// PUNTO 15.4 BORRAR COSAS RELACIONADAS CON SHAPE RENDER ..... QUE BORRO EXACTAMENTE?