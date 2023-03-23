import Network.ConnectURI;
import Model.ResponseModel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ResponseGUI {
    private JPanel mainpanel;
    private JTextField fieldmsg;
    private JButton btnclose;
    private JTextField fieldsts;
    private JTextField fieldcmt;
    private JButton btncek;
    private JLabel labelmsg;
    private JLabel labelsts;
    private JLabel labelcmt;

    public ResponseGUI(){
        btncek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConnectURI koneksisaya = new ConnectURI();
                    URL myAddres = koneksisaya.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
                    String response = null;
                    try {
                        response = koneksisaya.getResponseFromHttpUrl(myAddres);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println(response);

                    JSONArray responseJSON = new JSONArray(response);
                    ArrayList<ResponseModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        ResponseModel resModel = new ResponseModel();
                        JSONObject myJSONObject = responseJSON.getJSONObject(i);
                        resModel.setMsg(myJSONObject.getString("message"));
                        resModel.setComment(myJSONObject.getString("status"));
                        resModel.setStatus(myJSONObject.getString("comment"));
                        responseModel.add(resModel);}
                    for (int index = 0; index < responseModel.size(); index++) {
                        fieldmsg.setText(responseModel.get(index).getMsg());
                        fieldsts.setText(responseModel.get(index).getStatus());
                        fieldcmt.setText(responseModel.get(index).getComment());

                    }
                } catch (RuntimeException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldmsg.setText("");
                fieldsts.setText("");
                fieldcmt.setText("");
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ResponseGUI");
        frame.setContentPane(new ResponseGUI().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}