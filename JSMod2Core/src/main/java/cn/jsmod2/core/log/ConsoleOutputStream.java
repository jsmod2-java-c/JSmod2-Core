package cn.jsmod2.core.log;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @ClassName: ConsoleOutputStream
 * @Description: OutputStream
 * @Author: GNX-Susanoo
 * @Date: 2019/7/20 19:39
 * @Version: 1.0
 */
public class ConsoleOutputStream extends OutputStream {
    private TextArea textArea;
    private int lineHeight = 100;
    private volatile boolean scroll = true;

    @Override
    public void write(int b) throws IOException {
        if (textArea == null) {
            return;
        }
        textArea.appendText(String.valueOf((char) b));
        cleanUpLin(textArea.getParagraphs());
        if (scroll) {
            textArea.setScrollTop(Double.MAX_VALUE);
        }
    }

    private void cleanUpLin(ObservableList<CharSequence> charSequencesList) {
        if (charSequencesList.size() > lineHeight + 1) {
            textArea.deleteText(0, charSequencesList.get(0).length() + 1);
            cleanUpLin(charSequencesList);
        }
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public boolean isScroll() {
        return scroll;
    }

    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
