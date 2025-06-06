package starlords.util.dialogControler.dialogRull;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.SneakyThrows;
import org.json.JSONObject;
import starlords.person.Lord;
import starlords.util.dialogControler.dialogRull.bases.DialogRule_minmax;

public class DialogRule_playerLordRomanceAction extends DialogRule_minmax {
    @SneakyThrows
    public DialogRule_playerLordRomanceAction(JSONObject jsonObject,String key){
        super(jsonObject, key);
    }

    @Override
    protected int getValue(Lord lord, Lord targetLord, MarketAPI targetMarket) {
        int rel = lord.getRomanticActions();
        return rel;
    }
}
