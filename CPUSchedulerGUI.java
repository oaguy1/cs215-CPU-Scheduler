import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

public class CPUSchedulerGUI extends JFrame {
    private static JLabel current_exe, current_exe_label;
    private static JLabel queue0_blocks[];
    private static JLabel queue1_blocks[];
    private static JLabel queue2_blocks[];
    private static JPanel current, queue0, queue1, queue2, control;
    private static JButton start, quit;
    private static Border blackline;

    public CPUSchedulerGUI() {
        initComponents();
    }//CPUScheduler

    private void initComponents() {
        //construct border
        blackline = BorderFactory.createLineBorder(Color.black);

        //construct currently executing lables
        current = new JPanel();
        current_exe = new JLabel("", JLabel.CENTER);
        current_exe_label = new JLabel("currently executing thread:", JLabel.CENTER);

        //construct queue 0
        queue0 = new JPanel();
        queue0_blocks = new JLabel[6];
        for(int i = 0; i < 6; i++) {
            queue0_blocks[i] = new JLabel("", JLabel.CENTER);
        }//for

        //construct queue 1
        queue1 = new JPanel();
        queue1_blocks = new JLabel[6];
        for(int i = 0; i < 6; i++) {
            queue1_blocks[i] = new JLabel("", JLabel.CENTER);
        }//for

        //construct queue 2
        queue2 = new JPanel();
        queue2_blocks = new JLabel[6];
        for(int i = 0; i < 6; i++) {
            queue2_blocks[i] = new JLabel("", JLabel.CENTER);
        }//for

        //construct buttons
        control = new JPanel();
        start = new JButton("Start");
        quit = new JButton("Quit");

        //set main window properties
        setTitle("CPU Scheduler Emulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //layout root window frame
        GroupLayout root_layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(root_layout);
        root_layout.setVerticalGroup(
                root_layout.createSequentialGroup()
                .addComponent(current)
                .addComponent(queue0)
                .addComponent(queue1)
                .addComponent(queue2)
                .addComponent(control)
                );
        root_layout.setHorizontalGroup(
                root_layout.createSequentialGroup()
                .addGroup(root_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(current)
                    .addComponent(queue0)
                    .addComponent(queue1)
                    .addComponent(queue2)
                    .addComponent(control))
                );

        //layout currently executing thread
        current.add(current_exe_label);
        current.add(current_exe);
        current_exe.setBorder(blackline);
        current_exe.setPreferredSize(new Dimension(100, 20));

        //layout Queue 0
        queue0.setBorder(BorderFactory.createTitledBorder("Queue 0"));
        queue0.add(queue0_blocks[0]);
        queue0_blocks[0].setBorder(blackline);
        queue0_blocks[0].setPreferredSize(new Dimension(100, 20)); 
        queue0.add(queue0_blocks[1]);
        queue0_blocks[1].setBorder(blackline);
        queue0_blocks[1].setPreferredSize(new Dimension(100, 20));
        queue0.add(queue0_blocks[2]);
        queue0_blocks[2].setBorder(blackline);
        queue0_blocks[2].setPreferredSize(new Dimension(100, 20));
        queue0.add(queue0_blocks[3]);
        queue0_blocks[3].setBorder(blackline);
        queue0_blocks[3].setPreferredSize(new Dimension(100, 20));
        queue0.add(queue0_blocks[4]);
        queue0_blocks[4].setBorder(blackline);
        queue0_blocks[4].setPreferredSize(new Dimension(100, 20));
        queue0.add(queue0_blocks[5]);
        queue0_blocks[5].setBorder(blackline);
        queue0_blocks[5].setPreferredSize(new Dimension(100, 20));

        //layout Queue 1
        queue1.setBorder(BorderFactory.createTitledBorder("Queue 1"));
        queue1.add(queue1_blocks[0]);
        queue1_blocks[0].setBorder(blackline);
        queue1_blocks[0].setPreferredSize(new Dimension(100, 20)); 
        queue1.add(queue1_blocks[1]);
        queue1_blocks[1].setBorder(blackline);
        queue1_blocks[1].setPreferredSize(new Dimension(100, 20));
        queue1.add(queue1_blocks[2]);
        queue1_blocks[2].setBorder(blackline);
        queue1_blocks[2].setPreferredSize(new Dimension(100, 20));
        queue1.add(queue1_blocks[3]);
        queue1_blocks[3].setBorder(blackline);
        queue1_blocks[3].setPreferredSize(new Dimension(100, 20));
        queue1.add(queue1_blocks[4]);
        queue1_blocks[4].setBorder(blackline);
        queue1_blocks[4].setPreferredSize(new Dimension(100, 20));
        queue1.add(queue1_blocks[5]);
        queue1_blocks[5].setBorder(blackline);
        queue1_blocks[5].setPreferredSize(new Dimension(100, 20));

        //layout Queue 2
        queue2.setBorder(BorderFactory.createTitledBorder("Queue 2"));
        queue2.add(queue2_blocks[0]);
        queue2_blocks[0].setBorder(blackline);
        queue2_blocks[0].setPreferredSize(new Dimension(100, 20)); 
        queue2.add(queue2_blocks[1]);
        queue2_blocks[1].setBorder(blackline);
        queue2_blocks[1].setPreferredSize(new Dimension(100, 20));
        queue2.add(queue2_blocks[2]);
        queue2_blocks[2].setBorder(blackline);
        queue2_blocks[2].setPreferredSize(new Dimension(100, 20));
        queue2.add(queue2_blocks[3]);
        queue2_blocks[3].setBorder(blackline);
        queue2_blocks[3].setPreferredSize(new Dimension(100, 20));
        queue2.add(queue2_blocks[4]);
        queue2_blocks[4].setBorder(blackline);
        queue2_blocks[4].setPreferredSize(new Dimension(100, 20));
        queue2.add(queue2_blocks[5]);
        queue2_blocks[5].setBorder(blackline);
        queue2_blocks[5].setPreferredSize(new Dimension(100, 20));

        //layout controls
        control.add(start);
        control.add(quit);

        //register event handlers for buttons
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
               startScheduler(event); 
            }//actionPerformed
        });
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.exit(0);
            }//actionPerformed
        });

        pack();
    }//initComponents

    public void startScheduler(java.awt.event.ActionEvent event) {
        //do stuff
    }//startScheduler

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPUSchedulerGUI().setVisible(true);
            }
        });
    }//main

}//CPUSchedulerGUI
