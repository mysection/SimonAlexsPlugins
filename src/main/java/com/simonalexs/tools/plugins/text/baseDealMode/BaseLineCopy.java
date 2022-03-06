package com.simonalexs.tools.plugins.text.baseDealMode;

import com.intellij.openapi.editor.VisualPosition;
import com.simonalexs.tools.plugins.text.base.BaseTextDeal;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: BaseLineCopy
 * @Description: TODO-wcy
 * @Author: wcy
 * @Date: 2022/3/6 18:07
 * @Version: 1.0
 */
public abstract class BaseLineCopy extends BaseTextDeal {
    @Override
    public String getText() {
        String text = caret.getEditor().getSelectionModel().getSelectedText();
        if (StringUtils.isEmpty(text)) {
            editor.getSelectionModel().selectLineAtCaret();
            text = editor.getSelectionModel().getSelectedText();
        }
        return text;
    }

    public void beforeWriteResultText(String resultText, String origionText) {
        int start = editor.getSelectionModel().getSelectionStart();
        userObjects.put("origionStartIndex", start);
    }

    public void afterWriteResultText(String resultText) {
        int start = (Integer)userObjects.get("origionStartIndex");
        editor.getSelectionModel().setSelection(start, start + resultText.length() - 1);
        VisualPosition end = editor.getSelectionModel().getSelectionEndPosition();
        editor.getCaretModel().moveToVisualPosition(end);
        editor.getSelectionModel().removeSelection();
    }

}
