package com.petrsu.attt;

import android.graphics.Color;
import android.util.Log;

import com.badlogic.androidgames.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by lexer on 7/21/13.
 */
public class Settings {
    public static final String FILENAME = ".attt";
    public static boolean soundEnabled = true;
    public static int gridColor = Color.parseColor("#1a4780");

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(FILENAME)));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            Log.d("TEST", Integer.toString(gridColor));
            gridColor = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            //Do we have default values? Yes? OK! No? Make it!
        } catch (NumberFormatException e) {
            //Do nothing
        } finally {
            try {
                if(in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(FILENAME)));
            out.write(Boolean.toString(soundEnabled));
            out.write(Integer.toString(gridColor));
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
}
