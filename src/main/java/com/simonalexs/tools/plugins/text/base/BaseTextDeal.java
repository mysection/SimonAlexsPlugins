package com.simonalexs.tools.plugins.text.base;

import com.intellij.codeInsight.actions.MultiCaretCodeInsightAction;
import com.intellij.codeInsight.actions.MultiCaretCodeInsightActionHandler;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.simonalexs.tools.plugins.text.Enum.TextDealModeEnum;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * @ClassName: BaseTextDeal
 * @Description: TODO-wcy
 * @Author: wcy
 * @Date: 2022/3/4 11:35
 * @Version: 1.0
 */
public abstract class BaseTextDeal extends MultiCaretCodeInsightAction {
    protected Project project;
    protected Editor editor;
    protected Caret caret;
    protected PsiFile file;

    protected PsiFile file1;
    protected Editor editor1;
    protected Caret caret1;

    protected HashMap<String, Object> userObjects = new HashMap<>();

    protected TextDealModeEnum textDealMode;

    private void initProperties(Project project, Editor editor, Caret caret, PsiFile file) {
        this.project = project;
        this.editor = editor;
        this.caret = caret;
        this.file = file;
    }

    public void afterInitProperties() {

    }

    abstract public String getText();

    abstract public boolean isTextLegal(String text);

    abstract public String getResultText(String text);

    public void beforeWriteResultText(String resultText, String origionText) {

    }

    abstract public void writeResultText(String resultText, String origionText);

    public void afterWriteResultText(String resultText) {

    }

    @Override
    @NotNull
    protected MultiCaretCodeInsightActionHandler getHandler() {
        return
                new MultiCaretCodeInsightActionHandler() {
                    @Override
                    public void invoke(@NotNull Project project, @NotNull Editor editor, @NotNull Caret caret,
                                       @NotNull PsiFile file) {
                        initProperties(project, editor, caret, file);
                        afterInitProperties();

                        String origionText = getText();
                        if (!isTextLegal(origionText)) {
                            return;
                        }

                        String resultText = getResultText(origionText);

                        ApplicationManager.getApplication().runWriteAction(() ->
                                CommandProcessor.getInstance().executeCommand(
                                        project,
                                        () ->
                                                WriteAction.run(() -> {
                                                    beforeWriteResultText(resultText, origionText);
                                                    writeResultText(resultText, origionText);
                                                    afterWriteResultText(resultText);
                                                })
                                        ,
                                        "CamelCase",
                                        ActionGroup.EMPTY_GROUP
                                )
                        );
                    }
                };
    }
}
