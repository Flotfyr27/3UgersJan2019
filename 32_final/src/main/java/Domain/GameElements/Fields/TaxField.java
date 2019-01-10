package Domain.GameElements.Fields;

import java.awt.*;

public class TaxField extends Field {
    private int tax;

    public TaxField(String name, String subtext, Color bgColour, int tax){
        super(name, subtext, bgColour);
        this.tax = tax;

        

    }
}
