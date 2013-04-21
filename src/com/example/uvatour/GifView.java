package com.example.uvatour;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.SystemClock;
import android.view.View;

public class GifView extends View {

	Movie movie,movie1;
	InputStream is;
	long moviestart;

	public GifView(Context context, InputStream stream) {
		super(context);
		is = stream;
		movie = Movie.decodeStream(is);
		
	}

	@Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        final long now = SystemClock.uptimeMillis();

        if (moviestart == 0) {
            moviestart = now;
        }
        final int relTime = (int) ((now - moviestart) % movie.duration());
        movie.setTime(relTime);
        movie.draw(canvas, 10, 10);
        this.invalidate();
    }
}
