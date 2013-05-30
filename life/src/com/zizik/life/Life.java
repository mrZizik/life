package com.zizik.life;



import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Life implements ApplicationListener, InputProcessor {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Map map;
	private Timer timer;
	
  
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		//camera = new OrthographicCamera(1, h/w);
		//batch = new SpriteBatch();
		
		map = new Map(32, 18, 6000);
		timer = new Timer();
		System.out.println(w+" "+h+" asdsads");
		
		
		
	}
	class UpdateTimeTask extends TimerTask {
		public void run() {
		 if (map.getState()==1) { map.update();
		   try {
			Thread.sleep(map.getDeltaTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}}
		}

	@Override
	public void dispose() {
		map.dispose();
		
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.justTouched()) {
			map.changeState(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
			//System.out.print();
			if (Gdx.input.getX()<56&&Gdx.input.getX()>0&&Gdx.input.getY()<30) {
				map.setState(1-map.getState());
			}
			if (Gdx.input.getX()<660&&Gdx.input.getX()>620&&Gdx.input.getY()<30) {
				map.clear();
			}
			if (Gdx.input.getX()<150&&Gdx.input.getX()>130&&Gdx.input.getY()<30) {
				if (map.getDeltaTime()<10000) {map.setDeltaTime(map.getDeltaTime()+1000);}
			}
			if (Gdx.input.getX()<180&&Gdx.input.getX()>160&&Gdx.input.getY()<30) {
				if (map.getDeltaTime()>1000) {map.setDeltaTime(map.getDeltaTime()-1000);}
			}
			if (Gdx.input.getX()<Gdx.graphics.getWidth()&&Gdx.input.getX()>Gdx.graphics.getWidth()-60&&Gdx.input.getY()<30) {
				Gdx.app.exit();
			}
			System.out.println(Gdx.input.getX());
		}
		timer.schedule(new UpdateTimeTask(), 3000, map.getDeltaTime());
		
		//System.out.println("delta: "+map.getDeltaTime());
		map.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		map.setState(0);
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		map.changeState(screenX, screenY);
		System.out.print("click");
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
