package starlords.util.dialogControler.dialogValues;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import org.json.JSONObject;
import starlords.person.Lord;

public class DialogValue_playerMarketNumbers extends DialogValue_base{
    public DialogValue_playerMarketNumbers(JSONObject json, String key) {
        super(json, key);
    }

    @Override
    public int value(Lord lord, Lord targetLord) {
        int value = 0;
        FactionAPI faction = getFactionID(lord, targetLord);
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()) {
            if (market.getFaction().equals(faction)) value += 1;
        }
        return value;
    }
    protected FactionAPI getFactionID(Lord lord, Lord targetLord){
        return Global.getSector().getPlayerFaction();
    }
}
