package com.simonalexs.tools.plugins.text.baseDealMode;

import com.simonalexs.tools.plugins.text.base.BaseTextDeal;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: BaseTextReplace
 * @Description: TODO-wcy
 * @Author: wcy
 * @Date: 2022/3/6 18:31
 * @Version: 1.0
 */
public abstract class BaseTextReplace extends BaseTextDeal {
    @Override
    public String getText() {
        String text = caret.getEditor().getSelectionModel().getSelectedText();
        if (StringUtils.isEmpty(text)) {
            editor.getSelectionModel().selectWordAtCaret(true);
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
        editor.getSelectionModel().setSelection(start, start + resultText.length());
    }
}
