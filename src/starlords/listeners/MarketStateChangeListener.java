package starlords.listeners;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyDecivListener;
import starlords.controllers.FiefController;
import starlords.controllers.LifeAndDeathController;

public class MarketStateChangeListener implements ColonyDecivListener {
    @Override
    public void reportColonyAboutToBeDecivilized(MarketAPI market, boolean fullyDestroyed) {

    }

    @Override
    public void reportColonyDecivilized(MarketAPI market, boolean fullyDestroyed) {
        FiefController.destroyMarket(market);
        LifeAndDeathController.getInstance().removeMarket(market);
    }
}
