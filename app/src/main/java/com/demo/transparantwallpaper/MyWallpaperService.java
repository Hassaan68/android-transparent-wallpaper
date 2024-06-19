package com.demo.transparantwallpaper;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

import java.io.IOException;

/* loaded from: classes.dex */
public class MyWallpaperService extends WallpaperService {
    public static final int STATE_NONE = 1;
    public static final int STATE_PLAYING = 4;
    public static final int STATE_PREPARING = 2;
    public static final int STATE_READY = 3;
    public static VideoSurfaceHolder mySurfaceHolder;
    public static int playstate;
    int a = 0;
    Camera b;
    boolean c;

    @Override // android.service.wallpaper.WallpaperService
    public Engine onCreateEngine() {
        return new VideoWallpaperEngine();
    }

    /* loaded from: classes.dex */
    public class VideoWallpaperEngine extends Engine {
        private MediaPlayer b;
        private int c;
        private int d;
        public int playstate;

        public VideoWallpaperEngine() {
            super();
            this.c = 0;
            this.d = 0;
            this.playstate = 1;
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        @RequiresApi(api = 16)
        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
            Log.e("TAG", "on surface created");
            this.b = new MediaPlayer();
            MyWallpaperService.mySurfaceHolder = new VideoSurfaceHolder(surfaceHolder);
            MyWallpaperService.mySurfaceHolder.addCallback(new a());
            MyWallpaperService.mySurfaceHolder.setType(3);
            this.b.setDisplay(MyWallpaperService.mySurfaceHolder);
            this.b.setVolume(0.0f, 0.0f);
            this.b.setLooping(true);
            Constants.show = false;
            try {
                MediaPlayer mediaPlayer = this.b;
                MyWallpaperService myWallpaperService = MyWallpaperService.this;
                mediaPlayer.setDataSource(myWallpaperService, Uri.parse("android.resource://" + MyWallpaperService.this.getPackageName() + "/" + Constants.wallpaper));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.b.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.demo.example.MyWallpaperService.VideoWallpaperEngine.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    Log.e("TAG", "on media player on prepared");
                    VideoWallpaperEngine.this.playstate = 3;
                    try {
                        VideoWallpaperEngine.this.a(true);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onVisibilityChanged(boolean z) {
            super.onVisibilityChanged(z);
            Log.d("Log", "onVisibilityChanged:$visible $playerState");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a(Boolean bool) {
            if (this.b == null || this.b.isPlaying() || bool.booleanValue() == this.b.isPlaying()) {
                return 0;
            }
            if (!bool.booleanValue()) {
                this.b.pause();
                this.playstate = 3;
            } else if (this.playstate == 3) {
                Log.d("AppLog", "ready, so starting to play");
                this.b.start();
                this.playstate = 4;
            } else if (this.playstate == 1) {
                Log.d("AppLog", "not ready, so preparing");
                this.playstate = 2;
            }
            return 0;
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.e("TAG", "on surface destroyed");
            super.onSurfaceDestroyed(surfaceHolder);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            MyWallpaperService.this.b = MyWallpaperService.this.a();
            MyWallpaperService.this.b.getParameters().getSupportedFocusModes().contains("auto");
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (MyWallpaperService.this.c) {
                MyWallpaperService.this.b.stopPreview();
                MyWallpaperService.this.c = false;
            }
            try {
                MyWallpaperService.this.b.setPreviewDisplay(surfaceHolder);
                MyWallpaperService.this.b.startPreview();
                MyWallpaperService.this.c = true;
                MyWallpaperService.b(Constants.act, MyWallpaperService.this.a, MyWallpaperService.this.b);
            } catch (Exception unused) {
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onDestroy() {
            super.onDestroy();
        }
    }

    /* loaded from: classes.dex */
    public class VideoSurfaceHolder implements SurfaceHolder {
        private SurfaceHolder b;

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(boolean z) {
        }

        public VideoSurfaceHolder(SurfaceHolder surfaceHolder) {
            this.b = surfaceHolder;
        }

        @Override // android.view.SurfaceHolder
        public void addCallback(Callback callback) {
            this.b.addCallback(callback);
        }

        @Override // android.view.SurfaceHolder
        public Surface getSurface() {
            return this.b.getSurface();
        }

        @Override // android.view.SurfaceHolder
        public Rect getSurfaceFrame() {
            return this.b.getSurfaceFrame();
        }

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return this.b.isCreating();
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas() {
            return this.b.lockCanvas();
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas(Rect rect) {
            return this.b.lockCanvas(rect);
        }

        @Override // android.view.SurfaceHolder
        public void removeCallback(Callback callback) {
            this.b.removeCallback(callback);
        }

        @Override // android.view.SurfaceHolder
        public void setFixedSize(int i, int i2) {
            this.b.setFixedSize(i, i2);
            this.b.setSizeFromLayout();
        }

        @Override // android.view.SurfaceHolder
        public void setFormat(int i) {
            this.b.setFormat(i);
        }

        @Override // android.view.SurfaceHolder
        public void setSizeFromLayout() {
            this.b.setSizeFromLayout();
        }

        @Override // android.view.SurfaceHolder
        public void setType(int i) {
            this.b.setType(3);
        }

        @Override // android.view.SurfaceHolder
        public void unlockCanvasAndPost(Canvas canvas) {
            this.b.unlockCanvasAndPost(canvas);
        }
    }

    /* loaded from: classes.dex */
    final class a implements SurfaceHolder.Callback {
        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }

        private a() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (MyWallpaperService.this.c) {
                MyWallpaperService.this.b.stopPreview();
                MyWallpaperService.this.c = false;
            }
            try {
                MyWallpaperService.this.b.setPreviewDisplay(surfaceHolder);
                MyWallpaperService.this.b.startPreview();
                MyWallpaperService.this.c = true;
                MyWallpaperService.b(Constants.act, MyWallpaperService.this.a, MyWallpaperService.this.b);
            } catch (Exception unused) {
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            MyWallpaperService.this.b = MyWallpaperService.this.a();
            MyWallpaperService.this.b.getParameters().getSupportedFocusModes().contains("auto");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Camera a() {
        Camera camera;
        RuntimeException e;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera camera2 = null;
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 0) {
                try {
                    camera = Camera.open(i);
                } catch (RuntimeException e2) {
                    camera = camera2;
                    e = e2;
                }
                try {
                    this.a = i;
                } catch (RuntimeException e3) {
                    e = e3;
                    Log.e("TAG", "Camera failed to open: " + e.getLocalizedMessage());
                    camera2 = camera;
                }
                camera2 = camera;
            }
        }
        return camera2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Activity activity, int i, Camera camera) {
        int i2;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        int i3 = 0;
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i3 = 90;
                break;
            case 2:
                i3 = 180;
                break;
            case 3:
                i3 = 270;
                break;
        }
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((cameraInfo.orientation + i3) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i3) + 360) % 360;
        }
        camera.setDisplayOrientation(i2);
    }
}
