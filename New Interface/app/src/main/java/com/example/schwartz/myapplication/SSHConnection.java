package com.example.schwartz.myapplication;

/**
 * imports
 */
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ChannelExec;
import java.io.InputStream;

/**
 * WHAT DOES THIS DO? ***********************************************************************
 */
public class SSHConnection {
    /**
     * Initiations
     */
    String username;
    String password;
    String host;
    int portNum;
    Session session;
    Channel channel;

    /**
     * Constructor
     * @param username
     * @param password
     * @param host
     * @param portNum
     */
    public SSHConnection(String username, String password, String host, int portNum) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.portNum = portNum;
    }

    /**
     * WHAT DOES THIS DO? ***********************************************************************
     * @param cmd
     * @return
     */
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
