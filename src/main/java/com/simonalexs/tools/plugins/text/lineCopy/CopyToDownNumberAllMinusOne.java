package com.simonalexs.tools.plugins.text.lineCopy;


import com.intellij.openapi.editor.EditorModificationUtil;
import com.simonalexs.tools.plugins.text.baseDealMode.BaseLineCopy;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyToDownNumberAllMinusOne extends BaseLineCopy {
    @Override
    public boolean isTextLegal(String text) {
        if (StringUtils.isEmpty(text)) {
            return false;
        }
        return true;
    }

    @Override
    public String getResultText(String text) {
        String newText = text;
        Matcher matcher = Pattern.compile("(\\d+)").matcher(text);
        String tempStr;
        while (matcher.find()) {
            tempStr = matcher.group();
            newText = newText.replaceFirst(tempStr, String.valueOf(Integer.parseInt(tempStr) - 1));
        }
        newText = text + newText;;
        return newText;
    }

    @Override
    public void writeResultText(String resultText, String origionText){
        EditorModificationUtil.insertStringAtCaret(editor, resultText);
    }
}
