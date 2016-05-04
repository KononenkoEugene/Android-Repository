package com.example.eugenekon.rgbcircles;

/**
 * Created by Eugenekon on 07.04.2016.
 */
public interface ICanvasView {

        void drawCircle(SimpleCircle circle);

        void redraw();

        void showMessage(String text);
    }


