package itsm.springGreetingsClient.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import itsm.springGreetingsClient.messages.SimpleGreetingsRequest;
import itsm.springGreetingsClient.messages.SimpleGreetingsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SpringGreetingsClient {

    private String hostName;
    private Integer port;
    private String clientName;
    private ObjectMapper objectMapper;
    private Socket clientSocket;

    @Autowired
    public SpringGreetingsClient(String hostName, Integer port, String clientName, ObjectMapper mapper) {
        this.objectMapper = mapper;
        this.hostName = hostName;
        this.port = port;
        this.clientName  = clientName;
    }

    @PostConstruct
    public void init() {
        try {
            InetAddress address = InetAddress.getByName(hostName);
            clientSocket = new Socket(address, port);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String sendRequest(String message) {
        String responseString = null;
        try {
            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            System.out.println("Trying to send message to server: " + message);
            SimpleGreetingsRequest request = new SimpleGreetingsRequest(clientName, message);
            String requestStr = objectMapper.writeValueAsString(request)+"\n";
            writer.write(requestStr);
            writer.flush();

            InputStream is = clientSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            responseString = br.readLine();
            SimpleGreetingsResponse response = objectMapper.readValue(responseString, SimpleGreetingsResponse.class);
            responseString = response.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return responseString;
        }
    }
}
