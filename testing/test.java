import javax.swing.JFrame;

public class test {
    public static void main(String []args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2d adv");

        gp game =new gp();
        window.add(game);

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        game.startGameThread();
    }
}
