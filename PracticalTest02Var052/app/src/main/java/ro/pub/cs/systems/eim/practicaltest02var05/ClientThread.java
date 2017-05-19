package ro.pub.cs.systems.eim.practicaltest02var05;

/**
 * Created by student on 19.05.2017.
 */
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ro.pub.cs.systems.eim.practicaltest02var05.Constants;
import ro.pub.cs.systems.eim.practicaltest02var05.Utilities;

public class ClientThread extends Thread
{
    private int port;
    private String value;
    private int minute;
    private TextView timeTextView;

    private Socket socket;

    public ClientThread(int port, String value, int minute, TextView timeTextView) {
        this.port = port;
        this.value = value;
        this.minute = minute;
        this.timeTextView = timeTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(value);
            printWriter.flush();
            printWriter.println(minute);
            printWriter.flush();
            String timeInformation;
            while ((timeInformation = bufferedReader.readLine()) != null) {
                final String finalizedTimeInformation = timeInformation;
                timeTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        timeTextView.setText(finalizedTimeInformation);
                    }
                });
            }
        } catch (IOException ioException)
        {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }
}
