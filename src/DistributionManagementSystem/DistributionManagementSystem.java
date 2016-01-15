/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistributionManagementSystem;

/**
 *
 * @author Nikith_Shetty
 */
public class DistributionManagementSystem {

    mainFrame frame;
    
    public void init() {
        frame = new mainFrame();
        frame.setVisible(true);
        //onn = frame
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        /* Set the Windows look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        DistributionManagementSystem n = new DistributionManagementSystem();
        n.init();
        
    }
    
}
