import com.alibaba.fastjson.JSON;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.ui.Messages;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author owl
 * @Date 2019/6/17
 * @Description
 */
public class MyDictionnaryAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Caret caret = e.getData(CommonDataKeys.CARET);
        String selectedText = caret.getSelectedText();
        String result = null;
        try {
            result = YoudaoTranslate.sendGet(selectedText);
            Map<String, Object> maps = (Map) JSON.parse(result);
            Map<String, Object> basic = (Map<String, Object>)maps.get("basic");
            Object phonetic = basic.get("us-phonetic");
            List<String> explains = (List<String>) basic.get("explains");
            String resultStr = "phonetic:" + String.valueOf(phonetic) + "\n" + "explains:" + Arrays.toString(explains.toArray());
            Messages.showMessageDialog(resultStr, "提示", Messages.getInformationIcon());
        } catch (Exception e1) {
            e1.printStackTrace();
            if(result != null){
                Messages.showMessageDialog(result, "错误", Messages.getInformationIcon());
            }else{
                Messages.showMessageDialog(e.toString(), "错误", Messages.getInformationIcon());
            }

        }


    }
}
