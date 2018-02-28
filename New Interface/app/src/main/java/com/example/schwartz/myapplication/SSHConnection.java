package com.example.schwartz.myapplication;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageButton;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ChannelExec;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by Harvey on 2/25/2018.
 */

public class SSHConnection {
    String username;
    String password;
    String host;
    int portNum;
    Session session;
    Channel channel;


    public SSHConnection(String username, String password, String host, int portNum) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.portNum = portNum;
    }
    public InputStream command(String cmd) {
        InputStream is = null;
        InputStream in = null;
        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jSch = new JSch();
            session = jSch.getSession(username, host, portNum);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(cmd);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            in = channel.getInputStream();
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            is = sftp.get("desmet.bmp");


            /*
            byte[] tmp = new byte[1024];
            while (true){
                while(in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if(i<0)
                        break;
                    System.out.print(new String(tmp, 0 , i));
                }
                if (channel.isClosed()){
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);} catch (Exception ee ){}
            }
            */
            sftp.disconnect();
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return in;
    }


}
