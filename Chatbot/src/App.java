
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String args[]) {
        Bot b = new Bot();
    }
};

class Bot extends javax.swing.JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
    private static String last = "";
    private static String[] variables = new String[2];
    public Bot() {
        onCreate();
        this.setVisible(true);
        status.setVisible(true);
    }

    private String getBot(String q) {
        return "Bot :\n" + q+"\n";
    }
    private String getMe(String q) {
        return "Me :\n" + q+"\n\n";
    }

    private void hello(){
        chatArea.append(getBot("Hey there! \uD83D\uDE01 \nHow may i help you today?\nYou can ask me to:\n1. Schedule a flight\n2. Show flights\n3. Edit a flight\n"));
    }

    private void laterHello(){
        chatArea.append(getBot("Well that was done. What next!? \uD83D\uDE01 \nYou can ask me to:\n1. Schedule a flight\n2. Show flights\n3. Edit a flight\n"));
    }

    private String getJoke(){
        String[] arr = {"Where does a mountain climber keep his plane?\n" +
                "In a cliff-hangar.","Why do people take an instant dislike to flight attendants?\n" +
                "To save time later.","Will invisible airplanes ever be a thing?\n" +
                "I just can’t see them taking off.\n","What is the difference between God and an airline pilot?\n" +
                "God doesn’t think he’s an airline pilot."};
        return arr[(int)Math.random()*4];
    }

    private void init(){
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
    }

    private void setBounds(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(97, 1, 0));
        jPanel1.setForeground(new java.awt.Color(229, 119, 131));
        jPanel1.setLayout(null);

        jTextField1.setToolTipText("text\tType your message here...");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(10, 370, 410, 40);

        jButton1.setBackground(new java.awt.Color(229, 119, 131));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Send");
        jPanel1.add(jButton1);
        jButton1.setBounds(420, 370, 80, 40);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 490, 280);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 1, 38)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Airline Chatbot");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(120, 20, 300, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 419,
                                javax.swing.GroupLayout.PREFERRED_SIZE));

        setSize(new java.awt.Dimension(522, 455));
        setLocationRelativeTo(null);
    }
    private void handleButtonClick(){
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/schedule",
                            "root", "root123");
                    Statement stmt = con.createStatement();
                    String query = jTextField1.getText().toString();
                    chatArea.append(getMe(query));
                    if (query.equalsIgnoreCase("bye")) {
                        setVisible(false);
                        dispose();
                        return;
                    }
                    if (last.equals("schedule")) {
                        System.out.println("The query is" + query);
                        String[] arr = query.split(" ");
                        String q = "insert into airline values('" + arr[0] + "','" + arr[1] + "'," + arr[2] + "," + arr[3] + ");";
                        stmt.executeUpdate(q);
                        chatArea.append(getBot("The flight was scheduled!\n Don't forget to get a something good to eat from there! \uD83D\uDE02"));
                        jTextField1.setText("");
                        laterHello();

                        return;
                    }else if(last.equals("edit")){
                        String[] arr = query.split(" ");
                        String q = "update airline set fromLoc = '" + arr[2] + "', toLoc = '" + arr[3] + "' where fromLoc = '"+arr[0]+"' and toLoc = '"+arr[1]+"';";
                        stmt.executeUpdate(q);
                        chatArea.append(getBot("The flight schedule was changed! :)"));
                        jTextField1.setText("");
                        laterHello();

                        return;

                    }else if(last.equals("delete")){
                        String[] arr = query.split(" ");
                        String q = "delete from airline where fromLoc = '"+arr[0]+"' and toLoc = '"+arr[1]+"';";
                        stmt.executeUpdate(q);
                        chatArea.append(getBot("The flight was cancelled. Hope it was not cancelled coz of bankruptcy \uD83D\uDE02"));
                        jTextField1.setText("");
                        laterHello();

                        return;
                    }

                    if (query.contains("schedule")||query.contains("add")) {
                        last = "schedule";
                        chatArea.append(getBot("So you want to schedule a flight huh. Nice\nEnter the data (It should be space separated):\nFrom\nTo\nDeparture time\nArrival Time\n"));
                        jTextField1.setText("");
                    }else if(query.contains("edit")|| query.contains("change")){
                        last = "edit";
                        chatArea.append(getBot("Ok!!\nYou would like to edit some details of a flight. No problem.\nEnter the data (It should be space separated plz):\nFromOld\nToOld\nFromNew\nToNew\n"));
                    }else if(query.contains("remove")|| query.contains("delete")){
                        last = "delete";
                        chatArea.append(getBot("Sad \uD83D\uDE14 \nYou would like to cancel a flight.\nEnter the data (It should be space separated plz):\nFrom\nTo\n"));
                    }else if(query.contains("joke")){
                        chatArea.append(getBot(getJoke()));
                    }
                    jTextField1.setText("");
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
    private void onCreate() {
        init();
        setBounds();
        hello();
        handleButtonClick();
    }
}
