/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.Adp_properties_server.dbconnection;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author emile
 */
public class Server extends JFrame{
        //variables        
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;     
    
    //constructor
    public Server()
    {
        super("Server");
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    sendMessage(event.getActionCommand());
                }
            }
        );
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300,150);
        setVisible(true);
    }
    
    //setting up and run the server
    public void startRunning()
    {
        try{
            server = new ServerSocket(6789, 100);
            while(true){
            try{
                waitForConnection();
                setupStreams();
                whileChatting();                
            }catch(EOFException eofException)
            {
                showMessage("\n Server ended the connection");
            }finally
            {
                closeCrap();
            }
        }  
        }catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    //waiting for connection, to display the data
    private void waitForConnection() throws IOException
    {
        showMessage("Waiting for connection... \n");
        connection = server.accept();
        showMessage("Now connected to " + connection.getInetAddress().getHostAddress());
    }
    
    //get streams to send and recieve data
    public void setupStreams()throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now setup! \n");
    }
    
    //during the chat conversation
    private void whileChatting() throws IOException
    {
        String message = "You are now connected ";
        showMessage(message);
        ableToType(true);
        do{
            try{
                message = (String) input.readObject();
                showMessage("\n" + message);
            }catch(ClassNotFoundException classNotfoundException)
            {
                showMessage("\n i dont know where it sent to");
            }
        }while(!message.equals("Client - End"));
    }
    
    //close streams and sockets after you are done chatting
    private void closeCrap()
    {
        showMessage("/n Closing connections... \n");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    //send a message to the client
    private void sendMessage(String message)
    {
        try{
            output.writeObject("Server -" + message);
            output.flush();
            showMessage("\n Server -" + message);
        }catch(IOException ioException){
            chatWindow.append("\n ERROR, cant send message");
        } 
    }
    
    //update chatWindow
    private void showMessage(final String text)
    {
        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    chatWindow.append(text);
                }
            }
        );
    }
    
    //let user type stuff in the box
    private void ableToType(final boolean tof)
    {
        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    userText.setEditable(tof);
                }
            }
        );
    }
}
