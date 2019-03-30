package inputhandlinglistening.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputHandlingListening extends ApplicationAdapter implements InputProcessor
    {
        private static final Logger             LOGGER_INFO       = new Logger ( InputHandlingListening.class.getName () , Logger.INFO );
        private static final int                MAX_MESSAGE_COUNT = 15;
        private final        Array < String >   messages          = new Array < String > ();
        private              SpriteBatch        batch;
        private              OrthographicCamera camera;
        private              Viewport           viewport;
        private              BitmapFont         font;

        @Override
        public void create ()
            {
                camera = new OrthographicCamera ();
                viewport = new FitViewport ( 1080 , 720 , camera );
                batch = new SpriteBatch ();
                font = new BitmapFont ( Gdx.files.internal ( "oswald-32.fnt" ) );
                Gdx.input.setInputProcessor ( this );
            }

        @Override
        public void resize ( int width , int height )
            {
                viewport.update ( width , height , true );
            }

        @Override
        public void render ()
            {
                GdxUtils.clearScreen ();
                batch.setProjectionMatrix ( camera.combined );
                batch.begin ();
                for ( int i = 0; i < messages.size; i++ )
                    {
                        font.draw ( batch , ( ( i < 9 ) ? "0" : "" ) + ( i + 1 ) + ". " + messages.get ( i ) , 20.0f , 720 - 40.0f * ( i + 1 ) );
                    }
                batch.end ();
            }

        @Override
        public boolean keyDown ( int keycode )
            {
                String message = "keyDown keycode= " + keycode;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean keyUp ( int keycode )
            {
                String message = "keyUp keycode= " + keycode;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean keyTyped ( char character )
            {
                String message = "keyTyped keycode= " + character;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean touchDown ( int screenX , int screenY , int pointer , int button )
            {
                String message = "touchDown screenX= " + screenX + " screenY= " + screenY;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean touchUp ( int screenX , int screenY , int pointer , int button )
            {
                String message = "touchUp screenX= " + screenX + " screenY= " + screenY;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean touchDragged ( int screenX , int screenY , int pointer )
            {
                String message = "touchDragged screenX= " + screenX + " screenY= " + screenY;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean mouseMoved ( int screenX , int screenY )
            {
                String message = "mouseMoved screenX= " + screenX + " screenY= " + screenY;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        @Override
        public boolean scrolled ( int amount )
            {
                String message = "scrolled amount= " + amount;
                LOGGER_INFO.debug ( message );
                addMessage ( message );
                return true;
            }

        private void addMessage ( String message )
            {
                messages.add ( message );
                if ( messages.size > MAX_MESSAGE_COUNT )
                    {
                        messages.removeIndex ( 0 );
                    }
            }
    }
