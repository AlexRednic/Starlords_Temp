package starlords.util.dialogControler.dialogRull.randoms;

import starlords.controllers.LordController;
import starlords.controllers.PoliticsController;
import starlords.faction.LawProposal;
import starlords.person.Lord;

public class DialogRule_random_opinionOfPlayerProposal extends DialogRule_random_base{
    public DialogRule_random_opinionOfPlayerProposal(double multi) {
        super(multi);
    }
    @Override
    public double value(Lord lord) {
        LawProposal proposal = PoliticsController.getProposal(LordController.getPlayerLord());
        if (proposal == null) return 0;
        int opinion = PoliticsController.getApproval(lord, proposal, false).one;
        return super.value(lord) * opinion;
    }
}
