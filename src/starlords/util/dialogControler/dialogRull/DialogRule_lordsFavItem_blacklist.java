package starlords.util.dialogControler.dialogRull;

import starlords.person.Lord;

import java.util.ArrayList;

public class DialogRule_lordsFavItem_blacklist extends DialogRule_Base {
    ArrayList<String> data;
    public DialogRule_lordsFavItem_blacklist(ArrayList<String> data){
        this.data = data;
    }
    @Override
    public boolean condition(Lord lord) {
        for (String a : data){
            if (a.equals(lord.getTemplate().preferredItemId)){
                return false;
            }
        }
        return true;
    }
}
