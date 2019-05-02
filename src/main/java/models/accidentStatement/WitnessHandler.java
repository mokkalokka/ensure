package models.accidentStatement;

import java.util.ArrayList;

public class WitnessHandler {
    private ArrayList<Witness> temporaryListOfWitnesses = new ArrayList<>();

    public ArrayList<Witness> getTemporaryListOfWitnesses() {
        return temporaryListOfWitnesses;
    }

    public void addTemporaryWitness(Witness witness) {
        this.temporaryListOfWitnesses.add(witness);
    }

    public int getTemporaryWitnessesCount() {
        return temporaryListOfWitnesses.size();
    }

}


