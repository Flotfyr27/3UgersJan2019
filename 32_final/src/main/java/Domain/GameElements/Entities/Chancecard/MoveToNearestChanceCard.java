package Domain.GameElements.Entities.Chancecard;

import Domain.GameElements.Entities.Player;
import Domain.GameElements.Fields.Field;

public class MoveToNearestChanceCard extends ChanceCard {

    //TODO find ud af om der kun er mulighed for at skulle flytte til type eller
    // om der er flere af samme type der er forskellige move to destinationer

    public MoveToNearestChanceCard(String description){
        super(description);
    }

    public void action(Player player) {

    }

}
