package starlords.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.missions.academy.GACelestialObject;
import com.fs.starfarer.api.util.Misc;
import com.sun.source.tree.CaseTree;
import exerelin.campaign.DiplomacyManager;
import exerelin.campaign.alliances.Alliance;
import exerelin.utilities.*;
import starlords.person.Lord;

import javax.swing.text.ChangedCharSetException;
import java.util.HashMap;
import java.util.Map;

public class NexerlinUtilitys {
    public static void declarePeace(FactionAPI proposer,FactionAPI propose){
        DiplomacyManager.adjustRelations(proposer,propose,0.51f,null,null,RepLevel.INHOSPITABLE);
    }
    public static void declareWar(FactionAPI proposer,FactionAPI propose){
        DiplomacyManager.adjustRelations(proposer,propose,-1.1f,null,null,RepLevel.HOSTILE);
    }
    public static boolean canBeAttacked(FactionAPI faction){
        if (NexConfig.getFactionConfig(faction.getId()).pirateFaction) return false;
        if (!NexConfig.getFactionConfig(faction.getId()).canInvade) return false;
        if (!NexConfig.getFactionConfig(faction.getId()).playableFaction) return false;
        return true;
    }
    public static boolean canBeAttacked(MarketAPI market){
        return NexUtilsMarket.canBeInvaded(market,false);
    }
    public static boolean canChangeRelations(FactionAPI faction){
        //if (NexConfig.getFactionConfig(faction.getId()).hostileToAll || NexConfig.getFactionConfig(faction.getId()).playableFaction) return false;
        if (!NexConfig.getFactionConfig(faction.getId()).playableFaction) return false;
        if (NexConfig.getFactionConfig(faction.getId()).disableDiplomacy) return false;
        if (Misc.isPirateFaction(faction)) return false;
        return true;
    }
    public static boolean isMinorFaction(FactionAPI faction){
        //ok, so I have lined wether or not something is a minor faction and can be attacked together. this can be overwritten independently, but for now this makes sense I think?
        return !canBeAttacked(faction);
    }

    public static Map<Alliance.Alignment, Float> getFactionAlignments(String factionId) {
        return NexConfig.getFactionConfig(factionId).getAlignmentValues();
    }

    public static float generateCloseAlignment(float alignment) {
        if (alignment > 0.5f) return alignment - 0.5f;
        if (alignment < -0.5f) return alignment + 0.5f;

        return alignment + (Utils.getRandomChance(2) - 0.5f);
    }

    public static float generateRandomAlignment() {
        return ((float) Utils.getRandomChance(5) - 2f) / 2f;
    }

    public static Map<Alliance.Alignment, Float> generateLordAlignments(Lord lord) {
        Map<Alliance.Alignment, Float> factionAlignments =  getFactionAlignments(lord.getFaction().getId());
        Map<Alliance.Alignment, Float> lordAlignments = new HashMap<>();

        for (Map.Entry<Alliance.Alignment, Float> alignment : factionAlignments.entrySet()) {
            int chance = Utils.getRandomChance(100);
            float newAlignment = 0;
            if (chance < 50) newAlignment = alignment.getValue();
            else if (chance < 80) newAlignment = generateCloseAlignment(alignment.getValue());
            else newAlignment = generateRandomAlignment();

            lordAlignments.put(alignment.getKey(),newAlignment);
        }

    return lordAlignments;
    }

    public static float getAlignmentsComparison(Map<Alliance.Alignment, Float> a, Map<Alliance.Alignment, Float> b) {
        float difference = 0;
        for (Map.Entry<Alliance.Alignment, Float> alignment : a.entrySet()) {
            difference += Math.abs(alignment.getValue() - b.get(alignment.getKey()));
        }
        return difference;
    }
}
