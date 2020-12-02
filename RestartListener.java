package Lab9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {
    private SwingTicTacToe s;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        s.reset();
    }
    public void setTicTacToe(SwingTicTacToe s) {
        this.s = s;
    }
}
