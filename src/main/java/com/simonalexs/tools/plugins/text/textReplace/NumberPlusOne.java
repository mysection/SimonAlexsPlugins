package com.simonalexs.tools.plugins.text.textReplace;

import com.intellij.openapi.editor.EditorModificationUtil;
import com.simonalexs.tools.plugins.text.baseDealMode.BaseTextReplace;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberPlusOne extends BaseTextReplace {
    @Override
    public boolean isTextLegal(String text) {
        if (StringUtils.isEmpty(text) || !Pattern.compile("\\d+").matcher(text).find()) {
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
            newText = newText.replaceFirst(tempStr, String.valueOf(Integer.parseInt(tempStr) + 1));
        }
        return newText;
    }

    @Override
    public void writeResultText(String resultText, String origionText){
        EditorModificationUtil.insertStringAtCaret(editor, resultText);
    }
}
