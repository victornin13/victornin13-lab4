import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {
    
    private JTextField pos;
    private JComboBox<Integer> Inner;
    private JComboBox<Integer> Middle;
    private JComboBox<Integer> Outer;
    private JTextArea Input;
    private JTextArea Output;

    private final Integer[] num = {
        1,2,3,4,5
    };

    private final String message ="";

    private final String letters = "";

    private boolean isEncrypted = false;

    

    public EnigmaFrame(){


        //middle panel
        JPanel Mpanel = new JPanel(new BorderLayout()); 
        
        //Bottom Panel
        JPanel Bpanel = new JPanel(new BorderLayout()); 

        JToggleButton Encrypt = new JToggleButton("Encrypt");
        JToggleButton Decrypt = new JToggleButton("Decrypt");
        

        pos = new JTextField(letters, 3);
        pos.setPreferredSize(new Dimension(50, 20));

        

        Inner = new JComboBox<Integer>(num);
        Middle = new JComboBox<Integer>(num);
        Outer = new JComboBox<Integer>(num);

        Input = new JTextArea(message);
        Input.setPreferredSize(new Dimension(200, 100));
        Output = new JTextArea(message);
        Output.setPreferredSize(new Dimension(200, 100));
        Output.setEditable(false);

        JPanel dpanel = new JPanel(new FlowLayout()); 
        dpanel.add(new JLabel("Inner: "));
        dpanel.add(Inner);
        dpanel.add(new JLabel("Middle: "));
        dpanel.add(Middle);
        dpanel.add(new JLabel("Outer: "));
        dpanel.add(Outer);
        dpanel.add(new JLabel("Initial Position: "));
        dpanel.add(pos);
        dpanel.add(Encrypt);
        dpanel.add(Decrypt);


        Mpanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        Mpanel.add(Input, BorderLayout.CENTER);
        Mpanel.add(new JLabel("Input:  "), BorderLayout.WEST);
        Bpanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        Bpanel.add(new JLabel("Output: "), BorderLayout.WEST);
        Bpanel.add(Output, BorderLayout.CENTER);

        add(dpanel, BorderLayout.NORTH);
        add(Mpanel, BorderLayout.CENTER);
        add(Bpanel, BorderLayout.SOUTH);
        

        ConverterActionListener a = new ConverterActionListener();
        
        pos.addActionListener(a); //change the number in from (when enter is hit)
        Inner.addActionListener(a);//change the Inner Rotor Value
        Middle.addActionListener(a); //change the Middle Rotor Value
        Outer.addActionListener(a); //change the Outer Rotor Value
        
        //boolean isEncrypt = false;

         //encrypts message from the input displays the encrypted message in the output
        Encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isEncrypted = true;
                TestOutput();
                
            }
        });
         //decrypts message from the input and displays the decrypted message in the output
        Decrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isEncrypted = false;
                TestOutput();
               
            }
        });
        
        
        pack();
        setTitle("Enigma GUI");
        setSize(1000,500);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void TestOutput() {
        boolean isEncrypted = this.isEncrypted;
    
        // Read what the input is
        String originalMessage = Input.getText();
    
        // If the input is not empty
        if (!originalMessage.isEmpty()) {
            // Keep track of the rotor positions
            int inner = (int) Inner.getSelectedItem();
            int middle = (int) Middle.getSelectedItem();
            int outer = (int) Outer.getSelectedItem();
            //String initialPosition = pos.getText();
            Enigma enigma = new Enigma(inner, middle, outer);
    
            StringBuilder outputMessage = new StringBuilder();
    
            for (char c : originalMessage.toCharArray()) {
                if (isEncrypted) {
                    outputMessage.append(enigma.encryptChar(c));
                } else {
                    outputMessage.append(enigma.decryptChar(c));
                }
            }
    
            // Set the output message in the Output text area
            Output.setText(outputMessage.toString());
        } else {
            // Clear the output if the input is empty
            Output.setText("");
        }
    }
 

private class ConverterActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        TestOutput();;
}
}
}
