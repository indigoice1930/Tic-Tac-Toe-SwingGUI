package Lab9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndingListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
